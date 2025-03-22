package com.ShoppyCart.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity
public class Products {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id ;
	private String name;
	private double price;
	private String details;
	private String image;
	private int stock;

	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	 @JoinColumn(referencedColumnName = "catId")
	private Categery categary;
	
	@ManyToOne(cascade = CascadeType.ALL)
//	 @JoinColumn(name = "venderId",referencedColumnName = "venderId")
	private Vendor vendor;
	
	
	 @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	    private List<Rating> ratings;
	 
	 
	 
	public List<Rating> getRatings() {
		return ratings;
	}

	public void setRatings(List<Rating> ratings) {
		this.ratings = ratings;
	}

	public Vendor getVendor() {
		return vendor;
	}

	public void setVendor(Vendor vendor) {
		this.vendor = vendor;
	}

	
	public int getId() {
		return id;
	}

	
	public String getName() {
		return name;
	}

	public double getPrice() {
		return price;
	}

	public String getDetails() {
		return details;
	}

	public String getImage() {
		return image;
	}

	public int getStock() {
		return stock;
	}

	public Categery getCategary() {
		return categary;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public void setCategary(Categery categary) {
		this.categary = categary;
	}

	public Products() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	@Override
	public String toString() {
		return "Products [id=" + id + ", name=" + name + ", price=" + price + ", details=" + details + ", image="
				+ image + ", stock=" + stock + ", categary=" + categary + ", vendor=" + vendor + ", ratings=" + ratings
				+ "]";
	}

	public Products(int id, String name, double price, String details, String image, int stock, Categery categary) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.details = details;
		this.image = image;
		this.stock = stock;
		this.categary = categary;
	}
	
	
	
	
		

}
