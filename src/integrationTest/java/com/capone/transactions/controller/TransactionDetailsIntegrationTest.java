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
}
