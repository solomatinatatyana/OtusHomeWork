package tests.HomeWork5Tests;

import config.BaseWebDrivingTest;
import config.LoginConstants;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.PersonalDataPage;
import pages.ProfilePage;
import pages.login.ILoginPage;
import pages.login.LoginPageOtus;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.TextInput;

public class HomeWork5Test extends BaseWebDrivingTest {

    private ILoginPage loginPage;
    private ProfilePage profilePage;
    private PersonalDataPage dataPage;

    @BeforeClass(alwaysRun = true)
    public void init(){
        //инициализация страниц
        loginPage = new LoginPageOtus(webApp.getDriver());
        profilePage = new ProfilePage(webApp.getDriver());
        dataPage = new PersonalDataPage(webApp.getDriver());
    }

    @Test()
    public void checkProfile(){
        loginPage.doLogin(testData.getURL(), LoginConstants.AUTH_OTUS.getLogin(), LoginConstants.AUTH_OTUS.getPassword());
        goToProfile();
        setContactInfo();
        //driver.get("");
        //loginPage.doLogin(LoginConstants.AUTH_OTUS.getLogin(), LoginConstants.AUTH_OTUS.getPassword());
        //goToProfile();
        //проверка раздела о себе
    }

    private void setContactInfo(){
        dataPage.addContactButton.click();
        selectCommunicationMethod(dataPage.watsAppButton,0);
        setInfo(dataPage.communicationTextInput0,"text1");

        //selectCommunicationMethod(dataPage.watsAppButton,1);
        //setInfo(dataPage.communicationTextInput0,"text2");
    }

    private void goToProfile(){
        driver.get("https://otus.ru/learning/");
        profilePage.goToBiography();
    }

    private void setInfo(TextInput textInput, String text){
        textInput.clear();
        textInput.sendKeys(text);
    }

    private void selectCommunicationMethod(Button button, int position){
        int size = driver.findElements(dataPage.communicationMethod).size();

        //dataPage.communicationMethod.get(position).click();
        button.click();
    }
}
