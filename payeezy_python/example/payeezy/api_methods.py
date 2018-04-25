#####################################################################################################################################################
#		File Name   	:  Security.py																												#
#		Methods	Type	:  Transactions - authorize, payment, capture, void, return | Merchants - boarding											#
#####################################################################################################################################################
import os, sys 
import payeezy        # Standard system functions
import http_authorization
import json

class Payeezy(object):

	def __init__(self):
		return None
	
	def authorize(self, amount=None, currency_code=None, card_type=None, cardholder_name=None, card_number=None, card_expiry=None, card_cvv=None, description=None):
		
		makePayload_output = self.makePayload(amount=amount, 
											  currency_code=currency_code, 
											  card_type=card_type, 
											  cardholder_name=cardholder_name, 
											  card_number=card_number, 
											  card_expiry=card_expiry, 
											  card_cvv=card_cvv, 
											  description=description, 
											  transactionType='authorize')

		return self.makePrimaryTransaction( payload=makePayload_output['payload'])

	def purchase(self, amount=None, currency_code=None, card_type=None, cardholder_name=None, card_number=None, card_expiry=None, card_cvv=None, description=None):
		
		makePayload_output = self.makePayload(amount=amount,
											  currency_code=currency_code,
											  card_type=card_type,
											  cardholder_name=cardholder_name,
											  card_number=card_number,
											  card_expiry=card_expiry,
											  card_cvv=card_cvv,
											  description=description,
											  transactionType='purchase')

		return self.makePrimaryTransaction( payload=makePayload_output['payload'])



	def capture(self, amount=None, currency_code=None, transactionTag=None, transactionID=None, description=None):
		
		makePayload_output = self.makePayload(amount=amount,
											  currency_code=currency_code,
											  transactionTag=transactionTag,
											  transactionID=transactionID,
											  description=description,
											  transactionType='capture')

		return self.makeSecondaryTransaction(payload=makePayload_output['payload'], transactionID=makePayload_output['transactionID'])

	def void(self,  payload ):
		self.payload = payload
		self.transactionType = "void"
		return self.makeSecondaryTransaction(self.transactionType, self.payload);

	def refund(self,  payload):
		self.payload = payload
		self.transactionType = "refund"
		return self.makeSecondaryTransaction(self.transactionType, self.payload);


	def makePrimaryTransaction(self, payload):
		self.payload = payload
		self.payeezy = http_authorization.PayeezyHTTPAuthorize(payeezy.apikey,payeezy.apisecret,payeezy.token,payeezy.url,payeezy.tokenurl)
		return self.payeezy.makeCardBasedTransactionPostCall(self.payload)

	def makeSecondaryTransaction(self, payload,transactionID):
		self.payload = payload
		self.transactionID = transactionID
		self.payeezy = http_authorization.PayeezyHTTPAuthorize(payeezy.apikey,payeezy.apisecret,payeezy.token,payeezy.url,payeezy.tokenurl)
		return self.payeezy.makeCaptureVoidRefundPostCall(self.payload,self.transactionID)

	def makePayload(self, amount=None, currency_code=None, card_type=None, cardholder_name=None, card_number=None, card_expiry=None, card_cvv=None, description=None, transactionType=None, transactionTag=None, transactionID=None):

		if amount is None:
			raise ValueError, 'Amount cannot be nil'

		if type(amount) is int:
			amount = str(amount)

		if currency_code is None:
			raise ValueError, 'Currency code cannot be nil'

		if transactionType is None:
			raise ValueError, 'Internal Script Error - Transaction Type is NIL'

		if currency_code.upper() != 'USD':
			raise ValueError, 'currency code provided is not valid'

		if description is None:
			description = transactionType+'transaction for amount: '+amount

		if transactionType in ['authorize', 'purchase']: 

			if card_number is None:
				raise ValueError, 'card number cannot be nil'

			if type(card_number) is int:
				card_number = str(card_number)

			if cardholder_name is None:
				cardholder_name = 'Not Provided'

			if card_cvv is None:
				raise ValueError, 'cvv number cannot be nil'

			if type(card_cvv) is int:
				card_cvv = str(card_cvv)

			if card_expiry is None:
				raise ValueError, 'card expiration cannot be nil. It has to be in MMYY format'

			if type(card_expiry) is int:
				card_expiry = str(card_expiry)

			payload = { "merchant_ref": description, "transaction_type": transactionType, "method": "credit_card", "amount": amount, "currency_code": currency_code.upper(), "credit_card": { "type": card_type, "cardholder_name": cardholder_name, "card_number": card_number, "exp_date": card_expiry, "cvv": card_cvv } }

		else:

			if transactionID is None:
				raise ValueError, 'Transaction ID cannot be nil'

			if transactionTag is None:
				raise ValueError, 'Transaction Tag cannot be nil'

			if type(transactionTag) is int:
				transactionTag = str(transactionTag)

			payload = { "merchant_ref": description, "transaction_tag" : transactionTag, "transaction_type": transactionType, "method": "credit_card", "amount": amount, "currency_code": currency_code.upper() }

		return {'payload':payload,'transactionID':transactionID}

########### TeleCheck Methods #######################
  
def teleCheckPurchaseVoid(self,transaction_type=None, amount=None, currency_code=None, merchant_ref=None, check_number=None, check_type=None, account_number=None, routing_number=None, 
  accountholder_name=None, customer_id_type=None,customer_id_number=None, client_email=None, gift_card_amount=None, vip=None, clerk_id=None,
  device_id=None,release_type=None,registration_number=None,registration_date=None, date_of_birth=None ,street=None, city=None, state_province=None, zip_postal_code=None, country=None):
    
    makePayload_output = self.makePayload({  
                                    "method":"tele_check",
                                    "transaction_type":transaction_type,
                                    "amount":amount,
                                    "currency_code":currency_code,
                                    "merchant_ref":merchant_ref,
                                    "tele_check":{  
                                        "check_number":check_number,
                                        "check_type":check_type,
                                        "account_number":account_number,
                                        "routing_number":routing_number,
                                        "accountholder_name":accountholder_name,
                                        "customer_id_type":customer_id_type,
                                        "customer_id_number":customer_id_number,
                                        "client_email":client_email,
                                        "gift_card_amount":gift_card_amount,
                                        "vip":vip,
                                        "clerk_id":clerk_id,
                                        "device_id":device_id,
                                        "release_type":release_type,
                                        "registration_number":registration_number,
                                        "registration_date":registration_date,
                                        "date_of_birth":date_of_birth
                                    },
                                    "billing_address":{  
                                        "street":street,
                                        "city":city,
                                        "state_province":state_province,
                                        "zip_postal_code":zip_postal_code,
                                        "country":country
                                    }
                                })   
    return self.makePrimaryTransaction( payload=makePayload_output['payload']);

def teleCheckTaggedVoid(self,  payload):
    self.payload = payload
    self.transactionType = "void"
    return self.makeSecondaryTransaction(self.transactionType, self.payload);

def teleCheckTaggedRefund(self,  payload):
    self.payload = payload
    self.transactionType = "refund"
    return self.makeSecondaryTransaction(self.transactionType, self.payload);
  
  ########### ValueLink Methods - start #######################

 
def valueLinkPurchaseReloadPartialPurchase(self, transaction_type=None, amount=None, currency_code=None,  cardholder_name=None, cc_number=None ):

  makePayload_output = self.makePayload({"method":'value_link',
                        "transaction_type":transaction_type,
                        "amount":amount,
                        "currency_code":currency_code,
                        "valuelink":{
                        "cardholder_name":cardholder_name,
                        "cc_number":cc_number,
                        "credit_card_type":'Gift'
                        }})    
  return self.makePrimaryTransaction(payload=makePayload_output['payload']);

def valueLinkActivation(self, amount=None, currency_code=None, cardholder_name=None, cc_number=None, card_cost=None ):
    
    makePayload_output = self.makePayload({"method":'value_link',
                        "transaction_type":'activation',
                        "amount":amount,
                        "currency_code":currency_code,
                        "valuelink":{
                        "cardholder_name":cardholder_name,
                        "cc_number":cc_number,
                        "credit_card_type":'Gift',
                        "card_cost":card_cost
                        }})

    return self.makePrimaryTransaction( payload=makePayload_output['payload']);

def valueLinkCashOutDeactivationBalanceInquiry(self, currency_code=None, transaction_type=None, cardholder_name=None, cc_number=None, card_cost=None ):
    
    makePayload_output = self.makePayload({"method":'value_link',
                        "transaction_type":transaction_type,
                        "valuelink":
                        {   
                        "cardholder_name":cardholder_name,
                        "cc_number":cc_number,
                        "credit_card_type":'Gift'
                        }})

    return self.makePrimaryTransaction( payload=makePayload_output['payload']);

def valueLinkVoid(self,  payload):
    self.payload = payload
    self.transactionType = "void"
    return self.makeSecondaryTransaction(self.transactionType, self.payload);

def valueLinkRefund(self,  payload):
    self.payload = payload
    self.transactionType = "refund"
    return self.makeSecondaryTransaction(self.transactionType, self.payload);

def valueLinkTaggedVoid(self,  payload):
    self.payload = payload
    self.transactionType = "void"
    return self.makeSecondaryTransaction(self.transactionType, self.payload);

def valueLinkTaggedRefund(self,  payload):
    self.payload = payload
    self.transactionType = "refund"
    return self.makeSecondaryTransaction(self.transactionType, self.payload);
        
    ########### ValueLink Methods - end #######################    
    
    ########### German Direct Debit Methods - start #######################
    
def processPurchaseCreditTransactionWithAVSDirectDebit(self, method=None, transaction_type=None, amount=None, iban=None, mandate_ref=None, bic=None, billing_name=None, city=None, country=None, email=None, phone_type=None, phone_number=None, street=None, state_province=None, zip_postal_code=None ):

    makePayload_output = self.makePayload({ "method":'debit_card',
                                            "transaction_type":transaction_type,
                                            "amount":amount,
                                            "currency_code":'EUR',
                                            "debit_card": 
                                            {
                                                "iban":iban,
                                                "mandate_ref":mandate_ref,
                                                "bic":bic
                                            },
                                            "billing_address": {
                                                "name":billing_name,
                                                "city":city,
                                                "country":country,
                                                "email":email,
                                                "phone": {
                                                    "type":phone_type,
                                                    "number":phone_number,
                                                },
                                                "street":street,
                                                "state_province":state_province,
                                                "zip_postal_code":zip_postal_code
                                            }
                                         })    
    return self.makePrimaryTransaction(payload=makePayload_output['payload']);
     
     
def processPurchaseCreditTransactionWithSoftDescDirectDebit(self, method=None, transaction_type=None, amount=None, iban=None, mandate_ref=None, bic=None, dba_name=None, street=None, region=None, mid=None, mcc=None , postal_code=None, country_code=None, merchant_contact_info=None ):
  makePayload_output = self.makePayload({"method":'debit_card',
    "transaction_type":transaction_type,
    "amount":amount,
    "currency_code":'EUR',
    "debit_card":{
    "iban":iban,
    "mandate_ref":mandate_ref,
    "bic":bic },
    "soft_descriptors":{
    "dba_name":dba_name,
    "street":street,
    "region":region,
    "mid":mid,
    "mcc":mcc,
    "postal_code":postal_code,
    "country_code":country_code,
    "merchant_contact_info":merchant_contact_info
    }
    })
  return self.makePrimaryTransaction(payload=makePayload_output['payload']);
      
def processPurchaseCreditTransactionWithL2L3DirectDebit(self, method=None, transaction_type=None, amount=None, currency_code=None,iban=None, mandate_ref=None, bic=None, name=None, city=None, country=None, email=None, type=None, number=None, street=None, state_province=None, zip_postal_code=None):

    makePayload_output = self.makePayload({  
      "method":'debit_card',
      "transaction_type":'purchase',
      "amount":amount,
      "currency_code":'EUR',
      "debit_card": {
      "iban":iban,
      "mandate_ref":mandate_ref,
      "bic":bic
      },
      "level2":{
      "tax1_amount":tax1_amount,
      "tax1_number":tax1_number,
      "tax2_amount":tax2_amount,
      "tax2_number":tax2_number,
      "customer_ref":customer_ref
      },
      "level3":{
      "alt_tax_amount":alt_tax_amount,
      "alt_tax_id":alt_tax_id,
      "discount_amount":discount_amount,
      "duty_amount":duty_amount,
      "freight_amount":freight_amount,
      "ship_from_zip":ship_from_zip,
      "ship_to_address":{
      "city":city,
      "state":state,
      "zip":zip,
      "country":country,
      "email":email,
      "name":name,
      "phone":phone,
      "address_1":address_1,
      "customer_number":customer_number
      },
      "line_items":[
      {
      "description":description,
      "quantity":quantity,
      "commodity_code":commodity_code,
      "discount_amount":discount_amount,
      "discount_indicator":discount_indicator,
      "gross_net_indicator":gross_net_indicator,
      "line_item_total":line_item_total,
      "product_code":product_code,
      "tax_amount":tax_amount,
      "tax_rate":tax_rate,
      "tax_type":tax_type,
      "unit_cost":unit_cost,
      "unit_of_measure":unit_of_measure
      }
      ]
      }})
    return self.makePrimaryTransaction( payload=makePayload_output['payload'])
       
     
########### German Direct Debit Methods - end #######################  

########### Timeout Reversal for Credit Card Payments - start #######################
    
def processPurchaseCreditTransactionWithTimeoutReversal(self, method=None, transaction_type=None, amount=None, iban=None, mandate_ref=None, bic=None, billing_name=None, city=None, country=None, email=None, phone_type=None, phone_number=None, street=None, state_province=None, zip_postal_code=None ):

    makePayload_output = self.makePayload({  "amount": "100",
                                              "transaction_type": "authorize",
                                              "method": "credit_card",
                                              "currency_code": "GBP",
                                              "credit_card": {
                                                "type": "visa",
                                                "cardholder_name": "John Smith",
                                                "card_number": "4035874000424977",
                                                "exp_date": "1218"
                                              },
                                              "billing_address": {
                                                "city": "St. Louis",
                                                "country": "US",
                                                "email": "abc@main.com",
                                                "phone": {
                                                  "type": "home",
                                                  "number": "212-515-1212"
                                                 },
                                                "street": "12115 LACKLAND",
                                                "state_province": "MO",
                                                "zip_postal_code": "63146 ",
                                                },
                                              "reversal_id": "Re-txn-12341202"
                                         })    


    return self.makePrimaryTransaction(payload=makePayload_output['payload']);

def processTimeoutReversal(self, method=None, transaction_type=None, amount=None, iban=None, mandate_ref=None, bic=None, billing_name=None, city=None, country=None, email=None, phone_type=None, phone_number=None, street=None, state_province=None, zip_postal_code=None ):

    makePayload_output = self.makePayload({  "amount": "100",
                                             "transaction_type": "void",
                                             "method": "credit_card",
                                             "currency_code": "GBP",
                                             "reversal_id": "Re-txn-123412B2"
                                         })    


    return self.makePrimaryTransaction(payload=makePayload_output['payload']);

########### TimeoutReversal - end #######################

########### DCC/Dynamic Pricing Lookup - end #######################
def processDCCLookup(self, bin=None, amount=None):

    makePayload_output = self.makePayload({  "rate_type":"card_rate",
                                              "bin":"559552",
                                              "amount":"100"
                                         })    

    return self.makePrimaryTransaction(payload=makePayload_output['payload']);

def processDynamicPricingLookup(self, bin=None, amount=None):

    makePayload_output = self.makePayload({  "rate_type":"merchant_rate",
                                             "currency_code":"USD",
                                             "amount":"100"
                                         })    

    return self.makePrimaryTransaction(payload=makePayload_output['payload']);

def processDCCPurchase(self, bin=None, amount=None):

    makePayload_output = self.makePayload({  
                                            "amount": "100",
                                            "transaction_type": "Purchase",
                                            "method": "credit_card",
                                            "currency_code": "GBP",
                                            "credit_card": {
                                              "type": "mastercard",
                                              "cardholder_name": "John Smith",
                                              "card_number": "4389800000000006",
                                              "exp_date": "1215",
                                              "cvv": "006"
                                            },
                                           "rate_reference": {
                                              "rate_id": "136752",
                                              "dcc_accepted": "true"
                                          }
                                         })    

    return self.makePrimaryTransaction(payload=makePayload_output['payload']);

########### DCC/Dynamic Pricing Lookup - end #######################

def getFDTokenPayload(self,  FDtype = None, auth = None, ta_token = None, cardholder_name= None, card_number=None, card_expiry=None, card_cvv=None):

    if (transactionType == ('authorize' or 'purchase')): 

      if card_number is None:
        raise ValueError, 'card number cannot be nil'

      if type(card_number) is int:
        card_number = str(card_number)

      if cardholder_name is None:
        cardholder_name = 'Not Provided'

      if card_cvv is None:
        raise ValueError, 'cvv number cannot be nil'

      if type(card_cvv) is int:
        card_cvv = str(card_cvv)

      if card_expiry is None:
        raise ValueError, 'card expiration cannot be nil. It has to be in MMYY format'

      if type(card_expiry) is int:
        card_expiry = str(card_expiry)

      payload = { "type": "FDToken", "auth": "false", "ta_token": "NOIW" , "credit_card": { "type": card_type, "cardholder_name": cardholder_name, "card_number": card_number, "exp_date": card_expiry, "cvv": card_cvv } }

    return {'payload':payload,'transactionID':transactionID}
