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
import com.capone.transactions.model.DetailedTransaction;
import com.capone.transactions.model.MerchantAddress;
import com.capone.transactions.model.MerchantDetails;
import com.capone.transactions.model.TransactionSearchRequest;
import com.capone.transactions.utils.EncryptionDecryptionUtility;
import com.capone.transactions.utils.TransactionsUtility;

@RunWith(MockitoJUnitRunner.class)
public class TransactionDAOImplTest {

	@Spy
	TransactionsStore transactionsStore;
	
	@InjectMocks
	TransactionDAOImpl transactionDAO;

	@Spy
	AccountTransactionsMapping accountTransactionsMapping;

	@Spy
	TransactionsUtility transactionUtility;
	
	@Spy
	EncryptionDecryptionUtility encryptionDecryptionUtility;
	
	@Before
	public void init(){
		
		populateCard1Transactions();
		populateCard2Transactions();
		
	}

	private void populateCard1Transactions() {
		DetailedTransaction transaction1 = new DetailedTransaction();
		
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
		
		DetailedTransaction transaction2 = new DetailedTransaction();
		transaction2.setCardExpirationDate("0415");
		transaction2.setCardReferenceId("12345678912345");
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
	}

	private void populateCard2Transactions() {
		
		DetailedTransaction transaction1 = new DetailedTransaction();
		
		transaction1.setCardExpirationDate("1122");
		transaction1.setCardReferenceId("54245394529567492");
		transaction1.setCurrencyCode("840");
		transaction1.setDebitCreditCode("CR");
		transaction1.setPointOfSaleCardUsageCode("Chip");
		transaction1.setPointOfSalePresenceDescription("Card holder present at time of transaction");
		transaction1.setPointOfSalePresenceCode("12");
		transaction1.setPostedDate("20180210");
		transaction1.setTransactionAmount("55.23");
		transaction1.setTransactionDate("20180206");
		transaction1.setTransactionDescription("Purchase");
		transaction1.setTransactionReferenceId("530");
		
		MerchantDetails merchant = new MerchantDetails();
		merchant.setCategoryCode("23");
		merchant.setCategory("Internet");
		merchant.setMerchantName("ABC Internet");
		merchant.setStoreNumber("603953432");
		
		MerchantAddress address = new MerchantAddress();
		address.setAddressLine1("Address Line 1");
		address.setAddressLine2("Address Line 2");
		address.setCity("Boston");
		address.setCountry("US");
		address.setPostalCode("52040");
		address.setState("MC");
		
		merchant.setMerchantAddress(address);
		transaction1.setMerchantDetails(merchant);
		transactionsStore.getTransactions().put("530", transaction1);
		
		DetailedTransaction transaction2 = new DetailedTransaction();
		transaction2.setCardExpirationDate("0822");
		transaction2.setCardReferenceId("54245394529567492");
		transaction2.setCurrencyCode("124");
		transaction2.setDebitCreditCode("DB");
		transaction2.setPostedDate("20180805");
		transaction2.setTransactionAmount("24.05");
		transaction2.setTransactionDate("20180802");
		transaction2.setTransactionDescription("Refund");
		transaction2.setTransactionReferenceId("732");
		
		transactionsStore.getTransactions().put("732", transaction2);
		
		List<String> transactionsList = new ArrayList();
		transactionsList.add("530");
		transactionsList.add("732");

		accountTransactionsMapping.getTransactionsMap().put("54245394529567492", transactionsList);
		
	}
	
	@Test
	public void retrieveCorrectTransactionByIdTest() {
		DetailedTransaction transaction = transactionDAO.getTransaction("123");
		Assert.assertTrue("Transaction is null",transaction!=null);
		Assert.assertTrue("Transaction ID doesn't match", transaction.getTransactionReferenceId().equals("123"));
		Assert.assertTrue("Merchant Name doesn't match", transaction.getMerchantDetails().getMerchantName().equals("Abc Restaraunt"));
		Assert.assertTrue("Card reference doesn't match",transaction.getCardReferenceId().equals("12345678912345"));
	}
	
	@Test
	public void retrieveCorrectTransactionByAmountTest() {
		TransactionSearchRequest transactionSearchRequest = new TransactionSearchRequest();
		transactionSearchRequest.setAmount("34.11");
		transactionSearchRequest.setAccountNumber("12345678912345");
		
		//when(transactionUtility.encryptPCIData(any(Transaction.class)).thenReturn()  
		
		List<DetailedTransaction> transactions = transactionDAO.getTransactionsList(transactionSearchRequest);
		Assert.assertTrue("Transaction List should contain only one transaction",transactions.size()==1);
		Assert.assertTrue("Incorrect transaction retrieved", transactions.get(0).getTransactionReferenceId().equals("123"));
		Assert.assertTrue("Incorrect transaction retrieved", transactions.get(0).getCardReferenceId().equals("12345678912345"));
	}
	
	@Test
	public void retrieveAllTransactionsOfAnAccount(){
		
		TransactionSearchRequest transactionSearchRequest = new TransactionSearchRequest();
		transactionSearchRequest.setAccountNumber("54245394529567492");
		
		List<DetailedTransaction> transactions = transactionDAO.getTransactionsList(transactionSearchRequest);
		Assert.assertTrue("Transaction List should contain two transactions",transactions.size()==2);
		Assert.assertTrue("Incorrect transaction retrieved", transactions.get(0).getTransactionReferenceId().equals("732"));
		Assert.assertTrue("Incorrect transaction retrieved", transactions.get(0).getCardReferenceId().equals("54245394529567492"));
		
		Assert.assertTrue("Incorrect transaction retrieved", transactions.get(1).getTransactionReferenceId().equals("530"));
		Assert.assertTrue("Incorrect transaction retrieved", transactions.get(1).getCardReferenceId().equals("54245394529567492"));
	}

	
}
