var app = angular.module('sportnetworkApp');

app.controller('UserEditorController', function($scope, UserFactory) {

	
	$scope.getUser = function() {
		UserFactory.getCurrent().then(function (data) {
            $scope.user = data;
            cutScope();
        }, function (error) {
            alert(error);
        });		
	};
	
	$scope.modifyUser = function() {
		if (validate()) {
			UserFactory.modifyUser($scope.user).then(function (data) {
				alert("data saved");
	            window.location.href = "#/user";
	        }, function (error) {
	            alert(error);
	        });	
		}		
	};
	
	validate = function() {
		var re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
		if (!$scope.user.email) {
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
	
	cutScope = function() {
		delete $scope.user.status;
		delete $scope.user.warning;
		delete $scope.user.links;
		delete $scope.user.interest;
		delete $scope.user.deleted;
		delete $scope.pw;
	}
		
	$scope.getUser();
});