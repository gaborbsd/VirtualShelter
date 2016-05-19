var app = angular.module('sportnetworkApp', ['ngRoute']);

app.config(function($routeProvider) {
	$routeProvider.when('/user/:id', {
		controller : 'UserController',
		templateUrl : '/html/users.html'
	}).when('/editor/events', {
		controller : 'SportEventController',
		templateUrl : '/html/events.html'
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