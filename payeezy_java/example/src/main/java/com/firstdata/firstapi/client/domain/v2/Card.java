package com.firstdata.firstapi.client.domain.v2;


import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonAutoDetect.Visibility;

@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
public class Card implements Cloneable  {

	public Card() {
	}
	@JsonProperty("type")
	private String type;
	@JsonProperty("cardholder_name")
	private String name;
	@JsonProperty("card_number")
	private String number;
	@JsonProperty("exp_date")
	private String expiryDt;
    @JsonProperty("cvv")
	private String cvv;
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getNumber() {
		return number;
	}
	
	public void setNumber(String nmber) {
		this.number = nmber;
	}
	
	public String getExpiryDt() {
		return expiryDt;
	}
	
	public void setExpiryDt(String expiryDt) {
		this.expiryDt = expiryDt;
	}

	public String getCvv() {
		return cvv;
	}

	public void setCvv(String cvv) {
		this.cvv = cvv;
	}
	
	@Override
	public Card clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return (Card)super.clone();
	}

}
