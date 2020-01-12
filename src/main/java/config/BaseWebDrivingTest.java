package config;

import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

/**
 * Класс для запуска тестов с использованием браузера
 */
public class BaseWebDrivingTest extends BaseTest {
    protected WebDriver driver;
    protected TestDataConfig testData;

    @BeforeClass(alwaysRun = true)
    public void setUp(){
        testData = ConfigFactory.create(TestDataConfig.class);
        //добавить DriverFactory
        //driver.manage().window().maximize();
    }

    @AfterClass(alwaysRun = true)
    public void tearDown(){
        if (driver != null) {
            driver.quit();
        }
    }
}
