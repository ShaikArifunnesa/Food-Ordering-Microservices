package com.online.userservice.Dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


public class UserDTO {
	
	@NotBlank(message = "Username cannot be empty")
	@NotEmpty(message="Username can't be empty")
	private String userName;
	
	@NotBlank(message="emailId can't be empty")
	@NotEmpty(message="emailId can't be empty")
	private String emailId;
	
	@NotBlank(message="phoneNumber can't be empty")
	@NotEmpty(message="phoneNumber can't be empty")
	private String phoneNumber;
	
	@NotBlank(message="Address can't be empty")
	@NotEmpty(message="Address can't be empty")
	private String address;
		
	@NotEmpty @Min(value = 100, message="Amount can't be less than 100 ")
	private double ewallet;

	public double getEwallet() {
		return ewallet;
	}

	public void setEwallet(double ewallet) {
		this.ewallet = ewallet;
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

	public UserDTO(
			@NotBlank(message = "Username cannot be null") @NotEmpty(message = "Username can't be empty") String userName,
			@NotBlank(message = "emailId can't be empty") @NotEmpty(message = "emailId can't be empty") String emailId,
			@NotBlank(message = "phoneNumber can't be empty") @NotEmpty(message = "phoneNumber can't be empty") String phoneNumber,
			@NotBlank(message = "Address can't be empty") @NotEmpty(message = "Address can't be empty") String address,
			@Min(100) @NotNull(message = "Wallet amount cannot be less than 100") double ewallet) {
		super();
		this.userName = userName;
		this.emailId = emailId;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.ewallet = ewallet;
	}
	public UserDTO() {}
	
}
