package tests.HomeWork4Tests;

import config.BaseWebDrivingTest;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
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
        listNews.get(0).text();
        listNews.forEach(element -> System.out.println(element.text()));
    }

    @Test()
    public void test2(){
        //String
    }
}
