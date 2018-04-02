var http = require('https');

var util = require('./util');

var hasOwn = {}.hasOwnProperty;

Resource.extend = function(sub) {

    var Super = this;
    var Constructor = hasOwn.call(sub, 'constructor') ? sub.constructor : function() {
      Super.apply(this, arguments);
    };
    Constructor.prototype = Object.create(Super.prototype);
    for (var i in sub) {
      if (hasOwn.call(sub, i)) {
        Constructor.prototype[i] = sub[i];
      }
    }
    for (i in Super) {
      if (hasOwn.call(Super, i)) {
        Constructor[i] = Super[i];
      }
    }
    return Constructor;
  };

function Resource(payeezy) {
	this.payeezy = payeezy;
}

function sendRequest(httpMethod, uri, requestJSON, callback) {
	var nonce = Math.random() * 1000000000000000000;
	var timeInMillis = new Date().getTime();
	var headers = {
		'Content-Type': 'application/json',
		'Accept': 'application/json',
		'apikey': this.payeezy.getApiKey(),
		'token': this.payeezy.getToken(),
		'nonce': nonce,
		'timestamp':timeInMillis,
		'Authorization': util.getAuthorizationHeader(this.payeezy.getApiKey(), this.payeezy.getApiSecret(), requestJSON, this.payeezy.getToken(), nonce, timeInMillis)
	};
	if (typeof this.payeezy.host == 'undefined') {
		this.payeezy.host = 'api-cert.payeezy.com';
	}
	if (typeof this.payeezy.version == 'undefined') {
		this.payeezy.version = 'v1';
	}
	var options = {
		host: this.payeezy.host,
		path: '/'+this.payeezy.version+this.path+uri,
		port: '443',
		method: httpMethod,
		headers: headers
	};
	var req = http.request(options);
	req.on('response', this.responseHandler(callback));
	req.on('error', this.errorHandler(callback));
	req.write(requestJSON);
	req.end();
}

function responseHandler(callback) {
	var self = this;
	return function (response) {
		var str = '';
		response.on('data', function (chunk) {
			str += chunk;
		});
		response.on('end', function () {
			try {
				str = JSON.parse(str);
				if (response.statusCode == 201 || response.statusCode == 200) {
					callback.call(self, null, str);
				} else {
					callback.call(self, str, null);
				}
			} catch(e) {
				callback.call(self, {type:'server_error', message:'Invalid JSON response from First Data. Actual Response-'+str, error: e},null);
			}
		});
	}
}

function errorHandler(callback) {
	var self = this;
	return function (error) {
		if (error) {
			if (error.code == 'ETIMEDOUT') {
				callback.call(self, {type:error.code, message:'Request timed out', error: error},null);
			} else {
				callback.call(self, {type:'connection_error',message:'Error occured while connecting to First API', error: error},null);
			}
		}
	}
}

Resource.prototype = {

		sendRequest: sendRequest,
		responseHandler: responseHandler,
		errorHandler: errorHandler
};

module.exports = Resource;
