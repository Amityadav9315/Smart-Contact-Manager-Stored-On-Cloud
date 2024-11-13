package com.hcm.scm20.config;

import java.security.AuthProvider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.hcm.scm20.services.impl.SecurityCustomUserDetailService;

@Configuration
public class SecurityConfig {

    // Create an in-memory user for authentication
   // @Bean
   // public UserDetailsService userDetailsService() {
       // UserDetails user1 = User
              //  .withDefaultPasswordEncoder()
               // .Username("admin123")
               // .password("{noop}admin123")  // {noop} is for plain-text password encoding
               // .roles("ADMIN","USER")               // Assigning role "ADMIN" to the user
               // .build();

              //  UserDetails user2 = User
              //  .withUsername("user123")
              //  .password("password")  // {noop} is for plain-text password encoding
               // .roles("ADMIN","USER")               // Assigning role "ADMIN" to the user
              //  .build();


               // @SuppressWarnings("deprecation")
              //  var inMemoryUserDetailsManager= new InMemoryUserDetailsManager(user1,user2);

       // return  inMemoryUserDetailsManager;
       //}
       @Autowired
        private SecurityCustomUserDetailService userDetailService;

        //configuration of Authentication provider from spring security
       @Bean
       public DaoAuthenticationProvider authenticatioProvider(){
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
         httpSecurity.formLogin(Customizer.withDefaults());
        return httpSecurity.build();

       }

        @Bean
       public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
       }

    }

