package com.ShoppyCart.Repository;

import java.sql.Connection;
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

import com.ShoppyCart.commonUse.CacheMachanism;
import com.ShoppyCart.entity.Order;
import com.ShoppyCart.entity.Products;
import com.ShoppyCart.entity.Rating;

@Repository
public class OrderDao {
	private Session session;
	
	@Autowired CacheMachanism cacheMachamism;
	@Autowired CacheManager cacheManager;
	
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

	
	
	@Cacheable(value = "Orders" )
	public List<Order> getOrders() {
		System.out.println("inside getOrders Dao ");
		String query="select *from Orders";
		List<Order> list= new ArrayList<>();
		session.doWork(connection->{
			try (Statement stmt = connection.createStatement()) {
				ResultSet result = stmt.executeQuery(query);
				while(result.next()) {
					Order o= new Order();
					o.setOrderId(result.getString("orderId"));
					o.setPrice(result.getDouble("price"));
					o.setProdId(result.getInt("prodId"));
					o.setProductImg(result.getString("productImg"));
					o.setProductName(result.getString("productName"));
					o.setQuentity(result.getInt("quentity"));
					o.setStatus(result.getString("status"));
					o.setTime(result.getString("time"));
					o.setUserId(result.getInt("userId"));
				
					list.add(o);
					
				}
			};
		});
		
		 Cache itemsCache = cacheManager.getCache("Product");
		 for (Order order : list) {
	            itemsCache.put(order.getOrderId(), order); // Key = itemId, Value = Item
	        }
		 
		  itemsCache.put("allOrders", list);
		  
		return list;
	}

	@Cacheable(value = "Order", key = "#orderId")
	public Order getOrderByOrderId(String orderId) {
		System.out.println("inside OrderId method" );
		Order order = session.find(Order.class, orderId);
		return order;
	}

	
	@CachePut(value="Order" ,key = "#order.userId")
	public List<Order> saveOrder(Order order) {
		System.out.println("inside save Mehtod ...");
		Transaction tnx = session.beginTransaction();
		session.persist(order);
		tnx.commit();
		List<Order> cacheList = updOrderCacheNew(order);
		cacheList.add(order);
		return cacheList;
	}

	
	
	@CacheEvict(value="Order" ,key = "#orderId")
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
		cacheMachamism.evictCacheEntry("Order",orderId);
		String name = session.getCacheStoreMode().name();
		return "order delete";
	}
	
	
	private  List<Order> updOrderCacheNew(Order order) {
		Object cachedValue =cacheMachamism.getCachedValue("Order", order.getOrderId());
		 List<Order> collect= null;
		 if (cachedValue instanceof List) {
	            // Safely cast to List<Item> (with unchecked cast warning suppressed)
	            @SuppressWarnings("unchecked")
	            List<Order> orders = (List<Order>) cachedValue;
	           collect = orders.stream()
	            						.map(e-> e.getOrderId()==order.getOrderId() ? order : e )
	            					.collect(Collectors.toList());
	            return collect;
	        }
		 return new ArrayList<Order>();
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
