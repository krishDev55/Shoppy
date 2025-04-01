package com.ShoppyCart.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.ShoppyCart.entity.User;
import com.ShoppyCart.vo.GetString;

@Repository
public class UserDao {
		
		
		Session session;
		
		public UserDao(SessionFactory sessionFactory){
			
			session = sessionFactory.openSession();
			
		} 
		
		@Cacheable(value = "User", key="#id")
		public User getUserById(int id) {
			User user = session.find(User.class, id);
			return user;
		}
		
		public User getUserByIdAndPassword(int id, String password) {
//			temp.findByq
			return null;
		}
		
		
		public User saveUser(User user) {
			Transaction tnx = session.beginTransaction();
			session.persist(user);
			tnx.commit();
			return user;
		}
		
		
		public List<User> getAllUser(){
			return null;
		}


		public User getUserByEmailAndPassword(String email, String password) {
			User user= new User();
			String query="select * from user where email=? and password=?";
			session.doWork(connection->{				
				PreparedStatement s= connection.prepareStatement(query);				
				s.setString(1, email);
				s.setString(2, password);
				ResultSet result = s.executeQuery();
				while(result.next()) {
					user.setId(result.getInt("id"));
					user.setEmail(result.getString("email"));
					user.setMobileNo(result.getString("mobileNo"));
					user.setPassword(result.getString("password"));
				}
				s.close();
				
			}
			
					);
			
			return user;
		}


		public User getUserByEmail(String email) {
			User user= new User();
			String query="select * from user where email=?";
			session.doWork(connection->{				
				PreparedStatement s= connection.prepareStatement(query);				
				s.setString(1, email);
				
				ResultSet result = s.executeQuery();
				while(result.next()) {
					user.setId(result.getInt("id"));
					user.setEmail(result.getString("email"));
					user.setMobileNo(result.getString("mobileNo"));
					user.setPassword(result.getString("password"));
				}
				s.close();	
			});
			return user;
		}


		public User updateUser(User user) {
			// TODO Auto-generated method stub
			return null;
		}

		@Cacheable(value = "User", key = "#id")
		public String getEmailById(int id) {
			String query ="select email from user where id=? ";
			 GetString gs= new GetString();
			session.doWork(connection->{
				PreparedStatement stmt = connection.prepareStatement(query);
				stmt.setInt(1, id);
				ResultSet result = stmt.executeQuery();
				gs.setValue(result.getString("email"));
			});
			if(gs.getValue()!=null) {
				return gs.getValue();	
			}
			else {
				return "TempEmail";
			}
		}
}
