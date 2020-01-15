package config.injection;

import com.google.inject.AbstractModule;
import config.injection.configprovider.UIConfigProvider;
import config.injection.interfaces.UIConfig;

public class InjectionUIConfigModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(UIConfig.class).toProvider(UIConfigProvider.class);
    }
}
