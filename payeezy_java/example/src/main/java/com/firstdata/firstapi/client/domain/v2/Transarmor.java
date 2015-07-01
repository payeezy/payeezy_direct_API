package com.firstdata.firstapi.client.domain.v2;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonAutoDetect.Visibility;

@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
public class Transarmor extends Card {

	
	@JsonProperty("value")
	private String token;
	
	
	public String getValue() {
		return token;
	}
	public void setValue(String value) {
		this.token = value;
	}

}
