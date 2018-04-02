
var method = require("../method");
var Resource = require("../resource");

module.exports = Resource.extend({
		
		path : '/transactions',
		
		purchase : method ({
			tranType : 'purchase',
			method: 'POST'
		}),
		
		authorize : method ({
			tranType : 'authorize',
			method: 'POST'
		}),

		tokenAuthorize : method ({
			tranType : 'authorize',
			method: 'POST'
		}),

		tokenPurchase: method ({
			tranType : 'purchase',
			method: 'POST'
		}),
		
		credit : method ({
			tranType : 'credit',
			method: 'POST'
		}),
			
		capture : method ({
			tranType : 'capture',
			method: 'POST',
			path: '/{id}',
			urlParams: ['id']
		}),
			
		void : method ({
			tranType : 'void',
			method: 'POST',
			path: '/{id}',
			urlParams: ['id']
		}),
			
		refund : method ({
			tranType : 'refund',
			method: 'POST',
			path: '/{id}',
			urlParams: ['id']
		}),
		
		split : method ({
			tranType : 'split',
			method: 'POST',
			path: '/{id}',
			urlParams: ['id']
		})
});
