package com.ShoppyCart.controller;




import java.net.HttpCookie;
import java.net.http.HttpRequest;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.apache.hc.client5.http.cookie.BasicCookieStore;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.tomcat.util.digester.ArrayStack;
import org.json.JSONObject;
//import org.apache.kafka.clients.consumer.Consumer;
//import org.apache.kafka.clients.consumer.ConsumerRecord;
//import org.apache.kafka.clients.consumer.ConsumerRecords;
//import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.Cache.ValueWrapper;
import org.springframework.cache.CacheManager;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.ShoppyCart.Kafka.Consumers;
import com.ShoppyCart.Kafka.JsonProducer;
import com.ShoppyCart.Kafka.Producer;
import com.ShoppyCart.Kafka.vo.UserSend;
import com.ShoppyCart.Repository.RatingDao;
import com.ShoppyCart.Service.ProductService;
import com.ShoppyCart.Service.UserService;
import com.ShoppyCart.Service.VendorService;
import com.ShoppyCart.commonUse.CacheMachanism;
import com.ShoppyCart.entity.Categery;
import com.ShoppyCart.entity.Products;
import com.ShoppyCart.entity.Rating;
import com.ShoppyCart.entity.User;
import com.ShoppyCart.entity.Vendor;
import com.ShoppyCart.vo.LoginCreadintial;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;






@RestController
@RequestMapping("/v1")
public class HomeController {
	@Autowired ProductService productService;
	
	@Autowired UserService userService;
	
	@Autowired VendorService vendorService;
	
	@Autowired CacheManager cacheManager;
	
	@Autowired CacheMachanism cacheMachanism;
	

	
//	When we are use kafka server then comments the line
	/*
	 * @Autowired Producer producer;
	 * 
	 * @Autowired JsonProducer jsonProducer;
	 * 
	 * @Autowired Consumers consumers;
	 */
	
	
	static String  KEY="rzp_test_HKhIE2kfoLbD8L";
	static String 	SECRET="9FnBhBsO6eDmvEa7KrHQO69h";
	// this Logic is Razorpay peyment Application : 
	
	@PostMapping("/pay")
	public String razorpayPayment(@RequestBody Map<String, Object> data) throws RazorpayException {
		System.out.println("inside pay method ......1!!!"); 
		var client=new RazorpayClient(KEY, SECRET);
		
		int amt=Integer.parseInt(data.get("amount").toString());
		
		System.out.println("inside pay method 2 ......1!!! "+amt); 
		
		JSONObject orderRequest = new JSONObject();
		orderRequest.put("amount",amt*100);
		orderRequest.put("currency","INR");
		orderRequest.put("receipt", "txn_112121");
		
		System.out.println("inside pay method 3 ......1!!! "); 
		// create a order 
				Order order = client.orders.create(orderRequest);
				System.out.println("Order is create : "+order);
				
				JSONObject notes = new JSONObject();
				notes.put("notes_key_1","Tea, Earl Grey, Hot");
				orderRequest.put("notes",notes);
				
				String order2 = order.toString();
			
				System.out.println("Massage is "+order2);
		return order2;
	}
	
	@GetMapping("/status/{orderId}")
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
	
	
	 
	@GetMapping("/getCache/{str}")
	public String getSessionCache(@PathVariable String str) {
		return cacheMachanism.sessionCache(str) ;
	}
	
	
	
	
	
	@GetMapping("/")
	public String Home(HttpServletRequest req,HttpServletResponse res) {
		String url="https://dexter.goibibo.com/common/newrelic/?serviceApp=superbus&seatLayoutCall=Jintur/Pune/2035/BanjaraTravels/NONACSleeper2+1-redbusnew@1001994551629449393-25450-2025/02/18";
		
		RestTemplate temp = new RestTemplate();
		ResponseEntity<String> response = temp.exchange(url,  
									HttpMethod.GET,
										null,
										String.class);
		

		// Get all response headers
		HttpHeaders headers = response.getHeaders();
		System.out.println("Headers: " + headers);

		
		String body = response.getBody();
		System.out.println("Body is : "+body);
		System.out.println("cookies : " +req.getHeader("cookie"));
		Enumeration<String> headerNames = req.getHeaderNames();
		while(headerNames.hasMoreElements()) {
			String nextElement = headerNames.nextElement();
			System.out.println("header Itr: "+nextElement);
		}
		System.out.println("Headers: " + req.getHeaderNames());
		
		
		// Extract cookies from the 'Set-Cookie' header(s)
		List<String> setCookieHeaders = headers.get("Cookie");
		List<HttpCookie> cookies = new ArrayList<>();
		if (setCookieHeaders != null) {
		    for (String cookieHeader : setCookieHeaders) {
		    	
		        cookies.addAll(HttpCookie.parse(cookieHeader));
		    }
		}

		// Store cookies (e.g., in a list or database)
		cookies.forEach(cookie -> {
		    System.out.println("Cookie: " + cookie.getName() + " = " + cookie.getValue());
		});
		
		
		
//		String url2="https://bus.goibibo.com/apis/v1/seatlayout/?format=json";
//		
//		RestTemplate temp2 = new RestTemplate();
//		
//		ResponseEntity<String> response2 = temp2.postForEntity(
//										url2,  
//									HttpMethod.POST,
//										null,
//										String.class);
//		
//
//		// Get all response headers
//		HttpHeaders headers2 = response2.getHeaders();
//		System.out.println("Headers: " + headers2);
//
//		
//		String body2 = response.getBody();
//		System.out.println("Body is : "+body2);
//		
		return "testing the Application";
		
	}
	
	@GetMapping("/h")
	public String Home2(){
		String url="https://bus.goibibo.com/apis/v1/seatlayout/?format=json";
		// Create a cookie store
	BasicCookieStore cookieStore = new BasicCookieStore();
    
		// Build HttpClient with the cookie store
		CloseableHttpClient httpClient = HttpClients.custom()
		    .setDefaultCookieStore(cookieStore)
		    .build();

		// Configure RestTemplate to use HttpClient
		HttpComponentsClientHttpRequestFactory factory = 
		    new HttpComponentsClientHttpRequestFactory(httpClient);
//		factory.setHttpClient(httpClient);
		
		RestTemplate restTemplate = new RestTemplate(factory);

		// Subsequent requests will automatically handle cookies
		ResponseEntity<String> response = restTemplate.postForEntity(
		    url,
		    HttpMethod.POST,
		    String.class
		);
		System.out.println("responce Header "+response.getHeaders());
		System.out.println("responce body "+response.getBody());
		
		return "home method 2";

	}
	
	@GetMapping("/loginPage")
	public String Home1() { 
		return "WithOUt Authenticate";
		
	}
	
//	-----------------------------------------------------------------------------------
	
	@PostMapping("/saveProduct")
	public  Products saveProducts(@RequestBody Products product) {
		Products saveProduct = productService.saveProduct(product);
		return saveProduct;
	}
	
	@GetMapping("/getProductById/{id}")
	public Products getProductsById(@PathVariable int id ) {
		Products productById = productService.getProductById(id);
//		productById.setCategary(null);
//		productById.setVendor(null);
		return productById;
	} 

	@GetMapping("/getAllProducts")
	public ResponseEntity<Map<String, Object>> getAllProducts( ) {
//		List<Products> allProduct = productService.getAllProducts();
		Set<Products> allProduct = productService.getAllRandomProducts();
		
		Map<String, Object> map= new HashMap<>();
		map.put("productList", allProduct );
		
		return ResponseEntity.ok(map);	
	} 
	
	@PostMapping("/saveCategery")
	public  Categery saveProducts(@RequestBody Categery categary) {
		Categery categary1 = productService.saveCategery(categary);
		return categary1;
	}
	
	
	
	@GetMapping("/getCategaryById/{id}")
	public Categery getCategaryById(@PathVariable int id ) {
		Categery categary = productService.getCategeryById(id);
		
		return categary;
	} 
	
	
	
	@PostMapping("/login")
	public ResponseEntity<Map<String, Object>> LoginUser(@RequestBody LoginCreadintial creadintial) {
		System.out.println("loginCredintial "+creadintial);	
		String varify = userService.varify(creadintial);
			if(varify==null) {				
				throw new NoSuchElementException("Username And Password Incorect");
			}
			User user = userService.getUserByEmail(creadintial.getEmail());
			Vendor vendor= vendorService.getVendorByEmail(creadintial.getEmail());
			System.out.println("Token Is "+varify);
			Map<String, Object> map= new HashMap<>();
			map.put("token", varify);
			if(creadintial.getEmail().equals(user.getEmail())) {
				map.put("entity", user);	
				map.put("roll", "user");
			}
			else {
				map.put("entity", vendor);
				map.put("roll", "vendor");
			}
//			When we are use the kafka Server then use the following code;
			/* 
			 * UserSend userSend = new UserSend(); userSend.setId(user.getId());
			 * userSend.setEmail(user.getEmail()); userSend.setMobileNo(user.getMobileNo());
			 * userSend.setPassword(user.getPassword()); userSend.setDate(new Date());
			 * jsonProducer.sendMessage(userSend); producer.sentMessage(user.getEmail());
			 */
		return ResponseEntity.ok(map);	
	}
	
	@PostMapping("/saveUser")
	public User saveUser(@RequestBody User user) {
		return userService.saveUser(user);
	}
	
	@GetMapping("/getUserById/{userId}")
	public User getMethodName(@PathVariable int  userId) {
		return userService.getUserById(userId);
	}
	
	@GetMapping("/getUserByEmailAndPassword")
	public User getUserByEmailAndPassword(@RequestBody LoginCreadintial login) {	
		return userService.getUserByEmailAndPassword(login.getEmail(), login.getPassword());
	}
	
	@GetMapping("/getKafkaLogs/{abc}")
	  public void consumeKafka(@PathVariable String abc) {
		
		System.out.println("inside mathdo ....");
//		consumers.getMessege(abc);
	       
	      
	    }
	
	
	
}
