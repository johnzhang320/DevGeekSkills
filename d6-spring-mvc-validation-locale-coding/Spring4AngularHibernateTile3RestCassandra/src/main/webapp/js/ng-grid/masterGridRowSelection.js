var app = angular.module('ngGridMasterDetailRowSelection', ['ngTouch', 'ui.grid', 'ui.grid.selection']);   // add ui.grid.selection

app.controller('SecondCtrl', ['$scope', '$http', '$interval','$log','uiGridConstants','dmarcFactory', 
                              function ($scope, $http, $interval,$log, uiGridConstants,dmarcFactory) {
	
	var URL = "/Spring4AngularHibernateTile3RestCassandra/masters.html/";

    $scope.toggleRowSelection = function() {
	    $scope.gridApi.selection.clearSelectedRows();
	    $scope.gridOptions.enableRowSelection = !$scope.gridOptions.enableRowSelection;
	    $scope.gridApi.core.notifyDataChange( uiGridConstants.dataChange.OPTIONS);
	  };
	
	$scope.gridOptions = {	 	
	enableRowSelection: true, 
	multiSelect: true,
    enableFiltering: true,
	//modifierKeysToMultiSelect: false,
	//noUnselect: true,
    paginationPageSizes: [25, 50, 75],
    paginationPageSize: 10,
	//enableRowHeaderSelection: false, 
	onRegisterApi: function( gridApi ) {
	    $scope.gridApi = gridApi;
	},
	showGroupPanel: true,
 
	columnDefs: [
	            { field:'masterId', displayName:'Id', width:60, enableFiltering: true},  			
			    { field:'orgName', displayName:'Organization',width:120, enableFiltering: true }, 				
			    { field:'email', displayName:'Email',width:260, enableFiltering: true }, 	
			    { field:'reportId', displayName:'Report Id',width:260,enableFiltering: true }, 		
			    { field:'domain', displayName:'Domain Name',width:140, enableFiltering: true }, 
			    { field:'hostName', displayName:'Host Name',width:140, enableFiltering: true }, 	
			    { field:'policyString', displayName:'Policy',width:260,enableFiltering: true },
			    { field:'beginTime', displayName:'Begin Date',width:120,enableFiltering: false }, 			
			    { field:'endTime', displayName:'End Date',width:120, enableFiltering: false } 				
			 
  			],
  			rowData: null,
  			 
	  		  		 
	   }
	   $scope.promise = dmarcFactory.fetchAllData(URL);
	   $scope.promise
	   .then (function(data){
		   $scope.gridOptions.data = data;   
	       // $interval whilst we wait for the grid to digest the data we just gave it
	       $interval( function() {$scope.gridApi.selection.selectRow($scope.gridOptions.data[0]);}, 0, 1);  
	       $log.log("Successfully retrieved!");	       
	   },function(error){
		   $log.log("Error Occurred")
	   });
	 
}]);

// can not inject $scope to factory or service !!!! spend hour to figure out, $scope is only in controller
app.factory('dmarcFactory', ['$http','$log','$q',function($http,$log,$q) {
	$log.log("Initialize dmarcFactory....");
	oDmarcFactory={};
	 
	oDmarcFactory.fetchAllData=function(myUrl) {   // cb here can be any name as callback function
		var q = $q.defer();
		$http ({
			url:     myUrl,
			method: 'GET'
		}).then (function(resp){	// delay to response by callback 		
			var data = resp.data;
			q.resolve(data);
			$log.log("Successfully retrieved Data="+data);
		},function(resp) {
			q.reject('Error occurred!');
			$log.error("Error occurred!");
		});
		return q.promise;
	};
	return oDmarcFactory;
}]);
