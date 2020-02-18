package pages.otusPages.ProfilePages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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

    public By removeContactButton = By.xpath("./div[3]/div[2]/button[text() = 'Удалить']");
    public By addContactButtonLocator = By.xpath(".//button[contains(text(), 'Добавить')]");
    public By selectOptions = By.cssSelector(".lk-cv-block__select-scroll.lk-cv-block__select-scroll_service.js-custom-select-options");
    public By contBlock1 = By.xpath(".//div[@data-num = 1]");

    @FindBy(xpath = ".//div[@data-num = 0]")
    public HtmlElement contactBlock0;

    @FindBy(xpath = ".//div[@data-num = 1]")
    public HtmlElement contactBlock1;

    @FindBy(xpath = ".//button[contains(text(), 'Добавить')]")
    public WebElement addContactButton;

    @FindBy(xpath = "//div[span[contains(text(),'Способ связи')]]")
    public HtmlElement communicationMethod;

    @FindBy(xpath = ".//input[@name= 'contact-0-value']")
    public TextInput communicationTextInput0;

    @FindBy(xpath = ".//input[@name= 'contact-1-value']")
    public TextInput communicationTextInput1;

    @FindBy(xpath = ".//button[@title= 'WhatsApp']")
    public Button whatsAppButton;

    @FindBy(xpath = ".//button[@title= 'VK']")
    public Button VKButton;

    @FindBy(xpath = ".//button[@title= 'Сохранить и продолжить']")
    public Button saveButton;

    public void selectCommunicationMethod(Button button){
        communicationMethod.click();
        WebDriverWait wait = new WebDriverWait(driver,50L);
        wait.until(ExpectedConditions.presenceOfElementLocated(selectOptions));
        scrollBy(50);
        button.click();
    }
    public void scrollBy(int y){ ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,"+ y +")"); }
}
