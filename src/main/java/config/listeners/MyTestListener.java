package config.listeners;

import config.influx.InfluxClient;
import config.influx.InfluxConfig;
import config.influx.InfluxResultWriter;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.TestNG;

public class MyTestListener extends TestNG implements ISuiteListener{

    private final static String host = System.getProperty("influx.host", "http://192.168.99.100:8086");
    private final static String user = System.getProperty("influx.user", "root");
    private final static String pass = System.getProperty("influx.pass", "root");
    private final static String db = System.getProperty("influx.db", "otusHM");
    private final static String measurement = System.getProperty("influx.measurement", "STATS");
    private final static String envTag = System.getProperty("influx.envtag", "env2");

    private final InfluxTestListener listener;

    public MyTestListener(){
        InfluxConfig config = new InfluxConfig(host, user, pass, db, measurement, envTag);
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

    public void run(){
        super.addListener(listener);
        super.run();
    }

}
