package com.dachser.app.ws.security;

import com.dachser.app.ws.SpringApplicationContext;

public class SecurityConstants {

	public static final long EXPIRATION_TIME = 864000000;
	public static final String TOCKEN_PREFIX = "Bearer ";
	public static final String HEADER_STRING = "Authorization";
	public static final String SING_UP_RUL = "/user";
	
	public static String getTokenSecret() {
		// because this class not component or bean, we can't access AppProperties Component, 
		// for that use SpringApplicationContext to create a Bean manually, for that we need create this 
		// component: 'AppProperties' in MobileAppWsApplication and annotated as @Bean
		
		// create AppProperties Bean
		AppProperties AppProperties = (AppProperties) SpringApplicationContext.getBean("AppProperties");
		return AppProperties.getTokenSecret();
	}
	
}
