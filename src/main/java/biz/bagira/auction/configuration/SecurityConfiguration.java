package biz.bagira.auction.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Created by Dmitriy on 17.02.2017.
 */

/**
 * WebSecurityConfigurerAdapter  provides a default configuration Spring Security
 *
 * @EnableWebSecurity enable Spring Security configuration
 */


@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    @Qualifier("customUserDetailsService")
    UserDetailsService userDetailsService;

    /**
     * It allows configuring web based security for specific http requests.
     * By default it will be applied to all requests, but can be restricted
     * using requestMatcher(RequestMatcher) or other similar methods.
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/index", "/login", "/register")
                .access("permitAll")
                .and()
                .formLogin().
                loginPage("/login").
                defaultSuccessUrl("/index",true)
                .failureUrl("/login?error=true")
                .loginProcessingUrl("/login")
                .usernameParameter("login")
                .passwordParameter("password")
                .and().csrf();

    }

    /**
     * You should never store passwords in plain text
     * BCryptPasswordEncoder is best to be on the safe side
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * The most common approach to verifying an authentication request is to load the corresponding
     * UserDetails and check the loaded password against the one that has been entered by the user. This
     * is the approach used by the DaoAuthenticationProvider.
     */

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    /**
     * SecurityBuilder used to create an AuthenticationManager. Allows for easily building in memory authentication,
     * LDAP authentication, JDBC based authentication, adding UserDetailsService,
     * and adding AuthenticationProvider's.
     */

    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
        auth.authenticationProvider(authenticationProvider());
    }

}
