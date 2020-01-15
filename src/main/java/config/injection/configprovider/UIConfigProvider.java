package config.injection.configprovider;

import com.google.inject.Provider;
import config.injection.interfaces.UIConfig;
import org.aeonbits.owner.ConfigFactory;

public class UIConfigProvider implements Provider<UIConfig> {
    @Override
    public UIConfig get() {
        return new LocalConfig(ConfigFactory.create(UIConfig.class,System.getProperties()));
    }

    public static class LocalConfig implements UIConfig{
        UIConfig config;

        public LocalConfig(UIConfig config) {
            this.config = config;
        }

        @Override
        public String getBrowser() {
            return config.getBrowser();
        }
    }
}
