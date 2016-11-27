var app = angular.module('sportnetworkApp');

app.factory("EventFactory", function($http, $q) {
	var factory = {};
	
	factory.browse = function(type) {
		var deferred = $q.defer();
		$http.get("api/sportevent/browse/?type=" + type).success(function(data, status) {
			deferred.resolve(data);
		}).error(function(data, status) {
			deferred.reject(data);
		});
		return deferred.promise;
	}
	
	factory.cancelEvent = function(id) {
		var deferred = $q.defer();
		$http.delete("api/sportevent/cancel/" + id).success(function(data, status) {
			deferred.resolve(data);
		}).error(function(data, status) {
			deferred.reject(data);
		});
		return deferred.promise;
	}
	
    factory.closeEvent = function(id) {
    	var deferred = $q.defer();
		$http.put("api/sportevent/close/" + id).success(function(data, status) {
			deferred.resolve(data);
		}).error(function(data, status) {
			deferred.reject(data);
		});
		return deferred.promise;
	}
	
	factory.createEvent = function(event) {
		var deferred = $q.defer();
		$http.post("api/sportevent/create", event).success(function(data, status) {
			deferred.resolve(data);
		}).error(function(data, status) {
			deferred.reject(data);
		});
		return deferred.promise;
	}
	
	factory.deleteComment = function(id) {
		var deferred = $q.defer();
		$http.delete("api/sportevent/delcomment/" + id).success(function(data, status) {
			deferred.resolve(data);
		}).error(function(data, status) {
			deferred.reject(data);
		});
		return deferred.promise;
	}
	
    factory.deleteEvent = function(id) {
    	var deferred = $q.defer();
		$http.delete("api/sportevent/delete/" + id).success(function(data, status) {
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
	
    factory.joinEvent = function(id) {
    	var deferred = $q.defer();
		$http.post("api/sportevent/apply/" + id).success(function(data, status) {
			deferred.resolve(data);
		}).error(function(data, status) {
			deferred.reject(data);
		});
		return deferred.promise;
	}
    
    factory.leaveEvent = function(id) {
    	var deferred = $q.defer();
		$http.delete("api/sportevent/leave/" + id).success(function(data, status) {
			deferred.resolve(data);
		}).error(function(data, status) {
			deferred.reject(data);
		});
		return deferred.promise;
	}

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