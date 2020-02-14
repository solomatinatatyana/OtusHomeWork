package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementDecorator;

public class SkillsPage extends AbstractPage {
    public SkillsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(new HtmlElementDecorator(driver), this);
    }
}
