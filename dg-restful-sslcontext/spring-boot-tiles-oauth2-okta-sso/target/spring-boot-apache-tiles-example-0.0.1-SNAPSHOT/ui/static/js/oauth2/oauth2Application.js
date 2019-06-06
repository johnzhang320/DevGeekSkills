var appOauth2=angular.module('MyOauth2Application',['ngResource','ngRoute']);
/*
appOauth2.controller('Oauth2AppController',['$scope','$log','$http','$httpParamSerializer','oauth2Factory',
                                   function($scope,$log,$http,$httpParamSerializer,oauth2Factory){*/

var appOauth2=angular.module('MyOauth2Application',['ngResource','ngRoute']);
appOauth2.controller('Oauth2AppController',['$scope','$log','$http', '$httpParamSerializer', 
                                            function($scope,$log,$http, $httpParamSerializer){
	/**
	 * parameter example
	 * autho_server_uri=http://localhost:8080/SpringSecurityOAuth2Example/oauth/token
	 * trustedClientId = my-trusted-client
	 * trustedClientSecret = secret
	 * 
	 */  
	$scope.resourceOwner_username = "bill";
	$scope.resourceOwner_password = "abc123";
	$scope.trustedClientId = "my-trusted-client";
	$scope.trustedClientSecret="secret";
	
	$scope.autho_server_uri="/SpringSecurityOAuth2Example/oauth/token";
	$scope.autho_server_url=" ";
	$scope.resource_server_url=" ";
	$scope.credential_display=" ";
	$scope.content_type_display=" ";
	$scope.result_display =" ";
	$scope.access_token=" ";
	$scope.refresh_token=" ";
	$scope.token_type = " ";
	$scope.expires_in = " ";
	$log.log("Loading Controller oauth2Controller.......$scope.autho_server_uri="+$scope.autho_server_uri);
	
	$scope.credential = btoa($scope.trustedClientId + ":" + $scope.trustedClientSecret);
 	$scope.data = {
	        grant_type:"password", 
	        username: $scope.resourceOwner_username, 
	        password: $scope.resourceOwner_password, 
	        client_id:$scope.trustedClientId
	};
	
	var myPostData = $httpParamSerializer($scope.data);
	$scope.verifyRequest = function() {
		//http://localhost:8080/SpringSecurityOAuth2Example/oauth/token?grant_type=password&username=bill&password=abc123
		$scope.autho_server_url=$scope.autho_server_uri+"?grant_type=password&username="+$scope.resourceOwner_username+"&password="+$scope.resourceOwner_password;
		$scope.credential_display=$scope.credential;
		$scope.content_type_display="application/json";
	};
	$scope.clearRequest = function() {
 		$scope.autho_server_url=" ";
		$scope.credential_display=" ";
		$scope.content_type_display=" ";
	};
	$scope.tokens = "";
	$scope.sendRequest = function() {		
		if ($scope.autho_server_uri==" ") {
			$log.log("$scope.autho_server_uri is not initialized");
		};
 		oauth2Factory.getToken($scope.autho_server_uri,$scope.credential,myPostData,function(tokens) {
				$scope.access_token=tokens.access_token;
				$scope.refresh_token=tokens.refresh_token;
				$scope.token_type = tokens.token_type;
				$scope.expires_in = tokens.expires_in;
				$scope.tokens = tokens;
		    }
		);  
	};
	$scope.fetchAllUsers = function() {
		var resourceServerUri = "/SpringSecurityOAuth2Example/user/";
		if (access_token==" ") {
			$log.log("access_token is not initialized!");
			return;
		};
		$scope.resource_server_url = resourceServerUri+"?access_token="+access_token;
		oauth2Factory.getDataByAccessToken($scope.resource_server_url,function(result) {				 
				$scope.result_display = result;
	    	}
		);  
	};
	$scope.clearResult = function() {
		$scope.result_display =" ";
	};
	$scope.getRefreshedAccessToken = function() {		
		if ($scope.autho_server_uri==" ") {
			$log.log("$scope.autho_server_uri is not initialized");
			return;
		};
		if ($scope.refresh_token==" ") {
			$log.log("$scope.refresh_token is not initialized");
			return;
		};
		//http://localhost:8080/SpringSecurityOAuth2Example/oauth/token?grant_type=refresh_token&refresh_token=73ddba90-6256-4bcc-9e93-d91c1218d992
		var refreshUrl = $scope.autho_server_uri+"?grant_type=refresh_token&refresh_token="+$scope.refresh_token;
		$log.log("$refreshUrl = "+refreshUrl);
		objoauth2Factory.getTokenByRefreshToken(refreshUrl,trustedClientCredentialCode, cb)
 		oauth2Factory.getToken($scope.autho_server_uri,$scope.credential,myPostData,function(tokens) {
				$scope.access_token=tokens.access_token;
				$scope.refresh_token=tokens.refresh_token;
				$scope.token_type = tokens.token_type;
				$scope.expires_in = tokens.expires_in;
				$scope.tokens = tokens;
		    }
		);  
	};
	 
}]);
 
appOauth2.factory('oauth2Factory',['$http','$log','$q',function($http,$log,$q){
	$log.log("Initialized oauth2Factory");
	var objOauth2Factory = {};	
	 
	objoauth2Factory.getToken=function(resourceOwnerUri,trustedClientCredentialCode , cb) {   // SpringSecurityOAuth2Example
		if (resouceOwnerUri==" ") {
			$log.log("resouceOwnerUri is not initialized!");
		};
		$log.log("resouceOwnerUri Initialized="+resouceOwnerUri)
		$http({
			method:    'POST',		
			url:resourceOwnerUri,
			headers: { 
					"Content-type": "application/json; charset=utf-8",
				    "Authorization":  'Basic ' + trustedClientCredentialCode
			} 
			//data: postData
			}.then(function(response){
				var tokens = response.data.data;
				$log.log("Successfully get token, access token:"+tokens.access_token+" , refresh token:"+tokens.refresh_token);
				cb(tokens);
			},function(error){
				$log.log("Error Occurred");
				$q.reject("Error Occurred");
			})
		);
	};
	objoauth2Factory.getDataByAccessToken=function(resourceServerUrl, cb) {   // SpringSecurityOAuth2Example
	 	 
		$log.log("resourceServerUrl="+resourceServerUrl);
		$http({
			method:    'GET',		
			url:resourceServerUrl,			 
			}.then(function(response){
				var retVal = response.data;
				$log.log("Successfully get data by access token:"+retVal);
				cb(retVal);
			},function(error){
				$log.log("Error Occurred");
				$q.reject("Error Occurred");
			})
		);
	};
	objoauth2Factory.getTokenByRefreshToken=function(authorizationServerUri,trustedClientCredentialCode, cb) {   // SpringSecurityOAuth2Example
		if (authorizationServerUri==" ") {
			$log.log("authorizationServerUri is not initialized!");
		};
		$log.log("authorizationServerUri Initialized="+authorizationServerUri)
		$http({
				method:    'POST',		
				url:authorizationServerUri,
				headers: { 
						"Content-type": "application/json; charset=utf-8",
					    "Authorization":  'Basic ' + trustedClientCredentialCode
			  } 
			}.then(function(response){
				var tokens = response.data;
				$log.log("Successfully get token, access token:"+tokens.access_token+" , refresh token:"+tokens.refresh_token);
				cb(tokens);
			},function(error){
				$log.log("Error Occurred");
				$q.reject("Error Occurred");
			})
		);
	};
	return objOauth2Factory;
}]);