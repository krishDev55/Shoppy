package com.ShoppyCart.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ShoppyCart.Repository.UserDao;
import com.ShoppyCart.SecurityService.JWTService;
import com.ShoppyCart.entity.User;
import com.ShoppyCart.vo.LoginCreadintial;

@Service
public class UserService {
		@Autowired
		UserDao userDao;
		
		@Autowired
		AuthenticationManager authManager;
		
		@Autowired
		JWTService jwtService;
		
		private BCryptPasswordEncoder encoder= new BCryptPasswordEncoder(12);
		
	
		
		
		public User getUserById(int id) {
			return userDao.getUserById(id);
		}
		
		public User getUserByIdAndPassword(int id, String password) {
//			
			return userDao.getUserByIdAndPassword(id, password);
		}
		
		
		public User saveUser(User user) {
			user.setPassword(encoder.encode(user.getPassword()));
			return userDao.saveUser(user);
		}
		
		
		public List<User> getAllUser(){
			return userDao.getAllUser();
		}

		public User getUserByEmailAndPassword(String email, String password) {
			System.out.println("password is :"+encoder.encode(password));
			return userDao.getUserByEmailAndPassword(email,encoder.encode(password));
		}
		
		public User getUserByEmail(String email) {
			 User user = userDao.getUserByEmail(email);
			 user.setPassword("********");
			return user;
		}
		
		

		public String varify(LoginCreadintial user ) {
			Authentication authenticate = 
							authManager.authenticate(
										new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
			if(authenticate.isAuthenticated()) {
				return jwtService.generateToken(user.getEmail()) ;
			}
			
			return "User not found....!!!";
		}

		public User updateUser(User user) {
			System.out.println(" user is "+user);
			
			return userDao.updateUser(user);
			
		}

		public String getEmailById(int id) {
		
			return userDao.getEmailById(id);
		}
	}
