package com.ShoppyCart.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ShoppyCart.Repository.BankAcountDao;
import com.ShoppyCart.Repository.BankTransactionDao;
import com.ShoppyCart.Repository.UserDao;
import com.ShoppyCart.SecurityService.JWTService;
import com.ShoppyCart.entity.BankAccount;
import com.ShoppyCart.entity.BankTransaction;
import com.ShoppyCart.entity.User;
import com.ShoppyCart.vo.LoginCreadintial;

@Service
public class UserService {
		@Autowired
		UserDao userDao;
		
		@Autowired BankAcountDao bankAccountDao;
		@Autowired BankTransactionDao bankTnxDao;
		
		@Autowired
		AuthenticationManager authManager;
		
		@Autowired
		JWTService jwtService;
		
		private BCryptPasswordEncoder encoder= new BCryptPasswordEncoder(12);
		
	
		
		
		public User getUserById(int id) {
			return userDao.getUserById(id);
		}
		
		public User getUserByIdAndPassword(int id, String password) {
			return userDao.getUserByIdAndPassword(id, password);
		}
		
		@CachePut(value = "User",key = "#user.id")
		public User saveUser(User user) {
			user.setPassword(encoder.encode(user.getPassword()));
			return userDao.saveUser(user);
		}
		
		@Cacheable
		public List<User> getAllUser(){
			return userDao.getAllUser();
		}

		public User getUserByEmailAndPassword(String email, String password) {
			System.out.println("password is :"+encoder.encode(password));
			return userDao.getUserByEmailAndPassword(email,encoder.encode(password));
		}
		
		@Cacheable(value = "User", key = "#email")
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

		
		
	@CachePut(value = "user",key = "#user.id")
		public User updateUser(User user) {
			System.out.println(" user is "+user);
			return userDao.updateUser(user);
			
		}
	
		public String getEmailById(int id) {
			return userDao.getEmailById(id);
		}
	
	
//-------------------------------------------------------------------------------------
	// bankAccount Section
	
	public String saveBankAccount(BankAccount bankAccount) {	
		 bankAccountDao.saveBankAccount(bankAccount);
		 return "";
	}
	
	
	public BankAccount getBankAccountByAccountNu(long accNumber) {
		return bankAccountDao.getBankAccountById(accNumber);
	}
	
//	--------------------------------------------------------------------------------
	// bankTransaction flow 

	public void saveBankTnx(BankTransaction bankTnx) {
		try {
			BankAccount bAcc = getBankAccountByAccountNu(bankTnx.getAccountNo());
			if(bAcc== null) {
				System.out.println("objecct is null");
				bankTnxDao.saveBankTnx(bankTnx);
			}
			else if (bAcc.getBalance()>= bankTnx.getAmount()) {
				try {
					bankTnxDao.saveBankTnx(bankTnx);
					bAcc.setBalance(bAcc.getBalance()-bankTnx.getAmount());
					bankAccountDao.updateBankAccount(bAcc);
				}
				catch (Exception e) {
					
				}
			}
			else {
				System.out.println("I think Not SufficentBalence");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}	
	 }
	
	private void saveBankTnxOnline(BankTransaction bankTnx) {
		
		}
	}
