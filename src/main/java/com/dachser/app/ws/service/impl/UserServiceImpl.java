package com.dachser.app.ws.service.impl;

import java.util.ArrayList;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.dachser.app.ws.io.entity.UserEntity;
import com.dachser.app.ws.repository.UserRepository;
import com.dachser.app.ws.service.UserService;
import com.dachser.app.ws.shared.dto.UserDto;
import com.dachser.app.ws.shared.dto.MyUtils;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	MyUtils myUtils;
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public UserDto createUser(UserDto userDto) {
		UserEntity userEntity = new UserEntity();
		
		BeanUtils.copyProperties(userDto, userEntity);
		
		String encodedPassword = bCryptPasswordEncoder.encode(userDto.getPassword());
		String uuid = myUtils.gennerateUUID();
		
		userEntity.setEncryptedPassword(encodedPassword);
		userEntity.setUserId(uuid);
		userEntity.setEmailVerificationToken("this is temp email VerificaitonToken");
		
		//check if email and userId exist before.
		UserEntity checkUser = userRepository.findByEmail(userEntity.getEmail());
		if (checkUser != null) {
			throw new RuntimeException("this email exist before");
		}
		
		//save user in database
		UserEntity storedUserDetails = userRepository.save(userEntity);
		UserDto returnValue = new UserDto();
		
		BeanUtils.copyProperties(storedUserDetails, returnValue);
		
		
		return returnValue;
	}

	// this method used by spring framework, it will help spring framework to load user details when it needs.
	// this method will be used in the process of user login, in default spring's login page: http://localhost/:8080/login.
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		UserEntity userEntity = userRepository.findByEmail(email);
		if(userEntity == null ) throw new UsernameNotFoundException(" this user : " +email + "not exist");
		
		return new User(userEntity.getEmail(), userEntity.getEncryptedPassword(), new ArrayList<>() );
	}

}
