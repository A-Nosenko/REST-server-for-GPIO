package remote.to.gpio.models.user;

/**
 * @author Anatolii Nosenko
 * @version 1.0 4/17/2018.
 */
public class User {
    private String login;
    private String password;

    public User() {
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
