package tests.HomeWork4Tests;

import config.BaseWebDrivingTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import tests.HomeWork4Tests.dto.Offer;

import java.util.ArrayList;
import java.util.List;

public class WebCrawlerTestPageSourceTest extends BaseWebDrivingTest {
    private Logger log = LogManager.getLogger(WebCrawlerTestPageSourceTest.class);
    //=======================Тестовые данные=================
    private String currentUrl;
    private String carType;
    private List<String> carsList = new ArrayList<>();
    private List<Offer> offerList = new ArrayList<>();
    /*
    * собрать все объявления о продаже в csv-файл (ссылка, год автомобиля, цена, марка, модель, объем двигателя)
    */
    @BeforeClass(alwaysRun = true)
    public void init(){
        log.info("Переходим на сайт -> {}", testData.getWebCrawlerIrl());
        helpers.webCrawlerHelper.open(testData.getWebCrawlerIrl());
        log.info("Получаем список машин для сбора информации");
        carsList = pages.mainPage.getCarsList();
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

    public List<Offer> getAllOffersListByCar(List<String> carsList){
        carsList.forEach(car ->{
            carType = car;
            List<String> modelsList = new ArrayList<>();
            pages.modelPage.goToModel(car);
            while(helpers.webCrawlerHelper.isElementPresent(pages.modelPage.moreCarsButton))
                helpers.webCrawlerHelper.scrollBy(50000);
            int countOffers = helpers.webCrawlerHelper.getCountOffers();
            log.info("Количество предложений о продажи {} - {}", car, countOffers);
            helpers.webCrawlerHelper.setModelList(modelsList);
            modelsList.forEach(e->{
                currentUrl = driver.getCurrentUrl();
                log.info(e);
                //переходим на объявление
                helpers.webCrawlerHelper.goToOffer(e);
                //собираем объявление и добавляем в список
                helpers.webCrawlerHelper.setOfferList(carType,e,offerList);
                log.info("Возвращаемся к списку моделей ...");
                helpers.webCrawlerHelper.open(currentUrl);
            });
            log.info("Возвращамся назад к маркам авто ...");
            helpers.webCrawlerHelper.open(testData.getWebCrawlerIrl());
            offerList.forEach(elem-> System.out.println(elem.toString()));
        });
        return offerList;
    }
}
