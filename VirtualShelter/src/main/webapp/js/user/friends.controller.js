var app = angular.module('sportnetworkApp');

app.controller('FriendsController', function($scope, UserFactory) {

	$scope.getFriends = function() {
		UserFactory.getFriends().then(function (data) {
            $scope.friends = data;
        }, function (error) {
            alert(error);
        });
	}
	
	$scope.getFriends();
});