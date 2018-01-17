appRaamApp.controller('DriverManagementController', ['$scope', '$rootScope', '$route', '$timeout', '$location', 'DriverManagementService', 'MineService', '$window',
function ($scope, $rootScope, $route, $timeout,  $location, DriverManagementService, MineService, $window) {
// while refreshing the page or loading of page we are remove the cookie store loan details.
	$scope.mydate = new Date();
	var numberOfDaysToSub = 5479;
	$scope.mydate.setDate($scope.mydate.getDate() - numberOfDaysToSub)
	  
	 // $scope.newdate = $scope.mydate.setDate($scope.mydate.getDate() - numberOfDaysToAdd);
	//temp.setDate(temp.getDate()-1);
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
    		DriverManagementService.fetchAllDrivers($scope.currentPage, $scope.pageSize, function(response){
    			if(response.call){
    				$scope.drivers = response.data.data.object.content;
    				$scope.totalElements = response.data.data.object.totalElements;
    	            $scope.totalPages = response.data.data.object.totalPages;
    	            $scope.page = response.data.data.object.number+1;
    	            $scope.numberOfElements = response.data.data.object.numberOfElements;
    	            $scope.size = response.data.data.object.size;
    			}
    		});
      }else if($scope.tab == 2){
    		DriverManagementService.fetchAllDriverBeacons($scope.currentPage, $scope.pageSize, function(response){
    			if(response.call){
    				$scope.driverbeacons = response.data.data.object.content;    				
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
    
    $scope.searchDriver = function(text){
		if($scope.text){
			DriverManagementService.searchDriversByText($scope.text, function(response){
				$scope.drivers = response.data.data.object;
		});
		}else{
			DriverManagementService.fetchAllDrivers(function(response){
				$scope.drivers = response.data.data.object;
		});			
		}
	}
    
    $scope.addOrEditDriverProfile = function(driver){
		$scope.driver = driver;
	}
    
    $scope.addOrEditDriverBeacon = function(driverbeacon){
    	$scope.driverbeacon = driverbeacon;
    	DriverManagementService.fetchAllUnmappedDriverBeacons(function(response){
    		$scope.driverbeaconsdata = response.data.data.object;
    	});
/*    	NewDeviceManagementService.fetchAlldriverbeacons(function(response){
 		   $scope.beacons = response.data.data.object;
 	});*/
    }

    $scope.fetchDriverByEmpId = function(empId){
    	$scope.empId = empId;
    DriverManagementService.fetchDriverByEmpId($scope.empId,function(response){
		$scope.driverbeacon = response.data.data.object;
	});
    }
    
	 /*$scope.showAdvanced = function(ev) {
		    $mdDialog.show({
		      controller: DialogController,
		      templateUrl: 'serf.dialog.html',
		      parent: angular.element(document.body),
		      targetEvent: ev,
		      clickOutsideToClose:true
		    })
		    .then(function(answer) {
		      $scope.status = 'You said the information was "' + answer + '".';
		    }, function() {
		      $scope.status = 'You cancelled the dialog.';
		    });
		  };*/
		  
	DriverManagementService.fetchAllDrivers($scope.currentPage, $scope.pageSize, function(response){
		if(response.call){
			$scope.drivers = response.data.data.object.content;
			$scope.totalElements = response.data.data.object.totalElements;
            $scope.totalPages = response.data.data.object.totalPages;
            $scope.page = response.data.data.object.number+1;
            $scope.numberOfElements = response.data.data.object.numberOfElements;
            $scope.size = response.data.data.object.size;
		}
	});
	
	$scope.pageChanged = function(page){/*pageClicked,totalPages, size*/
        $scope.newPage = page;
        DriverManagementService.fetchAllDrivers($scope.newPage, $scope.pageSize, function(response){
            $scope.drivers = response.data.data.object.content;
            $scope.totalElements = response.data.data.object.totalElements;
            $scope.totalPages = response.data.data.object.totalPages;
            $scope.page = response.data.data.object.number+1;
            $scope.numberOfElements = response.data.data.object.numberOfElements;
            $scope.size = response.data.data.object.size;
    });        
    }
	
	$scope.driverBeaconpageChanged = function(page){/*pageClicked,totalPages, size*/
        $scope.newPage = page;
        DriverManagementService.fetchAllDriverBeacons($scope.newPage, $scope.pageSize, function(response){
            $scope.driverbeacons = response.data.data.object.content;
            $scope.totalElements = response.data.data.object.totalElements;
            $scope.totalPages = response.data.data.object.totalPages;
            $scope.page = response.data.data.object.number+1;
            $scope.numberOfElements = response.data.data.object.numberOfElements;
            $scope.size = response.data.data.object.size;
    });        
    }
	
	/*$scope.save = function(driver) {
		if($scope.driver.driverRole == 'PERMANENT'){
			$scope.driver.contractAgency = '';
			$scope.driver.contractExpiryDate = '';
		}
        if ($scope.driver.id) {
        	DriverManagementService.updateDriver($scope.driver, $scope.driver.id, function(response){
        		if(response.call){
        			$('#form-drAdd').modal('hide');
        			DriverManagementService.fetchAllDrivers(function(response){
        				if(response.call){
        					$scope.drivers = response.data.data.object;
        				}
        			});
        		}
        	});
        }else{
        	DriverManagementService.saveDriver($scope.driver, function(response){
        		if(response.call){
        			$('#form-drAdd').modal('hide');
        			DriverManagementService.fetchAllDrivers(function(response){
        				if(response.call){
        					$scope.drivers = response.data.data.object;
        				}
        			});
        		}
        	});
        }
      };*/
	$scope.save = function(driver) {
		if($scope.driver.driverRole == 'PERMANENT'){
			$scope.driver.contractAgency = '';
			$scope.driver.contractExpiryDate = '';
		}
		if(!$scope.driver.id){
			DriverManagementService.saveDriver($scope.driver, function(response){
			
        		if(response.data.data.statusCode == 200){
		            $scope.succsmessage = response.data.data.message;
        			$timeout(function () {
    		            $scope.succsmessage = '';
    		            $('#form-drAdd').modal('hide');            			
            			$route.reload();
    		            /*DriverManagementService.fetchAllDrivers(function(response){
            				if(response.call){
            					$scope.drivers = response.data.data.object;
            				}
            			});*/
        		    }, 3000, true);
        		}else{
        			$scope.errmessage = response.data.data.message;
        			/*$timeout(function () {
    		            $scope.errmessage = '';
    		        }, 3000, true);*/
        		}
        	});
		}else{
			DriverManagementService.updateDriver($scope.driver, $scope.driver.id, function(response){
        		if(response.data.data.statusCode == 200){
		            $scope.succsmessage = response.data.data.message;
        			$timeout(function(){
    		            $scope.succsmessage = '';
            			$('#form-drAdd').modal('hide');
            			$route.reload();
    		        }, 3000, true);
        		}else{
		            $scope.errmessage = response.data.data.message;
/*        			$timeout(function(){
            			$scope.errmessage='';
    		        }, 3000, true);*/
        		}
        	});
        }
      };
      
      $scope.saveDriverBeacon = function(driverbeacon) {

    	  DriverManagementService.saveDriverBeacon($scope.driverbeacon, function(response){
    			
        		if(response.data.data.statusCode == 200){
		            $scope.succsmessage = response.data.data.message;
        			$timeout(function () {
    		            $scope.succsmessage = '';
    		            $('#form-dbm').modal('hide');            			
            			
    		            /*DriverManagementService.fetchAllDriverBeacons(function(response){
          				if(response.call){
          					$scope.driverbeacons = response.data.data.object;
          				}
          			});*/$route.reload();
        		    }, 3000, true);
        		}else{
        			$scope.errmessage = response.data.data.message;
/*        			$timeout(function () {
    		            $scope.errmessage = '';
    		        }, 3000, true);*/
        		}
        	});
    	
    	          };
     
      /*$scope.saveDriverBeacon = function(driverbeacon) {
  		if(!$scope.driverbeacon.id){
  			DriverManagementService.saveDriverBeacon($scope.driverbeacon, function(response){
  			
          		if(response.data.data.statusCode == 200){
  		            $scope.succsmessage = response.data.data.message;
          			$timeout(function () {
      		            $scope.succsmessage = '';
      		            $('#form-dbm').modal('hide');            			
              			
      		            DriverManagementService.fetchAllDriverBeacons(function(response){
            				if(response.call){
            					$scope.driverbeacons = response.data.data.object;
            				}
            			});
          		    }, 3000, true);
          		}else{
          			$scope.errmessage = response.data.data.message;
          			$timeout(function () {
      		            $scope.errmessage = '';
      		        }, 3000, true);
          		}
          	});
  		}else{
  			DriverManagementService.updateDriverBeacon($scope.driverbeacon, $scope.driverbeacon.id, function(response){
          		if(response.data.data.statusCode == 200){
  		            $scope.succsmessage = response.data.data.message;
          			$timeout(function(){
      		            $scope.succsmessage = '';
              			$('#form-dbm').modal('hide');
              			DriverManagementService.fetchAllDriverBeacons(function(response){
            				if(response.call){
            					$scope.driverbeacons = response.data.data.object;
            				}
            			});
      		        }, 3000, true);
          		}else{
  		            $scope.errmessage = response.data.data.message;
          			$timeout(function(){
              			$scope.errmessage='';
      		        }, 3000, true);
          		}
          	});
          }
        };*/
$scope.example = {
                currentDate: new Date()

              };

}
]);
