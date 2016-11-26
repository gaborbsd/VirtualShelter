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
	
	$scope.cancelRequest = function() {
		var arg = {
				to: $scope.user.name
			}
		UserFactory.cancelRequest(arg).then(function (data) {
				$scope.user = data;
	        }, function (error) {
	            alert(error);
	    });
	};
	
	$scope.declineRequest = function() {
		var arg = {
				to: $scope.user.name
			}
		UserFactory.declineRequest(arg).then(function (data) {
				$scope.user = data;
	        }, function (error) {
	            alert(error);
	    });
	};
	
	$scope.deleteFriend = function() {
		UserFactory.deleteFriend($routeParams.id).then(function (data) {
				$scope.user = data;
	        }, function (error) {
	            alert(error);
	    });
	};
	
	$scope.edit = function() {
		window.location.href = "#/edit";
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
	
	$scope.sendRequest = function() {
		var arg = {
			to: $scope.user.name
		}
		UserFactory.sendRequest(arg).then(function (data) {
			$scope.user = data;
        }, function (error) {
            alert(error);
        });
	};
	
	$scope.getUser();
});