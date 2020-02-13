package config;

public enum LoginConstants {
    AUTH_OTUS("tokio9507@mail.ru", "w75bSGUM");

    LoginConstants(String login, String password) {
        this.login = login;
        this.password = password;
    }
    private String login;
    private String password;

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}
