appRaamApp.controller('IndexController', ['$scope', '$cookieStore', '$timeout', '$route', '$rootScope', '$interval', '$location', 'IndexService', '$window',
function ($scope, $cookieStore, $timeout, $route, $rootScope, $interval, $location, IndexService, $window) {

	
	function issues(){
		
		$scope.user = $cookieStore.get('loggedInuser');
		if($scope.user){
		console.log("User password : "+$scope.user.userName);
		}
	IndexService.fetchOpenedIssues(function(response){
	 		if(response.call){
	 			$scope.issues = response.data.data.object;
	 		}
	 });
	}
	
	 $interval(issues, 5000);
}
]);
