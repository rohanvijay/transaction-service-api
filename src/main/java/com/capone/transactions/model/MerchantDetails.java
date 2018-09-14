package com.capone.transactions.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

public class MerchantDetails {
	
	@NotNull
	@Length(max = 50, min = 1, message = "The message description should be within 1 to 50 characters")
	private String merchantName;
	
	@NotNull
	@Length(max = 50, min = 1, message = "The message description should be within 1 to 50 characters")
	private String category;
	
	@NotNull
	@Pattern(regexp = "[\\d{2}]")
	private String categoryCode;
	
	@NotNull
	@Pattern(regexp = "[\\d{6}]")
	private String storeNumber;
	
	@NotNull
	private MerchantAddress merchantAddress;

	public String getMerchantName() {
		return merchantName;
	}

	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

	public String getStoreNumber() {
		return storeNumber;
	}

	public void setStoreNumber(String storeNumber) {
		this.storeNumber = storeNumber;
	}

	public MerchantAddress getMerchantAddress() {
		return merchantAddress;
	}

	public void setMerchantAddress(MerchantAddress merchantAddress) {
		this.merchantAddress = merchantAddress;
	}
	
	

}
