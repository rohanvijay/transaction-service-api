package com.capone.transactions.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capone.transactions.model.TransactionSearchRequest;
import com.capone.transactions.utils.EncryptionDecryptionUtility;

@Service
public class ValidationService {

	@Autowired
	EncryptionDecryptionUtility encryptionDecryptionUtility;
	
	public Boolean validateTransactionId(String transactionReferenceId){
		
		String transactionId=null;
		
		try{
			transactionId = encryptionDecryptionUtility.decrypt(transactionReferenceId);
		}
		catch(Exception ex){
			
		}
		
		if (transactionId == null){
			
			
		}
		
		if (transactionId.length() !=9 ){
			
			
		}
		
		
		return true;
	}
	
	public Boolean validateAccountNumber(String accountReferenceId){
		
//		String accountNumber=null;
//		
//		//Decrypt Account Number
//		try{
//			accountNumber = encryptionDecryptionUtility.decrypt(accountReferenceId);
//		}
//		catch(Exception ex){
//				
//		}
//			
//		if (accountNumber == null){
//				
//				
//		}
//			
//		if (accountNumber.length() != 16){
//				
//				
//		}
//			
//		if (true){
//			
//		}
			
			return true;
	
	}
	
	public Boolean valiateAmount(String amount){

		if(amount !=null){
			BigDecimal amountBigDecimal=null;
			
			try {
				amountBigDecimal = new BigDecimal(amount);
			} catch (Exception e) {
				
			}
		}	
		return true;
	}
	
}
