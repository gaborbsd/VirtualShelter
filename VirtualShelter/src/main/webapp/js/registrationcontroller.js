var app = angular.module('sportnetworkApp', []);

app.controller('RegistrationController', function($scope, $http) {

	$scope.postUser = function() {
		if (validate()) {
			$http.post('api/user/register', $scope.user).success(function() {
				alert("Registration succesfull");
			}).error(function(data, status) {
				alert(data);
			});
		}
	};
	
	validate = function() {
		var re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
		if (!$scope.user) {
			alert("fill name");
			return false;
		} else if (!$scope.user.name) {
			alert("fill name");
			return false;
		} else if (!$scope.user.email) {
			alert("fill email");
			return false;
		} else if (!re.test($scope.user.email)) {
			alert("invalid email format");
			return false;
		} else if (!$scope.user.password) {
			alert("fill password");
			return false;
		} else if (!$scope.pw) {
			alert("confirm password");
			return false;
		} else if ($scope.user.password != $scope.pw) {
			alert("passwords do not match");
			return false;
		} else if (!$scope.user.age) {
			alert("fill age");
			return false;
		} else if (!$scope.user.address) {
			alert("fill country");
			return false;
		} else if (!$scope.user.address.country) {
			alert("fill country");
			return false;
		} else if (!$scope.user.address.city) {
			alert("fill city");
			return false;
		}
		return true;
	}
});