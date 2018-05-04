package remote.to.gpio.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import remote.to.gpio.services.mode.ModeService;
import remote.to.gpio.services.mode.ModeServiceImpl;
import remote.to.gpio.services.relay.RelaysService;
import remote.to.gpio.services.relay.RelaysServiceImpl;

/**
 * @author Anatolii Nosenko
 * @version 1.0 4/21/2018.
 */
@Configuration
@ComponentScan("remote.to.gpio")
public class SupportConfig {
    @Bean
    ModeService modeService() {
        return new ModeServiceImpl();
    }

    @Bean
    RelaysService relaysService() {
        return new RelaysServiceImpl();
    }
}
