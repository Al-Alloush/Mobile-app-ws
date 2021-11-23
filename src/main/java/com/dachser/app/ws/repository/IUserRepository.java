package com.dachser.app.ws.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dachser.app.ws.io.entity.User;

@Repository
public interface IUserRepository extends CrudRepository<User, Long> {
	
	User findUserByEmail(String email); 

}
