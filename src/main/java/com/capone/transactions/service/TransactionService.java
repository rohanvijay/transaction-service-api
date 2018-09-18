package com.capone.transactions.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capone.transactions.dao.impl.TransactionDAOImpl;
import com.capone.transactions.exceptions.BadRequestException;
import com.capone.transactions.model.Transaction;
import com.capone.transactions.model.TransactionSearchRequest;
import com.capone.transactions.utils.EncryptionDecryptionUtility;
import com.capone.transactions.utils.TransactionsUtility;

@Service
public class TransactionService {

	@Autowired
	TransactionDAOImpl transactionDAO;

	@Autowired
	EncryptionDecryptionUtility encryptionDecryptionUtility;

	@Autowired
	TransactionsUtility transactionsUtility;

	public Transaction getTransactionById(String transactionReferenceId) {

		String transactionId = null;

		try {
			transactionId = encryptionDecryptionUtility
					.decrypt(transactionReferenceId);
		} catch (Exception ex) {
			throw new BadRequestException();
		}

		return transactionDAO.getTransaction(transactionId);
	}

	public List<Transaction> getTransactionBySearchCriteria(
			TransactionSearchRequest transactionRequest) {

		String accountNumber = null;

		try {
			accountNumber = encryptionDecryptionUtility
					.decrypt(transactionRequest.getAccountReferenceId());
		} catch (Exception ex) {

		}

		transactionRequest.setAccountNumber(accountNumber);
		return transactionDAO.getTransactionsList(transactionRequest);
	}

	public void addTransaction(String accountReferenceId,
			Transaction transaction) {

		String accountNumber = null;

		try {
			accountNumber = encryptionDecryptionUtility
					.decrypt(accountReferenceId);
		} catch (Exception ex) {

		}

		String transactionId = transactionsUtility.generateKey();

		transaction.setTransactionReferenceId(transactionId);

		transactionDAO.addToTransactionsList(accountNumber, transaction,
				transactionId);
	}

}
