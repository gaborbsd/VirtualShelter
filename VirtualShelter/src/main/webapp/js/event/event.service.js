var app = angular.module('sportnetworkApp');

app.factory("EventFactory", function($http, $q) {
	var factory = {};
	
	factory.getPublicEvents = function() {
		var deferred = $q.defer();
		$http.get("api/sportevent/public").success(function(data, status) {
			deferred.resolve(data);
		}).error(function(data, status) {
			deferred.reject(data);
		});
		return deferred.promise;
	};
	
	factory.createEvent = function(event) {
		var deferred = $q.defer();
		$http.post("api/sportevent/create", event).success(function(data, status) {
			deferred.resolve(data);
		}).error(function(data, status) {
			deferred.reject(data);
		});
		return deferred.promise;
	}
	
	return factory;
});