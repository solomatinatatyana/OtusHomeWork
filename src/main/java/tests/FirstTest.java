package tests;

import config.BaseWebDrivingTest;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ui.BrowserType;

@Test(groups = "smoke")
public class FirstTest extends BaseWebDrivingTest{

    Logger logger = LogManager.getLogger(getClass());

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
        logger.info(getClass().getSimpleName());
        driver.manage().window().maximize();
        driver.get(testData.getURL());
        System.out.println(driver.getTitle());
        logger.debug("End of Test");
    }

    @AfterMethod( alwaysRun = true)
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

}
