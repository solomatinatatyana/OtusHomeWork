package tests.HomeWork5Tests;

import config.BaseWebDrivingTest;
import config.LoginConstants;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.login.ILoginPage;
import pages.login.LoginPageOtus;
import pages.otusPages.*;
import pages.otusPages.ProfilePages.EducationPage;
import pages.otusPages.ProfilePages.PersonalDataPage;
import pages.otusPages.ProfilePages.ProjectsPage;
import pages.otusPages.ProfilePages.SkillsPage;
import ru.yandex.qatools.htmlelements.element.TextInput;

public class HomeWork5Test extends BaseWebDrivingTest {

    private ILoginPage loginPage;
    private ProfilePage profilePage;
    private PersonalDataPage dataPage;
    private SkillsPage skillsPage;
    private ProjectsPage projectsPage;
    private EducationPage educationPage;

    @BeforeClass(alwaysRun = true)
    public void before(){
        this.init();
        //добавить проверку на подготовку окружения для теста
    }

    private void init(){
        //инициализация страниц
        loginPage = new LoginPageOtus(webApp.getDriver());
        profilePage = new ProfilePage(webApp.getDriver());
        dataPage = new PersonalDataPage(webApp.getDriver());
        skillsPage = new SkillsPage(webApp.getDriver());
        projectsPage = new ProjectsPage(webApp.getDriver());
        educationPage = new EducationPage(webApp.getDriver());
    }

    //=======================Тестовые данные=================
    //private Contact contact1;
    //private Contact contact2;
    @Test()
    public void checkProfile(){
        //this.init();
        loginPage.doLogin(testData.getURL(), LoginConstants.AUTH_OTUS.getLogin(), LoginConstants.AUTH_OTUS.getPassword());
        goToProfile();
        setContactInfo();
        openNewBrowser();
        loginPage.doLogin(testData.getURL(), LoginConstants.AUTH_OTUS.getLogin(), LoginConstants.AUTH_OTUS.getPassword());
        goToProfile();
        String contactText1 = dataPage.communicationTextInput0.getEnteredText();
        Assert.assertEquals(contactText1, "text1", "Неверный текст у контакта 1");
        //проверка раздела о себе
    }


    @AfterClass(alwaysRun = true)
    public void resetTestData(){
        clearContactsOnProfile();
    }
//-------------------------------------------------METHODS--------------------------------------------------------------

    private void setContactInfo(){
        dataPage.addContactButton.click();
        dataPage.selectCommunicationMethod(dataPage.watsAppButton);
        setInfo(dataPage.communicationTextInput0,"text1");
        //setInfo(dataPage.communicationTextInput1,"text2");
        //dataPage.selectCommunicationMethod(dataPage.watsAppButton);
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
        skillsPage.saveButton.click();
        projectsPage.saveButton.click();
        educationPage.saveButton.click();
    }

    private void openNewBrowser(){
        tearDown();
        setUp();
        this.init();
    }

    private void clearContactsOnProfile(){
        //goToProfile();
        dataPage.removeContactButton.click();
        saveInfo();
    }

}
