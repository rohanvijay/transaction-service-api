package com.capone.transactions.dao;

import java.util.List;

import com.capone.transactions.model.Transaction;
import com.capone.transactions.model.TransactionSearchRequest;


public interface TransactionDAO {

	Transaction getTransaction(String transactionId);
	
	List<Transaction> getTransactionsList(TransactionSearchRequest transactionRequest);

	void addToTransactionsList(String accountNumber, Transaction newTransaction, String transactionId);
	
}
