(function () {

    angular.module('vshelterApp')
        .factory('AnimalService', AnimalService);

    AnimalService.$inject = ['$http', '$q'];
    function AnimalService($http, $q) {

        var service = {
            getSpecies: getSpecies,
            saveAnimal: saveAnimal,
            getAnimal: getAnimal
        };

        return service;

        function saveAnimal(animal) {
            var deferred = $q.defer();
            $http.post("api/animal", animal).success(function (data, status) {
                deferred.resolve(data);
            }).error(function (data, status) {
                deferred.reject(status);
            });
            return deferred.promise;
        }

        function getSpecies() {
            var deferred = $q.defer();
            $http.get("api/species").success(function (data, status) {
                deferred.resolve(data);
            }).error(function (data, status) {
                deferred.reject(status);
            });
            return deferred.promise;
        }

        function getAnimal(id) {
            var deferred = $q.defer();
            $http.get("api/animal/" + id).success(function (data, status) {
                deferred.resolve(data);
            }).error(function (data, status) {
                deferred.reject(status);
            });
            return deferred.promise;
        }

    }

})();