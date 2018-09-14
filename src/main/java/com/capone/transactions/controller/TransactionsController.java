package com.capone.transactions.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.capone.transactions.exceptions.BadRequestException;
import com.capone.transactions.model.Transaction;
import com.capone.transactions.model.TransactionSearchRequest;
import com.capone.transactions.service.TransactionsService;
import com.capone.transactions.service.ValidationService;
import com.capone.transactions.utils.EncryptionDecryptionUtility;

@RestController
public class TransactionsController {
	
	@Autowired
	TransactionsService transactionService;
	
	@Autowired
	ValidationService validationService;
	
	@Autowired
	EncryptionDecryptionUtility encryptionDecryptionUtility;
	
	@RequestMapping("/accounts/{accountReferenceId}/transactions")
	public List<Transaction> getTransactions(@PathVariable("accountReferenceId") String accountReferenceId, @RequestParam(value = "amount", required=false) String amount){
		
			validationService.validateAccountNumber(accountReferenceId);
			TransactionSearchRequest transactionRequest = new TransactionSearchRequest();
			transactionRequest.setAccountReferenceId(accountReferenceId);
			
			return transactionService.getTransactionBySearchCriteria(transactionRequest);
		
	}
	
	@RequestMapping("/accounts/{accountReferenceId}/transactions/{transactionReferenceId}")
	public Transaction getTransactionById(@PathVariable("accountReferenceId") String accountReferenceId, @PathVariable("transactionReferenceId") String transactionReferenceId){
		
	//	boolean isValidAccountNumber = validationService.validateAccountNumber(accountReferenceId);
	//	boolean isValidTransactionId = validationService.validateTransactionId(transactionReferenceId);
		
		boolean isValidAccountNumber = true;
		boolean isValidTransactionId = true;
		
		if(isValidAccountNumber && isValidTransactionId){
			return transactionService.getTransactionById(transactionReferenceId);
		}else{
			throw new BadRequestException();
		}
	}
	
	@RequestMapping("/postTransaction")
	public void postTransaction(@Valid @RequestBody Transaction transaction){
		
		validationService.validatePostedTransaction(transaction);
		
		transactionService.addTransaction(transaction.getCardReferenceId(),transaction);
		
	}
	
	@RequestMapping("/accounts/{accountNumber}/encrypt")
	public String encryptAccountNumber(@PathVariable("accountNumber") String accountNumber) throws Exception{
		
		return encryptionDecryptionUtility.encrypt(accountNumber);
	}
	
}
