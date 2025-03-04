package com.ShoppyCart.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name = "Orders")
public class Order {

	@Id
	
	private String orderId  ;
	private String productName ;
	private String productImg ;
	private int quentity ;
	 private double price ;
	 private String time    ;
	 private String	 status ; 
	 private int prodId ;
	 private int userId ;
	public String getOrderId() {
		return orderId;
	}
	public String getProductName() {
		return productName;
	}
	public String getProductImg() {
		return productImg;
	}
	public int getQuentity() {
		return quentity;
	}
	public double getPrice() {
		return price;
	}
	public String getTime() {
		return time;
	}
	public String getStatus() {
		return status;
	}
	public int getProdId() {
		return prodId;
	}
	public int getUserId() {
		return userId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public void setProductImg(String productImg) {
		this.productImg = productImg;
	}
	public void setQuentity(int quentity) {
		this.quentity = quentity;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public void setProdId(int prodId) {
		this.prodId = prodId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Order [orderId=");
		builder.append(orderId);
		builder.append(", productName=");
		builder.append(productName);
		builder.append(", productImg=");
		builder.append(productImg);
		builder.append(", quentity=");
		builder.append(quentity);
		builder.append(", price=");
		builder.append(price);
		builder.append(", time=");
		builder.append(time);
		builder.append(", status=");
		builder.append(status);
		builder.append(", prodId=");
		builder.append(prodId);
		builder.append(", userId=");
		builder.append(userId);
		builder.append("]");
		return builder.toString();
	}
	public Order(String orderId, String productName, String productImg, int quentity, double price, String time,
			String status, int prodId, int userId) {
		super();
		this.orderId = orderId;
		this.productName = productName;
		this.productImg = productImg;
		this.quentity = quentity;
		this.price = price;
		this.time = time;
		this.status = status;
		this.prodId = prodId;
		this.userId = userId;
	}
	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}
	 
	 
	
	 
	
	
	 
	 
		
}
