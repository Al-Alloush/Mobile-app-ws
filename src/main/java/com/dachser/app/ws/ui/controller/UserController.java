package com.dachser.app.ws.ui.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dachser.app.ws.service.impl.UserServiceImpl;
import com.dachser.app.ws.shared.dto.UserDto;
import com.dachser.app.ws.ui.model.request.UserDetailsRequestModel;
import com.dachser.app.ws.ui.model.response.UserResp;



@RestController // to receive HTTP request
@RequestMapping("user") // http://localholst:8080/users
public class UserController {
	
	@Autowired
	UserServiceImpl userServiceImpl; 
	
	
	@GetMapping()
	public String getUsers() {
		
		return "get users methode";
	}
	
	@GetMapping("/{userId}")
	public UserResp getUsers(@PathVariable String userId) {
		
		UserDto userDto = userServiceImpl.findByUserId(userId);
		
		UserResp userResp = new UserResp();
		
		BeanUtils.copyProperties(userDto, userResp);
		
		return userResp;

	}
	
	@PostMapping()
	public UserResp createUser(@RequestBody UserDetailsRequestModel userDetails ) {
		
		UserDto userDto = new UserDto();
		
		// mapping data from source userDetails to userDto
		BeanUtils.copyProperties(userDetails, userDto);
		
		UserDto createUser = userServiceImpl.createUser(userDto);
		
		
		UserResp returnValue = new UserResp();
		BeanUtils.copyProperties(createUser, returnValue);
		
		return returnValue;
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
