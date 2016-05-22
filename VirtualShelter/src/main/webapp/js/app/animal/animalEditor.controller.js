(function () {

    angular.module('vshelterApp')
        .controller('AnimalEditorController', AnimalEditorController);

    AnimalEditorController.$inject = ['$scope', 'AnimalService', '$routeParams'];

    function AnimalEditorController($scope, AnimalService, $routeParams) {
        $scope.animal = {};
        $scope.error = "";
        $scope.species = [];
        $scope.breeds = [];


        $scope.saveAnimal = function () {
            AnimalService.saveAnimal($scope.animal).then(function () {
                location.href = "/";
            }, function (response) {
                $scope.error = response;
            })
        };

        $scope.getSpecies = function () {
            AnimalService.getSpecies().then(function (data) {
                $scope.species = data;
            }, function (response) {
                $scope.error = response;
            })
        };

        $scope.getBreeds = function () {
            var selectedSpecies = $scope.species.filter(function (value) {
                return value.name == $scope.animal.species.speciesName;
            });
            $scope.animal.breed = null;
            $scope.breeds = selectedSpecies[0].breeds;
        };


        if ($scope.animal.id > 0) {
            AnimalService.getAnimal($scope.animal.id).then(function (data) {
                $scope.animal = data;
            }, function (response) {
                $scope.error = response;
            })
        }

        $scope.getSpecies();
    }

})();