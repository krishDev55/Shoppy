package com.ShoppyCart.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class BankAccount {

	@Id
	  int bankId;    
	 String  accountType ;
	 double balance ;
	private  String branchName   ;      
	private  String ifscCode ; 
	 private String status ; 
	 @OneToOne
	private User user;
	 
	 
	public int getBankId() {
		return bankId;
	}
	public String getAccountType() {
		return accountType;
	}
	public double getBalance() {
		return balance;
	}
	public String getBranchName() {
		return branchName;
	}
	public String getIfscCode() {
		return ifscCode;
	}
	public String getStatus() {
		return status;
	}
	public User getUser() {
		return user;
	}
	public void setBankId(int bankId) {
		this.bankId = bankId;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	@Override
	public String toString() {
		return "BankAccount [bankId=" + bankId + ", accountType=" + accountType + ", balance=" + balance
				+ ", branchName=" + branchName + ", ifscCode=" + ifscCode + ", status=" + status + ", user=" + user
				+ "]";
	}
	 
	
	
	 
}
