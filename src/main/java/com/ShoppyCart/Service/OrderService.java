package com.ShoppyCart.Service;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ShoppyCart.Repository.OrderDao;
import com.ShoppyCart.commonUse.Common;
import com.ShoppyCart.entity.Order;

@Service
public class OrderService {

	@Autowired
	OrderDao orderDao;
	
	public List<Order> getOrderByUserId(int userId) {
		// TODO Auto-generated method stub
		return orderDao.getOrderByUserId(userId);
	}

	public Order saveOrders(Order order) {
		// TODO Auto-generated method stub
		order.setOrderId(Common.genrateOrderId());
		order.setStatus("Prossing");
		String string = LocalDateTime.now().toString();
		System.out.println( "Date is  : "+string);
		order.setTime(string);
		return orderDao.saveOrder(order);
	}

	public List<Order> getOrders() {
		// TODO Auto-generated method stub
		return orderDao.getOrders();
	}

	public Order getOrderByOrderId(String orderId) {
		// TODO Auto-generated method stub
		return orderDao.getOrderByOrderId(orderId);
	}

	public String deleteOrderById(String orderId) {
		
		return orderDao.deleteOrderById(orderId);
	}

	public List<Order> getOrderByVendorId(String vendorId) {
		return orderDao.getOrderByVendorId(vendorId);
		
	}

}
