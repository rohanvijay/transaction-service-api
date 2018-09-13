package com.capone.transactions.model;

import java.math.BigDecimal;

public class TransactionSearchRequest {

	String accountReferenceId;
	BigDecimal amount;
	String accountNumber;
	
	public String getAccountReferenceId() {
		return accountReferenceId;
	}
	public void setAccountReferenceId(String accountReferenceId) {
		this.accountReferenceId = accountReferenceId;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	
}
