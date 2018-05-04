package remote.to.gpio.configs;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;
import org.springframework.web.filter.CharacterEncodingFilter;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;

/**
 * @author Anatolii Nosenko
 * @version 1.0 4/17/2018.
 */
public class SecurityWebInitializer extends AbstractSecurityWebApplicationInitializer {
    @Override
    protected void beforeSpringSecurityFilterChain(ServletContext servletContext) {
        FilterRegistration.Dynamic encodingFilter = servletContext.addFilter("encodingFilter", new CharacterEncodingFilter());
        encodingFilter.setInitParameter("encoding", "UTF-8");
        encodingFilter.setInitParameter("forceEncoding", "true");
        encodingFilter.addMappingForUrlPatterns(null, false, "/*");
    }
}
