package com.dachser.app.ws.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dachser.app.ws.io.entity.User;

@Repository
public interface IUserRepository extends CrudRepository<User, Long> {
	/* 
	 * spring data jpa provides with convenient use query language, 
	 * the technique of the method name: 
	 * 		findBy<faield name>(<faieldType> value)
	*/ 
	
	User findByEmail(String value); 
	
	User findByUserId(String value);
	

}
