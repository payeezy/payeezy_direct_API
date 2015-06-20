package com.firstdata.firstapi.client.domain.v2;


import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonAutoDetect.Visibility;
import org.codehaus.jackson.annotate.JsonProperty;
@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
public class Address {

	public Address() {
	}
	@JsonProperty("name")
	private String name;

	@JsonProperty("street")
	private String addressLine1;

	
	@JsonProperty("state_province")
	private String state;
	
	@JsonProperty("city")
	private String city;
	
	@JsonProperty("country")
	private String country;
	
	@JsonProperty("email")
	private String email;
	
	private Phone phone;
	
	@JsonProperty("zip_postal_code")
	private String zip;
	
	public String getZip() {
		return zip;
	}

	@JsonProperty("zip_postal_code")
	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getName() {
		return name;
	}

	@JsonProperty("name")
	public void setName(String name) {
		this.name = name;
	}

	public String getAddressLine1() {
		return addressLine1;
	}
	
	@JsonProperty("street")
	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	public String getState() {
		return state;
	}
	
	@JsonProperty("state_province")
	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	@JsonProperty("city")
	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	@JsonProperty("country")
	public void setCountry(String country) {
		this.country = country;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@JsonProperty("phone")
	public Phone getPhone() {
		return phone;
	}

	@JsonProperty("phone")
	public void setPhone(Phone phone) {
		this.phone = phone;
	}

	public static class Phone{
		@JsonProperty("type")
		private String type;
		@JsonProperty("number")
		private String number;
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
		public String getNumber() {
			return number;
		}
		public void setNumber(String number) {
			this.number = number;
		}
	}
	
	
	

}
