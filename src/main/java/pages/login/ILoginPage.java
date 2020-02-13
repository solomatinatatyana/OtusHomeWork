package pages.login;

public interface ILoginPage {
    void doLogin(String url, String login, String password);
    void doLogin(String login, String password);
}
