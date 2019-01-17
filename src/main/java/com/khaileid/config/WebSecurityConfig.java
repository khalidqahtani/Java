package com.khaileid.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;

import javax.sql.DataSource;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    private DataSource dataSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery("select username, password, enable"
                        + " from Users where username=?")
                .authoritiesByUsernameQuery("select username, role "
                        + "from Users where username=?")
                .passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // Authenticate users with HTTP basic authentication
        http.csrf().disable().httpBasic().and()
                .authorizeRequests()
                .antMatchers("/api/users/registration/**").permitAll()
                .antMatchers("/api/role/**").permitAll()
                .anyRequest().authenticated()
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

//                .authorizeRequests().antMatchers("/api/adminaccess/users").hasRole("ADMIN")
                //Add roles from all
//                .antMatchers("/api/adminaccess/users").permitAll()
//                .antMatchers("/roles").permitAll()
//                .antMatchers("/adminaccess/users/adduser/**").permitAll()
//                .antMatchers("/adminaccess/users/updateusers/**").permitAll()
//                .antMatchers("/api/event/available/all").permitAll()
//                .antMatchers("/api/event/ByCity/**").permitAll()
//                .antMatchers("/api/event/ByDate/**").permitAll()
//                .antMatchers("/api/event/ByDateAndCity/**/**").permitAll()
//                .antMatchers("/api/useraccess/eventgender/**").permitAll()
//                .antMatchers("/api/allcomment").permitAll()
//                .antMatchers("/api/adminaccess/rate").permitAll()
//                .antMatchers("/comment/findallcomment/foreventid/**").permitAll()
//                .antMatchers("/comment/findallcomment/foruserid/**").permitAll()
                //View for organizer and admin
//                .antMatchers("/organizeraccess/users/firstname/**").hasRole("ADMIN")
//                .antMatchers("/organizeraccess/users/id/**").hasAnyRole("ADMIN","ORGANIZER")
//                .antMatchers("/adminaccess/users/lastname/**").hasRole("ADMIN")
                //.antMatchers("/view/").hasAnyRole("ADMIN", "USER", "ORGANIZER")
                //.antMatchers("/welcome").hasRole("USER")
//                .and().httpBasic(); // Authenticate users with HTTP basic authentication

    }

    }