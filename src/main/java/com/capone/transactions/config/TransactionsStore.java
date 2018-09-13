package com.capone.transactions.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.capone.transactions.model.Transaction;

@Component
public class TransactionsStore {

	//Map that will store plain text TrasactionId as key and corresponding Transaction as value.
	private Map<String, Transaction> transactionsMap = new HashMap<String, Transaction>();
	
	public Map<String, Transaction> getTransactions(){
		return transactionsMap;
	}

}
