(function () {

    angular.module('vshelterApp')
        .controller('ShelterEditorController', ShelterEditorController);

    ShelterEditorController.$inject = ['$scope', 'ShelterService', '$routeParams'];

    function ShelterEditorController($scope, ShelterService, $routeParams) {
        $scope.shelter = {};
        $scope.error = "";
        $scope.shelter.id = $routeParams.id ? $routeParams.id : -1;

        $scope.saveShelter = function () {

            ShelterService.saveShelter($scope.shelter).then(function () {
                location.href = "/";
            }, function (response) {
                $scope.error = response;
            })
        };

        if ($scope.shelter.id > 0) {
            ShelterService.getShelter($scope.shelter.id).then(function (data) {
                $scope.shelter = data;
            }, function (response) {
                $scope.error = response;
            })
        }
    }

})();