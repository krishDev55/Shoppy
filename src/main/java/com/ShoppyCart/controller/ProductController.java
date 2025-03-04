package com.ShoppyCart.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ShoppyCart.Service.ProductService;
import com.ShoppyCart.Service.RatingService;
import com.ShoppyCart.Service.VendorService;
import com.ShoppyCart.entity.Products;
import com.ShoppyCart.entity.Rating;
import com.ShoppyCart.entity.Vendor;

@RestController
@RequestMapping("/v1/prod")
public class ProductController {

	@Autowired ProductService productService;
	@Autowired VendorService vendorService;
	@Autowired RatingService ratingService;

	@GetMapping("/getAllProduct")
	public ResponseEntity<Map<String, Object>> getAllProduct() {

		List<Products> allProduct1 = productService.getAllProducts();
//		Set<Product> allProduct = productService.randomProductList(allProduct1);
		Set<Products> allProduct = new HashSet<>(allProduct1);

		Map<String, Object> map = new HashMap<>();
		map.put("productList", allProduct);

		return ResponseEntity.ok(map);
	}

	@GetMapping("/getProductById/{id}")
	public ResponseEntity<Map<String, Products>> getProductById(@PathVariable Integer id) {
		Products productById = productService.getProductById(id);
		Map<String, Products> map = new HashMap<>();
		map.put("product", productById);
		return ResponseEntity.ok(map);
	}

	@PostMapping("/saveProduct")
	public Products saveProduct(@RequestBody Products product) {
//List<Products> list= new ArrayList<>();
//		Vendor vendors = vendorService.getVendorById(product.getVendorId());
//				List<Products> products = vendors.getProductList();
//		if(products==null) {
//			vendors.setProductList(list);
//		}else {   
//				products.add(product);
//				vendors.setProductList(products);
//		}
		System.out.println(" Produdct print : " + product +" -- ");
		System.out.println(" Last vendor print : " + product.getVendor() +" -- ");
////		vendorService.saveAndUpdateVendor(vendors);
		product.setId(new Random().nextInt(1000,9999));
		
		return  productService.saveProduct(product);
	}

	@GetMapping("/getProductByVendorId/{vendorId}")
	public ResponseEntity<Map<String, List<Products>>> getProductByVendorId(@PathVariable String vendorId) {
		List<Products> allProduct = productService.getProductByVendorId(vendorId);
		Map<String, List<Products>> map = new HashMap<>();
		map.put("productList", allProduct);
		return ResponseEntity.ok(map);
	}

	@GetMapping("/searchProduct/{search}")
	public List<Products> searchProduct(@PathVariable String search) {
		return productService.searchProduct(search);
	}
	
//	---------------------------------------------------------
//	Rating 
	@PostMapping("/setRating")
	public String setRating(@RequestBody Rating rating) {
		
		return ratingService.setRating(rating);
	}
	
	@PutMapping("/updateRating")
	public String updateRating(@RequestBody Rating rating) {
		return ratingService.updateRating(rating);
	}
	@GetMapping("/getAllRatingByProductId/{productId}")
	public List<Rating> getAllRatingByProductId(@PathVariable int productId){
		return ratingService.getAllRatingByProductId(productId);
	}
	 
}
