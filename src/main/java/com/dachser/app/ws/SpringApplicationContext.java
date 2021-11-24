package com.dachser.app.ws;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class SpringApplicationContext implements ApplicationContextAware {

	private static ApplicationContext CONTEXT;
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		CONTEXT = applicationContext;
		
	}
	
	// return the bean that has been created by spring framework
	// for example to get the UserServiceImpl Bean inside the AuthenticationFilter class
	public static Object getBean(String beanName) {
		return CONTEXT.getBean(beanName);
	}

}
