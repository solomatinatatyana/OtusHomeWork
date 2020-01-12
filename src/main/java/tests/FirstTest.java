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

    private Logger logger = LogManager.getLogger(FirstTest.class);

    @DataProvider(name = "browsers")
    public Object[][] Browsers() {
        return new Object[][] {
                {BrowserType.CHROME},
                {BrowserType.FIREFOX}
        };}

    @Test(dataProvider = "browsers")
    public void test(BrowserType type) {
        logger.info("Start test Class [" + getClass().getSimpleName() + "]  on Browser [" + type +"]");
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
        System.out.println("Заголовок сайта: [" + driver.getTitle() + "]");
        logger.info("End of test Class [" + getClass().getSimpleName() + "]  on Browser [" + type +"]");
    }

    @AfterMethod( alwaysRun = true)
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

}
