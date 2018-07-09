package com.judge.dredd.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.judge.dredd.repository.AppUserRepository;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurity extends WebSecurityConfigurerAdapter {
    private UserDetailsService userDetailsService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
	private AppUserRepository userRepository;
	
    public WebSecurity(UserDetailsService userDetailsService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userDetailsService = userDetailsService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
		        .antMatchers("/swagger-ui.html#/**").hasRole("ADMIN")
		        .antMatchers("/swagger-ui.html#/**").authenticated()
		        .antMatchers("/v2/api-docs/**").hasRole("ADMIN")
		        .antMatchers("/v2/api-docs/**").authenticated()
		    	.antMatchers("/stomp/**").permitAll()
		        .antMatchers("/public/**").permitAll()
        		.antMatchers("/login").permitAll()
                .antMatchers("/dredd/api/**").authenticated().and()
                .addFilter(new JWTAuthenticationFilter(authenticationManager(),userRepository))
                .addFilter(new JWTAuthorizationFilter(authenticationManager()))
                .httpBasic();
    }

    @Override                                                                                                                                                                                                                                                                                                                                   
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
        auth.inMemoryAuthentication().withUser("acnjudgeadmin").password(bCryptPasswordEncoder.encode("acnjudgeadmin")).roles("ADMIN");
    }

    
}