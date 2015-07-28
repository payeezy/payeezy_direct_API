<?php
/**
 * File Name: PayeezyCheckLink.php
 * Description: Unit Test for Telecheck and Value Link
 * Author: Richard Ardila - richard.ardila@firstdata.com
 * Date: 2/13/15
 * Time: 3:08 PM
 * Updates: Please include Initials, short description of the update and data/time when update took place
 */

require_once('.\src\Payeezy.php');

class PayeezyCheckLink extends PHPUnit_Framework_TestCase
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



  public function setTeleCheckPrimaryTxPayload()
  {

    $method = $transaction_type = $amount = $currency_code = $check_number = $check_type = $routing_number="";
    $account_number = $accountholder_name = $customer_id_type = $customer_id_number = $client_email = $gift_card_amount = $vip="";
    $clerk_id = $device_id = $micr = $release_type = $registration_number = $registration_date = $date_of_birth="";
    $street = $city = $state_province = $zip_postal_code = $country="";

    $method = $this->processInput("tele_check");
    $transaction_type = $this->processInput("purchase");
    $amount = $this->processInput("10");
    $currency_code = $this->processInput("USD");
    $check_number = $this->processInput("4788250000028291");
    $check_type = $this->processInput("C");
    $routing_number = $this->processInput("123456789");
    $account_number = $this->processInput("123");
    $accountholder_name = $this->processInput("Tom Eck");
    $customer_id_type = $this->processInput("1");
    $customer_id_number = $this->processInput("7623786df");
    $client_email = $this->processInput("rajan.veeramani@firstdata.com");
    $gift_card_amount = $this->processInput("100");
    $vip = $this->processInput("n");
    $clerk_id = $this->processInput("RVK_001");
    $device_id = $this->processInput("jkhsdfjkhsk");
    $micr = $this->processInput("jkhjkh");
    $release_type = $this->processInput("X");
    $registration_number = $this->processInput("12345");
    $registration_date = $this->processInput("01012014");
    $date_of_birth = $this->processInput("01012010");
    $street = $this->processInput("225 Liberty Street");
    $city = $this->processInput("NYC");
    $state_province = $this->processInput("NY");
    $zip_postal_code = $this->processInput("10281");
    $country = $this->processInput("US");

    $primaryTxPayload = array(
      "method" => $method,
      "transaction_type" => $transaction_type,
      "amount"=> $amount,
      "currency_code" => $currency_code,
      "check_number" => $check_number,
      "check_type" => $check_type,
      "routing_number" => $routing_number,
      "account_number" => $account_number,
      "accountholder_name" => $accountholder_name,
      "customer_id_type" => $customer_id_type,
      "customer_id_number" => $customer_id_number,
      "client_email" => $client_email,
      "gift_card_amount" => $gift_card_amount,
      "vip" => $vip,
      "clerk_id" => $clerk_id,
      "device_id" => $device_id,
      "micr" => $micr,
      "release_type" => $release_type,
      "registration_number" => $registration_number,
      "registration_date" => $registration_date,
      "date_of_birth" => $date_of_birth,
      "street" => $street,
      "city" => $city,
      "state_province" => $state_province,
      "zip_postal_code" => $zip_postal_code,
      "country" => $country,
    );

    return $primaryTxPayload;

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

  public function setTeleCheckSecondaryTxPayload($transaction_id, $transaction_tag, $amount, $txflag)
  {

    $transaction_type = $merchant_ref= $currency_code = "";

    switch ($txflag) {
      case 'void':
        # code...
        break;
      
      case 'taggedvoid':
        # code...
        break;

      default:
        # code...
        # default case will be tagged refund
        break;
    }
    $transaction_id = $this->processInput($transaction_id);
    $transaction_tag = $this->processInput($transaction_tag);
    $amount = $this->processInput($amount);
    $currency_code = $this->processInput("USD");
    $merchant_ref = $this->processInput("Astonishing-Sale");
    $split_shipment = $this->processInput($split_shipment);

    if( is_null($split_shipment) )
    {
      $secondaryTxPayload = array(
        "amount"=> $amount,
        "transaction_tag" => $transaction_tag,
        "transaction_id" => $transaction_id,
        "merchant_ref" => $merchant_ref,
        "currency_code" => $currency_code,
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
      );
    }


    return $secondaryTxPayload;

  }

  public function setValueLinkPrimaryTxPayload(){

    $card_holder_name = $card_number = $card_type = $card_cvv = $card_expiry = $currency_code = $merchant_ref="";

    $card_holder_name = $this->processInput("John Smith");
    $card_number = $this->processInput("4788250000028291");
    $card_type = $this->processInput("visa");
    $card_cvv = $this->processInput("123");
    $card_expiry = $this->processInput("1218");
    $amount = $this->processInput("1200");
    $currency_code = $this->processInput("USD");
    $merchant_ref = $this->processInput("Astonishing-Sale");

    $primaryTxPayload = array(
      "amount"=> $amount,
      "card_number" => $card_number,
      "card_type" => $card_type,
      "card_holder_name" => $card_holder_name,
      "card_cvv" => $card_cvv,
      "card_expiry" => $card_expiry,
      "merchant_ref" => $merchant_ref,
      "currency_code" => $currency_code,
    );

    return $primaryTxPayload;

  }

  public function setValueLinkSecondaryTxPayload($transaction_id, $transaction_tag, $amount, $split_shipment){

    $transaction_type = $merchant_ref= $currency_code = "";

    $transaction_id = $this->processInput($transaction_id);
    $transaction_tag = $this->processInput($transaction_tag);
    $amount = $this->processInput($amount);
    $currency_code = $this->processInput("USD");
    $merchant_ref = $this->processInput("Astonishing-Sale");
    $split_shipment = $this->processInput($split_shipment);

    if( is_null($split_shipment) )
    {
      $secondaryTxPayload = array(
        "amount"=> $amount,
        "transaction_tag" => $transaction_tag,
        "transaction_id" => $transaction_id,
        "merchant_ref" => $merchant_ref,
        "currency_code" => $currency_code,
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
      );
    }


    return $secondaryTxPayload;

  }

/**
   * [testTeleCheckPurchase description]
   * 
   */
  public function testGetToken()
  {
    $primaryTxResponse_JSON = json_decode(self::$payeezy->getToken($this->setTeleCheckPrimaryTxPayload()));
    $this->assertEquals($primaryTxResponse_JSON->transaction_status,"approved");
  }

  /**
   * [testTeleCheckPurchase description]
   * 
   */
  public function testTeleCheckPurchase()
  {
    $primaryTxResponse_JSON = json_decode(self::$payeezy->telecheck_purchase($this->setTeleCheckPrimaryTxPayload()));
    $this->assertEquals($primaryTxResponse_JSON->transaction_status,"approved");
  }

  /**
   * [testTeleCheckVoid description]
   * 
   */
  public function testTeleCheckVoid()
  {
    // first do a purchase
      $primaryTxResponse_JSON = json_decode(self::$payeezy->telecheck_purchase($this->setTeleCheckPrimaryTxPayload()));
      $this->assertEquals($primaryTxResponse_JSON->transaction_status,"approved");

      $secondaryTxPayload = $this->setTeleCheckSecondaryTxPayload($primaryTxResponse_JSON->transaction_id
                                                            ,$primaryTxResponse_JSON->transaction_tag
                                                            ,$primaryTxResponse_JSON->amount
                                                            ,null);
    // void the previous txn using the transaction id and transaction tag
      $secondaryTxResponse_JSON = json_decode(self::$payeezy->telecheck_void($secondaryTxPayload));
      $this->assertEquals($secondaryTxResponse_JSON->transaction_status,"approved");
  }

  /**
   * [testTeleCheckTaggedVoid description]
   * 
   */
  public function testTeleCheckTaggedVoid()
  {
    // first do a purchase
      $primaryTxResponse_JSON = json_decode(self::$payeezy->telecheck_tagged_void($this->setTeleCheckPrimaryTxPayload()));
      $this->assertEquals($primaryTxResponse_JSON->transaction_status,"approved");

      $secondaryTxPayload = $this->setTeleCheckSecondaryTxPayload($primaryTxResponse_JSON->transaction_id
                                                            ,$primaryTxResponse_JSON->transaction_tag
                                                            ,$primaryTxResponse_JSON->amount
                                                            ,null);
    // void the previous txn using the transaction id and transaction tag
      $secondaryTxResponse_JSON = json_decode(self::$payeezy->telecheck_tagged_void($secondaryTxPayload));
      $this->assertEquals($secondaryTxResponse_JSON->transaction_status,"approved");
  }

  /**
   * [testTeleCheckTaggedRefund description]
   * 
   */
  public function testTeleCheckTaggedRefund()
  {
    // first do a purchase
      $primaryTxResponse_JSON = json_decode(self::$payeezy->telecheck_tagged_refund($this->setTeleCheckPrimaryTxPayload()));
      $this->assertEquals($primaryTxResponse_JSON->transaction_status,"approved");

      $secondaryTxPayload = $this->setTeleCheckSecondaryTxPayload($primaryTxResponse_JSON->transaction_id
                                                            ,$primaryTxResponse_JSON->transaction_tag
                                                            ,$primaryTxResponse_JSON->amount
                                                            ,null);
    // void the previous txn using the transaction id and transaction tag
      $secondaryTxResponse_JSON = json_decode(self::$payeezy->telecheck_tagged_refund($secondaryTxPayload));
      $this->assertEquals($secondaryTxResponse_JSON->transaction_status,"approved");
  }

  /**
   * [testValueLinkPurchase description]
   * 
   */
  public function testValueLinkPurchase()
  {
    $primaryTxResponse_JSON = json_decode(self::$payeezy->valuelink_purchase($this->setValueLinkPrimaryTxPayload()));
    $this->assertEquals($primaryTxResponse_JSON->transaction_status,"approved");
  }


  /**
   * [testValueLinkRefund description]
   * 
   */
  public function testValueLinkRefund()
  {
    // first do a purchase
      $primaryTxResponse_JSON = json_decode(self::$payeezy->valuelink_purchase($this->setValueLinkPrimaryTxPayload()));
      $this->assertEquals($primaryTxResponse_JSON->transaction_status,"approved");

      $secondaryTxPayload = $this->setValueLinkSecondaryTxPayload($primaryTxResponse_JSON->transaction_id
                                                            ,$primaryTxResponse_JSON->transaction_tag
                                                            ,$primaryTxResponse_JSON->amount
                                                            ,null);
    // refund the previous txn using the transaction id and transaction tag
      $secondaryTxResponse_JSON = json_decode(self::$payeezy->valuelink_refund($secondaryTxPayload));
      $this->assertEquals($secondaryTxResponse_JSON->transaction_status,"approved");
  }

  
  /**
   * [testValueLinkVoid description]
   * 
   */
  public function testValueLinkVoid()
  {
    // first do a purchase
      $primaryTxResponse_JSON = json_decode(self::$payeezy->valuelink_purchase($this->setValueLinkPrimaryTxPayload()));
      $this->assertEquals($primaryTxResponse_JSON->transaction_status,"approved");

      $secondaryTxPayload = $this->setValueLinkSecondaryTxPayload($primaryTxResponse_JSON->transaction_id
                                                            ,$primaryTxResponse_JSON->transaction_tag
                                                            ,$primaryTxResponse_JSON->amount
                                                            ,null);
    // void the previous txn using the transaction id and transaction tag
      $secondaryTxResponse_JSON = json_decode(self::$payeezy->valuelink_void($secondaryTxPayload));
      $this->assertEquals($secondaryTxResponse_JSON->transaction_status,"approved");
  }

  /**
   * [testValueLinkCashout description]
   * 
   */
  public function testValueLinkCashout()
  {
    $primaryTxResponse_JSON = json_decode(self::$payeezy->valuelink_cashout($this->setValueLinkPrimaryTxPayload()));
    $this->assertEquals($primaryTxResponse_JSON->transaction_status,"approved");
  }

  /**
   * [testValueLinkReload description]
   * 
   */
  public function testValueLinkReload()
  {
    $primaryTxResponse_JSON = json_decode(self::$payeezy->valuelink_reload($this->setValueLinkPrimaryTxPayload()));
    $this->assertEquals($primaryTxResponse_JSON->transaction_status,"approved");
  }


  /**
   * [testValueLinkPartialPurchase description]
   * 
   */
  public function testValueLinkPartialPurchase()
  {
    $primaryTxResponse_JSON = json_decode(self::$payeezy->valuelink_partial_purchase($this->setValueLinkPrimaryTxPayload()));
    $this->assertEquals($primaryTxResponse_JSON->transaction_status,"approved");
  }


  /**
   * [testValueLinkActivation description]
   * 
   */
  public function testValueLinkActivation()
  {
    $primaryTxResponse_JSON = json_decode(self::$payeezy->valuelink_activation($this->setValueLinkPrimaryTxPayload()));
    $this->assertEquals($primaryTxResponse_JSON->transaction_status,"approved");
  }


  /**
   * [testValueLinkDeacivation description]
   * 
   */
  public function testValueLinkDeacivation()
  {
    $primaryTxResponse_JSON = json_decode(self::$payeezy->valuelink_deactivation($this->setValueLinkPrimaryTxPayload()));
    $this->assertEquals($primaryTxResponse_JSON->transaction_status,"approved");
  }

  /**
   * [testValueLinkBalanceInquiry description]
   * 
   */
  public function testValueLinkBalanceInquiry()
  {
    $primaryTxResponse_JSON = json_decode(self::$payeezy->valuelink_balance_inqury($this->setValueLinkPrimaryTxPayload()));
    $this->assertEquals($primaryTxResponse_JSON->transaction_status,"approved");
  }
}
?>
