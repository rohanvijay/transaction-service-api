package com.capone.transactions.controller;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.capone.transactions.application.Transactions;
import com.capone.transactions.model.DetailedTransaction;
import com.capone.transactions.model.MerchantAddress;
import com.capone.transactions.model.MerchantDetails;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Transactions.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TransactionDetailsIntegrationTest {

	@LocalServerPort
	private int port;

	TestRestTemplate restTemplate = new TestRestTemplate();

	HttpHeaders headers = new HttpHeaders();

	private String createURLWithPort(String uri) {
		
		return "http://localhost:" + port + uri;
	}
	
	@Test
	public void testGetAccountTransactions() {
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);

		try{
			String encodedAcnt = "s+v3zxBqIUNfmlmi9FAFq1TDikltNCZL2ptcPzDhWpo=";
			ResponseEntity response = restTemplate.exchange(
				createURLWithPort("/accounts/"+encodedAcnt+"/transactions"),
				HttpMethod.GET, entity, new ParameterizedTypeReference<List<DetailedTransaction>>() {});
		
		HttpStatus responseCode = response.getStatusCode();
		
		assertTrue("Incorrect status code. Expected : 200, Actual : "+responseCode,responseCode==HttpStatus.OK);
		
		List<DetailedTransaction> detailedTransasctions = (List<DetailedTransaction>)response.getBody();
		assertTrue(detailedTransasctions.size()==2);
		
		}catch(Exception ex){
			fail("Exception : "+ex.getMessage());
		}
	}
	
	@Test
	public void testGetAccountTransactionsNoContent() {

		HttpEntity<String> entity = new HttpEntity<String>(null, headers);

		try{
		String encodedAcnt = "cgfzw9OoEfwWMaj3spNboFTDikltNCZL2ptcPzDhWpo=";
		ResponseEntity<String> response = restTemplate.exchange(
				createURLWithPort("/accounts/"+encodedAcnt+"/transactions"),
				HttpMethod.GET, entity, String.class);
		
		HttpStatus responseCode = response.getStatusCode();
		assertTrue("Incorrect status code. Expected : 204, Actual : "+responseCode,responseCode==HttpStatus.NO_CONTENT);
		
		}catch(Exception ex){
			fail("Exception : "+ex.getMessage());
		}
	}
	
	@Test
	public void testGetAccountTransactionsPassingInvalidAmountInRequest() {

		HttpEntity<String> entity = new HttpEntity<String>(null, headers);

		try{
		String encodedAcnt = "cgfzw9OoEfwWMaj3spNboFTDikltNCZL2ptcPzDhWpo=";
		ResponseEntity<String> response = restTemplate.exchange(
				createURLWithPort("/accounts/"+encodedAcnt+"/transactions?amount=A1"),
				HttpMethod.GET, entity, String.class);
		
		HttpStatus responseCode = response.getStatusCode();
		assertTrue("Incorrect status code. Expected : 404, Actual : "+responseCode,responseCode==HttpStatus.BAD_REQUEST);
		
		}catch(Exception ex){
			fail("Exception : "+ex.getMessage());
		}
	}
	
	@Test
	public void testPostValidAccountTransactions() {

		DetailedTransaction transaction = getTransaction();
		
		HttpEntity<DetailedTransaction> entity = new HttpEntity<DetailedTransaction>(transaction, headers);

		try{
		ResponseEntity<String> response = restTemplate.exchange(
				createURLWithPort("/postTransaction"),
				HttpMethod.POST, entity, String.class);
		
		HttpStatus responseCode = response.getStatusCode();
		assertTrue("Incorrect status code. Expected : 200, Actual : "+responseCode,responseCode==HttpStatus.OK);
		
		}catch(Exception ex){
			fail("Exception : "+ex.getMessage());
		}
	}
	
	@Test
	public void testPostInvalidValidAccountTransactions() {

		//Invalid Transaction as Country is CA and Postal Code is 43032.
		DetailedTransaction transaction = getTransaction();
		transaction.getMerchantDetails().getMerchantAddress().setCountry("CA");
		
		HttpEntity<DetailedTransaction> entity = new HttpEntity<DetailedTransaction>(transaction, headers);

		try{
		ResponseEntity<String> response = restTemplate.exchange(
				createURLWithPort("/postTransaction"),
				HttpMethod.POST, entity, String.class);
		
		HttpStatus responseCode = response.getStatusCode();
		assertTrue("Incorrect status code. Expected : 400, Actual : "+responseCode,responseCode==HttpStatus.BAD_REQUEST);
		
		}catch(Exception ex){
			fail("Exception : "+ex.getMessage());
		}
	}

	private DetailedTransaction getTransaction() {
		
		DetailedTransaction transaction = new DetailedTransaction();
		
		transaction.setCardExpirationDate("0321");
		transaction.setCardReferenceId("CF94ndQMq/bbjG2+zbgvhVTDikltNCZL2ptcPzDhWpo=");
		transaction.setCurrencyCode("840");
		transaction.setDebitCreditCode("CR");
		transaction.setPointOfSaleCardUsageCode("Chip");
		transaction.setPointOfSaleCardPresenceDescription("Card holder present at time of transaction");
		transaction.setPointOfSaleCardPresenceCode("12");
		transaction.setPostedDate("20180210");
		transaction.setTransactionAmount("34.12");
		transaction.setTransactionDate("20180206");
		transaction.setTransactionDescription("Purchase");
		transaction.setTransactionReferenceId("123");
		
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
		transaction.setMerchantDetails(merchant);
		
		return transaction;
		
	}
}
