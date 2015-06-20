package com.firstdata.firstapi.client.domain;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonAutoDetect.Visibility;
import org.codehaus.jackson.annotate.JsonProperty;
@JsonAutoDetect(getterVisibility=Visibility.ANY,setterVisibility=Visibility.ANY,fieldVisibility=Visibility.NONE)
public class MerchantValidationInfo {
    
    private String taxID;
    private String aba;
    private String dda;
    private String tid;
    private String merchantNo;
    private String bankName;    
    private String businessPhoneNumber;
    
    private String legalName;
    
    private String businessAddress1;
    
    private String businessAddress2;
    private String city;
    private String state;
    
    private String zipCode;
    
    private String threatMatrixSessionId;
    
    @JsonProperty("tin")
    public String getTaxID() {
        return taxID;
    }
    
    @JsonProperty("tin")
    public void setTaxID(String taxID) {
        this.taxID = taxID;
    }
    
    @JsonProperty("trans_routing_number")
    public String getAba() {
        return aba;
    }
    
    @JsonProperty("trans_routing_number")
    public void setAba(String aba) {
        this.aba = aba;
    }
    
    @JsonProperty("checking_account_number")
    public String getDda() {
        return dda;
    }
    
    @JsonProperty("checking_account_number")
    public void setDda(String dda) {
        this.dda = dda;
    }
    
    
    public String getTid() {
        return tid;
    }
    
    
    public void setTid(String tid) {
        this.tid = tid;
    }
    
    @JsonProperty("merchant_no")
    public String getMerchantNo() {
        return merchantNo;
    }
    
    @JsonProperty("merchant_no")
    public void setMerchantNo(String merchantNo) {
        this.merchantNo = merchantNo;
    }
    
    @JsonProperty("bank_name")
    public String getBankName() {
        return bankName;
    }
    
    @JsonProperty("bank_name")
    public void setBankName(String bankName) {
        this.bankName = bankName;
    }
    
    @JsonProperty("business_phone_number")
    public String getBusinessPhoneNumber() {
        return businessPhoneNumber;
    }
    
    @JsonProperty("business_phone_number")
    public void setBusinessPhoneNumber(String businessPhoneNumber) {
        this.businessPhoneNumber = businessPhoneNumber;
    }
    
    @JsonProperty("legal_name")
    public String getLegalName() {
        return legalName;
    }
    
    @JsonProperty("legal_name")
    public void setLegalName(String legalName) {
        this.legalName = legalName;
    }
    
    @JsonProperty("business_address_1")
    public String getBusinessAddress1() {
        return businessAddress1;
    }
    
    @JsonProperty("business_address_1")
    public void setBusinessAddress1(String businessAddress1) {
        this.businessAddress1 = businessAddress1;
    }
    
    @JsonProperty("business_address_2")
    public String getBusinessAddress2() {
        return businessAddress2;
    }
    
    @JsonProperty("business_address_2")
    public void setBusinessAddress2(String businessAddress2) {
        this.businessAddress2 = businessAddress2;
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
    
    @JsonProperty("zip_code")
    public String getZipCode() {
        return zipCode;
    }
    
    @JsonProperty("zip_code")
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
    
    
    @JsonProperty("threat_metrix_session_id")
    public String getThreatMatrixSessionId() {
        return threatMatrixSessionId;
    }
    

    @JsonProperty("threat_metrix_session_id")
    public void setThreatMatrixSessionId(String threatMatrixSessionId) {
        this.threatMatrixSessionId = threatMatrixSessionId;
    }
    
    
    public static String toJson() {
        return "{\"taxID\":111," + "\"aba\":123," + "\"dda\":\"\"," + "\"tid\":\"\","
            + "\"merchantNo\":\"\" ," + "\"bankName\":\"\" ," + "\"businessPhoneNumber\":\"\" ,"
            + "\"legalName\":\"\" ," + "\"businessAddress1\":\"\" ,"
            + "\"businessAddress2\":\"\" ," + "\"city\":\"\" ," + "\"state\":\"\" ,"
            + "\"zipCode\":\"\" ," + "\"threatMatrixSessionId\":\"\"" + "}";
    }
    
}
