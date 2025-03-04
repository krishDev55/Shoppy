package com.ShoppyCart.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ShoppyCart.entity.Categery;
import com.ShoppyCart.entity.Products;

@Repository
public class ProductDao {
		
//		@Autowired
//		SessionFactory sessionFactory;
		
		Session session ;
		
		public ProductDao(SessionFactory sessionFactory){
			session=sessionFactory.openSession();
		} 
		
		
		
		public Products saveProduct(Products product) {
//			Transaction tnx = session.beginTransaction();
//			Categery categary = product.getCategary();
//			List<Products> products = categary.getProducts();
//			products.add(product);
//			categary.setProducts(products);
//			session.persist(product);
//			tnx.commit();
			
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
						System.out.println("Query Update Sucsessfully or Not "+execute);
					});
		return product;
		}
		
		public Products getProductById(int  id) {
			Products products = session.get(Products.class, id);
			
			return products;
			}
		
		
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
		
		public Categery getCategeryById(int  id) {
//			Transaction tnx = session.getTransaction();
			Categery categary = session.get(Categery.class, id);
//			tnx.commit();
			
			return categary;
			}



		public List<Products> getAllProducts() {
			String query = " select * from products";
			List<Products> list= new ArrayList<>(); 
			session.doWork(connection -> {
						Statement stmt = connection.createStatement();
						ResultSet result = stmt.executeQuery(query);
						
						while(result.next()) {
							Products p= new Products();
							p.setId(result.getInt("id"));
							p.setName(result.getString("name"));
							p.setImage(result.getString("image"));
							p.setPrice(result.getDouble("price"));
							p.setDetails(result.getString("details"));
							p.setStock(result.getInt("stock"));
							list.add(p);
						}
						
			} );
			return list;
		}



		public List<Products> getAllProductsByVendorId(String vendorId) {
			List<Products> list= new ArrayList<>();
		String query ="select *from products where vendorId=?";
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



		
}
