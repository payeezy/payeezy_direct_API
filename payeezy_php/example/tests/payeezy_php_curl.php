<?php

$serviceURL = 'https://api-cert.payeezy.com/v1/transactions';
$apiKey = "bfvCs2v0BlGr2GlKn55b5iDeRDcX0AMs";
$apiSecret = "4012faf411afc618b77f0703ad1c37be8eff8d8337e8e7571364d35c49edf003";
$token = "fdoa-a480ce8951daa73262734cf102641994c1e55e7cdf4c02b6";



$nonce = strval(hexdec(bin2hex(openssl_random_pseudo_bytes(4, $cstrong))));
$timestamp = strval(time()*1000); //time stamp in milli seconds


$payload = getPayload(setPrimaryTxPayload());

function processInput($data) {
        $data = trim($data);
        $data = stripslashes($data);
        $data = htmlspecialchars($data);
        return strval($data);
  }

  function setPrimaryTxPayload(){

        $card_holder_name = $card_number = $card_type = $card_cvv = $card_expiry = $currency_code = $merchant_ref="";

        $card_holder_name = processInput("John Smith");
        $card_number = processInput("4788250000028291");
        $card_type = processInput("visa");
        $card_cvv = processInput("123");
        $card_expiry = processInput("1218");
        $amount = processInput("1200");
        $currency_code = processInput("USD");
        $merchant_ref = processInput("Astonishing-Sale");

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


/**
   * Payeezy
   *
   * Generate Payload
   */

   function getPayload($args = array())
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

    ), $args);

    $data = "";
    
    $data = array(
              'merchant_ref'=> $args['merchant_ref'],
              'transaction_type'=> "authorize",
              'method'=> 'credit_card',
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
   
    return json_encode($data, JSON_FORCE_OBJECT);
  }

echo "<br><br> Request JSON Payload :" ;

echo $payload ;

echo "<br><br> Authorization :" ;

$data = $apiKey . $nonce . $timestamp . $token . $payload;

$hashAlgorithm = "sha256";

### Make sure the HMAC hash is in hex -->
$hmac = hash_hmac ( $hashAlgorithm , $data , $apiSecret, false );

### Authorization : base64 of hmac hash -->
$hmac_enc = base64_encode($hmac);

echo "<br><br> " ;

echo $hmac_enc;

echo "<br><br>" ;

$curl = curl_init('https://api-cert.payeezy.com/v1/transactions');

$headers = array(
      'Content-Type: application/json',
      'apikey:'.strval($apiKey),
      'token:'.strval($token),
      'Authorization:'.$hmac_enc,
      'nonce:'.$nonce,
      'timestamp:'.$timestamp,
    );



curl_setopt($curl, CURLOPT_HEADER, false);
curl_setopt($curl, CURLOPT_POST, true);
curl_setopt($curl, CURLOPT_RETURNTRANSFER, true);
curl_setopt($curl, CURLOPT_POSTFIELDS, $payload);

curl_setopt($curl, CURLOPT_VERBOSE, true);
curl_setopt($curl, CURLOPT_HTTPHEADER, $headers);
curl_setopt($curl, CURLOPT_SSL_VERIFYPEER, false);

$json_response = curl_exec($curl);



$status = curl_getinfo($curl, CURLINFO_HTTP_CODE);

$response = json_decode($json_response, true);

echo "<br><br> " ;

if ( $status != 201 ) {
die("Error: call to URL $serviceURL failed with status $status, response $json_response, curl_error " . curl_error($curl) . ", curl_errno " . curl_errno($curl));
}

curl_close($curl);

echo "Response is: ".$response."\n";

echo "JSON response is: ".$json_response."\n";



?>
