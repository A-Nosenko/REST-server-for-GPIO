package remote.to.gpio.services.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import remote.to.gpio.tools.PasswordParser;
import remote.to.gpio.tools.PropertyHandler;

import java.util.Properties;

import static remote.to.gpio.values.Constants.*;

/**
 * @author Anatolii Nosenko
 * @version 1.0 4/17/2018.
 */
public class SecurityServiceImpl implements SecurityService {

    private static final Logger logger = LoggerFactory.getLogger(SecurityServiceImpl.class);

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    public void updatePersonalData(String login, String newLogin, String password, String newPassword) {

        Properties properties = PropertyHandler.read(SECURITY);

        if(login.equals(properties.getProperty("login"))
                && PasswordParser.checkPassword(password, properties.getProperty("password"))) {
            properties.setProperty("login", newLogin);
            properties.setProperty("password", PasswordParser.hidePassword(newPassword));

            PropertyHandler.write(properties, SECURITY);
        }
    }

    @Override
    public void autoLogin(String login, String password) {

        UserDetails userDetails = userDetailsService.loadUserByUsername(login);

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(
                        login, userDetails.getPassword(), userDetails.getAuthorities());
        authenticationManager.authenticate(authenticationToken);

        if (authenticationToken.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);

            logger.debug(String.format("Welcome, %s !!!", login));
        }
    }
}
