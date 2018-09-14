package com.capone.transactions.model;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

public class MerchantAddress {

	@NotNull
	@Length(max = 50, min = 1, message = "The message description should be within 1 to 50 characters")
	private String addressLine1;
	
	@NotNull
	@Length(max = 50, min = 1, message = "The message description should be within 1 to 50 characters")
	private String addressLine2;
	
	@NotNull
	@Length(max = 50, min = 1, message = "The message description should be within 1 to 50 characters")
	private String addressLine3;
	
	@NotNull
	@Length(max = 50, min = 1, message = "The message description should be within 1 to 50 characters")
	private String city;
	
	@NotNull
	@Length(max = 50, min = 1, message = "The message description should be within 1 to 50 characters")
	private String state;
	
	@NotNull
	private String postalCode;
	
	@NotNull
	@Length(max = 50, min = 1, message = "The message description should be within 1 to 50 characters")
	private String country;

	public String getAddressLine1() {
		return addressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	public String getAddressLine2() {
		return addressLine2;
	}

	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	public String getAddressLine3() {
		return addressLine3;
	}

	public void setAddressLine3(String addressLine3) {
		this.addressLine3 = addressLine3;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
	
}
