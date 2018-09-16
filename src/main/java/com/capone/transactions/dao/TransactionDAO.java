package com.capone.transactions.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.capone.transactions.model.Transaction;
import com.capone.transactions.model.TransactionSearchRequest;

@Component
public interface TransactionDAO {

	Transaction getTransaction(String transactionId);
	
	List<Transaction> getTransactionsList(TransactionSearchRequest transactionRequest);

	void addToTransactionsList(String accountNumber, Transaction newTransaction, String transactionId);
	
}
