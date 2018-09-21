package com.capone.transactions.config;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.capone.transactions.model.DetailedTransaction;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class DataSource {

	@Autowired
	AccountsStore accountsStore;
	
	@Autowired
	TransactionsStore transactionsStore;
	
	@Autowired
	AccountTransactionsMapping accountsTransactionsMapping;
	
	@SuppressWarnings("unchecked")
	@PostConstruct
	public void init() {
		List<String> accountsList = accountsStore.getAccountsList();
		
		accountsList.add("5424480021237823");
		accountsList.add("1234567891234502");
		accountsList.add("5424480021237822");
		accountsList.add("1234567891234504");
		accountsList.add("1234567891234505");
		accountsList.add("1234567891234506");
		accountsList.add("1234567891234507");
		accountsList.add("1234567891234508");
		accountsList.add("1234567891234509");
		accountsList.add("1234567891234510");
		accountsList.add("1234567891234511");
		//TypeReference<List<DetailedTransaction>> mapType = new TypeReference<List<DetailedTransaction>>() {};
		  
		InputStream inJson = DetailedTransaction[].class.getResourceAsStream("/Transactions.json");
		//JsonReader reader = new JsonReader(new InputStreamReader(is, "UTF-8"));
		//reader.setLenient(true);
		BufferedReader br = new BufferedReader(new InputStreamReader(inJson));
		ObjectMapper mapper=new ObjectMapper();
		  try {
			  
			  TypeReference<List<DetailedTransaction>> mapType = new TypeReference<List<DetailedTransaction>>() {};
			  List<DetailedTransaction> transations = mapper.readValue(inJson, mapType);
			  
			  for(DetailedTransaction detailedTransaction : transations) {
				  transactionsStore.getTransactions().put(detailedTransaction.getTransactionReferenceId(),
						  detailedTransaction);
			  }
		} catch (IOException e) {
			e.printStackTrace();
		}
		  List<String> transactionIds = new ArrayList<>();
		  transactionIds.add("501");
		  transactionIds.add("502");
		accountsTransactionsMapping.getTransactionsMap().put("5424480021237823",transactionIds);
		
		transactionIds = new ArrayList<>();
		transactionIds.add("503");
		accountsTransactionsMapping.getTransactionsMap().put("5424480021237822",transactionIds);
	}
}
