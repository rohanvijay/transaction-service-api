package com.capone.transactions.service;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import com.capone.transactions.dao.impl.TransactionDAOImpl;
import com.capone.transactions.model.DetailedTransaction;
import com.capone.transactions.model.MerchantAddress;
import com.capone.transactions.model.MerchantDetails;
import com.capone.transactions.utils.EncryptionDecryptionUtility;

@RunWith(MockitoJUnitRunner.class)
public class TransactionServiceTest {

	@InjectMocks
	TransactionService transactionService;
	
	@Mock
	TransactionDAOImpl transactionDAO;

	@Spy
	EncryptionDecryptionUtility encryptionDecryptionUtility;
	
public void init(){
		
		populateCard1Transactions();
		populateCard2Transactions();
		
	}

	private DetailedTransaction populateCard1Transactions() {
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
		
		
		List<String> transactionsList = new ArrayList();
		transactionsList.add("123");
		transactionsList.add("234");

		return transaction1;
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
		
		DetailedTransaction transaction2 = new DetailedTransaction();
		transaction2.setCardExpirationDate("0822");
		transaction2.setCardReferenceId("5424239400239827");
		transaction2.setCurrencyCode("124");
		transaction2.setDebitCreditCode("DB");
		transaction2.setPostedDate("20180805");
		transaction2.setTransactionAmount("24.05");
		transaction2.setTransactionDate("20180802");
		transaction2.setTransactionDescription("Refund");
		transaction2.setTransactionReferenceId("732");
		
		
		List<String> transactionsList = new ArrayList();
		transactionsList.add("530");
		transactionsList.add("732");

		
	}
	
	@Test
	public void test() {
		
		Mockito.doReturn(populateCard1Transactions()).when(transactionDAO).getTransaction("123");
		
		transactionService.getTransactionById("86/PSKkokJK1LFe7wqD41g==");
		
		fail("Not yet implemented");
	}

}
