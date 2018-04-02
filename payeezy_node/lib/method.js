var util = require('./util');
var Q = require('q');

module.exports = function method(resProp) {

	return function() {

		var args = [].slice.call(arguments);
		var logArgs = [].slice.call(arguments);
		var callback = util.isFunction(args[args.length-1])? args.pop() : null;
		var data = util.isObject(args[args.length-1])? args.pop() : {};

		var urlParams = resProp.urlParams;

		var uri = resProp.path || '';
		for (var i in urlParams) {
			var value = args.shift();
			if(!value) {
				throw new Error('invalid_request value for argument "'+urlParams[i]+ '" not provided. Please refer to API usage guide');
			}
			uri = uri.replace('{'+urlParams[i]+'}', value);
		}
	    if (args.length) {
	    	throw new Error('invalid_request Invalid number of arguments provided '+util.getArgumentTypes(logArgs)+'. Please refer to API usage guide');
	    }

	    var requestData = {};
	    for (var i in data) {
	    	requestData[i] = data[i];
	    }
	    if (resProp.tranType) {
	    	requestData['transaction_type'] = resProp.tranType;
	    }
	    var requestJSON = JSON.stringify(requestData);

	    var deferred = Q.defer();

	    this.sendRequest(resProp.method, uri, requestJSON, function (err, data) {
	    	if (err) {
	    		deferred.reject(err);
	    	} else {
	    		deferred.resolve(data);
	    	}
	    });

	    return deferred.promise.nodeify(callback);

	}
};
