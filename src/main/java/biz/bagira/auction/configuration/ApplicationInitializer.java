package biz.bagira.auction.configuration;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * Created by Dmitriy on 16.02.2017.
 */

/**
 * An abstract base class implementation of WebApplicationInitializer named
 * AbstractDispatcherServletInitializer makes it even easier to register the
 * DispatcherServlet and specify the servlet mapping and the location
 */


public class ApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[]{ApplicationConfig.class, SecurityConfiguration.class};
    }

    protected Class<?>[] getServletConfigClasses() {
        return null;
    }


    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}
