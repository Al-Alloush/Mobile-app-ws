package com.dachser.app.ws.exciptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;



//the purpose of this class is to handle custom exception or existing exception, 
//and customize the way to throw the error to client
@ControllerAdvice
public class ApiExcepionHandler {
	
	@ExceptionHandler() 
	public ResponseEntity<Object> HandlerException(Exception e){
		
		HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
		
		ApiExcepion apiExcepion = new ApiExcepion();
		apiExcepion.setStatus(httpStatus.value());
		apiExcepion.setMessage(e.getMessage());
		apiExcepion.setTimeStamp(System.currentTimeMillis());
		
		return new ResponseEntity<Object>(apiExcepion, httpStatus);
		
	}
	
	
	// tell spring that this message is will be responsible of handling Exceptions, by use this annotation,
	// pass this class {value = {ApiRequestNotFoundException.class}} as value 
	@ExceptionHandler() 
	public ResponseEntity<Object> HandlerNotFoundException(ExceptionNotFound e){
		
		HttpStatus httpStatus = HttpStatus.NOT_FOUND;
		
		ApiExcepion apiExcepion = new ApiExcepion();
		apiExcepion.setStatus(httpStatus.value());
		apiExcepion.setMessage(e.getMessage());
		apiExcepion.setTimeStamp(System.currentTimeMillis());
		
		return new ResponseEntity<Object>(apiExcepion, httpStatus);
		
	}

}
