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
	
	return factory;
});