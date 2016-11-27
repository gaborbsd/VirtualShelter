var app = angular.module('adminApp');

app.controller('UserAdminController', function($scope, $http) {
	
	$scope.search = function() {
		if (!$scope.text) {
			alert("type something");
		} else {
			$http({
				method: 'POST',
				url: 'http://localhost:8080/api/user/search',
				headers: {
					'Content-Type': 'application/json'
				},
				data: {
					value: $scope.text
				}
			}).then(function successCallback(data) {			  
				  $scope.users = data.data;
			  }, function errorCallback(error) {
				  alert(error);
			  });
		}		
	}
	
	$scope.warn = function(name, m) {
		if (!m) {
			alert("write warning message");
		} else {
			$http({
				method: 'PUT',
				url: 'http://localhost:8080/api/admin/warn',
				headers: {
					'Content-Type': 'application/json'
				},
				data: {
					to: name,
					message: m
				}
			}).then(function successCallback(data) {			  
				  alert("User warned")
			  }, function errorCallback(error) {
				  alert(error);
			  });
		}		
	}
	
	$scope.del = function(id) {
		var confirmed = confirm("Are you sure you want to delete this user?");
		if (confirmed) {
			$http({
				method: 'DELETE',
				url: 'http://localhost:8080/api/admin/delete/user/' + id
			}).then(function successCallback(data) {			  
				  alert("User deleted")
			  }, function errorCallback(error) {
				  alert(error);
			  });
		}		
	}

});