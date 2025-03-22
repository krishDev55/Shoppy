package com.ShoppyCart.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.ShoppyCart.Repository.ProductDao;
import com.ShoppyCart.Repository.RatingDao;
import com.ShoppyCart.commonUse.Common;
import com.ShoppyCart.entity.Categery;
import com.ShoppyCart.entity.Products;
import com.ShoppyCart.entity.Rating;

@Service
public class ProductService {
		
	@Autowired	ProductDao productDao;
	
	@Autowired RatingDao ratingDao;
	
	
	
	public Products saveProduct(Products product) {
		Categery categary = product.getCategary();
		System.out.println("categery is : "+ categary);
		Products product1 = productDao.saveProduct(product);
		
		return product1;
		}
		
	
	
	
		public Products getProductById(int  id) {
		Products productById = productDao.getProductById(id);
		List<Rating> allRatingByProductId = ratingDao.getllRatingByProductId(id);
		productById.setRatings(allRatingByProductId);
			return productById;
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
		}
		
	
	
	
	
	
		public Categery getCategeryById(int  id) {		
			return productDao.getCategeryById(id);
		}

	
	
	
	
		
		public List<Products> getAllProducts() {
			List<Products> p_List = productDao.getAllProducts();
			List<Products> origalList= new ArrayList<>();
			for (Products p : p_List) {
				p.setRatings(ratingDao.getllRatingByProductId(p.getId()));
				origalList.add(p);
			}
			
			return origalList;
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
		
		
		
		
		@Cacheable(value = "Products", key = "#vendorId")
		public List<Products> getProductByVendorId(String vendorId){	
			List<Products> products = productDao.getAllProductsByVendorId(vendorId);
			System.out.println("All product is : "+products);
			return products ;
		}

		
		
		
		public List<Products> searchProduct(String search) {
			return null;
		}




		public Products updateProduct(Products product) {
			
			return productDao.updateProduct(product);
		}
		
}
