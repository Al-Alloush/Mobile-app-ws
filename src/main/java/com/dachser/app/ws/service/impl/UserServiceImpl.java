package com.dachser.app.ws.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

}
