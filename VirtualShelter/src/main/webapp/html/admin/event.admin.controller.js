var app = angular.module('adminApp');

app.controller('EventAdminController', function($scope, $http) {
	
	$scope.search = function() {
		if (!$scope.text) {
			alert("type something");
		} else {
			$http({
				method: 'POST',
				url: 'http://localhost:8080/api/admin/events',
				headers: {
					'Content-Type': 'application/json'
				},
				data: {
					value: $scope.text
				}
			}).then(function successCallback(data) {			  
				  $scope.events = data.data;
			  }, function errorCallback(error) {
				  alert(error);
			  });
		}		
	}
	
	
	$scope.del = function(id) {
		var confirmed = confirm("Are you sure you want to delete this event?");
		if (confirmed) {
			$http({
				method: 'DELETE',
				url: 'http://localhost:8080/api/sportevent/delete/' + id
			}).then(function successCallback(data) {			  
				  alert("Event deleted")
			  }, function errorCallback(error) {
				  alert(error);
			  });
		}		
	}
});