package com.capone.transactions.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capone.transactions.controller.TransactionsController;
import com.capone.transactions.dao.TransactionDAO;
import com.capone.transactions.exceptions.BadRequestException;
import com.capone.transactions.exceptions.InternalServerException;
import com.capone.transactions.model.DetailedTransaction;
import com.capone.transactions.model.Transaction;
import com.capone.transactions.model.TransactionSearchRequest;
import com.capone.transactions.utils.EncryptionDecryptionUtility;
import com.capone.transactions.utils.TransactionsUtility;

@Service
public class TransactionService {

	@Autowired
	TransactionDAO transactionDAO;

	@Autowired
	EncryptionDecryptionUtility encryptionDecryptionUtility;

	@Autowired
	TransactionsUtility transactionsUtility;

	Logger logger = LoggerFactory.getLogger(TransactionService.class);
	
	public DetailedTransaction getTransactionById(String transactionReferenceId) {

		String transactionId = null;

		try {
			transactionId = encryptionDecryptionUtility.decrypt(transactionReferenceId);
		} catch (Exception ex) {
			logger.error("Exception while decrypting Account Id: "+ transactionReferenceId);
			logger.error("Exception: "+ ex.getMessage());
			throw new BadRequestException("Invalid Transaction Id");
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
			logger.error("Exception while decrypting Account Id: "+ transactionRequest.getAccountReferenceId());
			logger.error("Exception: "+ ex.getMessage());
			throw new BadRequestException("Invalid Account Number");
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
			logger.error("Exception while decrypting Account Id: "+ accountReferenceId);
			logger.error("Exception: "+ ex.getMessage());
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
		
		Transaction transaction = null;
		
		if(detailedTransactionFromBackend!=null){
		
			transaction = new Transaction();
			transaction.setDebitCreditCode(detailedTransactionFromBackend.getDebitCreditCode());
			transaction.setMerchantName(detailedTransactionFromBackend.getMerchantDetails().getMerchantName());
			transaction.setTransactionAmount(detailedTransactionFromBackend.getTransactionAmount());
			transaction.setTransactionDate(detailedTransactionFromBackend.getTransactionDate());
			
			try{
			transaction.setCardReferenceId(encryptionDecryptionUtility.encrypt(detailedTransactionFromBackend.getCardReferenceId()));
			transaction.setTransactionReferenceId(encryptionDecryptionUtility.encrypt(detailedTransactionFromBackend.getTransactionReferenceId()));
			}catch (Exception e){
				logger.error(e.getMessage());
				throw new InternalServerException();
			}
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
				logger.error(e.getMessage());
				throw new InternalServerException();
			}
		}
		return responseTransaction;
	}

}
