package config.interfaces;

import org.aeonbits.owner.Config;

@Config.Sources("classpath:properties/testData.properties")
public interface TestDataConfig extends Config {

    @DefaultValue("https://google.com/")
    @Key("URL")
    String getURL();
}
