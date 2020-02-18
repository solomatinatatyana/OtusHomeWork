package tests.HomeWork5Tests;

import config.BaseWebDrivingTest;
import config.LoginConstants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.login.ILoginPage;
import pages.login.LoginPageOtus;
import pages.otusPages.ProfilePage;
import pages.otusPages.ProfilePages.PersonalDataPage;
import ru.yandex.qatools.htmlelements.element.TextInput;

public class HomeWork5Test extends BaseWebDrivingTest {
    private Logger log = LogManager.getLogger(HomeWork5Test.class);
    private SoftAssert softAssert = new SoftAssert();

    private ILoginPage loginPage;
    private ProfilePage profilePage;
    private PersonalDataPage dataPage;

    private void init(){
        //инициализация страниц
        loginPage = new LoginPageOtus(webApp.getDriver());
        profilePage = new ProfilePage(webApp.getDriver());
        dataPage = new PersonalDataPage(webApp.getDriver());
    }

    //=======================Тестовые данные=================
    private static final String  contactText1 = "79035808468";
    private static final String  contactText2 = "text2";

    @Test()
    public void checkProfile(){
        this.init();
        loginPage.doLogin(testData.getURL(), LoginConstants.AUTH_OTUS.getLogin(), LoginConstants.AUTH_OTUS.getPassword());
        goToProfile();
        setContactInfo();
        openNewBrowser();
        loginPage.doLogin(testData.getURL(), LoginConstants.AUTH_OTUS.getLogin(), LoginConstants.AUTH_OTUS.getPassword());
        goToProfile();
        String currentContactText1 = dataPage.communicationTextInput0.getEnteredText();
        //String currentContactText2 = dataPage.communicationTextInput1.getEnteredText();
        softAssert.assertEquals(currentContactText1, contactText1, "Неверный текст у контакта 1");
        //softAssert.assertEquals(currentContactText2, contactText2, "Неверный текст у контакта 2");
        softAssert.assertAll();
    }


    @AfterClass(alwaysRun = true)
    public void resetTestData(){
        clearContactsOnProfile();
    }
//-------------------------------------------------METHODS--------------------------------------------------------------

    private void setContactInfo(){
        WebDriverWait wait = new WebDriverWait(driver, 50L);
        wait.until(ExpectedConditions.presenceOfElementLocated(dataPage.addContactButtonLocator));
        dataPage.addContactButton.click();
        dataPage.selectCommunicationMethod(dataPage.whatsAppButton);
        setInfo(dataPage.communicationTextInput0,contactText1);
        //dataPage.selectCommunicationMethod(dataPage.whatsAppButton);
        //setInfo(dataPage.communicationTextInput1,"text2");
        saveInfo();
    }

    private void goToProfile(){
        profilePage.goToMyProfile();
        profilePage.goToBiography();
    }

    private String setInfo(TextInput textInput, String text){
        textInput.clear();
        textInput.sendKeys(text);
        return text;
    }

    private void saveInfo(){
        dataPage.saveButton.click();
    }

    private void openNewBrowser(){
        tearDown();
        setUp();
        this.init();
    }

    private void clearContactsOnProfile(){
        dataPage.contactBlock0.findElement(dataPage.removeContactButton).click();
        /*WebDriverWait wait = new WebDriverWait(driver, 50L);
        wait.until(ExpectedConditions.presenceOfElementLocated(dataPage.contBlock1));
        dataPage.contactBlock1.findElement(dataPage.removeContactButton).click();*/
        saveInfo();
    }

}
