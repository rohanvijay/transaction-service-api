package com.capone.transactions.service;

import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capone.transactions.exceptions.BadRequestException;
import com.capone.transactions.model.DetailedTransaction;
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
		if (transactionId == null || !(transactionId.matches("\\d{18}"))){
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

	public void validatePostedTransaction(DetailedTransaction transaction) {
				
		validateAccountNumber(transaction.getCardReferenceId());
		boolean validPostalCode = false;
		
		if(transaction.getDebitCreditCode().equalsIgnoreCase("CR")){
			if(transaction.getPointOfSalePresenceCode() == null || transaction.getPointOfSalePresenceDescription() == null){
				throw new BadRequestException();
			}
		}
		
		if(transaction.getMerchantDetails().getMerchantAddress().getCountry().equalsIgnoreCase("US")){
			validPostalCode = Pattern.matches("^\\d{5}(?:[-\\s]\\d{4})?$", transaction.getMerchantDetails().getMerchantAddress().getPostalCode());
		}
		
		if(transaction.getMerchantDetails().getMerchantAddress().getCountry().equalsIgnoreCase("CA")){
			validPostalCode = Pattern.matches("^(?!.*[DFIOQU])[A-VXY][0-9][A-Z] ?[0-9][A-Z][0-9]$", transaction.getMerchantDetails().getMerchantAddress().getPostalCode());
		}
		
		if(!validPostalCode){
			throw new BadRequestException();
		}
		
	}

	public void validateAmount(String amount) {
		if(amount != null){
			try{
			Double.parseDouble(amount);
			}catch (Exception ex){
				throw new BadRequestException();
			}
		}
		
		
	}
	
}
