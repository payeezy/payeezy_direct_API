package com.firstdata.firstapi.client.domain;


public interface TransactionTypeConstants {
    final String GGE4_POST = "00";
    final String GGE4_PREAUTH = "01";
    final String GGE4_V11_PREAUTH_COMPLETE = "02";
    final String GGE4_V11_REFUND = "04";
    final String GGE4_V11_VOID = "13";
    
    final String GGE4_V12_PREAUTH_COMPLETE = "32";
    final String GGE4_V12_VOID = "33";
    final String GGE4_V12_REFUND = "34";
    
    final String GGE4_CASHOUT = "83";
    final String GGE4_VALUELINK_ACTIVATION = "85";
    final String GGE4_VALUELINK_DEACTIVATION = "89";
    final String GGE4_BAL_INQUERY = "86";
    final String GGE4_RELOAD = "88";
    
    String getValue() ;
}
