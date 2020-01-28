package config.influx;

import org.influxdb.dto.Point;
import org.testng.ITestContext;

import java.util.concurrent.TimeUnit;

public class InfluxResultWriter {
    private final InfluxClient client;
    private final InfluxConfig config;
    private long timestamp;

    public InfluxResultWriter(InfluxClient client, InfluxConfig config) {
        this.client = client;
        this.config = config;
        client.createDbIfNeeded(config.getDbName());
    }

    public void writeStats(ITestContext result) {
        timestamp = System.currentTimeMillis();
        writeTotal(result.getAllTestMethods().length);
        writeSuccess(result.getAllTestMethods().length - result.getFailedTests().size());
        writeFails(result.getFailedTests().size());

        System.out.println("Статистика отправлена в Influx");
    }

    private void writeTotal(int total) {
        System.out.println("Total: " + total);
        Point p = getPoint("total", total);
        client.writePoint(p);
    }

    private void writeSuccess(int success) {
        System.out.println("Success: " + success);
        Point p = getPoint("succeed", success);
        client.writePoint(p);
    }

    private void writeFails(int fails) {
        System.out.println("Fails: " + fails);
        Point p = getPoint("fail", fails);
        client.writePoint(p);
    }

    private Point getPoint(String field, int value) {
        return Point
                .measurement(config.getMeasurement())
                .tag("environment", config.getEnvTag())
                .addField(field, value)
                .time(timestamp, TimeUnit.MILLISECONDS)
                .build();
    }

}
