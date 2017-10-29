package com.smartsoft.core;

import com.smartsoft.user.DetailsService;
import com.smartsoft.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity //let spring security knows
@EnableGlobalMethodSecurity(prePostEnabled = true) // complete annotation and secure our method
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    DetailsService userDetailService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailService).passwordEncoder(User.PASSWORD_ENCODER);//make sure it knows how to decrypt the passw
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().anyRequest().authenticated().and().httpBasic().and().csrf().disable();//#csrf cross-site request forgery stuff
    }
}
