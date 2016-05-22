(function () {

    angular.module('vshelterApp')
        .factory('AdvertisementService', AdvertisementService);

    AdvertisementService.$inject = ['$http', '$q'];
    function AdvertisementService($http, $q) {

        var service = {
            getAdvertisements: getAdvertisements,
            deleteAdvertisement: deleteAdvertisement,
            getAnimalsForCurrentUser: getAnimalsForCurrentUser,
            saveAdvertisement: saveAdvertisement
        };

        return service;

        function getAdvertisements() {
            var deferred = $q.defer();
            $http.get("api/advertisement").success(function (data, status) {
                deferred.resolve(data);
            }).error(function (data, status) {
                deferred.reject(status);
            });
            return deferred.promise;
        }

        function deleteAdvertisement(id) {
            var deferred = $q.defer();
            $http.delete("api/advertisement/" + id).success(function (data, status) {
                deferred.resolve(data);
            }).error(function (status) {
                deferred.reject(status);
            });
            return deferred.promise;
        }

        function getAnimalsForCurrentUser() {
            var deferred = $q.defer();
            $http.get("api/advertisement/animals").success(function (data, status) {
                deferred.resolve(data);
            }).error(function (data, status) {
                deferred.reject(status);
            });
            return deferred.promise;
        }

        function saveAdvertisement(adv) {
            var deferred = $q.defer();
            $http.post("api/advertisement", adv).success(function (data, status) {
                deferred.resolve(data);
            }).error(function (data, status) {
                deferred.reject(status);
            });
            return deferred.promise;
        }

    }

})();