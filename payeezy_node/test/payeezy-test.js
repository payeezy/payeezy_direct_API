// Sandbox Credentials below
var apikey = 'y6pWAJNyJyjGv66IsVuWnklkKUPFbb0a';
var apisecret = '86fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f7';
var merchant_token = 'fdoa-a480ce8951daa73262734cf102641994c1e55e7cdf4c02b6';

var payeezy = require('../lib/payeezy')(apikey, apisecret, merchant_token);
payeezy.version = "v1";


// Sandbox Environment - Replace this value for Live Environment "api.payeezy.com"
payeezy.host = "api-cert.payeezy.com";


// This will first execute an Auth followed by a Capture transaction .. followed by
// Auth followed by Void .. followed by
// Purchase followed by a Refund transaction
runExamples();


// Below are the helper methods that will perform Authorize, Purchase, Capture, Void & Refund.

function runExamples() {
    performAuthorizeTransaction('capture');
}

function performAuthorizeTransaction(secondaryTransactionType) {
    console.log('*******************************************\nPerforming Authorize Transaction\n************************************')
    payeezy.transaction.authorize({
            method: 'credit_card',
            amount: '1299',
            currency_code: 'USD',
            credit_card: {
                card_number: '4788250000028291',
                cvv: '123',
                type: 'visa',
                exp_date: '1230',
                cardholder_name: 'Tom Eck'
            },
            billing_address: {
                street: '225 Liberty Street',
                city: 'NYC',
                state_province: 'NY',
                zip_postal_code: '10281',
                country: 'US'
            }
        },
        function(error, response) {
            if (error) {
                console.log('Authorize Transaction Failed\n' + error);
            }
            if (response) {
                console.log('Authorize Successful.\nTransaction Tag: ' + response.transaction_tag);
                performSecondaryTransaction(secondaryTransactionType, response.transaction_id, response.transaction_tag, response.amount);
            }
        });

}



function performPurchaseTransaction(secondaryTransactionType) {
    console.log('*******************************************\nPerforming Purchase Transaction\n************************************')

    payeezy.transaction.purchase({
            method: 'credit_card',
            amount: '100',
            currency_code: 'USD',
            credit_card: {
                card_number: '4788250000028291',
                cvv: '123',
                type: 'visa',
                exp_date: '1230',
                cardholder_name: 'Tom Eck'
            },
            billing_address: {
                street: '225 Liberty Street',
                city: 'NYC',
                state_province: 'NY',
                zip_postal_code: '10281',
                country: 'US'
            }
        },
        function(error, response) {
            if (error) {
                console.log('Purchase Transaction Failed\n' + error);
            }
            if (response) {
                console.log('Purchase Successful.\nTransaction Tag: ' + response.transaction_tag);
                performSecondaryTransaction(secondaryTransactionType, response.transaction_id, response.transaction_tag, response.amount);
            }
        });
}

function performSecondaryTransaction(secondaryTransactionType, id, tag, amount) {

    if (secondaryTransactionType == 'capture') {
        console.log('*******************************************\nPerforming Capture Transaction\n************************************')

        payeezy.transaction.capture(id, {
                method: 'credit_card',
                amount: amount,
                currency_code: 'USD',
                transaction_tag: tag,
            },

            function(error, response) {
                if (error) {
                    console.log('Capture Transaction Failed\n' + error);
                }
                if (response) {
                    console.log('Capture Successful');
                    performAuthorizeTransaction('void');
                }
            });

    } else if (secondaryTransactionType == 'refund') {
        console.log('*******************************************\nPerforming Refund Transaction\n************************************')

        payeezy.transaction.refund(id, {
                method: 'credit_card',
                amount: amount,
                currency_code: 'USD',
                credit_card: {
                    card_number: '4788250000028291',
                    cvv: '123',
                    type: 'visa',
                    exp_date: '1230',
                    cardholder_name: 'Tom Eck'
                },
            },

            function(error, response) {
                if (error) {
                    console.log('Refund Transaction Failed\n' + error);
                }
                if (response) {
                    console.log('Refund Successful');
                    generateToken();
                }
            });
    } else if (secondaryTransactionType == 'void') {
        console.log('*******************************************\nPerforming Void Transaction\n************************************')

        payeezy.transaction.void(id, {
                method: 'credit_card',
                amount: amount,
                currency_code: 'USD',
                transaction_tag: tag,
            },

            function(error, response) {
                if (error) {
                    console.log('Void Transaction Failed\n' + error);
                }
                if (response) {
                    console.log('Void Successful');
                    performPurchaseTransaction('refund');
                }
            });
    }

}

function generateToken() {
    console.log('*******************************************\nCreating FD-Token for a Credit Card\n************************************')

    payeezy.tokens.getToken({
            type: "FDToken",
            credit_card: {
                type: "VISA",
                cardholder_name: "Tom Eck",
                card_number: "4788250000028291",
                exp_date: "1030",
                cvv: "123"
            },
            auth: "false",
            ta_token: "NOIW"
        },
        function(error, response) {
            if (error) {
                console.log('Get Token for Card Failed\n' + error);
            }
            if (response) {
                console.log('FD-Token is generated Successfully, Token Value: ' + JSON.stringify(response.token, null, 4));
                tokenBasedAuthorizeTransaction();
            }
        });
}

function tokenBasedAuthorizeTransaction() {

    console.log('*******************************************\n Authorize using FD - Token \n************************************')

    payeezy.transaction.tokenAuthorize({
        merchant_ref: "Simple FD Token Authorize",
        method: "token",
        amount: "200",
        currency_code: "USD",
        token: {
            token_type: "FDToken",
            token_data: {
                type: "visa",
                value: "2537446225198291",
                cardholder_name: "Tom Eck",
                exp_date: "1030"
            }
        }
    }, function(error, response) {
        if (error) {
            console.log('Authorize Token Failed\n' + error);
        }
        if (response) {
            console.log('Authorize Token -  Success.\nTransaction Tag: ' + response.transaction_tag);
        }
    });
}