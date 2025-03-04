package com.ShoppyCart.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Vendor {
	@Id
	private String  venderId;
	private String fullname; 
	private String email; 
	private String  password;
	private Long mobile ;
	private int pincode ;
	
	@OneToMany(targetEntity = Products.class ,mappedBy = "vendor"  ,cascade = CascadeType.ALL)
	List<Products> productList;
	
	
	
	public String getVenderId() {
		return venderId;
	}
	public List<Products> getProductList() {
		return productList;
	}
	public void setVenderId(String venderId) { 
		this.venderId = venderId;
	}
	public void setProductList(List<Products> productList) {
		this.productList = productList;
	}
	public String getVender_id() {
		return venderId;
	}
	public String getFullname() {
		return fullname;
	}
	public String getEmail() {
		return email;
	}
	public String getPassword() {
		return password;
	}
	public Long getMobile() {
		return mobile;
	}
	public int getPincode() {
		return pincode;
	}
	public void setVender_id(String venderId) {
		this.venderId = venderId;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setMobile(Long mobile) {
		this.mobile = mobile;
	}
	public void setPincode(int pincode) {
		this.pincode = pincode;
	}
	@Override
	public String toString() {
		return "Vendor [venderId=" + venderId + ", fullname=" + fullname + ", email=" + email + ", password="
				+ password + ", mobile=" + mobile + ", pincode=" + pincode + "]";
	}
	public Vendor(String venderId, String fullname, String email, String password, Long mobile, int pincode) {
		super();
		this.venderId = venderId;
		this.fullname = fullname;
		this.email = email;
		this.password = password;
		this.mobile = mobile;
		this.pincode = pincode;
	}
	public Vendor() {
		super();
		// TODO Auto-generated constructor stub
	}
	
//	 private String address ;
	
	
	 
	 
}
