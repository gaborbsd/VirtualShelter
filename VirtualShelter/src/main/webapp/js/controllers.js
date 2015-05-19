var app = angular.module('vshelterApp', [ 'ngRoute', 'bgf.paginateAnything' ]);

app.config(function($routeProvider) {
	$routeProvider.when('/editor/user', {
		controller : 'UserEditorController',
		templateUrl : '/html/userform.html'
	}).when('/editor/user/:id', {
		controller : 'UserEditorController',
		templateUrl : '/html/userform.html'
	}).when('/editor/animal/:id', {
		controller : 'AnimalEditorController',
		templateUrl : '/html/animalform.html'
	}).when('/editor/animal', {
		controller : 'AnimalEditorController',
		templateUrl : '/html/animalform.html'
	}).when('/editor/shelter', {
		controller : 'ShelterEditorController',
		templateUrl : '/html/shelterform.html'
	}).when('/editor/shelter/:id', {
		controller : 'ShelterEditorController',
		templateUrl : '/html/shelterform.html'
	}).when('/search', {
		controller : 'SearchController',
		templateUrl : '/html/search.html'
	}).otherwise({
		redirectTo : '/search'
	})
})

app.factory('SearchService', SearchService)
app.factory('AnimalService', AnimalService)
app.factory('ShelterService', ShelterService)
app.factory('UserService', UserService)

AnimalService.$inject = [ '$http', '$q' ];
function AnimalService($http, $q) {

	var service = {
		getSpecies : getSpecies,
		saveAnimal : saveAnimal,
		getAnimal : getAnimal
	};

	return service;

	function saveAnimal(animal) {
		var deferred = $q.defer();
		$http.post("api/animal", animal).success(function(data, status) {
			deferred.resolve(data);
		}).error(function(reason) {
			deferred.reject(reason);
		});
		return deferred.promise;
	}

	function getSpecies() {
		var deferred = $q.defer();
		$http.get("api/species").success(function(data, status) {
			deferred.resolve(data);
		}).error(function(data, status) {
			deferred.reject(status);
		});
		return deferred.promise;
	}
	
	function getAnimal(id) {
		var deferred = $q.defer();
		$http.get("api/animal/"+id).success(function(data, status) {
			deferred.resolve(data);
		}).error(function(data, status) {
			deferred.reject(status);
		});
		return deferred.promise;
	}
}

SearchService.$inject = [ '$http', '$q' ];
function SearchService($http, $q) {

	var service = {
		getAnimals : getAnimals
	};

	return service;

	function getAnimals() {
		var deferred = $q.defer();
		$http.get("api/animal").success(function(data, status) {
			deferred.resolve(data);
		}).error(function(data, status) {
			deferred.reject(status);
		});
		return deferred.promise;
	}
}

ShelterService.$inject = [ '$http', '$q' ];
function ShelterService($http, $q) {

	var service = {
		saveShelter : saveShelter
	};

	return service;

	function saveShelter(shelter) {
		var deferred = $q.defer();
		$http.post("api/shelter", shelter).success(function(data, status) {
			deferred.resolve(data);
		}).error(function(reason) {
			deferred.reject(reason);
		});
		return deferred.promise;
	}

}

UserService.$inject = [ '$http', '$q' ];
function UserService($http, $q) {

	var service = {
		saveUser : saveUser,
		getUser : getUser
	};

	return service;

	function saveUser(user) {
		var deferred = $q.defer();
		$http.post("api/user", shelter).success(function(data, status) {
			deferred.resolve(data);
		}).error(function(reason) {
			deferred.reject(reason);
		});
		return deferred.promise;
	}
	
	function getUser(id) {
		var deferred = $q.defer();
		$http.get("api/user/"+id).success(function(data, status) {
			deferred.resolve(data);
		}).error(function(data, status) {
			deferred.reject(status);
		});
		return deferred.promise;
	}

}



var controllers = {};

controllers.HeaderController = function($scope, $http) {
	$scope.loginForm = {}
	$scope.login = login;
	$scope.loginForm.email = "sajt";
	function login() {

	}

	function register() {
		location.href = "#/editor/user"
	}

};

controllers.SearchController = function($scope, $http, SearchService) {
	$scope.clientLimit;
	$scope.animals;
	$scope.page;
	$scope.perPage
	$scope.url = 'api/animal'
	$scope.urlParams;

	$scope.detailed = false;
	$scope.getAnimals = function getAnimals() {
		$http.get('api/animal').success(function(data) {
			$scope.animals = data;
		});
	};

};

controllers.UserEditorController = function($scope, $http, $routeParams, UserService) {
	$scope.user = {}

	$scope.user.id = $routeParams.id ? $routeParams.id : -1;
	$scope.saveUser = function(){
		UserService.saveUser($scope.animal).then(function() {
			location.href = "/";
		}, function(response) {
			$scope.error = response;
		})
	}
	
	if ($scope.user.id > 0) {
		UserService.getUser($scope.user.id).then(function(data) {
			$scope.user = data;
		}, function(response) {
			$scope.error = response;
		})
	}
};

controllers.AnimalEditorController = function($scope, $http, AnimalService,	$routeParams) {
	$scope.animal = {};
	$scope.animal.disabilities = false;
	$scope.animal.diseases = false;
	$scope.animal.otherCosts = false;
	$scope.error = "";
	$scope.animalCoreData = {};
	$scope.animal.id = $routeParams.id ? $routeParams.id : -1;
	
	
	$scope.saveAnimal = function() {
		if ($scope.animal.disabilities) {
			$scope.animal.disabilities = $scope.animal.disabilitiesDesc;
		}
		if ($scope.animal.diseases) {
			$scope.animal.diseases = $scope.animal.diseasesDesc;
		}
		if ($scope.animal.otherCosts) {
			$scope.animal.otherCosts = $scope.animal.otherCostsDesc;
		}
		AnimalService.saveAnimal($scope.animal).then(function() {
			location.href = "/";
		}, function(response) {
			$scope.error = response;
		})
	}

	$scope.getSpecies = function() {
		AnimalService.getSpecies().then(function(data) {
			$scope.animalCoreData.species = data;
		}, function(response) {
			$scope.error = response;
		})
	}

	if ($scope.animal.id > 0) {
		AnimalService.getAnimal($scope.animal.id).then(function(data) {
			$scope.animal = data;
		}, function(response) {
			$scope.error = response;
		})
	}

	$scope.getSpecies();
};

controllers.ShelterEditorController = function($scope, $http) {
	$scope.shelter = {};

	$scope.saveShelter = function() {

		ShelterService.saveShelter($scope.shelter).then(function() {
			location.href = "/";
		}, function(response) {
			$scope.error = response;
		})
	}
};

app.controller(controllers)

app.directive('fileUpload', FileUploadDirective)

function FileUploadDirective() {
	return {
		restrict : 'A',
		scope : true,
		link : function(scope, el, attrs) {
			el.bind('change', function(event) {
				var files = event.target.files;
				for (var i = 0; i < files.length; i++) {
					scope.$emit("fileSelected", {
						file : files[i]
					});
				}
			});
		}
	};
}