package config;

import Helpers.*;
import config.injection.interfaces.AuthorizationConfig;
import config.injection.interfaces.TestDataConfig;
import config.ui.BrowserType;
import config.ui.WebApplicationManager;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

/**
 * Класс для запуска тестов с использованием браузера
 */
public class BaseWebDrivingTest extends BaseTest {
    protected WebApplicationManager webApp;
    protected WebDriver driver;
    protected TestDataConfig testData;
    protected AuthorizationConfig auth;
    protected MutableCapabilities options;
    public Helpers helpers;
    public Pages pages;

    public void setDriver(WebDriver driver){
        this.driver = driver;
    }

    protected  static String browser = System.getProperty("browser").toUpperCase();

    @BeforeClass(alwaysRun = true)
    public void setUp(){
        super.setUp();
        log.info("Test: [{}]",this.getClass().asSubclass(this.getClass()).getSimpleName());
        log.info("Browser: [{}]",BrowserType.valueOf(browser));
        testData = ConfigFactory.create(TestDataConfig.class);
        auth = ConfigFactory.create(AuthorizationConfig.class);
        this.options = new MutableCapabilities();
        this.webApp = new WebApplicationManager(BrowserType.valueOf(browser), options);
        this.setDriver(webApp.getDriver());
        helpers = new Helpers(webApp, log);
        pages = new Pages(webApp);
    }

    @AfterClass(alwaysRun = true)
    public void tearDown(){
        log.info("END of TEST - {}",getClass().asSubclass(getClass().getSuperclass()).getSimpleName());
        if (driver != null) {
            driver.quit();
        }
    }
}
