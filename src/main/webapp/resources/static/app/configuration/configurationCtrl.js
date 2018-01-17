appRaamApp.controller('ConfigurationController', ['$scope', '$timeout', '$route', '$rootScope', '$interval', '$location', 'ConfigurationService', '$window',
function ($scope, $timeout, $route, $rootScope, $interval, $location, ConfigurationService, $window) {

	$scope.tab = 1;

    $scope.setTab = function(newValue){
      $scope.tab = newValue;
    };

    $scope.isSet = function(tabName){
      return $scope.tab === tabName;
    };
    
    $scope.save = function(config) {
		
		
			ConfigurationService.saveConfiguration($scope.config, function(response){
			
        		if(response.data.data.statusCode == 200){
		            $scope.succsmessage = response.data.data.message;
        			$timeout(function () {
    		            $scope.succsmessage = '';
    		            $('#form-config').modal('hide');            			
            			$route.reload();
    		            
        		    }, 3000, true);
        		}else{
        			$scope.errmessage = response.data.data.message;
        			
        		}
        	});
		}
    
    
/*$scope.save = function(config) {
	console.log($scope.config);
    ConfigurationService.saveConfiguration($scope.config, function(response){

               if(response.call){

                   $('#form-config').modal('hide');

                   ConfigurationService.fetchAllConfig(function(response){

                       $scope.configs = response.data.data.object;

               });

               }

           });

       

     };*/
 
      
      $scope.setTab = function(newValue){
          $scope.tab = newValue;
        };
    
        ConfigurationService.fetchAllConfig(function(response){
			if(response.call){
				$scope.configs = response.data.data.object;
			}
		});
}
]);
