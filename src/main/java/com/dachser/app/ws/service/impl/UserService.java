package com.dachser.app.ws.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dachser.app.ws.io.entity.User;
import com.dachser.app.ws.repository.IUserRepository;
import com.dachser.app.ws.service.IUserService;
import com.dachser.app.ws.shared.dto.UserDto;
import com.dachser.app.ws.shared.dto.MyUtils;

@Service
public class UserService implements IUserService {
	
	@Autowired
	IUserRepository userRepository;
	
	@Autowired
	MyUtils myUtils;

	@Override
	public UserDto createUser(UserDto userDto) {
		User user = new User();
		
		BeanUtils.copyProperties(userDto, user);
		
		String uuid = myUtils.gennerateUUID();
		
		user.setEncryptedPassword("!QA1qa");
		user.setUserId(uuid);
		user.setEmailVerificationToken("this is temp email VerificaitonToken");
		
		//check if email and userId exist before.
		User checkUser = userRepository.findByEmail(user.getEmail());
		if (checkUser != null) {
			throw new RuntimeException("this email exist before");
		}
		
		//save user in database
		User storedUserDetails = userRepository.save(user);
		UserDto returnValue = new UserDto();
		
		BeanUtils.copyProperties(storedUserDetails, returnValue);
		
		
		return returnValue;
	}

}
