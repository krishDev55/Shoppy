package com.ShoppyCart.Kafka.vo;

import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class UserSend {

	private int id ;
	private String email;
	private String password;
	private String mobileNo;
	private Date date;
	public int getId() {
		return id;
	}
	public String getEmail() {
		return email;
	}
	public String getPassword() {
		return password;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public Date getDate() {
		return date;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	
	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", password=" + password + ", mobileNo=" + mobileNo + ", date="
				+ date + "]";
	}
	
	
	
	
	
}
