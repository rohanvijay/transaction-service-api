package com.capone.transactions.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.capone.transactions.model.DetailedTransaction;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class TransactionsStore {

	static {
		InputStream inJson = DetailedTransaction.class.getResourceAsStream("/Transactions.json");
		ObjectMapper mapper=new ObjectMapper();
		DetailedTransaction transation=null;
		  try {
			  transation = mapper.readValue(inJson, DetailedTransaction.class);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		  System.out.println("Prepolulated data: "+ transation.getCardExpirationDate());
		  
	}
	//Map that will store plain text TrasactionId as key and corresponding Transaction as value.
	private Map<String, DetailedTransaction> transactionsMap = new HashMap<String, DetailedTransaction>();
	
	public Map<String, DetailedTransaction> getTransactions(){
		return transactionsMap;
	}

}
