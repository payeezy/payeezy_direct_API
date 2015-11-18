apikey = ""
apisecret = ""
token = ""
baseURL = ""
tokenURL = "" # optional parameter for oken based transaction 

# Resource
import payeezy.http_authorization
from payeezy.api_methods import Payeezy

transactions = Payeezy()

import sys as _sys
from inspect import isclass as _isclass, ismodule as _ismodule

_dogetattr = object.__getattribute__
_ALLOWED_ATTRIBUTES = (
    'apikey',
	'apisecret',
	'token',
	'baseURL',
	'tokenURL' # optional parameter for oken based transaction 
)
_original_module = _sys.modules[__name__]
