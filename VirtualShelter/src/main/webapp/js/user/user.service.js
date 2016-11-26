var app = angular.module('sportnetworkApp');

app.factory("UserFactory", function($http, $q) {
	var factory = {};
	
	factory.getOne = function(id) {
		var deferred = $q.defer();
		$http.get("api/user/" + id).success(function(data, status) {
			deferred.resolve(data);
		}).error(function(data, status) {
			deferred.reject(data);
		});
		return deferred.promise;
	};
	
	factory.getCurrent = function() {
		var deferred = $q.defer();
		$http.get("api/user/").success(function(data, status) {
			deferred.resolve(data);
		}).error(function(data, status) {
			deferred.reject(data);
		});
		return deferred.promise;
	};
	
	return factory;
});