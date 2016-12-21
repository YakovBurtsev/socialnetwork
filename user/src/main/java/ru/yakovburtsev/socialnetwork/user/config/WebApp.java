package ru.yakovburtsev.socialnetwork.user.config;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

/**
 * The class is an initializer which in this case acts as replacement of any spring configuration
 * defined in web.xml
 */
public class WebApp implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
        rootContext.register(SpringRootConfig.class);
        rootContext.register(SpringWebConfig.class);
        rootContext.register(SpringDbConfig.class);
        rootContext.register(SpringSecurityConfig.class);
        rootContext.setServletContext(servletContext);

        ServletRegistration.Dynamic servlet = servletContext.addServlet("dispatcher", new DispatcherServlet(rootContext));

        servlet.setLoadOnStartup(1);
        servlet.addMapping("/");
    }
}
