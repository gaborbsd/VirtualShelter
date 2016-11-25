var app = angular.module('sportnetworkApp');

app.controller('RateController', function($scope, $routeParams, NotificationFactory) {

	$scope.getMembersToRate = function() {
		NotificationFactory.getMembersToRate($routeParams.id).then(function (data) {
            $scope.object = data;
        }, function (error) {
            alert(error);
        });
	};
	
	$scope.rate = function() {
		var arr = [];
		
		for (var i=0; i<$scope.object.users.length; i++) {
			if ($scope.object.users[i].rate) {
				var a = {
					name: $scope.object.users[i].name,
					rate: parseInt($scope.object.users[i].rate)
				}
				arr.push(a);
			}			
		}
		
		var arg = {
			notificationId: Number($routeParams.id),
			rates: arr
		}
		
		console.log(JSON.stringify(arg));
		
		NotificationFactory.rate(arg).then(function (data) {
            alert("Rates saved");
            window.location.href = "#/listevents";
        }, function (error) {
            alert(error);
        });
	}
	

	
	$scope.getMembersToRate();

});