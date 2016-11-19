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

app.controller('EventController', function($scope, $http, $routeParams) {
	
	$scope.getEvent = function(id) {
		$http.get("api/sportevent/" + id).success(function(data, status) {
			$scope.event = data;
		}).error(function(data, status) {
			alert(data);
		});
	}
	
	$scope.getEvent($routeParams.id);
});

app.controller('CreateSportEventController', function($scope, $http) {

	$scope.createEvent = function() {
		if ($scope.event)
			//$scope.event.date = "2016-" + $scope.month + "-" + $scope.day + " " + $scope.hour + ":00:00";
			$http.post("api/sportevent/create", $scope.event).success(function(data, status) {
				alert("Event created");
			}).error(function(data, status) {
				alert(data);
			});
	}

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

