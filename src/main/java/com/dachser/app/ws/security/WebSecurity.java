package com.dachser.app.ws.security;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.dachser.app.ws.service.UserService;

@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {
	
	private final UserService userDetailsService;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	private final String[] publicPostEendpoints = {
			"/users", "/login", "/user/login"
	};
	// change the sequence because when request users/** then request /users will return the last request 403 forbidden error
	private final String[] publicGetEndpoints = {
			 "/users/**", "/users", "/login"
	};
	
	
	public WebSecurity(UserService userDetailsService, BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.userDetailsService = userDetailsService;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}


	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
	}

	
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.csrf().disable()
			.authorizeRequests()
				.antMatchers(HttpMethod.POST, publicPostEendpoints).permitAll()
				.antMatchers(HttpMethod.GET, publicGetEndpoints).permitAll()
			.anyRequest().authenticated()
			.and()
			.addFilter(getAuthenticationFilter ());
	}
	
	// to change the login default path and disabled the old one (http://localhost:8080/login)
	public AuthenticationFilter getAuthenticationFilter () throws Exception{
		
		final AuthenticationFilter filter = new AuthenticationFilter(authenticationManager());
		filter.setFilterProcessesUrl("/users/login");
		
		return filter;
		
	}
	
	

}
