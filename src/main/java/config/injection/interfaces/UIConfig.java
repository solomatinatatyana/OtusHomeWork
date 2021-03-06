package config.injection.interfaces;

import org.aeonbits.owner.Config;
import config.ui.BrowserName;

@Config.Sources({"classpath:webdriver.properties"})
public interface UIConfig extends Config{

    @DefaultValue(BrowserName.CHROME)
    @Key("browser")
    String getBrowser();
}
