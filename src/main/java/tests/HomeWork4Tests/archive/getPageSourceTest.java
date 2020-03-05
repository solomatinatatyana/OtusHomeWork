package tests.HomeWork4Tests.archive;

import config.BaseWebDrivingTest;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.testng.annotations.Test;

public class getPageSourceTest extends BaseWebDrivingTest {
    @Test()
    public void test(){
        driver.get("https://www.drive2.ru/cars/?sort=selling");
        String str = driver.getPageSource();

        Document html = Jsoup.parse(str);
        String title = html.title();
        String h1 = html.body().getElementsByTag("a").text();
        Elements listNews = html.select(".c-makes__item.is-important");
        listNews.forEach(element -> System.out.println(element.text()));
    }

    @Test()
    public void test2(){
        driver.get("https://www.drive2.ru/cars/audi/?sort=selling");
        String str = driver.getPageSource();
        Document html = Jsoup.parse(str);
        Elements listNews = html.select(".c-car-card-sa__caption>span");
        listNews.forEach(element -> System.out.println(element.text()));
    }

    @Test()
    public void test3(){
        driver.get("https://www.drive2.ru/r/acura/tl/1044921/");
        String offerPage = driver.getPageSource();
        Document html = Jsoup.parse(offerPage);
        Element price = html.select(".c-car-forsale__price>strong").first();
        Element volume = html.select("ul[class=c-car-forsale__info]>li:nth-child(2)").first();
        Element year = html.select("ul[class=c-car-forsale__info]>li:nth-child(5)").first();
        System.out.println("price: "+ price.text());
        System.out.println("volume: "+ volume.text());
        System.out.println("year: "+ year.text());
    }
}


