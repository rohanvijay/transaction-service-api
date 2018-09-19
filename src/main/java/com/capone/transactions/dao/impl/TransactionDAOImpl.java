package com.capone.transactions.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.capone.transactions.config.AccountTransactionsMapping;
import com.capone.transactions.config.TransactionsStore;
import com.capone.transactions.dao.TransactionDAO;
import com.capone.transactions.model.DetailedTransaction;
import com.capone.transactions.model.Transaction;
import com.capone.transactions.model.TransactionSearchRequest;
import com.capone.transactions.utils.TransactionsUtility;

@Component("transactionDAO")
public class TransactionDAOImpl implements TransactionDAO {

	@Autowired
	TransactionsStore transactionsStore;

	@Autowired
	AccountTransactionsMapping accountTransactionsMapping;

	@Autowired
	TransactionsUtility transactionsUtility;

	@Override
	public DetailedTransaction getTransaction(String transactionId) {
		Map<String, DetailedTransaction> transactionsMap = transactionsStore
				.getTransactions();
		DetailedTransaction transaction = null;

		for (Map.Entry<String, DetailedTransaction> entry : transactionsMap.entrySet()) {

			if (transactionId.equals(entry.getKey())) {
				transaction = entry.getValue();
			}
		}
		return transaction;
	}

	@Override
	public List<DetailedTransaction> getTransactionsList(
			TransactionSearchRequest transactionRequest) {
		List<DetailedTransaction> transactionList = new ArrayList();

		Map<String, List<String>> accountTransactions = accountTransactionsMapping
				.getTransactionsMap();

		List<String> listOfTransactionsOnAccount = accountTransactions
				.get(transactionRequest.getAccountNumber());

		Map<String, DetailedTransaction> transactionsMap = transactionsStore
				.getTransactions();

		// Iterating from the last to get the latest transactions first.
		if (listOfTransactionsOnAccount != null) {
			for (int i = listOfTransactionsOnAccount.size() - 1; i >= 0; i--) {

				DetailedTransaction detailedTransaction = transactionsMap
						.get(listOfTransactionsOnAccount.get(i));

				if (transactionRequest.getAmount() != null){
					if(Double.parseDouble(detailedTransaction.getTransactionAmount()) >= 
							Double.parseDouble(transactionRequest.getAmount())) {

						transactionList.add(detailedTransaction);
					}
				
				} else {
					transactionList.add(detailedTransaction);
				}
			}
		}
		return transactionList;
	}

	@Override
	public void addToTransactionsList(String accountNumber,
			DetailedTransaction newTransaction, String transactionId) {
		Map<String, DetailedTransaction> transactionsMap = transactionsStore
				.getTransactions();

		transactionsMap.put(transactionId, newTransaction);

		Map<String, List<String>> accountTransactions = accountTransactionsMapping
				.getTransactionsMap();

		if (accountTransactions.containsKey(accountNumber)) {
			accountTransactions.get(accountNumber).add(transactionId);
		} else {
			List<String> transactionIDList = new ArrayList<String>();
			transactionIDList.add(transactionId);
			accountTransactions.put(accountNumber, transactionIDList);
		}
	}

}
