package tests;

import config.BaseWebDrivingTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test(groups = "smoke")
public class HomeWork2Test extends BaseWebDrivingTest{

    private Logger logger = LogManager.getLogger(HomeWork2Test.class);

    @Test(description = "Перейти на Otus.ru")
    public void goToMainPage() {
        driver.get(testData.getURL());
        logger.info("Заголовок сайта: [" + driver.getTitle() + "]");
        Assert.assertEquals(driver.getCurrentUrl(),testData.getURL(),"Неверный URL");
    }

    @Test(description = "логин", dependsOnMethods = "goToMainPage")
    public void login() {
        //todo
    }
}
