
<?php
require_once('./Payeezy.php');

$payeezy = new Payeezy();
//jae's developer account
// $payeezy->setApiKey("EHMenTH0KF1aN7KQpjGDsj7V9r5fxaaB");
// $payeezy->setApiSecret("243a48b691a3b8f62b4f496e67ae2a58c333729aa58dafa074f211f3eaefe4b5");
// $payeezy->setMerchantToken("fdoa-a480ce8951daa73262734cf102641994c1e55e7cdf4c02b6");
 
//test account provided in sample code
$payeezy->setApiKey("y6pWAJNyJyjGv66IsVuWnklkKUPFbb0a");
$payeezy->setApiSecret("86fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f7");
$payeezy->setMerchantToken("fdoa-a480ce8951daa73262734cf102641994c1e55e7cdf4c02b6");
 
 
$payeezy->setTokenUrl("https://api-cert.payeezy.com/v1/transactions/tokens");  
$payeezy->setUrl("https://api-cert.payeezy.com/v1/transactions");
 
 
 
echo "-------------------- Testing authorize 1----------"; 
$response=$payeezy->authorize(setTokenPayload());
echo $response;

echo "<br>";
echo "<br>";

echo "-------------------- Testing authorize 2 ---------- </br> "; 
$response=$payeezy->authorize(setPrimaryTxPayload());
echo $response;
 
function setTokenPayload(){
 
    $card_holder_name = $amount = $transaction_type = $auth = $card_number = $ta_token = $card_type = $card_cvv = $card_expiry = $currency_code = $merchant_ref="";
   
    $transaction_type = processInput("FDToken");
    $auth = processInput("false");
    $ta_token = processInput("NOIW");
 
    $card_holder_name = processInput("PHP Client");
    $card_number = processInput("4788250000028291");
    $card_type = processInput("visa");
    $card_cvv = processInput("123");
    $card_expiry = processInput("1230");
    $currency_code = processInput("USD");
    $amount = processInput("1000");
    $merchant_ref = processInput("Astonishing-Sale");
    
 
    $getTokenPayload = array(
 
        "type"=> $transaction_type,
        "auth" => $auth,
        "ta_token" => $ta_token,
        "card_type" => $card_type,
        "card_holder_name" => $card_holder_name,
        "card_number" => $card_number,
        "card_expiry" => $card_expiry,
        "card_cvv" => $card_cvv,
        "amount"=> $amount,
        "currency_code"=> $currency_code,
    );
 
    return $getTokenPayload;
 
}
 
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
 
?>
