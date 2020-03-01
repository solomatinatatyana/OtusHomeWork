package pages.otusPages.ProfilePages.archive;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.AbstractPage;

public class PersonalDataPage2 extends AbstractPage {
    public PersonalDataPage2(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }
    public By selectOptions = By.cssSelector(".lk-cv-block__select-scroll.lk-cv-block__select-scroll_service.js-custom-select-options");
    public By communicationMethod = By.xpath("//div[span[contains(text(),'Способ связи')]]");
    public By communicationTextInputFirst = By.xpath(".//input[@name= 'contact-0-value']");
    public By communicationTextInputSecond = By.xpath(".//input[@name= 'contact-1-value']");
    public By saveButton = By.xpath(".//button[@title= 'Сохранить и продолжить']");
    public By whatsAppButtonFirst = By.xpath(".//div[@data-num='0']/div/div/div/div/div/div/button[@data-value='whatsapp']");
    public By whatsAppButtonSecond = By.xpath(".//div[@data-num='1']/div/div/div/div/div/div/button[@data-value='whatsapp']");
    public By contactBlock0 = By.xpath(".//div[@data-num = 0]");
    public By contactBlock1 = By.xpath(".//div[@data-num = 1]");
    public By removeContactButton = By.xpath("./div[3]/div[2]/button[text() = 'Удалить']");
    public By addContactButtonLocator = By.xpath(".//button[contains(text(), 'Добавить')]");

    public void selectCommunicationMethod(WebElement button){
        WebDriverWait wait = new WebDriverWait(driver,50L);
        wait.until(ExpectedConditions.presenceOfElementLocated(this.communicationMethod));
        driver.findElement(this.communicationMethod).click();
        WebDriverWait wait2 = new WebDriverWait(driver,50L);
        wait2.until(ExpectedConditions.presenceOfElementLocated(selectOptions));
        button.click();
    }
}
