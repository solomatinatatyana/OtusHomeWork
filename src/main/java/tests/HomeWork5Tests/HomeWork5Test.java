package tests.HomeWork5Tests;

import config.BaseWebDrivingTest;
import config.LoginConstants;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.ProfilePage;
import pages.login.ILoginPage;
import pages.login.LoginPageOtus;

public class HomeWork5Test extends BaseWebDrivingTest {

    private ILoginPage loginPage;
    private ProfilePage profilePage;

    @BeforeClass(alwaysRun = true)
    public void init(){
        //инициализация страниц
        loginPage = new LoginPageOtus(webApp.getDriver());
        profilePage = new ProfilePage(webApp.getDriver());
    }

    @Test()
    public void checkProfile(){
        loginPage.doLogin(testData.getURL(), LoginConstants.AUTH_OTUS.getLogin(), LoginConstants.AUTH_OTUS.getPassword());
        /*goToProfile();
        setInfo();
        driver.get("");
        loginPage.doLogin(LoginConstants.AUTH_OTUS.getLogin(), LoginConstants.AUTH_OTUS.getPassword());
        goToProfile();*/
        //проверка раздела о себе
    };


    private void goToProfile(){

    }

    private void setInfo(){

    }
}
