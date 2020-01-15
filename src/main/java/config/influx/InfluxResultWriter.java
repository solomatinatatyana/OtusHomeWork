package config.influx;

import org.influxdb.dto.Point;
import org.testng.ITestContext;

import java.util.concurrent.TimeUnit;

public class InfluxResultWriter {
    //private final static Predicate<Failure> NEW_DEFECT = f -> f.getDescription().getAnnotation(JiraDefectId.class) != null;
    //private final static Predicate<Failure> ASSERTION_FAILED = f -> f.getException().getClass().equals(AssertionFailedError.class);
    //private final static Predicate<Failure> TEST_ERROR = f -> !f.getException().getClass().equals(AssertionFailedError.class);

    private final InfluxClient client;
    private final InfluxConfig config;
    private long timestamp;

    public InfluxResultWriter(InfluxClient client, InfluxConfig config) {
        this.client = client;
        this.config = config;
        client.createDbIfNeeded(config.getDbName());
    }

    public void writeStats(ITestContext context) {
        timestamp = System.currentTimeMillis();
        writeTotal(context.getAllTestMethods().length);
        writeSuccess(context.getAllTestMethods().length - context.getFailedTests().size());
        writeFails( context.getFailedTests().size());
        //writeAssertionErrors(context.getFailedTests().size());
        //writeTestError(result.getFailures());

        System.out.println("Статистика отправлена в Influx");
    }

    /*private void writeTestError(List<Failure> failures) {
        int testErrorCount = (int) failures.stream().filter(TEST_ERROR).count();
        System.out.println("Test with errors: " + testErrorCount);
        Point p = getPoint("testError", testErrorCount);
        client.writePoint(p);
    }

    private void writeAssertionErrors(List<Failure> failures) {
        int assertionFailedCount = (int) failures.stream().filter(ASSERTION_FAILED).count();
        System.out.println("Assertion failed tests: " + assertionFailedCount);
        Point p = getPoint("assertionFailed", assertionFailedCount);
        client.writePoint(p);
    }*/

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
