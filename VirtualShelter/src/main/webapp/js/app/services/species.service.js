(function () {

    angular.module('vshelterApp')
        .factory('SpeciesAndBreedsService', SpeciesAndBreedsService);

    SpeciesAndBreedsService.$inject = ['$http', '$q'];
    function SpeciesAndBreedsService($http, $q) {

        var service = {
            getSpecies: getSpecies,
            getSpeciesId: getSpeciesId,
            saveSpecies: saveSpecies,
            deleteSpecies: deleteSpecies,
            saveBreed: saveBreed,
            deleteBreed: deleteBreed
        };

        return service;

        function getSpecies() {
            var deferred = $q.defer();
            $http.get("api/species").success(function (data, status) {
                deferred.resolve(data);
            }).error(function (data, status) {
                deferred.reject(status);
            });
            return deferred.promise;
        }

        function getSpeciesId(id) {
            var deferred = $q.defer();
            $http.get("api/species/" + id).success(function (data, status) {
                deferred.resolve(data);
            }).error(function (data, status) {
                deferred.reject(status);
            });
            return deferred.promise;
        }

        function saveSpecies(species) {
            var deferred = $q.defer();
            $http.post("api/species", species).success(function (data, status) {
                deferred.resolve(data);
            }).error(function (status) {
                deferred.reject(status);
            });
            return deferred.promise;
        }

        function deleteSpecies(id) {
            var deferred = $q.defer();
            $http.delete("api/species/" + id).success(function (data, status) {
                deferred.resolve(data);
            }).error(function (status) {
                deferred.reject(status);
            });
            return deferred.promise;
        }

        function saveBreed(breed, species) {
            var deferred = $q.defer();
            $http.post("api/species/" + species.speciesId + "/breed", breed).success(function (data, status) {
                deferred.resolve(data);
            }).error(function (status) {
                deferred.reject(status);
            });
            return deferred.promise;
        }

        function deleteBreed(breed, species) {
            var deferred = $q.defer();
            $http.delete("api/species/" + species.speciesId + "/breed", breed).success(function (data, status) {
                deferred.resolve(data);
            }).error(function (status) {
                deferred.reject(status);
            });
            return deferred.promise;
        }

    }

})();