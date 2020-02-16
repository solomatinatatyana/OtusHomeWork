package pages.otusPages.ProfilePages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.AbstractPage;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementDecorator;

public class SkillsPage extends AbstractPage {
    public SkillsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(new HtmlElementDecorator(driver), this);
    }

    @FindBy(xpath = ".//button[@title= 'Сохранить и продолжить']")
    public Button saveButton;
}
