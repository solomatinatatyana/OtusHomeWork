package tests.HomeWork3Tests;

import config.BaseWebDrivingTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//@Test(groups = "smoke")
public class HomeWork3Test extends BaseWebDrivingTest {
    private Logger log = LogManager.getLogger(HomeWork3Test.class);
    private SoftAssert softAssert = new SoftAssert();

    //=======================Тестовые данные=================
    private List<String> testSmartPhones = new ArrayList<>();
    private static final String baseUrl = "https://market.yandex.ru/";
    private static String nameOfGood;
    private enum ModelSmartPhones{
        HUAWEI("HUAWEI"),
        XIAOMI("Xiaomi");
        private String model;
        ModelSmartPhones(String model) { this.model = model; }
        public String getModel() { return model; }
    }

    @DataProvider(name = "models")
    public Object[][] Browsers() {
        return new Object[][] {
                {ModelSmartPhones.HUAWEI.getModel()},
                {ModelSmartPhones.XIAOMI.getModel()}
        };}

    @BeforeClass(alwaysRun = true)
    public void init(){
        testSmartPhones.addAll(Arrays.asList("HUAWEI", "Xiaomi"));
        driver.get("https://ya.ru");
        driver.get(baseUrl+"catalog--mobilnye-telefony/54726/list?hid=91491&local-offers-first=0&onstock=1");
        this.filterSmartPhones(testSmartPhones);
        this.sortByPrice();
    }

    @Test(description = "Проверить, что отобразилась плашка 'Товар {товар} добавлен к сравнению'",
            dataProvider = "models")
    public void testGoodToCompare(String model) throws InterruptedException {
        log.info("Проверка добавления "+ model +" к сравнению");
        addSmartPhoneToCompare(model);
        WebElement paneNotify = (new WebDriverWait(driver, 30))
                .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.popup-informer__title")));
        String textLabel = paneNotify.getText();
        softAssert.assertTrue(paneNotify.isDisplayed(),"плашка [Товар {"+ nameOfGood +"} добавлен к сравнению] не отображается");
        softAssert.assertEquals(textLabel,"Товар "+ nameOfGood +" добавлен к сравнению", "Текст сообщения неверный");
        softAssert.assertAll();
    }

    /*@Test(description = "Перейти в раздел Сравнение. Проверить, что в списке товаров 2 позиции",
            dependsOnMethods = "testGoodToCompare")
    public void checkComparingGoods(){
        //todo
        log.info("Проверка Раздела Сравнение");
    }

    @Test(description = "Нажать на опцию 'все характеристики'. " +
            "Проверить, что в списке характеристик появилась позиция 'Операционная система'",
            dependsOnMethods = "checkComparingGoods")
    public void checkOperationSystem(){
        //todo
        log.info("Проверка характеристики 'Операционная система'");
    }

    @Test(description = "Нажать на опцию 'различающиеся характеристики'. " +
            "Проверить, что позиция 'Операционная система' не отображается в списке характеристик",
            dependsOnMethods = "checkOperationSystem")
    public void checkOperationSystemNoInList(){
        //todo
        log.info("Проверка отсутствия характеристики 'Операционная система'");
    }*/
//-------------------------------------------------METHODS--------------------------------------------------------------
    private void filterSmartPhones(List<String> smartPhones){
        WebElement fieldsetBrand = driver.findElement(By.xpath(".//fieldset[@data-autotest-id='7893318']"));
        smartPhones.forEach(e->{
            fieldsetBrand.findElement(By.xpath(".//div/span[contains(text(),'"+ e +"')]")).click();
            log.info("Выбрана модель телефона: [{}]",e);
        });
        WebElement fieldsetType = driver.findElement(By.xpath(".//fieldset[@data-autotest-id='16816262']"));
        fieldsetType.findElement(By.xpath(".//div/span[contains(text(),'смартфон')]")).click();
    }

    private void sortByPrice(){
        WebElement sortBlock = driver.findElement(By.xpath(".//div[@class = 'n-filter-block_pos_left i-bem']"));
        sortBlock.findElement(By.xpath(".//a[contains(text(),'по цене')]")).click();
        log.info("Список телефонов отсортирован");
    }

    private void addSmartPhoneToCompare(String model) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver,50L);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(".//div[contains(@class,'preloadable__preloader')]")));
        WebElement good = driver.findElement(By.xpath(".//a[contains(@title,'"+ model +"')]/.."));
        good.findElement(By.cssSelector("div.n-product-toolbar__item")).click();
        nameOfGood = good.findElement(By.cssSelector("a[title*="+ model +"]")).getAttribute("title");
        log.info("Телефон [{}] добавлен к сравнению", nameOfGood);
    }

}
