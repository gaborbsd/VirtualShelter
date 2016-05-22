(function () {

    angular.module('vshelterApp')
        .controller('UpdateShelterEditorController', UpdateShelterEditorController);

    UpdateShelterEditorController.$inject = ['$scope', '$routeParams', 'ShelterService'];

    function UpdateShelterEditorController($scope, $routeParams, ShelterService) {
        $scope.shelter = {};
        $scope.error = "";

        $scope.shelter.id = $routeParams.id ? $routeParams.id : null;
        $scope.updateShelter = function () {
            ShelterService.updateShelter($scope.shelter).then(function () {
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