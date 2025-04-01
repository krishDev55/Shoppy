package com.ShoppyCart.entity;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class UpdUser {
	@Id
	private int id;
	
	private String fristName;
	private String middleName;
	private String lastName;
	private  Date dob;
	private  String currentCity;
	
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFristName() {
		return fristName;
	}
	public String getMiddleName() {
		return middleName;
	}
	public String getLastName() {
		return lastName;
	}
	public Date getDob() {
		return dob;
	}
	public String getCurrentCity() {
		return currentCity;
	}
	public void setFristName(String fristName) {
		this.fristName = fristName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public void setCurrentCity(String currentCity) {
		this.currentCity = currentCity;
	}
	@Override
	public String toString() {
		return "UpdUser [id=" + id + ", fristName=" + fristName + ", middleName=" + middleName + ", lastName="
				+ lastName + ", dob=" + dob + ", currentCity=" + currentCity + "]";
	}
	
	
	
	
	
	
}
