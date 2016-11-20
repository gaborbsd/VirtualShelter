var app = angular.module('sportnetworkApp');

app.controller('EventController', function($scope, $routeParams, $interval, EventFactory) {

	$scope.cancelEvent = function(){
		EventFactory.cancelEvent($routeParams.id).then(function (data) {
			$scope.event = data;
        }, function (error) {
            alert(error);
        });
	}
	
	$scope.closeEvent = function(){
		var confirmed = confirm("Are you sure you want to close this event?");
		if (confirmed) {
			EventFactory.closeEvent($routeParams.id).then(function (data) {
				$scope.event = data;
				alert("event closed");
	        }, function (error) {
	            alert(error);
	        });
		}
	}
	
	$scope.deleteEvent = function(){
		var confirmed = confirm("Are you sure you want to delete this event?");
		if (confirmed) {
			EventFactory.deleteEvent($routeParams.id).then(function (data) {
				alert("event deleted");
				window.location.href = "#/listevents";
	        }, function (error) {
	            alert(error);
	        });
		}
	}
	
	$scope.getSingleEvent = function(id) {
		EventFactory.pollSingleEvent(id, function(data) {
			$scope.event = data;
		}, function(error) {
			alert(error);
		});
	}
	
	$scope.joinEvent = function() {
		EventFactory.joinEvent($routeParams.id).then(function (data) {
			$scope.event = data;
        }, function (error) {
            alert(error);
        });
	}
	
	$scope.leaveEvent = function() {
		var confirmed = confirm("Are you sure you want to leave this event?");
		if (confirmed) {
			EventFactory.leaveEvent($routeParams.id).then(function (data) {
				$scope.event = data;
				alert("event left");
	        }, function (error) {
	            alert(error);
	        });
		}
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