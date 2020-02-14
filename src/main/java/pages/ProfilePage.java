package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.qatools.htmlelements.element.HtmlElement;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementDecorator;

import java.util.List;

public class ProfilePage extends AbstractPage{

    public ProfilePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(new HtmlElementDecorator(driver), this);
    }

    @FindBy(xpath = ".//a[@class = 'learning-item__box']")
    public List<WebElement> learningBox;

    @FindBy(xpath = ".//a[@title = 'Мои курсы']")
    public HtmlElement myCourses;

    @FindBy(xpath = ".//a[@title = 'Оплата']")
    public HtmlElement payment;

    @FindBy(xpath = ".//a[@title = 'О себе']")
    public HtmlElement biography;

    @FindBy(xpath = ".//a[@title = 'Приведи друга']")
    public HtmlElement invite;

    @FindBy(xpath = ".//a[@title = 'Работа в кампаниях']")
    public HtmlElement employment;

    @FindBy(xpath = ".//a[@title = 'Настройки']")
    public HtmlElement settings;

    @FindBy(xpath = ".//a[@title = 'Документы']")
    public HtmlElement documents;

    public void goToBiography(){
        WebDriverWait wait = new WebDriverWait(driver,50L);
        wait.until(ExpectedConditions.visibilityOf(biography));
        this.biography.click();
    }

}
