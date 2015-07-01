package com.firstdata.firstapi.client;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Assert;
import org.junit.Before;
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

@ContextConfiguration(locations={"classpath:/META-INF/spring/core-rest-client-v2-context.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public abstract class AbstractClientTest {
	
	private static final Logger log=LoggerFactory.getLogger(AbstractClientTest.class);
	public static final String PRIMARY_TRAN_KEY = "prime";
	public static final String SECONDARY_TRAN_KEY = "secondary";
	private static final String TEST_STATUS_APPROVED  = "approved";
	
	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private FirstAPIClientV2Helper client;
	
	public RestTemplate getRestTemplate() {
		return restTemplate;
	}
	public void setRestTemplate(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}
	public FirstAPIClientV2Helper getClient() {
		return client;
	}
	public void setClient(FirstAPIClientV2Helper client) {
		this.client = client;
	}

	@Before
	public void setupCheck(){
        Assert.assertNotNull(restTemplate);
        Assert.assertNotNull(client);
	}
	
    protected void verifyResponse(TransactionResponse response) {
    	assertNotNull("Response is null ",response);
        assertNull("Error in response",response.getError());
        assertTrue(TEST_STATUS_APPROVED.equalsIgnoreCase(response.getTransactionStatus()));
        log.info("Transaction Tag:{} Transaction id:{}",response.getTransactionTag(),response.getTransactionId());        
    }

    public TransactionResponse doPrimaryTransaction(TransactionRequest req) throws Exception{
    	TransactionResponse response = null;
    	
    	assertTrue("Prime transaction requested transaction type name cannot be null ", req.getTransactionType() != null);
    	
    	if (TransactionType.PURCHASE.name().equalsIgnoreCase(req.getTransactionType()))
    		response = client.purchaseTransaction(req);
    	else {
    		response = client.doPrimaryTransaction(req);
    	}
    	return response;
    }
    
    public HashMap<String, TransactionResponse> doSecondaryTransaction(TransactionRequest primeReq, TransactionRequest secondaryReq) throws Exception{
    	TransactionResponse primeReponse = null;
    	TransactionResponse secondaryReponse = null;
    	HashMap<String, TransactionResponse> responseMap = new HashMap<String, TransactionResponse>();
    	
    	assertTrue("Secondary transaction requested transaction type name cannot be null ", secondaryReq.getTransactionType() != null);    	
    	
    	primeReponse = doPrimaryTransaction(primeReq);

    	assertNotNull("Prime transaction response is null ",	primeReponse);
        assertNull("Error in prime transaction response", primeReponse.getError());
        log.info("Prime Transction Tag:{} Transaction id:{}",primeReponse.getTransactionTag(),primeReponse.getTransactionId());

        responseMap.put(PRIMARY_TRAN_KEY, primeReponse);
        
        secondaryReq.setId(primeReponse.getTransactionId());
        secondaryReq.setTransactionTag(primeReponse.getTransactionTag());
        secondaryReq.setAmount(primeReponse.getAmount());        
    	
        if (TransactionType.VOID.name().equalsIgnoreCase(secondaryReq.getTransactionType())) {
			secondaryReponse = client.voidTransaction(secondaryReq);
		}
        
        responseMap.put(SECONDARY_TRAN_KEY, secondaryReponse);
    	return responseMap;
    }
	
}
