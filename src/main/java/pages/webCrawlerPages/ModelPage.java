package pages.webCrawlerPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.AbstractPage;

import java.util.List;

public class ModelPage extends AbstractPage {
    public ModelPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public String modelsElements = ".c-car-card-sa__caption>span";
    public By moreCarsButton = By.xpath(".//button[@data-action='catalog.morecars']");

    @FindBy(css = ".c-darkening-hover-container")
    public List<WebElement> offerContainer;

    public void goToModel(String car){
        driver.findElement(By.xpath(".//a[contains(text(),'"+ car +"')]")).click();
    }

}
