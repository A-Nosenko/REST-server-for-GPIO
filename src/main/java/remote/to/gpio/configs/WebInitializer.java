package remote.to.gpio.configs;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

/**
 * @author Anatolii Nosenko
 * @version 1.0 4/17/2018.
 */
public class WebInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
        ctx.register(remote.to.gpio.configs.AppConfig.class);
        ctx.setServletContext(servletContext);
        ServletRegistration.Dynamic servlet = servletContext
                .addServlet("dispatcher", new DispatcherServlet(ctx));
        servlet.addMapping("/");
        servlet.setLoadOnStartup(1);
    }
}
