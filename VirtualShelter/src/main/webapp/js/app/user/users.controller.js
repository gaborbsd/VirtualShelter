(function () {

    angular.module('vshelterApp')
        .controller('UsersController', UsersController);

    UsersController.$inject = ['$scope', 'UserService'];

    function UsersController($scope, UserService) {
        $scope.users = [];
        $scope.error = "";
        $scope.getUsers = function () {
            UserService.getUsers($scope.user).then(function (data) {
                $scope.users = data;
            }, function (response) {
                $scope.error = response;
            })
        }();

        $scope.deleteUser = function (id) {
            $scope.id = id;
            UserService.deleteUser($scope.id).then(function () {
                $scope.getUsers = function () {
                    UserService.getUsers($scope.user).then(function (data) {
                        $scope.users = data;
                    }, function (response) {
                        $scope.error = response;
                    })
                }();
            }, function (response) {
                $scope.error = response;
            });
        }
    }

})();