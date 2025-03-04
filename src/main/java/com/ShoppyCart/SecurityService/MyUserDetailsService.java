package com.ShoppyCart.SecurityService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ShoppyCart.Repository.UserDao;
import com.ShoppyCart.Repository.VendorDao;
import com.ShoppyCart.entity.User;
import com.ShoppyCart.entity.Vendor;

@Service
public class MyUserDetailsService implements UserDetailsService {
	
	@Autowired
	UserDao userDao;
	@Autowired
	VendorDao vendorDao;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
			User user=userDao.getUserByEmail(username);
			Vendor vendor = vendorDao.getVendorByEmail(username);
			if(username.equals(vendor.getEmail())) {
				return new UserLoginPrinciple(vendor);
			}
			else {
				return new UserLoginPrinciple(user);}
	}

}
