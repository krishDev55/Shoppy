package com.ShoppyCart.Service;


import java.text.DateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
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
		
		
		if (temp.getId()==0 ) {	
			 
			List<Rating> saveRating = ratingDao.saveRating(rating);
			return "rating save Secussfully"; 
		}
		else {
			rating.setId(temp.getId());
			List<Rating> updateRating = ratingDao.updateRating(rating);
			return "product Update Successfully";
		}
	}

	
	
	
//	@CachePut(value = "Rating", key = "#rating.id")
	public String updateRating(Rating rating) {
		String dateToStr = DateFormat.getInstance().format(new Date());  
		rating.setDate(dateToStr);
		List<Rating> updateRating = ratingDao.updateRating(rating);
		return "product Update Successfully";
	}
	
	
	
	
//	@Cacheable(value="Rating" ,key = "#productId")
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
	public Double calculateRatingAvarage(int productId) {
		List<Rating> lists = ratingDao.getllRatingByProductId(productId);
		Double collect = lists.stream().collect(Collectors.averagingDouble(Rating::getValue));
		System.out.println(productId +" this Product Avarage rating is : "+collect);
		return collect ;
	}




	public String deleteRatingByRatingId(int ratingId) {
					Rating ratingByRatingId = getRatingByRatingId(ratingId);
					
					ratingDao.deleteRatingByRatingId(ratingId);
					return "delete";
	}




	public Rating getRatingByRatingId(int ratingId) {
		
		return ratingDao.getRatingByRatingId(ratingId);
	}

}
