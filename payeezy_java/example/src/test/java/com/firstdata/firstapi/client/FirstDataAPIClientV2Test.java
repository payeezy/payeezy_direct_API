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
        assertNotNull("RESTTemplate is null:", restTemplate);
        assertNotNull("clietn is null:", client);
        TransactionRequest trans = getPrimaryTransaction();
        trans.getCard().setNumber("4788250000028291");
        trans.getCard().setName("John Smith");
        trans.getCard().setExpiryDt("1030");
        FirstAPIClientV2Helper.OVERRIDE = "override";
        //client.setAppId("C5VAeX3WwStH0ZDnlF4eXVhHJiIedDtY");
        client.setAppId("fP0iYUx4oJ8LolKl2LiOT1Zo94mL0IDQ");
        client.setSecuredSecret("2b940ece234ee38131e70cc617aa2afa3d7ff8508856917958e7feb3ef190447");
        client.setToken("fdoa-a480ce8951daa73262734cf102641994c1e55e7cdf4c02b6");
        client.setTrToken("y6pzAbc3Def123");
        client.setMerchantid("OGEzNGU3NjM0ODQyMTU3NzAxNDg0MjE4NDY4ZTAwMDA=");
        //client.setUrl("https://api-int.payeezy.com/v1");
        client.setUrl("https://api-cert.payeezy.com/v1");
        FirstAPIClientV2Helper.OVERRIDE = "overrides";
        Token token = new Token();
        Transarmor ta = new Transarmor();
        ta.setNumber("6011000990099818");
        ta.setName("xyz");
        ta.setExpiryDt("0416");
        ta.setCvv("123");
        ta.setType("discover");
        //ta.setValue(FirstAPIClientV2Helper.TA_TOKEN_VALUE);
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
    }
    
    //@Test
    public void doGetToken()throws Exception {
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
    }
    
    /*
     * REM curl -k -X POST -H "Content-Type:application/json" 
     * -H "apikey:fP0iYUx4oJ8LolKl2LiOT1Zo94mL0IDQ" -H 
     * "token:fdoa-a480ce8951daa73262734cf102641994c1e55e7cdf4c02b6" 
     * -H "Authorization:YzA2MTc4OWIyZTI2Nzc1ODZhNmRhOTI4MjIzM2QxYWI3YzliMzg4MTRkNjJlZDk2MjJiOTM2MTJjMDJmOGY2Zg" 
     * -H "nonce:4855700038839132000" -H "timestamp:1430763448135" 
     * --data @FDTokenAuthorize.txt  https://api-int.payeezy.com/v1/transactions -v
     * 
     */
    
    //////////////////get token auth true///////////////////////////////
    
    @Test
    public void doPostTATokenGenerateSample() throws Exception {
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
    }
    
    @Test
    public void doPostTATokenGenerateSample2() throws Exception {
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
    }
    
    //////////////////get token auth true///////////////////////////////
    

    @Test
    public void doPostTATokenGenerateAuthTrue() throws Exception {
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
    }
    
    
    
    ///////////////////////////gettoken mastercard/////////////////////////
    
    @Test
    public void doPostTATokenGenerate() throws Exception {
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
    }
    
    
    @Test
    public void doPostTATokenAuthorize() throws Exception 
    {
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
    }
    
    @Test
    public void doPostTATokenCapture() throws Exception {
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
    }
    
    @Test
    public void doPostTATokenPurchase() throws Exception {
    	
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
    }
    

    
    @Test
    public void doPostTATokenRefund() throws Exception {
    	
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
    }
    
    @Test
    public void doPostTATokenVoid() throws Exception {
    	
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
    
    }
    
    
    /////////////////////////gettoken visa/////////////////
    

    @Test
    public void doPostTATokenAuthorizeVisa() throws Exception 
    {
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
    }
    
    @Test
    public void doPostTATokenCaptureVisa() throws Exception {
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
    }
    
    @Test
    public void doPostTATokenPurchaseVisa() throws Exception {
    	
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
    }
    
    @Test
    public void doPostTATokenGenerateVisa() throws Exception {
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
    }
    
    @Test
    public void doPostTATokenRefundVisa() throws Exception {
    	
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
    }
    
    @Test
    public void doPostTATokenVoidVisa() throws Exception {
    	
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
    
    }
    
    
    //////////////////////gettoken amex //////////////
    

    @Test
    public void doPostTATokenAuthorizeAmex() throws Exception 
    {
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
    }
    
    @Test
    public void doPostTATokenCaptureAmex() throws Exception {
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
    }
    
    @Test
    public void doPostTATokenPurchaseAmex() throws Exception {
    	
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
    }
    
    @Test
    public void doPostTATokenGenerateAmex() throws Exception {
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
    }
    
    @Test
    public void doPostTATokenRefundAmex() throws Exception {
    	
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
    }
    
    @Test
    public void doPostTATokenVoidAmex() throws Exception {
    	
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
    
    }
    
    
    /////////////////////gettoken discover //////////////////
    

    @Test
    public void doPostTATokenAuthorizeDiscover() throws Exception 
    {
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
    }
    
    @Test
    public void doPostTATokenCaptureDiscover() throws Exception {
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
    }
    
    @Test
    public void doPostTATokenPurchaseDiscover() throws Exception {
    	
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
    }
    
    @Test
    public void doPostTATokenGenerateDiscover() throws Exception {
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
    }
    
    @Test
    public void doPostTATokenRefundDiscover() throws Exception {
    	
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
    }
    
    @Test
    public void doPostTATokenVoidDiscover() throws Exception {
    	
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
    
    }
    
    
    /////////////////////////////////////////////////////////
    
    //@Test
    public void doPostToken()throws Exception {
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
    }
    
    @Test
    public void doAuthorizePayment()throws Exception {
        assertNotNull("RESTTemplate is null:", restTemplate);
        assertNotNull("clietn is null:", client);
        TransactionResponse response=client.authorizeTransaction(getPrimaryTransaction());

        assertNotNull("Response is null ",response);
        assertNull("Error in response",response.getError());
        log.info("Transaction Tag:{} Transaction id:{}",response.getTransactionTag(),response.getTransactionId());
    }

    @Test
    public void doVoidPayment()throws Exception {
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
        
    }
    
    
    @Test
    public void doRefundPayment()throws Exception {
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
        
    }
    
    @Test
    public void doCapturePayment()throws Exception {
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
        
    }
    
    

    private TransactionRequest getPrimaryTransaction() {
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
        return request;
    }
     
    private TransactionRequest getSecondaryTransaction() {
        TransactionRequest trans=new TransactionRequest();
        trans.setPaymentMethod("credit_card");
        trans.setAmount("0.00");
        trans.setCurrency("USD");
        trans.setTransactionTag("349990997");
        trans.setId("07698G");
        return trans;
    }
    
    
    
}
