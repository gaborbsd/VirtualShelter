var app = angular.module('sportnetworkApp');

app.factory("ConversationFactory", function($http, $q) {
	var factory = {};
	
	factory.getConversations = function() {
		var deferred = $q.defer();
		$http.get("api/messages/conversations").success(function(data, status) {
			deferred.resolve(data);
		}).error(function(data, status) {
			deferred.reject(data);
		});
		return deferred.promise;
	};
	
	factory.getMessages = function(id) {
		var deferred = $q.defer();
		$http.get("api/messages/" + id).success(function(data, status) {
			deferred.resolve(data);
		}).error(function(data, status) {
			deferred.reject(data);
		});
		return deferred.promise;
	};
	
	factory.writeMessage = function(arg) {
		var deferred = $q.defer();
		$http.post("api/messages/write/", arg).success(function(data, status) {
			deferred.resolve(data);
		}).error(function(data, status) {
			deferred.reject(data);
		});
		return deferred.promise;
	}
	
	return factory;
});