package tests.HomeWork2Tests;

import config.BaseWebDrivingTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.concurrent.TimeUnit;

@Test(groups = "smoke")
public class HomeWork2Test extends BaseWebDrivingTest{
    SoftAssert softAssert = new SoftAssert();

    private Logger logger = LogManager.getLogger(HomeWork2Test.class);

    @Test(description = "Перейти на otus.ru")
    public void goToMainPage() {
        driver.get(testData.getURL());
        logger.info("Заголовок сайта: [{}]", driver.getTitle());
        Assert.assertEquals(driver.getCurrentUrl(),testData.getURL(),"Неверный URL");
    }

    @Test(description = "логин", dependsOnMethods = "goToMainPage")
    public void incorrectLogin() throws InterruptedException {
        //By.xpath(".//button[@class='header2__auth js-open-modal']")
        driver.findElement(By.cssSelector("button[class='header2__auth js-open-modal']")).click();
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
        WebElement loginForm = driver.findElement(By.cssSelector("form.new-log-reg__form.js-login"));
        loginForm.findElement(By.cssSelector("input[name=email]")).sendKeys("tokio9507@mail.ru");
        loginForm.findElement(By.cssSelector("input[type=password]")).sendKeys("123");
        loginForm.findElement(By.cssSelector("button[type='submit']")).click();
        WebElement incorrectLabel = (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.new-input-error.new-input-error_top.new-input-error_form.js-text")));
        softAssert.assertTrue(incorrectLabel.isDisplayed(),"Сообщение о неверно введенных данных не отображается!");
        softAssert.assertEquals(incorrectLabel.getText(),"Такая пара логин/пароль не существует",
                "Неверный текст сообщения");
        softAssert.assertEquals(Color.fromString(incorrectLabel.getCssValue("color")).asHex(),"#9a0f0f",
                "Цвет сообщения не #9a0f0f (красный)");
        softAssert.assertAll();
    }
}