(function () {

    angular.module('vshelterApp')
        .controller('AdvertisementEditorController', AdvertisementEditorController);

    AdvertisementEditorController.$inject = ['$scope', 'AdvertisementService', 'AnimalService', 'UserService'];

    function AdvertisementEditorController($scope, AdvertisementService, AnimalService, UserService) {
        $scope.advertisement = {};
        $scope.administeredShelters = [];
        $scope.species = [];
        $scope.breeds = [];

        $scope.saveAdvertisement = function () {
            delete $scope.advertisement.animal['links'];
            delete $scope.advertisement.animal['knownDiseases'];
            delete $scope.advertisement.animal['knownHandicaps'];
            delete $scope.advertisement.animal['age'];
            AdvertisementService.saveAdvertisement($scope.advertisement).then(function () {
                location.href = "/";
            }, function (response) {
                $scope.error = response;
            })
        };

        $scope.getAdministeredShelters = function () {
            UserService.getAdministeredShelters().then(
                function (data) {
                    $scope.administeredShelters = data;
                },
                function (response) {
                    $scope.error = response;
                });
        };

        $scope.clearAdvertiser = function () {
            if ($scope.advertisement.hasOwnProperty('advertiser')) {
                delete $scope.advertisement.advertiser;
            }
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
                return value.name == $scope.advertisement.animal.species.speciesName;
            });
            $scope.advertisement.animal.breed = null;
            $scope.breeds = selectedSpecies[0].breeds;
        };

        $scope.getSpecies();
        $scope.getAdministeredShelters();
    }

})();