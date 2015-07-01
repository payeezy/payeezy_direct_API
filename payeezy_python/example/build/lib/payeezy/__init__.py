apikey = ""
apisecret = ""
token = ""
baseURL = ""


# Resource
import http_authorization
from api_methods import Payeezy

transactions = Payeezy()

import sys as _sys
from inspect import isclass as _isclass, ismodule as _ismodule

_dogetattr = object.__getattribute__
_ALLOWED_ATTRIBUTES = (
    'apikey',
	'apisecret',
	'token',
	'baseURL'
)
_original_module = _sys.modules[__name__]
