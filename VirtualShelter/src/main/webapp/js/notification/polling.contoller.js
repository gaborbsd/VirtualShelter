var app = angular.module('sportnetworkApp');

app.controller('PollingController', function($scope, $http, $interval) {

	$scope.poll = function() {
		$http.get("api/notification/poll").success(function(data, status) {
			$scope.not = data;
		}).error(function(data, status) {
			alert(data);
		});
	}
		
	stop = $interval(function() {
		$scope.poll();
    }, 2000);
	
	$scope.$on("$destroy", function(){
		$interval.cancel(stop);
	});
});