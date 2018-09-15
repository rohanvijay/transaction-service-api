package com.capone.transactions.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.capone.transactions.config.AccountTransactionsMapping;
import com.capone.transactions.config.TransactionsStore;
import com.capone.transactions.dao.TransactionDAO;
import com.capone.transactions.model.Transaction;
import com.capone.transactions.model.TransactionSearchRequest;

public class TransactionDAOImpl implements TransactionDAO {

	@Autowired
	TransactionsStore transactionsStore;

	@Autowired
	AccountTransactionsMapping accountTransactionsMapping;

	@Override
	public List<Transaction> getTransactionsList(String accountNumber) {
		List<Transaction> transactionList = new ArrayList<Transaction>();

		Map<String, List<String>> accountTransactions = accountTransactionsMapping
				.getTransactionsMap();

		List<String> listOfTransactionsOnAccount = accountTransactions
				.get(accountNumber);

		Map<String, Transaction> transactionsMap = transactionsStore
				.getTransactions();

		if (!listOfTransactionsOnAccount.isEmpty()) {
			for (Map.Entry<String, Transaction> entry : transactionsMap
					.entrySet()) {

				for (int i = 0; i < listOfTransactionsOnAccount.size()
						|| i > 24; i++) {

					if (listOfTransactionsOnAccount.get(i) == entry.getKey()) {
						transactionList.add(entry.getValue());
					}
				}
			}
		}

		// insert sorting logic

		return transactionList;
	}

	@Override
	public Transaction getTransaction(String transactionId) {
		Map<String, Transaction> transactionsMap = transactionsStore
				.getTransactions();
		Transaction transaction = null;

		for (Map.Entry<String, Transaction> entry : transactionsMap.entrySet()) {

			if (transactionId.equals(entry.getKey())) {
				transaction = entry.getValue();
			}
		}
		return transaction;
	}

	@Override
	public List<Transaction> getTransactionsList(
			TransactionSearchRequest transactionRequest) {
		List<Transaction> transactionList = new ArrayList<Transaction>();

		Map<String, List<String>> accountTransactions = accountTransactionsMapping
				.getTransactionsMap();

		List<String> listOfTransactionsOnAccount = accountTransactions
				.get(transactionRequest.getAccountNumber());

		Map<String, Transaction> transactionsMap = transactionsStore
				.getTransactions();

		for (int i = 0; i < listOfTransactionsOnAccount.size(); i++) {
			transactionList.add(transactionsMap.get(listOfTransactionsOnAccount
					.get(i)));
		}

		// insert sorting logic

		return transactionList;
	}

	@Override
	public void addToTransactionsList(String accountNumber,
			Transaction newTransaction, String transactionId) {
		Map<String, Transaction> transactionsMap = transactionsStore
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
