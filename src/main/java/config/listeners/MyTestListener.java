package config.listeners;

import config.influx.InfluxClient;
import config.influx.InfluxConfig;
import config.influx.InfluxResultWriter;
import config.injection.interfaces.InfluxDBConfig;
import org.aeonbits.owner.ConfigFactory;
import org.testng.*;

public class MyTestListener implements ISuiteListener, ITestListener {

    private InfluxTestListener listener;
    private InfluxDBConfig influxDBConfig;

    public MyTestListener() {
        influxDBConfig = ConfigFactory.create(InfluxDBConfig.class);
        InfluxConfig config = new InfluxConfig(
                influxDBConfig.getHost(),
                influxDBConfig.getUser(),
                influxDBConfig.getPassword(),
                influxDBConfig.getDbName(),
                influxDBConfig.getMeasurement(),
                influxDBConfig.getEnvtag());
        InfluxClient client = new InfluxClient(config);
        InfluxResultWriter writer = new InfluxResultWriter(client, config);
        this.listener = new InfluxTestListener(writer);
    }

    public void onStart(ISuite suite) {
        System.out.println("START [" + suite.getXmlSuite().getName()+  "] SUITE");
    }

    public void onFinish(ISuite suite) {
        System.out.println("END OF [" + suite.getXmlSuite().getName()+  "] SUITE");
    }

    @Override
    public void onTestStart(ITestResult iTestResult) { }

    @Override
    public void onTestSuccess(ITestResult iTestResult) { }

    @Override
    public void onTestFailure(ITestResult iTestResult) { }

    @Override
    public void onTestSkipped(ITestResult iTestResult) { }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) { }

    @Override
    public void onStart(ITestContext iTestContext) { }

    @Override
    public void onFinish(ITestContext context) {
        listener.onFinish(context);
    }
}
