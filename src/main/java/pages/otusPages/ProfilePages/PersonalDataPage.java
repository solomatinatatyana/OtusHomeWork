package pages.otusPages.ProfilePages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.AbstractPage;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.HtmlElement;
import ru.yandex.qatools.htmlelements.element.TextInput;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementDecorator;


public class PersonalDataPage extends AbstractPage {

    public PersonalDataPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(new HtmlElementDecorator(driver), this);
    }
    public By selectOptions = By.cssSelector(".lk-cv-block__select-scroll.lk-cv-block__select-scroll_service.js-custom-select-options");
    public By removeContactButton = By.xpath("./div[3]/div[2]/button[text() = 'Удалить']");

    @FindBy(xpath = "//div[span[contains(text(),'Способ связи')]]")
    public HtmlElement communicationMethod;

    @FindBy(xpath = ".//input[@name= 'contact-0-value']")
    public TextInput communicationTextInputFirst;

    @FindBy(xpath = ".//input[@name= 'contact-1-value']")
    public TextInput communicationTextInputSecond;

    @FindBy(xpath = ".//div[@data-num='0']/div/div/div/div/div/div/button[@data-value='whatsapp']")
    public Button whatsAppButtonFirst;

    @FindBy(xpath = ".//div[@data-num='1']/div/div/div/div/div/div/button[@data-value='whatsapp']")
    public Button whatsAppButtonSecond;

    @FindBy(xpath = ".//button[@title= 'Сохранить и продолжить']")
    public Button saveButton;

    @FindBy(xpath = ".//div[@data-num = 0]")
    public WebElement contactBlockFirst;

    @FindBy(xpath = ".//div[@data-num = 1]")
    public WebElement contactBlockSecond;

    @FindBy(xpath = ".//button[contains(text(), 'Добавить')]")
    public WebElement addContactButton;

    /*@FindBy(xpath = "./div[3]/div[2]/button[text() = 'Удалить']")
    public WebElement removeContactButton;*/


    public void selectCommunicationMethod(Button button){
        WebDriverWait wait = new WebDriverWait(driver,50L);
        wait.until(ExpectedConditions.visibilityOfAllElements(this.communicationMethod));
        this.communicationMethod.click();
        WebDriverWait wait2 = new WebDriverWait(driver,50L);
        wait2.until(ExpectedConditions.presenceOfElementLocated(selectOptions));
        button.click();
    }

    public void addContact(){
        WebDriverWait wait = new WebDriverWait(driver, 50L);
        wait.until(ExpectedConditions.visibilityOfAllElements(this.addContactButton));
        this.addContactButton.click();
    }

    public void deleteContact(WebElement element){
        WebDriverWait wait = new WebDriverWait(driver, 50L);
        wait.until(ExpectedConditions.visibilityOfAllElements(element));
        element.findElement(this.removeContactButton).click();
    }
}
