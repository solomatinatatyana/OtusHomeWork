package config.ui;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;

import java.util.Arrays;

public class WebDriverFactory {
    public static WebDriver createNewDriver(BrowserType browser){
        WebDriver driver = null;
        switch (browser){
            case CHROME:
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
            case FIREFOX:
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
        }
        return driver;
    }

    public static WebDriver createNewDriver(BrowserType browser, MutableCapabilities options){
        WebDriver driver = null;
        switch (browser){
            case CHROME:
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments(Arrays.asList("--start-maximized",
                        "--allow-file-access-from-files",
                        "--allow-running-insecure-content",
                        "--disable-notifications",
                        "--disable-infobars",
                        "--disable-file-cookies",
                        "--disable-web-security",
                        "--disable-extensions",
                        "--disable-feature=VizDisplayCompositor"));
                options.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
                driver = new ChromeDriver();
                break;
            case FIREFOX:
                WebDriverManager.firefoxdriver().setup();
                options.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.IGNORE);
                options.setCapability(CapabilityType.HAS_NATIVE_EVENTS, true);
                driver = new FirefoxDriver();
                break;
        }
        return driver;
    }
}
