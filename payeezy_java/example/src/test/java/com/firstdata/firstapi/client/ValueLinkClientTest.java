package com.firstdata.firstapi.client;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.math.BigDecimal;
import java.util.HashMap;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.firstdata.firstapi.client.domain.TransactionType;
import com.firstdata.firstapi.client.domain.v2.TransactionRequest;
import com.firstdata.firstapi.client.domain.v2.TransactionResponse;
import com.firstdata.firstapi.client.domain.v2.ValueLink;


public class ValueLinkClientTest<K> extends AbstractClientTest{
    private static final Logger log=LoggerFactory.getLogger(ValueLinkClientTest.class);
    private static final String TEST_PURCHASE_AMT = "600";

    /**
     * {"amount":"6100",
     * "transaction_type":"purchase",
     * "method":"valuelink",
     * "currency_code":"USD",
     * "valuelink":{"cardholder_name":"Joe Smith","cc_number":"7777061906994745","credit_card_type":"Gift"}}
     * @return
     */
    protected TransactionRequest getValueLinkPrimeTransactionTemplate(String tranTypeName) {
        TransactionRequest request=new TransactionRequest();
        request.setCurrency("USD");
        request.setPaymentMethod("valuelink");
        request.setAmount(TEST_PURCHASE_AMT);
        request.setTransactionType(tranTypeName);
        ValueLink vl = new ValueLink();
        vl.setCardHoldersName("Simulated User");
        vl.setCardNumber("7777042858204660");
        vl.setEan("71065155");
        request.setValuelink(vl);
        return request;
    }

    protected TransactionRequest getValueLinkSecondaryTransactionTemplate(String tranTypeName) {
        TransactionRequest request=new TransactionRequest();
        request.setCurrency("USD");
        request.setPaymentMethod("valuelink");
        request.setTransactionType(tranTypeName);
        ValueLink vl = new ValueLink();
        request.setValuelink(vl);
        return request;
    }
    
    
    @Test
    public void doPurchaseTest() throws Exception{
    	BigDecimal prevBalance = getCurrentTestCardBalance();
    	TransactionRequest request = getValueLinkPrimeTransactionTemplate(TransactionType.PURCHASE.name());
    	TransactionResponse response=doPrimaryTransaction(request);

	    verifyResponse(response);
		try {
			BigDecimal currentBal = BigDecimal.valueOf(Double.parseDouble(response.getValueLink().getCurrentBalance()));
			BigDecimal diff = prevBalance.subtract(currentBal);
			assertTrue(diff.compareTo((new BigDecimal(TEST_PURCHASE_AMT)).divide(new BigDecimal("100"))) == 0);
		} catch (Exception e) {
			fail();
		}
	    
    }
    
    @Test
    public void doRefundTest() throws Exception{
    	BigDecimal prevBalance = getCurrentTestCardBalance();    	
        TransactionRequest primeRequest = getValueLinkPrimeTransactionTemplate(TransactionType.PURCHASE.name());
        TransactionRequest secondaryRequest = getValueLinkSecondaryTransactionTemplate(TransactionType.REFUND.name());
        
        HashMap<String, TransactionResponse> responseMap = doSecondaryTransaction(primeRequest, secondaryRequest);
        verifyResponse(responseMap.get(SECONDARY_TRAN_KEY));
		try {
			BigDecimal currentBal = BigDecimal.valueOf(Double.parseDouble(responseMap.get(SECONDARY_TRAN_KEY).getValueLink().getCurrentBalance()));
			BigDecimal diff = prevBalance.subtract(currentBal);
			assertTrue(diff.abs().compareTo(new BigDecimal(0)) == 0);	// beginning balance should equal to current balance since it is void after purchase
		} catch (Exception e) {
			fail();
		}
        
    }

    @Test
    public void doNakedRefundTest() throws Exception{
    	BigDecimal prevBalance = getCurrentTestCardBalance();    	
        TransactionRequest primeRequest = getValueLinkPrimeTransactionTemplate(TransactionType.REFUND.name());
        
        TransactionResponse response = doPrimaryTransaction(primeRequest);

        verifyResponse(response);
		try {
			BigDecimal currentBal = new BigDecimal(response.getValueLink().getCurrentBalance());
			BigDecimal diff = currentBal.subtract(prevBalance);
			assertTrue(diff.abs().compareTo(new BigDecimal(TEST_PURCHASE_AMT)) == 0); // current balance should greater than previous balance since it is a naked refund withough purchase
		} catch (Exception e) {
			fail();
		}
        
    }
    
    @Test
    public void doVoidTest() throws Exception{
    	BigDecimal prevBalance = getCurrentTestCardBalance();    	
        TransactionRequest primeRequest = getValueLinkPrimeTransactionTemplate(TransactionType.PURCHASE.name());
        TransactionRequest secondaryRequest = getValueLinkSecondaryTransactionTemplate(TransactionType.VOID.name());
        
        HashMap<String, TransactionResponse> responseMap = doSecondaryTransaction(primeRequest, secondaryRequest);
        verifyResponse(responseMap.get(SECONDARY_TRAN_KEY));
		try {
			BigDecimal currentBal = BigDecimal.valueOf(Double.parseDouble(responseMap.get(SECONDARY_TRAN_KEY).getValueLink().getCurrentBalance()));
			assertTrue(prevBalance.compareTo(currentBal) == 0);		// current balance should equal to previous balance since trans is voided after a purchase
		} catch (Exception e) {
			fail();
		}
        
    }

    @Test
    public void doNakedVoidTest() throws Exception{
    	BigDecimal beginningBalance = getCurrentTestCardBalance();    	
        TransactionRequest primeRequest = getValueLinkPrimeTransactionTemplate(TransactionType.PURCHASE.name());		// do a purchase first
        TransactionResponse response = doPrimaryTransaction(primeRequest);
        verifyResponse(response);
        
        TransactionRequest nakeVoidRequest = getValueLinkPrimeTransactionTemplate(TransactionType.VOID.name());
        response = doPrimaryTransaction(nakeVoidRequest);
        
		try {
			BigDecimal currentBal = new BigDecimal(response.getValueLink().getCurrentBalance());
			BigDecimal diff = currentBal.subtract(beginningBalance);
			assertTrue(diff.abs().compareTo(new BigDecimal(0)) == 0);	// they should be equal since the purchase trans should get voided.
		} catch (Exception e) {
			fail();
		}
        
    }    
    @Test
    public void doCashoutTest() throws Exception{
    	BigDecimal prevBalance = getCurrentTestCardBalance();    	
        TransactionRequest primeRequest = getValueLinkPrimeTransactionTemplate(TransactionType.CASHOUT.name());
        
        TransactionResponse response = doPrimaryTransaction(primeRequest);
        verifyResponse(response);
        try {
			BigDecimal currentBal = getCurrentTestCardBalance();
			assertTrue(prevBalance.longValue() > 0);
			assertTrue(currentBal.longValue() ==  0);
		} catch (Exception e) {
			fail();
		}
    }
    
    @Test
    public void doReloadTest() throws Exception{
    	BigDecimal prevBalance = getCurrentTestCardBalance();    	
    	TransactionRequest primeRequest = getValueLinkPrimeTransactionTemplate(TransactionType.RELOAD.name());
        
        TransactionResponse response = doPrimaryTransaction(primeRequest);
        verifyResponse(response);
		try {
			BigDecimal currentBal = BigDecimal.valueOf(Double.parseDouble(response.getValueLink().getCurrentBalance()));
			BigDecimal diff = prevBalance.subtract(currentBal);
			assertTrue(diff.compareTo(new BigDecimal(TEST_PURCHASE_AMT)) == 0);
		} catch (Exception e) {
			fail();
		}

    }
    
    @Test
    public void doPartialPurchaseTest() throws Exception{
    	TransactionRequest request = getValueLinkPrimeTransactionTemplate(TransactionType.PURCHASE.name());
    	request.setAmount("60000");  // bigger number than card balance
    	TransactionResponse response=doPrimaryTransaction(request);

	    verifyResponse(response);
		try {
			BigDecimal currentBal = BigDecimal.valueOf(Double.parseDouble(response.getValueLink().getCurrentBalance()));
			assertTrue(currentBal.longValue()  == 0);
		} catch (Exception e) {
			fail();
		}
	    
    }
    
    @Test
    public void doActivationTest() throws Exception{
    	// first try to deactivate the card
    	TransactionRequest request = getValueLinkPrimeTransactionTemplate(TransactionType.DEACTIVATION.name());
    	TransactionResponse response=doPrimaryTransaction(request);
	    verifyResponse(response);
	    
	    // now try to activate it again with new balance
	    request = getValueLinkPrimeTransactionTemplate(TransactionType.ACTIVATION.name());
	    request.setAmount("6000");
	    response = doPrimaryTransaction(request);
	    verifyResponse(response);
	    
		try {
			BigDecimal currentBal = BigDecimal.valueOf(Double.parseDouble(response.getValueLink().getCurrentBalance()));
			assertTrue(currentBal.compareTo(new BigDecimal("60") ) == 0);
		} catch (Exception e) {
			fail();
		}
        
    }
    
    @Test
    public void doDeactivationTest() throws Exception{
    	// first try to deactivate the card
    	TransactionRequest request = getValueLinkPrimeTransactionTemplate(TransactionType.DEACTIVATION.name());
    	TransactionResponse response=doPrimaryTransaction(request);
	    verifyResponse(response);

		try {
			BigDecimal currentBal = BigDecimal.valueOf(Double.parseDouble(response.getValueLink().getCurrentBalance()));
			assertTrue(currentBal.compareTo(new BigDecimal("0") ) == 0);
		} catch (Exception e) {
			fail();
		}
	    
    }
    
    @Test
    public void doBalanceInquiryTest() throws Exception{
    	BigDecimal balance = getCurrentTestCardBalance();
    	assertTrue(balance.compareTo(new BigDecimal(-1d)) != 0);
    }
    
	private BigDecimal getCurrentTestCardBalance() throws Exception{
		TransactionRequest request = getValueLinkPrimeTransactionTemplate(TransactionType.BALANCE_INQUIRY.name());
		TransactionResponse response = doPrimaryTransaction(request); 
		try {
			String currentBal = response.getValueLink().getCurrentBalance();
			return new BigDecimal(currentBal);
		} catch (Exception e) {
			return new BigDecimal(-1d);
		}
		
	}	
    
}
