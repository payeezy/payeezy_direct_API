package com.firstdata.firstapi.client;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.math.BigDecimal;
import java.util.HashMap;

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
import com.firstdata.firstapi.client.domain.v2.Telecheck;
import com.firstdata.firstapi.client.domain.v2.TransactionRequest;
import com.firstdata.firstapi.client.domain.v2.TransactionResponse;
import com.firstdata.firstapi.client.domain.v2.ValueLink;

public class TeleCheckClientTest extends AbstractClientTest {
    private static final Logger log=LoggerFactory.getLogger(ValueLinkClientTest.class);
    private static final String TEST_PURCHASE_AMT = "3700";
    private static final String TEST_STATUS_APPROVED  = "approved";
    
    protected TransactionRequest getTelecheckPrimeTransactionTemplate(String tranTypeName) {
        TransactionRequest request=new TransactionRequest();
        request.setCurrency("USD");
        request.setPaymentMethod("tele_check");
        request.setAmount(TEST_PURCHASE_AMT);
        request.setTransactionType(tranTypeName);
        request.setReferenceNo("Telecheck_12345");
        
        Telecheck  tk = new Telecheck();
        tk.setCheckNumber("0101");
        tk.setBankRoutingNumber("BN1234567801234567890");
        tk.setBankAccountNumber("I7101874");
        // Need check following payload as it is from TestScript Excel sheet from TCoE
        tk.setCheckType("P");
        tk.setAccountholderName("Tom Eck");
        tk.setCustomerIdType("0");
        tk.setCustomerIdNumber("TX10531769");
        tk.setClientEmail("rajan.veeramani@firstdata.com");
        tk.setGiftCardAmount("100");
        tk.setVip("n");
        tk.setClerkId("RVK_001");
        tk.setDeviceId("jkhsdfjkhsk");
        tk.setReleaseType("X");
        tk.setRegistrationNumber("12345");
        tk.setRegistrationDate("01012014");
        tk.setDateOfBirth("01012010");
        request.setTeleCheck(tk);
        
        Address addr = new Address();
        addr.setAddressLine1("225 Liberty Street");
        addr.setCity("NYC");
        addr.setState("NY");
        addr.setZip("10281");
        addr.setCountry("US");
        
        request.setBilling(addr);
        return request;
    }

    protected TransactionRequest getTelecheckSecondaryTransactionTemplate(String tranTypeName) {
        TransactionRequest request=new TransactionRequest();
        request.setCurrency("USD");
        request.setPaymentMethod("tele_check");
        request.setAmount(TEST_PURCHASE_AMT);
        request.setTransactionType(tranTypeName);
        Telecheck  tk = new Telecheck();
        request.setTeleCheck(tk);
        
        return request;
    }
    
    @Test
    public void doPurchaseTest() throws Exception{
    	TransactionRequest request = getTelecheckPrimeTransactionTemplate(TransactionType.PURCHASE.name());
    	TransactionResponse response=doPrimaryTransaction(request);

	    verifyResponse(response);
	    assertTrue(TEST_STATUS_APPROVED.equalsIgnoreCase(response.getTransactionStatus()));
    }    

    @Test
    public void doVoidTest() throws Exception{
        TransactionRequest primeRequest = getTelecheckPrimeTransactionTemplate(TransactionType.VOID.name());
        
    	TransactionResponse response=doPrimaryTransaction(primeRequest);

	    verifyResponse(response);
	    assertTrue(TEST_STATUS_APPROVED.equalsIgnoreCase(response.getTransactionStatus()));
    }

    @Test
    public void doTaggedVoidTest() throws Exception{
        TransactionRequest primeRequest = getTelecheckSecondaryTransactionTemplate(TransactionType.VOID.name());
        primeRequest.setTransactionTag("1846059");	
        
        //We post tagged void directly since transaction tag is already known, we can also use doSecondaryTransaction test method, which will first post a purchase, and use returned 
        // transaction id and tagged for tagged void transaction
    	TransactionResponse response=doPrimaryTransaction(primeRequest);

	    verifyResponse(response);
	    assertTrue(TEST_STATUS_APPROVED.equalsIgnoreCase(response.getTransactionStatus()));
    }

    @Test
    public void doTaggedRefundTest() throws Exception{
        TransactionRequest primeRequest = getTelecheckSecondaryTransactionTemplate(TransactionType.REFUND.name());
        primeRequest.setTransactionTag("1845156");	
        
        //We post tagged void directly since transaction tag is already known, we can also use doSecondaryTransaction test method, which will first post a purchase, and use returned 
        // transaction id and tagged for tagged void transaction
    	TransactionResponse response=doPrimaryTransaction(primeRequest);

	    verifyResponse(response);
	    assertTrue(TEST_STATUS_APPROVED.equalsIgnoreCase(response.getTransactionStatus()));
    }
    
}
