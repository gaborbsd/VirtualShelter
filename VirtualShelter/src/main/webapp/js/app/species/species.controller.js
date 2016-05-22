(function () {

    angular.module('vshelterApp')
        .controller('SpeciesAndBreedsController', SpeciesAndBreedsController);

    SpeciesAndBreedsController.$inject = ['$scope', '$routeParams', 'SpeciesAndBreedsService'];

    function SpeciesAndBreedsController($scope, $routeParams, SpeciesAndBreedsService) {
        $scope.speciesList = [];
        $scope.error = "";
        $scope.species = {breeds: null};

        $scope.getSpecies = function () {
            SpeciesAndBreedsService.getSpecies().then(function (data) {
                $scope.speciesList = data;
            }, function (response) {
                $scope.error = response;
            })
        }();

        $scope.saveSpecies = function () {
            SpeciesAndBreedsService.saveSpecies($scope.species).then(function () {
                SpeciesAndBreedsService.getSpecies().then(function (data) {
                    $scope.speciesList = data;
                }, function (response) {
                    $scope.error = response;
                });
                location.href = "/#/speciesandbreeds";
            }, function (response) {
                $scope.error = response;
            })
        };

        $scope.deleteSpecies = function (id) {
            $scope.id = id;
            SpeciesAndBreedsService.deleteSpecies($scope.id).then(function () {
                SpeciesAndBreedsService.getSpecies().then(function (data) {
                    $scope.speciesList = data;
                }, function (response) {
                    $scope.error = response;
                });
                location.href = "/#/speciesandbreeds";
            }, function (response) {
                $scope.error = response;
            });
        }

    }

})();