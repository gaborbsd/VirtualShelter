var app = angular.module('sportnetworkApp');

app.controller('EventCreateController', function($scope, EventFactory) {

	$scope.createEvent = function() {
		if (validate()) {
			arg = {
				title: $scope.title,
			    type: $scope.type,
			 	maxSize: $scope.maxSize,
			    levelIntervalFrom: $scope.levelIntervalFrom,
			    levelIntervalTo: $scope.levelIntervalTo,
			    isPublic: $scope.publicity == "public" ? true : false,
			    date:  $scope.year + "-" + $scope.month + "-" + $scope.day + "T" + $scope.hour + ":00:00Z",
			    description: $scope.description,
			    address: {
			      	country: $scope.country,
			        city: $scope.city,
			        address: $scope.address 
			    }	
			}
			EventFactory.createEvent(arg).then(function (data) {
				alert("Event created");
	        }, function (error) {
	        	alert(error);
	        });
		}
	};
	
	validate = function() {
		if (!$scope.title) {
			alert("Fill title");
			return false;
		} else if ($scope.type == null) {
			alert("Choose a sport");
			return false;
		} else if ($scope.maxSize == null) {
			alert("Fill participants");
			return false;
		} else if ($scope.year == null || $scope.month == null || $scope.day == null || $scope.hour == null) {
			alert("Fill date");
			return false;
		} else if ($scope.levelIntervalFrom == null || $scope.levelIntervalTo == null) {
			alert("Fill levelintervalls");
			return false;
		} else if (parseInt($scope.levelIntervalTo) < parseInt($scope.levelIntervalFrom)) {
			alert("Incorrect levelintervall");
			return false;
		} else if (!$scope.country || !$scope.city || !$scope.address) {
			alert("Country city and address are mandantory");
			return false;
		} else if ($scope.publicity == null) {
			alert("Set publicity");
			return false;
		}
		return true;
	}

});