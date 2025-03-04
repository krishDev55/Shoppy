package com.ShoppyCart.entity;

import java.util.List;

import jakarta.persistence.*;







@Entity
public class Categery {
	@Id
			private int catId;
			private String catName;
			
			@OneToMany(targetEntity = Products.class ,
						mappedBy = "categary",
						orphanRemoval = true  ,
						cascade = CascadeType.ALL, 
						fetch = FetchType.LAZY)
			private List<Products> products;
			
			
			
			
			
			public List<Products> getProducts() {
				return products;
			}
			public void setProducts(List<Products> products) {
				this.products = products;
			}
			public int getCatId() {
				return catId;
			}
			public String getCatName() {
				return catName;
			}
			public void setCatId(int catId) {
				this.catId = catId;
			}
			public void setCatName(String catName) {
				this.catName = catName;
			}
			
			@Override
			public String toString() {
				return "Categery [catId=" + catId + ", catName=" + catName + ", products=" + " " + "]";
			}
			public Categery() {
				super();
				// TODO Auto-generated constructor stub
			}
			public Categery(int catId, String catName, List<Products> products) {
				super();
				this.catId = catId;
				this.catName = catName;
				this.products = products;
			}
			
			public Categery(int catId, String catName) {
				super();
				this.catId = catId;
				this.catName = catName;
			}
			
			
			
			
}
