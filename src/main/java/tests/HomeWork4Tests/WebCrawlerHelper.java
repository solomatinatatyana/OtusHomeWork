package tests.HomeWork4Tests;

import Helpers.Pages;
import config.ui.WebApplicationManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import tests.HomeWork4Tests.dto.Offer;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WebCrawlerHelper{
    private Logger log = LogManager.getLogger(WebCrawlerHelper.class);
    private  static  final String regExDigital = "[0-9]*[.,]?[0-9]";
    private static final String deSilvaModel = "De'Silva";
    private static final String beautyInBlackModel = "Beauty in Black";

    private WebDriver driver;
    private Pages pages;

    public WebCrawlerHelper(WebApplicationManager webApp) {
        this.driver = webApp.getDriver();
        this.pages = new Pages(webApp);
    }

    public static String getVolumeFromString(String string){
        Pattern pattern = Pattern.compile(regExDigital);
        Matcher matcher = pattern.matcher(string);
        if(matcher.find()) string = matcher.group();
        return string;
    }

    public static String getYearOfCarFromString(String string){
        Pattern pattern = Pattern.compile(regExDigital);
        Matcher matcher = pattern.matcher(string);
        if(matcher.find()) string = matcher.group();
        return string;
    }

    public static String getFormattedString(String string){
        Pattern pattern = Pattern.compile("\\s{2,}");
        Matcher matcher = pattern.matcher(string);
        if(matcher.find())
            string = string.replaceAll("\\s{2,}"," ");
        return string;
    }

    public void open(String url){
        driver.get(url);
    }

    public void setModelList(List<String> modelsList){
        String modelPageParsed = driver.getPageSource();
        Document html = Jsoup.parse(modelPageParsed);
        Elements models = html.select(pages.modelPage.modelsElements);
        models.forEach(element -> {
            String text =  element.text();
            if (text.contains("'")) {
                int indexOfSilva = text.indexOf(deSilvaModel);
                text = text.substring(0, indexOfSilva);
            }
            if(text.contains(beautyInBlackModel)){
                int indexOfBeauty = text.indexOf(beautyInBlackModel);
                text = text.substring(0, indexOfBeauty);
            }
            modelsList.add(text);
            System.out.println(text);
        });
    }

    public void goToOffer(String offer){
        while(isElementPresent(pages.modelPage.moreCarsButton)) scrollBy(50000);
        pages.carPage.goToOffer(offer);
    }

    public void setOfferList(String carType, String model, List<Offer> offerList){
        String deeplink = driver.getCurrentUrl();
        offerList.add(new Offer(Offer.anOffer()
                .withCar(carType)
                .withModel(model)
                .withVolume(WebCrawlerHelper.getVolumeFromString(pages.carPage.getVolume().getText()))
                .withPrice(pages.carPage.getPrice().getText())
                .withYear(WebCrawlerHelper.getYearOfCarFromString(pages.carPage.getYear().getText()))
                .withDeepLink(deeplink)
                .build()));
    }

    public int getCountOffers(){
        return pages.modelPage.offerContainer.size()-1;
    }

    public boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void scrollBy(int y){ ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,"+ y +")"); }

}
