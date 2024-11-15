package com.hcm.scm20.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import com.hcm.scm20.services.impl.SecurityCustomUserDetailService;



@Configuration
public class SecurityConfig {



  

       @Autowired
        private SecurityCustomUserDetailService userDetailService;

        //configuration of Authentication provider from spring security
       @Bean
       public AuthenticationProvider authenticatioProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        //user detail service ka detail
        daoAuthenticationProvider.setUserDetailsService(userDetailService);
        //password encode ka object
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider; 
       }
       
       @Bean
       public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        
         //configuration
         //urls confoguration kiya hai ki kaun se public and kaun se private
         httpSecurity.authorizeHttpRequests(authorize->{
            //authorize.requestMatchers("/home","/register","/services").permitAll(); 
            authorize.requestMatchers("/user/**").authenticated();
            authorize.anyRequest().permitAll();
         });
           //form default login
           //agar hame kuch bhee change karna hogo to yaha ayenge form login se related
         httpSecurity.formLogin(formLogin ->{

          formLogin.loginPage("/login");
          formLogin.loginProcessingUrl("/authenticate");
          formLogin.successForwardUrl("/user/dashboard");
          formLogin.failureForwardUrl("/login?error=true");
          formLogin.usernameParameter("email");
          formLogin.passwordParameter("password");
          
          
         });

         httpSecurity.logout(logoutForm->{
          logoutForm.logoutUrl("/logout");
          logoutForm.logoutSuccessUrl("/login?logout=true");
         });
        return httpSecurity.build();

       }

        @Bean
       public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
       }

    }

