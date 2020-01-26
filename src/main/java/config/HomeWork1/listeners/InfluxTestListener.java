package config.HomeWork1.listeners;

import config.HomeWork1.influx.InfluxResultWriter;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class InfluxTestListener implements ITestListener {

    private final InfluxResultWriter influxWriter;


    public InfluxTestListener(InfluxResultWriter writer) {
        this.influxWriter = writer;
    }

    @Override
    public void onFinish(ITestContext context) {
        influxWriter.writeStats(context);
    }

    @Override
    public void onTestStart(ITestResult result) { }

    @Override
    public void onTestSuccess(ITestResult result) { }

    @Override
    public void onTestFailure(ITestResult result) { }

    @Override
    public void onTestSkipped(ITestResult result) { }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) { }

    @Override
    public void onStart(ITestContext context) { }
}
