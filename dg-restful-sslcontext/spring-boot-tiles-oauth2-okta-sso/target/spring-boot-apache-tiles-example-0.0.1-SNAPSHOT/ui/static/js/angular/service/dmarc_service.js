/**
 * Create AngularJS Service to communicate with Server

	In our application, we will be communicating with Server which in our example is Spring REST API based 
	back-end. In AngularJS based applications, the preferred way to communicate with server is using 
	AngularJS built-in $http Service. AngularJS $http service allows us to communicate with server 
	endpoints using XHR [browser's XMLHttpRequest Object] API. The $http API is based on the deferred/promise 
	APIs exposed by the $q service which is an implementation of Promise interface, based on Kris Kowal’s Q 
	proposal, which is a standardized way of dealing with asynchronous calls in JavaScript.
	
	To know more in details about AngularJS Services, please have a look at Server communication with AngularJS 
	$http service.
	
	$q constructor

		The streamlined ES6 style promise is essentially just using $q as a constructor which takes a resolver 
		function as the first argument. This is similar to the native Promise implementation from ES6, see MDN.
		
		While the constructor-style use is supported, not all of the supporting methods from ES6 promises are 
		available yet.
		
		It can be used like so:
		
		// for the purpose of this example let's assume that variables `$q` and `okToGreet`
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
		
		Note: unlike ES6 behavior, an exception thrown in the constructor function will NOT implicitly reject
		 the promise.
		
		However, the more traditional CommonJS-style usage is still available, and documented below.
		
		The CommonJS Promise proposal describes a promise as an interface for interacting with an object that 
		represents the result of an action that is performed asynchronously, and may or may not be finished at 
		any given point in time.
		
		From the perspective of dealing with error handling, deferred and promise APIs are to asynchronous 
		programming what try, catch and throw keywords are to synchronous programming.
		
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
		
		At first it might not be obvious why this extra complexity is worth the trouble. The payoff comes in the 
		way of guarantees that promise and deferred APIs make, see
		 https://github.com/kriskowal/uncommonjs/blob/master/promises/specification.md.
		
		Additionally the promise api allows for composition that is very hard to do with the traditional
		 callback (CPS) approach. For more on this please see the Q documentation especially the section 
		 on serial or parallel joining of promises.
		The Deferred API
		
		A new instance of deferred is constructed by calling $q.defer().
		
		The purpose of the deferred object is to expose the associated Promise instance as well as APIs that 
		can be used for signaling the successful or unsuccessful completion, as well as the status of the task.
		
		Methods
		
		    resolve(value) – resolves the derived promise with the value. If the value is a rejection constructed 
		    via $q.reject, the promise will be rejected instead.
		    reject(reason) – rejects the derived promise with the reason. This is equivalent to resolving it with 
		    a rejection constructed via $q.reject.
		    notify(value) - provides updates on the status of the promise's execution. This may be called multiple 
		    times before the promise is either resolved or rejected.
		
		Properties
		
		    promise – {Promise} – promise object associated with this deferred.
		
		The Promise API
		
		A new promise instance is created when a deferred instance is created and can be retrieved by calling 
		deferred.promise.
		
		The purpose of the promise object is to allow for interested parties to get access to the result of 
		the deferred task when it completes.
		
		Methods
		
		    then(successCallback, [errorCallback], [notifyCallback]) – regardless of when the promise was or 
		    will be resolved or rejected, then calls one of the success or error callbacks asynchronously 
		    as soon as the result is available. The callbacks are called with a single argument: the result 
		    or rejection reason. Additionally, the notify callback may be called zero or more times to 
		    provide a progress indication, before the promise is resolved or rejected.
		
		    This method returns a new promise which is resolved or rejected via the return value of the 
		    successCallback, errorCallback (unless that value is a promise, in which case it is resolved 
		    with the value which is resolved in that promise using promise chaining). It also notifies 
		    via the return value of the notifyCallback method. The promise cannot be resolved or rejected 
		    from the notifyCallback method. The errorCallback and notifyCallback arguments are optional.
		
		    catch(errorCallback) – shorthand for promise.then(null, errorCallback)
		
		    finally(callback, notifyCallback) – allows you to observe either the fulfillment or rejection of a 
		    promise, but to do so without modifying the final value. This is useful to release resources or 
		    do some clean-up that needs to be done whether the promise was rejected or resolved. See the full 
		    specification for more information.

 */

'use strict';

// we already defined module in app.js, using $q to implement asynchronize $http, call back in controller

angular.module('dmarcApp').factory('DmarcService', ['$http', '$q', function($http, $q){

    var REST_MASTER_SERVICE_URI = '/SpringbootSecurityDemo/masters.html';
    var REST_DETAIL_SERVICE_URI = '/SpringbootSecurityDemo/details.html';

    var factory = {
        fetchRangeMasters: fetchRangeMasters,
        fetchSingleMaster: fetchSingleMaster,
        fetchDetails: fetchDetails,       
    };

    return factory;

    function fetchRangeMasters() {
        var deferred = $q.defer();
        $http.get(REST_MASTER_ERVICE_URI+"/10357/10361")
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while fetching Masters');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
    
    function fetchSingleMaster(masterId) {
        var deferred = $q.defer();
        $http.get(REST_MASTER_ERVICE_URI+"/"+masterId)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while fetching Masters');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
    
    function fetchDetails(masterId) {
        var deferred = $q.defer();
        $http.get(REST_DETAIL_ERVICE_URI+"/"+masterId)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while fetching Details');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
 }]);
