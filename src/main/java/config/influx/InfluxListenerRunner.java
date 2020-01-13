package config.influx;

import config.listeners.MyTestListener;

public class InfluxListenerRunner {

    private final MyTestListener listener;

    public InfluxListenerRunner(Class<?> klass){
        //super(klass);
        //InfluxConfig config = new InfluxConfig();
        InfluxClient client = new InfluxClient();
        InfluxResultWriter writer  = new InfluxResultWriter();
        this.listener = new MyTestListener();
    }
}
