package com.capone.transactions.model;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Transaction {

	private String transactionDate;
	
	private String postedDate;
	
	private BigDecimal transactionAmount;
	
	private String transactionReferenceId;
	
	private String transactionDescription;
	
	private String balanceSegmentCode;
	
	private String debitCreditCode;
	
//	private String transactionAccountdNumber;
	
	private String cardExpirationDate;
	
	private String storeId;
	
	private String currencyCode;
	
	private String pointOfSalePresenceCode;
	
	private String pointOfSalePresenceDescription;
	
	private String pointOfSaleCardUsageCode;
	
	private String cardReferenceId;
	
	private MerchantDetails merchantDetails;

	public String getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(String transactionDate) {
		this.transactionDate = transactionDate;
	}

	public String getPostedDate() {
		return postedDate;
	}

	public void setPostedDate(String postedDate) {
		this.postedDate = postedDate;
	}

	public BigDecimal getTransactionAmount() {
		return transactionAmount;
	}

	public void setTransactionAmount(BigDecimal transactionAmount) {
		this.transactionAmount = transactionAmount;
	}

	public String getTransactionReferenceId() {
		return transactionReferenceId;
	}

	public void setTransactionReferenceId(String transactionReferenceId) {
		this.transactionReferenceId = transactionReferenceId;
	}

	public String getTransactionDescription() {
		return transactionDescription;
	}

	public void setTransactionDescription(String transactionDescription) {
		this.transactionDescription = transactionDescription;
	}

	public String getBalanceSegmentCode() {
		return balanceSegmentCode;
	}

	public void setBalanceSegmentCode(String balanceSegmentCode) {
		this.balanceSegmentCode = balanceSegmentCode;
	}

	public String getDebitCreditCode() {
		return debitCreditCode;
	}

	public void setDebitCreditCode(String debitCreditCode) {
		this.debitCreditCode = debitCreditCode;
	}

	public String getCardExpirationDate() {
		return cardExpirationDate;
	}

	public void setCardExpirationDate(String cardExpirationDate) {
		this.cardExpirationDate = cardExpirationDate;
	}

	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public String getPointOfSalePresenceCode() {
		return pointOfSalePresenceCode;
	}

	public void setPointOfSalePresenceCode(String pointOfSalePresenceCode) {
		this.pointOfSalePresenceCode = pointOfSalePresenceCode;
	}

	public String getPointOfSalePresenceDescription() {
		return pointOfSalePresenceDescription;
	}

	public void setPointOfSalePresenceDescription(
			String pointOfSalePresenceDescription) {
		this.pointOfSalePresenceDescription = pointOfSalePresenceDescription;
	}

	public String getPointOfSaleCardUsageCode() {
		return pointOfSaleCardUsageCode;
	}

	public void setPointOfSaleCardUsageCode(String pointOfSaleCardUsageCode) {
		this.pointOfSaleCardUsageCode = pointOfSaleCardUsageCode;
	}

	public String getCardReferenceId() {
		return cardReferenceId;
	}

	public void setCardReferenceId(String cardReferenceId) {
		this.cardReferenceId = cardReferenceId;
	}

	public MerchantDetails getMerchantDetails() {
		return merchantDetails;
	}

	public void setMerchantDetails(MerchantDetails merchantDetails) {
		this.merchantDetails = merchantDetails;
	}

}
