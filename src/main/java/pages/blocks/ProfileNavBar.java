package pages.blocks;

import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

@Name("Search form")
@FindBy(xpath = ".//div[@class = 'nav__items']")
public class ProfileNavBar extends HtmlElement {
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

}
