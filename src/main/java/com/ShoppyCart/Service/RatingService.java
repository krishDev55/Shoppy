package com.ShoppyCart.Service;


import java.text.DateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ShoppyCart.Repository.RatingDao;
import com.ShoppyCart.entity.Products;
import com.ShoppyCart.entity.Rating;
import com.ShoppyCart.vo.RatingCombineData;

@Service
public class RatingService {
	@Autowired RatingDao ratingDao;
	@Autowired UserService userService;
	@Autowired ProductService productService;
	
	public String setRating(Rating rating) {	
		String dateToStr = DateFormat.getInstance().format(new Date());  
		rating.setDate(dateToStr);
		
		Rating temp = ratingDao.getRatingByUserIdAndProductId(
				rating.getUser().getId(), rating.getProduct().getId());
		
		System.out.println("temp is "+temp);
		
		if (temp.getId()==0) {	
			return ratingDao.saveRating(rating);
		}
		else {
			rating.setId(temp.getId());
			return ratingDao.updateRating(rating);
		}
	
	}

	public String updateRating(Rating rating) {
		return ratingDao.updateRating(rating);
	}
	
	public List<Rating> getAllRatingByProductId(int productId){
		return ratingDao.getllRatingByProductId(productId);	
	}
	
	// extra Oparation Like rating and User masage and reviev system
	public  List<RatingCombineData> combainData(int productId) {
		Products product = productService.getProductById(productId);
		product.setCategary(null);
		product.setVendor(null);
		List<RatingCombineData> combineData = ratingDao.getCombineData(product);
		
		return combineData;
	}
	
	// calculate Avarage of Reting the Paticullar product Id
	public void calculateRatingAvarage(int productId) {
		
	}
}
