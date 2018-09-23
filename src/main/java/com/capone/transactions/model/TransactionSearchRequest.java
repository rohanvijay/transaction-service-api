package com.capone.transactions.model;


public class TransactionSearchRequest {

	String accountReferenceId;
	String amount;
	String accountNumber;
	
	public String getAccountReferenceId() {
		return accountReferenceId;
	}
	public void setAccountReferenceId(String accountReferenceId) {
		this.accountReferenceId = accountReferenceId;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	
}
