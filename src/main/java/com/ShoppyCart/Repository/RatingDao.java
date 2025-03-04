package com.ShoppyCart.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.ShoppyCart.entity.Products;
import com.ShoppyCart.entity.Rating;
import com.ShoppyCart.entity.User;
import com.ShoppyCart.vo.RatingCombineData;

@Repository

public class RatingDao {
		Session session;
		public RatingDao(SessionFactory sessionFactory) {
				this.session=sessionFactory.openSession();
	
				}
	
	public String saveRating(Rating rating) {
		Transaction tnx = session.beginTransaction();
		try {
			session.persist(rating);
		} catch (Exception e) {
			System.out.println("some Error But i Dont know " + e.getMessage());
		} finally {
			tnx.commit();
		}
		return "rating save Secussfully";
	}

	public String updateRating(Rating rating) {
			String query ="update rating set comment=?,value=? where "
											+ " User_Id=? And product_id=?";
			session.doWork(connection->{
				PreparedStatement stmt = connection.prepareStatement(query);
				stmt.setString(1, rating.getComment());
				stmt.setDouble(2, rating.getValue());
				stmt.setInt(3, rating.getUser().getId());
				stmt.setInt(4, rating.getProduct().getId());
				boolean execute = stmt.execute();
				System.out.println("Rating update Okkk "+ execute);
			});
		
		return "Okkk  ";
	}
	
	public Rating  getRatingByUserIdAndProductId(int userId, int productId) {
		String query="select * from rating where User_Id=? And product_id=?";
		Rating rating= new Rating();
		
		session.doWork(connection->{
			PreparedStatement stmt = connection.prepareStatement(query);
			stmt.setInt(1, userId);
			stmt.setInt(2, productId);
			ResultSet result = stmt.executeQuery();
			
				while(result.next()) {
						rating.setId(result.getLong("id"));
						rating.setComment(result.getString("comment"));
						rating.setValue(result.getDouble("value"));	
						rating.setDate(result.getString("date"));
				}
			
		});
		
		return rating;
	}
	
	private void check(Boolean flage) {
		
	}

	public List<Rating> getllRatingByProductId(int productId) {
		String query = "select *from rating where Product_id=?";
		List<Rating> list = new ArrayList<>();
		session.doWork(connection->{
			PreparedStatement stmt = connection.prepareStatement(query);
			stmt.setInt(1, productId);
			ResultSet result = stmt.executeQuery();
			while(result.next()) {
			Rating r= new Rating();
			User u= new User();
			Products p= new Products();
			r.setId(result.getLong("id"));
			r.setValue(result.getDouble("value"));
			r.setComment(result.getString("comment"));
			r.setDate(result.getString("date"));
			
			u.setId(result.getInt("User_Id"));
			r.setUser(u);
			
			list.add(r); 
			}
		});
		return list;
	}
	
	
	// GetCombine of Join Coloum 
	public List<RatingCombineData> getCombineData(Products product) {
		String query="select u.email, r.comment,r.value ,r.user_id, r.date from rating r, user u "
				                   + "where u.id=r.User_Id And r.product_id=?";
		List<RatingCombineData> list= new ArrayList<>();
		session.doWork(connection->{
			PreparedStatement stmt = connection.prepareStatement(query);
			stmt.setInt(1, product.getId());
			ResultSet result = stmt.executeQuery();
			while(result.next()) {
				RatingCombineData rd= new RatingCombineData();
				rd.setEmail(result.getString("email"));
				rd.setComment(result.getString("comment"));
				rd.setDate(result.getString("date"));
				rd.setUser_id(result.getInt("user_id"));
				rd.setValue(result.getDouble("value"));
//				rd.setProduct(product);
				list.add(rd);
			}
		});
		return list;
	}
}
