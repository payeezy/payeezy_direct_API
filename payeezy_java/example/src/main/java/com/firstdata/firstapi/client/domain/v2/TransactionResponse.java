package com.firstdata.firstapi.client.domain.v2;

import java.util.ArrayList;
import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonAutoDetect.Visibility;
@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
public class TransactionResponse {

    /**
     * {"method":"credit_card","amount":"1100","currency":"USD","avs":"Z","cvv2":"I",
     * "card":{"type":"visa","cvv":"123","cardholder_name":"Ritesh Shah","card_number":"8291","exp_date":"1214"},
     * "transaction_status":"approved","validation_status":"success","transaction_type":"PURCHASE",
     * "transaction_id":"OK3740","transaction_tag":"1597368","transarmor_token":"2537446225198291",
     * "correlation_id":"55.1410534812572"} 
     * */
	
	/*
	 * {
		    correlation_id=227.1431376843540,
		    status=success,
		    type=FDToken,
		    token={
		        type=mastercard,
		        cardholder_name=JohnSmith,
		        exp_date=0416,
		        value=2833693264441732
		    }
		}
	 * 
	 * */
	 
	 
	public TransactionResponse() {
	}
	
	@JsonProperty("transaction_status")
	private String transactionStatus;
	@JsonProperty("validation_status")
	private String validationStatus;
	@JsonProperty("transaction_type")
	private String transactionType;
	@JsonProperty("transaction_id")
	private String transactionId;
	@JsonProperty("transaction_tag")
	private String transactionTag;
	@JsonProperty("method")
	private String method;
	@JsonProperty("amount")
	private String amount;
	@JsonProperty("currency")
	private String currency;
	@JsonProperty("avs")	
	private String avs;
	@JsonProperty("cvv2")
	private String cvv2;
    @JsonProperty("token")
	private Token token;
    @JsonProperty("card")
    private Card card;
    @JsonProperty("Error")
    private Error error;
	@JsonProperty("correlation_id")
	private String correlationID;

	@JsonProperty("bank_resp_code")
	private String bankResponseCode;
	@JsonProperty("bank_resp_code_2")
    private String bankResponseCode2;
	@JsonProperty("bank_message")
    private String bankMessage;
	@JsonProperty("gateway_resp_code")
    private String gatewayResponseCode;
	@JsonProperty("gateway_message")
    private String gatewayMessage;

	
	//additional properties
	
	
	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}

	@JsonProperty("status")
    private String status;

	@JsonProperty("type")
    private String type;

	
	//end - additional properties
	
	
	@JsonProperty("tele_check")
    private Telecheck teleCheck;
	
	@JsonProperty("valuelink")
    private ValueLink valueLink;
	
	public String getTransactionStatus() {
		return transactionStatus;
	}

	
	public void setTransactionStatus(String transactionStatus) {
		this.transactionStatus = transactionStatus;
	}
	
	
	public String getValidationStatus() {
		return validationStatus;
	}

	
	public void setValidationStatus(String validationStatus) {
		this.validationStatus = validationStatus;
	}


	
	
	public String getTransactionType() {
		return transactionType;
	}


	
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	
	
	public String getTransactionId() {
		return transactionId;
	}

	
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}


	
	public String getTransactionTag() {
		return transactionTag;
	}


	
	public void setTransactionTag(String transactionTag) {
		this.transactionTag = transactionTag;
	}
	
	
	public String getMethod() {
		return method;
	}

	
	public void setMethod(String method) {
		this.method = method;
	}


	
	public String getAmount() {
		return amount;
	}


	
	public void setAmount(String amount) {
		this.amount = amount;
	}


	
	public String getCurrency() {
		return currency;
	}


	
	public void setCurrency(String currency) {
		this.currency = currency;
	}

	
	public String getAvs() {
		return avs;
	}


	
	public void setAvs(String avs) {
		this.avs = avs;
	}


	
	public String getCvv2() {
		return cvv2;
	}


	
	public void setCvv2(String cvv2) {
		this.cvv2 = cvv2;
	}




	public Token getToken() {
		return token;
	}


	public void setToken(Token token) {
		this.token = token;
	}

	
	public Card getCard() {
		return card;
	}


	
	public void setCard(Card card) {
		this.card = card;
	}


	
	public Error getError() {
		return error;
	}

	
	
	public void setErrMessage(ArrayList<String> messages){
		error= new Error();
		error.setMessage(messages);
	}


    
    public String getCorrelationID() {
        return correlationID;
    }


    
    public void setCorrelationID(String correlationID) {
        this.correlationID = correlationID;
    }


    
    public String getBankResponseCode() {
        return bankResponseCode;
    }


    
    public void setBankResponseCode(String bankResponseCode) {
        this.bankResponseCode = bankResponseCode;
    }


    
    public String getBankResponseCode2() {
        return bankResponseCode2;
    }


    
    public void setBankResponseCode2(String bankResponseCode2) {
        this.bankResponseCode2 = bankResponseCode2;
    }


    
    public String getBankMessage() {
        return bankMessage;
    }


    
    public void setBankMessage(String bankMessage) {
        this.bankMessage = bankMessage;
    }


    
    public String getGatewayResponseCode() {
        return gatewayResponseCode;
    }


    
    public void setGatewayResponseCode(String gatewayResponseCode) {
        this.gatewayResponseCode = gatewayResponseCode;
    }


    
    public String getGatewayMessage() {
        return gatewayMessage;
    }


    
    public void setGatewayMessage(String gatewayMessage) {
        this.gatewayMessage = gatewayMessage;
    }


    
    public void setError(Error error) {
        this.error = error;
    }


	public ValueLink getValueLink() {
		return valueLink;
	}


	public void setValueLink(ValueLink valueLink) {
		this.valueLink = valueLink;
	}


	public Telecheck getTeleCheck() {
		return teleCheck;
	}


	public void setTeleCheck(Telecheck teleCheck) {
		this.teleCheck = teleCheck;
	}


    
/*    public void setError(Error error) {
        this.error = error;
    }
*/

}
