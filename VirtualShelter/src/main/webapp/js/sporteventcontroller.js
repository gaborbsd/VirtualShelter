var app = angular.module('sportnetworkApp');

app.controller('UserController', function($scope, $http, $routeParams) {

	$scope.getUser = function(id) {
		$http.get("api/user/" + id).success(function(data, status) {
			$scope.user = data;
		}).error(function(data, status) {
			alert(data);
		});
	}
	
	$scope.getUser($routeParams.id);
});


app.controller('UserEditorController', function($scope, $http) {
	
	$scope.getUser = function() {
		$http.get("api/user").success(function(data, status) {
			$scope.user = data;
		}).error(function(data, status) {
			alert(data);
		});
	}
	
	$scope.getUser();
});

