
var method = require("../method");
var Resource = require("../resource");

module.exports = Resource.extend({
		
		path : '/transactions/tokens',
		
		purchase : method ({
			tranType : 'FDToken',
			method: 'POST'
		})
		
});