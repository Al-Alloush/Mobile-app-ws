package com.dachser.app.ws.shared.dto;

import java.security.SecureRandom;
import java.sql.Timestamp;
import java.util.Random;
import java.util.UUID;

import org.springframework.stereotype.Component;

// this class must be a component because we going to Autowired it into Some Services

@Component
public class MyUtils {
	
	private final Random RANDOM = new SecureRandom();
	private final String ALPHABET = "0123456789QWERTZUIOPASDFGHJKLYXCVBNMqwertzuiopasdfghjklyxcvbnm";
	
	Timestamp timpDateTime;
	
	// Java UUID (Universally Unique Identifier) 
	public String gennerateUUID() {
		
		UUID uuid = UUID.randomUUID();
		return uuid.toString();
	}
	
	// Generate unique String
	public String gennerateUniqueString(int length) {
		
		timpDateTime = Timestamp.valueOf("2018-09-01 09:01:15");  
		
		StringBuilder returnValue = new StringBuilder(length);
		
		// to add timestamp just one time;
		Boolean addedTimestamp = false;
		
		for (int i = 0; i < length; i++) {
			
			// add the timestamp in middle the unique string
			if(Math.round((length / 2)) == i && addedTimestamp == false) {
				// append timestamp dateTime to be more secure, that it a unique string
				long timestamp = timpDateTime.getTime();
				returnValue.append(timestamp);
				addedTimestamp = true;
			}
			
			returnValue.append(ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length())));
		}
		
		// *****  return the string length to the right length
		
		// 0: for left side, 1 for right side
		int deleteSide =  new Random().nextInt(2); 
		int deleteNumbers = returnValue.length() - length;

		if (deleteSide == 1) {
			// delete the deleteNumber from right side
			returnValue.setLength(returnValue.length() - deleteNumbers);

		}else {
			// delete form left side
			returnValue.delete(0, deleteNumbers);
		}
		
		System.out.println("the Security String after deleted :" + new String(returnValue));

		return new String(returnValue);
	}
	



}
