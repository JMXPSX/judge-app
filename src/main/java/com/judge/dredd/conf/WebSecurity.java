package com.judge.dredd.conf;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {
    private UserDetailsService userDetailsService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public WebSecurity(UserDetailsService userDetailsService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userDetailsService = userDetailsService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
        		.antMatchers("/login").permitAll()
                .antMatchers("/dredd/api/**").permitAll().and()
                .addFilter(new JWTAuthenticationFilter(authenticationManager()))
                .addFilter(new JWTAuthorizationFilter(authenticationManager()));
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

//    @Override
//    public void configure(org.springframework.security.config.annotation.web.builders.WebSecurity web) {
//        web.ignoring().antMatchers(HttpMethod.POST, SIGN_UP_URL)
//                .antMatchers(HttpMethod.POST, LANGUAGE_URL)
//                .antMatchers(HttpMethod.POST, DROPDOWN_URL)
//                .antMatchers(HttpMethod.POST, ADDRESS_URL)
//                .antMatchers(HttpMethod.POST, VALIDATE_USER)
//                .antMatchers(HttpMethod.POST, FORGOT_PASSWORD)
//        		.antMatchers(HttpMethod.POST, FARM_MANAGEMENT)
//                .antMatchers(HttpMethod.POST, FORGOT_PASSWORD)
//                .antMatchers(HttpMethod.POST, ADDRESS_URL)
//                .antMatchers(HttpMethod.POST, CALENDAR_URL);
//
//    }

}