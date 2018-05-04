package remote.to.gpio.services.user;

/**
 * @author Anatolii Nosenko
 * @version 1.0 4/17/2018.
 */
public interface SecurityService {
    void updatePersonalData(String login, String newLogin, String password, String newPassword);
    void autoLogin(String login, String password);
}
