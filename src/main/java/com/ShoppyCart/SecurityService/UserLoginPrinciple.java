package com.ShoppyCart.SecurityService;

import java.util.Collection;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.ShoppyCart.entity.User;
import com.ShoppyCart.entity.Vendor;

public class UserLoginPrinciple implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private User user;
	private Vendor vendor;
	
	public UserLoginPrinciple(User user) {
		this.user=user;
	}
	public UserLoginPrinciple(Vendor vendor) {
		this.vendor=vendor;
	}

	@Override
	public String getPassword() {
		if(vendor!=null) {
			return vendor.getPassword();
		}
		else {
			return user.getPassword();
		}
	}
	
	@Override
	public String getUsername() {
		if(null!=vendor) {
			return vendor.getEmail();
		}
		else {
			return user.getEmail();
		}
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
	
		return Collections.singleton( new SimpleGrantedAuthority("USER"));
	}


}
