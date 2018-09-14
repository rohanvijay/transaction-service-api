package com.capone.transactions.service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capone.transactions.exceptions.BadRequestException;
import com.capone.transactions.model.Transaction;
import com.capone.transactions.utils.EncryptionDecryptionUtility;

@Service
public class ValidationService {

	@Autowired
	EncryptionDecryptionUtility encryptionDecryptionUtility;
	
	public void validateTransactionId(String transactionReferenceId){
		
		String transactionId=null;
		
		try{
			transactionId = encryptionDecryptionUtility.decrypt(transactionReferenceId);
		}
		catch(Exception ex){
			throw new BadRequestException();
		}
		if (transactionId == null || !(transactionId.matches("\\d{9}"))){
			throw new BadRequestException();
		}
	}
	
	public void validateAccountNumber(String accountReferenceId){
		
		String accountNumber=null;
		
		try{
			accountNumber = encryptionDecryptionUtility.decrypt(accountReferenceId);
		}
		catch(Exception ex){
			throw new BadRequestException();
		}
		if (accountNumber == null || !(accountNumber.matches("\\d{16}"))){
			throw new BadRequestException();
		}
	}

	public void validatePostedTransaction(Transaction transaction) {
		
		validateTransactionId(transaction.getTransactionReferenceId());
		
		validateAccountNumber(transaction.getCardReferenceId());
		
	}
	
}
