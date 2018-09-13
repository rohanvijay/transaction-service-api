package com.capone.transactions.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class AccountsStore {

	public AccountsStore(){
		accountsList.add("1234567891234501");
		accountsList.add("1234567891234502");
		accountsList.add("1234567891234503");
		accountsList.add("1234567891234504");
		accountsList.add("1234567891234505");
		accountsList.add("1234567891234506");
		accountsList.add("1234567891234507");
		accountsList.add("1234567891234508");
		accountsList.add("1234567891234509");
		accountsList.add("1234567891234510");
		accountsList.add("1234567891234511");
	}
	
	private List<String> accountsList = new ArrayList<>();

	public List<String> getAccountsList() {
		return accountsList;
	}

}
