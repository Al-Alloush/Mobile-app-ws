package com.dachser.app.ws.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.mapping.List;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.dachser.app.ws.SpringApplicationContext;
import com.dachser.app.ws.service.UserService;
import com.dachser.app.ws.shared.dto.UserDto;
import com.dachser.app.ws.ui.model.request.UserLoginRequestModel;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/*
 can't be able to access the UserService implementation, because the AuthenticationFilter not a Bean @Autowrid into WebSecurity,
 because create a new instance of it manually rather than @Autowrid or inject it in WebSecurity, 
 AuthenticationFilter can't @Autowrid or inject other Beans
 
 
 */


public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	
	private final AuthenticationManager authenticationManager;

	public AuthenticationFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}
	
	
	/*
	 when our Web services receive a request to authenticate user, 
	 spring framework will be used to authenticate login user with the Username and password that were provided by the login Request.
	 And this method will be triggered.*/

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		
		try {
			// mapping the json payload that include in request body and create now object from UserLoginRequestModel class with json data
			UserLoginRequestModel userLogin = new ObjectMapper().readValue(request.getInputStream(), UserLoginRequestModel.class);
			
			// will be used to authenticate user and it will use email and password
			// It will look up user in our database and for that we have implemented a method 
			// loadUserByUsername() in UserServiceImpl class.
			return authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(
							userLogin.getEmail(), 
							userLogin.getPassword(), 
							new ArrayList<>() 
							)
					);
			// It will find the user, It will authenticate the user using username and password
			// when the username and password match the record, this will triggered new method:  successfulAuthentication
			
			
		} catch (IOException e) {
			
			throw new RuntimeException(e);
		}
		
	}

	
	// if username or password are not correct, then this method successfulAuthentication will not be called.
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		
		String userName = ((User) authResult.getPrincipal()).getUsername();
		
		
		// AuthenticationFilter is not @Autowrid ... we can't call the Bean from it.
		// for that by SpringApplicationContext get the UserService Bean
		UserService userService =  (UserService) SpringApplicationContext.getBean("userServiceImpl");
		// from UserService get inside UserRepository and get the user By email
		UserDto userDto = userService.findByEmail(userName);

		String token = Jwts.builder()
							.setSubject(userName)
							.setId(userDto.getUserId())
							.setExpiration(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME))
							.signWith(SignatureAlgorithm.HS512, SecurityConstants.getTokenSecret())
							.compact();
		
		response.addHeader(SecurityConstants.HEADER_STRING, SecurityConstants.TOCKEN_PREFIX + token);
		response.addHeader("UserId", userDto.getUserId());
	}
	
	
	
	

}
