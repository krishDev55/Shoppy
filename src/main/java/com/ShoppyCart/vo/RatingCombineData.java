package com.ShoppyCart.vo;

import java.util.List;

import com.ShoppyCart.entity.Products;

public class RatingCombineData {

	private	String  email;
	private String comment;
	private String date;
	private double value;
	private int user_id;
	private Products product;
	
	
	
	
	public Products getProduct() {
		return product;
	}



	public void setProduct(Products product) {
		this.product = product;
	}



	public String getEmail() {
		return email;
	}



	public String getComment() {
		return comment;
	}



	public String getDate() {
		return date;
	}



	public double getValue() {
		return value;
	}



	public int getUser_id() {
		return user_id;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public void setComment(String comment) {
		this.comment = comment;
	}



	public void setDate(String date) {
		this.date = date;
	}



	public void setValue(double value) {
		this.value = value;
	}



	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}



	public RatingCombineData() {
		
	}

}
