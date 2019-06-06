/**
 * // for the purpose of this example let's assume that variables `$q` and `okToGreet`
	// are available in the current lexical scope (they could have been injected or passed in).
	
	function asyncGreet(name) {
	  // perform some asynchronous operation, resolve or reject the promise when appropriate.
	  return $q(function(resolve, reject) {
	    setTimeout(function() {
	      if (okToGreet(name)) {
	        resolve('Hello, ' + name + '!');
	      } else {
	        reject('Greeting ' + name + ' is not allowed.');
	      }
	    }, 1000);
	  });
	}
	
	var promise = asyncGreet('Robin Hood');
	promise.then(function(greeting) {
	  alert('Success: ' + greeting);
	}, function(reason) {
	  alert('Failed: ' + reason);
	});
	Note: progress/notify callbacks are not currently supported via the ES6-style interface.
	
	Note: unlike ES6 behavior, an exception thrown in the constructor function will NOT 
	implicitly reject the promise.
	
	However, the more traditional CommonJS-style usage is still available, and documented below.
	
	The CommonJS Promise proposal describes a promise as an interface for interacting with an object that represents the result of an action that is performed asynchronously, and may or may not be finished at any given point in time.
	
	From the perspective of dealing with error handling, deferred and promise APIs are to asynchronous programming what try, catch and throw keywords are to synchronous programming.
	
	// for the purpose of this example let's assume that variables `$q` and `okToGreet`
	// are available in the current lexical scope (they could have been injected or passed in).
	
	function asyncGreet(name) {
	  var deferred = $q.defer();
	
	  setTimeout(function() {
	    deferred.notify('About to greet ' + name + '.');
	
	    if (okToGreet(name)) {
	      deferred.resolve('Hello, ' + name + '!');
	    } else {
	      deferred.reject('Greeting ' + name + ' is not allowed.');
	    }
	  }, 1000);
	
	  return deferred.promise;
	}
	
	var promise = asyncGreet('Robin Hood');
	promise.then(function(greeting) {
	  alert('Success: ' + greeting);
	}, function(reason) {
	  alert('Failed: ' + reason);
	}, function(update) {
	  alert('Got notification: ' + update);
	});

 *
 */



'use strict';

App.factory('ItemService', ['$http', '$q', function($http, $q){

	return {
		
			fetchCategoryList: function() {//Fetches category list from server.
				return $http.get('/Spring4MVCAngularJSRoutingWithUIRouterExample/categories')
					.then(
								function(response){
									return response.data;
								}, 
								function(errResponse){
									console.error('Error while fetching Items');
									return $q.reject(errResponse);
								}
						);
			},

			fetchAllItems: function(category) {//Fetches list of item for a specific category.
					return $http.get('/Spring4MVCAngularJSRoutingWithUIRouterExample/item/'+category)
							.then(
									function(response){
										return response.data;
									}, 
									function(errResponse){
										console.error('Error while fetching Items');
										return $q.reject(errResponse);
									}
							);
			},
		    
			fetchSpecificItem: function(category,id) {//Fetches a specific item based on category and item id.
				return $http.get('/Spring4MVCAngularJSRoutingWithUIRouterExample/item/'+category+'/'+id)
						.then(
								function(response){
									return response.data;
								}, 
								function(errResponse){
									console.error('Error while fetching specific Item');
									return $q.reject(errResponse);
								}
						);
			}
	};

}]);
