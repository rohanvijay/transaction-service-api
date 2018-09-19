package com.capone.transactions.dao;

import java.util.List;

import com.capone.transactions.model.DetailedTransaction;
import com.capone.transactions.model.Transaction;
import com.capone.transactions.model.TransactionSearchRequest;


public interface TransactionDAO {

	DetailedTransaction getTransaction(String transactionId);
	
	List<DetailedTransaction> getTransactionsList(TransactionSearchRequest transactionRequest);

	void addToTransactionsList(String accountNumber, DetailedTransaction newTransaction, String transactionId);
	
}
