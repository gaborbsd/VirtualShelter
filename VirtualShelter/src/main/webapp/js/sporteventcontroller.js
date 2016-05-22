var app = angular.module('sportnetworkApp', ['ngRoute']);

app.config(function($routeProvider) {
	$routeProvider.when('/user/:id', {
		controller : 'UserController',
		templateUrl : '/html/users.html'
	}).when('/editor/events', {
		controller : 'SportEventController',
		templateUrl : '/html/events.html'
	}).when('/user', {
		controller : 'UserEditorController',
		templateUrl : '/html/users.html'
	}).when('/editor/events/new', {
		controller : 'CreateSportEventController',
		templateUrl : '/html/createEvent.html'
	}).otherwise({
		redirectTo : '/editor/events'
	})
})

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

app.controller('SportEventController', function($scope, $http) {

	$scope.getEvents = function() {
		$http.get("api/sportevent").success(function(data, status) {
			$scope.events = data;
		}).error(function(data, status) {
			alert(status);
		});
	}
	
	$scope.getEvents();
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

