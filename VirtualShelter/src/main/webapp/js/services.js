app.factory('SearchService', SearchService)

SearchService.$inject = ['$http', '$q'];
function SearchService($http, $q){
	
	var service = {
		getAnimals: getAnimals
	};
	
	return service;
		
	function getAnimals() {
		var deferred = $q.defer();
		$http.get("api/animal")
			.success(function(data, status) {
				deferred.resolve(data);
			})
			.error(function(data, status){
				deferred.reject(status);
			});
		return deferred.promise;
	}
}


