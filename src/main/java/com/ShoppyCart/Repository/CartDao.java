package com.ShoppyCart.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.DeleteMapping;

import com.ShoppyCart.entity.Cart;

@Repository
public class CartDao {
	
	Session session ;
	
	public CartDao(SessionFactory sessionFactory){
		session=sessionFactory.openSession();
	}

	public Cart save(Cart cart) {
		
		Transaction tnx = session.beginTransaction();
			session.persist(cart);
			tnx.commit();		
		return cart;
	}

	@Cacheable(value = "Cart", key = "#userId")
	public List<Cart> getCartByUserId(int userId) {
				String query= "select  * from cart where user_id=?";	
			List<Cart> list= new ArrayList<>();
				session.doWork(connection ->{
					PreparedStatement stmt = connection.prepareStatement(query);
					stmt.setInt(1, userId);
					ResultSet result = stmt.executeQuery();
					while(result.next()) {
						Cart tempCard= new Cart();
						tempCard.setProd_image(result.getString("prod_image"));
						tempCard.setCart_id(result.getInt("cart_id"));
						tempCard.setProd_id(result.getInt("prod_id"));
						tempCard.setProd_name(result.getString("prod_name"));
						tempCard.setProd_price(result.getDouble("prod_price"));
						tempCard.setProd_quantity(result.getInt("prod_quantity"));		
						list.add(tempCard);
					}
//					connection.close();
				} );
				
		return list;
	}

	public Cart getCartByCartId(int cartId) {
		Transaction tnx = session.beginTransaction();		
		Cart cart = session.get(Cart.class, cartId);
		tnx.commit();
		return cart;
	} 
	

	public String  deleteCartById(Integer cartId) {
		String query="delete from cart where cart_id=?";
	session.doWork(( connection)->{
			PreparedStatement stmt = connection.prepareStatement(query);
			stmt.setInt(1, cartId);
			boolean execute = stmt.execute();			
			if(execute) { return ;}
			stmt.close();			
		});	
		return "Cart Delete";
	}
	
	
	
		
}
