# Payeezy Curl Library

Quickly integrate Payeezy from First Data into your Curl calls.

If you want to enable secure and convenient payments in your Curl app this guide will get you up and running within minutes. The Payeezy Service was created to simplify your integration with Android. 


http://www.oracle.com/webfolder/technetwork/tutorials/obe/cloud/13_2/messagingservice/files/installing_curl_command_line_tool_on_windows.html
*	You should have Curl installed with option to execute Curl using open SSL.

For quick start on installing and setting up Curl refer to the link http://www.oracle.com/webfolder/technetwork/tutorials/obe/cloud/13_2/messagingservice/files/installing_curl_command_line_tool_on_windows.html

# Payeezy Prerequisites

Please refer to prerequisites document for 
*	Developer registration
*	API Key, secret and merchant token generation for sandbox/test region
*	Download SDK from GitHub

For more information on step by step prerequisites, refer to our Getting Started Guide.

# Payeezy Curl
*	Unzip Payeezy_curl.zip file to a local folder
*	curl_payeezy.bat file is the main file that calls all the payment methods.
*	This batch file calls primary and secondary transactions for Credit Card, eCheck or TeleCheck and GiftCard or ValueLink
*	The secondary transactions (url with {transaction_id}) parameter needs transaction_id and transaction_tag values from primary transaction.

# TokenBased Transactions (NEW) - 
*	Generate Token with ta_token - auth false - GET API
*	Generate Token with ta_token - auth true - GET API
*	Generate Token without  ta_token & auth -  - GET API with 0$ Auth
*	Generate Token - Backward compatible -  GET API call

# Getting Started with Payeezy
Using below listed steps, you can easily integrate your mobile/web payment application with Payeezy APIs and go LIVE!
*	LITE  - REGISTRATION  
*	GET CERTIFIED
*	ADD MERCHANTS 
*	LIVE!

![alt tag](https://github.com/payeezy/payeezy_js/raw/master/ignore/get_start_with_payeezy.png)

For more information on getting started, visit  [get_start_with_payeezy guide] (https://github.com/payeezy/get_started_with_payeezy/raw/master/get_started_with_payeezy042015.pdf)

# Testing - Payeezy {SANDBOX region}
For test credit card,eCheck,GiftCard please refer to [test data](https://github.com/payeezy/testing_payeezy/raw/master/payeezy_testdata042015.pdf)

# Handling Errors - Payeezy {SANDBOX/PROD region}
For HTTP status code, API Error codes and error description please refer to [API error code ](https://developer-qa.payeezy.com/integration) and select your API


#Questions?
We're always happy to help with code or other questions you might have! Check out [form page](https://developer.payeezy.com/forum) or call us. 


## Contributing
1. Fork it 
2. Create your feature branch (`git checkout -b my-new-feature`)
3. Commit your changes (`git commit -am 'Add some feature'`)
4. Push to the branch (`git push origin my-new-feature`)
5. Create a new Pull Request  

## Feedback
Payeezy Curl Library is in active development. We appreciate the time you take to try it out and welcome your feedback!
Here are a few ways to get in touch:
* [GitHub Issues](https://github.com/payeezy/payeezy/issues) - For generally applicable issues and feedback
* support@payeezy.com - for personal support at any phase of integration
* [1.855.799.0790](tel:+18557990790)  - for personal support in real time 

## Terms of Use
Terms and conditions for using Payeezy IOS SDK: Please see [Payeezy Terms & conditions](https://developer.payeezy.com/terms-use)
 
### License
The Payeezy IOS SDK is open source and available under the MIT license. See the LICENSE file for more info.
