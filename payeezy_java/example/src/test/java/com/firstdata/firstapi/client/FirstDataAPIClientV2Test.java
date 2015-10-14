package com.firstdata.firstapi.client;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;
import com.firstdata.firstapi.client.domain.TransactionType;
import com.firstdata.firstapi.client.domain.v2.Address;
import com.firstdata.firstapi.client.domain.v2.Card;
import com.firstdata.firstapi.client.domain.v2.Token;
import com.firstdata.firstapi.client.domain.v2.TransactionRequest;
import com.firstdata.firstapi.client.domain.v2.TransactionResponse;
import com.firstdata.firstapi.client.domain.v2.Transarmor;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:/META-INF/spring/core-rest-client-v2-context.xml"})
public class FirstDataAPIClientV2Test {
    
    private static final Logger log=LoggerFactory.getLogger(FirstDataAPIClientV2Test.class);
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private FirstAPIClientV2Helper client;
    
    @Test
    public void doPurchase()throws Exception {
        assertNotNull("RESTTemplate is null:", restTemplate);
        assertNotNull("clietn is null:", client);
        TransactionResponse response=client.purchaseTransaction(getPrimaryTransaction());
        assertNotNull("Response is null ",response);
        assertNull("Error in response",response.getError());
        log.info("Transaction Tag:{} Transaction id:{}",response.getTransactionTag(),response.getTransactionId());
    }
    
    //@Test
    public void doGetTAToken()throws Exception {
    	 log.info("+++++++++++++++++++++++++++++++++++++ start ++++++++++++++++++");
    	assertNotNull("RESTTemplate is null:", restTemplate);
        assertNotNull("clietn is null:", client);
        TransactionRequest trans = getPrimaryTransaction();
        trans.getCard().setNumber("4788250000028291");
        trans.getCard().setName("John Smith");
        trans.getCard().setExpiryDt("1030");
        FirstAPIClientV2Helper.OVERRIDE = "override";
        
        client.setAppId("fP0iYUx4oJ8LolKl2LiOT1Zo94mL0IDQ");
        client.setSecuredSecret("2b940ece234ee38131e70cc617aa2afa3d7ff8508856917958e7feb3ef190447");
        client.setToken("fdoa-a480ce8951daa73262734cf102641994c1e55e7cdf4c02b6");
        client.setTrToken("y6pzAbc3Def123");
        client.setMerchantid("OGEzNGU3NjM0ODQyMTU3NzAxNDg0MjE4NDY4ZTAwMDA=");
        
        client.setUrl("https://api-cert.payeezy.com/v1");
        FirstAPIClientV2Helper.OVERRIDE = "overrides";
        Token token = new Token();
        Transarmor ta = new Transarmor();
        ta.setNumber("6011000990099818");
        ta.setName("xyz");
        ta.setExpiryDt("0416");
        ta.setCvv("123");
        ta.setType("discover");
        
        ta.setValue(FirstAPIClientV2Helper.TA_TOKEN_VALUE);
        
        token.setTokenData(ta);
        token.setTokenType("FDToken");
        trans.setToken(token);
        trans.setAuth("false");
        
        //TransactionResponse response=client.getTokenTransaction(getPrimaryTransaction());
        TransactionResponse response=client.getTokenTransaction(trans);
        
        assertNotNull("Response is null ",response);
        assertNull("Error in response",response.getError());
        log.info("Transaction Tag:{} Transaction id:{}",response.getTransactionTag(),response.getTransactionId());
        log.info("++++++++++++++++++++++++++++++++++++++ end +++++++++++++++++");
    }
    
    //@Test
    public void doGetToken()throws Exception {
    	 log.info("+++++++++++++++++++++++++++++++++++++ start ++++++++++++++++++");
        assertNotNull("RESTTemplate is null:", restTemplate);
        assertNotNull("clietn is null:", client);
        TransactionRequest trans = getPrimaryTransaction();
        trans.getCard().setNumber("4788250000028291");
        trans.getCard().setName("John Smith");
        trans.getCard().setExpiryDt("1030");
        client.setAppId("y6pWAJNyJyjGv66IsVuWnklkKUPFbb0a");
        client.setSecuredSecret("y6pzAbc3Def123");
        client.setMerchantid("OGEzNGU3NjM0ODQyMTU3NzAxNDg0MjE4NDY4ZTAwMDA=");
        //client.setUrl("https://api-cert.payeezy.com/v1");
        client.setUrl("https://api-int.payeezy.com/v1");
        Token token = new Token();
        Transarmor ta = new Transarmor();
        ta.setNumber("4788250000028291");
        ta.setName("John Smith");
        ta.setExpiryDt("1030");
        ta.setCvv("123");
        ta.setType("visa");
        ta.setValue("");
        
        token.setTokenData(ta);
        token.setTokenType("payeezy");
        trans.setToken(token);
        trans.setAuth("false");
        
        //TransactionResponse response=client.getTokenTransaction(getPrimaryTransaction());
        TransactionResponse response=client.getTokenTransaction(trans);
        assertNotNull("Response is null ",response);
        assertNull("Error in response",response.getError());
        log.info("Transaction Tag:{} Transaction id:{}",response.getTransactionTag(),response.getTransactionId());
        log.info("++++++++++++++++++++++++++++++++++++++ end +++++++++++++++++");
    }
    

    
    @Test
    public void doPostTATokenGenerateSample() throws Exception {
    	 log.info("+++++++++++++++++++++++++++++++++++++ start ++++++++++++++++++");
        assertNotNull("RESTTemplate is null:", restTemplate);
        assertNotNull("clietn is null:", client);
        TransactionRequest trans = getPrimaryTransaction();
        
        trans.setType("FDToken");
        
        trans.getCard().setNumber("4788250000028291");
        trans.getCard().setName("JohnSmith");
        trans.getCard().setExpiryDt("1030");
        trans.getCard().setCvv("123");
        trans.getCard().setType("VISA");
        
        
        trans.setAuth("true");
        trans.setTa_token(FirstAPIClientV2Helper.TA_TOKEN_VALUE);
        
        trans.setToken(null);
        trans.setBilling(null);
        trans.setTransactionType(null);
        trans.setPaymentMethod(null);
        trans.setAmount(null);
        trans.setCurrency(null);
        
        TransactionResponse response=client.postTokenTransaction(trans);
        
        
        assertNotNull("Response is null ",response);
        assertNull("Error in response",response.getError());
        log.info("FD Token : " + response.getToken().getTokenData().getValue());
        log.info("++++++++++++++++++++++++++++++++++++++ end +++++++++++++++++");
    }
    
    @Test
    public void doPostTATokenGenerateSample2() throws Exception {
    	 log.info("+++++++++++++++++++++++++++++++++++++ start ++++++++++++++++++");
        assertNotNull("RESTTemplate is null:", restTemplate);
        assertNotNull("clietn is null:", client);
        TransactionRequest trans = getPrimaryTransaction();
        
        FirstAPIClientV2Helper.OVERRIDE = "override";
        
        trans.setType("FDToken");
        Card card = new Card();
        trans.setCard(card);
        
        trans.getCard().setNumber("4788250000028291");
        trans.getCard().setName("JohnSmith");
        trans.getCard().setExpiryDt("1030");
        trans.getCard().setCvv("123");
        trans.getCard().setType("VISA");
        
        trans.setAuth("false");
        //trans.setAuth("true");
        trans.setTa_token(FirstAPIClientV2Helper.TA_TOKEN_VALUE);
        
        trans.setToken(null);
        trans.setBilling(null);
        trans.setTransactionType(null);
        trans.setPaymentMethod(null);
        trans.setAmount(null);
        trans.setCurrency(null);
        
        TransactionResponse response=client.postTokenTransaction(trans);
        
        assertNotNull("Response is null ",response);
        assertNull("Error in response",response.getError());
        log.info("FD Token : " + response.getToken().getTokenData().getValue());
        log.info("++++++++++++++++++++++++++++++++++++++ end +++++++++++++++++");
    }
    
    //////////////////get token auth true///////////////////////////////
    

    @Test
    public void doPostTATokenGenerateAuthTrue() throws Exception {
    	 log.info("+++++++++++++++++++++++++++++++++++++ start ++++++++++++++++++");
        assertNotNull("RESTTemplate is null:", restTemplate);
        assertNotNull("clietn is null:", client);
        TransactionRequest trans = getPrimaryTransaction();
        
        trans.setType("FDToken");
        
        trans.getCard().setNumber("5424180279791732");
        trans.getCard().setName("John Smith");
        trans.getCard().setExpiryDt("0416");
        trans.getCard().setCvv("123");
        trans.getCard().setType("mastercard");
        
        trans.setAuth("true");
        trans.setTa_token("");
        
        trans.setToken(null);
        trans.setBilling(null);
        trans.setTransactionType(null);
        trans.setPaymentMethod(null);
        trans.setAmount(null);
        trans.setCurrency(null);
        
        TransactionResponse response=client.postTokenTransaction(trans);
        assertNotNull("Response is null ",response);
        assertNull("Error in response",response.getError());
        log.info("FD Token : " + response.getToken().getTokenData().getValue());
        log.info("++++++++++++++++++++++++++++++++++++++ end +++++++++++++++++");
    }
    
    
    
    ///////////////////////////gettoken mastercard/////////////////////////
    
    @Test
    public void doPostTATokenGenerate() throws Exception {
    	 log.info("+++++++++++++++++++++++++++++++++++++ start ++++++++++++++++++");
        assertNotNull("RESTTemplate is null:", restTemplate);
        assertNotNull("clietn is null:", client);
        TransactionRequest trans = getPrimaryTransaction();
        
        trans.setType("FDToken");
        
        trans.getCard().setNumber("5424180279791732");
        trans.getCard().setName("John Smith");
        trans.getCard().setExpiryDt("0416");
        trans.getCard().setCvv("123");
        trans.getCard().setType("mastercard");
        
        trans.setAuth("false");
        trans.setTa_token(FirstAPIClientV2Helper.TA_TOKEN_VALUE);
        
        trans.setToken(null);
        trans.setBilling(null);
        trans.setTransactionType(null);
        trans.setPaymentMethod(null);
        trans.setAmount(null);
        trans.setCurrency(null);
        
        TransactionResponse response=client.postTokenTransaction(trans);
        assertNotNull("Response is null ",response);
        assertNull("Error in response",response.getError());
        log.info("FD Token : " + response.getToken().getTokenData().getValue());
        log.info("++++++++++++++++++++++++++++++++++++++ end +++++++++++++++++");
    }
    
    
    @Test
    public void doPostTATokenAuthorize() throws Exception 
    {
    	 log.info("+++++++++++++++++++++++++++++++++++++ start ++++++++++++++++++");
    	// Generate Token
    	assertNotNull("RESTTemplate is null:", restTemplate);
        assertNotNull("clietn is null:", client);
        TransactionRequest transreq = getPrimaryTransaction();
        
        transreq.setType("FDToken");
        
        transreq.getCard().setNumber("5424180279791732");
        transreq.getCard().setName("John Smith");
        transreq.getCard().setExpiryDt("0416");
        transreq.getCard().setCvv("123");
        transreq.getCard().setType("mastercard");
        
        transreq.setAuth("false");
        transreq.setTa_token(FirstAPIClientV2Helper.TA_TOKEN_VALUE);
        
        transreq.setToken(null);
        transreq.setBilling(null);
        transreq.setTransactionType(null);
        transreq.setPaymentMethod(null);
        transreq.setAmount(null);
        transreq.setCurrency(null);
 
        TransactionResponse responseToken=client.postTokenTransaction(transreq);
        assertNotNull("Response is null ",responseToken);
        assertNull("Error in response",responseToken.getError());
        log.info("FD Token:{} ",responseToken.getToken().getTokenData().getValue());
    	
    	//Authorize
    	
    	assertNotNull("RESTTemplate is null:", restTemplate);
        assertNotNull("clietn is null:", client);
        
        TransactionRequest trans = getPrimaryTransaction();
        
        trans.setReferenceNo("abc1412096293369");
        trans.setTransactionType("authorize");
        trans.setPaymentMethod("token");
        trans.setAmount("0");
        trans.setCurrency("USD");
        
        Token token = new Token();
        Transarmor ta = new Transarmor();
        
        ta.setValue("2833693264441732");
        String s = responseToken.getToken().getTokenData().getValue();
        ta.setValue(responseToken.getToken().getTokenData().getValue());
        ta.setName("John Smith");
        ta.setExpiryDt("0416");
        ta.setType("mastercard");
        token.setTokenData(ta);
        token.setTokenType("FDToken");
        trans.setToken(token);
        
        trans.setCard(null);
        trans.setBilling(null);
        
        TransactionResponse response=client.postTokenTransaction(trans);
        assertNotNull("Response is null ",response);
        assertNull("Error in response",response.getError());
        log.info("Transaction Tag:{} Transaction id:{}",response.getTransactionTag(),response.getTransactionId());
        log.info("++++++++++++++++++++++++++++++++++++++ end +++++++++++++++++");
    }
    
    @Test
    public void doPostTATokenCapture() throws Exception {
    	 log.info("+++++++++++++++++++++++++++++++++++++ start ++++++++++++++++++");
    	// Generate Token
    	assertNotNull("RESTTemplate is null:", restTemplate);
        assertNotNull("clietn is null:", client);
        TransactionRequest transreq = getPrimaryTransaction();
        
        transreq.setType("FDToken");
        
        transreq.getCard().setNumber("5424180279791732");
        transreq.getCard().setName("John Smith");
        transreq.getCard().setExpiryDt("0416");
        transreq.getCard().setCvv("123");
        transreq.getCard().setType("mastercard");
        
        transreq.setAuth("false");
        transreq.setTa_token(FirstAPIClientV2Helper.TA_TOKEN_VALUE);
        
        transreq.setToken(null);
        transreq.setBilling(null);
        transreq.setTransactionType(null);
        transreq.setPaymentMethod(null);
        transreq.setAmount(null);
        transreq.setCurrency(null);
 
        TransactionResponse responseToken=client.postTokenTransaction(transreq);
        assertNotNull("Response is null ",responseToken);
        assertNull("Error in response",responseToken.getError());
        log.info("FD TOken : " + responseToken.getToken().getTokenData().getValue());
        
        //authorize
        assertNotNull("RESTTemplate is null:", restTemplate);
        assertNotNull("clietn is null:", client);
        
        TransactionRequest trans = getPrimaryTransaction();
        
        trans.setReferenceNo("abc1412096293369");
        trans.setTransactionType("authorize");
        trans.setPaymentMethod("token");
        trans.setAmount("1");
        trans.setCurrency("USD");
        
        Token token = new Token();
        Transarmor ta = new Transarmor();
        
        ta.setValue(responseToken.getToken().getTokenData().getValue());
        ta.setName(responseToken.getToken().getTokenData().getName());
        ta.setExpiryDt(responseToken.getToken().getTokenData().getExpiryDt());
        ta.setType(responseToken.getToken().getTokenData().getType());
        token.setTokenData(ta);
        token.setTokenType("FDToken");
        trans.setToken(token);
        
        trans.setCard(null);
        trans.setBilling(null);
        
        TransactionResponse response=client.postTokenTransaction(trans);
        assertNotNull("Response is null ",response);
        assertNull("Error in response",response.getError());
        log.info("FD TOken : " + responseToken.getToken().getTokenData().getValue());

    	
    	//Capture
        assertNotNull("RESTTemplate is null:", restTemplate);
        assertNotNull("clietn is null:", client);
        trans = getPrimaryTransaction();
        
        trans.setReferenceNo("abc1412096293369");
        trans.setTransactionTag("1871007");
        trans.setTransactionType(TransactionType.CAPTURE.name()) ; 
        trans.setPaymentMethod("token");
        trans.setAmount("1");
        trans.setCurrency("USD");
        trans.setTransactionTag(response.getTransactionTag());
        trans.setId(response.getTransactionId());
        
        token = new Token();
        ta = new Transarmor();
        
        ta.setValue(responseToken.getToken().getTokenData().getValue());
        ta.setName(responseToken.getToken().getTokenData().getName());
        ta.setExpiryDt(responseToken.getToken().getTokenData().getExpiryDt());
        ta.setType(responseToken.getToken().getTokenData().getType());
        
        token.setTokenData(ta);
        token.setTokenType("FDToken");
        trans.setToken(token);
        trans.setCard(null);
        trans.setBilling(null);
        
        TransactionResponse response2=client.postTokenTransaction(trans);
        assertNotNull("Response is null ",response2);
        assertNull("Error in response",response2.getError());
        log.info("Transaction Tag:{} Transaction id:{}",response2.getTransactionTag(),response2.getTransactionId());
        log.info("++++++++++++++++++++++++++++++++++++++ end +++++++++++++++++");
    }
    
    @Test
    public void doPostTATokenPurchase() throws Exception {
    	 log.info("+++++++++++++++++++++++++++++++++++++ start ++++++++++++++++++");
    	
    	// Generate Token
    	assertNotNull("RESTTemplate is null:", restTemplate);
        assertNotNull("clietn is null:", client);
        TransactionRequest transreq = getPrimaryTransaction();
        
        transreq.setType("FDToken");
        
        transreq.getCard().setNumber("5424180279791732");
        transreq.getCard().setName("John Smith");
        transreq.getCard().setExpiryDt("0416");
        transreq.getCard().setCvv("123");
        transreq.getCard().setType("mastercard");
        
        transreq.setAuth("false");
        transreq.setTa_token(FirstAPIClientV2Helper.TA_TOKEN_VALUE);
        
        transreq.setToken(null);
        transreq.setBilling(null);
        transreq.setTransactionType(null);
        transreq.setPaymentMethod(null);
        transreq.setAmount(null);
        transreq.setCurrency(null);
 
        TransactionResponse responseToken=client.postTokenTransaction(transreq);
        assertNotNull("Response is null ",responseToken);
        assertNull("Error in response",responseToken.getError());
        log.info("FD TOken : " + responseToken.getToken().getTokenData().getValue());
    	
        //purchase
        assertNotNull("RESTTemplate is null:", restTemplate);
        assertNotNull("clietn is null:", client);
        TransactionRequest trans = getPrimaryTransaction();
        
        trans.setReferenceNo("Astonishing-Sale");
        trans.setTransactionType("purchase");
        trans.setPaymentMethod("token");
        trans.setAmount("1");
        trans.setCurrency("USD");
        
        Token token = new Token();
        Transarmor ta = new Transarmor();

        ta.setValue(responseToken.getToken().getTokenData().getValue());
        ta.setName(responseToken.getToken().getTokenData().getName());
        ta.setExpiryDt(responseToken.getToken().getTokenData().getExpiryDt());
        ta.setType(responseToken.getToken().getTokenData().getType());
        
        token.setTokenData(ta);
        token.setTokenType("FDToken");
        trans.setToken(token);
        
        trans.setCard(null);
        trans.setBilling(null);
        
        //TransactionResponse response=client.postTokenTransaction(trans);
        TransactionResponse response=client.purchaseTransaction(trans);
        assertNotNull("Response is null ",response);
        assertNull("Error in response",response.getError());
        log.info("Transaction Tag:{} Transaction id:{}",response.getTransactionTag(),response.getTransactionId());
        log.info("++++++++++++++++++++++++++++++++++++++ end +++++++++++++++++");
    }
    

    
    @Test
    public void doPostTATokenRefund() throws Exception {
    	 log.info("+++++++++++++++++++++++++++++++++++++ start ++++++++++++++++++");
    	
    	// Generate Token
    	assertNotNull("RESTTemplate is null:", restTemplate);
        assertNotNull("clietn is null:", client);
        TransactionRequest transreq = getPrimaryTransaction();
        
        transreq.setType("FDToken");
        
        transreq.getCard().setNumber("5424180279791732");
        transreq.getCard().setName("John Smith");
        transreq.getCard().setExpiryDt("0416");
        transreq.getCard().setCvv("123");
        transreq.getCard().setType("mastercard");
        
        transreq.setAuth("false");
        transreq.setTa_token(FirstAPIClientV2Helper.TA_TOKEN_VALUE);
        
        transreq.setToken(null);
        transreq.setBilling(null);
        transreq.setTransactionType(null);
        transreq.setPaymentMethod(null);
        transreq.setAmount(null);
        transreq.setCurrency(null);
 
        TransactionResponse responseToken=client.postTokenTransaction(transreq);
        assertNotNull("Response is null ",responseToken);
        assertNull("Error in response",responseToken.getError());
        log.info("FD Token : " + responseToken.getToken().getTokenData().getValue());
    	
    	
    	//purchase
        assertNotNull("RESTTemplate is null:", restTemplate);
        assertNotNull("clietn is null:", client);
        TransactionRequest trans = getPrimaryTransaction();
        
        trans.setReferenceNo("abc1412096293369");
        trans.setTransactionType("purchase");
        trans.setPaymentMethod("token");
        trans.setAmount("1");
        trans.setCurrency("USD");
        
        Token token = new Token();
        Transarmor ta = new Transarmor();
        
        ta.setValue(responseToken.getToken().getTokenData().getValue());
        ta.setName(responseToken.getToken().getTokenData().getName());
        ta.setExpiryDt(responseToken.getToken().getTokenData().getExpiryDt());
        ta.setType(responseToken.getToken().getTokenData().getType());
        
        
        token.setTokenData(ta);
        token.setTokenType("FDToken");
        trans.setToken(token);
        
        trans.setCard(null);
        trans.setBilling(null);
        
        TransactionResponse response=client.postTokenTransaction(trans);
        assertNotNull("Response is null ",response);
        assertNull("Error in response",response.getError());
        log.info("Transaction Tag:{} Transaction id:{}",response.getTransactionTag(),response.getTransactionId());
        
        
    	//refund
        assertNotNull("RESTTemplate is null:", restTemplate);
        assertNotNull("clietn is null:", client);
        trans = getPrimaryTransaction();
        
        trans.setReferenceNo("abc1412096293369");
        trans.setTransactionType(TransactionType.REFUND.name()) ; 
        trans.setPaymentMethod("token");
        trans.setAmount("1");
        trans.setCurrency("USD");
        
        trans.setTransactionTag(response.getTransactionTag());
        trans.setId(response.getTransactionId());
        
        token = new Token();
        ta = new Transarmor();
        
        ta.setValue(responseToken.getToken().getTokenData().getValue());
        ta.setName(responseToken.getToken().getTokenData().getName());
        ta.setExpiryDt(responseToken.getToken().getTokenData().getExpiryDt());
        ta.setType(responseToken.getToken().getTokenData().getType());
        
        token.setTokenData(ta);
        token.setTokenType("FDToken");
        trans.setToken(token);
        
        trans.setCard(null);
        trans.setBilling(null);
        
        TransactionResponse response2=client.postTokenTransaction(trans);
        //TransactionResponse response2=client.refundTransaction(trans);
        assertNotNull("Response is null ",response2);
        assertNull("Error in response",response2.getError());
        log.info("Transaction Tag:{} Transaction id:{}",response2.getTransactionTag(),response2.getTransactionId());
        log.info("++++++++++++++++++++++++++++++++++++++ end +++++++++++++++++");
    }
    
    @Test
    public void doPostTATokenVoid() throws Exception {
    	 log.info("+++++++++++++++++++++++++++++++++++++ start ++++++++++++++++++");
    	// Generate Token
    	assertNotNull("RESTTemplate is null:", restTemplate);
        assertNotNull("clietn is null:", client);
        TransactionRequest transreq = getPrimaryTransaction();
        
        transreq.setType("FDToken");
        
        transreq.getCard().setNumber("5424180279791732");
        transreq.getCard().setName("John Smith");
        transreq.getCard().setExpiryDt("0416");
        transreq.getCard().setCvv("123");
        transreq.getCard().setType("mastercard");
        
        transreq.setAuth("false");
        transreq.setTa_token(FirstAPIClientV2Helper.TA_TOKEN_VALUE);
        
        transreq.setToken(null);
        transreq.setBilling(null);
        transreq.setTransactionType(null);
        transreq.setPaymentMethod(null);
        transreq.setAmount(null);
        transreq.setCurrency(null);
 
        TransactionResponse responseToken=client.postTokenTransaction(transreq);
        assertNotNull("Response is null ",responseToken);
        assertNull("Error in response",responseToken.getError());
        //log.info("Transaction Tag:{} Transaction id:{}",responseToken.getTransactionTag(),responseToken.getTransactionId());
        log.info("FD Token : " + responseToken.getToken().getTokenData().getValue());
    	
    	//purchase
        assertNotNull("RESTTemplate is null:", restTemplate);
        assertNotNull("clietn is null:", client);
        TransactionRequest trans = getPrimaryTransaction();
        
        trans.setReferenceNo("abc1412096293369");
        trans.setTransactionType("purchase");
        trans.setPaymentMethod("token");
        trans.setAmount("1");
        trans.setCurrency("USD");
        
        Token token = new Token();
        Transarmor ta = new Transarmor();
        
        ta.setValue(responseToken.getToken().getTokenData().getValue());
        ta.setName(responseToken.getToken().getTokenData().getName());
        ta.setExpiryDt(responseToken.getToken().getTokenData().getExpiryDt());
        ta.setType(responseToken.getToken().getTokenData().getType());
        
        token.setTokenData(ta);
        token.setTokenType("FDToken");
        trans.setToken(token);
        
        trans.setCard(null);
        trans.setBilling(null);
        
        TransactionResponse response=client.postTokenTransaction(trans);
        assertNotNull("Response is null ",response);
        assertNull("Error in response",response.getError());
        log.info("Transaction Tag:{} Transaction id:{}",response.getTransactionTag(),response.getTransactionId());
        
        
        
    	//void
        assertNotNull("RESTTemplate is null:", restTemplate);
        assertNotNull("clietn is null:", client);
        trans = getPrimaryTransaction();
        trans.setTransactionType(TransactionType.VOID.name()) ; 
        
        trans.setReferenceNo("abc1412096293369");
        trans.setPaymentMethod("token");
        trans.setAmount("1");
        trans.setCurrency("USD");
        
        trans.setTransactionTag(response.getTransactionTag());
        trans.setId(response.getTransactionId());
        
        token = new Token();
        ta = new Transarmor();
        
        ta.setValue(responseToken.getToken().getTokenData().getValue());
        ta.setName(responseToken.getToken().getTokenData().getName());
        ta.setExpiryDt(responseToken.getToken().getTokenData().getExpiryDt());
        ta.setType(responseToken.getToken().getTokenData().getType());
        
        token.setTokenData(ta);
        token.setTokenType("FDToken");
        trans.setToken(token);
        
        trans.setCard(null);
        trans.setBilling(null);
        
        TransactionResponse response2=client.postTokenTransaction(trans);
        assertNotNull("Response is null ",response2);
        assertNull("Error in response",response2.getError());
        log.info("Transaction Tag:{} Transaction id:{}",response2.getTransactionTag(),response2.getTransactionId());
        log.info("++++++++++++++++++++++++++++++++++++++ end +++++++++++++++++");
    
    }
    
    
    /////////////////////////gettoken visa/////////////////
    

    @Test
    public void doPostTATokenAuthorizeVisa() throws Exception 
    {
    	 log.info("+++++++++++++++++++++++++++++++++++++ start ++++++++++++++++++");
    	// Generate Token
    	assertNotNull("RESTTemplate is null:", restTemplate);
        assertNotNull("clietn is null:", client);
        TransactionRequest transreq = getPrimaryTransaction();
        
        
        transreq.setType("FDToken");
        
        transreq.getCard().setNumber("4012000033330026");
        transreq.getCard().setName("John Smith");
        transreq.getCard().setExpiryDt("0416");
        transreq.getCard().setCvv("123");
        transreq.getCard().setType("visa");
        
        transreq.setAuth("false");
        transreq.setTa_token(FirstAPIClientV2Helper.TA_TOKEN_VALUE);
        
        transreq.setToken(null);
        transreq.setBilling(null);
        transreq.setTransactionType(null);
        transreq.setPaymentMethod(null);
        transreq.setAmount(null);
        transreq.setCurrency(null);
 
        TransactionResponse responseToken=client.postTokenTransaction(transreq);
        assertNotNull("Response is null ",responseToken);
        assertNull("Error in response",responseToken.getError());
        log.info("FD Token:{} ",responseToken.getToken().getTokenData().getValue());
    	
    	//Authorize
    	
    	assertNotNull("RESTTemplate is null:", restTemplate);
        assertNotNull("clietn is null:", client);
        
        TransactionRequest trans = getPrimaryTransaction();
        
        trans.setReferenceNo("abc1412096293369");
        trans.setTransactionType("authorize");
        trans.setPaymentMethod("token");
        trans.setAmount("0");
        trans.setCurrency("USD");
        
        Token token = new Token();
        Transarmor ta = new Transarmor();
        
        ta.setValue("2833693264441732");
        String s = responseToken.getToken().getTokenData().getValue();
        ta.setValue(responseToken.getToken().getTokenData().getValue());
        ta.setName("John Smith");
        ta.setExpiryDt("0416");
        ta.setType("visa");
        token.setTokenData(ta);
        token.setTokenType("FDToken");
        trans.setToken(token);
        
        trans.setCard(null);
        trans.setBilling(null);
        
        TransactionResponse response=client.postTokenTransaction(trans);
        assertNotNull("Response is null ",response);
        assertNull("Error in response",response.getError());
        log.info("Transaction Tag:{} Transaction id:{}",response.getTransactionTag(),response.getTransactionId());
        log.info("++++++++++++++++++++++++++++++++++++++ end +++++++++++++++++");
    }
    
    @Test
    public void doPostTATokenCaptureVisa() throws Exception {
    	 log.info("+++++++++++++++++++++++++++++++++++++ start ++++++++++++++++++");
    	// Generate Token
    	assertNotNull("RESTTemplate is null:", restTemplate);
        assertNotNull("clietn is null:", client);
        TransactionRequest transreq = getPrimaryTransaction();
        
        transreq.setType("FDToken");
        
        transreq.getCard().setNumber("4012000033330026");
        transreq.getCard().setName("John Smith");
        transreq.getCard().setExpiryDt("0416");
        transreq.getCard().setCvv("123");
        transreq.getCard().setType("visa");
        
        transreq.setAuth("false");
        transreq.setTa_token(FirstAPIClientV2Helper.TA_TOKEN_VALUE);
        
        transreq.setToken(null);
        transreq.setBilling(null);
        transreq.setTransactionType(null);
        transreq.setPaymentMethod(null);
        transreq.setAmount(null);
        transreq.setCurrency(null);
 
        TransactionResponse responseToken=client.postTokenTransaction(transreq);
        assertNotNull("Response is null ",responseToken);
        assertNull("Error in response",responseToken.getError());
        log.info("FD TOken : " + responseToken.getToken().getTokenData().getValue());
        
        //authorize
        assertNotNull("RESTTemplate is null:", restTemplate);
        assertNotNull("clietn is null:", client);
        
        TransactionRequest trans = getPrimaryTransaction();
        
        trans.setReferenceNo("abc1412096293369");
        trans.setTransactionType("authorize");
        trans.setPaymentMethod("token");
        trans.setAmount("1");
        trans.setCurrency("USD");
        
        Token token = new Token();
        Transarmor ta = new Transarmor();
        
        ta.setValue(responseToken.getToken().getTokenData().getValue());
        ta.setName(responseToken.getToken().getTokenData().getName());
        ta.setExpiryDt(responseToken.getToken().getTokenData().getExpiryDt());
        ta.setType(responseToken.getToken().getTokenData().getType());
        token.setTokenData(ta);
        token.setTokenType("FDToken");
        trans.setToken(token);
        
        trans.setCard(null);
        trans.setBilling(null);
        
        TransactionResponse response=client.postTokenTransaction(trans);
        assertNotNull("Response is null ",response);
        assertNull("Error in response",response.getError());
        log.info("FD TOken : " + responseToken.getToken().getTokenData().getValue());

    	
    	//Capture
        assertNotNull("RESTTemplate is null:", restTemplate);
        assertNotNull("clietn is null:", client);
        trans = getPrimaryTransaction();
        
        trans.setReferenceNo("abc1412096293369");
        trans.setTransactionTag("1871007");
        trans.setTransactionType(TransactionType.CAPTURE.name()) ; 
        trans.setPaymentMethod("token");
        trans.setAmount("1");
        trans.setCurrency("USD");
        trans.setTransactionTag(response.getTransactionTag());
        trans.setId(response.getTransactionId());
        
        token = new Token();
        ta = new Transarmor();
        
        ta.setValue(responseToken.getToken().getTokenData().getValue());
        ta.setName(responseToken.getToken().getTokenData().getName());
        ta.setExpiryDt(responseToken.getToken().getTokenData().getExpiryDt());
        ta.setType(responseToken.getToken().getTokenData().getType());
        
        token.setTokenData(ta);
        token.setTokenType("FDToken");
        trans.setToken(token);
        trans.setCard(null);
        trans.setBilling(null);
        
        TransactionResponse response2=client.postTokenTransaction(trans);
        assertNotNull("Response is null ",response2);
        assertNull("Error in response",response2.getError());
        log.info("Transaction Tag:{} Transaction id:{}",response2.getTransactionTag(),response2.getTransactionId());
        log.info("++++++++++++++++++++++++++++++++++++++ end +++++++++++++++++");
    }
    
    @Test
    public void doPostTATokenPurchaseVisa() throws Exception {
    	 log.info("+++++++++++++++++++++++++++++++++++++ start ++++++++++++++++++");
    	// Generate Token
    	assertNotNull("RESTTemplate is null:", restTemplate);
        assertNotNull("clietn is null:", client);
        TransactionRequest transreq = getPrimaryTransaction();
        
        transreq.setType("FDToken");
        
        transreq.getCard().setNumber("4012000033330026");
        transreq.getCard().setName("John Smith");
        transreq.getCard().setExpiryDt("0416");
        transreq.getCard().setCvv("123");
        transreq.getCard().setType("visa");
        
        transreq.setAuth("false");
        transreq.setTa_token(FirstAPIClientV2Helper.TA_TOKEN_VALUE);
        
        transreq.setToken(null);
        transreq.setBilling(null);
        transreq.setTransactionType(null);
        transreq.setPaymentMethod(null);
        transreq.setAmount(null);
        transreq.setCurrency(null);
 
        TransactionResponse responseToken=client.postTokenTransaction(transreq);
        assertNotNull("Response is null ",responseToken);
        assertNull("Error in response",responseToken.getError());
        log.info("FD TOken : " + responseToken.getToken().getTokenData().getValue());
    	
        //purchase
        assertNotNull("RESTTemplate is null:", restTemplate);
        assertNotNull("clietn is null:", client);
        TransactionRequest trans = getPrimaryTransaction();
        
        trans.setReferenceNo("Astonishing-Sale");
        trans.setTransactionType("purchase");
        trans.setPaymentMethod("token");
        trans.setAmount("1");
        trans.setCurrency("USD");
        
        Token token = new Token();
        Transarmor ta = new Transarmor();

        ta.setValue(responseToken.getToken().getTokenData().getValue());
        ta.setName(responseToken.getToken().getTokenData().getName());
        ta.setExpiryDt(responseToken.getToken().getTokenData().getExpiryDt());
        ta.setType(responseToken.getToken().getTokenData().getType());
        
        token.setTokenData(ta);
        token.setTokenType("FDToken");
        trans.setToken(token);
        
        trans.setCard(null);
        trans.setBilling(null);
        
        //TransactionResponse response=client.postTokenTransaction(trans);
        TransactionResponse response=client.purchaseTransaction(trans);
        assertNotNull("Response is null ",response);
        assertNull("Error in response",response.getError());
        log.info("Transaction Tag:{} Transaction id:{}",response.getTransactionTag(),response.getTransactionId());
        log.info("++++++++++++++++++++++++++++++++++++++ end +++++++++++++++++");
    }
    
    @Test
    public void doPostTATokenGenerateVisa() throws Exception {
    	 log.info("+++++++++++++++++++++++++++++++++++++ start ++++++++++++++++++");
        assertNotNull("RESTTemplate is null:", restTemplate);
        assertNotNull("clietn is null:", client);
        TransactionRequest trans = getPrimaryTransaction();
        
        trans.setType("FDToken");
        
        trans.getCard().setNumber("4012000033330026");
        trans.getCard().setName("John Smith");
        trans.getCard().setExpiryDt("0416");
        trans.getCard().setCvv("123");
        trans.getCard().setType("visa");
        
        trans.setAuth("false");
        trans.setTa_token(FirstAPIClientV2Helper.TA_TOKEN_VALUE);
        
        trans.setToken(null);
        trans.setBilling(null);
        trans.setTransactionType(null);
        trans.setPaymentMethod(null);
        trans.setAmount(null);
        trans.setCurrency(null);
        
        TransactionResponse response=client.postTokenTransaction(trans);
        assertNotNull("Response is null ",response);
        assertNull("Error in response",response.getError());
        log.info("FD Token : " + response.getToken().getTokenData().getValue());
        log.info("++++++++++++++++++++++++++++++++++++++ end +++++++++++++++++");
    }
    
    @Test
    public void doPostTATokenRefundVisa() throws Exception {
    	 log.info("+++++++++++++++++++++++++++++++++++++ start ++++++++++++++++++");
    	
    	// Generate Token
    	assertNotNull("RESTTemplate is null:", restTemplate);
        assertNotNull("clietn is null:", client);
        TransactionRequest transreq = getPrimaryTransaction();
        
        transreq.setType("FDToken");
        
        transreq.getCard().setNumber("4012000033330026");
        transreq.getCard().setName("John Smith");
        transreq.getCard().setExpiryDt("0416");
        transreq.getCard().setCvv("123");
        transreq.getCard().setType("visa");
        
        transreq.setAuth("false");
        transreq.setTa_token(FirstAPIClientV2Helper.TA_TOKEN_VALUE);
        
        transreq.setToken(null);
        transreq.setBilling(null);
        transreq.setTransactionType(null);
        transreq.setPaymentMethod(null);
        transreq.setAmount(null);
        transreq.setCurrency(null);
 
        TransactionResponse responseToken=client.postTokenTransaction(transreq);
        assertNotNull("Response is null ",responseToken);
        assertNull("Error in response",responseToken.getError());
        log.info("FD Token : " + responseToken.getToken().getTokenData().getValue());
    	
    	
    	//purchase
        assertNotNull("RESTTemplate is null:", restTemplate);
        assertNotNull("clietn is null:", client);
        TransactionRequest trans = getPrimaryTransaction();
        
        trans.setReferenceNo("abc1412096293369");
        trans.setTransactionType("purchase");
        trans.setPaymentMethod("token");
        trans.setAmount("1");
        trans.setCurrency("USD");
        
        Token token = new Token();
        Transarmor ta = new Transarmor();
        
        ta.setValue(responseToken.getToken().getTokenData().getValue());
        ta.setName(responseToken.getToken().getTokenData().getName());
        ta.setExpiryDt(responseToken.getToken().getTokenData().getExpiryDt());
        ta.setType(responseToken.getToken().getTokenData().getType());
        
        
        token.setTokenData(ta);
        token.setTokenType("FDToken");
        trans.setToken(token);
        
        trans.setCard(null);
        trans.setBilling(null);
        
        TransactionResponse response=client.postTokenTransaction(trans);
        assertNotNull("Response is null ",response);
        assertNull("Error in response",response.getError());
        log.info("Transaction Tag:{} Transaction id:{}",response.getTransactionTag(),response.getTransactionId());
        
        
    	//refund
        assertNotNull("RESTTemplate is null:", restTemplate);
        assertNotNull("clietn is null:", client);
        trans = getPrimaryTransaction();
        
        trans.setReferenceNo("abc1412096293369");
        trans.setTransactionType(TransactionType.REFUND.name()) ; 
        trans.setPaymentMethod("token");
        trans.setAmount("1");
        trans.setCurrency("USD");
        
        trans.setTransactionTag(response.getTransactionTag());
        trans.setId(response.getTransactionId());
        
        token = new Token();
        ta = new Transarmor();
        
        ta.setValue(responseToken.getToken().getTokenData().getValue());
        ta.setName(responseToken.getToken().getTokenData().getName());
        ta.setExpiryDt(responseToken.getToken().getTokenData().getExpiryDt());
        ta.setType(responseToken.getToken().getTokenData().getType());
        
        token.setTokenData(ta);
        token.setTokenType("FDToken");
        trans.setToken(token);
        
        trans.setCard(null);
        trans.setBilling(null);
        
        TransactionResponse response2=client.postTokenTransaction(trans);
        //TransactionResponse response2=client.refundTransaction(trans);
        assertNotNull("Response is null ",response2);
        assertNull("Error in response",response2.getError());
        log.info("Transaction Tag:{} Transaction id:{}",response2.getTransactionTag(),response2.getTransactionId());
        log.info("++++++++++++++++++++++++++++++++++++++ end +++++++++++++++++");
    }
    
    @Test
    public void doPostTATokenVoidVisa() throws Exception {
    	 log.info("+++++++++++++++++++++++++++++++++++++ start ++++++++++++++++++");
    	
    	// Generate Token
    	assertNotNull("RESTTemplate is null:", restTemplate);
        assertNotNull("clietn is null:", client);
        TransactionRequest transreq = getPrimaryTransaction();
        
        transreq.setType("FDToken");
        
        transreq.getCard().setNumber("4012000033330026");
        transreq.getCard().setName("John Smith");
        transreq.getCard().setExpiryDt("0416");
        transreq.getCard().setCvv("123");
        transreq.getCard().setType("visa");
        
        transreq.setAuth("false");
        transreq.setTa_token(FirstAPIClientV2Helper.TA_TOKEN_VALUE);
        
        transreq.setToken(null);
        transreq.setBilling(null);
        transreq.setTransactionType(null);
        transreq.setPaymentMethod(null);
        transreq.setAmount(null);
        transreq.setCurrency(null);
 
        TransactionResponse responseToken=client.postTokenTransaction(transreq);
        assertNotNull("Response is null ",responseToken);
        assertNull("Error in response",responseToken.getError());
        //log.info("Transaction Tag:{} Transaction id:{}",responseToken.getTransactionTag(),responseToken.getTransactionId());
        log.info("FD Token : " + responseToken.getToken().getTokenData().getValue());
    	
    	//purchase
        assertNotNull("RESTTemplate is null:", restTemplate);
        assertNotNull("clietn is null:", client);
        TransactionRequest trans = getPrimaryTransaction();
        
        trans.setReferenceNo("abc1412096293369");
        trans.setTransactionType("purchase");
        trans.setPaymentMethod("token");
        trans.setAmount("1");
        trans.setCurrency("USD");
        
        Token token = new Token();
        Transarmor ta = new Transarmor();
        
        ta.setValue(responseToken.getToken().getTokenData().getValue());
        ta.setName(responseToken.getToken().getTokenData().getName());
        ta.setExpiryDt(responseToken.getToken().getTokenData().getExpiryDt());
        ta.setType(responseToken.getToken().getTokenData().getType());
        
        token.setTokenData(ta);
        token.setTokenType("FDToken");
        trans.setToken(token);
        
        trans.setCard(null);
        trans.setBilling(null);
        
        TransactionResponse response=client.postTokenTransaction(trans);
        assertNotNull("Response is null ",response);
        assertNull("Error in response",response.getError());
        log.info("Transaction Tag:{} Transaction id:{}",response.getTransactionTag(),response.getTransactionId());
        
        
        
    	//void
        assertNotNull("RESTTemplate is null:", restTemplate);
        assertNotNull("clietn is null:", client);
        trans = getPrimaryTransaction();
        trans.setTransactionType(TransactionType.VOID.name()) ; 
        
        trans.setReferenceNo("abc1412096293369");
        trans.setPaymentMethod("token");
        trans.setAmount("1");
        trans.setCurrency("USD");
        
        trans.setTransactionTag(response.getTransactionTag());
        trans.setId(response.getTransactionId());
        
        token = new Token();
        ta = new Transarmor();
        
        ta.setValue(responseToken.getToken().getTokenData().getValue());
        ta.setName(responseToken.getToken().getTokenData().getName());
        ta.setExpiryDt(responseToken.getToken().getTokenData().getExpiryDt());
        ta.setType(responseToken.getToken().getTokenData().getType());
        
        token.setTokenData(ta);
        token.setTokenType("FDToken");
        trans.setToken(token);
        
        trans.setCard(null);
        trans.setBilling(null);
        
        TransactionResponse response2=client.postTokenTransaction(trans);
        assertNotNull("Response is null ",response2);
        assertNull("Error in response",response2.getError());
        log.info("Transaction Tag:{} Transaction id:{}",response2.getTransactionTag(),response2.getTransactionId());
        log.info("++++++++++++++++++++++++++++++++++++++ end +++++++++++++++++");
    
    }
    
    
    //////////////////////gettoken amex //////////////
    

    @Test
    public void doPostTATokenAuthorizeAmex() throws Exception 
    {
    	 log.info("+++++++++++++++++++++++++++++++++++++ start ++++++++++++++++++");
    	// Generate Token
    	assertNotNull("RESTTemplate is null:", restTemplate);
        assertNotNull("clietn is null:", client);
        TransactionRequest transreq = getPrimaryTransaction();
        
        transreq.setType("FDToken");
        
        transreq.getCard().setNumber("373953192351004");
        transreq.getCard().setName("John Smith");
        transreq.getCard().setExpiryDt("0416");
        transreq.getCard().setCvv("1234");
        transreq.getCard().setType("American Express");
        
        transreq.setAuth("false");
        transreq.setTa_token(FirstAPIClientV2Helper.TA_TOKEN_VALUE);
        
        transreq.setToken(null);
        transreq.setBilling(null);
        transreq.setTransactionType(null);
        transreq.setPaymentMethod(null);
        transreq.setAmount(null);
        transreq.setCurrency(null);
 
        TransactionResponse responseToken=client.postTokenTransaction(transreq);
        assertNotNull("Response is null ",responseToken);
        assertNull("Error in response",responseToken.getError());
        log.info("FD Token:{} ",responseToken.getToken().getTokenData().getValue());
    	
    	//Authorize
    	
    	assertNotNull("RESTTemplate is null:", restTemplate);
        assertNotNull("clietn is null:", client);
        
        TransactionRequest trans = getPrimaryTransaction();
        
        trans.setReferenceNo("abc1412096293369");
        trans.setTransactionType("authorize");
        trans.setPaymentMethod("token");
        trans.setAmount("0");
        trans.setCurrency("USD");
        
        Token token = new Token();
        Transarmor ta = new Transarmor();
        
        ta.setValue("2833693264441732");
        String s = responseToken.getToken().getTokenData().getValue();
        ta.setValue(responseToken.getToken().getTokenData().getValue());
        ta.setName("John Smith");
        ta.setExpiryDt("0416");
        ta.setType("American Express");
        token.setTokenData(ta);
        token.setTokenType("FDToken");
        trans.setToken(token);
        
        trans.setCard(null);
        trans.setBilling(null);
        
        TransactionResponse response=client.postTokenTransaction(trans);
        assertNotNull("Response is null ",response);
        assertNull("Error in response",response.getError());
        log.info("Transaction Tag:{} Transaction id:{}",response.getTransactionTag(),response.getTransactionId());
        log.info("++++++++++++++++++++++++++++++++++++++ end +++++++++++++++++");
    }
    
    @Test
    public void doPostTATokenCaptureAmex() throws Exception {
    	 log.info("+++++++++++++++++++++++++++++++++++++ start ++++++++++++++++++");
    	// Generate Token
    	assertNotNull("RESTTemplate is null:", restTemplate);
        assertNotNull("clietn is null:", client);
        TransactionRequest transreq = getPrimaryTransaction();
        
        transreq.setType("FDToken");
        
        transreq.getCard().setNumber("373953192351004");
        transreq.getCard().setName("John Smith");
        transreq.getCard().setExpiryDt("0416");
        transreq.getCard().setCvv("1234");
        transreq.getCard().setType("American Express");
        
        transreq.setAuth("false");
        transreq.setTa_token(FirstAPIClientV2Helper.TA_TOKEN_VALUE);
        
        transreq.setToken(null);
        transreq.setBilling(null);
        transreq.setTransactionType(null);
        transreq.setPaymentMethod(null);
        transreq.setAmount(null);
        transreq.setCurrency(null);
 
        TransactionResponse responseToken=client.postTokenTransaction(transreq);
        assertNotNull("Response is null ",responseToken);
        assertNull("Error in response",responseToken.getError());
        log.info("FD TOken : " + responseToken.getToken().getTokenData().getValue());
        
        //authorize
        assertNotNull("RESTTemplate is null:", restTemplate);
        assertNotNull("clietn is null:", client);
        
        TransactionRequest trans = getPrimaryTransaction();
        
        trans.setReferenceNo("abc1412096293369");
        trans.setTransactionType("authorize");
        trans.setPaymentMethod("token");
        trans.setAmount("1");
        trans.setCurrency("USD");
        
        Token token = new Token();
        Transarmor ta = new Transarmor();
        
        ta.setValue(responseToken.getToken().getTokenData().getValue());
        ta.setName(responseToken.getToken().getTokenData().getName());
        ta.setExpiryDt(responseToken.getToken().getTokenData().getExpiryDt());
        ta.setType(responseToken.getToken().getTokenData().getType());
        token.setTokenData(ta);
        token.setTokenType("FDToken");
        trans.setToken(token);
        
        trans.setCard(null);
        trans.setBilling(null);
        
        TransactionResponse response=client.postTokenTransaction(trans);
        assertNotNull("Response is null ",response);
        assertNull("Error in response",response.getError());
        log.info("FD TOken : " + responseToken.getToken().getTokenData().getValue());

    	
    	//Capture
        assertNotNull("RESTTemplate is null:", restTemplate);
        assertNotNull("clietn is null:", client);
        trans = getPrimaryTransaction();
        
        trans.setReferenceNo("abc1412096293369");
        trans.setTransactionTag("1871007");
        trans.setTransactionType(TransactionType.CAPTURE.name()) ; 
        trans.setPaymentMethod("token");
        trans.setAmount("1");
        trans.setCurrency("USD");
        trans.setTransactionTag(response.getTransactionTag());
        trans.setId(response.getTransactionId());
        
        token = new Token();
        ta = new Transarmor();
        
        ta.setValue(responseToken.getToken().getTokenData().getValue());
        ta.setName(responseToken.getToken().getTokenData().getName());
        ta.setExpiryDt(responseToken.getToken().getTokenData().getExpiryDt());
        ta.setType(responseToken.getToken().getTokenData().getType());
        
        token.setTokenData(ta);
        token.setTokenType("FDToken");
        trans.setToken(token);
        trans.setCard(null);
        trans.setBilling(null);
        
        TransactionResponse response2=client.postTokenTransaction(trans);
        assertNotNull("Response is null ",response2);
        assertNull("Error in response",response2.getError());
        log.info("Transaction Tag:{} Transaction id:{}",response2.getTransactionTag(),response2.getTransactionId());
        log.info("++++++++++++++++++++++++++++++++++++++ end +++++++++++++++++");
    }
    
    @Test
    public void doPostTATokenPurchaseAmex() throws Exception {
    	 log.info("+++++++++++++++++++++++++++++++++++++ start ++++++++++++++++++");
    	
    	// Generate Token
    	assertNotNull("RESTTemplate is null:", restTemplate);
        assertNotNull("clietn is null:", client);
        TransactionRequest transreq = getPrimaryTransaction();
        
        transreq.setType("FDToken");
        
        transreq.getCard().setNumber("373953192351004");
        transreq.getCard().setName("John Smith");
        transreq.getCard().setExpiryDt("0416");
        transreq.getCard().setCvv("1234");
        transreq.getCard().setType("American Express");
        
        transreq.setAuth("false");
        transreq.setTa_token(FirstAPIClientV2Helper.TA_TOKEN_VALUE);
        
        transreq.setToken(null);
        transreq.setBilling(null);
        transreq.setTransactionType(null);
        transreq.setPaymentMethod(null);
        transreq.setAmount(null);
        transreq.setCurrency(null);
 
        TransactionResponse responseToken=client.postTokenTransaction(transreq);
        assertNotNull("Response is null ",responseToken);
        assertNull("Error in response",responseToken.getError());
        log.info("FD TOken : " + responseToken.getToken().getTokenData().getValue());
    	
        //purchase
        assertNotNull("RESTTemplate is null:", restTemplate);
        assertNotNull("clietn is null:", client);
        TransactionRequest trans = getPrimaryTransaction();
        
        trans.setReferenceNo("Astonishing-Sale");
        trans.setTransactionType("purchase");
        trans.setPaymentMethod("token");
        trans.setAmount("1");
        trans.setCurrency("USD");
        
        Token token = new Token();
        Transarmor ta = new Transarmor();

        ta.setValue(responseToken.getToken().getTokenData().getValue());
        ta.setName(responseToken.getToken().getTokenData().getName());
        ta.setExpiryDt(responseToken.getToken().getTokenData().getExpiryDt());
        ta.setType(responseToken.getToken().getTokenData().getType());
        
        token.setTokenData(ta);
        token.setTokenType("FDToken");
        trans.setToken(token);
        
        trans.setCard(null);
        trans.setBilling(null);
        
        //TransactionResponse response=client.postTokenTransaction(trans);
        TransactionResponse response=client.purchaseTransaction(trans);
        assertNotNull("Response is null ",response);
        assertNull("Error in response",response.getError());
        log.info("Transaction Tag:{} Transaction id:{}",response.getTransactionTag(),response.getTransactionId());
        log.info("++++++++++++++++++++++++++++++++++++++ end +++++++++++++++++");
    }
    
    @Test
    public void doPostTATokenGenerateAmex() throws Exception {
    	 log.info("+++++++++++++++++++++++++++++++++++++ start ++++++++++++++++++");
        assertNotNull("RESTTemplate is null:", restTemplate);
        assertNotNull("clietn is null:", client);
        TransactionRequest trans = getPrimaryTransaction();
        
        trans.setType("FDToken");
        
        trans.getCard().setNumber("373953192351004");
        trans.getCard().setName("John Smith");
        trans.getCard().setExpiryDt("0416");
        trans.getCard().setCvv("1234");
        trans.getCard().setType("American Express");
        
        trans.setAuth("false");
        trans.setTa_token(FirstAPIClientV2Helper.TA_TOKEN_VALUE);
        
        trans.setToken(null);
        trans.setBilling(null);
        trans.setTransactionType(null);
        trans.setPaymentMethod(null);
        trans.setAmount(null);
        trans.setCurrency(null);
        
        TransactionResponse response=client.postTokenTransaction(trans);
        assertNotNull("Response is null ",response);
        assertNull("Error in response",response.getError());
        log.info("FD Token : " + response.getToken().getTokenData().getValue());
        log.info("++++++++++++++++++++++++++++++++++++++ end +++++++++++++++++");
    }
    
    @Test
    public void doPostTATokenRefundAmex() throws Exception {
    	 log.info("+++++++++++++++++++++++++++++++++++++ start ++++++++++++++++++");
    	
    	// Generate Token
    	assertNotNull("RESTTemplate is null:", restTemplate);
        assertNotNull("clietn is null:", client);
        TransactionRequest transreq = getPrimaryTransaction();
        
        transreq.setType("FDToken");
        
        transreq.getCard().setNumber("373953192351004");
        transreq.getCard().setName("John Smith");
        transreq.getCard().setExpiryDt("0416");
        transreq.getCard().setCvv("1234");
        transreq.getCard().setType("American Express");
        
        transreq.setAuth("false");
        transreq.setTa_token(FirstAPIClientV2Helper.TA_TOKEN_VALUE);
        
        transreq.setToken(null);
        transreq.setBilling(null);
        transreq.setTransactionType(null);
        transreq.setPaymentMethod(null);
        transreq.setAmount(null);
        transreq.setCurrency(null);
 
        TransactionResponse responseToken=client.postTokenTransaction(transreq);
        assertNotNull("Response is null ",responseToken);
        assertNull("Error in response",responseToken.getError());
        log.info("FD Token : " + responseToken.getToken().getTokenData().getValue());
    	
    	
    	//purchase
        assertNotNull("RESTTemplate is null:", restTemplate);
        assertNotNull("clietn is null:", client);
        TransactionRequest trans = getPrimaryTransaction();
        
        trans.setReferenceNo("abc1412096293369");
        trans.setTransactionType("purchase");
        trans.setPaymentMethod("token");
        trans.setAmount("1");
        trans.setCurrency("USD");
        
        Token token = new Token();
        Transarmor ta = new Transarmor();
        
        ta.setValue(responseToken.getToken().getTokenData().getValue());
        ta.setName(responseToken.getToken().getTokenData().getName());
        ta.setExpiryDt(responseToken.getToken().getTokenData().getExpiryDt());
        ta.setType(responseToken.getToken().getTokenData().getType());
        
        
        token.setTokenData(ta);
        token.setTokenType("FDToken");
        trans.setToken(token);
        
        trans.setCard(null);
        trans.setBilling(null);
        
        TransactionResponse response=client.postTokenTransaction(trans);
        assertNotNull("Response is null ",response);
        assertNull("Error in response",response.getError());
        log.info("Transaction Tag:{} Transaction id:{}",response.getTransactionTag(),response.getTransactionId());
        
        
    	//refund
        assertNotNull("RESTTemplate is null:", restTemplate);
        assertNotNull("clietn is null:", client);
        trans = getPrimaryTransaction();
        
        trans.setReferenceNo("abc1412096293369");
        trans.setTransactionType(TransactionType.REFUND.name()) ; 
        trans.setPaymentMethod("token");
        trans.setAmount("1");
        trans.setCurrency("USD");
        
        trans.setTransactionTag(response.getTransactionTag());
        trans.setId(response.getTransactionId());
        
        token = new Token();
        ta = new Transarmor();
        
        ta.setValue(responseToken.getToken().getTokenData().getValue());
        ta.setName(responseToken.getToken().getTokenData().getName());
        ta.setExpiryDt(responseToken.getToken().getTokenData().getExpiryDt());
        ta.setType(responseToken.getToken().getTokenData().getType());
        
        token.setTokenData(ta);
        token.setTokenType("FDToken");
        trans.setToken(token);
        
        trans.setCard(null);
        trans.setBilling(null);
        
        TransactionResponse response2=client.postTokenTransaction(trans);
        //TransactionResponse response2=client.refundTransaction(trans);
        assertNotNull("Response is null ",response2);
        assertNull("Error in response",response2.getError());
        log.info("Transaction Tag:{} Transaction id:{}",response2.getTransactionTag(),response2.getTransactionId());
        log.info("++++++++++++++++++++++++++++++++++++++ end +++++++++++++++++");
    }
    
    @Test
    public void doPostTATokenVoidAmex() throws Exception {
    	 log.info("+++++++++++++++++++++++++++++++++++++ start ++++++++++++++++++");
    	
    	// Generate Token
    	assertNotNull("RESTTemplate is null:", restTemplate);
        assertNotNull("clietn is null:", client);
        TransactionRequest transreq = getPrimaryTransaction();
        
        transreq.setType("FDToken");
        
        transreq.getCard().setNumber("373953192351004");
        transreq.getCard().setName("John Smith");
        transreq.getCard().setExpiryDt("0416");
        transreq.getCard().setCvv("1234");
        transreq.getCard().setType("American Express");
        
        transreq.setAuth("false");
        transreq.setTa_token(FirstAPIClientV2Helper.TA_TOKEN_VALUE);
        
        transreq.setToken(null);
        transreq.setBilling(null);
        transreq.setTransactionType(null);
        transreq.setPaymentMethod(null);
        transreq.setAmount(null);
        transreq.setCurrency(null);
 
        TransactionResponse responseToken=client.postTokenTransaction(transreq);
        assertNotNull("Response is null ",responseToken);
        assertNull("Error in response",responseToken.getError());
        //log.info("Transaction Tag:{} Transaction id:{}",responseToken.getTransactionTag(),responseToken.getTransactionId());
        log.info("FD Token : " + responseToken.getToken().getTokenData().getValue());
    	
    	//purchase
        assertNotNull("RESTTemplate is null:", restTemplate);
        assertNotNull("clietn is null:", client);
        TransactionRequest trans = getPrimaryTransaction();
        
        trans.setReferenceNo("abc1412096293369");
        trans.setTransactionType("purchase");
        trans.setPaymentMethod("token");
        trans.setAmount("1");
        trans.setCurrency("USD");
        
        Token token = new Token();
        Transarmor ta = new Transarmor();
        
        ta.setValue(responseToken.getToken().getTokenData().getValue());
        ta.setName(responseToken.getToken().getTokenData().getName());
        ta.setExpiryDt(responseToken.getToken().getTokenData().getExpiryDt());
        ta.setType(responseToken.getToken().getTokenData().getType());
        
        token.setTokenData(ta);
        token.setTokenType("FDToken");
        trans.setToken(token);
        
        trans.setCard(null);
        trans.setBilling(null);
        
        TransactionResponse response=client.postTokenTransaction(trans);
        assertNotNull("Response is null ",response);
        assertNull("Error in response",response.getError());
        log.info("Transaction Tag:{} Transaction id:{}",response.getTransactionTag(),response.getTransactionId());
        
        
        
    	//void
        assertNotNull("RESTTemplate is null:", restTemplate);
        assertNotNull("clietn is null:", client);
        trans = getPrimaryTransaction();
        trans.setTransactionType(TransactionType.VOID.name()) ; 
        
        trans.setReferenceNo("abc1412096293369");
        trans.setPaymentMethod("token");
        trans.setAmount("1");
        trans.setCurrency("USD");
        
        trans.setTransactionTag(response.getTransactionTag());
        trans.setId(response.getTransactionId());
        
        token = new Token();
        ta = new Transarmor();
        
        ta.setValue(responseToken.getToken().getTokenData().getValue());
        ta.setName(responseToken.getToken().getTokenData().getName());
        ta.setExpiryDt(responseToken.getToken().getTokenData().getExpiryDt());
        ta.setType(responseToken.getToken().getTokenData().getType());
        
        token.setTokenData(ta);
        token.setTokenType("FDToken");
        trans.setToken(token);
        
        trans.setCard(null);
        trans.setBilling(null);
        
        TransactionResponse response2=client.postTokenTransaction(trans);
        assertNotNull("Response is null ",response2);
        assertNull("Error in response",response2.getError());
        log.info("Transaction Tag:{} Transaction id:{}",response2.getTransactionTag(),response2.getTransactionId());
        log.info("++++++++++++++++++++++++++++++++++++++ end +++++++++++++++++");
    
    }
    
    
    /////////////////////gettoken discover //////////////////
    

    @Test
    public void doPostTATokenAuthorizeDiscover() throws Exception 
    {
    	 log.info("+++++++++++++++++++++++++++++++++++++ start ++++++++++++++++++");
    	// Generate Token
    	assertNotNull("RESTTemplate is null:", restTemplate);
        assertNotNull("clietn is null:", client);
        TransactionRequest transreq = getPrimaryTransaction();
        
        transreq.setType("FDToken");
        
        transreq.getCard().setNumber("6510000000001248");
        transreq.getCard().setName("John Smith");
        transreq.getCard().setExpiryDt("0416");
        transreq.getCard().setCvv("123");
        transreq.getCard().setType("discover");
        
        transreq.setAuth("false");
        transreq.setTa_token(FirstAPIClientV2Helper.TA_TOKEN_VALUE);
        
        transreq.setToken(null);
        transreq.setBilling(null);
        transreq.setTransactionType(null);
        transreq.setPaymentMethod(null);
        transreq.setAmount(null);
        transreq.setCurrency(null);
 
        TransactionResponse responseToken=client.postTokenTransaction(transreq);
        assertNotNull("Response is null ",responseToken);
        assertNull("Error in response",responseToken.getError());
        log.info("FD Token:{} ",responseToken.getToken().getTokenData().getValue());
    	
    	//Authorize
    	
    	assertNotNull("RESTTemplate is null:", restTemplate);
        assertNotNull("clietn is null:", client);
        
        TransactionRequest trans = getPrimaryTransaction();
        
        trans.setReferenceNo("abc1412096293369");
        trans.setTransactionType("authorize");
        trans.setPaymentMethod("token");
        trans.setAmount("0");
        trans.setCurrency("USD");
        
        Token token = new Token();
        Transarmor ta = new Transarmor();
        
        ta.setValue("2833693264441732");
        String s = responseToken.getToken().getTokenData().getValue();
        ta.setValue(responseToken.getToken().getTokenData().getValue());
        ta.setName("John Smith");
        ta.setExpiryDt("0416");
        ta.setType("discover");
        token.setTokenData(ta);
        token.setTokenType("FDToken");
        trans.setToken(token);
        
        trans.setCard(null);
        trans.setBilling(null);
        
        TransactionResponse response=client.postTokenTransaction(trans);
        assertNotNull("Response is null ",response);
        assertNull("Error in response",response.getError());
        log.info("Transaction Tag:{} Transaction id:{}",response.getTransactionTag(),response.getTransactionId());
        log.info("++++++++++++++++++++++++++++++++++++++ end +++++++++++++++++");
    }
    
    @Test
    public void doPostTATokenCaptureDiscover() throws Exception {
    	 log.info("+++++++++++++++++++++++++++++++++++++ start ++++++++++++++++++");
    	// Generate Token
    	assertNotNull("RESTTemplate is null:", restTemplate);
        assertNotNull("clietn is null:", client);
        TransactionRequest transreq = getPrimaryTransaction();
        
        transreq.setType("FDToken");
        
        transreq.getCard().setNumber("6510000000001248");
        transreq.getCard().setName("John Smith");
        transreq.getCard().setExpiryDt("0416");
        transreq.getCard().setCvv("123");
        transreq.getCard().setType("discover");
        
        transreq.setAuth("false");
        transreq.setTa_token(FirstAPIClientV2Helper.TA_TOKEN_VALUE);
        
        transreq.setToken(null);
        transreq.setBilling(null);
        transreq.setTransactionType(null);
        transreq.setPaymentMethod(null);
        transreq.setAmount(null);
        transreq.setCurrency(null);
 
        TransactionResponse responseToken=client.postTokenTransaction(transreq);
        assertNotNull("Response is null ",responseToken);
        assertNull("Error in response",responseToken.getError());
        log.info("FD TOken : " + responseToken.getToken().getTokenData().getValue());
        
        //authorize
        assertNotNull("RESTTemplate is null:", restTemplate);
        assertNotNull("clietn is null:", client);
        
        TransactionRequest trans = getPrimaryTransaction();
        
        trans.setReferenceNo("abc1412096293369");
        trans.setTransactionType("authorize");
        trans.setPaymentMethod("token");
        trans.setAmount("1");
        trans.setCurrency("USD");
        
        Token token = new Token();
        Transarmor ta = new Transarmor();
        
        ta.setValue(responseToken.getToken().getTokenData().getValue());
        ta.setName(responseToken.getToken().getTokenData().getName());
        ta.setExpiryDt(responseToken.getToken().getTokenData().getExpiryDt());
        ta.setType(responseToken.getToken().getTokenData().getType());
        token.setTokenData(ta);
        token.setTokenType("FDToken");
        trans.setToken(token);
        
        trans.setCard(null);
        trans.setBilling(null);
        
        TransactionResponse response=client.postTokenTransaction(trans);
        assertNotNull("Response is null ",response);
        assertNull("Error in response",response.getError());
        log.info("FD TOken : " + responseToken.getToken().getTokenData().getValue());

    	
    	//Capture
        assertNotNull("RESTTemplate is null:", restTemplate);
        assertNotNull("clietn is null:", client);
        trans = getPrimaryTransaction();
        
        trans.setReferenceNo("abc1412096293369");
        trans.setTransactionTag("1871007");
        trans.setTransactionType(TransactionType.CAPTURE.name()) ; 
        trans.setPaymentMethod("token");
        trans.setAmount("1");
        trans.setCurrency("USD");
        trans.setTransactionTag(response.getTransactionTag());
        trans.setId(response.getTransactionId());
        
        token = new Token();
        ta = new Transarmor();
        
        ta.setValue(responseToken.getToken().getTokenData().getValue());
        ta.setName(responseToken.getToken().getTokenData().getName());
        ta.setExpiryDt(responseToken.getToken().getTokenData().getExpiryDt());
        ta.setType(responseToken.getToken().getTokenData().getType());
        
        token.setTokenData(ta);
        token.setTokenType("FDToken");
        trans.setToken(token);
        trans.setCard(null);
        trans.setBilling(null);
        
        TransactionResponse response2=client.postTokenTransaction(trans);
        assertNotNull("Response is null ",response2);
        assertNull("Error in response",response2.getError());
        log.info("Transaction Tag:{} Transaction id:{}",response2.getTransactionTag(),response2.getTransactionId());
        log.info("++++++++++++++++++++++++++++++++++++++ end +++++++++++++++++");
    }
    
    @Test
    public void doPostTATokenPurchaseDiscover() throws Exception {
    	 log.info("+++++++++++++++++++++++++++++++++++++ start ++++++++++++++++++");
    	
    	// Generate Token
    	assertNotNull("RESTTemplate is null:", restTemplate);
        assertNotNull("clietn is null:", client);
        TransactionRequest transreq = getPrimaryTransaction();
        
        transreq.setType("FDToken");
        
        transreq.getCard().setNumber("6510000000001248");
        transreq.getCard().setName("John Smith");
        transreq.getCard().setExpiryDt("0416");
        transreq.getCard().setCvv("123");
        transreq.getCard().setType("discover");
        
        transreq.setAuth("false");
        transreq.setTa_token(FirstAPIClientV2Helper.TA_TOKEN_VALUE);
        
        transreq.setToken(null);
        transreq.setBilling(null);
        transreq.setTransactionType(null);
        transreq.setPaymentMethod(null);
        transreq.setAmount(null);
        transreq.setCurrency(null);
 
        TransactionResponse responseToken=client.postTokenTransaction(transreq);
        assertNotNull("Response is null ",responseToken);
        assertNull("Error in response",responseToken.getError());
        log.info("FD TOken : " + responseToken.getToken().getTokenData().getValue());
    	
        //purchase
        assertNotNull("RESTTemplate is null:", restTemplate);
        assertNotNull("clietn is null:", client);
        TransactionRequest trans = getPrimaryTransaction();
        
        trans.setReferenceNo("Astonishing-Sale");
        trans.setTransactionType("purchase");
        trans.setPaymentMethod("token");
        trans.setAmount("1");
        trans.setCurrency("USD");
        
        Token token = new Token();
        Transarmor ta = new Transarmor();

        ta.setValue(responseToken.getToken().getTokenData().getValue());
        ta.setName(responseToken.getToken().getTokenData().getName());
        ta.setExpiryDt(responseToken.getToken().getTokenData().getExpiryDt());
        ta.setType(responseToken.getToken().getTokenData().getType());
        
        token.setTokenData(ta);
        token.setTokenType("FDToken");
        trans.setToken(token);
        
        trans.setCard(null);
        trans.setBilling(null);
        
        //TransactionResponse response=client.postTokenTransaction(trans);
        TransactionResponse response=client.purchaseTransaction(trans);
        assertNotNull("Response is null ",response);
        assertNull("Error in response",response.getError());
        log.info("Transaction Tag:{} Transaction id:{}",response.getTransactionTag(),response.getTransactionId());
        log.info("++++++++++++++++++++++++++++++++++++++ end +++++++++++++++++");
    }
    
    @Test
    public void doPostTATokenGenerateDiscover() throws Exception {
    	 log.info("+++++++++++++++++++++++++++++++++++++ start ++++++++++++++++++");
        assertNotNull("RESTTemplate is null:", restTemplate);
        assertNotNull("clietn is null:", client);
        TransactionRequest trans = getPrimaryTransaction();
        
        trans.setType("FDToken");
        
        trans.getCard().setNumber("6510000000001248");
        trans.getCard().setName("John Smith");
        trans.getCard().setExpiryDt("0416");
        trans.getCard().setCvv("123");
        trans.getCard().setType("discover");
        
        trans.setAuth("false");
        trans.setTa_token(FirstAPIClientV2Helper.TA_TOKEN_VALUE);
        
        trans.setToken(null);
        trans.setBilling(null);
        trans.setTransactionType(null);
        trans.setPaymentMethod(null);
        trans.setAmount(null);
        trans.setCurrency(null);
        
        TransactionResponse response=client.postTokenTransaction(trans);
        assertNotNull("Response is null ",response);
        assertNull("Error in response",response.getError());
        log.info("FD Token : " + response.getToken().getTokenData().getValue());
        log.info("++++++++++++++++++++++++++++++++++++++ end +++++++++++++++++");
    }
    
    @Test
    public void doPostTATokenRefundDiscover() throws Exception {
    	 log.info("+++++++++++++++++++++++++++++++++++++ start ++++++++++++++++++");
    	
    	// Generate Token
    	assertNotNull("RESTTemplate is null:", restTemplate);
        assertNotNull("clietn is null:", client);
        TransactionRequest transreq = getPrimaryTransaction();
        
        transreq.setType("FDToken");
        
        transreq.getCard().setNumber("6510000000001248");
        transreq.getCard().setName("John Smith");
        transreq.getCard().setExpiryDt("0416");
        transreq.getCard().setCvv("123");
        transreq.getCard().setType("discover");
        
        transreq.setAuth("false");
        transreq.setTa_token(FirstAPIClientV2Helper.TA_TOKEN_VALUE);
        
        transreq.setToken(null);
        transreq.setBilling(null);
        transreq.setTransactionType(null);
        transreq.setPaymentMethod(null);
        transreq.setAmount(null);
        transreq.setCurrency(null);
 
        TransactionResponse responseToken=client.postTokenTransaction(transreq);
        assertNotNull("Response is null ",responseToken);
        assertNull("Error in response",responseToken.getError());
        log.info("FD Token : " + responseToken.getToken().getTokenData().getValue());
    	
    	
    	//purchase
        assertNotNull("RESTTemplate is null:", restTemplate);
        assertNotNull("clietn is null:", client);
        TransactionRequest trans = getPrimaryTransaction();
        
        trans.setReferenceNo("abc1412096293369");
        trans.setTransactionType("purchase");
        trans.setPaymentMethod("token");
        trans.setAmount("1");
        trans.setCurrency("USD");
        
        Token token = new Token();
        Transarmor ta = new Transarmor();
        
        ta.setValue(responseToken.getToken().getTokenData().getValue());
        ta.setName(responseToken.getToken().getTokenData().getName());
        ta.setExpiryDt(responseToken.getToken().getTokenData().getExpiryDt());
        ta.setType(responseToken.getToken().getTokenData().getType());
        
        
        token.setTokenData(ta);
        token.setTokenType("FDToken");
        trans.setToken(token);
        
        trans.setCard(null);
        trans.setBilling(null);
        
        TransactionResponse response=client.postTokenTransaction(trans);
        assertNotNull("Response is null ",response);
        assertNull("Error in response",response.getError());
        log.info("Transaction Tag:{} Transaction id:{}",response.getTransactionTag(),response.getTransactionId());
        
        
    	//refund
        assertNotNull("RESTTemplate is null:", restTemplate);
        assertNotNull("clietn is null:", client);
        trans = getPrimaryTransaction();
        
        trans.setReferenceNo("abc1412096293369");
        trans.setTransactionType(TransactionType.REFUND.name()) ; 
        trans.setPaymentMethod("token");
        trans.setAmount("1");
        trans.setCurrency("USD");
        
        trans.setTransactionTag(response.getTransactionTag());
        trans.setId(response.getTransactionId());
        
        token = new Token();
        ta = new Transarmor();
        
        ta.setValue(responseToken.getToken().getTokenData().getValue());
        ta.setName(responseToken.getToken().getTokenData().getName());
        ta.setExpiryDt(responseToken.getToken().getTokenData().getExpiryDt());
        ta.setType(responseToken.getToken().getTokenData().getType());
        
        token.setTokenData(ta);
        token.setTokenType("FDToken");
        trans.setToken(token);
        
        trans.setCard(null);
        trans.setBilling(null);
        
        TransactionResponse response2=client.postTokenTransaction(trans);
        //TransactionResponse response2=client.refundTransaction(trans);
        assertNotNull("Response is null ",response2);
        assertNull("Error in response",response2.getError());
        log.info("Transaction Tag:{} Transaction id:{}",response2.getTransactionTag(),response2.getTransactionId());
        log.info("++++++++++++++++++++++++++++++++++++++ end +++++++++++++++++");
    }
    
    @Test
    public void doPostTATokenVoidDiscover() throws Exception {
    	 log.info("+++++++++++++++++++++++++++++++++++++ start ++++++++++++++++++");
    	
    	// Generate Token
    	assertNotNull("RESTTemplate is null:", restTemplate);
        assertNotNull("clietn is null:", client);
        TransactionRequest transreq = getPrimaryTransaction();
        
        
        transreq.setType("FDToken");
        
        transreq.getCard().setNumber("6510000000001248");
        transreq.getCard().setName("John Smith");
        transreq.getCard().setExpiryDt("0416");
        transreq.getCard().setCvv("123");
        transreq.getCard().setType("discover");
        
        transreq.setAuth("false");
        transreq.setTa_token(FirstAPIClientV2Helper.TA_TOKEN_VALUE);
        
        transreq.setToken(null);
        transreq.setBilling(null);
        transreq.setTransactionType(null);
        transreq.setPaymentMethod(null);
        transreq.setAmount(null);
        transreq.setCurrency(null);
 
        TransactionResponse responseToken=client.postTokenTransaction(transreq);
        assertNotNull("Response is null ",responseToken);
        assertNull("Error in response",responseToken.getError());
        //log.info("Transaction Tag:{} Transaction id:{}",responseToken.getTransactionTag(),responseToken.getTransactionId());
        log.info("FD Token : " + responseToken.getToken().getTokenData().getValue());
    	
    	//purchase
        assertNotNull("RESTTemplate is null:", restTemplate);
        assertNotNull("clietn is null:", client);
        TransactionRequest trans = getPrimaryTransaction();
        
        trans.setReferenceNo("abc1412096293369");
        trans.setTransactionType("purchase");
        trans.setPaymentMethod("token");
        trans.setAmount("1");
        trans.setCurrency("USD");
        
        Token token = new Token();
        Transarmor ta = new Transarmor();
        
        ta.setValue(responseToken.getToken().getTokenData().getValue());
        ta.setName(responseToken.getToken().getTokenData().getName());
        ta.setExpiryDt(responseToken.getToken().getTokenData().getExpiryDt());
        ta.setType(responseToken.getToken().getTokenData().getType());
        
        token.setTokenData(ta);
        token.setTokenType("FDToken");
        trans.setToken(token);
        
        trans.setCard(null);
        trans.setBilling(null);
        
        TransactionResponse response=client.postTokenTransaction(trans);
        assertNotNull("Response is null ",response);
        assertNull("Error in response",response.getError());
        log.info("Transaction Tag:{} Transaction id:{}",response.getTransactionTag(),response.getTransactionId());
        
        
        
    	//void
        assertNotNull("RESTTemplate is null:", restTemplate);
        assertNotNull("clietn is null:", client);
        trans = getPrimaryTransaction();
        trans.setTransactionType(TransactionType.VOID.name()) ; 
        
        trans.setReferenceNo("abc1412096293369");
        trans.setPaymentMethod("token");
        trans.setAmount("1");
        trans.setCurrency("USD");
        
        trans.setTransactionTag(response.getTransactionTag());
        trans.setId(response.getTransactionId());
        
        token = new Token();
        ta = new Transarmor();
        
        ta.setValue(responseToken.getToken().getTokenData().getValue());
        ta.setName(responseToken.getToken().getTokenData().getName());
        ta.setExpiryDt(responseToken.getToken().getTokenData().getExpiryDt());
        ta.setType(responseToken.getToken().getTokenData().getType());
        
        token.setTokenData(ta);
        token.setTokenType("FDToken");
        trans.setToken(token);
        
        trans.setCard(null);
        trans.setBilling(null);
        
        TransactionResponse response2=client.postTokenTransaction(trans);
        assertNotNull("Response is null ",response2);
        assertNull("Error in response",response2.getError());
        log.info("Transaction Tag:{} Transaction id:{}",response2.getTransactionTag(),response2.getTransactionId());
        log.info("++++++++++++++++++++++++++++++++++++++ end +++++++++++++++++");
    
    }
    
    
    /////////////////////////////////////////////////////////
    
    //@Test
    public void doPostToken()throws Exception {
    	log.info("+++++++++++++++++++++++++++++++++++++ start ++++++++++++++++++");
        assertNotNull("RESTTemplate is null:", restTemplate);
        assertNotNull("clietn is null:", client);
        TransactionRequest trans = getPrimaryTransaction();
        trans.getCard().setNumber("4788250000028291");
        trans.getCard().setName("John Smith");
        trans.getCard().setExpiryDt("1030");
        
        
        Token token = new Token();
        Transarmor ta = new Transarmor();
        ta.setNumber("4788250000028291");
        ta.setName("John Smith");
        ta.setExpiryDt("1030");
        ta.setCvv("123");
        ta.setType("visa");
        ta.setValue("");
        
        token.setTokenData(ta);
        token.setTokenType("payeezy");
        trans.setToken(token);
        trans.setAuth("false");
        
        trans.setCard(null);
        trans.setBilling(null);
        
        TransactionResponse response=client.getTokenTransaction(trans);
        assertNotNull("Response is null ",response);
        assertNull("Error in response",response.getError());
        log.info("Transaction Tag:{} Transaction id:{}",response.getTransactionTag(),response.getTransactionId());
        log.info("++++++++++++++++++++++++++++++++++++++ end +++++++++++++++++");
    }
    
    @Test
    public void doAuthorizePayment()throws Exception {
    	log.info("+++++++++++++++++++++++++++++++++++++ start ++++++++++++++++++");
        assertNotNull("RESTTemplate is null:", restTemplate);
        assertNotNull("clietn is null:", client);
        TransactionResponse response=client.authorizeTransaction(getPrimaryTransaction());

        assertNotNull("Response is null ",response);
        assertNull("Error in response",response.getError());
        log.info("Transaction Tag:{} Transaction id:{}",response.getTransactionTag(),response.getTransactionId());
        log.info("++++++++++++++++++++++++++++++++++++++ end +++++++++++++++++");
    }

    @Test
    public void doVoidPayment()throws Exception {
    	log.info("+++++++++++++++++++++++++++++++++++++ start ++++++++++++++++++");
        assertNotNull("RESTTemplate is null:", restTemplate);
        assertNotNull("clietn is null:", client);
        TransactionRequest req=getPrimaryTransaction();
        
        TransactionResponse response=client.purchaseTransaction(req);
        assertNotNull("Response is null ",response);
        assertNull("There was error:",response.getError());
        log.info("Transaction Tag:{} Transaction id:{}",response.getTransactionTag(),response.getTransactionId());

        if(response.getError()==null) {
            req=getSecondaryTransaction();
            req.setId(response.getTransactionId());
            req.setTransactionTag(response.getTransactionTag());
            req.setAmount(response.getAmount());
            response=client.voidTransaction(req);
            assertNotNull("Response is null ",response);
            assertNull(response.getError());
//            log.info("Transaction Tag:{} Transaction id:{}",response.getTransactionTag(),response.getTransactionId());

        }
        log.info("++++++++++++++++++++++++++++++++++++++ end +++++++++++++++++");
    }
    
    
    @Test
    public void doRefundPayment()throws Exception {
    	log.info("+++++++++++++++++++++++++++++++++++++ start ++++++++++++++++++");
        assertNotNull("RESTTemplate is null:", restTemplate);
        assertNotNull("clietn is null:", client);
        TransactionRequest req=getPrimaryTransaction();
        
        TransactionResponse response=client.purchaseTransaction(req);
        assertNotNull("Response is null ",response);
        assertNull("Error in response",response.getError());
        log.info("Transaction Tag:{} Transaction id:{}",response.getTransactionTag(),response.getTransactionId());

        if(response.getError()==null) {
            req=getSecondaryTransaction();
            req.setId(response.getTransactionId());
            req.setTransactionTag(response.getTransactionTag());
            req.setAmount(response.getAmount());
            response=client.refundTransaction(req);
            assertNotNull("Response is null ",response);
            assertNull(response.getError());
        }
        log.info("++++++++++++++++++++++++++++++++++++++ end +++++++++++++++++");
        
    }
    
    @Test
    public void doCapturePayment()throws Exception {
    	log.info("+++++++++++++++++++++++++++++++++++++ start ++++++++++++++++++");
        assertNotNull("RESTTemplate is null:", restTemplate);
        assertNotNull("clietn is null:", client);
        TransactionRequest req=getPrimaryTransaction();
        
        TransactionResponse response=client.authorizeTransaction(req);
        assertNotNull("Response is null ",response);
        assertNull("Error in response",response.getError());
        log.info("Transaction Tag:{} Transaction id:{}",response.getTransactionTag(),response.getTransactionId());

        if(response.getError()==null) {
//        SecondaryTransaction trans= getSecondaryTransaction();
            req=getSecondaryTransaction();
            req.setId(response.getTransactionId());
            req.setTransactionTag(response.getTransactionTag());
            req.setAmount(response.getAmount());
            response=client.captureTransaction(req);
            assertNotNull("Response is null ",response);
            assertNull(response.getError());
        }
        log.info("++++++++++++++++++++++++++++++++++++++ end +++++++++++++++++");
    }
    
    

    private TransactionRequest getPrimaryTransaction() {
    	log.info("+++++++++++++++++++++++++++++++++++++ start ++++++++++++++++++");
        TransactionRequest request=new TransactionRequest();
        request.setAmount("1100");
        request.setCurrency("USD");
        request.setPaymentMethod("credit_card");
        request.setTransactionType(TransactionType.AUTHORIZE.getValue());
        Card card=new Card();
        card.setCvv("123");
        card.setExpiryDt("1219");
        card.setName("Test data ");
        card.setType("visa");
        card.setNumber("4788250000028291");
        request.setCard(card);
        Address address=new Address();
        request.setBilling(address);
        address.setState("NY");
        address.setAddressLine1("sss");
        address.setZip("11747");
        address.setCountry("US");
        //request.setTa_token(null);
        log.info("++++++++++++++++++++++++++++++++++++++ end +++++++++++++++++");
        return request;
    }
     
    private TransactionRequest getSecondaryTransaction() {
    	log.info("+++++++++++++++++++++++++++++++++++++ start ++++++++++++++++++");
        TransactionRequest trans=new TransactionRequest();
        trans.setPaymentMethod("credit_card");
        trans.setAmount("0.00");
        trans.setCurrency("USD");
        trans.setTransactionTag("349990997");
        trans.setId("07698G");
        log.info("++++++++++++++++++++++++++++++++++++++ end +++++++++++++++++");
        return trans;
    }
    
    
    
}
