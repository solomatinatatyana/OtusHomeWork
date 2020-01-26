package config;

import com.google.inject.Inject;
import config.injection.InjectionUIConfigModule;
import config.injection.interfaces.UIConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Guice;

/**
 * Безовый класс для запуска тестов. Содержит настройки логирования и другие общие настройки
 */
//@Listeners(MyTestListener.class)
@Guice(modules = InjectionUIConfigModule.class)
public class BaseTest {
    protected static Logger log;

    @Inject
    protected UIConfig uiConfig;

    @BeforeClass(alwaysRun = true)
    public void setUp(){
         log = LogManager.getLogger("TestRunner");
    }
}
