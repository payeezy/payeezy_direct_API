<?php

class Payeezy
{
  /**
   * @var string The Payeezy API params to be used for requests.
   */
  public static $apiKey;
  public static $apiSecret;
  public static $merchantToken;
  public static $baseURL;
  public static $tokenURL;
  public static $url;

  /**
   * Sets the API key to be used for requests.
   *
   * @param string $apiKey
   */
  public static function setApiKey($apiKey)
  {
    self::$apiKey = $apiKey;
  }
  /**
   * Sets the API key to be used for requests.
   *
   * @param string $apiKey
   */
  public static function getApiKey()
  {
    return self::$apiKey;
  }
    /**
   * Sets the API secret to be used for requests.
   *
   * @param string $apiSecret
   */
  public static function setApiSecret($apiSecret)
  {
    self::$apiSecret = $apiSecret;
  }
  /**
   * Sets the API secret to be used for requests.
   *
   * @param string $apiSecret
   */
  public static function getApiSecret()
  {
    return self::$apiSecret;
  }
  /**
   * Sets the API Base URL.
   *
   * @param string $url
   */
  public static function setUrl($baseURL)
  {
    self::$baseURL = $baseURL;
  }
  /**
   * Gets the API Base URL.
   *
   * @param string $url
   */
  public static function getUrl()
  {
    return self::$baseURL;
  }
  /**
   * Sets the API Base URL.
   *
   * @param string $url
   */
  public static function setTokenUrl($tokenURL)
  {
    self::$tokenURL = $tokenURL;
  }
  /**
   * Gets the API Base token URL.
   *
   * @param string $url
   */
  public static function getTokenUrl()
  {
    return self::$tokenURL;
  }
  /**
   * Sets the API Merchant Token
   *
   * @param string $merchantToken
   */
  public static function setMerchantToken($merchantToken)
  {
    self::$merchantToken = $merchantToken;
  }
  /**
   * Gets the API Merchant Token
   *
   * @param string $merchantToken
   */
  public static function getMerchantToken()
  {
    return self::$merchantToken;
  }

  /**
   * Payeezy
   *
   * Generate Payload
   */

  public function getTokenPayload($args = array())
  {
    $args = array_merge(array(
        "type"=> "",
        "auth" => "",
        "ta_token" => "",
        "card_type" => "",
        "card_holder_name" => "",
        "card_number" => "",
        "card_exp_date" => "",
        "card_cvv" => ""

    ), $args);

    $transaction_type = strtolower(func_get_arg(1));

    $data = "";
    $data = array(
              'type'=> 'FDToken',
              'auth'=> 'false',
              'ta_token'=> 'NOIW',
              'credit_card'=> array(
                      'type'=> $args['card_type'],
                      'cardholder_name'=> $args['card_holder_name'],
                      'card_number'=> $args['card_number'],
                      'exp_date'=> $args['card_exp_date'],
                      'cvv'=> $args['card_cvv'],
                    )
               );
     self::$url = self::$tokenURL;
    
    return json_encode($data, JSON_FORCE_OBJECT);
  }

  /**
   * Payeezy
   *
   * Generate Payload
   */

  public function getPayload($args = array())
  {
    $args = array_merge(array(
      "amount"=> "",
      "card_number" => "",
      "card_type" => "",
      "card_holder_name" => "",
      "card_cvv" => "",
      "card_expiry" => "",
      "merchant_ref" => "",
      "currency_code" => "",
      "transaction_tag" => "",
      "split_shipment" => "",
      "transaction_id" => "",
      "method" => ""

    ), $args);

    $transaction_type = strtolower(func_get_arg(1));


    $data = "";
    if($transaction_type == "authorize" || $transaction_type == "purchase")
    {

      if($args['method'] == 'token')
      {

        $token_data = array_merge(array(
          'type' => '',
          'cardholder_name' => '',
          'exp_date' => '',
          'value' => '',
        ), $args['token']['token_data']);

        $data = array(
          'merchant_ref'=> $args['merchant_ref'],
          'transaction_type'=> $transaction_type,
          'method'=> $args['method'],
          'amount'=> $args['amount'],
          'currency_code'=> strtoupper($args['currency_code']),
          'token'=> array(
            'token_type'=> $args['token']['token_type'],
            'token_data'=> $token_data,
          )
        );
      }
      else
      {
        $data = array(
          'merchant_ref'=> $args['merchant_ref'],
          'transaction_type'=> $transaction_type,
          'method'=> $args['method'],
          'amount'=> $args['amount'],
          'currency_code'=> strtoupper($args['currency_code']),
          'credit_card'=> array(
            'type'=> $args['card_type'],
            'cardholder_name'=> $args['card_holder_name'],
            'card_number'=> $args['card_number'],
            'exp_date'=> $args['card_expiry'],
            'cvv'=> $args['card_cvv'],
          )
        );
      }

      self::$url = self::$baseURL;
    }else if($transaction_type == "creditGDDAVS" || $transaction_type == "purchaseGDDAVS"){
       
	    if($args['method'] == 'token')
        {
          $token_data = array_merge(array(
            'type' => '',
            'cardholder_name' => '',
            'exp_date' => '',
            'value' => '',
          ), $args['token']['token_data']);

          $data = array(
            'merchant_ref'=> $args['merchant_ref'],
            'transaction_type'=> $transaction_type,
            'method'=> $args['method'],
            'amount'=> $args['amount'],
            'currency_code'=> strtoupper($args['currency_code']),
            'token'=> array(
              'token_type'=> $args['token']['token_type'],
              'token_data'=> $token_data,
            )
          );
        }
        else
        {
          $data = array(
            'transaction_type'=> $transaction_type,
            'method'=> $args['method'],
            'amount'=> $args['amount'],
            'currency_code'=> strtoupper($args['currency_code']),
            'debit_card'=> array(
              'iban'=> $args['iban'],
              'mandate_ref'=> $args['mandate_ref'],
              'bic'=> $args['bic'],
            ),
			'billing_address'=> array(
              'name'=> $args['name'],
              'city'=> $args['city'],
              'country'=> $args['country'],
			  'email'=> $args['email'],
			  'street'=> $args['street'],
			  'state_province'=> $args['state_province'],
			  'zip_postal_code'=> $args['zip_postal_code'],
			  'phone'=> array(
				  'type'=> $args['type'],
				  'number'=> $args['number'],
			  )
			 )	
          );
        }

        self::$url = self::$baseURL;
    }else if($transaction_type == "creditGDDSoftDesc" || $transaction_type == "purchaseGDDSoftDesc"){
       
	    if($args['method'] == 'token')
        {
          $token_data = array_merge(array(
            'type' => '',
            'cardholder_name' => '',
            'exp_date' => '',
            'value' => '',
          ), $args['token']['token_data']);

          $data = array(
            'merchant_ref'=> $args['merchant_ref'],
            'transaction_type'=> $transaction_type,
            'method'=> $args['method'],
            'amount'=> $args['amount'],
            'currency_code'=> strtoupper($args['currency_code']),
            'token'=> array(
              'token_type'=> $args['token']['token_type'],
              'token_data'=> $token_data,
            )
          );
        }
        else
        {
          $data = array(
            'transaction_type'=> $transaction_type,
            'method'=> $args['method'],
            'amount'=> $args['amount'],
            'currency_code'=> strtoupper($args['currency_code']),
            'debit_card'=> array(
              'iban'=> $args['iban'],
              'mandate_ref'=> $args['mandate_ref'],
              'bic'=> $args['bic'],
            ),
			'soft_descriptors'=> array(
              'dba_name'=> $args['dba_name'],
              'street'=> $args['street'],
              'region'=> $args['region'],
			  'mid'=> $args['mid'],
			  'mcc'=> $args['mcc'],
			  'postal_code'=> $args['postal_code'],
			  'country_code'=> $args['country_code'],
			  'merchant_contact_info'=> $args['merchant_contact_info'],
		     )	
          );
        }

       self::$url = self::$baseURL;
	}else if($transaction_type == "creditGDDL2L3" || $transaction_type == "purchaseGDDL2L3"){
   
    if($args['method'] == 'token')
    {
      $token_data = array_merge(array(
        'type' => '',
        'cardholder_name' => '',
        'exp_date' => '',
        'value' => '',
      ), $args['token']['token_data']);

      $data = array(
        'merchant_ref'=> $args['merchant_ref'],
        'transaction_type'=> $transaction_type,
        'method'=> $args['method'],
        'amount'=> $args['amount'],
        'currency_code'=> strtoupper($args['currency_code']),
        'token'=> array(
          'token_type'=> $args['token']['token_type'],
          'token_data'=> $token_data,
        )
      );
    }
    else
    {
      $data = array(
        'transaction_type'=> $transaction_type,
        'method'=> $args['method'],
        'amount'=> $args['amount'],
        'currency_code'=> strtoupper($args['currency_code']),
        'debit_card'=> array(
          'iban'=> $args['iban'],
          'mandate_ref'=> $args['mandate_ref'],
          'bic'=> $args['bic'],
        ),
		'level2'=> array(
          'tax1_amount'=> $args['tax1_amount'],
          'tax2_amount'=> $args['tax2_amount'],
          'tax2_number'=> $args['tax2_number'],
		  'customer_ref'=> $args['customer_ref'],
		  ),
  		'level3'=> array(
            'alt_tax_amount'=> $args['alt_tax_amount'],
            'alt_tax_id'=> $args['alt_tax_id'],
            'discount_amount'=> $args['discount_amount'],
  		  	'duty_amount'=> $args['duty_amount'],
            'freight_amount'=> $args['freight_amount'],
            'ship_from_zip'=> $args['ship_from_zip'],
            'ship_to_address'=> $args['ship_to_address'],
  		  	'city'=> $args['city'],
            'state'=> $args['state'],
            'zip'=> $args['zip'],
            'country'=> $args['country'],
  		  	'email'=> $args['email'],
            'name'=> $args['name'],
            'phone'=> $args['phone'],
  		  	'address_1'=> $args['address_1'],
            'customer_number'=> $args['customer_number'],
  		  ),	
		'line_items'=> array(
		  'description'=> $args['description'],
		  'quantity'=> $args['quantity'],
		  'commodity_code'=> $args['commodity_code'],
		  'discount_amount'=> $args['discount_amount'],
		  'discount_indicator'=> $args['discount_indicator'],
		  'gross_net_indicator'=> $args['gross_net_indicator'],
		  'line_item_total'=> $args['line_item_total'],
		  'product_code'=> $args['product_code'],
		  'tax_amount'=> $args['tax_amount'],
		  'tax_rate'=> $args['tax_rate'],
		  'tax_type'=> $args['tax_type'],
		  'unit_cost'=> $args['unit_cost'],
		  'unit_of_measure'=> $args['unit_of_measure'],
			  
			  
		 )	
      );
    }

	 self::$url = self::$baseURL;
    }else{
	 self::$url = self::$baseURL . '/' . $args['transaction_id'];


      if($transaction_type == "split")
      {
        $data = array(
          'merchant_ref'=> $args['merchant_ref'],
          'transaction_type'=> $transaction_type,
          'method'=> 'credit_card',
          'amount'=> $args['amount'],
          'currency_code'=> strtoupper($args['currency_code']),
          'transaction_tag'=>$args['transaction_tag'],
          'split_shipment'=>$args['split_shipment'],
        );

      }else{
        $data = array(
          'merchant_ref'=> $args['merchant_ref'],
          'transaction_type'=> $transaction_type,
          'method'=> 'credit_card',
          'amount'=> $args['amount'],
          'currency_code'=> strtoupper($args['currency_code']),
          'transaction_tag'=>$args['transaction_tag'],
        );

      }
    }
    return json_encode($data, JSON_FORCE_OBJECT);
  }


  /**
   * Payeezy
   *
   * Generate Payload for Telecheck & Value Link
   */

  public function getTeleCheckValueLinkPayLoad($args = array())
  {

    $method_name = strtolower(func_get_arg(0));

    //check if telecheck or valuelink
    if ($method_name == 'tele_check')
    {
      //Common required parameters
      $args = array_merge(array(
        "billing_address" => array(
          "street" => "",
          "city" => "",
          "state_province" => "",
          "zip_postal_code" => "",
          "country" => "",
        ),
      ), $args);

      $transaction_type = strtolower(func_get_arg(1));

      $data = "";
      if ($transaction_type == 'purchase') {
        $data = array(
          "method" => $method_name,
          "transaction_type" => $transaction_type,
          "amount" => $args['amount'],
          "currency_code" => $args['currency_code'],
          "tele_check" => array(
          "check_number" => $args['check_number'],
          "check_type" => $args['check_type'],
          "routing_number" => $args['routing_number'],
          "account_number" => $args['account_number'],
          "accountholder_name" => $args['accountholder_name'],
          "customer_id_type" => $args['customer_id_type'],
          "customer_id_number" => $args['customer_id_number'],
          "client_email" => $args['client_email'],
          "gift_card_amount" => $args['gift_card_amount'],
          "vip" => $args['vip'],
          "clerk_id" => $args['clerk_id'],
          "device_id" => $args['device_id'],
          "micr" => $args['micr'],
          "release_type" => $args['release_type'],
          "registration_number" => $args['registration_number'],
          "registration_date" => $args['registration_date'],
          "date_of_birth" => $args['date_of_birth'],
          )
        );

        self::$url = self::$baseURL;

      }
      else
      {
        //Not Purchase but Void/TaggedVoid/TaggedRefund

        self::$url = self::$baseURL . '/' . $args['transaction_id'];


        if ($transaction_type == "void" || $transaction_type == 'refund' && isset($args['transaction_tag'])) {
          $data = array(
            "method" => $method_name,
            "transaction_type" => $transaction_type,
            "amount" => $args['amount'],
            "currency_code" => $args['currency_code'],
            "transaction_tag" => $args['transaction_tag'],
          );

        }
        else
        {
          $data = array(
            "method" => $method_name,
            "transaction_type" => $transaction_type,
            "amount" => $args['amount'],
            "currency_code" => $args['currency_code'],
            "tele_check" => array(
              "check_number" => $args['check_number'],
              "check_type" => $args['check_type'],
              "routing_number" => $args['routing_number'],
              "account_number" => $args['account_number'],
              "accountholder_name" => $args['accountholder_name'],
              "customer_id_type" => $args['customer_id_type'],
              "customer_id_number" => $args['customer_id_number'],
              "client_email" => $args['client_email'],
              "gift_card_amount" => $args['gift_card_amount'],
              "vip" => $args['vip'],
              "clerk_id" => $args['clerk_id'],
              "device_id" => $args['device_id'],
              "micr" => $args['micr'],
              "release_type" => $args['release_type'],
              "registration_number" => $args['registration_number'],
              "registration_date" => $args['registration_date'],
              "date_of_birth" => $args['date_of_birth'],
            )
          );
        }//end of void/tagged void/tagged refund
      }//end of purchase telecheck

    }//end of telecheck
    else
    {
      //Common required parameters
      $args = array_merge(array(
        "valuelink" => array(
          "cardholder_name" => "",
          "cc_number" => "",
          "credit_card_type" => "",
          "card_cost" => "",
        ),
      ), $args);

      $transaction_type = strtolower(func_get_arg(1));

      $data = "";
      if ($transaction_type == 'cashout' || $transaction_type == 'deactivation' || $transaction_type == 'balance_inquiry') {
        $data = array(
          "method" => $method_name,
          "transaction_type" => $transaction_type,
        );

        self::$url = self::$baseURL;

      }
      elseif($transaction_type == 'void')
      {
        self::$url = self::$baseURL . '/' . $args['transaction_id'];
        $data = array(
            "method" => $method_name,
            "transaction_type" => $transaction_type,
            "amount" => $args['amount'],
            "currency_code" => $args['currency_code'],
            "transaction_tag" => $args['transaction_tag'],
        );
      }
      else{

        $data = array(
          "transaction_type" => $transaction_type,
          "method" => $method_name,
          "amount" => $args['amount'],
          "currency_code" => $args['currency_code'],
        );

        self::$url = self::$baseURL;
      }//end of valuelink methods
    }//end of valuelink

    return json_encode($data, JSON_FORCE_OBJECT);
  }


  /**
   * Payeezy
   *
   * HMAC Authentication
   */

  public function hmacAuthorizationToken($payload)
  {

    $nonce = strval(hexdec(bin2hex(openssl_random_pseudo_bytes(4, $cstrong))));

    $timestamp = strval(time()*1000); //time stamp in milli seconds

    $data = self::$apiKey . $nonce . $timestamp . self::$merchantToken . $payload;

    $hashAlgorithm = "sha256";

    $hmac = hash_hmac ( $hashAlgorithm , $data , self::$apiSecret, false );    // HMAC Hash in hex

    $authorization = base64_encode($hmac);


    return array(
            'authorization' => $authorization,
            'nonce' => $nonce,
            'timestamp' => $timestamp,
      );
  }

  /**
   * jsonpp - Pretty print JSON data
   *
   * In versions of PHP < 5.4.x, the json_encode() function does not yet provide a
   * pretty-print option. In lieu of forgoing the feature, an additional call can
   * be made to this function, passing in JSON text, and (optionally) a string to
   * be used for indentation.
   *
   * @param string $json  The JSON data, pre-encoded
   * @param string $istr  The indentation string
   *
   * @return string
   */
  public function jsonpp($json, $istr='  ')
  {
      $result = '';
      for($p=$q=$i=0; isset($json[$p]); $p++)
      {
          $json[$p] == '"' && ($p>0?$json[$p-1]:'') != '\\' && $q=!$q;
          if(strchr('}]', $json[$p]) && !$q && $i--)
          {
              strchr('{[', $json[$p-1]) || $result .= "\n".str_repeat($istr, $i);
          }
          $result .= $json[$p];
          if(strchr(',{[', $json[$p]) && !$q)
          {
              $i += strchr('{[', $json[$p])===FALSE?0:1;
              strchr('}]', $json[$p+1]) || $result .= "\n".str_repeat($istr, $i);
          }
      }
      return $result;
  }

  /**
   * Payeezy
   *
   * Post Transaction
   */

  public  function postTransaction($payload, $headers)
  {

    $request = curl_init();
    curl_setopt($request, CURLOPT_URL, self::$url );
    curl_setopt($request, CURLOPT_POST, true);
    curl_setopt($request, CURLOPT_POSTFIELDS, $payload);
    curl_setopt($request, CURLOPT_RETURNTRANSFER, true);
    curl_setopt($request, CURLOPT_HEADER, false);
	//curl_setopt($request, CURLOPT_SSL_VERIFYPEER, false);
    curl_setopt($request, CURLOPT_HTTPHEADER, array(
      'Content-Type: application/json',
      'apikey:'.strval(self::$apiKey),
      'token:'.strval(self::$merchantToken),
      'Authorization:'.$headers['authorization'],
      'nonce:'.$headers['nonce'],
      'timestamp:'.$headers['timestamp'],
    ));

    $response = curl_exec($request);

	if (FALSE === $response)
        echo curl_error($request);

    //$httpcode = curl_getinfo($request, CURLINFO_HTTP_CODE);
    curl_close($request);

    return $response;
  }

  /**
   * Payeezy
   *
   * Authorize Transaction
   */

  public function authorize($args = array())
  {
      $payload = $this->getPayload($args, "authorize");

      $headerArray = $this->hmacAuthorizationToken($payload);

      return $this->postTransaction($payload, $headerArray);
  }

  /**
   * Payeezy
   *
   * Purchase Transaction
   */

  public function purchase($args = array())
  {
      $payload = $this->getPayload($args, "purchase");
      $headerArray = $this->hmacAuthorizationToken($payload);
      return $this->postTransaction($payload, $headerArray);
  }

  /**
   * Payeezy German Direct Debit
   *
   * Purchase Transaction
   */

  public function processPurchaseTransactionWithAVSDirectDebit($args = array())
  {
      $payload = $this->getPayload($args, "purchaseGDDAVS");
      $headerArray = $this->hmacAuthorizationToken($payload);
      return $this->postTransaction($payload, $headerArray);
  }
  
  /**
   * Payeezy German Direct Debit
   *
   * Credit Transaction
   */

  public function processCreditTransactionWithAVSDirectDebit($args = array())
  {
      $payload = $this->getPayload($args, "creditGDDAVS");
      $headerArray = $this->hmacAuthorizationToken($payload);
      return $this->postTransaction($payload, $headerArray);
  }
  
  /**
   * Payeezy German Direct Debit
   *
   * Purchase Transaction
   */

  public function processPurchaseTransactionWithSoftDescDirectDebit($args = array())
  {
      $payload = $this->getPayload($args, "purchaseGDDSoftDesc");
      $headerArray = $this->hmacAuthorizationToken($payload);
      return $this->postTransaction($payload, $headerArray);
  }
  
  /**
   * Payeezy German Direct Debit
   *
   * Credit Transaction
   */

  public function processCreditTransactionWithSoftDescDirectDebit($args = array())
  {
      $payload = $this->getPayload($args, "creditGDDSoftDesc");
      $headerArray = $this->hmacAuthorizationToken($payload);
      return $this->postTransaction($payload, $headerArray);
  }
  
  /**
   * Payeezy German Direct Debit
   *
   * Purchase Transaction
   */

  public function processPurchaseTransactionWithL2L3DirectDebit($args = array())
  {
      $payload = $this->getPayload($args, "purchaseGDDL2L3");
      $headerArray = $this->hmacAuthorizationToken($payload);
      return $this->postTransaction($payload, $headerArray);
  }
  
  /**
   * Payeezy German Direct Debit
   *
   * Credit Transaction
   */

  public function processCreditTransactionWithL2L3DirectDebit($args = array())
  {
      $payload = $this->getPayload($args, "creditGDDL2L3");
      $headerArray = $this->hmacAuthorizationToken($payload);
      return $this->postTransaction($payload, $headerArray);
  }
  
  /**
   * Payeezy
   *
   * Capture Transaction
   */

  public function capture($args = array())
  {
      $payload = $this->getPayload($args, "capture");
      $headerArray = $this->hmacAuthorizationToken($payload);
      return $this->postTransaction($payload, $headerArray);
  }

  /**
   * Payeezy
   *
   * Void Transaction
   */

  public function void($args = array())
  {
      $payload = $this->getPayload($args, "void");
      $headerArray = $this->hmacAuthorizationToken($payload);
      return $this->postTransaction($payload, $headerArray);
  }

  /**
   * Payeezy
   *
   * Refund Transaction
   */

  public function refund($args = array())
  {
      $payload = $this->getPayload($args, "refund");
      $headerArray = $this->hmacAuthorizationToken($payload);
      return $this->postTransaction($payload, $headerArray);
  }

  /**
   * Payeezy
   *
   * split Transaction
   */

  public function split_shipment($args = array())
  {
      $payload = $this->getPayload($args, "split");
      $headerArray = $this->hmacAuthorizationToken($payload);
      return $this->postTransaction($payload, $headerArray);
  }

  /**
   * Payeezy Telecheck
   *
   * Purchase Transaction
   */

  public function telecheck_purchase($args = array())
  {
    $payload = $this->getTeleCheckValueLinkPayLoad($args, 'purchase');
    $headerArray = $this->hmacAuthorizationToken($payload);
    return $this->postTransaction($payload, $headerArray);
  }

  /**
   * Payeezy Telecheck
   *
   * Void Transaction
   */

  public function telecheck_void($args = array())
  {
    $payload = $this->getTeleCheckValueLinkPayLoad($args, 'void');
    $headerArray = $this->hmacAuthorizationToken($payload);
    return $this->postTransaction($payload, $headerArray);
  }

  /**
   * Payeezy Telecheck
   *
   * Tagged Void Transaction
   */

  public function telecheck_tagged_void($args = array())
  {
    $payload = $this->getTeleCheckValueLinkPayLoad($args, 'void');
    $headerArray = $this->hmacAuthorizationToken($payload);
    return $this->postTransaction($payload, $headerArray);
  }

  /**
   * Payeezy Telecheck
   *
   * Tagged Refund Transaction
   */

  public function telecheck_tagged_refund($args = array())
  {
    $payload = $this->getTeleCheckValueLinkPayLoad($args, 'refund');
    $headerArray = $this->hmacAuthorizationToken($payload);
    return $this->postTransaction($payload, $headerArray);
  }


  /**
   * Payeezy Value Check
   *
   * Purchase Transaction
   */

  public function valuelink_purchase($args = array())
  {
    $payload = $this->getTeleCheckValueLinkPayLoad($args, 'purchase');
    $headerArray = $this->hmacAuthorizationToken($payload);
    return $this->postTransaction($payload, $headerArray);
  }

  /**
   * Payeezy Value Check
   *
   * Refund Transaction
   */

  public function valuelink_refund($args = array())
  {
    $payload = $this->getTeleCheckValueLinkPayLoad($args, 'refund');
    $headerArray = $this->hmacAuthorizationToken($payload);
    return $this->postTransaction($payload, $headerArray);
  }

  /**
   * Payeezy Value Check
   *
   * Void Transaction
   */

  public function valuelink_void($args = array())
  {
    $payload = $this->getTeleCheckValueLinkPayLoad($args, 'void');
    $headerArray = $this->hmacAuthorizationToken($payload);
    return $this->postTransaction($payload, $headerArray);
  }

  /**
   * Payeezy Value Check
   *
   * Cashout Transaction
   */

  public function valuelink_cashout($args = array())
  {
    $payload = $this->getTeleCheckValueLinkPayLoad($args, 'cashout');
    $headerArray = $this->hmacAuthorizationToken($payload);
    return $this->postTransaction($payload, $headerArray);
  }

  /**
   * Payeezy Value check
   *
   * Reload Transaction
   */

  public function valuelink_reload($args = array())
  {
    $payload = $this->getTeleCheckValueLinkPayLoad($args, 'reload');
    $headerArray = $this->hmacAuthorizationToken($payload);
    return $this->postTransaction($payload, $headerArray);
  }

  /**
   * Payeezy Value check
   *
   * Partial Purchase Transaction
   */

  public function valuelink_partial_purchase($args = array())
  {
    $payload = $this->getTeleCheckValueLinkPayLoad($args, 'partial_purchase');
    $headerArray = $this->hmacAuthorizationToken($payload);
    return $this->postTransaction($payload, $headerArray);
  }

  /**
   * Payeezy Value check
   *
   * Activation Transaction
   */

  public function valuelink_activation($args = array())
  {
    $payload = $this->getTeleCheckValueLinkPayLoad($args, 'activation');
    $headerArray = $this->hmacAuthorizationToken($payload);
    return $this->postTransaction($payload, $headerArray);
  }

  /**
   * Payeezy Value check
   *
   * Deactivation Transaction
   */

  public function valuelink_deactivation($args = array())
  {
    $payload = $this->getTeleCheckValueLinkPayLoad($args, 'deactivation');
    $headerArray = $this->hmacAuthorizationToken($payload);
    return $this->postTransaction($payload, $headerArray);
  }

  /**
   * Payeezy Value check
   *
   * Balance Inquiry Transaction
   */

  public function valuelink_balance_inquiry($args = array())
  {
    $payload = $this->getTeleCheckValueLinkPayLoad($args, 'balance_inquiry');
    $headerArray = $this->hmacAuthorizationToken($payload);
    return $this->postTransaction($payload, $headerArray);
  }

}//end of class
