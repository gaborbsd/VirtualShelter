var app = angular.module('vshelterApp', []);

app.controller('AnimalController', function($scope, $http) {
	$scope.getAnimals = function() {
		$http.get('api/animal').success(function(data) {
			$scope.animals = data;
		});
	};

	$scope.postAnimal = function() {
		if ($scope.animal) {
			$http.post('api/animal', $scope.animal)
					.success(function() {
						$scope.getAnimals();
						$scope.animal = null;
					});
		}
	};

	$scope.getAnimals();
});