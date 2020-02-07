package tests.HomeWork4Tests;

import config.BaseWebDrivingTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import tests.HomeWork4Tests.dto.Car;

import java.util.ArrayList;
import java.util.List;

public class WebCrawlerTest extends BaseWebDrivingTest {
    private Logger log = LogManager.getLogger(WebCrawlerTest.class);
    private SoftAssert softAssert = new SoftAssert();
    //=======================Тестовые данные=================
    private List<String> typesOfCarsList = new ArrayList<>();
    private Car car = new Car();
    /*
    * собрать все объявления о продаже в csv-файл (ссылка, год автомобиля, цена, марка, модель, объем двигателя)
    */
    @BeforeClass(alwaysRun = true)
    public void init(){
        driver.get("https://www.drive2.ru/cars/?sort=selling");
    }

    @Test(description = "собрать все объявления о продаже в csv-файл")
    public void checkGetSellingCars(){
        log.info("init");
        car.setDeepLink(driver.findElement(By.xpath(".//a[text()='Acura']")).getAttribute("href"));
        log.info("deeplink:{}", car.getDeepLink());
    }
    //-------------------------------------------------METHODS----------------------------------------------------------
    public String getDeepLinkByType(String type){
        return car.getDeepLink();
    }

    public List<String> getCarsTypeList(){
        return typesOfCarsList;
    }

    public void writeToFile(Car car){

    }
}
