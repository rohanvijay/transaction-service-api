package com.capone.transactions.config;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

//This class will map Account Numbers to all the Transactions IDs on that account

@Component
public class AccountTransactionsMapping {

	private Map<String, List<String>> transactionsMap = new HashMap<>();

	public Map<String, List<String>> getTransactionsMap() {
		return transactionsMap;
	}

	public void setTransactionsMap(Map<String, List<String>> transactionsMap) {
		this.transactionsMap = transactionsMap;
	}
}
