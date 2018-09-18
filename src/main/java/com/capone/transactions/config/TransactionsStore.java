package com.capone.transactions.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.capone.transactions.model.Transaction;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class TransactionsStore {

	static {
		InputStream inJson = Transaction.class.getResourceAsStream("/Transactions.json");
		ObjectMapper mapper=new ObjectMapper();
		Transaction transation=null;
		  try {
			  transation = mapper.readValue(inJson, Transaction.class);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		  System.out.println("Prepolulated data: "+transation.getBalanceSegmentCode());
		  
	}
	//Map that will store plain text TrasactionId as key and corresponding Transaction as value.
	private Map<String, Transaction> transactionsMap = new HashMap<String, Transaction>();
	
	public Map<String, Transaction> getTransactions(){
		return transactionsMap;
	}

}
