(function () {

    angular.module('vshelterApp')
        .controller('SearchController', SearchController);

    SearchController.$inject = ['$scope', '$http', 'SearchService'];

    function SearchController($scope, $http, SearchService) {
        $scope.clientLimit;
        $scope.animals;
        $scope.page;
        $scope.perPage;
        $scope.url = 'api/animal';
        $scope.urlParams;

        $scope.detailed = false;
        $scope.getAnimals = function getAnimals() {
            $http.get('api/animal').success(function (data) {
                $scope.animals = data;
            }).error(function (response) {
                $scope.error = response.status;
            });
        };

    }

})();