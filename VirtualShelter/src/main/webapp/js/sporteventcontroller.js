var app = angular.module('sportnetworkApp', []);

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