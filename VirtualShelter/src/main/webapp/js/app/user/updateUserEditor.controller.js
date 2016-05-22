(function () {

    angular.module('vshelterApp')
        .controller('UpdateUserEditorController', UpdateUserEditorController);

    UpdateUserEditorController.$inject = ['$scope', '$routeParams', 'UserService'];

    function UpdateUserEditorController($scope, $routeParams, UserService) {
        $scope.user = {};
        $scope.error = "";

        $scope.user.id = $routeParams.id ? $routeParams.id : null;
        $scope.updateUser = function () {
            UserService.updateUser($scope.user).then(function () {
                location.href = "/";
            }, function (response) {
                $scope.error = response;
            })
        };

        if ($scope.user.id > 0) {
            UserService.getUser($scope.user.id).then(function (data) {
                $scope.user = data;
            }, function (response) {
                $scope.error = response;
            })
        }
    }

})();