var app = angular.module('adminApp');

app.controller('CommentAdminController', function($scope, $routeParams, $http) {
		
	$scope.getAll = function() {
		$http({
			method: 'GET',
			url: 'http://localhost:8080/api/admin/comments/' + $routeParams.id
		}).then(function successCallback(data) {			  
			  $scope.comments = data.data;
		  }, function errorCallback(error) {
			  alert(error);
		  });
	}
	
	$scope.del = function(id) {
		var confirmed = confirm("Are you sure you want to delete this comment?");
		if (confirmed) {
			$http({
				method: 'DELETE',
				url: 'http://localhost:8080/api/sportevent/delcomment/' + id
			}).then(function successCallback(data) {			  
				  alert("Comment deleted")
			  }, function errorCallback(error) {
				  alert(error);
			  });
		}		
	}
	
	$scope.getAll();
});