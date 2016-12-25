package ru.yakovburtsev.socialnetwork.friends.config;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

/**
 * The class is an initializer which in this case acts as replacement of any spring configuration
 * defined in web.xml
 */
public class WebApp implements WebApplicationInitializer {

    private static final String DISPATCHER_SERVLET_NAME = "dispatcher";
    private static final String DISPATCHER_SERVLET_MAPPING = "/";

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
        rootContext.register(SpringRootConfig.class);
        rootContext.register(SpringWebConfig.class);
        rootContext.register(SpringDbConfig.class);

        ServletRegistration.Dynamic servlet = servletContext.addServlet(
                DISPATCHER_SERVLET_NAME, new DispatcherServlet(rootContext)
        );
        servlet.setLoadOnStartup(1);
        servlet.addMapping(DISPATCHER_SERVLET_MAPPING);

//        FilterRegistration.Dynamic securityFilter = servletContext.addFilter(
//                AbstractSecurityWebApplicationInitializer.DEFAULT_FILTER_NAME, DelegatingFilterProxy.class
//        );
//        securityFilter.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), false, "/*");

        FilterRegistration.Dynamic encodingFilter = servletContext.addFilter("encodingFilter", new CharacterEncodingFilter());
        encodingFilter.setInitParameter("encoding", "UTF-8");
        encodingFilter.setInitParameter("forceEncoding", "true");
        encodingFilter.addMappingForUrlPatterns(null, true, "/*");

        servletContext.addListener(new ContextLoaderListener(rootContext));
    }
}
