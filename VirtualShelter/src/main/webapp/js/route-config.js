(function () {
    angular.module('vshelterApp')
        .config(config);

    function config($routeProvider) {
        $routeProvider
            .when('/editor/user', {
                templateUrl: '/html/userform.html',
                controller: 'UserEditorController'
            })
            .when('/editor/user/:id', {
                templateUrl: '/html/userform.html',
                controller: 'UserEditorController'
            })
            .when('/advertisements', {
                templateUrl: '/html/advertisements.html',
                controller: 'AdvertisementController'
            })
            .when('/editor/advertisement', {
                templateUrl: '/html/advertisementform.html',
                controller: 'AdvertisementEditorController'
            })
            .when('/editor/animal/:id', {
                templateUrl: '/html/animalform.html',
                controller: 'AnimalEditorController'
            })
            .when('/editor/shelter', {
                templateUrl: '/html/shelterform.html',
                controller: 'ShelterEditorController'
            })
            .when('/editor/shelter/:id', {
                templateUrl: '/html/shelterform.html',
                controller: 'ShelterEditorController'
            })
            .when('/search', {
                templateUrl: '/html/search.html',
                controller: 'SearchController'
            })
            .when('/users', {
                templateUrl: '/html/users.html',
                controller: 'UsersController'
            })
            .when('/update/user/:id', {
                templateUrl: '/html/updateuserform.html',
                controller: 'UpdateUserEditorController'
            })
            .when('/shelters', {
                templateUrl: '/html/shelters.html',
                controller: 'SheltersController'
            })
            .when('/update/shelter/:id', {
                templateUrl: '/html/updateshelterform.html',
                controller: 'UpdateShelterEditorController'
            })
            .when('/speciesandbreeds', {
                templateUrl: '/html/speciesandbreeds.html',
                controller: 'SpeciesAndBreedsController'
            })
            .when('/update/species/:id', {
                templateUrl: '/html/breeds.html',
                controller: 'BreedsController'
            })
            .otherwise({
                redirectTo: '/editor/user'
            })
    }
})();
