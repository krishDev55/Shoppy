package com.ShoppyCart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ShoppyCart.Service.CartService;
import com.ShoppyCart.Service.OrderService;
import com.ShoppyCart.Service.RatingService;
import com.ShoppyCart.Service.UserService;
import com.ShoppyCart.entity.Cart;
import com.ShoppyCart.entity.Order;
import com.ShoppyCart.entity.Rating;
import com.ShoppyCart.entity.User;
import com.ShoppyCart.vo.RatingCombineData;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/v1/user")

public class UserController {
	@Autowired CartService cartService;
	@Autowired OrderService ordersService;
	@Autowired UserService userService;
	@Autowired RatingService ratingService;
	
	
	@GetMapping("/")
	public String Home() {
		return "testing the Application";
		
	}
	
	@GetMapping("/loginPage")
	public String Home1() {
		return "WithOUt Authenticate";	
	}
	
	@GetMapping("/getUserById/{userId}")
	public User getUserById(@PathVariable /* String email */ int userId) {
		return userService.getUserById(userId);
	}
	
	@PutMapping("/userUpdate")
	public User updateUsers(@RequestBody User user) {
		return userService.updateUser(user);
	}
	
	
	
//	-------------------------------------------------------------------------
//	   cart Section All Api
	
	@PostMapping("/saveCart") 
	public Cart saveCart(@RequestBody Cart cart) {
		System.out.println("inside saveCartMethod");
		Cart saveCart = cartService.saveCart(cart);
		System.out.println("inside2 saveCartMethod" +saveCart);
		return saveCart;
	}
	
	@GetMapping("/getCardByUserId/{userId}")
	public List<Cart> getMethodName(@PathVariable String userId) {
		int int1 = Integer.parseInt(userId);
		List<Cart> cartByUserId = cartService.getCartByUserId(int1);
		System.out.println(cartByUserId);
		return cartByUserId;
	}
	@GetMapping("/getCardByCartId/{cartId}")
	public Cart getCartByCartId(@PathVariable String cartId) {
		int int1 = Integer.parseInt(cartId);
		Cart cartByUserId = cartService.getCartByCartId(int1);
		
		return cartByUserId;
	}
	
	@DeleteMapping("/deleteCartById/{cartId}")
	public String  deleteCartById(@PathVariable int cartId) {	
		return cartService.deleteCartById(cartId);
	}
	
//	--------------------------------------------------------------------
//	Orders Alll Section
	
	@GetMapping("/getOrderByUserId/{userId}")
	public List<Order> getOrderByUserId(@PathVariable int userId){
		List<Order> orderByUserId = ordersService.getOrderByUserId(userId);
		System.out.println(userId+" --- "+orderByUserId);
		return orderByUserId;
	}
	
	@PostMapping("/saveOrder")
	public Order saveOrders(  @RequestBody Order order) {	
		System.out.println("S_Order : "+order);
		return ordersService.saveOrders(order);
		
	}
	
	@GetMapping("/getAllOrders")
	public List<Order> getOrders(){
		
		return ordersService.getOrders();
	}
	@GetMapping("/getOrderByOrderId/{orderId}")
	public Order getOrderByOrderId(@PathVariable String orderId){
		
		return ordersService.getOrderByOrderId(orderId);
	}
	
	@DeleteMapping("/deleteOrderById/{orderId}")
	public String deleteOrderById(@PathVariable String orderId){
		Order order = ordersService.getOrderByOrderId(orderId);
		return ordersService.deleteOrderById(orderId,order.getUserId());
		
	}
	
//	_-----------------------------------------------------------
//	rating Service
	@GetMapping("/getProductHistoryById/{productId}")
	public List<RatingCombineData> getProductHistoryById(@PathVariable int productId) {
//		List<Rating> allRatingByProductId = ratingService.getAllRatingByProductId(productId);
		List<RatingCombineData> combainData = ratingService.combainData(productId);
		return combainData;
	}
}
