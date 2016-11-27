var adminApp = angular.module('adminApp', ['ngRoute']);

adminApp.config(function($routeProvider) {
	$routeProvider.when('/user', {
		controller : 'UserAdminController',
		templateUrl : '/html/admin/user.html'
	}).when('/event', {
		controller : 'EventAdminController',
		templateUrl : '/html/admin/event.html'
	}).otherwise({
		redirectTo : '/user'
	})
})