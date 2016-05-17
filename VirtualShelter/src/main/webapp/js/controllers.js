var app = angular.module('vshelterApp', []);

app.controller('UserController', function($scope, $http) {
	$scope.getUsers = function() {
		$http.get('api/user').success(function(data) {
			$scope.users = data;
		});
	};

	$scope.postUser = function() {
		if ($scope.user) {
			$http.post('api/user', $scope.user)
					.success(function() {
						$scope.getUsers();
						$scope.user = null;
					});
		}
	};

	$scope.getUsers();
});