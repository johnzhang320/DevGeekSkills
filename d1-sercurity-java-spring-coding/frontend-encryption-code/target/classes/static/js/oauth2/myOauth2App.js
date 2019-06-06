
var appOauth2=angular.module('MyOauth2Application',['ngResource','ngRoute']);

appOauth2.controller('Oauth2AppController',['$scope','$log','$http','oauth2Factory',function($scope,$log,$http,oauth2Factory){
	$scope.trustedClientId = "my-trusted-client";
	$scope.trustedClientSecret="secret";	
	$scope.autho_server_uri="/SpringSecurityOAuth2Example/oauth/token";
	$scope.resourceOwner_username = "bill";
	$scope.resourceOwner_password = "abc123";
	$scope.trustedClientId = "my-trusted-client";
	$scope.trustedClientSecret="secret";
	
	//$scope.autho_server_uri="/SpringSecurityOAuth2Example/oauth/token";
	$scope.resource_server_url = "/SpringSecurityOAuth2Example/user/?access_token=";
	$scope.refreshUrl ="/SpringSecurityOAuth2Example/oauth/token?grant_type=refresh_token&refresh_token=";
	//$scope.resource_server_url=" ";
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
	        client_id: $scope.trustedClientId
	};
	$scope.autho_server_url=$scope.autho_server_uri+"?grant_type=password&username="+$scope.resourceOwner_username+"&password="+$scope.resourceOwner_password;

//	var myPostData = $httpParamSerializer($scope.data);
	$scope.verifyRequest = function() {
		$log.log("Begin verifyRequest().....");
		$scope.autho_server_url=$scope.autho_server_uri+"?grant_type=password&username="+$scope.resourceOwner_username+"&password="+$scope.resourceOwner_password;
		$log.log("$scope.autho_server_url="+$scope.autho_server_url);
		$scope.credential_display=btoa($scope.trustedClientId + ":" + $scope.trustedClientSecret);
		$log.log("$scope.credential_display="+$scope.credential_display);
		$scope.content_type_display='application/json';
		$log.log("$scope.content_type_display="+$scope.content_type_display);
		$log.log("End verifyRequest()");
	};
	$scope.clearRequest = function() {
		$log.log("Begin clearRequest().....");
 		$scope.autho_server_url=" ";
		$scope.credential_display=" ";
		$scope.content_type_display=" ";
		$log.log("End clearRequest");
		
	};
	$scope.clearResult = function() {
		$scope.result_display =" ";
	};
	$scope.clearTokens = function() {
		$scope.access_token=" ";
		$scope.refresh_token=" ";
		$scope.token_type = " ";
		$scope.expires_in = " ";
	};
	$scope.tokens = "";
	$scope.sendRequest = function() {		
		$log.log("Begin sendRequest().....");
		if ($scope.autho_server_uri==" ") {
			$log.log("$scope.autho_server_uri is not initialized");
		};
		oauth2Factory.gettingToken($scope.autho_server_url,$scope.credential,function(tokens) {
				$scope.access_token=tokens.access_token;
				$scope.refresh_token=tokens.refresh_token;
				$scope.token_type = tokens.token_type;
				$scope.expires_in = tokens.expires_in;
				$scope.tokens = tokens;
				$scope.resource_server_url = "/SpringSecurityOAuth2Example/user/?access_token="+tokens.access_token;
				$scope.refreshUrl =$scope.autho_server_uri+"?grant_type=refresh_token&refresh_token="+tokens.refresh_token;
		});
 		$log.log("End sendRequest()");
	};
	$scope.fetchAllUsers = function() {
		$log.log("Begin fetchAllUsers().....");
		 
		if ($scope.access_token==" ") {
			$log.log("access_token is not initialized!");
			return;
		};
		oauth2Factory.gettingDataByAccessToken($scope.resource_server_url,function(result) {				 
				$scope.result_display = result;
	    });  
		$log.log("End fetchAllUsers()")
	};
	$scope.getRefreshedAccessToken = function() {		
		$log.log("Begin getRefreshedAccessToken().....");
		if ($scope.autho_server_uri==" ") {
			$log.log("$scope.autho_server_uri is not initialized");
			return;
		};
		if ($scope.refresh_token==" ") {
			$log.log("$scope.refresh_token is not initialized");
			return;
		};
		//http://localhost:8080/SpringSecurityOAuth2Example/oauth/token?grant_type=refresh_token&refresh_token=73ddba90-6256-4bcc-9e93-d91c1218d992
		$scope.refreshUrl = $scope.autho_server_uri+"?grant_type=refresh_token&refresh_token="+$scope.refresh_token;
	
		$log.log("$scope.refreshUrl  = "+$scope.refreshUrl );
		
		 
 		oauth2Factory.gettingTokenByRefreshToken($scope.refreshUrl,$scope.credential,function(tokens) {
				$scope.access_token=tokens.access_token;
				$scope.refresh_token=tokens.refresh_token;
				$scope.token_type = tokens.token_type;
				$scope.expires_in = tokens.expires_in;
				$scope.tokens = tokens;
				$scope.resource_server_url = "/SpringSecurityOAuth2Example/user/?access_token="+tokens.access_token;
				$scope.refreshUrl =$scope.autho_server_uri+"?grant_type=refresh_token&refresh_token="+tokens.refresh_token;

		    }
		);  
		$log.log("End getRefreshedAccessToken()")
	};
}]);

appOauth2.factory('oauth2Factory',['$http','$log','$q',function($http,$log,$q){
	$log.log("Initialized oauth2Factory....");
	var objoauth2Factory = {};	
	
	
	objoauth2Factory.gettingToken=function(autho_server_Url,trustedClientCredentialCode , cb) {   // SpringSecurityOAuth2Example
		$log.log("Begin objoauth2Factory.getingToken().....")
		if (autho_server_Url==" ") {
			$log.log("autho_server_Url is not initialized!");
		};
		$log.log("autho_server_Url Initialized="+autho_server_Url);
		
		$http({
			method:    'POST',		
			url:autho_server_Url,
			headers: { 
					"Content-type": "application/json; charset=utf-8",
				    "Authorization":  'Basic ' + trustedClientCredentialCode
			         } 
			//data: postData
		}).then(function(response){
			$log.log("response.data="+response.data);
			var tokens = response.data;
			$log.log("Successfully get token, access token:"+tokens.access_token+" , refresh token:"+tokens.refresh_token);
			cb(tokens);
		},function(error){
			$log.log("Error Occurred");
			$q.reject("Error Occurred");
		});
		
		$log.log("End objoauth2Factory.getingToken()")
	};
	objoauth2Factory.gettingDataByAccessToken=function(resourceServerUrl, cb) {   // SpringSecurityOAuth2Example
		$log.log("Begin gettingDataByAccessToken().....");
		$log.log("Current resourceServerUrl="+resourceServerUrl);
		$http({
			method:    'GET',		
			url:resourceServerUrl,			 
		}).then(function(response){
			var retVal = response.data;
			$log.log("Successfully get data by access token:"+retVal);
			cb(retVal);
		},function(error){
			$log.log("Error Occurred");
			$q.reject("Error Occurred");
		});
		
		$log.log("End gettingDataByAccessToken()");
	};
	objoauth2Factory.gettingTokenByRefreshToken=function(authorizationServerUri,trustedClientCredentialCode, cb) {   // SpringSecurityOAuth2Example
		$log.log("Begin gettingTokenByRefreshToken()....");
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
		}).then(function(response){
			var tokens = response.data;
			$log.log("Successfully get token, access token:"+tokens.access_token+" , refresh token:"+tokens.refresh_token);
			cb(tokens);
		},function(error){
			$log.log("Error Occurred");
			$q.reject("Error Occurred");
		});
		$log.log("End gettingTokenByRefreshToken()");
	};
	return objoauth2Factory;
}]);