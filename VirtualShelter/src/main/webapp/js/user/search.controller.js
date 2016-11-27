var app = angular.module('sportnetworkApp');

app.controller('UserSearchController', function($scope, UserFactory) {

	$scope.search = function() {
		if (!$scope.text) {
			alert("type something");
		} else {
			arg = {
				value: $scope.text
			}
			UserFactory.search(arg).then(function (data) {
	            $scope.users = data;
	        }, function (error) {
	            alert(error);
	        });
		}
		
	}

});