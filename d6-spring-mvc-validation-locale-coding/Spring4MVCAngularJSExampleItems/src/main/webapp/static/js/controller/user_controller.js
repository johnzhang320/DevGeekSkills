/**
 * The Deferred API

	A new instance of deferred is constructed by calling $q.defer().
	
	The purpose of the deferred object is to expose the associated Promise instance as 
	well as APIs that can be used for signaling the successful or unsuccessful completion, 
	as well as the status of the task.

Methods

    resolve(value) – resolves the derived promise with the value. If the value is a rejection
     constructed via $q.reject, the promise will be rejected instead.
    reject(reason) – rejects the derived promise with the reason. This is equivalent to 
    resolving it with a rejection constructed via $q.reject.
    notify(value) - provides updates on the status of the promise's execution. This may 
    be called multiple times before the promise is either resolved or rejected.

	Properties
	
	    promise – {Promise} – promise object associated with this deferred.
	
	The Promise API
	
	A new promise instance is created when a deferred instance is created and can be retrieved 
	by calling deferred.promise.
	
	The purpose of the promise object is to allow for interested parties to get access to the 
	result of the deferred task when it completes.
	
	Methods
	
	    then(successCallback, [errorCallback], [notifyCallback]) – regardless of when the 
	    promise was or will be resolved or rejected, then calls one of the success or error 
	    callbacks asynchronously as soon as the result is available. The callbacks are called 
	    with a single argument: the result or rejection reason. Additionally, the notify 
	    callback may be called zero or more times to provide a progress indication, before 
	    the promise is resolved or rejected.
	
	    This method returns a new promise which is resolved or rejected via the return value
	    of the successCallback, errorCallback (unless that value is a promise, in which case 
	    it is resolved with the value which is resolved in that promise using promise chaining).
	    It also notifies via the return value of the notifyCallback method. The promise cannot
	    be resolved or rejected from the notifyCallback method. The errorCallback and 
        notifyCallback arguments are optional.
	
	    catch(errorCallback) – shorthand for promise.then(null, errorCallback)
	
	    finally(callback, notifyCallback) – allows you to observe either the fulfillment or 
	    rejection of a promise, but to do so without modifying the final value. This is useful 
	    to release resources or do some clean-up that needs to be done whether the promise was 
	    rejected or resolved. See the full specification for more information.

 */
'use strict';

angular.module('myApp').controller('UserController', ['$scope', 'UserService', function($scope, UserService) {
    var self = this;
    self.user={id:null,username:'',address:'',email:''};
    self.users=[];

    self.submit = submit;
    self.edit = edit;
    self.remove = remove;
    self.reset = reset;


    fetchAllUsers();

    function fetchAllUsers(){
        UserService.fetchAllUsers()
            .then(
            function(d) {
                self.users = d;
            },
            function(errResponse){
                console.error('Error while fetching Users');
            }
        );
    }

    function createUser(user){
        UserService.createUser(user)
            .then(
            fetchAllUsers,
            function(errResponse){
                console.error('Error while creating User');
            }
        );
    }

    function updateUser(user, id){
        UserService.updateUser(user, id)
            .then(
            fetchAllUsers,
            function(errResponse){
                console.error('Error while updating User');
            }
        );
    }

    function deleteUser(id){
        UserService.deleteUser(id)
            .then(
            fetchAllUsers,
            function(errResponse){
                console.error('Error while deleting User');
            }
        );
    }

    function submit() {
        if(self.user.id===null){
            console.log('Saving New User', self.user);
            createUser(self.user);
        }else{
            updateUser(self.user, self.user.id);
            console.log('User updated with id ', self.user.id);
        }
        reset();
    }

    function edit(id){
        console.log('id to be edited', id);
        for(var i = 0; i < self.users.length; i++){
            if(self.users[i].id === id) {
                self.user = angular.copy(self.users[i]);
                break;
            }
        }
    }

    function remove(id){
        console.log('id to be deleted', id);
        if(self.user.id === id) {//clean form if the user to be deleted is shown there.
            reset();
        }
        deleteUser(id);
    }


    function reset(){
        self.user={id:null,username:'',address:'',email:''};
        $scope.myForm.$setPristine(); //reset Form
    }

}]);
