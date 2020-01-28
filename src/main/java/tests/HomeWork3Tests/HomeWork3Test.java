package tests.HomeWork3Tests;

import config.BaseWebDrivingTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//@Test(groups = "smoke")
public class HomeWork3Test extends BaseWebDrivingTest {
    private Logger logger = LogManager.getLogger(HomeWork3Test.class);

    private List<String> testSmartPhones = new ArrayList<>();

    @BeforeClass(alwaysRun = true)
    public void init(){
        testSmartPhones.addAll(Arrays.asList("RedMi", "Xiaomi"));
        driver.get("https://ya.ru");
        driver.get("https://market.yandex.ru/catalog--mobilnye-telefony/54726/list?hid=91491&local-offers-first=0&onstock=1");
    }

    @Test(description = "Проверить, что отобразилась плашка 'Товар {RedMi} добавлен к сравнению'")
    public void testRedMi(){
        this.filterSmartPhones(testSmartPhones);
        this.sort();
        this.addSmartPhoneToCompare("RedMi");
        //Проверить, что отобразилась плашка "Товар {RedMi} добавлен к сравнению"
        logger.info("Проверка RedMi");
    }

    @Test(description = "Проверить, что отобразилась плашка 'Товар {Xiaomi} добавлен к сравнению'",
    dependsOnMethods = "testRedMi")
    public void testXiaomi(){
        this.filterSmartPhones(testSmartPhones);
        this.sort();
        this.addSmartPhoneToCompare("Xiaomi");
        //Проверить, что отобразилась плашка "Товар {Xiaomi} добавлен к сравнению"
        logger.info("Проверка Xiaomi");
    }

    @Test(description = "Перейти в раздел Сравнение. Проверить, что в списке товаров 2 позиции",
            dependsOnMethods = "testXiaomi")
    public void checkComparingGoods(){
        //todo
        logger.info("Проверка Раздела Сравнение");
    }

    @Test(description = "Нажать на опцию 'все характеристики'. " +
            "Проверить, что в списке характеристик появилась позиция 'Операционная система'",
            dependsOnMethods = "checkComparingGoods")
    public void checkOperationSystem(){
        //todo
        logger.info("Проверка характеристики 'Операционная система'");
    }

    @Test(description = "Нажать на опцию 'различающиеся характеристики'. " +
            "Проверить, что позиция 'Операционная система' не отображается в списке характеристик",
            dependsOnMethods = "checkOperationSystem")
    public void checkOperationSystemNoInList(){
        //todo
        logger.info("Проверка отсутствия характеристики 'Операционная система'");
    }
//-------------------------------------------------METHODS--------------------------------------------------------------
    private void filterSmartPhones(List<String> smartPhones){
        //todo
    }

    private void sort(){
        //todo
    }

    private void addSmartPhoneToCompare(String model){
        //todo
    }

}
