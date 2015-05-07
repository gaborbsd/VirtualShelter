var app = angular.module('vshelterApp', ['ngRoute','bgf.paginateAnything']);

app.config(function($routeProvider) {
	$routeProvider.when('/editor/user',{
		controller: 'UserEditorController',
		templateUrl: '/html/userform.html'
	}).when('/editor/user/:id',{
		controller: 'UserEditorController',
		templateUrl: '/html/userform.html'
	}).when('/editor/animal/:id',{
		controller: 'AnimalEditorController',
		templateUrl: '/html/animalform.html'
	}).when('/editor/animal',{
		controller: 'AnimalEditorController',
		templateUrl: '/html/animalform.html'
	}).when('/editor/shelter',{
		controller: 'ShelterEditorController',
		templateUrl: '/html/shelterform.html'
	}).when('/editor/shelter/:id',{
		controller: 'ShelterEditorController',
		templateUrl: '/html/shelterform.html'
	}).when('/',{
		
	})
})

app.controller('AnimalController', function($scope, $http) {
	$scope.getAnimals = getAnimals;
	$scope.postAnimal = postAnimal;
	
	function getAnimals() {
		$http.get('api/animal').success(function(data) {
			$scope.animals = data;
		});
	};

	function postAnimal() {
		if ($scope.animal) {
			$http.post('api/animal', vm.animal)
					.success(function() {
						$scope.getAnimals();
						$scope.animal = null;
					});
		}
	};

	vm.getAnimals();
});

app.controller('HeaderController', function($scope, $http) {
	$scope.loginForm = {}
	$scope.login = login;
	
	function login(){}
	
	function register(){}
	
});

app.controller('SearchController', function($scope, $http){
	$scope.detailed = true;
	
});

app.controller('UserEditorController', function($scope, $http){
	
})

app.controller('AnimalEditorController', function($scope, $http){
	$scope.animal = {};
	$scope.animal.disabilities = false;
	$scope.animal.diseases = false;
	$scope.animal.otherCosts = false;
})

app.controller('ShelterEditorController', function($scope, $http){
	
})

