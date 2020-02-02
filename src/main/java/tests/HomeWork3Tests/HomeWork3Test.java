package tests.HomeWork3Tests;

import config.BaseWebDrivingTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//@Test(groups = "smoke")
public class HomeWork3Test extends BaseWebDrivingTest {
    private Logger log = LogManager.getLogger(HomeWork3Test.class);

    //======Тестовые данные====
    private List<String> testSmartPhones = new ArrayList<>();
    private static final String baseUrl = "https://market.yandex.ru/";

    @BeforeClass(alwaysRun = true)
    public void init(){
        testSmartPhones.addAll(Arrays.asList("HUAWEI", "Xiaomi"));
        driver.get("https://ya.ru");
        driver.get(baseUrl+"catalog--mobilnye-telefony/54726/list?hid=91491&local-offers-first=0&onstock=1");
        this.filterSmartPhones(testSmartPhones);
        this.sortByPrice();
    }

    @Test(description = "Проверить, что отобразилась плашка 'Товар {HUAWEI} добавлен к сравнению'")
    public void testHuawei(){
        log.info("Проверка добавления HUAWEI к сравнению");
        this.addSmartPhoneToCompare("HUAWEI");
        //Проверить, что отобразилась плашка "Товар {RedMi} добавлен к сравнению"
    }

    @Test(description = "Проверить, что отобразилась плашка 'Товар {Xiaomi} добавлен к сравнению'",
    dependsOnMethods = "testHuawei")
    public void testXiaomi(){
        log.info("Проверка добавления Xiaomi к сравнению");
        this.addSmartPhoneToCompare("Xiaomi");
        //Проверить, что отобразилась плашка "Товар {Xiaomi} добавлен к сравнению"
    }

    /*@Test(description = "Перейти в раздел Сравнение. Проверить, что в списке товаров 2 позиции",
            dependsOnMethods = "testXiaomi")
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
        WebDriverWait wait = new WebDriverWait(driver,10L);wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//fieldset[@data-autotest-id='7893318']")));
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

    public boolean retryingFindClick(By by) {
        boolean result = false;
        int attempts = 0;
        while(attempts < 2) {
            try {
                driver.findElement(by).click();
                result = true;
                break;
            } catch(StaleElementReferenceException e) {
            }
            attempts++;
        }
        return result;
    }

    private void addSmartPhoneToCompare(String model){
        //todo исправить ожидания и улучить локаторы, избавиться от StaleException
        WebDriverWait wait = new WebDriverWait(driver,10L);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".n-snippet-list")));
        driver.findElements(By.cssSelector(".n-snippet-list>div.n-snippet-cell2"));
        List<WebElement> good1 = driver.findElements(By.cssSelector(".n-snippet-list>div.n-snippet-cell2 a[title*="+ model +"]"));
        WebElement parent = good1.get(0).findElement(By.xpath(".."));
        parent.findElement(By.cssSelector("div.n-product-toolbar__item")).click();
        log.info(parent.getText());
        log.info("Телефон [{}] добавлен к сравнению", model);
    }

}
