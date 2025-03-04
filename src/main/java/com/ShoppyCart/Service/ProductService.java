package com.ShoppyCart.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ShoppyCart.Repository.ProductDao;
import com.ShoppyCart.commonUse.Common;
import com.ShoppyCart.entity.Categery;
import com.ShoppyCart.entity.Products;

@Service
public class ProductService {
		
	@Autowired
	ProductDao productDao;
	
	public Products saveProduct(Products product) {
		Categery categary = product.getCategary();
		System.out.println("categery is : "+ categary);
		Products product1 = productDao.saveProduct(product);
		
		return product1;
		}
		
		public Products getProductById(int  id) {
			return productDao.getProductById(id);
			}
		
		public Categery saveCategery(Categery categary) {
			Categery categary1 = getCategeryById(categary.getCatId());
//			----
			List<Products> products=null;
			List<Products> list = categary.getProducts();
			 
			if(categary1 !=null) {
							products = categary1.getProducts();
							categary.setProducts(null);	
							list.get(0).setCategary(categary);
							products.add(list.get(0));
							categary1.setProducts(products);	
							
				
				return productDao.saveCategery(categary1);
			}
			
			else {
				return productDao.saveCategery(categary);
			}
//			-------------------
			
		
		}
		
		public Categery getCategeryById(int  id) {
			
			return productDao.getCategeryById(id);
			}

		public List<Products> getAllProducts() {
			// TODO Auto-generated method stub
			return productDao.getAllProducts();
		}
		public Set<Products> getAllRandomProducts() {
			List<Products> list = productDao.getAllProducts();
			Set<Products> set= new HashSet<>();
			
			for(int i=0 ; i<list.size(); i++) {
				int genrateId = Common.genrateId()%list.size();
				Products product = list.get(genrateId);
				set.add(product);
			}
			
			return set;
		}
		
		public List<Products> getProductByVendorId(String vendorId){	
			List<Products> products = productDao.getAllProductsByVendorId(vendorId);
			System.out.println("All product is : "+products);
			return products ;
		}

		public List<Products> searchProduct(String search) {
			// TODO Auto-generated method stub
			return null;
		}
		
}
