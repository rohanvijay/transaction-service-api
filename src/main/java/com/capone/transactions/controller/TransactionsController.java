package com.capone.transactions.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.capone.transactions.exceptions.NoContentException;
import com.capone.transactions.model.DetailedTransaction;
import com.capone.transactions.model.Transaction;
import com.capone.transactions.model.TransactionSearchRequest;
import com.capone.transactions.service.TransactionService;
import com.capone.transactions.service.ValidationService;
import com.capone.transactions.utils.EncryptionDecryptionUtility;

@RestController
public class TransactionsController {
	
	@Autowired
	TransactionService transactionService;
	
	@Autowired
	ValidationService validationService;
	
	@Autowired
	EncryptionDecryptionUtility encryptionDecryptionUtility;
	
	Logger logger = LoggerFactory.getLogger(TransactionsController.class);
	
	@RequestMapping("/accounts/{accountReferenceId}/transactions")
	public List<Transaction> getTransactions(@PathVariable("accountReferenceId") String accountReferenceId, @RequestParam(value = "amount", required=false) String amount){
		
			logger.debug("Request received to get all transactions");
			logger.debug("Account Reference id |"+ accountReferenceId);
			logger.debug("Amount |"+ amount);
		
			validationService.validateAccountNumber(accountReferenceId);
			validationService.validateAmount(amount);
			TransactionSearchRequest transactionRequest = new TransactionSearchRequest();
			transactionRequest.setAccountReferenceId(accountReferenceId);
			transactionRequest.setAmount(amount);
			List<Transaction> transactionList = transactionService.getTransactionBySearchCriteria(transactionRequest);
			
			if(transactionList==null || transactionList.size()==0){
				throw new NoContentException();
			}else{
				return transactionList;
			}
			
	}
	
	@RequestMapping("/accounts/{accountReferenceId}/transactions/{transactionReferenceId}")
	public DetailedTransaction getTransactionById(@PathVariable("accountReferenceId") String accountReferenceId, @PathVariable("transactionReferenceId") String transactionReferenceId){
		
		logger.debug("Request received to get details of a transaction");
		logger.debug("Account Reference id |"+ accountReferenceId);
		logger.debug("Transaction Reference id |"+ transactionReferenceId);
		
		validationService.validateAccountNumber(accountReferenceId);
		validationService.validateTransactionId(transactionReferenceId);
	
		DetailedTransaction transaction=  transactionService.getTransactionById(transactionReferenceId);
	
		if(transaction!=null){
			return transaction;
		}
		else{
			throw new NoContentException();
		}
	}
	
	@RequestMapping("/postTransaction")
	public void postTransaction(@Valid @RequestBody DetailedTransaction transaction){
		
		logger.debug("Request received to post of a transaction");
		
		validationService.validatePostedTransaction(transaction);
		transactionService.addTransaction(transaction.getCardReferenceId(),transaction);
	}
	
	@RequestMapping("/accounts/{accountNumber}/encrypt")
	public String encryptAccountNumber(@PathVariable("accountNumber") String accountNumber) throws Exception{
		
		return encryptionDecryptionUtility.encrypt(accountNumber);
	}
	
}
