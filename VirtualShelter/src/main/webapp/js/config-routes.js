var app = angular.module('sportnetworkApp', ['ngRoute']);

app.config(function($routeProvider) {
	$routeProvider.when('/user/:id', {
		controller : 'UserController',
		templateUrl : '/html/users.html'
	}).when('/editor/events', {
		controller : 'SportEventController',
		templateUrl : '/html/events.html'
	}).when('/user', {
		controller : 'UserEditorController',
		templateUrl : '/html/users.html'
	}).when('/event/:id', {
		controller : 'EventController',
		templateUrl : '/html/event.html'
	}).when('/editor/events/new', {
		controller : 'CreateSportEventController',
		templateUrl : '/html/createEvent.html'
	}).when('/conversations', {
		controller : 'CreateSportEventController',
		templateUrl : '/html/conversations.html'
	}).otherwise({
		redirectTo : '/editor/events'
	})
})