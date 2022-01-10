package com.online.userservice.entity;

import javax.persistence.*;


@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userId;
	
	//@NotEmpty(message="Username can't be empty")
	private String userName;
	
	//@NotEmpty(message="emailId can't be empty")
	private String emailId;
	
	//@NotEmpty(message="phoneNumber can't be empty")
	private String phoneNumber;
	
	//@NotEmpty(message="Address can't be empty")
	private String address;
	
	private double ewallet;

	public double getEwallet() {
		return ewallet;
	}

	public void setEwallet(double ewallet) {
		this.ewallet = ewallet;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public User(Integer userId, String userName, String emailId, String phoneNumber, String address, double ewallet) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.emailId = emailId;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.ewallet = ewallet;
	}
	
	public User() {}
}
