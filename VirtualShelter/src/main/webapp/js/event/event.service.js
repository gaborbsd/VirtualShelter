var app = angular.module('sportnetworkApp');

app.factory("EventFactory", function($http, $q) {
	var factory = {};
	
	factory.createEvent = function(event) {
		var deferred = $q.defer();
		$http.post("api/sportevent/create", event).success(function(data, status) {
			deferred.resolve(data);
		}).error(function(data, status) {
			deferred.reject(data);
		});
		return deferred.promise;
	}
	
	factory.getPublicEvents = function() {
		var deferred = $q.defer();
		$http.get("api/sportevent/public").success(function(data, status) {
			deferred.resolve(data);
		}).error(function(data, status) {
			deferred.reject(data);
		});
		return deferred.promise;
	};

	factory.pollSingleEvent = function(id, callback, error) {
		return $http.get("api/sportevent/" + id).then(function (response) {
            if (typeof response.data === 'object') {
                callback(response.data);
            } else {
                error(response.data);
            }
		});
	};
	
	factory.searchPublicEvents = function(cond) {
		var deferred = $q.defer();
		$http.post("api/sportevent/public/search", cond).success(function(data, status) {
			deferred.resolve(data);
		}).error(function(data, status) {
			deferred.reject(data);
		});
		return deferred.promise;
	}
	
	factory.writeComment = function(arg) {
		var deferred = $q.defer();
		$http.post("api/sportevent/write/", arg).success(function(data, status) {
			deferred.resolve(data);
		}).error(function(data, status) {
			deferred.reject(data);
		});
		return deferred.promise;
	}
	
	return factory;
});