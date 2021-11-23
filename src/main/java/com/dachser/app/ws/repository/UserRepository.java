package com.dachser.app.ws.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dachser.app.ws.io.entity.UserEntity;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {
	/* 
	 * spring data jpa provides with convenient use query language, 
	 * the technique of the method name: 
	 * 		findBy<faield name>(<faieldType> value)
	*/ 
	
	UserEntity findByEmail(String value); 
	
	UserEntity findByUserId(String value);
	

}
