package com.ShoppyCart.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.ShoppyCart.entity.Order;

@Repository
public class OrderDao {
	private Session session;
	
	public OrderDao(SessionFactory sessionFactory) {
		this.session=sessionFactory.openSession();
	}

	public List<Order> getOrderByUserId(int userId) {
						String query="select *from orders where userId=?";
						List<Order> list= new ArrayList<>();
						
						session.doWork(connection->{
							PreparedStatement stmt = connection.prepareStatement(query);
							stmt.setInt(1, userId);
							ResultSet result = stmt.executeQuery();
							while(result.next()) {
								Order order= new Order();
							order.setOrderId(result.getString("orderId"));	
							order.setProductName(result.getString("productName"));
							order.setUserId(result.getInt("userId"));
							order.setProdId(result.getInt("prodId"));
							order.setProductImg(result.getString("productImg"));
							order.setPrice(result.getDouble("price"));
							order.setStatus(result.getString("status"));
							order.setQuentity(result.getInt("quentity"));
							order.setTime(result.getString("time"));
							list.add(order);
							
//							order=null;
							}
						
						} );
		
		return list;
	}

	public List<Order> getOrders() {
		// TODO Auto-generated method stub
		return null;
	}

	public Order getOrderByOrderId(String orderId) {
		Order order = session.find(Order.class, orderId);
		return order;
	}

	public Order saveOrder(Order order) {
		Transaction tnx = session.beginTransaction();
		session.persist(order);
		
		tnx.commit();
		return order;
	}

	public String deleteOrderById(String orderId) {
		String query="delete from orders where orderId=?";
		try {
			session.doWork(connection->{
				PreparedStatement stmt = connection.prepareStatement(query);
				stmt.setString(1, orderId);
				boolean execute = stmt.execute();
			});
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "order delete";
	}

	
	
	public List<Order> getOrderByVendorId(String vendorId) {
		List<Order> list= new ArrayList<>();
	String query="select o.orderId ,o.price,o.prodId,o.productImg , o.productName,o.quentity ,o.status ,o.time  from orders o  JOIN Products ON  o.prodId=products.id and products.venderId=?";
		session.doWork(connection->{
			PreparedStatement stmt = connection.prepareStatement(query);
			stmt.setString(1, vendorId);
			ResultSet result = stmt.executeQuery();
			while(result.next()) {
				Order order= new Order();
				order.setOrderId(result.getString("orderId"));
				order.setPrice(result.getDouble("price"));
				order.setProdId(result.getInt("prodId"));
				order.setProductName(result.getString("productName"));
				order.setProductImg(result.getString("productImg"));
				order.setQuentity(result.getInt("quentity"));
				order.setStatus(result.getString("status"));
				order.setTime(result.getString("time"));
				list.add(order);
			}
		});
	return list;
	}
			
	
		
}
