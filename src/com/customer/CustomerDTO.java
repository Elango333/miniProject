package com.customer;

public class CustomerDTO {
private String name;
private String emailId;
private String mobileNumber;
private String password;


public void setName(String name) {
	this.name = name;
}

public void setEmailId(String emailId) {
	this.emailId = emailId;
}

public void setMobileNumber(String mobileNumber) {
	this.mobileNumber = mobileNumber;
}

public void setPassword(String password) {
	this.password = password;
}

public String getName() {
	return name;
}

public String getEmailId(){
	return emailId;
}

public String getMobileNumber() {
	return mobileNumber;
}

public String getPassword() {
	return password;
}

}
