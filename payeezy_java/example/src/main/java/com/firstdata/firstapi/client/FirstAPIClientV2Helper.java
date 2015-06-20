package com.firstdata.firstapi.client;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.codehaus.jackson.JsonEncoding;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;
import com.firstdata.firstapi.client.domain.TransactionType;
import com.firstdata.firstapi.client.domain.v2.TransactionRequest;
import com.firstdata.firstapi.client.domain.v2.TransactionResponse;


public class FirstAPIClientV2Helper {
    
    private static final Logger log=LoggerFactory.getLogger(FirstAPIClientV2Helper.class);
    
    @Autowired
    RestTemplate restTemplate;
    
    private String url;
    private String appId;
    private String securedSecret;
    private String token;
    
    
    
    
    public String getUrl() {
        return url;
    }

    
    public void setUrl(String url) {
        this.url = url;
    }

    
    public String getAppId() {
        return appId;
    }

    
    public void setAppId(String appId) {
        this.appId = appId;
    }

    
    public String getSecuredSecret() {
        return securedSecret;
    }

    
    public void setSecuredSecret(String secureId) {
        this.securedSecret = secureId;
    }
    
    

    
    public String getToken() {
        return token;
    }


    
    public void setToken(String token) {
        this.token = token;
    }


/*    private String getValue(String value){
        return new StringBuilder().append("\"").append(value).append("\"").toString();
    }
    */
    private static final String NONCE="nonce";
    
    public static final String APIKEY="apikey";
    public static final String APISECRET="pzsecret";
    public static final String TOKEN="token";
    public static final String TIMESTAMP="timestamp";
    public static final String AUTHORIZE="Authorization";
    public static final String PAYLOAD="payload";
    public static final String OVERRIDE="override";
    
    private Map<String,String> getSecurityKeys(String appId,String secureId,String payLoad) throws Exception{

        Map<String,String> returnMap=new HashMap<String,String>();
        long nonce;
        try {
            
            nonce = Math.abs(SecureRandom.getInstance ("SHA1PRNG").nextLong());     
            log.debug("SecureRandom nonce:{}",nonce);
            returnMap.put(NONCE, Long.toString(nonce));
            returnMap.put(APIKEY, this.appId);
            returnMap.put(TIMESTAMP, Long.toString(System.currentTimeMillis()));
            returnMap.put(TOKEN, this.token);
            returnMap.put(APISECRET, this.securedSecret);
            returnMap.put(PAYLOAD, payLoad);
            returnMap.put(AUTHORIZE, getMacValue(returnMap));
            return returnMap;
            
        } catch (NoSuchAlgorithmException e) {
            log.error(e.getMessage(),e);
            throw new RuntimeException(e.getMessage(),e);
        }       
    }
    
    public String getMacValue(Map<String,String> data) throws Exception{
        Mac mac=Mac.getInstance("HmacSHA256");
        String apiSecret= data.get(APISECRET);
        log.debug("API_SECRET:{}",apiSecret);
        SecretKeySpec secret_key = new SecretKeySpec(apiSecret.getBytes(), "HmacSHA256");
        mac.init(secret_key);
        StringBuilder buff=new StringBuilder();
        buff.append(data.get(APIKEY))
        .append(data.get(NONCE))
        .append(data.get(TIMESTAMP));
        if(data.get(TOKEN)!=null)
            buff.append(data.get(TOKEN));
        if(data.get(PAYLOAD)!=null)
            buff.append(data.get(PAYLOAD));

        log.info(buff.toString());
        byte[] macHash=mac.doFinal(buff.toString().getBytes("UTF-8"));
        log.info("MacHAsh:{}",Arrays.toString( macHash));
        String authorizeString=Base64.encodeBase64String(toHex(macHash));
        log.info("Authorize: {}",authorizeString);
        return authorizeString;
}
    
    public byte[] toHex(byte[] arr) {
        String hex=Hex.encodeHexString(arr);
        log.info("Apache common value:{}" , hex);
        return hex.getBytes();
    }
    private HttpHeaders getHttpHeader(String appId,String securityKey,String payload ) throws Exception{
        Map<String,String> encriptedKey=getSecurityKeys(appId, securityKey,payload);
        HttpHeaders header=new HttpHeaders();
        Iterator<String> iter=encriptedKey.keySet().iterator();
        while(iter.hasNext()) {
            String key=iter.next();
            if(PAYLOAD.equals(key))
                continue;
            header.add(key, encriptedKey.get(key));
        }
       
        return header;
    }
    public String getJSONObject(Object data) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        OutputStream stream = new BufferedOutputStream(byteStream);
        JsonGenerator jsonGenerator =
            objectMapper.getJsonFactory().createJsonGenerator(stream,
                                                              JsonEncoding.UTF8);
        // mapper.getSerializationConfig();
        objectMapper.writeValue(jsonGenerator, data);
        // mapper.writeValue(stream, payload);
        stream.flush();
        return new String(byteStream.toByteArray());
    }
    public TransactionResponse doPrimaryTransaction(TransactionRequest trans) throws Exception{
        

        Assert.notNull(trans.getAmount(),"Amount is not present");
        
        Assert.notNull(trans.getTransactionType(),"Transaction type is not present");
    
        String url=this.url+"/transactions";
        String payload=getJSONObject(trans);
        HttpEntity<TransactionRequest> request=new HttpEntity<TransactionRequest>(trans,getHttpHeader(this.appId, this.securedSecret,payload));
        ResponseEntity<TransactionResponse> response= restTemplate.exchange(url, HttpMethod.POST, request, TransactionResponse.class);
//        ResponseEntity<HashMap> response= restTemplate.exchange(url, HttpMethod.POST, request, HashMap.class);
//      return doTransaction(trans,credentials);
        return response.getBody();
//        return null;
    
    }
       public TransactionResponse doSecondaryTransaction(TransactionRequest trans) throws Exception{
            Assert.notNull(trans.getTransactionTag(),"Transaction Tag is not present");
            Assert.notNull(trans.getId(),"Id is not present");    
            Assert.notNull(trans.getTransactionType(),"Transaction type is not present");
            String url=this.url+"/transactions/{id}";
            String payload=getJSONObject(trans);
            HttpEntity<TransactionRequest> request=new HttpEntity<TransactionRequest>(trans,getHttpHeader(this.appId, this.securedSecret,payload));
            ResponseEntity<TransactionResponse> response= restTemplate.exchange(url, HttpMethod.POST, request, TransactionResponse.class,trans.getId());
//        return doTransaction(trans,credentials);
            return response.getBody();

//           return null;
        }

    
    
    public TransactionResponse purchaseTransaction(TransactionRequest trans) throws Exception{
        
        trans.setTransactionType(TransactionType.PURCHASE.name());
        return doPrimaryTransaction(trans);
    }
    
    public TransactionResponse authorizeTransaction(TransactionRequest trans) throws Exception{
        trans.setTransactionType(TransactionType.AUTHORIZE.name());
        return doPrimaryTransaction(trans);
    }
    public TransactionResponse captureTransaction(TransactionRequest trans)throws Exception{
        trans.setTransactionType(TransactionType.CAPTURE.name());
        
        return doSecondaryTransaction(trans);
    }
    public TransactionResponse refundTransaction(TransactionRequest trans)throws Exception{
        trans.setTransactionType(TransactionType.REFUND.name());
        return doSecondaryTransaction(trans);
    }
    public TransactionResponse voidTransaction(TransactionRequest trans)throws Exception{
        trans.setTransactionType(TransactionType.VOID.name());      
        return doSecondaryTransaction(trans);
    }
    

}
