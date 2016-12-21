package ru.yakovburtsev.socialnetwork.user.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.RememberMeAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import ru.yakovburtsev.socialnetwork.user.util.PasswordUtil;

/**
 * The class is security configuration for the app.
 */

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(PasswordUtil.PASSWORD_ENCODER);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/resources/**")
                .antMatchers("/webjars/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authenticationProvider(getRememberMeAuthenticationProvider())
                .formLogin()
                    .loginPage("/login")
                    .permitAll()
                    .usernameParameter("email")
                    .passwordParameter("password")
                    .loginProcessingUrl("/spring_security_check")
                    .defaultSuccessUrl("/profile")
                .and()
                    .logout()
                    .logoutSuccessUrl("/login")
                .and()
                .csrf().disable()
                .authorizeRequests()
                    .antMatchers("/register").permitAll()
                    .antMatchers("/**").authenticated();
    }

    @Bean
    public RememberMeAuthenticationProvider getRememberMeAuthenticationProvider() {
        return new RememberMeAuthenticationProvider("rememberMe");
    }
}
