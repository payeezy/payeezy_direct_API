package com.firstdata.firstapi.client.domain.v2;



import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonAutoDetect.Visibility;


@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)

public class TransactionRequest {
	public TransactionRequest() {
	}

	@JsonIgnore
	private String id;

	@JsonProperty("transaction_type")
	private String transactionType;
	
	@JsonProperty("merchant_ref")
	private String referenceNo;
	
	@JsonProperty("method")
	private String paymentMethod;
	
	@JsonProperty("amount")
	private String amount;
	
	@JsonProperty("currency_code")
	private String currency;
	
	@JsonProperty("transaction_tag")
	private String transactionTag;
	
	@JsonProperty("credit_card")
	private Card card;
	
	@JsonProperty("billing_address")
	private Address billing;
	
	@JsonProperty("soft_descriptors")
	private SoftDescriptor descriptor;
	
	@JsonProperty("3DS")
	private ThreeDomainSecureToken threeDomainSecureToken;
	
	@JsonProperty("tele_check")
	private Telecheck teleCheck;
	
	@JsonProperty("valuelink")
    private ValueLink valuelink;
	
	//@JsonProperty("callback")
	private String callback;
	
	//@JsonProperty("authorization")
	private String auth;
	
	@JsonProperty("type")
	private String Type;
	
	public String getType() {
		return Type;
	}
	public void setType(String type) {
		Type = type;
	}

	@JsonProperty("ta_token")
	private String ta_token;
	
	public String getTa_token() {
		return ta_token;
	}
	public void setTa_token(String ta_token) {
		this.ta_token = ta_token;
	}
	public String getAuth() {
		return auth;
	}
	public void setAuth(String auth) {
		this.auth = auth;
	}

	//@JsonProperty("token")
	private Token token;
	
	public Token getToken() {
		return token;
	}
	public void setToken(Token token) {
		this.token = token;
	}
	public String getCallback() {
		return callback;
	}
	public void setCallback(String callback) {
		this.callback = callback;
	}
	public String getTransactionType() {
        return transactionType;
    }
    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }
	public ThreeDomainSecureToken getThreeDomainSecureToken() {
		return threeDomainSecureToken;
	}
	public void setThreeDomainSecureToken(ThreeDomainSecureToken threeDomainSecureToken) {
		this.threeDomainSecureToken = threeDomainSecureToken;
	}	
	
	public SoftDescriptor getDescriptor() {
		return descriptor;
	}
	public void setDescriptor(SoftDescriptor descriptor) {
		this.descriptor = descriptor;
	}
	public String getReferenceNo() {
		return referenceNo;
	}
	public void setReferenceNo(String referenceNo) {
		this.referenceNo = referenceNo;
	}
	
	public String getPaymentMethod() {
		return paymentMethod;
	}
	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
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

	public String getTransactionTag() {
		return transactionTag;
	}
	public void setTransactionTag(String transactionTag) {
		this.transactionTag = transactionTag;
	}

	public Card getCard() {
		return card;
	}
	public void setCard(Card card) {
		this.card = card;
	}

	public Address getBilling() {
		return billing;
	}
	public void setBilling(Address billing) {
		this.billing = billing;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
    
    public Telecheck getTeleCheck() {
        return teleCheck;
    }
    
    public void setTeleCheck(Telecheck teleCheck) {
        this.teleCheck = teleCheck;
    }
    
    public ValueLink getValuelink() {
        return valuelink;
    }
    
    public void setValuelink(ValueLink valuelink) {
        this.valuelink = valuelink;
    }
	
}
