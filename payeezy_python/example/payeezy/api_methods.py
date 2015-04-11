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
	########### TeleCheck Methods #######################
	
	def teleCheckPurchaseVoid(self, amount=None, transaction_type=None, currency_code=None, check_number=None, check_type=None, routing_number=None, account_number=None, accountholder_name=None, customer_id_type=None, customer_id_number=None, client_email=None, street=None,	city=None, state_province=None, zip_postal_code=None, country=None):
		
		makePayload_output = self.makePayload(method=tele_check,
											  transaction_type=transaction_type,
											  amount=amount,
											  currency_code=currency_code,
											  tele_check:
											  {   
												check_number=check_number,
												check_type=check_type,
												routing_number=routing_number,
												account_number=account_number,
												accountholder_name=accountholder_name,
												customer_id_type=customer_id_type,
												customer_id_number=customer_id_number,
												client_email=client_email,
												gift_card_amount=gift_card_amount,
												vip=vip,
												clerk_id=clerk_id,
												device_id=device_id,
												release_type=release_type,
												registration_number=registration_number,
												registration_date=registration_date,
												date_of_birth=date_of_birth
											   },
											   billing_address:
											   {   
												street=street,
												city=city,
												state_province=state_province,
												zip_postal_code=zip_postal_code,
												country=country
											    })

		return self.makePrimaryTransaction( payload=makePayload_output['payload'])

	def teleCheckTaggedVoid(self,  payload):
		self.payload = payload
		self.transactionType = "void"
		return self.makeSecondaryTransaction(self.transactionType, self.payload);

	def teleCheckTaggedRefund(self,  payload):
		self.payload = payload
		self.transactionType = "refund"
		return self.makeSecondaryTransaction(self.transactionType, self.payload);
	
	########### ValueLink Methods #######################
	
	def valueLinkPurchaseReloadPartialPurchase(self, amount=None, transaction_type=None, currency_code=None, cardholder_name=None, cc_number=None, ):
		
		makePayload_output = self.makePayload(method=value_link,
											  transaction_type=transaction_type,
											  amount=amount,
											  currency_code=currency_code,
											  valuelink:
											  {   
												cardholder_name=cardholder_name,
												cc_number=cc_number,
												credit_card_type='Gift'
											  })

		return self.makePrimaryTransaction( payload=makePayload_output['payload'])

	def valueLinkActivation(self, amount=None, currency_code=None, cardholder_name=None, cc_number=None, card_cost=None ):
		
		makePayload_output = self.makePayload(method=value_link,
											  transaction_type='activation',
											  amount=amount,
											  currency_code=currency_code,
											  valuelink:
											  {   
												cardholder_name=cardholder_name,
												cc_number=cc_number,
												credit_card_type='Gift',
												card_cost=card_cost
											  })

		return self.makePrimaryTransaction( payload=makePayload_output['payload'])

	def valueLinkCashOutDeactivationBalanceInquiry(self, currency_code=None, transaction_type=None, cardholder_name=None, cc_number=None, card_cost=None ):
		
		makePayload_output = self.makePayload(method=value_link,
											  transaction_type=transaction_type,
											  valuelink:
											  {   
												cardholder_name=cardholder_name,
												cc_number=cc_number,
												credit_card_type='Gift'
											  })

		return self.makePrimaryTransaction( payload=makePayload_output['payload'])

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

	def makePrimaryTransaction(self, payload):
		self.payload = payload
		self.payeezy = http_authorization.PayeezyHTTPAuthorize(payeezy.apikey,payeezy.apisecret,payeezy.token,payeezy.url)
		return self.payeezy.makeCardBasedTransactionPostCall(self.payload)

	def makeSecondaryTransaction(self, payload,transactionID):
		self.payload = payload
		self.transactionID = transactionID
		self.payeezy = http_authorization.PayeezyHTTPAuthorize(payeezy.apikey,payeezy.apisecret,payeezy.token,payeezy.url)
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
