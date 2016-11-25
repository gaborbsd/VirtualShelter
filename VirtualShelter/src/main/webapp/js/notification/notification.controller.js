var app = angular.module('sportnetworkApp');

app.controller('NotificationController', function($scope, NotificationFactory) {

	$scope.acceptEvent = function(id, event) {
		NotificationFactory.acceptEvent(id).then(function (data) {
            alert("Request accepted");
            window.location.href = "#/event/" + event;
        }, function (error) {
            alert(error);
        });
	};
	
	$scope.acceptFriend = function(id, user) {
		NotificationFactory.acceptFriend(id).then(function (data) {
			alert("Request accepted");
			window.location.href = "#/user/" + user;
        }, function (error) {
            alert(error);
        });
	};
	
	$scope.checkConversation = function(conv, not) {
		NotificationFactory.deleteNotification(not).then(function (data) {
			window.location.href = "#/conversation/" + conv;
        }, function (error) {
            alert(error);
        });
	};
	
	$scope.checkEvent = function(ev, not) {
		NotificationFactory.deleteNotification(not).then(function (data) {
			window.location.href = "#/event/" + ev;
        }, function (error) {
            alert(error);
        });
	};
	
	$scope.decline = function(id) {
		NotificationFactory.decline(id).then(function (data) {
			alert("Request declined");
			$scope.notifications = data;
        }, function (error) {
            alert(error);
        });
	};	
	
	$scope.getAll = function() {
		NotificationFactory.getAll().then(function (data) {
            $scope.notifications = data;
        }, function (error) {
            alert(error);
        });
	};
	
	$scope.getAll();
});