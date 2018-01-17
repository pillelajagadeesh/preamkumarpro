appRaamApp.controller('ReportsController', ['$scope', '$rootScope', '$timeout', '$location', 'ReportsService', '$window',
function ($scope, $rootScope, $timeout,  $location, ReportsService, $window) {
// while refreshing the page or loading of page we are remove the cookie store loan details.

	ReportsService.fetchAllReports(function(response){
     if(response.call){
	   $scope.report = response.data;
     }
    });

}]);
