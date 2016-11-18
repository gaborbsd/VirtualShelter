var app = angular.module('sportnetworkApp');

app.controller('MessageController', function($scope, $routeParams, $interval, ConversationFactory) {

	$scope.getMessages = function(id) {
		ConversationFactory.pollMessages(id, function(data){
			$scope.messages = data;
		}, function(error){
			alert(error);
		});
	};
	
	$scope.writeMessage = function() {
		var arg = {
			conversationId: $routeParams.id,
			message: $scope.message	
		};
		ConversationFactory.writeMessage(arg).then(function (data) {
            $scope.messages = data;
            $scope.message = '';
        }, function (error) {
            alert(error);
        });
	};
	
	$scope.getMessages($routeParams.id);
	stop = $interval(function() {
		$scope.getMessages($routeParams.id);
    }, 2000);
	
	$scope.$on("$destroy", function(){
		$interval.cancel(stop);
	});
});