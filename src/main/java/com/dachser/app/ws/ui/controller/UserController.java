package com.dachser.app.ws.ui.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dachser.app.ws.ui.model.request.UserDetailsRequestModel;

@RestController // to receive HTTP request
@RequestMapping("users") // http://localholst:8080/users
public class UserController {
	
	
	@GetMapping()
	public String getUsers() {
		
		return "get users methode";
	}
	
	@GetMapping("/{id}")
	public String getUsers(@PathVariable int id) {
		
		return "get user: "+id+", methode";
	}
	
	@PostMapping()
	public String createUser(@RequestBody UserDetailsRequestModel userDetails ) {
		
		return "Create user methode for user: " + userDetails.getFirstName();
	}
	
	
	@PutMapping()
	public String updateUser() {
		
		return "Update user methode";
	}
	
	
	@DeleteMapping()
	public String deleteUser() {
	
		return "Delete user methode";
	}

}
