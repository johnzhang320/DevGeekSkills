'use strict';

angular.module('dmarcApp').controller('DmarcController', ['$scope', 'DmarcService', function($scope, DmarcService) {
    var self = this;
    self.masters=[];
    self.details=[];
    self.master=[];
    self.masterId=0;
    fetchRangeMasters();
    function fetchRangeMasters(){
        DmarcService.fetchRangeMasters()
            .then(
            function(d) {
                self.masters = d;
            },
            function(errResponse){
                console.error('Error while fetching Masters');
            }
        );
    }
    function fetchSingleMasters(masterId){
        DmarcService.fetchSingleMasters(masterId)
            .then(
            function(d) {
                self.master = d;
            },
            function(errResponse){
                console.error('Error while fetching Masters');
            }
        );
    }
    function fetchDetails(masterId){
        DmarcService.fetchDetails(masterId)
            .then(
            function(d) {
                self.details = d;
            },
            function(errResponse){
                console.error('Error while fetching Details');
            }
        );
    }
  
}]);
