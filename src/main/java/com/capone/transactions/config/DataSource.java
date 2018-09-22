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
		
		accountsList.add("5424480021237821");
		accountsList.add("1234567891234501");
		  
		InputStream inJson = DetailedTransaction[].class.getResourceAsStream("/Transactions.json");
		
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
		  transactionIds.add("503");
		accountsTransactionsMapping.getTransactionsMap().put("5424480021237821",transactionIds);
		
		transactionIds = new ArrayList<>();
		transactionIds.add("504");
		transactionIds.add("505");
		transactionIds.add("506");
		accountsTransactionsMapping.getTransactionsMap().put("1234567891234501",transactionIds);
	}
}
