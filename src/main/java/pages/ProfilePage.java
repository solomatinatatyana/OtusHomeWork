package pages;

import org.openqa.selenium.WebDriver;
import ru.yandex.qatools.htmlelements.loader.HtmlElementLoader;

public class ProfilePage {

    public ProfilePage(WebDriver driver) {
        HtmlElementLoader.populatePageObject(this,driver);
    }


}
