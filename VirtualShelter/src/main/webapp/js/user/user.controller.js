var app = angular.module('sportnetworkApp');

app.controller('UserController', function($scope, $routeParams, UserFactory) {

	$scope.acceptFriend = function() {
		UserFactory.acceptFriend($routeParams.id).then(function (data) {
			alert("Request accepted");
			$scope.user = data;
        }, function (error) {
            alert(error);
        });
	};
	
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