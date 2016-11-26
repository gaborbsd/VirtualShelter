var app = angular.module('sportnetworkApp');

app.controller('UserController', function($scope, $routeParams, UserFactory) {

	$scope.getUser = function() {
		if ($routeParams.id) {
			UserFactory.getOne($routeParams.id).then(function (data) {
	            $scope.user = data;
	        }, function (error) {
	            alert(error);
	        });
		} else {
			UserFactory.getCurrent().then(function (data) {
	            $scope.user = data;
	        }, function (error) {
	            alert(error);
	        });
		}
		
	};
	
	$scope.getUser();
});