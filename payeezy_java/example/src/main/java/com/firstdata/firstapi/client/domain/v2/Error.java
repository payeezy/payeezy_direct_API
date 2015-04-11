package com.firstdata.firstapi.client.domain.v2;

import java.util.ArrayList;
import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonAutoDetect.Visibility;

@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
public class Error {
    @JsonProperty("messages")
    private ArrayList<String> messages;
    
    
    public ArrayList<String> getMessages() {
        return messages;
    }
    
    
    public void setMessage(ArrayList<String> message) {
        this.messages = message;
    }
}
