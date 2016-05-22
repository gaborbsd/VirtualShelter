(function () {

    angular.module('vshelterApp')
        .controller('BreedsController', BreedsController);

    BreedsController.$inject = ['$scope', '$routeParams', 'SpeciesAndBreedsService'];

    function BreedsController($scope, $routeParams, SpeciesAndBreedsService) {
        $scope.species = [];
        $scope.error = "";
        $scope.breeds = [];
        $scope.breed = {};

        if ($routeParams.id > 0) {
            SpeciesAndBreedsService.getSpeciesId($routeParams.id).then(function (data) {
                $scope.species = data;
                $scope.breeds = $scope.species.breeds;

            }, function (response) {
                $scope.error = response;
            })
        }

        $scope.saveBreed = function () {
            SpeciesAndBreedsService.saveBreed($scope.breed, $scope.species).then(function () {
                SpeciesAndBreedsService.getSpecies().then(function (data) {
                    SpeciesAndBreedsService.getSpeciesId($routeParams.id).then(function (data) {
                        $scope.species = data;
                        $scope.breeds = $scope.species.breeds;

                    }, function (response) {
                        $scope.error = response;
                    })
                }, function (response) {
                    $scope.error = response;
                });
                location.href = "#/update/species/" + $scope.species.speciesId;
            }, function (response) {
                $scope.error = response;
            })
        };

        $scope.deleteBreed = function (id) {
            $scope.id = id;
            $scope.breed.id = id;
            SpeciesAndBreedsService.deleteBreed($scope.breed, $scope.species).then(function () {
                SpeciesAndBreedsService.getSpecies().then(function (data) {
                    SpeciesAndBreedsService.getSpeciesId($routeParams.id).then(function (data) {
                        $scope.species = data;
                        $scope.breeds = $scope.species.breeds;

                    }, function (response) {
                        $scope.error = response;
                    })
                }, function (response) {
                    $scope.error = response;
                });
                location.href = "#/update/species/" + $scope.species.speciesId;
            }, function (response) {
                $scope.error = response;
            });
        }

    }

})();