package tests.HomeWork4Tests;

import config.BaseWebDrivingTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import tests.HomeWork4Tests.dto.Offer;

import java.util.ArrayList;
import java.util.List;

public class WebCrawlerTestPageSourceTest extends BaseWebDrivingTest {
    private Logger log = LogManager.getLogger(WebCrawlerTestPageSourceTest.class);
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
        String carPage = driver.getPageSource();
        Document html = Jsoup.parse(carPage);
        Elements cars = html.select(".c-makes__item.is-important");
        cars.forEach(element -> {
            carsList.add(element.text());
            System.out.println(element.text());
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
                while(isElementPresent(By.xpath(".//button[@data-action='catalog.morecars']"))) scrollBy(50000);
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
        String modelPage = driver.getPageSource();
        Document html = Jsoup.parse(modelPage);
        Elements models = html.select(".c-car-card-sa__caption>span");
        models.forEach(element -> {
            String text =  element.text();
            if (text.contains("'")) {
                int indexOfSilva = text.indexOf("De'Silva");
                text = text.substring(0, indexOfSilva);
            }
            if(text.contains("Beauty in Black")){
                int indexOfBeauty = text.indexOf("Beauty in Black");
                text = text.substring(0, indexOfBeauty);
            }
            modelsList.add(text);
            System.out.println(text);
        });
    }

    /*public void setOfferList(String carType, String model, List<Offer> offerList){
        String carPage = driver.getPageSource();
        Document html = Jsoup.parse(carPage);
        Element price = html.select(".c-car-forsale__price>strong").first();
        Element volume = html.select("ul[class=c-car-forsale__info]>li:nth-child(2)").first();
        Element year = html.select("ul[class=c-car-forsale__info]>li:nth-child(5)").first();
        String deeplink = driver.getCurrentUrl();
        offerList.add(new Offer(Offer.anOffer()
                .withCar(carType)
                .withModel(model)
                .withVolume(WebCrawlerHelper.getVolumeFromString(volume.text()))
                .withPrice(price.text())
                .withYear(WebCrawlerHelper.getYearOfCarFromString(year.text()))
                .withDeepLink(deeplink)
                .build()));
    }*/

    public void setOfferList(String carType, String model, List<Offer> offerList){
        WebElement price = (new WebDriverWait(driver,200))
                .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".c-car-forsale__price>strong")));
        WebElement volume = (new WebDriverWait(driver,200))
                .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("ul[class=c-car-forsale__info]>li:nth-child(2)")));
        WebElement year = (new WebDriverWait(driver,200))
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
