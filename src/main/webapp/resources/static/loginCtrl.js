appRaamLogin.controller('LoginController', ['$scope', '$cookieStore', '$timeout', '$route', '$rootScope', '$location', 'LoginService', '$window',
function ($scope, $cookieStore, $timeout, $route, $rootScope, $location, LoginService, $window) {

	$scope.login = function(user){
		$scope.user=user;
		LoginService.userLogin($scope.user, function(response){
	 		if(response.data.data.statusCode == 200){
	            $scope.loginerror = '';
	 			$scope.loginsuccess = response.data.data.message;
	 			$timeout(function () {
		            $scope.loginsuccess = '';
		 			$scope.loggedInuser = response.data.data.object;
		 			$cookieStore.put("loggedInuser", $scope.loggedInuser);
		 			window.location.href="./index.html"
    		    }, 3000, true);
	 		}else{
	 			$scope.loginsuccess = '';
	 			$scope.loginerror = response.data.data.message;
	 		}
	 });
	}

}
]);
