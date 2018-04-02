var CryptoJS = require("crypto-js");
module.exports = {
		
		getAuthorizationHeader: function (apiKey, apiSecret, payload, token, nonce, timestamp) {
			var data = apiKey + nonce + timestamp + token + payload;
			var digest = CryptoJS.HmacSHA256(data, apiSecret);
			var header = new Buffer(digest.toString()).toString('base64');
			return header;
		},
		
		isObject: function(obj) {
			if (typeof obj =='object') {
				return true;
			}
			return false;
		},
		
		isFunction: function(fun) {
			if (typeof fun =='function') {
				return true;
			}
			return false;
		},
		
		getArgumentTypes: function(args) {
			var argType = '';
			for (var i in args) {
				argType = argType + typeof args[i] + ','; 
			}
			argType = argType.substring(0, argType.length-1);
			return argType;
		}
	
};
