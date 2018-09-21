package com.capone.transactions.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.capone.transactions.model.DetailedTransaction;


@Component
public class TransactionsStore {

	//Map that will store plain text TrasactionId as key and corresponding Transaction as value.
	private Map<String, DetailedTransaction> transactionsMap = new HashMap<String, DetailedTransaction>();
	
	public Map<String, DetailedTransaction> getTransactions(){
		return transactionsMap;
	}

}
