
<!DOCTYPE html>
<html>
<head>
<LINK href="payeezyCSS.css" rel="stylesheet" type="text/css">
</head>
<body>
<?php
require_once('./Payeezy.php');

$payeezy = new Payeezy();

/*

https://developer.payeezy.com/tokenizedtreditcardpost/apis/post/transactions/tokens-1

POST - Tokenize Credit card

Payload details : 

{
"type": "FDToken",
"credit_card": {
    "type": "VISA",
    "cardholder_name": "JohnSmith",
    "card_number": "4788250000028291",
    "exp_date": "1030",
    "cvv": "123"
},
"auth": "false",   
"ta_token": "NOIW"
}

checkout "Header Parameters" on developer.payeezy.com

For more details : https://github.com/payeezy/payeezy_js/raw/master/guide/Payeezy_TokenBased_Transactions.pdf
// "auth": "true" will  authorize card for $0 and return token 

*/

 
function setTokenPayload(){
 
    $card_holder_name = $amount = $transaction_type = $auth = $card_number = $ta_token = $card_type = $card_cvv = $card_expiry = $currency_code = $merchant_ref="";
   
    $transaction_type = processInput("FDToken");
    $auth = processInput("false");
    $ta_token = processInput("NOIW");
 
    $card_holder_name = processInput("PHP Client");
    $card_number = processInput("4788250000028291");
    $card_type = processInput("visa");
    $card_cvv = processInput("123");
    $card_expiry = processInput("1250");

    $getTokenPayload = array(
        
        // trancation details 
        "type"=> $transaction_type,
        "auth" => $auth, 
        "ta_token" => $ta_token,
        
        // card details 
        "card_holder_name" => $card_holder_name,
        "card_number" => $card_number,
        "card_type" => $card_type,
        "card_exp_date" => $card_expiry,
        "card_cvv" => $card_cvv,
       
    );
 
    return $getTokenPayload;
 
}
 
function processInput($data) {
        $data = trim($data);
        $data = stripslashes($data);
        $data = htmlspecialchars($data);
        return strval($data);
}


/*
https://developer.payeezy.com/creditcardpayment/apis/post/transactions

{
  "merchant_ref": "Astonishing-Sale",
  "transaction_type": "authorize",   // note the payload  -> transaction_type = authorize
  "method": "credit_card",
  "amount": "1299",
  "currency_code": "USD",
  "credit_card": {
    "type": "visa",
    "cardholder_name": "John Smith",
    "card_number": "4788250000028291",
    "exp_date": "1020",
    "cvv": "123"
  }
}

checkout "Header Parameters" on developer.payeezy.com

note that transaction_type is set in payeezy.php file 
*/



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



function setSecondaryTxPayload($transaction_id, $transaction_tag, $amount){

        $transaction_type = $merchant_ref= $currency_code = "";
       
        $transaction_type = processInput("void");
        $transaction_id = processInput($transaction_id);
        $transaction_tag = processInput($transaction_tag);
        $amount = processInput($amount);
        $currency_code = processInput("USD");
        $merchant_ref = processInput("Astonishing-Sale");
        
        $card_type = processInput("visa");
        $card_expiry = processInput("1250");
        $card_number = processInput("4788250000028291");

       
        $secondaryTxPayload = array(
                "amount"=> $amount,
                "transaction_tag" => $transaction_tag,
                "transaction_id" => $transaction_id,
                "merchant_ref" => $merchant_ref,
                "currency_code" => $currency_code,
              
                "card_expiry" => $card_expiry,
                "card_type" => $card_type,
                "card_number" => $card_number,
                "transaction_type" => $transaction_type,
         );
      


        return $secondaryTxPayload;

}



 
//test account provided in sample code
$payeezy->setApiKey("y6pWAJNyJyjGv66IsVuWnklkKUPFbb0a");
$payeezy->setApiSecret("86fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f7");
$payeezy->setMerchantToken("fdoa-a480ce8951daa73262734cf102641994c1e55e7cdf4c02b6");
 
 
$payeezy->setTokenUrl("https://api-cert.payeezy.com/v1/transactions/tokens");  
$payeezy->setUrl("https://api-cert.payeezy.com/v1/transactions");
 
echo "<h1> GetToken Transactions </h1>";
echo "<div class='datagrid3'>";
echo "<table><thead><tr><th>Testing 'Tokenize Credit card - POST' </th></tr></thead>";
echo "<tbody><tr><td>Request for token:";

$tokenResponse=$payeezy->token(setTokenPayload());

echo "</td></tr>";
echo "<tr class='alt'><td>Response :";

echo  $tokenResponse;

echo "</td></tr> </tbody></table>"; 
echo "</div>";
echo "<br><br> " ;

echo "<h1> Authorize-Capture Transactions </h1>";
echo "<div class='datagrid1'>";
echo "<table><thead><tr><th>authorize  credit card: ' </th></tr></thead>";
echo "<tbody><tr><td>Request for authorize: ";


$authresponse=$payeezy->authorize(setPrimaryTxPayload());

echo "</td></tr>";
echo "<tr class='alt'><td>authorize Response :";

echo  $authresponse;

echo "</td></tr></tbody> </table>"; 
echo "</div>";
echo "<br><br> " ;

echo "<div class='datagrid1'>";
echo "<table><thead><tr><th> transaction_id and transaction_tag from previous transaction</th></tr></thead><tbody><tr><td> ";

$authr = json_decode($authresponse);
echo "transaction_id: " . $authr->transaction_id;
echo "<br>" ;
echo "transaction_tag: " . $authr->transaction_tag;

echo "</td></tr></tbody> </table>"; 
echo "</div>";
echo "<br><br> " ;


echo "<div class='datagrid1'>";
echo "<table><thead><tr><th>authorize-capture Credit card </th></thead></tr>";
echo "<tbody><tr><td>capture Request: ";


$secondaryTxResponse_JSON=$payeezy->capture(setSecondaryTxPayload($authr->transaction_id ,$authr->transaction_tag
                                                            ,$authr->amount ));
echo "</td></tr>";
echo "<tr class='alt'><td>authorize-capture Response :";

echo $secondaryTxResponse_JSON;


echo "</td></tr><tbody></table>"; 
echo "</div>";
echo "<br><br> " ;


echo "<h1> Purchase-void Transactions </h1>";

echo "<div class='datagrid2'>";
echo "<table><thead><tr><th>Purchase credit card transaction ' </th></tr></thead>";
echo "<tbody><tr><td>Request for Purchase: ";


$purchaseresponse=$payeezy->purchase(setPrimaryTxPayload());

echo "</td></tr>";
echo "<tr class='alt'><td>Purchase Response :";

echo  $purchaseresponse;

echo "</td></tr></tbody> </table>"; 
echo "</div>";
echo "<br><br> " ;

echo "<div class='datagrid2'>";
echo "<table><thead><tr><th> transaction_id and transaction_tag from previous transaction</th></tr></thead><tbody><tr><td> ";

$purchasetr = json_decode($purchaseresponse);
echo "transaction_id: " . $purchasetr->transaction_id;
echo "<br>" ;
echo "transaction_tag: " . $purchasetr->transaction_tag;

echo "</td></tr></tbody> </table>"; 
echo "</div>";
echo "<br><br> " ;

echo "<div class='datagrid2'>";
echo "<table><thead><tr><th>Purchase-void credit card transaction </th></thead></tr>";
echo "<tbody><tr><td>capture Request: ";


$purchaseSecondaryTxResponse_JSON=$payeezy->void(setSecondaryTxPayload($purchasetr->transaction_id ,$purchasetr->transaction_tag
                                                            ,$purchasetr->amount ));
echo "</td></tr>";
echo "<tr class='alt'><td>Purchase-void Response :";

echo $purchaseSecondaryTxResponse_JSON;


echo "</td></tr><tbody></table>"; 
echo "</div>";
echo "<br><br> " ;


 
?>
