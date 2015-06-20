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
import com.firstdata.firstapi.client.domain.v2.TransactionRequest;
import com.firstdata.firstapi.client.domain.v2.TransactionResponse;

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
