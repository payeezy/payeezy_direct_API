Getting started with Payeezy - https://github.com/payeezy/get_started_with_payeezy/blob/master/README.md.

Payeezy Node.js SDK is built to make it easy to integrate with the Payeezy API (https://developer.payeezy.com) for processing payements with various payment methods. Download the SDK, follow instructions to start testing against the sandbox environment with developer credentials.

Steps to download and run the Node.js code:

1. npm install payeezy
2. npm install
3. npm test

Primary Transactions
-------------------------

1) Authorize
2) Purchase
3) Authorize_score
4) Score_only (Applicable to all payment methods)
5) Purchase_Score (Valid only for Value link)

Secondary Transactions
-------------------------
Secondary transactions like void, refund can be performed once a primary transaction is completed in case if you need to reverse a transaction.

Tokenize Credit Cards
-------------------------
Credit Cards can be tokenized using our secure tokens api.

TokenBased Transactions -
-------------------------
Generate Token with ta_token - auth false - GET API                                                                                        
Generate Token with ta_token - auth true - GET API

## Contributing

1. Fork it 
2. Create your feature branch (`git checkout -b my-new-feature`)
3. Commit your changes (`git commit -am 'Add some feature'`)
4. Push to the branch (`git push origin my-new-feature`)
5. Create a new Pull Request  

Tokenize Credit Cards
-------------------------
Credit Cards can be tokenized using our secure tokens api.

TokenBased Transactions -
-------------------------
Generate Token with ta_token - auth false - GET API                                                                                    
Generate Token with ta_token - auth true - GET API

## Feedback
We appreciate the time you take to try out our sample code and welcome your feedback. Here are a few ways to get in touch:
* For generally applicable issues and feedback, create an issue in this repository.
* support@payeezy.com - for personal support at any phase of integration
* [1.855.799.0790](tel:+18557990790)  - for personal support in real time 

## Terms of Use
Terms and conditions for using Payeezy Direct API SDK: Please see [Payeezy Terms & conditions](https://developer.payeezy.com/terms-use)
 
### License
The Payeezy Direct API SDK is open source and available under the MIT license. See the LICENSE file for more info.
