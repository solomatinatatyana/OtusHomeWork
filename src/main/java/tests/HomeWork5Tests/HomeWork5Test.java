package tests.HomeWork5Tests;

import config.BaseWebDrivingTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
        loginPage.doLogin(testData.getURL(), auth.getLogin(), auth.getPassword());
        goToProfile();
        setContactInfo();
        openNewBrowser();
        loginPage.doLogin(testData.getURL(), auth.getLogin(), auth.getPassword());
        goToProfile();
        log.info("Проверяем добавленные данные");
        String currentContactText1 = dataPage.communicationTextInputFirst.getEnteredText();
        String currentContactText2 = dataPage.communicationTextInputSecond.getEnteredText();
        softAssert.assertEquals(currentContactText1, contactText1, "Неверный текст у контакта 1");
        softAssert.assertEquals(currentContactText2, contactText2, "Неверный текст у контакта 2");
        softAssert.assertAll();
    }


    @AfterClass(alwaysRun = true)
    public void resetTestData(){
        clearContactsOnProfile();
    }
//-------------------------------------------------METHODS--------------------------------------------------------------

    private void setContactInfo(){
        dataPage.addContact();
        log.info("Добавляем первый контакт");
        dataPage.selectCommunicationMethod(dataPage.whatsAppButtonFirst);
        setInfo(dataPage.communicationTextInputFirst,contactText1);
        log.info("Добавляем второй контакт");
        dataPage.selectCommunicationMethod(dataPage.whatsAppButtonSecond);
        log.info("Контакты добавлены");
        setInfo(dataPage.communicationTextInputSecond,contactText2);
        saveInfo();
    }

    private void goToProfile(){
        log.info("Переходим на страничку [О себе]");
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
        log.info("Открываем браузер заново");
        setUp();
        this.init();
    }

    private void clearContactsOnProfile(){
        dataPage.deleteContact(dataPage.contactBlockFirst);
        dataPage.deleteContact(dataPage.contactBlockSecond);
        saveInfo();
    }

}
