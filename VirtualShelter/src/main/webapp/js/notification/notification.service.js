var app = angular.module('sportnetworkApp');

app.factory("NotificationFactory", function($http, $q) {
	var factory = {};

	factory.getAll = function() {
		var deferred = $q.defer();
		$http.get("api/notification/get").success(function(data, status) {
			deferred.resolve(data);
		}).error(function(data, status) {
			deferred.reject(data);
		});
		return deferred.promise;
	};
	
	factory.acceptFriend = function(id) {
		var deferred = $q.defer();
		$http.put("api/notification/friendAccept/" + id).success(function(data, status) {
			deferred.resolve(data);
		}).error(function(data, status) {
			deferred.reject(data);
		});
		return deferred.promise;
	};
	
	factory.acceptEvent = function(id) {
		var deferred = $q.defer();
		$http.put("api/notification/eventAccept/" + id).success(function(data, status) {
			deferred.resolve(data);
		}).error(function(data, status) {
			deferred.reject(data);
		});
		return deferred.promise;
	};
	
	factory.decline = function(id) {
		var deferred = $q.defer();
		$http.put("api/notification/decline/" + id).success(function(data, status) {
			deferred.resolve(data);
		}).error(function(data, status) {
			deferred.reject(data);
		});
		return deferred.promise;
	};
	
	factory.deleteNotification = function(id) {
		var deferred = $q.defer();
		$http.delete("api/notification/delete/" + id).success(function(data, status) {
			deferred.resolve(data);
		}).error(function(data, status) {
			deferred.reject(data);
		});
		return deferred.promise;
	};
	
	factory.getMembersToRate = function(id) {
		var deferred = $q.defer();
		$http.get("api/notification/rate/" + id).success(function(data, status) {
			deferred.resolve(data);
		}).error(function(data, status) {
			deferred.reject(data);
		});
		return deferred.promise;
	};
	
	factory.rate = function(arg) {
		var deferred = $q.defer();
		$http.post("api/notification/rate", arg).success(function(data, status) {
			deferred.resolve(data);
		}).error(function(data, status) {
			deferred.reject(data);
		});
		return deferred.promise;
	};
	
	return factory;
});