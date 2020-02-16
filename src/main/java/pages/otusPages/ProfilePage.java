package pages.otusPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.AbstractPage;
import pages.otusBlocks.blocks.ProfileNavBar;
import pages.otusBlocks.blocks.ProfileSelect;
import ru.yandex.qatools.htmlelements.loader.HtmlElementLoader;

public class ProfilePage extends AbstractPage {

    public ProfileNavBar profileNavBar;
    public ProfileSelect profileSelectElements;

    @FindBy(xpath = ".//div[contains(@class,'header2-menu__item-wrapper__username')]")
    public WebElement profileSelect;

    public ProfilePage(WebDriver driver) {
        super(driver);
        HtmlElementLoader.populatePageObject(this, driver);
    }

    public void goToBiography(){
        WebDriverWait wait = new WebDriverWait(driver,50L);
        wait.until(ExpectedConditions.visibilityOf(profileNavBar.biography));
        profileNavBar.biography.click();
    }

    public void goToMyProfile(){
        WebDriverWait wait = new WebDriverWait(driver,50L);
        wait.until(ExpectedConditions.visibilityOf(profileSelect));
        Actions builder = new Actions(driver);
        builder.moveToElement(profileSelect).perform();
        profileSelectElements.profile.click();
    }

}
