package com.capone.transactions.config;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

//This class will map Account Numbers to all the Transactions IDs on that account

@Component
public class AccountTransactionsMapping {

	private Map<String, List<String>> accountTransactionsMap = new HashMap<>();

	public Map<String, List<String>> getTransactionsMap() {
		return accountTransactionsMap;
	}

	public void setTransactionsMap(Map<String, List<String>> transactionsMap) {
		this.accountTransactionsMap = transactionsMap;
	}
}
