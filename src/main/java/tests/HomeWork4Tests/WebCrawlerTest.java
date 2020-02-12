package tests.HomeWork4Tests;

import config.BaseWebDrivingTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
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
    private String currentUrl;
    private List<String> carsList = new ArrayList<>();
    private List<Offer> offerList = new ArrayList<>();
    private List<WebElement> carsListElements = new ArrayList<>();
    /*
    * собрать все объявления о продаже в csv-файл (ссылка, год автомобиля, цена, марка, модель, объем двигателя)
    */
    @BeforeClass(alwaysRun = true)
    public void init(){
        log.info("Переходим на сайт -> {}", URL);
        driver.get(URL);
        log.info("Получаем список машин для сбора информации");
        carsList = getCarsList();
    }

    @Test(description = "собрать все объявления о продаже")
    public void checkGetSellingCars(){
        log.info("Получаем количество объявлений по кадой марке машины");
        carsList.forEach(car ->{
            List<String> modelsList = new ArrayList<>();
            driver.findElement(By.xpath(".//a[contains(text(),'"+ car +"')]")).click();
            while(isElementPresent(By.xpath(".//button[@data-action='catalog.morecars']")))
                ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,50000)");
            int countOffers = driver.findElements(By.cssSelector(".c-darkening-hover-container")).size()-1;
            log.info("Количество предложений о продажи {} - {}", car, countOffers);
            List<WebElement> modelListElements = driver.findElements(By.xpath(".//div[@class = 'c-car-card-sa__caption']/span"));
            modelListElements.forEach(e->{
                String text =  e.getText();
                if (text.contains("\'")) {
                    text = text.replace("'", " ");
                }
                modelsList.add(text);
                System.out.println(text);
            });
            modelsList.forEach(e->{
                currentUrl = driver.getCurrentUrl();
                Offer offer = new Offer();
                log.info(e);
                while(isElementPresent(By.xpath(".//button[@data-action='catalog.morecars']")))
                    ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,50000)");
                //переходим на объявление
                driver.findElement(By.xpath(".//div[contains(@class, 'c-darkening-hover-container') and .//div/span[contains(text(),'"+ e +"')]]/a")).click();
                offer.setModel(e);
                WebElement price = (new WebDriverWait(driver,100))
                        .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".c-car-forsale__price>strong")));
                offer.setPrice(price.getText());
                offerList.add(new Offer(offer));
                //здесь будет запись объекта в файл csv
                log.info("Возвращаемся к списку моделей ...");
                driver.get(currentUrl);
            });
            log.info("Возвращамся назад к маркам авто ...");
            driver.get(URL);
            offerList.forEach(elem-> System.out.println(elem.toString()));
        });

        Assert.assertNotNull(offerList);
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

    public List<String> getCarsList(){
        carsListElements  = driver.findElements(By.cssSelector(".c-makes__item.is-important"));
        carsListElements.forEach(element->{
            carsList.add(element.getText());
            System.out.println(element.getText());
        });
        return carsList;
    }

    public List<Offer> getAllOffersListByCar(List<String> carsList){

        return offerList;
    }

    public void writeToFile(List<Offer> offerList){

    }

    public void readFromFile(String file){

    }
}
