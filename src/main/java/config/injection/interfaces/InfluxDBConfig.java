package config.injection.interfaces;

import org.aeonbits.owner.Config;

@Config.Sources("classpath:properties/influxdb.properties")
public interface InfluxDBConfig extends Config {

    @Key("influx.host")
    String getHost();

    @Key("influx.user")
    String getUser();

    @Key("influx.pass")
    String getPassword();

    @Key("influx.db")
    String getDbName();

    @Key("influx.measurement")
    String getMeasurement();

    @Key("influx.envtag")
    String getEnvtag();

}
