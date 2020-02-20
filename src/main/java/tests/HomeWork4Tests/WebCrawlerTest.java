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
    private WebCrawlerHelper webCrawlerHelper;
    //=======================Тестовые данные=================
    private static final String URL = "https://www.drive2.ru/cars/?sort=selling";
    private String currentUrl;
    private String carType;
    private List<String> carsList = new ArrayList<>();
    private List<Offer> offerList = new ArrayList<>();
    /*
    * собрать все объявления о продаже в csv-файл (ссылка, год автомобиля, цена, марка, модель, объем двигателя)
    */
    @BeforeClass(alwaysRun = true)
    public void init(){
        webCrawlerHelper = new WebCrawlerHelper(webApp);
        log.info("Переходим на сайт -> {}", URL);
        driver.get(URL);
        log.info("Получаем список машин для сбора информации");
        carsList = getCarsList();
    }

    @Test(description = "собрать все объявления о продаже")
    public void checkGetSellingCars(){
        log.info("Получаем количество объявлений по каждой марке машины");
        try{
        offerList = getAllOffersListByCar(carsList);
        helpers.csvHelper.writeToFile(offerList);
        }catch (Exception e){
            log.error("Ой, что-то пошло не так");
            e.printStackTrace();
        }finally {
            Assert.assertTrue( offerList.size() != 0, "Объявлений не нашлось");
            helpers.csvHelper.writeToFile(offerList);
        }

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
        List<WebElement> carsListElements  = driver.findElements(By.cssSelector(".c-makes__item.is-important"));
        carsListElements.forEach(element->{
            carsList.add(element.getText());
            System.out.println(element.getText());
        });
        return carsList;
    }

    public List<Offer> getAllOffersListByCar(List<String> carsList){
        carsList.forEach(car ->{
            carType = car;
            List<String> modelsList = new ArrayList<>();
            driver.findElement(By.xpath(".//a[contains(text(),'"+ car +"')]")).click();
            while(isElementPresent(By.xpath(".//button[@data-action='catalog.morecars']"))) scrollBy(50000);
            int countOffers = driver.findElements(By.cssSelector(".c-darkening-hover-container")).size()-1;
            log.info("Количество предложений о продажи {} - {}", car, countOffers);
            setModelList(modelsList);
            modelsList.forEach(e->{
                currentUrl = driver.getCurrentUrl();
                log.info(e);
                //переходим на объявление
                driver.findElement(By.xpath(".//div[contains(@class, 'c-darkening-hover-container') and .//div/span[contains(text(),'"+ e +"')]]/a")).click();
                //собираем объявление и добавляем в список
                setOfferList(carType,e,offerList);
                log.info("Возвращаемся к списку моделей ...");
                driver.get(currentUrl);
            });
            log.info("Возвращамся назад к маркам авто ...");
            driver.get(URL);
            offerList.forEach(elem-> System.out.println(elem.toString()));
        });
        return offerList;
    }

    public void setModelList(List<String> modelsList){
        List<WebElement> modelListElements = driver.findElements(By.xpath(".//div[@class = 'c-car-card-sa__caption']/span"));
        modelListElements.forEach(e->{
            String text =  e.getText();
            if (text.contains("\'")) {
                int indexOfSilva = text.indexOf("De'Silva");
                text = text.substring(0, indexOfSilva);
            }
            modelsList.add(text);
            System.out.println(text);
        });
    }

    public void setOfferList(String carType, String model, List<Offer> offerList){
        WebElement price = (new WebDriverWait(driver,100))
                .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".c-car-forsale__price>strong")));
        WebElement volume = (new WebDriverWait(driver,100))
                .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("ul[class=c-car-forsale__info]>li:nth-child(2)")));
        WebElement year = (new WebDriverWait(driver,100))
                .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("ul[class=c-car-forsale__info]>li:nth-child(5)")));
        String deeplink = driver.getCurrentUrl();
        offerList.add(new Offer(Offer.anOffer()
                .withCar(carType)
                .withModel(model)
                .withVolume(WebCrawlerHelper.getVolumeFromString(volume.getText()))
                .withPrice(price.getText())
                .withYear(WebCrawlerHelper.getYearOfCarFromString(year.getText()))
                .withDeepLink(deeplink)
                .build()));
    }

    public void scrollBy(int y){ ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,"+ y +")"); }
}
