var app = angular.module('sportnetworkApp', []);

app.controller('RegistrationController', function($scope, $http) {

	$scope.postUser = function() {
		if ($scope.user) {
			$http.post('api/user/register', $scope.user)
					.success(function() {
						alert("Registration succesfull");
					});
		}
	};
});