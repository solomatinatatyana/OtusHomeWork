package Helpers;

import config.ui.WebApplicationManager;
import pages.webCrawlerPages.CarPage;
import pages.webCrawlerPages.MainPage;
import pages.webCrawlerPages.ModelPage;

public class Pages {
    public WebApplicationManager webApp;

    //Pages
    public MainPage mainPage;
    public ModelPage modelPage;
    public CarPage carPage;

    public Pages(WebApplicationManager webApp) {
        this.webApp = webApp;
        this.mainPage = new MainPage(webApp.getDriver());
        this.modelPage = new ModelPage(webApp.getDriver());
        this.carPage = new CarPage(webApp.getDriver());
    }
}
