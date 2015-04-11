import payeezy 
import json

# test credentials
payeezy.apikey = "y6pWAJNyJyjGv66IsVuWnklkKUPFbb0a"
payeezy.apisecret = "86fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f7"
payeezy.token = "fdoa-a480ce8951daa73262734cf102641994c1e55e7cdf4c02b6"

# environment - sandbox
payeezy.url = "https://api-cert.payeezy.com/v1/transactions"

# environment - live
# payeezy.url = "https://api.payeezy.com/v1/transactions"




responseAuthorize =  payeezy.transactions.authorize(amount=100,
	                                                 currency_code='usd',
	                                                 card_type='visa',
	                                                 cardholder_name='test_name',
	                                                 card_number=4788250000028291,
	                                                 card_expiry=1030,
	                                                 card_cvv=123,
	                                                 description='This is a test authorize transaction'
                                                    )

print(responseAuthorize);

transactionTag = responseAuthorize.json()['transaction_tag']

transactionID = responseAuthorize.json()['transaction_id']

responseCapture =  payeezy.transactions.capture(amount=100,
												currency_code='usd',
	                                            transactionTag=transactionTag,
	                                            transactionID=transactionID,
	                                            description='This is a test authorize transaction'
	                                            )


print "************---------- Authorize Response ---------------*****************" +'\n'
print json.dumps(responseAuthorize.json(), indent=3) 

print "************---------- Capture Response ---------------*****************" +'\n'
print json.dumps(responseCapture.json(), indent=3) 






