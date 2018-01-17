appRaamApp.controller('VehicleManagementController', ['$scope','$route', '$rootScope', '$timeout', '$location', 'VehicleManagementService','MineService', '$window',
function ($scope,$route, $rootScope, $timeout,  $location, VehicleManagementService,  MineService, $window) {
// while refreshing the page or loading of page we are remove the cookie store loan details.
	
	$scope.tab = 1;
	$scope.currentPage = 1;
	$scope.pageSize = 10;
	
	MineService.fetchAllMines(function(response){
		if(response.call){
			$scope.mines = response.data.data.object;
		}
	});

    $scope.setTab = function(newValue){
      $scope.tab = newValue;
      
      if($scope.tab == 1){    	  
      	VehicleManagementService.fetchAllVehicles($scope.currentPage, $scope.pageSize, function(response){
      		if(response.call){
  			$scope.vehicles = response.data.data.object.content;
			$scope.totalElements = response.data.data.object.totalElements;
            $scope.totalPages = response.data.data.object.totalPages;
            $scope.page = response.data.data.object.number+1;
            $scope.numberOfElements = response.data.data.object.numberOfElements;
            $scope.size = response.data.data.object.size;
      		}
  	});
      }else if($scope.tab == 2){
      	VehicleManagementService.fetchAllVehicleSerfs($scope.currentPage, $scope.pageSize, function(response){
      		if(response.call){
  		    $scope.vehicleserfs = response.data.data.object.content;
			$scope.totalElements = response.data.data.object.totalElements;
            $scope.totalPages = response.data.data.object.totalPages;
            $scope.page = response.data.data.object.number+1;
            $scope.numberOfElements = response.data.data.object.numberOfElements;
            $scope.size = response.data.data.object.size;
      		}
      });
       }
      
    };

    $scope.isSet = function(tabName){
      return $scope.tab === tabName;
    };

  	VehicleManagementService.fetchAllVehicles($scope.currentPage, $scope.pageSize, function(response){
  		if(response.call){
	    $scope.vehicles = response.data.data.object.content;
		$scope.totalElements = response.data.data.object.totalElements;
        $scope.totalPages = response.data.data.object.totalPages;
        $scope.page = response.data.data.object.number+1;
        $scope.numberOfElements = response.data.data.object.numberOfElements;
        $scope.size = response.data.data.object.size;
  		}
	});
  	
  	$scope.vehiclePageChanged = function(page){/*pageClicked,totalPages, size*/
        $scope.newPage = page;
        VehicleManagementService.fetchAllVehicles($scope.newPage, $scope.pageSize, function(response){
      		if(response.call){
    		$scope.vehicles = response.data.data.object.content;
    		$scope.totalElements = response.data.data.object.totalElements;
            $scope.totalPages = response.data.data.object.totalPages;
            $scope.page = response.data.data.object.number+1;
            $scope.numberOfElements = response.data.data.object.numberOfElements;
            $scope.size = response.data.data.object.size;
      		}
    	});     
    }
  	
  	$scope.vehicleSerfPageChanged = function(page){/*pageClicked,totalPages, size*/
        $scope.newPage = page;
        VehicleManagementService.fetchAllVehicleSerfs($scope.newPage, $scope.pageSize, function(response){
      		if(response.call){
  		    $scope.vehicleserfs = response.data.data.object.content;
			$scope.totalElements = response.data.data.object.totalElements;
            $scope.totalPages = response.data.data.object.totalPages;
            $scope.page = response.data.data.object.number+1;
            $scope.numberOfElements = response.data.data.object.numberOfElements;
            $scope.size = response.data.data.object.size;
      		}
      });     
    }
    $scope.addOrEditVehicleProfile = function(vehicle){
		$scope.vehicle = vehicle;
	}
    
    $scope.addOrEditVehicleSerf = function(vehicleserf){
		$scope.vehicleserf = vehicleserf;
		VehicleManagementService.fetchAllUnmappedVehiclesAndSerfs(function(response){
			$scope.vehicleSerfs = response.data.data.object;
		});
/*		NewDeviceManagementService.fetchAllSerfs(function(response){
		    $scope.serfs = response.data.data.object;
    });*/
	}
    
    $scope.fetchVehicleByVehicleNo = function(vehicleNo){
    	$scope.vehicleNo = vehicleNo;
         VehicleManagementService.fetchVehicleByVehicleNo($scope.vehicleNo, function(response){
	     	$scope.vehicleserf = response.data.data.object;
	    });
    }
    
    $scope.searchVehicle = function(text){
		if($scope.text){
			VehicleManagementService.searchVehicleByText($scope.text, function(response){
			$scope.vehicles = response.data.data.object;
		});
		}else{
			VehicleManagementService.fetchAllVehicles(function(response){
				$scope.vehicles = response.data.data.object;
		});			
		}
	}
	
	    
	$scope.save = function(vehicle) {
		
		if($scope.vehicle.roleType == 'PERMANENT'){
			$scope.vehicle.contractAgency = '';
			$scope.vehicle.contractExpiryDate = '';
		}
        if ($scope.vehicle.id) {
        	VehicleManagementService.updateVehicle($scope.vehicle, $scope.vehicle.id, function(response){
        		if(response.data.data.statusCode == 200){
        			 $scope.succsmessage = response.data.data.message;
        			 $timeout(function () {
        				 $scope.succsmessage = '';
        			 
        			$('#form-vadd').modal('hide');
        			$route.reload();
         		    }, 2000, true);
         		}else{
         			$scope.errmessage = response.data.data.message;
 /*        			$timeout(function () {
     		            $scope.errmessage = '';
     		        }, 2000, true);*/
         		}
        	});
        }else{
        	VehicleManagementService.saveVehicle($scope.vehicle, function(response){
        		if(response.data.data.statusCode == 200){
        			 $scope.succsmessage = response.data.data.message;
        			 $timeout(function(){
     		            $scope.succsmessage = '';
        			$('#form-vadd').modal('hide');
        			$route.reload();
     		        }, 3000, true);
         		}else{
 		            $scope.errmessage = response.data.data.message;
/*         			$timeout(function(){
             			$scope.errmessage='';
     		        }, 2000, true);*/
         		}
         	});
         }
       };
      
	/*$scope.saveVehicleSerf = function(vehicleserf) {
		

        	VehicleManagementService.saveVehicleSerf($scope.vehicleserf, function(response){
        		if(response.data.data.statusCode == 200){
        			 $scope.succsmessage = response.data.data.message;
           			$timeout(function(){
     		            $scope.succsmessage = '';
        			$('#form-vsm').modal('hide');
        			$route.reload();
     		        }, 3000, true);
        		});
        		}
        	});

        }*/
       $scope.saveVehicleSerf = function(vehicleserf) {

    	   VehicleManagementService.saveVehicleSerf($scope.vehicleserf, function(response){
     			
         		if(response.data.data.statusCode == 200){
 		            $scope.succsmessage = response.data.data.message;
         			$timeout(function () {
     		            $scope.succsmessage = '';
     		            $('#form-vsm').modal('hide');            			
     		           $route.reload();
     		            
         		    }, 3000, true);
         		}else{
         			$scope.errmessage = response.data.data.message;
/*         			$timeout(function () {
     		            $scope.errmessage = '';
     		        }, 3000, true);*/
         		}
         	});
     	
     	          };

      
      $scope.example = {
              
              currentDate: new Date()
            };

}
]);
