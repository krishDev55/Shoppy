package com.ShoppyCart.commonUse;

import java.util.Random;

import org.springframework.stereotype.Service;

@Service
public class Common {

	public static int genrateId() {
		Random random= new Random();
		int nextInt = random.nextInt(10000, 99999);
		return nextInt;
	}
	public static String genrateOrderId() {
		Random random= new Random();
		int nextInt = random.nextInt(10000, 99999);	
		return "ord-"+nextInt;
	}
	public static String genrateVendorId() {
		Random random= new Random();
		int nextInt = random.nextInt(10000, 99999);	
		return "v-"+nextInt;
	}
	
	
}
