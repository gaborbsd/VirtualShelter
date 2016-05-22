(function () {

    angular.module('vshelterApp')
        .factory('UserService', UserService);

    UserService.$inject = ['$http', '$q'];
    function UserService($http, $q) {

        var service = {
            saveUser: saveUser,
            getUser: getUser,
            getUsers: getUsers,
            updateUser: updateUser,
            deleteUser: deleteUser,
            getCurrentUser: getCurrentUser,
            getAdministeredShelters: getAdministeredShelters
        };

        return service;

        function saveUser(user) {
            var deferred = $q.defer();
            $http.post("api/user", user).success(function (data, status) {
                deferred.resolve(data);
            }).error(function (status) {
                deferred.reject(status);
            });
            return deferred.promise;
        }

        function getUser(id) {
            var deferred = $q.defer();
            $http.get("api/user/" + id).success(function (data, status) {
                deferred.resolve(data);
            }).error(function (data, status) {
                deferred.reject(status);
            });
            return deferred.promise;
        }

        function getUsers() {
            var deferred = $q.defer();
            $http.get("api/user").success(function (data, status) {
                deferred.resolve(data);
            }).error(function (data, status) {
                deferred.reject(status);
            });
            return deferred.promise;
        }

        function updateUser(user) {
            user.id = user.userId;
            //must delete, because the PUT would not work
            delete user['links'];
            delete user['userId'];
            delete user['password-re'];
            var deferred = $q.defer();
            $http.put("api/user/" + user.id, user).success(function (data, status) {
                deferred.resolve(data);
            }).error(function (data, status) {
                deferred.reject(status);
            });
            return deferred.promise;

        }

        function getCurrentUser() {
            var deferred = $q.defer();
            $http.get("api/user/username").success(function (data, status) {
                deferred.resolve(data);
            }).error(function (status) {
                deferred.reject(status);
            });
            return deferred.promise;
        }

        function deleteUser(id) {
            var deferred = $q.defer();
            $http.delete("api/user/" + id).success(function (data, status) {
                deferred.resolve(data);
            }).error(function (status) {
                deferred.reject(status);
            });
            return deferred.promise;
        }

        function getAdministeredShelters() {
            var deferred = $q.defer();
            $http.get("api/user/administeredShelter").success(function (data, status) {
                deferred.resolve(data);
            }).error(function (status) {
                deferred.reject(status);
            });
            return deferred.promise;
        }

    }

})();