var app = angular.module('sportnetworkApp');

app.controller('SportEventListController', function($scope, EventFactory) {

	$scope.getPublicEvents = function() {
		EventFactory.getPublicEvents().then(function (data) {
            $scope.events = data;
        }, function (error) {
            alert(error);
        });
	};
	
	$scope.search = function() {
		if (validate()) {
			var arg = {};
			
			if ($scope.text) {
				arg.text = $scope.text; 
			}
			if (!$scope.city && !$scope.sport && !$scope.owner) {
				arg.city = true;
				arg.sport = true;
				arg.owner = true;
			}
			if ($scope.city) {
				arg.city = $scope.city;
			}
			if ($scope.sport) {
				arg.sport = $scope.sport;
			}
			if ($scope.owner) {
				arg.owner = $scope.owner;
			}
			if ($scope.levelIntervalFrom != null) {
				arg.levelFrom = parseInt($scope.levelIntervalFrom);
			}
			if ($scope.levelIntervalTo != null) {
				arg.levelTo = parseInt($scope.levelIntervalTo);
			}
			
			EventFactory.searchPublicEvents(arg).then(function (data) {
	            $scope.events = data;
	        }, function (error) {
	            alert(error);
	        });
		}
	};
	
	validate = function() {
		if (!$scope.text && $scope.levelIntervalFrom == null && $scope.levelIntervalTo == null) {
			alert("Invalid conditions");
			return false;
		} else if (parseInt($scope.levelIntervalTo) < parseInt($scope.levelIntervalFrom)) {
			alert("Incorrect levelintervall" + $scope.levelIntervalFrom + "/" + $scope.levelIntervalTo);
			return false;
		}
		return true;
	}
	
	
	$scope.getPublicEvents();
});