package com.ShoppyCart.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ShoppyCart.Repository.CartDao;
import com.ShoppyCart.entity.Cart;

@Service
public class CartService {
	@Autowired
	CartDao cartDao; 
	
	public Cart saveCart(Cart cart) {
		// TODO Auto-generated method stub
		
		return cartDao.save(cart);
	}

	public List<Cart> getCartByUserId(int userId) {
		// TODO Auto-generated method stub
		return cartDao.getCartByUserId(userId);
	}

	public Cart getCartByCartId(int cartId) {
		// TODO Auto-generated method stub
		return cartDao.getCartByCartId(cartId);
	}

	public String deleteCartById(int cartId) {
		return cartDao.deleteCartById(cartId);
	}
}
