var app = angular.module('sportnetworkApp');

app.controller('EventController', function($scope, $routeParams, EventFactory) {

	$scope.getSingleEvent = function(id) {
		EventFactory.getSingleEvent(id).then(function (data) {
            $scope.event = data;
        }, function (error) {
            alert(error);
        });
	}
	
	$scope.getSingleEvent($routeParams.id);

});