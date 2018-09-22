package com.capone.transactions.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class AccountsStore {
	
	private List<String> accountsList = new ArrayList<>();

	public List<String> getAccountsList() {
		return accountsList;
	}

}
