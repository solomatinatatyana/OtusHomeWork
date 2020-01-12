package config;

import org.testng.annotations.BeforeClass;

/**
 * Безовый класс для запуска тестов. Содержит настройки логирования и другие общие настройки
 */
public class BaseTest {
    @BeforeClass(alwaysRun = true)
    public void setUp(){
        //подключаем общее логирование
    }
}
