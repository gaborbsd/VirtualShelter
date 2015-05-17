var app = angular.module('vshelterApp', ['ngRoute','bgf.paginateAnything','']);

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
	}).when('/search/',{
		controller: 'SearchController',
		templateUrl: '/html/search.html'
	}).otherwise({ redirectTo: '/search/'	})
})

app.config(function ($httpProvider) {
        var httpInterceptor = ['$rootScope', '$q', function (scope, $q) {
            function success(response) {
                return response;
            }

            function error(response) {
                var status = response.status;
                if (status == 401) {
                    location.href = jsRoutes.controllers.BaseCtrl.sessionExpired().url;
                }
                return $q.reject(response);
            }

            return function (promise) {
                return promise.then(success, error);
            }
        }];
        $httpProvider.responseInterceptors.push(httpInterceptor);
    })

    var controllers = {};
controllers.AnimalController=function($scope, $http) {
	$scope.getAnimals = getAnimals;
	$scope.postAnimal = postAnimal;

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
};

controllers.HeaderController=function($scope, $http) {
	$scope.loginForm = {}
	$scope.login = login;
	
	function login(){}
	
	function register(){}
	
};

controllers.SearchController = function($scope, $http){
	$scope.detailed = false;
	function getAnimals() {
		$http.get('api/animal').success(function(data) {
			$scope.animals = data;
		});
	};
	
};

controllers.UserEditorController = function($scope, $http){
	
};

controllers.AnimalEditorController = function($scope, $http){
	$scope.animal = {};
	$scope.animal.disabilities = false;
	$scope.animal.diseases = false;
	$scope.animal.otherCosts = false;
};

controllers.ShelterEditorController = function($scope, $http){
	
};

app.controller(controllers)