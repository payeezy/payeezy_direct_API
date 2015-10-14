package com.firstdata.firstapi.client.domain.v2;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonAutoDetect.Visibility;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
public class Telecheck {
    @JsonProperty("accountholder_name")
    private String accountholderName;
    
    @JsonProperty("check_number")
    private String checkNumber;
    
    @JsonProperty("check_type")
    private String checkType;
    
    @JsonProperty("account_number")
    private String bankAccountNumber;
    
    @JsonProperty("routing_number")
    private String bankRoutingNumber;
    
    @JsonProperty("customer_id_type")
    private String customerIdType;
    
    @JsonProperty("customer_id_number")
    private String customerIdNumber;
    
    @JsonProperty("client_email")
    private String clientEmail;
    
    @JsonProperty("release_type")
    private String releaseType;
    
    @JsonProperty("gift_card_amount")
    private String giftCardAmount;
    
    @JsonProperty("date_of_birth")
    private String dateOfBirth;
    
    @JsonProperty("vip")
    private String vip;
    
    @JsonProperty("registration_number")
    private String registrationNumber;
    
    @JsonProperty("registration_date")
    private String registrationDate;
    
    @JsonProperty("clerk_id")
    private String clerkId;
    
    @JsonProperty("device_id")
    private String deviceId;
    
    @JsonProperty("micr")
    private String micr;
    

    
    public String getAccountholderName() {
        return accountholderName;
    }

    
    public void setAccountholderName(String accountholderName) {
        this.accountholderName = accountholderName;
    }

    
    public String getCheckNumber() {
        return checkNumber;
    }

    
    public void setCheckNumber(String checkNumber) {
        this.checkNumber = checkNumber;
    }

    
    public String getCheckType() {
        return checkType;
    }

    
    public void setCheckType(String checkType) {
        this.checkType = checkType;
    }

    
    public String getBankAccountNumber() {
        return bankAccountNumber;
    }

    
    public void setBankAccountNumber(String bankAccountNumber) {
        this.bankAccountNumber = bankAccountNumber;
    }

    
    public String getBankRoutingNumber() {
        return bankRoutingNumber;
    }

    
    public void setBankRoutingNumber(String bankRoutingNumber) {
        this.bankRoutingNumber = bankRoutingNumber;
    }

    
    public String getCustomerIdType() {
        return customerIdType;
    }

    
    public void setCustomerIdType(String customerIdType) {
        this.customerIdType = customerIdType;
    }

    
    public String getCustomerIdNumber() {
        return customerIdNumber;
    }

    
    public void setCustomerIdNumber(String customerIdNumber) {
        this.customerIdNumber = customerIdNumber;
    }

    
    public String getClientEmail() {
        return clientEmail;
    }

    
    public void setClientEmail(String clientEmail) {
        this.clientEmail = clientEmail;
    }


	public String getReleaseType() {
		return releaseType;
	}


	public void setReleaseType(String releaseType) {
		this.releaseType = releaseType;
	}


	public String getGiftCardAmount() {
		return giftCardAmount;
	}


	public void setGiftCardAmount(String giftCardAmount) {
		this.giftCardAmount = giftCardAmount;
	}


	public String getDateOfBirth() {
		return dateOfBirth;
	}


	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}


	public String getVip() {
		return vip;
	}


	public void setVip(String vip) {
		this.vip = vip;
	}


	public String getRegistrationNumber() {
		return registrationNumber;
	}


	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}


	public String getRegistrationDate() {
		return registrationDate;
	}


	public void setRegistrationDate(String registrationDate) {
		this.registrationDate = registrationDate;
	}


	public String getClerkId() {
		return clerkId;
	}


	public void setClerkId(String clerkId) {
		this.clerkId = clerkId;
	}


	public String getDeviceId() {
		return deviceId;
	}


	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}


	public String getMicr() {
		return micr;
	}


	public void setMicr(String micr) {
		this.micr = micr;
	}
    
    
}
