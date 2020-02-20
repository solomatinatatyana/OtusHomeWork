package tests.HomeWork4Tests;

import config.ui.WebApplicationManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WebCrawlerHelper extends WebApplicationManager{
    private Logger log = LogManager.getLogger(WebCrawlerHelper.class);
    private  static  final String regExDigital = "[0-9]*[.,]?[0-9]";

    private WebApplicationManager webApp;
    private WebDriver driver;

    public WebCrawlerHelper(WebApplicationManager webApp) {
        driver = webApp.getDriver();
    }

    public static String getVolumeFromString(String string){
        Pattern pattern = Pattern.compile(regExDigital);
        Matcher matcher = pattern.matcher(string);
        if(matcher.find()) string = matcher.group();
        return string;
    }

    public static String getYearOfCarFromString(String string){
        Pattern pattern = Pattern.compile(regExDigital);
        Matcher matcher = pattern.matcher(string);
        if(matcher.find()) string = matcher.group();
        return string;
    }

    public static String getFormattedString(String string){
        Pattern pattern = Pattern.compile("\\s{2,}");
        Matcher matcher = pattern.matcher(string);
        if(matcher.find())
            string = string.replaceAll("\\s{2,}"," ");
        return string;
    }

    public void scrollBy(int y){ ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,"+ y +")"); }

}
