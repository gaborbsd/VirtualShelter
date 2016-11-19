var app = angular.module('sportnetworkApp');

app.controller('SportEventListController', function($scope, EventFactory) {

	$scope.getPublicEvents = function() {
		EventFactory.getPublicEvents().then(function (data) {
            $scope.events = data;
        }, function (error) {
            alert(error);
        });
	};
	
	$scope.getPublicEvents();
});