package com.ShoppyCart.vo;

public class LoginCreadintial {

	
	private String email;
	private String password;
	
	
	public String getEmail() {
		return email;
	}
	public String getPassword() {
		return password;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public LoginCreadintial(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}
	public LoginCreadintial() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "LoginCreadintial [email=" + email + ", password=" + password + "]";
	}
	
	
}
