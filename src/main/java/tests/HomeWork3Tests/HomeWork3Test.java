package tests.HomeWork3Tests;

import config.BaseWebDrivingTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hamcrest.MatcherAssert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.hasItem;

//@Test(groups = "smoke")
public class HomeWork3Test extends BaseWebDrivingTest {
    private Logger log = LogManager.getLogger(HomeWork3Test.class);
    private SoftAssert softAssert = new SoftAssert();

    //=======================Тестовые данные=================
    private List<String> testSmartPhones = new ArrayList<>();
    private static final String baseUrl = "https://market.yandex.ru/";
    private static String nameOfGood;
    private static String LIST_OF_GOODS;
    private List<String> allCharacteristicsList = new ArrayList<>();
    private List<String> differentCharacteristicsList = new ArrayList<>();
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
        testSmartPhones.addAll(Arrays.asList(
                ModelSmartPhones.HUAWEI.getModel(),
                ModelSmartPhones.XIAOMI.getModel()));
        driver.get("https://ya.ru");
        driver.get(baseUrl+"catalog--mobilnye-telefony/54726/list?hid=91491&local-offers-first=0&onstock=1");
        this.filterSmartPhones(testSmartPhones);
        this.sortByPrice();
    }

    @Test(description = "Проверить, что отобразилась плашка 'Товар {товар} добавлен к сравнению'",
            dataProvider = "models")
    public void testGoodToCompare(String model){
        log.info("Проверка добавления "+ model +" к сравнению");
        addSmartPhoneToCompare(model);
        WebElement paneNotify = (new WebDriverWait(driver, 100))
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.popup-informer__title")));
        String textLabel = paneNotify.getText();
        softAssert.assertTrue(paneNotify.isDisplayed(),"плашка [Товар {"+ nameOfGood +"} добавлен к сравнению] не отображается");
        softAssert.assertEquals(textLabel,"Товар "+ nameOfGood +" добавлен к сравнению", "Текст сообщения неверный");
        softAssert.assertAll();
    }

    @Test(description = "Перейти в раздел Сравнение. Проверить, что в списке товаров 2 позиции",
            dependsOnMethods = "testGoodToCompare", alwaysRun = true)
    public void checkComparingGoods(){
        /*Закрыть плашку о добавлении в сравнение*/
        driver.findElement(By.cssSelector(".popup-informer__close")).click();
        /*Перейти на Экран [Сравнение]*/
        driver.findElement(By.xpath(".//a[.//span[text()='Сравнение']]")).click();
        /*Проверить, что в списке товаров 2 позиции*/
        int countGoodInComparing = driver.findElements(By.cssSelector(".n-compare-content__line>div.n-compare-cell")).size();
        Assert.assertEquals(countGoodInComparing,2,"Неверное количество товаров добавлено в сравнение");
    }

    @Test(description = "Нажать на опцию 'все характеристики'. " +
            "Проверить, что в списке характеристик появилась позиция 'Операционная система'",
            dependsOnMethods = "checkComparingGoods", alwaysRun = true)
    public void checkOperationSystem(){
        WebElement allCharacteristics = driver.findElement(By.xpath(".//span[@class='link n-compare-show-controls__all' and @role='button']"));
        allCharacteristics.click();
        List<WebElement> characteristics = driver.findElements(By.cssSelector(".n-compare-row-name"));
        characteristics.forEach(element->allCharacteristicsList.add(element.getText()));
        MatcherAssert.assertThat("В общих характеристиках отсутсвует характеристика - ОПЕРАЦИОННАЯ СИСТЕМА",
                allCharacteristicsList, hasItem("ОПЕРАЦИОННАЯ СИСТЕМА"));
    }

    /*@Test(description = "Нажать на опцию 'различающиеся характеристики'. " +
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

    private void addSmartPhoneToCompare(String model) {
        LIST_OF_GOODS = ".//a[contains(@title, '"+ model +"')]";
        WebDriverWait wait = new WebDriverWait(driver,50L);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(".//div[contains(@class,'preloadable__preloader')]")));
        WebElement firstGoodByModel = driver.findElement(By.xpath(LIST_OF_GOODS + "/..//div[contains(@class,'n-product-toolbar__item')]"));
        firstGoodByModel.click();
        nameOfGood = driver.findElement(By.cssSelector("a[title*="+model+"]")).getAttribute("title");
        log.info("Телефон [{}] добавлен к сравнению", nameOfGood);
    }

}
