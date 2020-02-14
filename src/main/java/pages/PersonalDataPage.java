package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.HtmlElement;
import ru.yandex.qatools.htmlelements.element.TextInput;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementDecorator;

import java.util.List;


public class PersonalDataPage extends AbstractPage {
    public PersonalDataPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(new HtmlElementDecorator(driver), this);
    }

    public By communicationMethod = By.xpath(".//div[span[contains(text(),'Способ связи')]]");

    @FindBy(xpath = ".//button[contains(text(), 'Добавить')]")
    public Button addContactButton;

    // .//div[contains(@class,'container__row js-formset-row')]/
    /*@FindBy(xpath = ".//div[span[contains(text(),'Способ связи')]]")
    public List<WebElement> communicationMethod;*/

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
}
