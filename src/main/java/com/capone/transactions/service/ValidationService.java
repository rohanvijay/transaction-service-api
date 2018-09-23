package com.capone.transactions.service;

import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capone.transactions.controller.TransactionsController;
import com.capone.transactions.exceptions.BadRequestException;
import com.capone.transactions.model.DetailedTransaction;
import com.capone.transactions.utils.EncryptionDecryptionUtility;

@Service
public class ValidationService {

	@Autowired
	EncryptionDecryptionUtility encryptionDecryptionUtility;
	
	Logger logger = LoggerFactory.getLogger(ValidationService.class);
	
	public void validateTransactionId(String transactionReferenceId){
		
		String transactionId=null;
		
		try{
			transactionId = encryptionDecryptionUtility.decrypt(transactionReferenceId);
		}
		catch(Exception ex){
			logger.error("Invalid Transaction Reference Id | "+ transactionReferenceId);
			throw new BadRequestException("Invalid Transaction Number");
		}
		if (transactionId == null || !(transactionId.matches("\\d{18}"))){
			logger.error("Invalid Transaction Reference Id | "+ transactionReferenceId);
			throw new BadRequestException("Invalid Transaction Number");
		}
	}
	
	public void validateAccountNumber(String accountReferenceId){
		
		String accountNumber=null;
		
		try{
			accountNumber = encryptionDecryptionUtility.decrypt(accountReferenceId);
		}
		catch(Exception ex){
			logger.error("Invalid Account Reference Id | "+ accountReferenceId);
			throw new BadRequestException("Invalid Account Number");
		}
		if (accountNumber == null || !(accountNumber.matches("\\d{16}"))){
			logger.error("Invalid Account Reference Id | "+ accountReferenceId);
			throw new BadRequestException("Invalid Account Number");
		}
	}

	public void validatePostedTransaction(DetailedTransaction transaction) {
				
		validateAccountNumber(transaction.getCardReferenceId());
		String postalCode=transaction.getMerchantDetails().getMerchantAddress().getPostalCode();
		boolean validPostalCode = false;
		
		if(transaction.getDebitCreditCode().equalsIgnoreCase("CR")){
			if(transaction.getPointOfSaleCardPresenceCode() == null || transaction.getPointOfSaleCardPresenceDescription() == null){
				logger.error("Invalid Point of Sale Card Presence Code");
				throw new BadRequestException("Point of Sale Card Presence Code is invalid");
			}
		}
		
		if(transaction.getMerchantDetails().getMerchantAddress().getCountry().equalsIgnoreCase("US")){
			validPostalCode = Pattern.matches("^\\d{5}(?:[-\\s]\\d{4})?$", postalCode);
		}
		
		if(transaction.getMerchantDetails().getMerchantAddress().getCountry().equalsIgnoreCase("CA")){
			validPostalCode = Pattern.matches("^(?!.*[DFIOQU])[A-VXY][0-9][A-Z] ?[0-9][A-Z][0-9]$", postalCode);
		}
		
		if(!validPostalCode){
			logger.error("Invalid Postal Code: "+ postalCode + " for "+ transaction.getMerchantDetails().getMerchantAddress().getCountry());
			throw new BadRequestException("Invalid postal code for "+ transaction.getMerchantDetails().getMerchantAddress().getCountry());
		}
		
	}

	public void validateAmount(String amount) {
		if(amount != null){
			try{
			Double.parseDouble(amount);
			}catch (Exception ex){
				logger.error("Invalid Amount: "+ amount);
				throw new BadRequestException("Invalid amount passed in the URL parameter request");
			}
		}
	}
	
}
