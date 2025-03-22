package com.ShoppyCart.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import com.ShoppyCart.entity.Categery;
import com.ShoppyCart.entity.Products;
import com.ShoppyCart.entity.Rating;

@Repository
public class ProductDao {
		
//		@Autowired
//		SessionFactory sessionFactory;
	
	
		
		Session session ;
		
		public ProductDao(SessionFactory sessionFactory){
			session=sessionFactory.openSession();
		} 
		
		
		@CachePut(value="Product", key = "#product.getId()")
		public Products saveProduct(Products product) {
			
			String query= "insert into products(catId,id,name,details,image,venderId,price,stock) "
									+ "values(?,?,?,?,?,?,?,?)";
					session.doWork(connection->{
						PreparedStatement stmt = connection.prepareStatement(query);
						stmt.setInt(1, product.getCategary().getCatId());
						stmt.setInt(2, product.getId());
						stmt.setString(3, product.getName());
						stmt.setString(4, product.getDetails());
						stmt.setString(5, product.getImage());
						stmt.setString(6, product.getVendor().getVender_id());
						stmt.setDouble(7, product.getPrice());
						stmt.setInt(8, product.getStock());
						
						boolean execute = stmt.execute();
						System.out.println("Query Update Sucsessfully or Not : "+execute);
					});
		return product;
		}
		
		
		@Cacheable(value="Product" , key = "#id")
		public Products getProductById(int  id) {
			System.out.println("inside text ....");
			Products products = new Products();	
			String query="select * from products where id =?";
			session.doWork(connection->{
				PreparedStatement stmt = connection.prepareStatement(query);
				stmt.setInt(1, id);
				ResultSet result = stmt.executeQuery();
				while(result.next()) {
					products.setId(result.getInt("id"));
					products.setDetails(result.getString("details"));
					products.setImage(result.getString("image"));
					products.setName(result.getString("name"));
					products.setPrice(result.getDouble("price"));
					products.setStock(result.getInt("stock"));
				Categery cat=new Categery();
					cat.setCatId(result.getInt("catId"));
					products.setCategary(cat);
					
					products.setRatings(null);
				}
			});		
			return products;
			}
		
		
		
		
		@CachePut(value="Categery", key="#categary.catId()")
		public Categery saveCategery(Categery categary) {
//			Categery categeryById = getCategeryById(categary.getCatId());
			Transaction tnx = session.getTransaction();
			session.persist(categary);
			tnx.commit();
			return categary;
//			List<Products> products=null;
//			
//			List<Products> list = categary.getProducts();
//			 
//			if(categeryById !=null) {
//							products = categeryById.getProducts();
//							categary.setProducts(null);	
//							list.get(0).setCategary(categary);
//							products.add(list.get(0));
//							categeryById.setProducts(products);	
//				session.persist(categeryById); 
//				tnx.commit();
//				return categary;
//			}
//			
//			else {
//				session.persist(categary);
//				tnx.commit();
//				return categary;
//			}
		}
		
		
		
		
		
		@Cacheable(value="Categery" , key = "#id")
		public Categery getCategeryById(int  id) {
//			Transaction tnx = session.getTransaction();
			Categery categary = session.get(Categery.class, id);
//			tnx.commit();
			return categary;
			}



		@Cacheable(value = "Product")
		public List<Products> getAllProducts() {
			
			System.out.println("inside getAll Product");
			String query = " select * from products";
			List<Products> list= new ArrayList<>(); 
			session.doWork(connection -> {
						Statement stmt = connection.createStatement();
						ResultSet result = stmt.executeQuery(query);
						
						while(result.next()) {
							Products p= new Products();
							Categery c= new Categery();
							p.setId(result.getInt("id"));
							p.setName(result.getString("name"));
							p.setImage(result.getString("image"));
							p.setPrice(result.getDouble("price"));
							p.setDetails(result.getString("details"));
							p.setStock(result.getInt("stock"));
							c.setCatId(result.getInt("catId"));
							p.setCategary(c);
							list.add(p);
						}
						
			} );
			
			 Cache itemsCache = cacheManager.getCache("Product");
			 for (Products item : list) {
		            itemsCache.put(item.getId(), item); // Key = itemId, Value = Item
		        }
			 
			  itemsCache.put("allItems", list);

			return list;
		}



		
		
		
		
		
		
		public List<Products> getAllProductsByVendorId(String vendorId) {
			List<Products> list= new ArrayList<>();
		String query ="select *from products where venderId=?";
		session.doWork(connection->{
			PreparedStatement stmt = connection.prepareStatement(query);
			stmt.setString(1, vendorId);
			ResultSet result = stmt.executeQuery();
			while(result.next()) {
				Products p= new Products();
				p.setId(result.getInt("id"));
				p.setName(result.getString("name"));
				p.setImage(result.getString("image"));
				p.setPrice(result.getDouble("price"));
				p.setDetails(result.getString("details"));
				p.setStock(result.getInt("stock"));
				
				list.add(p);
				p=null;
			}
		});
			
			return list;
		}


		
		@CacheEvict(value = "Product" ,key = "#product.getId()")
		public Products updateProduct(Products product) {
	String query="update products set details=?,image=?,name=?,price=?,stock=? where id=?";
				session.doWork(connection->{
					PreparedStatement stmt = connection.prepareStatement(query);
					stmt.setString(1, product.getDetails());
					stmt.setString(2, product.getImage());
					stmt.setString(3, product.getName());
					stmt.setDouble(4, product.getPrice());
					stmt.setInt(5, product.getStock());
					stmt.setInt(6, product.getId());
					
					boolean execute = stmt.execute();
					System.out.println("Product is update .....");
				});
				
				 Cache cache = cacheManager.getCache("Product");
			        if (cache != null) {
			            cache.evict("Product"); // Remove the cached list
			        }
			return product;
		}


		
		
		
		
		@Autowired   CacheManager cacheManager;
		public Object getCachedValue(String cacheName, Object key) {
	      
			Cache cache = cacheManager.getCache(cacheName);
	        if (cache != null) {
	        	
	            Cache.ValueWrapper valueWrapper = cache.get(key);
	            return (valueWrapper != null) ? valueWrapper.get() : null;
	        }
	        return null;
	    }
		
		
		private  List<Rating> updRatingNew(Rating rating) {
			Object cachedValue = getCachedValue("Rating", rating.getProduct().getId());
			 List<Rating> collect= null;
			 if (cachedValue instanceof List) {
		            // Safely cast to List<Item> (with unchecked cast warning suppressed)
		            @SuppressWarnings("unchecked")
		            List<Rating> ratings = (List<Rating>) cachedValue;
		           collect = ratings.stream()
		            						.map(e-> e.getId()==rating.getId() ? rating : e )
		            					.collect(Collectors.toList());
		            return collect;
		        }
			 return new ArrayList<Rating>();
		}


		
}
