var app = angular.module('sportnetworkApp');

app.controller('EventBrowseController', function($scope, $routeParams, EventFactory) {
	
	$scope.browse = function(){
		EventFactory.browse($routeParams.type).then(function (data) {
			$scope.events = data;
        }, function (error) {
            alert(error);
        });
	}
	
	$scope.browse();
});