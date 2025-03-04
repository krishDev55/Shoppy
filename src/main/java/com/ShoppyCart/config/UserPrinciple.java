package com.ShoppyCart.config;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;


public class UserPrinciple  implements UserDetails {

	String username;
	String password="abcd";
	
	
	public UserPrinciple( String username) {
		System.out.println("UserName is : "+username);
		if(username.equals("admin")) {
			
			this.username=username;
		}
	}
	
	

	public UserPrinciple() {
		super();
		// TODO Auto-generated constructor stub
	}



	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.password;
	}
	
	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return username;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}


}
