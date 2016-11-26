var app = angular.module('sportnetworkApp', ['ngRoute']);

app.config(function($routeProvider) {
	$routeProvider.when('/user/:id', {
		controller : 'UserController',
		templateUrl : '/html/user/user/user.html'
	}).when('/listevents', {
		controller : 'SportEventListController',
		templateUrl : '/html/user/event/eventlist.html'
	}).when('/user', {
		controller : 'UserController',
		templateUrl : '/html/user/user/user.html'
	}).when('/event/:id', {
		controller : 'EventController',
		templateUrl : '/html/user/event/event.html'
	}).when('/editor/events/new', {
		controller : 'EventCreateController',
		templateUrl : '/html/user/event/newevent.html'
	}).when('/conversations', {
		controller : 'ConversationController',
		templateUrl : '/html/user/conversation/conversations.html'
	}).when('/notifications', {
		controller : 'NotificationController',
		templateUrl : '/html/user/notification/notifications.html'
	}).when('/conversation/:id', {
		controller : 'MessageController',
		templateUrl : '/html/user/conversation/messages.html'
	}).when('/rate/:id', {
		controller : 'RateController',
		templateUrl : '/html/user/notification/rate.html'
	}).otherwise({
		redirectTo : '/listevents'
	})
})