(function () {

    angular.module('vshelterApp')
        .controller('SheltersController', SheltersController);

    SheltersController.$inject = ['$scope', 'ShelterService'];

    function SheltersController($scope, ShelterService) {
        $scope.shelters = [];
        $scope.error = "";
        $scope.getShelters = function () {
            ShelterService.getShelters($scope.shelter).then(function (data) {
                $scope.shelters = data;
            }, function (response) {
                $scope.error = response;
            })
        };

        $scope.deleteShelter = function (id) {
            $scope.id = id;
            ShelterService.deleteShelter($scope.id).then(function () {
                $scope.getShelters = function () {
                    ShelterService.getShelters($scope.shelter).then(function (data) {
                        $scope.shelters = data;
                    }, function (response) {
                        $scope.error = response;
                    })
                }();
                location.href = "/#/shelters";
            }, function (response) {
                $scope.error = response;
            });
        };

        $scope.getShelters();
    }

})();