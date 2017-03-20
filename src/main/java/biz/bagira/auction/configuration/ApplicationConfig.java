package biz.bagira.auction.configuration;

import biz.bagira.auction.service.SheduledTasks;
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
import org.springframework.http.MediaType;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Locale;
import java.util.Properties;

/**
 * Created by Dmitriy on 16.02.2017.
 */

/**
 * @Configuration is a class-level annotation indicating that an object is a source of bean definitions
 * @EnableWebMvc to enable MVC Java config add the annotation
 * @ComponentScan you need to add to autodetect classes and register the corresponding beans
 * To customize the default configuration in Java you simply implement the WebMvcConfigurer interface
 * or more likely extend the class WebMvcConfigurerAdapter and override the methods you need
 * @EnableAsync enables Spring's asynchronous method execution capability
 * @EnableScheduling enables Spring's scheduled task execution capability
 */

@Configuration
@EnableWebMvc
@EnableAsync
@EnableScheduling
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
        viewResolver.setContentType("text/html; charset=UTF-8");
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
    public java.util.Properties properties(){
        Properties props = new Properties();
        props.put("mail.smtp.auth",environment.getProperty("mail.smtp.auth"));
        props.put("mail.from",environment.getProperty("mail.from"));
        props.put("mail.smtp.starttls.enable",environment.getProperty("mail.smtp.starttls.enable"));
        props.put("mail.smtp.host",environment.getProperty("mail.smtp.host"));
        props.put("mail.smtp.port",environment.getProperty("mail.smtp.port"));
        props.put("mail.smtp.ssl.trust",environment.getProperty("mail.smtp.ssl.trust"));
        props.put("mail.password",environment.getProperty("mail.password"));
        props.put("app.host",environment.getProperty("app.host"));
        props.put("mail.mime.charset", environment.getProperty("mail.mime.charset"));
        return props;

    }

    @Bean
    public MailUtil mailUtil(){
        return new MailUtil(properties());
    }

    @Bean
    public SheduledTasks sheduller(){
        return new SheduledTasks();
    }

    @Bean
    public StringHttpMessageConverter stringHttpMessageConverter() {
        StringHttpMessageConverter converter = new StringHttpMessageConverter();
        converter.setSupportedMediaTypes(Arrays.asList(new MediaType("text", "plain", Charset.forName("UTF-8"))));
        return converter;
    }

}