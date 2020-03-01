package config.injection.interfaces;

import org.aeonbits.owner.Config;

@Config.Sources("classpath:properties/auth.properties")
public interface AuthorizationConfig extends Config {

    @DefaultValue("tokio9507@mail.ru")
    @Key("login")
    String getLogin();

    @DefaultValue("123")
    @Key("password")
    String getPassword();
}
