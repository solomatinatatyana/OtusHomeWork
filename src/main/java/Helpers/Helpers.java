package Helpers;

import config.ui.WebApplicationManager;
import org.apache.logging.log4j.Logger;
import tests.HomeWork4Tests.WebCrawlerHelper;
import tests.HomeWork4Tests.utils.CSVHelperImpl;
import tests.HomeWork4Tests.utils.ICSVHelper;

public class Helpers {
    private WebApplicationManager webApp;
    private Logger log;

    public ICSVHelper csvHelper;
    public WebCrawlerHelper webCrawlerHelper;

    public Helpers(WebApplicationManager webApp, Logger log) {
        this.webApp = webApp;
        this.log = log;
        this.csvHelper = new CSVHelperImpl();
        this.webCrawlerHelper = new WebCrawlerHelper(webApp);
    }
}
