(function () {
//ngRoute for the routing in the config
//bgf.paginateAnything for the PaginateAnything in the code more details : https://github.com/begriffs/angular-paginate-anything
//	server side must be prepared for handling further get parameters
    var app = angular.module('vshelterApp', ['ngRoute', 'bgf.paginateAnything']);

//To keep the MVC - MVVM you will need services like this 
//The first parameter is the name of the Service the second is a function which implements all the required functions
    app.factory('SearchService', SearchService);
    app.factory('AdvertisementService', AdvertisementService);
    app.factory('AnimalService', AnimalService);
    app.factory('ShelterService', ShelterService);
    app.factory('UserService', UserService);
    app.factory('SpeciesAndBreedsService', SpeciesAndBreedsService);

//$inject is neccessary with this concept of service handling
//$http does the communication
//$q future-promice "architecture" for the async datahandling
    AnimalService.$inject = ['$http', '$q'];
    function AnimalService($http, $q) {

//Here you must provide the method you can use from this service/factory
//The field name of the json is the method you call from the controller, the value is the name of the function which implements the required functionality
        var service = {
            getSpecies: getSpecies,
            saveAnimal: saveAnimal,
            getAnimal: getAnimal
        };

        return service;

        function saveAnimal(animal) {
            var deferred = $q.defer();
            $http.post("api/animal", animal).success(function (data, status) {
                deferred.resolve(data);
            }).error(function (data, status) {
                deferred.reject(status);
            });
            return deferred.promise;
        }

        function getSpecies() {
            var deferred = $q.defer();
            $http.get("api/species").success(function (data, status) {
                deferred.resolve(data);
            }).error(function (data, status) {
                deferred.reject(status);
            });
            return deferred.promise;
        }

        function getAnimal(id) {
            var deferred = $q.defer();
            $http.get("api/animal/" + id).success(function (data, status) {
                deferred.resolve(data);
            }).error(function (data, status) {
                deferred.reject(status);
            });
            return deferred.promise;
        }
    }

    SearchService.$inject = ['$http', '$q'];
    function AdvertisementService($http, $q) {

        var service = {
            getAdvertisements: getAdvertisements,
            deleteAdvertisement: deleteAdvertisement,
            getAnimalsForCurrentUser: getAnimalsForCurrentUser,
            saveAdvertisement: saveAdvertisement
        };

        return service;

        function getAdvertisements() {
            var deferred = $q.defer();
            $http.get("api/advertisement").success(function (data, status) {
                deferred.resolve(data);
            }).error(function (data, status) {
                deferred.reject(status);
            });
            return deferred.promise;
        }

        function deleteAdvertisement(id) {
            var deferred = $q.defer();
            $http.delete("api/advertisement/" + id).success(function (data, status) {
                deferred.resolve(data);
            }).error(function (status) {
                deferred.reject(status);
            });
            return deferred.promise;
        }

        function getAnimalsForCurrentUser() {
            var deferred = $q.defer();
            $http.get("api/advertisement/animals").success(function (data, status) {
                deferred.resolve(data);
            }).error(function (data, status) {
                deferred.reject(status);
            });
            return deferred.promise;
        }

        function saveAdvertisement(adv) {
            var deferred = $q.defer();
            $http.post("api/advertisement", adv).success(function (data, status) {
                deferred.resolve(data);
            }).error(function (data, status) {
                deferred.reject(status);
            });
            return deferred.promise;
        }

    }


    SearchService.$inject = ['$http', '$q'];
    function SearchService($http, $q) {

        var service = {
            getAnimals: getAnimals
        };

        return service;

        function getAnimals() {
            var deferred = $q.defer();
            $http.get("api/animal").success(function (data, status) {
                deferred.resolve(data);
            }).error(function (data, status) {
                deferred.reject(status);
            });
            return deferred.promise;
        }
    }

    ShelterService.$inject = ['$http', '$q'];
    function ShelterService($http, $q) {

        var service = {
            saveShelter: saveShelter,
            getShelter: getShelter,
            getShelters: getShelters,
            updateShelter: updateShelter,
            deleteShelter: deleteShelter
        };

        return service;

        function saveShelter(shelter) {
            delete shelter['id'];
            var deferred = $q.defer();
            $http.post("api/shelter", shelter).success(function (data, status) {
                deferred.resolve(data);
            }).error(function (reason) {
                deferred.reject(reason);
            });
            return deferred.promise;
        }


        function getShelter(id) {
            var deferred = $q.defer();
            $http.get("api/shelter/" + id).success(function (data, status) {
                deferred.resolve(data);
            }).error(function (data, status) {
                deferred.reject(status);
            });
            return deferred.promise;
        }

        function getShelters() {
            var deferred = $q.defer();
            $http.get("api/shelter").success(function (data, status) {
                deferred.resolve(data);
            }).error(function (data, status) {
                deferred.reject(status);
            });
            return deferred.promise;
        }

        function updateShelter(shelter) {
            shelter.id = shelter.institutionId;
            //must delete, because the PUT would not work
            delete shelter['links'];
            delete shelter['institutionId'];
            var deferred = $q.defer();
            $http.put("api/shelter/" + shelter.id, shelter).success(function (data, status) {
                deferred.resolve(data);
            }).error(function (data, status) {
                deferred.reject(status);
            });
            return deferred.promise;

        }


        function deleteShelter(id) {
            var deferred = $q.defer();
            $http.delete("api/shelter/" + id).success(function (data, status) {
                deferred.resolve(data);
            }).error(function (status) {
                deferred.reject(status);
            });
            return deferred.promise;
        }

    }

    UserService.$inject = ['$http', '$q'];
    function UserService($http, $q) {

        var service = {
            saveUser: saveUser,
            getUser: getUser,
            getUsers: getUsers,
            updateUser: updateUser,
            deleteUser: deleteUser,
            getCurrentUser: getCurrentUser,
            getAdministeredShelters: getAdministeredShelters
        };

        return service;

        function saveUser(user) {
            var deferred = $q.defer();
            $http.post("api/user", user).success(function (data, status) {
                deferred.resolve(data);
            }).error(function (status) {
                deferred.reject(status);
            });
            return deferred.promise;
        }

        function getUser(id) {
            var deferred = $q.defer();
            $http.get("api/user/" + id).success(function (data, status) {
                deferred.resolve(data);
            }).error(function (data, status) {
                deferred.reject(status);
            });
            return deferred.promise;
        }

        function getUsers() {
            var deferred = $q.defer();
            $http.get("api/user").success(function (data, status) {
                deferred.resolve(data);
            }).error(function (data, status) {
                deferred.reject(status);
            });
            return deferred.promise;
        }

        function updateUser(user) {
            user.id = user.userId;
            //must delete, because the PUT would not work
            delete user['links'];
            delete user['userId'];
            delete user['password-re'];
            var deferred = $q.defer();
            $http.put("api/user/" + user.id, user).success(function (data, status) {
                deferred.resolve(data);
            }).error(function (data, status) {
                deferred.reject(status);
            });
            return deferred.promise;

        }

        function getCurrentUser() {
            var deferred = $q.defer();
            $http.get("api/user/username").success(function (data, status) {
                deferred.resolve(data);
            }).error(function (status) {
                deferred.reject(status);
            });
            return deferred.promise;
        }

        function deleteUser(id) {
            var deferred = $q.defer();
            $http.delete("api/user/" + id).success(function (data, status) {
                deferred.resolve(data);
            }).error(function (status) {
                deferred.reject(status);
            });
            return deferred.promise;
        }

        function getAdministeredShelters() {
            var deferred = $q.defer();
            $http.get("api/user/administeredShelter").success(function (data, status) {
                deferred.resolve(data);
            }).error(function (status) {
                deferred.reject(status);
            });
            return deferred.promise;
        }

    }

    SpeciesAndBreedsService.$inject = ['$http', '$q'];
    function SpeciesAndBreedsService($http, $q) {

        var service = {
            getSpecies: getSpecies,
            getSpeciesId: getSpeciesId,
            saveSpecies: saveSpecies,
            deleteSpecies: deleteSpecies,
            saveBreed: saveBreed,
            deleteBreed: deleteBreed
        };

        return service;

        function getSpecies() {
            var deferred = $q.defer();
            $http.get("api/species").success(function (data, status) {
                deferred.resolve(data);
            }).error(function (data, status) {
                deferred.reject(status);
            });
            return deferred.promise;
        }

        function getSpeciesId(id) {
            var deferred = $q.defer();
            $http.get("api/species/" + id).success(function (data, status) {
                deferred.resolve(data);
            }).error(function (data, status) {
                deferred.reject(status);
            });
            return deferred.promise;
        }

        function saveSpecies(species) {
            var deferred = $q.defer();
            $http.post("api/species", species).success(function (data, status) {
                deferred.resolve(data);
            }).error(function (status) {
                deferred.reject(status);
            });
            return deferred.promise;
        }

        function deleteSpecies(id) {
            var deferred = $q.defer();
            $http.delete("api/species/" + id).success(function (data, status) {
                deferred.resolve(data);
            }).error(function (status) {
                deferred.reject(status);
            });
            return deferred.promise;
        }

        function saveBreed(breed, species) {
            var deferred = $q.defer();
            $http.post("api/species/" + species.speciesId + "/breed", breed).success(function (data, status) {
                deferred.resolve(data);
            }).error(function (status) {
                deferred.reject(status);
            });
            return deferred.promise;
        }

        function deleteBreed(breed, species) {
            var deferred = $q.defer();
            $http.delete("api/species/" + species.speciesId + "/breed", breed).success(function (data, status) {
                deferred.resolve(data);
            }).error(function (status) {
                deferred.reject(status);
            });
            return deferred.promise;
        }
    }

    var controllers = {};

    controllers.HeaderController = function ($scope, $http) {
        function login() {

        }

        function register() {
            location.href = "#/editor/user"
        }
    };

    controllers.AdvertisementEditorController = function ($scope, $http, AdvertisementService, AnimalService, UserService) {
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
    };

    controllers.SearchController = function ($scope, $http, SearchService) {
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

    };

    controllers.UsersController = function ($scope, $http, UserService) {
        $scope.users = [];
        $scope.error = "";
        $scope.getUsers = function () {
            UserService.getUsers($scope.user).then(function (data) {
                $scope.users = data;
            }, function (response) {
                $scope.error = response;
            })
        }();

        $scope.deleteUser = function (id) {
            $scope.id = id;
            UserService.deleteUser($scope.id).then(function () {
                $scope.getUsers = function () {
                    UserService.getUsers($scope.user).then(function (data) {
                        $scope.users = data;
                    }, function (response) {
                        $scope.error = response;
                    })
                }();
            }, function (response) {
                $scope.error = response;
            });
        }
    };

    controllers.UserEditorController = function ($scope, $http, $routeParams, UserService) {
        $scope.user = {};
        $scope.error = "";
        $scope.errorMap = {};

        $scope.user.id = $routeParams.id ? $routeParams.id : null;
        $scope.saveUser = function () {
            UserService.saveUser($scope.user).then(function () {
                location.href = "/";
            }, function (response) {
                if (typeof response == 'object') {
                    $scope.error = "Validation failed!";
                    $scope.errorMap = {};
                    response.forEach(function (err) {
                        $scope.errorMap[err.field] = err.defaultMessage;
                    });
                } else if (typeof response == 'string') {
                    $scope.error = response;
                    $scope.errorMap = {};
                } else {
                    $scope.error = "";
                    $scope.errorMap = {};
                }
            })
        };

        if ($scope.user.id > 0) {
            UserService.getUser($scope.user.id).then(function (data) {
                $scope.user = data;
            }, function (response) {
                $scope.error = response;
            })
        }
    };

    controllers.UpdateUserEditorController = function ($scope, $http, $routeParams, UserService) {
        $scope.user = {};
        $scope.error = "";

        $scope.user.id = $routeParams.id ? $routeParams.id : null;
        $scope.updateUser = function () {
            UserService.updateUser($scope.user).then(function () {
                location.href = "/";
            }, function (response) {
                $scope.error = response;
            })
        };

        if ($scope.user.id > 0) {
            UserService.getUser($scope.user.id).then(function (data) {
                $scope.user = data;
            }, function (response) {
                $scope.error = response;
            })
        }
    };

    controllers.AnimalEditorController = function ($scope, $http, AnimalService, $routeParams) {
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
    };

    controllers.AdvertisementController = function ($scope, $http, AdvertisementService, $routeParams) {
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
    };

    controllers.ShelterEditorController = function ($scope, $http, ShelterService, $routeParams) {
        $scope.shelter = {};
        $scope.error = "";
        $scope.shelter.id = $routeParams.id ? $routeParams.id : -1;

        $scope.saveShelter = function () {

            ShelterService.saveShelter($scope.shelter).then(function () {
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
    };

    controllers.SheltersController = function ($scope, $http, ShelterService) {
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
        }

        $scope.getShelters();
    };

    controllers.UpdateShelterEditorController = function ($scope, $http, $routeParams, ShelterService) {
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
    };

    controllers.SpeciesAndBreedsController = function ($scope, $http, $routeParams, SpeciesAndBreedsService) {
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

    };

    controllers.BreedsController = function ($scope, $http, $routeParams, SpeciesAndBreedsService) {
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

    };

    app.controller(controllers);

//Async file upload you can use it on any file input and this will emit a fileSelected event when a file is need to be uploaded
//You should listen for this event in the controller where you need this kinda action
//Will provice a tutorial how to handle 

    app.directive('fileUpload', FileUploadDirective);

    function FileUploadDirective() {
        return {
            restrict: 'A',
            scope: true,
            link: function (scope, el, attrs) {
                el.bind('change', function (event) {
                    var files = event.target.files;
                    for (var i = 0; i < files.length; i++) {
                        scope.$emit("fileSelected", {
                            file: files[i]
                        });
                    }
                });
            }
        };
    }
})();