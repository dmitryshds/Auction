package biz.bagira.auction.configuration;

import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by Dmitriy on 16.02.2017.
 */

/**
 * @Configuration is a class-level annotation indicating that an object is a source of bean definitions
 * @EnableTransactionManagement Enables Spring's annotation-driven transaction management capability
 * @ComponentScan you need to add to autodetect classes and register the corresponding beans
 * @PropertySource Annotation providing a convenient and declarative mechanism for adding a PropertySource to Spring's Environment
 */


@Configuration
@EnableTransactionManagement
@ComponentScan(value = "biz.bagira.auction.configuration")
@PropertySource(value = "classpath:application.properties")
public class HibernateConfiguration {

    @Autowired
    private Environment environment;

    /**
     * BasicDataSource datasource which gives you  a “real” connection pool outside of a JEE container
     */
    @Bean
    public DataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(environment.getRequiredProperty("jdbc.driverClassName"));
        dataSource.setUrl(environment.getRequiredProperty("jdbc.url"));
        dataSource.setUsername(environment.getRequiredProperty("jdbc.username"));
        dataSource.setPassword(environment.getRequiredProperty("jdbc.password"));
        return dataSource;
    }


    /**
     * LocalSessionFactoryBean: FactoryBean that creates a Hibernate SessionFactory
     */
    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan("biz.bagira.auction.entities");
        sessionFactory.setHibernateProperties(hibernateProperties());
        return sessionFactory;
    }

    /**
     * hibernate.dialect: Makes Hibernate generate the appropriate SQL for the chosen database.
     * hibernate.show_sql: Enable the logging of all the generated SQL statements to the console
     * hibernate.format_sql: Format the generated SQL statement to make it more readable, but takes up more screen space
     */
    private Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", environment.getRequiredProperty("hibernate.dialect"));
        properties.put("hibernate.show_sql", environment.getRequiredProperty("hibernate.show_sql"));
        properties.put("hibernate.format_sql", environment.getRequiredProperty("hibernate.format_sql"));
        return properties;
    }

    /**
     *
     * HibernateTransactionManager: PlatformTransactionManager implementation for a single Hibernate SessionFactory.
     * Binds a Hibernate Session from the specified factory to the thread,
     * potentially allowing for one thread-bound Session per factory.
     */

    @Bean
    @Autowired
    public HibernateTransactionManager transactionManager(SessionFactory s) {
        HibernateTransactionManager txManager = new HibernateTransactionManager();
        txManager.setSessionFactory(s);
        return txManager;
    }
}
