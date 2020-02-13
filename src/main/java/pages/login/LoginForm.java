package pages.login;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Block;

@Block(@FindBy(css = "form.new-log-reg__form.js-login"))
public class LoginForm {
    /**Кнопка "Войти и Регистрация"*/
    @FindBy(css = "button[class='header2__auth js-open-modal']")
    public WebElement signButton;

    /**Поле "Логин"*/
    @FindBy(xpath = ".//input[@name = 'email']")
    public WebElement usernameField;

    /**Поле "Пароль"*/
    @FindBy(css = "input[type=password]")
    public WebElement passwordField;

    /**Кнопка "Войти"*/
    @FindBy(css = "button[type='submit']")
    public WebElement submitButton;
}
