package pages.login;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.AbstractPage;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementDecorator;

public class LoginPageOtus extends AbstractPage implements ILoginPage{

    private By loginForm = By.cssSelector("form.new-log-reg__form.js-login");
    private By username = By.cssSelector("input[name=email]");
    private By pass = By.cssSelector("input[type=password]");
    private By submit = By.cssSelector("button[type='submit']");

    /**Кнопка "Войти и Регистрация"*/
    @FindBy(css = "button[class='header2__auth js-open-modal']")
    public Button signButton;

    public LoginPageOtus(WebDriver driver) {
        super(driver);
        PageFactory.initElements(new HtmlElementDecorator(driver), this);
    }

    @Override
    public void doLogin(String url, String login, String password) {
        login(url, login, password);
    }

    @Override
    public void doLogin(String login, String password) {
        login(login, password);
    }

    private void login(String url, String login, String password){
        driver.get(url);
        loginUI(login, password);
    }

    private void loginUI(String login, String password) {
        this.signButton.click();
        WebElement loginFormElem = (new WebDriverWait(driver,50L)
       .until(ExpectedConditions.visibilityOfElementLocated(loginForm)));
        loginFormElem.findElement(username).clear();
        loginFormElem.findElement(username).sendKeys(login);
        loginFormElem.findElement(pass).clear();
        loginFormElem.findElement(pass).sendKeys(password);
        loginFormElem.findElement(submit).click();
    }

    private void login(String login, String password){loginUI(login, password); }
}
