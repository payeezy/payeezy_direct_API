package com.firstdata.firstapi.client.domain;

import org.codehaus.jackson.annotate.JsonValue;


public enum TransactionType  implements TransactionTypeConstants{
   
        PURCHASE(){
             
            @JsonValue
            public String getValue() {
                return GGE4_POST;
            }
        },
        AUTHORIZE(){
            @JsonValue
            public String getValue() {
                return GGE4_PREAUTH;
            }
        }
        ,
        CAPTURE(){

            @JsonValue
            public String getValue() {
                return GGE4_V12_PREAUTH_COMPLETE;
            }
        },
        REFUND(){
      
            @JsonValue
            public String getValue() {
                return GGE4_V12_REFUND;
            }
        },

        CASHOUT(){
            @JsonValue
            public String getValue() {
                return GGE4_CASHOUT;
            }
        },        

        RELOAD(){
            @JsonValue
            public String getValue() {
                return GGE4_RELOAD;
            }
        },

        BALANCE_INQUIRY(){
            @JsonValue
            public String getValue() {
                return GGE4_BAL_INQUERY;
            }
        },   
        
        ACTIVATION(){
            @JsonValue
            public String getValue() {
                return GGE4_VALUELINK_ACTIVATION;
            }
        },

        DEACTIVATION(){
            @JsonValue
            public String getValue() {
                return GGE4_VALUELINK_DEACTIVATION;
            }
        },
                
        VOID(){

            @JsonValue
            public String getValue() {
                return GGE4_V12_VOID;
            }
        };
        
        
}
