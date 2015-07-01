package com.firstdata.firstapi.client.domain.v2;

import org.codehaus.jackson.annotate.JsonProperty;


public class ValueLink {
    @JsonProperty("cardholder_name")
    private String cardHoldersName;
    @JsonProperty("cc_number")
    private String cardNumber;
    @JsonProperty("amount")
    private String dollarAmount;
    @JsonProperty("credit_card_type")
    private final String cardType = "Gift";          
    @JsonProperty("card_cost")
    private String cardCost;                
    @JsonProperty("reference_3")
    private String reference3;          
    @JsonProperty("ean")
    private String ean;                          
    @JsonProperty("virtual_card")
    private String virtualCard;
    @JsonProperty("previous_balance")
    private String prevoiusBalance;
    @JsonProperty("current_balance")
    private String currentBalance;
    
    public String getCardHoldersName() {
        return cardHoldersName;
    }
    
    public void setCardHoldersName(String cardHoldersName) {
        this.cardHoldersName = cardHoldersName;
    }
    
    public String getCardNumber() {
        return cardNumber;
    }
    
    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }
    
    public String getDollarAmount() {
        return dollarAmount;
    }
    
    public void setDollarAmount(String dollarAmount) {
        this.dollarAmount = dollarAmount;
    }
    
    public String getCardCost() {
        return cardCost;
    }
    
    public void setCardCost(String cardCost) {
        this.cardCost = cardCost;
    }
    
    public String getReference3() {
        return reference3;
    }
    
    public void setReference3(String reference3) {
        this.reference3 = reference3;
    }
    
    public String getEan() {
        return ean;
    }
    
    public void setEan(String ean) {
        this.ean = ean;
    }
    
    public String getVirtualCard() {
        return virtualCard;
    }
    
    public void setVirtualCard(String virtualCard) {
        this.virtualCard = virtualCard;
    }
    
    public String getPrevoiusBalance() {
        return prevoiusBalance;
    }
    
    public void setPrevoiusBalance(String prevoiusBalance) {
        this.prevoiusBalance = prevoiusBalance;
    }
    
    public String getCurrentBalance() {
        return currentBalance;
    }
    
    public void setCurrentBalance(String currentBalance) {
        this.currentBalance = currentBalance;
    }
    
    public String getCardType() {
        return cardType;
    }

    
}
