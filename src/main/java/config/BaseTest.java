package config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;

/**
 * Безовый класс для запуска тестов. Содержит настройки логирования и другие общие настройки
 */
@Listeners(MyTestListener.class)
public class BaseTest {
    protected static Logger LOG;
    @BeforeClass(alwaysRun = true)
    public void setUp(){
         LOG = LogManager.getLogger(this.getClass().getSimpleName());
    }
}
