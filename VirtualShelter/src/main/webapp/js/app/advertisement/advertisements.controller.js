(function () {

    angular.module('vshelterApp')
        .controller('AdvertisementController', AdvertisementController);

    AdvertisementController.$inject = ['$scope', 'AdvertisementService', '$routeParams'];

    function AdvertisementController($scope, AdvertisementService, $routeParams) {
        $scope.advertisements = [];
        $scope.error = "";
        $scope.getAdvertisements = function () {
            AdvertisementService.getAdvertisements().then(function (data) {
                $scope.advertisements = data;
            }, function (response) {
                $scope.error = response;
            })
        }();


        $scope.deleteAdvertisement = function (id) {
            AdvertisementService.deleteAdvertisement(id).then(function () {
                AdvertisementService.getAdvertisements().then(function (data) {
                    $scope.advertisements = data;
                }, function (response) {
                    $scope.error = response;
                })
            }, function (response) {
                $scope.error = response;
            })
        };
    }

})();