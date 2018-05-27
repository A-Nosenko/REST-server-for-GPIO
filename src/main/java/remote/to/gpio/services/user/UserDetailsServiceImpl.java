package remote.to.gpio.services.user;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import remote.to.gpio.tools.PropertyHandler;

import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import static remote.to.gpio.values.Constants.*;

/**
 * @author Anatolii Nosenko
 * @version 1.0 4/17/2018.
 */
public class UserDetailsServiceImpl implements UserDetailsService {

   @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {

        Properties properties = PropertyHandler.read(SECURITY);

        if (!login.equals(properties.getProperty("login"))) {
            return null;
        }

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        grantedAuthorities.add(new SimpleGrantedAuthority("ADMIN"));

        return new org.springframework.security.core.userdetails
                .User(login, properties.getProperty("password"), grantedAuthorities);
    }
}
