package tests.HomeWork4Tests;

import config.BaseWebDrivingTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import tests.HomeWork4Tests.dto.Offer;

import java.util.ArrayList;
import java.util.List;

public class WebCrawlerTest extends BaseWebDrivingTest {
    private Logger log = LogManager.getLogger(WebCrawlerTest.class);
    private SoftAssert softAssert = new SoftAssert();
    //=======================Тестовые данные=================
    private static final String URL = "https://www.drive2.ru/cars/?sort=selling";
    private List<String> carsList = new ArrayList<>();
    /*
    * собрать все объявления о продаже в csv-файл (ссылка, год автомобиля, цена, марка, модель, объем двигателя)
    */
    @BeforeClass(alwaysRun = true)
    public void init(){
        log.info("Переходим на сайт -> {}", URL);
        driver.get(URL);
        log.info("Получаем список машин для сбора информации");
        List<WebElement> carsListElements  = driver.findElements(By.cssSelector(".c-makes__item.is-important"));
        carsListElements.forEach(element->{
            carsList.add(element.getText());
            System.out.println(element.getText());
        });
    }

    @Test(description = "собрать все объявления о продаже в csv-файл")
    public void checkGetSellingCars(){
        log.info("Получаем количество объявлений по кадой марке машины");

        carsList.forEach(car->{
            driver.findElement(By.xpath(".//a[contains(text(),'"+ car +"')]")).click();
            while(isElementPresent(By.xpath(".//button[@data-action='catalog.morecars']")))
                ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,50000)");
            /*
            * .//div[contains(@class, 'c-darkening-hover-container') and .//div/span[contains(text(),'Acura ZDX Grey Wolf')]]/a
            * */
            int countOffers = driver.findElements(By.cssSelector(".c-darkening-hover-container")).size();
            log.info("Title: " + driver.findElement(By.xpath(".//div[@class = 'c-car-card-sa__caption']/span")).getText());
            //список названий  c-car-card-sa__caption
            log.info("Колчество предложений о продажи {} - {}", car, countOffers);
            log.info("Возвращамся назад к маркам авто ...");
            driver.get(URL);
        });



    }
    //-------------------------------------------------METHODS----------------------------------------------------------
    private boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }



    public List<WebElement> getAllSalesOffersByCar(){
        return driver.findElements(By.cssSelector(""));
    }


    public void writeToFile(Offer offer){

    }
}
