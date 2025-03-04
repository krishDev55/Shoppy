package com.ShoppyCart.entity;

import java.time.LocalDate;
import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
	public class Rating {

	    @Id                   
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private long id;

	    private double value;  // e.g., a number between 1 and 5
	 
	    // Optionally include review/comment fields, user info, etc.
	    private String comment;
	    // Actual Time Set the Comment
	    private  String date;

	    @ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name = "product_id") 
	    private Products product;
	    
	    @ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name = "User_Id")
	    private User user;

	    // Constructors, getters, setters
	    public Rating() {
	    	
	    }

	    public Rating(double value, String comment, Products product) {
	        this.value = value;
	        this.comment = comment;
	        this.product = product;
	    }
	    
	    

		public String getDate() {
			return date;
		}

		public void setDate(String date) {
			this.date = date;
		}

		public User getUser() {
			return user;
		}

		public void setUser(User user) {
			this.user = user;
		}

		public Long getId() {
			return id;
		}

		public double getValue() {
			return value;
		}

		public String getComment() {
			return comment;
		}

		public Products getProduct() {
			return product;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public void setValue(double value) {
			this.value = value;
		}

		public void setComment(String comment) {
			this.comment = comment;
		}

		public void setProduct(Products product) {
			this.product = product;
		}

		@Override
		public String toString() {
			return "Rating [id=" + id + ", value=" + value + ", comment=" + comment + ", product=" + product + "]";
		}

	   
	    
		
	}

