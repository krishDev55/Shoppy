package com.ShoppyCart.Service;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.ShoppyCart.Repository.OrderDao;
import com.ShoppyCart.commonUse.Common;
import com.ShoppyCart.entity.Order;

@Service
public class OrderService {

	@Autowired
	OrderDao orderDao;
	
	
	@Cacheable(value = "Order", key = "#userId")
	public List<Order> getOrderByUserId(int userId) {
		return orderDao.getOrderByUserId(userId);
	}

	
	
	@CachePut(value="Order" ,key = "order.orderId")
	public Order saveOrders(Order order) {
		// TODO Auto-generated method stub
		order.setOrderId(Common.genrateOrderId());
		order.setStatus("Prossing");
		String string = LocalDateTime.now().toString();
		System.out.println( "Date is  : "+string);
		order.setTime(string);
		return orderDao.saveOrder(order);
	}

	
	
	@Cacheable()
	public List<Order> getOrders() {
		// TODO Auto-generated method stub
		return orderDao.getOrders();
	}

	
	
	
	
	@Cacheable(value = "Order", key = "#orderId")
	public Order getOrderByOrderId(String orderId) {
		return orderDao.getOrderByOrderId(orderId);
	}

	
	
	@CacheEvict(value = "Order", key = "#orderId")
	public String deleteOrderById(String orderId) {	
		return orderDao.deleteOrderById(orderId);
	}

	
	
	@Cacheable(value = "Order", key = "#vendorId")
	public List<Order> getOrderByVendorId(String vendorId) {
		return orderDao.getOrderByVendorId(vendorId);
		
	}

}
