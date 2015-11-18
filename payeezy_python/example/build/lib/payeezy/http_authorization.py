

#####################################################################################################################################################
#		File Name   	:  http_authorization.py																									#
#		Description 	:  Makes POST type rest	calls setup for PayeezyHTTPAuthorize																#
#		Backend     	:  SOA 									       (Nature : Static ////   ToDo: Dynamic)										#
#		Base URL    	:  https://cattest-fdc.broker.soa.com  (Extensions being used - /transactions, /merchants)									#
#		Methods	Type	:  POST 																													#
#####################################################################################################################################################
import os, sys          # Standard system functions
import time  			# Get Timestamp
import socket           # support functions for HTTPS connections - dependancy for HTTPAdapter
import ssl


try:
	import base64 		# Base 64 encoding/decoding lib
except Exception as BASE64LIB_Error:
    raise BASE64_IMPORT_Error

from base64 import b64encode

try:
	import json 		# Json Lib for data type conversions - REST Calls/Data output format
except Exception as JSONLIB_Error:
    raise JSON_IMPORT_Error

try:
	import hashlib      
except Exception as HASHLIB_Error:
    raise HASHLIB_IMPORT_Error

try:
	import hmac      # SHA1 encryption to accomodate the atmosphere varibles
except Exception as HMAC_Error:
    raise HMAC_IMPORT_Error


try:
	import requests 	# Apache2 Licensed HTTP library - has methods to make REST calls
except Exception as REQUESTSLIB_Error:
    raise REQUESTSLIB_Error

from requests.auth import HTTPBasicAuth
from requests.adapters import HTTPAdapter
from requests.packages.urllib3.poolmanager import PoolManager

# TLSv1 protocol addition to HTTP adaptor for HTTPS calls
class MyAdapter(HTTPAdapter):
    def init_poolmanager(self, connections, maxsize, block=False):
        self.poolmanager = PoolManager(num_pools=connections,
                                       maxsize=maxsize,
                                       block=block,
                                       ssl_version=ssl.PROTOCOL_TLSv1)

# HTTP authorization, headers generations and generic method to preform transactions and boarding operations.

class PayeezyHTTPAuthorize(object):

	# init function
	def __init__(self, apiKey,apiSecret, token, url,tokenurl):
		self.apikey = apiKey
		self.apisecret = apiSecret
		self.token = token
		self.url = url
		self.tokenurl = tokenurl
		self.timeout = 30 # max timeout is 30 sec

	# HMAC Generation
	def generateHMACAuthenticationHeader(self, payload):
		# cryptographically strong random number
		nonce = str(int(os.urandom(16).encode('hex'),16)) 
		currentTimeInMilli = str(int(round(time.time() * 1000)))
		messageData = self.apikey+nonce+currentTimeInMilli+self.token+payload
		hmacInHex = hmac.new(self.apisecret, msg=messageData, digestmod=hashlib.sha256).hexdigest()
		return b64encode(hmacInHex)

	# method to make calls for getToken 
	def getTokenPostCall(self, payload):
		response = requests.Session()
		response.mount('https://', MyAdapter())
		self.payload = json.dumps(payload)
		authorizationVal = self.generateHMACAuthenticationHeader(payload=self.payload)
		result = response.post(self.tokenURL, headers={'User-Agent':'Payeezy-Python','content-type': 'application/json','apikey':self.apikey,'token':self.token,'Authorization':'xxxxx'}, data=self.payload)
		return result
			
	#Generic method to make calls for primary transactions
	def makeCardBasedTransactionPostCall(self, payload):
		response = requests.Session()
		response.mount('https://', MyAdapter())
		self.payload = json.dumps(payload)
		authorizationVal = self.generateHMACAuthenticationHeader(payload=self.payload)
		result = response.post(self.url, headers={'User-Agent':'Payeezy-Python','content-type': 'application/json','apikey':self.apikey,'token':self.token,'Authorization':authorizationVal}, data=self.payload)
		return result


	#Generic method to make calls for secondary transactions
	def makeCaptureVoidRefundPostCall(self,payload,transactionID):
		response = requests.Session()
		response.mount('https://', MyAdapter())
		self.url =  self.url + '/' + transactionID
		self.payload = json.dumps(payload)
		authorizationVal = self.generateHMACAuthenticationHeader(payload=self.payload)
		result = response.post(self.url, headers={'User-Agent':'Payeezy-Python','content-type': 'application/json','apikey':self.apikey,'token':self.token,'Authorization':authorizationVal}, data=self.payload)
		return result

		