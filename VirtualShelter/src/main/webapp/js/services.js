app.factory('SearchService', SearchService)

SearchService.$inject = ['$http', '$q'];
function SearchService($http, $q){
	
	var service = {
		get: getFinalizedRFxListFormHeaderData
	};
	
	return service;
		
	function getFinalizedRFxListFormHeaderData() {
		var deferred = $q.defer();
		var uri = jsRoutes.controllers.FinalizedRFxCtrl.getFinalizedRFxListFormHeaderData();
		$http.get(uri.url)
			.success(function(data, status) {
				deferred.resolve(data);
			})
			.error(function(data, status){
				deferred.reject(status);
			});
		return deferred.promise;
	}
}