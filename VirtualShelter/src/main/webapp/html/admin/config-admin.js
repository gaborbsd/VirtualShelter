var adminApp = angular.module('adminApp', ['ngRoute']);

adminApp.config(function($routeProvider) {
	$routeProvider.when('/user', {
		controller : 'UserAdminController',
		templateUrl : '/html/admin/user.html'
	}).when('/comment/:id', {
		controller : 'CommentAdminController',
		templateUrl : '/html/admin/comment.html'
	}).when('/event', {
		controller : 'EventAdminController',
		templateUrl : '/html/admin/event.html'
	}).otherwise({
		redirectTo : '/user'
	})
})