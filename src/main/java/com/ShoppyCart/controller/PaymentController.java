package com.ShoppyCart.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ShoppyCart.commonUse.RazorPayCommon;
import com.razorpay.Order;
import com.razorpay.Payment;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/v1/RazorPay")
public class PaymentController {
	private final static String  KEY="rzp_test_HKhIE2kfoLbD8L";
	private final static String 	SECRET="9FnBhBsO6eDmvEa7KrHQO69h";
	// this Logic is Razorpay peyment Application :
	
	@Autowired private  RazorPayCommon razorPayCommon;
	
	

	
	@PostMapping("/genrateOrder")
	public String razorpayPayment(@RequestBody Map<String, Object> data) throws RazorpayException {
		System.out.println("inside pay method ......1!!!"); 
		var client=new RazorpayClient(KEY, SECRET);
		int amt=Integer.parseInt(data.get("amount").toString());
		System.out.println("inside pay method 2 ......1!!! "+amt); 
		
		JSONObject orderRequest = new JSONObject();
		orderRequest.put("amount",amt*100);
		orderRequest.put("currency",data.get("currency"));
		orderRequest.put("receipt", razorPayCommon.gen_receiptId());
		System.out.println("inside pay method 3 ......1!!! "); 
		// create a order s
				Order order = client.orders.create(orderRequest);
				System.out.println("Order is create : "+order);
				orderRequest.put("notes", data.get("notes"));
				
//				JSONObject notes = new JSONObject();
//				notes.put("notes_key_1","Tea, Earl Grey, Hot");
//				orderRequest.put("notes",notes);
				
				String order2 = order.toString();
			
				System.out.println("Massage is "+order2);
		return order2;
	}
	
	@GetMapping("/get_status/{orderId}")
	public String getPaymentStatus(@PathVariable String orderId) {
	    try {
	        RazorpayClient razorpay = new RazorpayClient(KEY, SECRET);
	        List<Order> fetchAll = razorpay.orders.fetchAll();
//	        for (Order order : fetchAll) {
//				System.out.println(order.toJson().toString());
//			}
	        Order order = razorpay.orders.fetch(orderId);
	        System.out.println(order);
	        return order.toJson().toString();
	    } catch (RazorpayException e) {
//	        return "Error fetching status";
	    }
	    return null;
	}
	
	
 @GetMapping("/get_orderByOrderId/{orderId}")
 public String get_orderByOrderId(@PathVariable String orderId) throws RazorpayException {
	 RazorpayClient client = new RazorpayClient(KEY, SECRET);
			  try {
				  Order fetch = client.orders.fetch(orderId);
				return   fetch.toJson().toString();
			} catch (Exception e) {
				return e.getMessage();
			}
 }
 
 @GetMapping("/get_orderByTnxId/{tnxId}")
 public String get_orderByTnxId(@PathVariable String tnxId) throws RazorpayException {
	 RazorpayClient client = new RazorpayClient(KEY, SECRET);
			  try {
				 
				 Order order = client
						 .orders
						 .fetchAll()
						  	.stream()
						  		.filter(e->e.toJson().get("receipt").toString().equalsIgnoreCase(tnxId.strip()))
						  			.findFirst()
						  				.get();
			  				
				return   order.toJson().toString();
			} catch (Exception e) {
				return e.getMessage();
			}
 }
 
 
   @GetMapping("/getPaymentByPayId/{peyId}")
   public String getPaymentByPayId(@PathVariable String peyId){
			   try {
					RazorpayClient client = new RazorpayClient(KEY, SECRET);
					Payment fetch = client.payments.fetch(peyId);
					System.out.println(fetch);
					return fetch.toJson().toString();
			   } 
			   catch (RazorpayException e) {
				
				return e.getMessage();
			}
   
   }
 
 
   
   @PostMapping("/createPayment")
   public String createPayment(@RequestBody Map<String, Object> data) {
	   System.out.println("data is  "+data.toString());
				   try {
					RazorpayClient client = new RazorpayClient(KEY, SECRET);
					JSONObject paymentRequest = new JSONObject();
					paymentRequest.put("email", data.get("email"));
					paymentRequest.put("contact", data.get("contact"));
					paymentRequest.put("order_id", data.get("order_id"));
					
//					JSONObject notes = new JSONObject();
////					notes.put("address", data.get(notes.get("address")));
//					
//					paymentRequest.put("notes", notes);
					
//					 Payment payment = client.payments.createJsonPayment(paymentRequest);
					 Payment payment = client.payments.createRecurringPayment(paymentRequest);
					 return payment.toJson().toString();
				} catch (RazorpayException e) {
					 e.printStackTrace();
					return "";
				}
	   
	
				   
				   
	   
   }
 
 
 
 
 
 
 
}
