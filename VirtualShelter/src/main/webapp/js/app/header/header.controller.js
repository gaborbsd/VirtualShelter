(function () {

    angular.module('vshelterApp')
        .controller('HeaderController', HeaderController);

    HeaderController.$inject = ['$scope'];

    function HeaderController($scope) {
        function login() {

        }

        function register() {
            location.href = "#/editor/user"
        }
    }

})();