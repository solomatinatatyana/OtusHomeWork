package config;

import org.aeonbits.owner.Config;

@Config.Sources("classpath:properties/testData.properties")
public interface TestDataConfig extends Config {

    @DefaultValue("https://otus.ru/")
    @Key("URL")
    String getURL();
}
