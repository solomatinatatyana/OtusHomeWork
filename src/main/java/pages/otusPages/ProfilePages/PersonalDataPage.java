package pages.otusPages.ProfilePages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.AbstractPage;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.HtmlElement;
import ru.yandex.qatools.htmlelements.element.TextInput;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementDecorator;

import javax.print.DocFlavor;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class PersonalDataPage extends AbstractPage {
    public PersonalDataPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(new HtmlElementDecorator(driver), this);
    }

    public By communicationMethod = By.xpath(".//div[span[contains(text(),'Способ связи')]]");

    @FindBy(xpath = ".//button[contains(text(), 'Добавить')]")
    public Button addContactButton;

    @FindBy(xpath = ".//button[contains(text(), 'Удалить')]")
    public Button removeContactButton;

    @FindBy(xpath = ".//input[@name= 'contact-0-value']")
    public TextInput communicationTextInput0;

    @FindBy(xpath = ".//input[@name= 'contact-1-service']")
    public HtmlElement communicationMethod1;

    @FindBy(xpath = ".//input[@name= 'contact-1-value']")
    public TextInput communicationTextInput1;

    @FindBy(xpath = ".//button[@title= 'WhatsApp']")
    public Button watsAppButton;

    @FindBy(xpath = ".//button[@title= 'Сохранить и продолжить']")
    public Button saveButton;

    public String selectCommunicationMethod(Button button){
        List<WebElement> list = driver.findElements(this.communicationMethod);
        list.get(0).click();
        button.click();
        System.out.println(button.getName());
        return button.getName();
    }
}
