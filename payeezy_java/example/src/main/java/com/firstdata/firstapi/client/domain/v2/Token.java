package com.firstdata.firstapi.client.domain.v2;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonAutoDetect.Visibility;

@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
public class Token {
	
	@JsonProperty("token_type")
	private String tokenType;
	
	@JsonProperty("token_data")
	private Transarmor tokenData;

	public String getTokenType() {
		return tokenType;
	}

	public void setTokenType(String tokenType) {
		this.tokenType = tokenType;
	}

	public Transarmor getTokenData() {
		return tokenData;
	}

	public void setTokenData(Transarmor tokenData) {
		this.tokenData = tokenData;
	}
}
