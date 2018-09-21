package com.capone.transactions.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capone.transactions.dao.impl.TransactionDAOImpl;
import com.capone.transactions.exceptions.BadRequestException;
import com.capone.transactions.model.DetailedTransaction;
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

	public DetailedTransaction getTransactionById(String transactionReferenceId) {

		String transactionId = null;

		try {
			transactionId = encryptionDecryptionUtility.decrypt(transactionReferenceId);
		} catch (Exception ex) {
			throw new BadRequestException();
		}

		DetailedTransaction detailedTransactionFromBackend = transactionDAO.getTransaction(transactionId);
		
		return createResponseDetailedTransaction(detailedTransactionFromBackend);
	}

	public List<Transaction> getTransactionBySearchCriteria(
			TransactionSearchRequest transactionRequest) {

		String accountNumber = null;
		List<Transaction> compactTransactionList = new ArrayList();
		try {
			accountNumber = encryptionDecryptionUtility.decrypt(transactionRequest.getAccountReferenceId());
		} catch (Exception ex) {
			throw new BadRequestException();
		}
		transactionRequest.setAccountNumber(accountNumber);
		
		List<DetailedTransaction> detailedTransactionListFromBackend = transactionDAO.getTransactionsList(transactionRequest);
		
		for(int i=0; i<detailedTransactionListFromBackend.size();i++){
			compactTransactionList.add(createResponseCompactTransaction(detailedTransactionListFromBackend.get(i)));
		}
		
		return compactTransactionList;
	}

	public void addTransaction(String accountReferenceId,
			DetailedTransaction transaction) {

		String accountNumber = null;

		try {
			accountNumber = encryptionDecryptionUtility
					.decrypt(accountReferenceId);
		} catch (Exception ex) {
			throw new BadRequestException();
		}

		String transactionId = transactionsUtility.generateKey();

		transaction.setCardReferenceId(accountNumber);
		transaction.setTransactionReferenceId(transactionId);

		transactionDAO.addToTransactionsList(accountNumber, transaction,
				transactionId);
	}
	
	private Transaction createResponseCompactTransaction(
			DetailedTransaction detailedTransactionFromBackend) {
		
		Transaction transaction = new Transaction();
		
		transaction.setDebitCreditCode(detailedTransactionFromBackend.getDebitCreditCode());
		transaction.setMerchantName(detailedTransactionFromBackend.getMerchantDetails().getMerchantName());
		transaction.setTransactionAmount(detailedTransactionFromBackend.getTransactionAmount());
		transaction.setTransactionDate(detailedTransactionFromBackend.getTransactionDate());
		
		try{
		transaction.setCardReferenceId(encryptionDecryptionUtility.encrypt(detailedTransactionFromBackend.getCardReferenceId()));
		transaction.setTransactionReferenceId(encryptionDecryptionUtility.encrypt(detailedTransactionFromBackend.getTransactionReferenceId()));
		}catch (Exception e){
			e.printStackTrace();
		}
		return transaction;
	}

	private DetailedTransaction createResponseDetailedTransaction(
			DetailedTransaction detailedTransactionFromBackend) {
		
		DetailedTransaction responseTransaction=null;
		
		if(detailedTransactionFromBackend!=null){
		responseTransaction= new DetailedTransaction();
		
		responseTransaction.setCardExpirationDate(detailedTransactionFromBackend.getCardExpirationDate());
		responseTransaction.setCurrencyCode(detailedTransactionFromBackend.getCurrencyCode());
		responseTransaction.setDebitCreditCode(detailedTransactionFromBackend.getDebitCreditCode());
		responseTransaction.setPointOfSaleCardUsageCode(detailedTransactionFromBackend.getPointOfSaleCardUsageCode());
		responseTransaction.setPointOfSaleCardPresenceDescription(detailedTransactionFromBackend.getPointOfSaleCardPresenceDescription());
		responseTransaction.setPointOfSaleCardPresenceCode(detailedTransactionFromBackend.getPointOfSaleCardPresenceCode());
		responseTransaction.setPostedDate(detailedTransactionFromBackend.getPostedDate());
		responseTransaction.setTransactionAmount(detailedTransactionFromBackend.getTransactionAmount());
		responseTransaction.setTransactionDate(detailedTransactionFromBackend.getTransactionDate());
		responseTransaction.setTransactionDescription(detailedTransactionFromBackend.getTransactionDescription());
		responseTransaction.setMerchantDetails(detailedTransactionFromBackend.getMerchantDetails());
		try{
			responseTransaction.setCardReferenceId(encryptionDecryptionUtility.encrypt(detailedTransactionFromBackend.getCardReferenceId()));
			responseTransaction.setTransactionReferenceId(encryptionDecryptionUtility.encrypt(detailedTransactionFromBackend.getTransactionReferenceId()));
		}catch (Exception e){
			e.printStackTrace();
		}
		}
		return responseTransaction;
	}

}
