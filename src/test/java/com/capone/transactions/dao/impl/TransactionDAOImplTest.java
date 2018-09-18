package com.capone.transactions.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import com.capone.transactions.config.AccountTransactionsMapping;
import com.capone.transactions.config.TransactionsStore;
import com.capone.transactions.model.MerchantAddress;
import com.capone.transactions.model.MerchantDetails;
import com.capone.transactions.model.Transaction;
import com.capone.transactions.model.TransactionSearchRequest;

@RunWith(MockitoJUnitRunner.class)
public class TransactionDAOImplTest {

	@Spy
	TransactionsStore transactionsStore;
	
	@InjectMocks
	TransactionDAOImpl transactionDAO;

	@Spy
	AccountTransactionsMapping accountTransactionsMapping;

	
	
	@Before
	public void init(){
		
		Transaction transaction1 = new Transaction();
		
		transaction1.setCardExpirationDate("0321");
		transaction1.setCardReferenceId("12345678912345");
		transaction1.setCurrencyCode("840");
		transaction1.setDebitCreditCode("CR");
		transaction1.setPointOfSaleCardUsageCode("Chip");
		transaction1.setPointOfSalePresenceDescription("Card holder present at time of transaction");
		transaction1.setPointOfSalePresenceCode("12");
		transaction1.setPostedDate("20180210");
		transaction1.setTransactionAmount("34.12");
		transaction1.setTransactionDate("20180206");
		transaction1.setTransactionDescription("Purchase");
		transaction1.setTransactionReferenceId("123");
		
		MerchantDetails merchant = new MerchantDetails();
		merchant.setCategoryCode("15");
		merchant.setCategory("Dining");
		merchant.setMerchantName("Abc Restaraunt");
		merchant.setStoreNumber("304983459");
		
		MerchantAddress address = new MerchantAddress();
		address.setAddressLine1("Address Line 1");
		address.setAddressLine2("Address Line 2");
		address.setCity("Chicago");
		address.setCountry("US");
		address.setPostalCode("43032");
		address.setState("IL");
		
		merchant.setMerchantAddress(address);
		transaction1.setMerchantDetails(merchant);
		transactionsStore.getTransactions().put("123", transaction1);
		
		Transaction transaction2 = new Transaction();
		
		transaction2.setCardExpirationDate("0415");
		transaction2.setCardReferenceId("12345678954321");
		transaction2.setCurrencyCode("124");
		transaction2.setDebitCreditCode("DB");
		transaction2.setPostedDate("20180312");
		transaction2.setTransactionAmount("12.09");
		transaction2.setTransactionDate("20180308");
		transaction2.setTransactionDescription("Refund");
		transaction2.setTransactionReferenceId("234");
		
		transactionsStore.getTransactions().put("234", transaction2);
		
		List<String> transactionsList = new ArrayList();
		transactionsList.add("123");
		transactionsList.add("234");

		accountTransactionsMapping.getTransactionsMap().put("12345678912345", transactionsList);
		//transactionsMap.put("12345678954321", transactionsList);
		
	}
	
	@Test
	public void test() {
		Transaction transaction = transactionDAO.getTransaction("123");
		Assert.assertTrue("Transaction is null",transaction!=null);
		Assert.assertTrue("Incorrect Transaction retrieved", transaction.getTransactionReferenceId().equals("123"));
	}
	
	@Test
	public void test2() {
		TransactionSearchRequest transactionSearchRequest = new TransactionSearchRequest();
		transactionSearchRequest.setAmount("34.11");
		transactionSearchRequest.setAccountNumber("12345678912345");
		
		List<Transaction> transactions = transactionDAO.getTransactionsList(transactionSearchRequest);
		Assert.assertTrue("Only One Transaction Exists",transactions.size()==1);
	}

}
