var app = angular.module('sportnetworkApp');

app.controller('ConversationController', function($scope, ConversationFactory) {

	$scope.getConversations = function() {
		ConversationFactory.getConversations().then(function (data) {
            $scope.conversations = data;
        }, function (error) {
            alert(error);
        });
	};
	
	$scope.getConversations();
});