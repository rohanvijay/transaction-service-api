package com.capone.transactions.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capone.transactions.model.Transaction;

@Service
public class TransactionsUtility {

	@Autowired
	EncryptionDecryptionUtility encryptionDecryptionUtility;
	
	public String generateKey(){
		Random rand = new Random();
		int num = rand.nextInt(900) + 100;
		
		 Date dNow = new Date();
	        SimpleDateFormat ft = new SimpleDateFormat("yyMMddhhmmssMs");
	        String datetime = ft.format(dNow);
		
		return datetime+num;
	}
	
	public Transaction encryptPCIData(Transaction transaction){
		
		try {
			transaction.setCardReferenceId(encryptionDecryptionUtility.encrypt(transaction.getCardReferenceId()));
			transaction.setTransactionReferenceId(encryptionDecryptionUtility.encrypt(transaction.getTransactionReferenceId()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return transaction;
	}
}
