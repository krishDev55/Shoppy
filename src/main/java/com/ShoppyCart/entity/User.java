package com.ShoppyCart.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
		private int id ;
		private String email;
		private String password;
		private String mobileNo;
		
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
		@Override
		public String toString() {
			return "User [id=" + id + ", email=" + email + ", password=" + password + ", mobileNo=" + mobileNo + "]";
		}
		public User(int id, String email, String password, String mobileNo) {
			super();
			this.id = id;
			this.email = email;
			this.password = password;
			this.mobileNo = mobileNo;
		}
		public User() {
			super();
			// TODO Auto-generated constructor stub
		}
		
		
		
		
		
		
			
}
