package pages.otusPages.blocks;

import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Block;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.HtmlElement;
import ru.yandex.qatools.htmlelements.element.Link;

@Name("ProfileNavSelect")
@Block(@FindBy( xpath = ".//div[contains(@class,'header2-menu__item-wrapper__username')]"))
public class ProfileSelect extends HtmlElement {

    @FindBy(xpath = "//a[@title = 'Личный кабинет']")
    public Link profile;
}
