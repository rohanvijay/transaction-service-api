package com.capone.transactions.utils;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.springframework.stereotype.Service;

@Service
public class TransactionsUtility {

	public String generateKey(){
		Random rand = new Random();
		int num = rand.nextInt(900) + 100;
		
		 Date dNow = new Date();
	        SimpleDateFormat ft = new SimpleDateFormat("yyMMddhhmmssMs");
	        String datetime = ft.format(dNow);
		
		return datetime+num;
	}
}
