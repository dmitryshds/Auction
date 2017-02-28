package biz.bagira.auction.configuration;

import biz.bagira.auction.util.ImageUtil;
import biz.bagira.auction.util.MailUtil;
import biz.bagira.auction.util.RoleToUserProfileConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import java.util.Locale;

/**
 * Created by Dmitriy on 16.02.2017.
 */

/**
 * @Configuration is a class-level annotation indicating that an object is a source of bean definitions
 * @EnableWebMvc to enable MVC Java config add the annotation
 * @ComponentScan you need to add to autodetect classes and register the corresponding beans
 * To customize the default configuration in Java you simply implement the WebMvcConfigurer interface
 * or more likely extend the class WebMvcConfigurerAdapter and override the methods you need
 */

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "biz.bagira.auction")
@PropertySource(value = "classpath:application.properties")
public class ApplicationConfig extends WebMvcConfigurerAdapter {

    @Autowired
    RoleToUserProfileConverter roleToUserProfileConverter;

    @Autowired
    private Environment environment;

    /**
     * Configure ViewResolvers to deliver preferred views.
     */

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");
        registry.viewResolver(viewResolver);
    }

    /**
     * Configure ResourceHandlers to serve static resources like CSS/ Javascript etc...
     */

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
    }

    /**
     * Configure Converter to be used.
     * In our example, we need a converter to convert string values[Roles] to UserProfiles in newUser.jsp
     */
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(roleToUserProfileConverter);
    }

    /**
     * Configure MessageSource to lookup any validation/error message in internationalized property files
     */
//    @Bean
//    public MessageSource messageSource() {
//        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
//        messageSource.setBasename("messages");
//        return messageSource;
//    }


    /**
     * ReloadableResourceBundleMessageSource allows for reading files from any Spring resource location
     * and supports hot reloading of bundle property files
     */

    @Bean(name = "messageSource")
    public ReloadableResourceBundleMessageSource reloadableResourceBundleMessageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasenames("classpath:/locales/messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

    /**
     * SessionLocaleResolver allows you to retrieve Locale and TimeZone from the session that
     * might be associated with the user’s request
     */
    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver resolver = new org.springframework.web.servlet.i18n.SessionLocaleResolver();
        resolver.setDefaultLocale(new Locale("en"));
        return resolver;
    }

    /**
     * Enable changing of locales by adding the LocaleChangeInterceptor to the handler mappings
     */

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor()).addPathPatterns("/**");
    }

    /**
     * LocaleChangeInterceptor detect a parameter in the request and change the locale
     */
    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
        localeChangeInterceptor.setParamName("language");
        return localeChangeInterceptor;
    }

    /**
     * Built-in multipart support handles file uploads in web applications
     */
    @Bean
    public MultipartResolver multipartResolver() {
        return new StandardServletMultipartResolver();
    }

    @Bean
    public ImageUtil imageUtil(){
        return new ImageUtil(environment.getRequiredProperty("root.folder"));
    }

    @Bean
    public MailUtil mailUtil(){
        return new MailUtil(environment.getProperty("mail.smtp.auth"),
                            environment.getProperty("mail.from"),
                            environment.getProperty("mail.smtp.starttls.enable"),
                            environment.getProperty("mail.smtp.host"),
                            environment.getProperty("mail.smtp.port"),
                            environment.getProperty("mail.smtp.ssl.trust"),
                            environment.getProperty("mail.password"),
                            environment.getProperty("app.host"));
    }
}