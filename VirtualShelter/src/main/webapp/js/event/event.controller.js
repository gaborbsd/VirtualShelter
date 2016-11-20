var app = angular.module('sportnetworkApp');

app.controller('EventController', function($scope, $routeParams, $interval, EventFactory) {

	$scope.getSingleEvent = function(id) {
		EventFactory.pollSingleEvent(id, function(data) {
			$scope.event = data;
		}, function(error) {
			alert(error);
		});
	}
	
	$scope.writeComment = function() {
		if (validate()) {
			arg = {
				eventId: $routeParams.id,
				message: $scope.message
			};
			
			EventFactory.writeComment(arg).then(function (data) {
				$scope.event = data;
	            $scope.message = '';
	        }, function (error) {
	            alert(error);
	        });
		}
		
	}
	
	validate = function() {
		if (!$scope.message) {
			alert("write something");
			return false;
		}
		return true;
	}
	
	$scope.getSingleEvent($routeParams.id);
	
	stop = $interval(function() {
		$scope.getSingleEvent($routeParams.id);
    }, 5000);
	
	$scope.$on("$destroy", function(){
		$interval.cancel(stop);
	});

});