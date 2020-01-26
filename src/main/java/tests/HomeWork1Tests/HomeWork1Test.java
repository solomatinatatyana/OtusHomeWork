package tests.HomeWork1Tests;

import config.BaseWebDrivingTest;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import config.ui.BrowserType;

public class HomeWork1Test extends BaseWebDrivingTest{

    private Logger logger = LogManager.getLogger(HomeWork1Test.class);

    @DataProvider(name = "browsers")
    public Object[][] Browsers() {
        return new Object[][] {
                {BrowserType.CHROME},
                {BrowserType.FIREFOX}
        };}

    @Test(dataProvider = "browsers")
    public void test(BrowserType type) {
        switch (type){
            case CHROME:
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
            case FIREFOX:
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
        }
        driver.manage().window().maximize();
        driver.get(testData.getURL());
        logger.info("Заголовок сайта: [" + driver.getTitle() + "]");
        Assert.assertEquals(driver.getCurrentUrl(),testData.getURL(),"Неверный URL");
    }

    @AfterMethod( alwaysRun = true)
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

}
