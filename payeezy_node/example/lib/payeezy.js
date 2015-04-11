'use strict';

function payeezy(apiKey, apiSecret, token) {
	if (!(this instanceof payeezy)) {
	    return new payeezy(apiKey, apiSecret, token);
	  }
	this.loadResources();
	this.setApiKey(apiKey);
	this.setApiSecret(apiSecret);
	this.setToken(token);
}

var resources = {

  transaction: require('./resources/transaction')

};

payeezy.resources = resources;

payeezy.prototype.host = 'api.payeezy.com';
payeezy.prototype.version = "v1";

payeezy.prototype = {
		
	setApiKey: function(apiKey) {
		this['apiKey'] = apiKey;
	},
	
	setApiSecret: function(apiSecret) {
		this['apiSecret'] = apiSecret;
	},
	
	setToken: function(token) {
		this['token'] = token;
	},
	 
	getApiKey: function() {
		return this['apiKey'];
	},
	 
	getApiSecret: function() {
		return this['apiSecret'];
	},
	
	getToken: function() {
		return this['token'];
	},
	loadResources: function() {
	    for (var name in resources) {
	      this[name] = new resources[name](this);
	    }
	  }
	
};

module.exports = payeezy;