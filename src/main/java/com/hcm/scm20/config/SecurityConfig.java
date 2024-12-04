package com.hcm.scm20.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import com.hcm.scm20.services.impl.SecurityCustomUserDetailService;


@Configuration
public class SecurityConfig {
    @Autowired
    private SecurityCustomUserDetailService userDetailService;

    @Bean
    public AuthenticationProvider authenticatioProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable() // Disable CSRF for development/testing
                .authorizeHttpRequests(authorize -> {
                    authorize.requestMatchers("/login", "/register", "/services", "/home").permitAll(); // Public URLs
                    authorize.requestMatchers("/user/**").authenticated(); // Authenticated URLs
                    authorize.anyRequest().permitAll();
                })
                .formLogin(formLogin -> {
                    formLogin.loginPage("/login");
                    formLogin.loginProcessingUrl("/authenticate");
                    formLogin.successForwardUrl("/user/dashboard");
                    formLogin.failureForwardUrl("/login?error=true");
                    formLogin.usernameParameter("email");
                    formLogin.passwordParameter("password");
                })
                .exceptionHandling()
                .accessDeniedPage("/403"); // Custom 403 page

                
                httpSecurity.logout(logoutForm->{
                  logoutForm.logoutUrl("/logout");
                  logoutForm.logoutSuccessUrl("/login?logout=true");
                });

        return httpSecurity.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
