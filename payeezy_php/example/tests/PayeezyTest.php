
<?php

require_once('Payeezy.php');

class PayeezyTest extends PHPUnit_Framework_TestCase
{

    public static $payeezy;

    public static function setUpBeforeClass(){

        self::$payeezy = new Payeezy();
        
	    self::$payeezy->setApiKey("y6pWAJNyJyjGv66IsVuWnklkKUPFbb0a");
    	self::$payeezy->setApiSecret("86fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f7");
    	self::$payeezy->setMerchantToken("fdoa-a480ce8951daa73262734cf102641994c1e55e7cdf4c02b6");
    	self::$payeezy->setTokenUrl("https://api-cert.payeezy.com/v1/transactions/tokens");  
        self::$payeezy->setUrl("https://api-cert.payeezy.com/v1/transactions");

    }

    public function processInput($data) {
        $data = trim($data);
        $data = stripslashes($data);
        $data = htmlspecialchars($data);
        return strval($data);
    }

    public function setTokenPayload(){

        $card_holder_name = $transaction_type = $auth = $card_number = $ta_token = $card_type = $card_cvv = $card_expiry = $currency_code = $merchant_ref="";
        
        $transaction_type = $this->processInput("FDToken");
        $auth = $this->processInput("false");
        $ta_token = $this->processInput("NOIW");

        $card_holder_name = $this->processInput("John Smith");
        $card_number = $this->processInput("4788250000028291");
        $card_type = $this->processInput("visa");
        $card_cvv = $this->processInput("123");
        $card_expiry = $this->processInput("1250");
        $currency_code = $this->processInput("USD");
        $merchant_ref = $this->processInput("Astonishing-Sale");

        $getTokenPayload = array(

            "type"=> $transaction_type,
            "auth" => $auth,
            "ta_token" => $ta_token,
            "card_type" => $card_type,
            "card_holder_name" => $card_holder_name,
            "card_number" => $card_number,
            "card_exp_date" => $card_expiry,
            "card_cvv" => $card_cvv,
        
        );

        return $getTokenPayload;

    }

   
    

    

    public function testGetToken()
    {
        $primaryTxResponse_JSON = json_decode(self::$payeezy->tokenize($this->setTokenPayload()));
        $this->assertEquals($primaryTxResponse_JSON->status,"success"); 
    }

    

public function setPrimaryTxPayload(){

        $card_holder_name = $card_number = $card_type = $card_cvv = $card_expiry = $currency_code = $merchant_ref="";

        $card_holder_name = $this->processInput("John Smith");
        $card_number = $this->processInput("4788250000028291");
        $card_type = $this->processInput("visa");
        $card_cvv = $this->processInput("123");
        $card_expiry = $this->processInput("1250");
        $amount = $this->processInput("1200");
        $currency_code = $this->processInput("USD");
        $merchant_ref = $this->processInput("Astonishing-Sale");
        $method = $this->processInput("credit_card");


        $primaryTxPayload = array(
            "amount"=> $amount,
            "card_number" => $card_number,
            "card_type" => $card_type,
            "card_holder_name" => $card_holder_name,
            "card_cvv" => $card_cvv,
            "card_expiry" => $card_expiry,
            "merchant_ref" => $merchant_ref,
            "currency_code" => $currency_code,
            "method"=> $method,
        );

        return $primaryTxPayload;

    }

    

    public function setSecondaryTxPayload($transaction_id, $transaction_tag, $amount, $split_shipment){

        $transaction_type = $merchant_ref= $currency_code = "";

        $transaction_id = $this->processInput($transaction_id);
        $transaction_tag = $this->processInput($transaction_tag);
        $amount = $this->processInput($amount);
        $currency_code = $this->processInput("USD");
        $merchant_ref = $this->processInput("Astonishing-Sale");
        $split_shipment = $this->processInput($split_shipment);
        $method = $this->processInput("credit_card");

        if( is_null($split_shipment) )
        {
            $secondaryTxPayload = array(
                "amount"=> $amount,
                "transaction_tag" => $transaction_tag,
                "transaction_id" => $transaction_id,
                "merchant_ref" => $merchant_ref,
                "currency_code" => $currency_code,
                "method"=> $method,
            );
        }
        else{
            $secondaryTxPayload = array(
                "amount"=> $amount,
                "transaction_tag" => $transaction_tag,
                "transaction_id" => $transaction_id,
                "merchant_ref" => $merchant_ref,
                "currency_code" => $currency_code,
                "split_shipment" => $split_shipment,
                "method"=> $method,
            );
        }


        return $secondaryTxPayload;

    }


  

    public function testAuthorize()
    {
        $primaryTxResponse_JSON = json_decode(self::$payeezy->authorize($this->setPrimaryTxPayload()));
        $this->assertEquals($primaryTxResponse_JSON->transaction_status,"approved");
    }

    public function testPurchase()
    {
        $primaryTxResponse_JSON = json_decode(self::$payeezy->purchase($this->setPrimaryTxPayload()));
        $this->assertEquals($primaryTxResponse_JSON->transaction_status,"approved");
    }


    public function testCapture()
    {
        // first do an authorize
        $primaryTxResponse_JSON = json_decode(self::$payeezy->authorize($this->setPrimaryTxPayload()));
        $this->assertEquals($primaryTxResponse_JSON->transaction_status,"approved");

        $secondaryTxPayload = $this->setSecondaryTxPayload($primaryTxResponse_JSON->transaction_id
                                                            ,$primaryTxResponse_JSON->transaction_tag
                                                            ,$primaryTxResponse_JSON->amount
                                                            ,null);
        // capture the previous txn using the transaction id and transaction tag
        $secondaryTxResponse_JSON = json_decode(self::$payeezy->capture($secondaryTxPayload));
        $this->assertEquals($secondaryTxResponse_JSON->transaction_status,"approved");
    }

    public function testVoid()
    {
        // first do an authorize
        $primaryTxResponse_JSON = json_decode(self::$payeezy->authorize($this->setPrimaryTxPayload()));
        $this->assertEquals($primaryTxResponse_JSON->transaction_status,"approved");

        $secondaryTxPayload = $this->setSecondaryTxPayload($primaryTxResponse_JSON->transaction_id
                                                            ,$primaryTxResponse_JSON->transaction_tag
                                                            ,$primaryTxResponse_JSON->amount
                                                            ,null);
        // void the previous txn using the transaction id and transaction tag
        $secondaryTxResponse_JSON = json_decode(self::$payeezy->void($secondaryTxPayload));
        $this->assertEquals($secondaryTxResponse_JSON->transaction_status,"approved");
    }

    public function testRefund()
    {
        // first do a purchase
        $primaryTxResponse_JSON = json_decode(self::$payeezy->purchase($this->setPrimaryTxPayload()));
        $this->assertEquals($primaryTxResponse_JSON->transaction_status,"approved");

        $secondaryTxPayload = $this->setSecondaryTxPayload($primaryTxResponse_JSON->transaction_id
                                                            ,$primaryTxResponse_JSON->transaction_tag
                                                            ,$primaryTxResponse_JSON->amount
                                                            ,null);
        // refund the purchase using the transaction id and transaction tag
        $secondaryTxResponse_JSON = json_decode(self::$payeezy->refund($secondaryTxPayload));
        $this->assertEquals($secondaryTxResponse_JSON->transaction_status,"approved");
    }

    public function testSplit()
    {
        // first do an authorize
        $primaryTxResponse_JSON = json_decode(self::$payeezy->authorize($this->setPrimaryTxPayload()));
        $this->assertEquals($primaryTxResponse_JSON->transaction_status,"approved");

        // in this example, the shipment is split into 2 txns
        $split_amount = ($primaryTxResponse_JSON->amount)/2;

        // the first shipment is sent out .. split shipmant value is 01/99 since the total no. of shipments is unknown
        $secondaryTxPayload = $this->setSecondaryTxPayload($primaryTxResponse_JSON->transaction_id
                                                            ,$primaryTxResponse_JSON->transaction_tag
                                                            ,$split_amount
                                                            ,"01/99");

        $secondaryTxResponse_JSON = json_decode(self::$payeezy->split_shipment($secondaryTxPayload));
        $this->assertEquals($secondaryTxResponse_JSON->transaction_status,"approved");
        
        // the second shipment is sent out. It is also the final shipment .. therefore 02/02
        $secondaryTxPayload = $this->setSecondaryTxPayload($primaryTxResponse_JSON->transaction_id
                                                            ,$primaryTxResponse_JSON->transaction_tag
                                                            ,$split_amount
                                                            ,"02/02");

        $secondaryTxResponse_JSON = json_decode(self::$payeezy->split_shipment($secondaryTxPayload));
        $this->assertEquals($secondaryTxResponse_JSON->transaction_status,"approved");
    }

}
?>
