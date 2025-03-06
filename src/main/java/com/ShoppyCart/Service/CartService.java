package com.ShoppyCart.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.ShoppyCart.Repository.CartDao;
import com.ShoppyCart.entity.Cart;

@Service
public class CartService {
	@Autowired
	CartDao cartDao; 
	
	
	@CachePut(value="Cart", key = "#cart.cart_id")
	public Cart saveCart(Cart cart) {
		return cartDao.save(cart);
	}

	
	
	@Cacheable(value = "Cart", key = "#userId")
	public List<Cart> getCartByUserId(int userId) {
		return cartDao.getCartByUserId(userId);
	}

	
	
	
	
	@Cacheable(value = "Cart", key = "#cartId")
	public Cart getCartByCartId(int cartId) {
		// TODO Auto-generated method stub
		return cartDao.getCartByCartId(cartId);
	}

	
	
	@CacheEvict(value = "Cart", key = "#cartId")
	public String deleteCartById(int cartId) {
		return cartDao.deleteCartById(cartId);
	}
	
	
	
	
	
}
