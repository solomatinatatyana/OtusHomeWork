package config.listeners;

import config.influx.InfluxResultWriter;
import org.testng.ITestContext;
import org.testng.TestListenerAdapter;

public class InfluxTestListener extends TestListenerAdapter {

    private final InfluxResultWriter influxWriter;

    public InfluxTestListener(InfluxResultWriter writer) {
        this.influxWriter = writer;
    }

    @Override
    public void onFinish(ITestContext context){
        super.onFinish(context);
        influxWriter.writeStats(context);
    }

}
