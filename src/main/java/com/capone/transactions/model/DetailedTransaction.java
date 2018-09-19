package com.capone.transactions.model;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

public class DetailedTransaction {

	@NotNull
	@Pattern(regexp = "\\d{8}", message = "Invalid Transaction Date")
	private String transactionDate;

	@NotNull
	@Pattern(regexp = "\\d{8}", message = "Invalid Posted Date")
	private String postedDate;

	@NotNull
	@DecimalMin(value = "0.0", message = "Invalid Transaction Amount")
	private String transactionAmount;

	private String transactionReferenceId;

	@NotNull
	@Length(max = 50, min = 1, message = "The message description should be within 1 to 50 characters")
	private String transactionDescription;

	@NotNull
	@Length(max = 2, min = 2, message = "Invalid Debit/Credit Code")
	private String debitCreditCode;

	// private String transactionAccountdNumber;
	@NotNull
	@Pattern(regexp = "\\d{4}", message = "Invalid Card Expiration Date")
	private String cardExpirationDate;

	@NotNull
	@Pattern(regexp = "\\d{3}", message = "Invalid Currency Code")
	private String currencyCode;

	@NotNull
	@Pattern(regexp = "\\d{2}")
	private String pointOfSalePresenceCode;

	@NotNull
	@Length(max = 50, min = 1, message = "The message description should be within 1 to 50 characters")
	private String pointOfSalePresenceDescription;

	@NotNull
	private String pointOfSaleCardUsageCode;

	@NotNull
	private String cardReferenceId;

	@NotNull
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

	public String getTransactionAmount() {
		return transactionAmount;
	}

	public void setTransactionAmount(String transactionAmount) {
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
