package com.capone.transactions.model;

public class MerchantDetails {
	
	private String merchantName;
	
	private String category;
	
	private String categoryCode;
	
	private String storeNumber;
	
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
