package com.ShoppyCart.commonUse;

import java.util.Random;

import org.springframework.stereotype.Service;

@Service
public class RazorPayCommon {

	public String gen_receiptId() {
		Random random= new Random();
		int nextInt = random.nextInt(10000, 99999);	
		return "tnx_"+nextInt;
	} 
}
