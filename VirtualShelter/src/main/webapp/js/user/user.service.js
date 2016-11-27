var app = angular.module('sportnetworkApp');

app.factory("UserFactory", function($http, $q) {
	var factory = {};
	
	factory.acceptFriend = function(id) {
		var deferred = $q.defer();
		$http.put("api/user/friendAccept/" + id).success(function(data, status) {
			deferred.resolve(data);
		}).error(function(data, status) {
			deferred.reject(data);
		});
		return deferred.promise;
	};
	
	factory.cancelRequest = function(arg) {
		var deferred = $q.defer();
		$http.delete("api/user/cancel", arg).success(function(data, status) {
			deferred.resolve(data);
		}).error(function(data, status) {
			deferred.reject(data);
		});
		return deferred.promise;
	};
	
	factory.declineRequest = function(arg) {
		var deferred = $q.defer();
		$http.put("api/user/decline", arg).success(function(data, status) {
			deferred.resolve(data);
		}).error(function(data, status) {
			deferred.reject(data);
		});
		return deferred.promise;
	};
	
	factory.deleteFriend = function(id) {
		var deferred = $q.defer();
		$http.delete("api/user/deleteFriend/" + id).success(function(data, status) {
			deferred.resolve(data);
		}).error(function(data, status) {
			deferred.reject(data);
		});
		return deferred.promise;
	};
	
	factory.getFriends = function() {
		var deferred = $q.defer();
		$http.get("api/user/friends").success(function(data, status) {
			deferred.resolve(data);
		}).error(function(data, status) {
			deferred.reject(data);
		});
		return deferred.promise;
	};
	
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
	
	factory.modifyUser = function(arg) {
		var deferred = $q.defer();
		$http.put("api/user/modify", arg).success(function(data, status) {
			deferred.resolve(data);
		}).error(function(data, status) {
			deferred.reject(data);
		});
		return deferred.promise;
	};
	
	factory.search = function(arg) {
		var deferred = $q.defer();
		$http.post("api/user/search", arg).success(function(data, status) {
			deferred.resolve(data);
		}).error(function(data, status) {
			deferred.reject(data);
		});
		return deferred.promise;
	};
		
	factory.sendRequest = function(arg) {
		var deferred = $q.defer();
		$http.post("api/user/friend", arg).success(function(data, status) {
			deferred.resolve(data);
		}).error(function(data, status) {
			deferred.reject(data);
		});
		return deferred.promise;
	};
	
	factory.write = function(arg) {
		var deferred = $q.defer();
		$http.post("api/messages/conversation", arg).success(function(data, status) {
			deferred.resolve(data);
		}).error(function(data, status) {
			deferred.reject(data);
		});
		return deferred.promise;
	};
	
	return factory;
});