(function () {

    angular.module('vshelterApp')
        .controller('UserEditorController', UserEditorController);

    UserEditorController.$inject = ['$scope', '$routeParams', 'UserService'];

    function UserEditorController($scope, $routeParams, UserService) {
        $scope.user = {};
        $scope.error = "";
        $scope.errorMap = {};

        $scope.user.id = $routeParams.id ? $routeParams.id : null;
        $scope.saveUser = function () {
            UserService.saveUser($scope.user).then(function () {
                location.href = "/";
            }, function (response) {
                if (typeof response == 'object') {
                    $scope.error = "Validation failed!";
                    $scope.errorMap = {};
                    response.forEach(function (err) {
                        $scope.errorMap[err.field] = err.defaultMessage;
                    });
                } else if (typeof response == 'string') {
                    $scope.error = response;
                    $scope.errorMap = {};
                } else {
                    $scope.error = "";
                    $scope.errorMap = {};
                }
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