package pages.webCrawlerPages;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.openqa.selenium.WebDriver;
import pages.AbstractPage;

import java.util.ArrayList;
import java.util.List;

public class MainPage extends AbstractPage {
    private List<String> carsList = new ArrayList<>();

    public MainPage(WebDriver driver) {
        super(driver);
    }
    public String carsElementsList = ".c-makes__item.is-important";

    public List<String> getCarsList(){
        String carPage = driver.getPageSource();
        Document html = Jsoup.parse(carPage);
        Elements cars = html.select(this.carsElementsList);
        cars.forEach(element -> {
            carsList.add(element.text());
            System.out.println(element.text());
        });
        return carsList;
    }
}
