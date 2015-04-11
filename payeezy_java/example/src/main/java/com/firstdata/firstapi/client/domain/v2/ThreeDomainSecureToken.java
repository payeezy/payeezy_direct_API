package com.firstdata.firstapi.client.domain.v2;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonAutoDetect.Visibility;

@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
public class ThreeDomainSecureToken {
	
	public ThreeDomainSecureToken() {

	}
	
	private String type;    
	private String transactionId;	
	private String encryptedData;	
	private String publicKeyHash;	
	private String wrappedKey;
	private String symmetricKeyInfo;
	private String asymmetricKeyInfo;
	private String pkcs7Signature;
	private String eciIndicator;
	private String merchantIdentifier;
	private String signatureAlgInfo;
	private String cavv;
	

	
	
	@JsonProperty("cavv")
	public String getCavv() {
		return cavv;
	}
	
	@JsonProperty("cavv")
	public void setCavv(String cavv) {
		this.cavv = cavv;
	}
	
	@JsonProperty("type")
	public String getType() {
		return type;
	}
	@JsonProperty("type")
	public void setType(String type) {
		this.type = type;
	}
	
	@JsonProperty("transactionId")
	public String getTransactionId() {
		return transactionId;
	}

	@JsonProperty("transactionId")
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}	
	
	@JsonProperty("encryptedData")
	public String getEncryptedData() {
		return encryptedData;
	}

	@JsonProperty("encryptedData")
	public void setEncryptedData(String encryptedData) {
		this.encryptedData = encryptedData;
	}
	
	@JsonProperty("publicKeyHash")
	public String getPublicKeyHash() {
		return publicKeyHash;
	}

	@JsonProperty("publicKeyHash")
	public void setPublicKeyHash(String publicKeyHash) {
		this.publicKeyHash = publicKeyHash;
	}	
	
	@JsonProperty("wrappedKey")
	public String getWrappedKey() {
		return wrappedKey;
	}

	@JsonProperty("wrappedKey")
	public void setWrappedKey(String wrappedKey) {
		this.wrappedKey = wrappedKey;
	}
	
	
	@JsonProperty("symmetricKeyInfo")
	public String getSymmetricKeyInfo() {
		return symmetricKeyInfo;
	}

	@JsonProperty("symmetricKeyInfo")
	public void setSymmetricKeyInfo(String symmetricKeyInfo) {
		this.symmetricKeyInfo = symmetricKeyInfo;
	}		
	
	@JsonProperty("asymmetricKeyInfo")
	public String getAsymmetricKeyInfo() {
		return asymmetricKeyInfo;
	}

	@JsonProperty("asymmetricKeyInfo")
	public void setAsymmetricKeyInfo(String asymmetricKeyInfo) {
		this.asymmetricKeyInfo = asymmetricKeyInfo;
	}		
	
	@JsonProperty("pkcs7Signature")
	public String Pkcs7Signature() {
		return pkcs7Signature;
	}

	@JsonProperty("pkcs7Signature")
	public void setPkcs7Signature(String pkcs7Signature) {
		this.pkcs7Signature = pkcs7Signature;
	}		
	
	@JsonProperty("eciIndicator")
	public String EciIndicator() {
		return eciIndicator;
	}

	@JsonProperty("eciIndicator")
	public void setEciIndicator(String eciIndicator) {
		this.eciIndicator = eciIndicator;
	}		
	
	@JsonProperty("merchantIdentifier")
	public String MerchantIdentifier() {
		return merchantIdentifier;
	}

	@JsonProperty("merchantIdentifier")
	public void setMerchantIdentifier(String merchantIdentifier) {
		this.merchantIdentifier = merchantIdentifier;
	}	
	
	@JsonProperty("signatureAlgInfo")
	public String SignatureAlgInfo() {
		return signatureAlgInfo;
	}

	@JsonProperty("signatureAlgInfo")
	public void setSignatureAlgInfo(String signatureAlgInfo) {
		this.signatureAlgInfo = signatureAlgInfo;
	}		
}
