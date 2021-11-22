package com.dachser.app.ws.io.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "users")
public class User implements Serializable {

	private static final long serialVersionUID = 3937474547466345805L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id", nullable = false)
	private long id;
	
	@Column(name="userId", nullable = false, length = 255)
	private String userId;
	
	@Column(name="firstName",  length = 50)
	private String firstName;
	
	@Column(name="lastName", length = 50)
	private String lastName;
	
	@Column(name="email", nullable = false, length = 150)
	private String email;
	
	@Column(name="encryptedPassword", nullable = false)
	private String encryptedPassword;
	
	@Column(name="emailVerificationToken", nullable = false)
	private String emailVerificationToken;
	
	// set default value is false
	@Column(name="emailVerificationStatus", nullable = false)
	private Boolean emailVerificationStatus = false;

	
	
	
	// getters & setters 
	
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEncryptedPassword() {
		return encryptedPassword;
	}

	public void setEncryptedPassword(String encryptedPassword) {
		this.encryptedPassword = encryptedPassword;
	}

	public String getEmailVerificationToken() {
		return emailVerificationToken;
	}

	public void setEmailVerificationToken(String emailVerificationToken) {
		this.emailVerificationToken = emailVerificationToken;
	}

	public boolean isEmailVerificationStatus() {
		return emailVerificationStatus;
	}

	public void setEmailVerificationStatus(boolean emailVerificationStatus) {
		this.emailVerificationStatus = emailVerificationStatus;
	}
	
	

	
	
	
	
	

}
