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
import com.ShoppyCart.Repository.ProductDao;
import com.ShoppyCart.commonUse.Common;
import com.ShoppyCart.entity.Order;
import com.ShoppyCart.entity.Products;

@Service
public class OrderService {

	@Autowired OrderDao orderDao;
	
	@Autowired ProductDao productDao;
	
	
	@Cacheable(value = "Order", key = "#userId" )
	public List<Order> getOrderByUserId(int userId) {
		System.out.println("beforeUserId");
		List<Order> orderByUserId = orderDao.getOrderByUserId(userId);
		System.out.println("After userId");
		return orderByUserId;
	}

	
	

	public Order saveOrders(Order order) {
		order.setOrderId(Common.genrateOrderId());
		order.setStatus("Prossing");
		order.setTime(LocalDateTime.now().toString());
		
			if(maintainStock(order)) {
				orderDao.saveOrder(order);
				return order;
			}
			else {
				
			}
		return new Order();
	}

	
	
			private boolean maintainStock(Order order) {
				
					Products prod = productDao.getProductById(order.getProdId());
					if(prod.getStock() >= order.getQuentity()) {
						prod.setStock(prod.getStock()-order.getQuentity());	
						productDao.updateProduct(prod);		
						return true;
					}
					else {
						System.out.println( order.getQuentity() +" Product Quentity is Not Available");
					}
					try {
						
					} catch (Exception e) {
						e.printStackTrace();
					}
					return false;
				}
	
	

	public List<Order> getOrders() {
		return orderDao.getOrders();
	}

	
	
	
	

	public Order getOrderByOrderId(String orderId) {
		return orderDao.getOrderByOrderId(orderId);
	}

	
	

	public String deleteOrderById(String orderId, int userId) {	
		return orderDao.deleteOrderById(orderId);
	}

	
	
	@Cacheable(value = "List<Order>", key = "#vendorId")
	public List<Order> getOrderByVendorId(String vendorId) {
		System.out.println("beforeVedorId");
		List<Order> orderByVendorId = orderDao.getOrderByVendorId(vendorId);
		System.out.println("After VedorId");
		return orderByVendorId;
	}

	
	
}
