package com.ShoppyCart.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Cart {
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
	 int cart_id ;
	 int user_id ;
	int  prod_id ;
	 double prod_price ;
	int  prod_quantity ;
	String prod_image;
	 String prod_name;
	 
	public int getCart_id() {
		return cart_id;
	}
	public int getUser_id() {
		return user_id;
	}
	public int getProd_id() {
		return prod_id;
	}
	public double getProd_price() {
		return prod_price;
	}
	public int getProd_quantity() {
		return prod_quantity;
	}
	public String getProd_image() {
		return prod_image;
	}
	public String getProd_name() {
		return prod_name;
	}
	public void setCart_id(int cart_id) {
		this.cart_id = cart_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public void setProd_id(int prod_id) {
		this.prod_id = prod_id;
	}
	public void setProd_price(double prod_price) {
		this.prod_price = prod_price;
	}
	public void setProd_quantity(int prod_quantity) {
		this.prod_quantity = prod_quantity;
	}
	public void setProd_image(String prod_image) {
		this.prod_image = prod_image;
	}
	public void setProd_name(String prod_name) {
		this.prod_name = prod_name;
	}   
	 
	
	 
}
