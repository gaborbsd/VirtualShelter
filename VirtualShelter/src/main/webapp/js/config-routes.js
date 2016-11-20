var app = angular.module('sportnetworkApp', ['ngRoute']);

app.config(function($routeProvider) {
	$routeProvider.when('/user/:id', {
		controller : 'UserController',
		templateUrl : '/html/users.html'
	}).when('/listevents', {
		controller : 'SportEventListController',
		templateUrl : '/html/event/eventlist.html'
	}).when('/user', {
		controller : 'UserEditorController',
		templateUrl : '/html/users.html'
	}).when('/event/:id', {
		controller : 'EventController',
		templateUrl : '/html/event/event.html'
	}).when('/editor/events/new', {
		controller : 'EventCreateController',
		templateUrl : '/html/event/newevent.html'
	}).when('/conversations', {
		controller : 'ConversationController',
		templateUrl : '/html/conversation/conversations.html'
	}).when('/conversation/:id', {
		controller : 'MessageController',
		templateUrl : '/html/conversation/messages.html'
	}).otherwise({
		redirectTo : '/listevents'
	})
})