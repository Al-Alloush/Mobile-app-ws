package com.dachser.app.ws.exciptionHandler;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/exception")
public class ExceptionTestController {
	
	
	@GetMapping()
	public String throwException() throws Exception {
		//return "this a throw Exception method";
		throw new Exception("this an Exception!");
	}
	
	
	@GetMapping("/notfound/{id}")
	public String notFound(@PathVariable int id) {
		
		//return "this function to test entity not found!";
		throw new ExceptionNotFound("test entity ("+ id +") Not Found Exception method");
	}

}
