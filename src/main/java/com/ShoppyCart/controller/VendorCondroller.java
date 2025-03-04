package com.ShoppyCart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.ShoppyCart.Service.OrderService;
import com.ShoppyCart.Service.VendorService;
import com.ShoppyCart.entity.Order;
import com.ShoppyCart.entity.Vendor;



@RestController()
@RequestMapping("/v1/ven")
public class VendorCondroller {
	
	@Autowired 
	VendorService vendorService;
	@Autowired
	OrderService orderService;
	
	
	@PostMapping("/saveVendor")
	public Vendor saveVendor(@RequestBody Vendor vendor) {	
		System.out.println("inside saveVendor "+vendor);
		
		return vendorService.saveVendor(vendor);
	}
	
	@GetMapping("/getAllVendor")
	public List<Vendor> getAllVendors(){
		return vendorService.getAllVendors();
	}
	
	@GetMapping("/getVenderById/{vendorId}")
	public Vendor getVendorById(@PathVariable String vendorId) {
		return vendorService.getVendorById(vendorId);
	}
	@GetMapping("/getVenderByEmail/{email}")
	public Vendor getVendorByEmail(@PathVariable String email) {
		return vendorService.getVendorByEmail(email);
	}
	
	
	@GetMapping("/getOrderByVendorId/{vendorId}")
	public List<Order> orderByVendorId(@PathVariable String vendorId){
		
		return orderService.getOrderByVendorId(vendorId);
		
	} 
	
}
