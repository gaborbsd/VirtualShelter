(function () {

    angular.module('vshelterApp')
        .factory('ShelterService', ShelterService);

    ShelterService.$inject = ['$http', '$q'];
    function ShelterService($http, $q) {

        var service = {
            saveShelter: saveShelter,
            getShelter: getShelter,
            getShelters: getShelters,
            updateShelter: updateShelter,
            deleteShelter: deleteShelter
        };

        return service;

        function saveShelter(shelter) {
            delete shelter['id'];
            var deferred = $q.defer();
            $http.post("api/shelter", shelter).success(function (data, status) {
                deferred.resolve(data);
            }).error(function (reason) {
                deferred.reject(reason);
            });
            return deferred.promise;
        }


        function getShelter(id) {
            var deferred = $q.defer();
            $http.get("api/shelter/" + id).success(function (data, status) {
                deferred.resolve(data);
            }).error(function (data, status) {
                deferred.reject(status);
            });
            return deferred.promise;
        }

        function getShelters() {
            var deferred = $q.defer();
            $http.get("api/shelter").success(function (data, status) {
                deferred.resolve(data);
            }).error(function (data, status) {
                deferred.reject(status);
            });
            return deferred.promise;
        }

        function updateShelter(shelter) {
            shelter.id = shelter.institutionId;
            //must delete, because the PUT would not work
            delete shelter['links'];
            delete shelter['institutionId'];
            var deferred = $q.defer();
            $http.put("api/shelter/" + shelter.id, shelter).success(function (data, status) {
                deferred.resolve(data);
            }).error(function (data, status) {
                deferred.reject(status);
            });
            return deferred.promise;

        }


        function deleteShelter(id) {
            var deferred = $q.defer();
            $http.delete("api/shelter/" + id).success(function (data, status) {
                deferred.resolve(data);
            }).error(function (status) {
                deferred.reject(status);
            });
            return deferred.promise;
        }

    }

})();