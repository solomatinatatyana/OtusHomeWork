package pages.webCrawlerPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.AbstractPage;

public class CarPage extends AbstractPage {
    public CarPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    private By price = By.cssSelector(".c-car-forsale__price>strong");
    private By volume = By.cssSelector("ul[class=c-car-forsale__info]>li:nth-child(2)");
    private By year = By.cssSelector("ul[class=c-car-forsale__info]>li:nth-child(5)");

    public WebElement getPrice(){
        return (new WebDriverWait(driver,200))
                .until(ExpectedConditions.presenceOfElementLocated(this.price));
    }

    public WebElement getVolume(){
        return (new WebDriverWait(driver,200))
                .until(ExpectedConditions.presenceOfElementLocated(this.volume));
    }

    public WebElement getYear(){
        return (new WebDriverWait(driver,200))
                .until(ExpectedConditions.presenceOfElementLocated(this.year));
    }

    public void goToOffer(String offer){
        driver.findElement(By.xpath(".//div[contains(@class, 'c-darkening-hover-container') and .//div/span[contains(text(),'"+ offer +"')]]/a")).click();
    }
}
