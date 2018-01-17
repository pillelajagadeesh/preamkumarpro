appRaamApp.controller('NewDeviceManagementController', ['$scope', '$timeout', '$route', '$rootScope', '$interval', '$location', 'NewDeviceManagementService', 'MineService', '$window',
function ($scope, $timeout, $route, $rootScope, $interval, $location, NewDeviceManagementService,  MineService, $window) {

	$scope.tab = 1;
	$scope.currentPage = 1;
    $scope.pageSize = 10;
    
	MineService.fetchAllMines(function(response){
		if(response.call){
			$scope.mines = response.data.data.object;
		}
	});
	
	
	/*macAddress*/
	/*var macAddress = $("#macAddress");
	var macAddresswp = $("#macAddresswp");
	var macAddressid = $("#macAddressid");*/
    $scope.setTab = function(newValue){
      $scope.tab = newValue;
      if($scope.tab == 1){
    		NewDeviceManagementService.fetchAllSerfs($scope.currentPage, $scope.pageSize, function(response){
    			$scope.serfs = response.data.data.object.content;
    			$scope.totalElements = response.data.data.object.totalElements;
    			$scope.totalPages = response.data.data.object.totalPages;
    			$scope.page = response.data.data.object.number+1;
    			$scope.numberOfElements = response.data.data.object.numberOfElements;
 			   $scope.size = response.data.data.object.size;
    	});
      }else if($scope.tab == 2){
    		NewDeviceManagementService.fetchAllwpbeacons($scope.currentPage, $scope.pageSize, function(response){
    			   $scope.wpbeacons = response.data.data.object.content;
    			   $scope.totalElements = response.data.data.object.totalElements;
       			   $scope.totalPages = response.data.data.object.totalPages;
       			   $scope.page = response.data.data.object.number+1;
       			   $scope.numberOfElements = response.data.data.object.numberOfElements;
    			   $scope.size = response.data.data.object.size;
    		});
      }else if($scope.tab == 3){    		
    		NewDeviceManagementService.fetchAlldriverbeacons($scope.currentPage, $scope.pageSize, function(response){
    			   $scope.driverbeacons = response.data.data.object.content;
    			   $scope.totalElements = response.data.data.object.totalElements;
       			   $scope.totalPages = response.data.data.object.totalPages;
       			   $scope.page = response.data.data.object.number+1;
       			   $scope.numberOfElements = response.data.data.object.numberOfElements;
    			   $scope.size = response.data.data.object.size;
    		});
 }
    };

    $scope.isSet = function(tabName){
      return $scope.tab === tabName;
    };
	/*$scope.isSet = function(route) {
        return route === $location.path();
    };*/
    
	NewDeviceManagementService.fetchAllSerfs($scope.currentPage, $scope.pageSize, function(response){
			$scope.serfs = response.data.data.object.content;
			$scope.totalElements = response.data.data.object.totalElements;
			$scope.totalPages = response.data.data.object.totalPages;
			$scope.page = response.data.data.object.number+1;
			$scope.numberOfElements = response.data.data.object.numberOfElements;
			$scope.size = response.data.data.object.size;
	});
	
	$scope.addOrEditSerf = function(serf){
		$scope.serf = serf;
	}
	
	$scope.addOrEditWpbeacon = function(wpbeacon){
		$scope.wpbeacon = wpbeacon;
	}
	
	$scope.addOrEditDriverbeacon = function(driverbeacon){
		$scope.driverbeacon = driverbeacon;
		
	}

	$scope.pageChanged = function(page){/*pageClicked,totalPages, size*/
		$scope.newPage = page;
		NewDeviceManagementService.fetchAllSerfs($scope.newPage, $scope.pageSize, function(response){
			$scope.serfs = response.data.data.object.content;
			$scope.totalElements = response.data.data.object.totalElements;
			$scope.totalPages = response.data.data.object.totalPages;
			$scope.page = response.data.data.object.number+1;
			$scope.numberOfElements = response.data.data.object.numberOfElements;
			$scope.size = response.data.data.object.size;
	});		
	}
		$scope.wppageChanged = function(page){
			$scope.newPage = page;
    		NewDeviceManagementService.fetchAllwpbeacons($scope.newPage, $scope.pageSize, function(response){
 			   $scope.wpbeacons = response.data.data.object.content;
 			   $scope.totalElements = response.data.data.object.totalElements;
    			   $scope.totalPages = response.data.data.object.totalPages;
    			   $scope.page = response.data.data.object.number+1;
    			   $scope.numberOfElements = response.data.data.object.numberOfElements;
    			   $scope.size = response.data.data.object.size;
		});	
	}
		
		$scope.driverpageChanged = function(page){
			$scope.newPage = page;
    		NewDeviceManagementService.fetchAlldriverbeacons($scope.newPage, $scope.pageSize, function(response){
 			   $scope.driverbeacons = response.data.data.object.content;
 			   $scope.totalElements = response.data.data.object.totalElements;
    			   $scope.totalPages = response.data.data.object.totalPages;
    			   $scope.page = response.data.data.object.number+1;
    			   $scope.numberOfElements = response.data.data.object.numberOfElements;
    			   $scope.size = response.data.data.object.size;
		});	
		}
		
	$scope.searchSerf = function(text){
		if($scope.text){
		NewDeviceManagementService.searchSerfsByText($scope.text, function(response){
			$scope.serfs = response.data.data.object;
		});
		}else{
			NewDeviceManagementService.fetchAllSerfs($scope.currentPage, $scope.pageSize, function(response){
				$scope.serfs = response.data.data.object.content;
    			$scope.totalElements = response.data.data.object.totalElements;
    			$scope.totalPages = response.data.data.object.totalPages;
    			$scope.page = response.data.data.object.number+1;
    			$scope.numberOfElements = response.data.data.object.numberOfElements;
 			   $scope.size = response.data.data.object.size;
		});			
		}
	}
	
	$scope.searchWPBeacon = function(text){
		if($scope.text){
		NewDeviceManagementService.searchWPBeaconsByText($scope.text, function(response){
			$scope.wpbeacons = response.data.data.object;
		});
		}else{
			NewDeviceManagementService.fetchAllwpbeacons($scope.currentPage, $scope.pageSize,function(response){
 			   $scope.wpbeacons = response.data.data.object.content;
			   $scope.totalElements = response.data.data.object.totalElements;
   			   $scope.totalPages = response.data.data.object.totalPages;
   			   $scope.page = response.data.data.object.number+1;
   			   $scope.numberOfElements = response.data.data.object.numberOfElements;
			   $scope.size = response.data.data.object.size;
 		});
		}
	}
	
	$scope.searchDriverBeacon = function(text){
		if($scope.text){
		NewDeviceManagementService.searchDriverBeaconsByText($scope.text, function(response){
			$scope.driverbeacons = response.data.data.object;
		});
		}else{
			NewDeviceManagementService.fetchAlldriverbeacons($scope.currentPage, $scope.pageSize, function(response){
 			   $scope.driverbeacons = response.data.data.object.content;
			   $scope.totalElements = response.data.data.object.totalElements;
   			   $scope.totalPages = response.data.data.object.totalPages;
   			   $scope.page = response.data.data.object.number+1;
   			   $scope.numberOfElements = response.data.data.object.numberOfElements;
			   $scope.size = response.data.data.object.size;
 		});
		}
	}
	
	$scope.save = function(serf) {
		if(!$scope.serf.id){
			NewDeviceManagementService.saveSerf($scope.serf, function(response){
        		if(response.data.data.statusCode == 200){
		            $scope.succsmessage = response.data.data.message;
        			$timeout(function () {
    		            $scope.succsmessage = '';
    		            $('#form-bp1').modal('hide');
            			//$location.path('/newdevicemanagement');
            			//$window.location.reload();
            			$route.reload();
        		    }, 3000, true);
        		}else{
        			$scope.errmessage = response.data.data.message;
        			/*$timeout(function () {
    		            $scope.errmessage = '';
    		        }, 3000, true);*/
        		}
        	});
		}else{
			NewDeviceManagementService.updateSerf($scope.serf, $scope.serf.id, function(response){
        		if(response.data.data.statusCode == 200){
		            $scope.succsmessage = response.data.data.message;
        			$timeout(function(){
    		            $scope.succsmessage = '';
            			$('#form-bp1').modal('hide');
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
      
      $scope.saveWpbeacon = function(wpbeacon) {
    	  if(!$scope.wpbeacon.id){
    		  NewDeviceManagementService.saveWpbeacon($scope.wpbeacon, function(response){
          		if(response.data.data.statusCode == 200){
		            $scope.wpsuccessmessage = response.data.data.message;
          			$timeout(function(){
    		            $scope.wpsuccessmessage = '';
                 	    $('#form-wpb').modal('hide');
                  	   NewDeviceManagementService.fetchAllwpbeacons($scope.currentPage, $scope.pageSize,function(response){
                 		   $scope.wpbeacons = response.data.data.object.content;
            			   $scope.totalElements = response.data.data.object.totalElements;
               			   $scope.totalPages = response.data.data.object.totalPages;
               			   $scope.page = response.data.data.object.number+1;
               			   $scope.numberOfElements = response.data.data.object.numberOfElements;
            			   $scope.size = response.data.data.object.size;
                 	});
    		        }, 3000, true);
          		}else{
		            $scope.wperrormessage = response.data.data.message;
/*          			$timeout(function(){
              			$scope.wperrormessage='';
    		        }, 3000, true);*/
          		}
    		  });
    	  }else{
    		  NewDeviceManagementService.updateWpbeacon($scope.wpbeacon, $scope.wpbeacon.id, function(response){
          		if(response.data.data.statusCode == 200){
		            $scope.wpsuccessmessage = response.data.data.message;
          			$timeout(function(){
              			$scope.wpsuccessmessage='';
              			$('#form-wpb').modal('hide');
              			NewDeviceManagementService.fetchAllwpbeacons($scope.currentPage, $scope.pageSize,function(response){
              			   $scope.wpbeacons = response.data.data.object.content;
            			   $scope.totalElements = response.data.data.object.totalElements;
               			   $scope.totalPages = response.data.data.object.totalPages;
               			   $scope.page = response.data.data.object.number+1;
               			   $scope.numberOfElements = response.data.data.object.numberOfElements;
            			   $scope.size = response.data.data.object.size;
              		});
    		        }, 3000, true);
          		}else{
   		            $scope.wperrormessage = response.data.data.message;
/*          			$timeout(function(){
              			$scope.wperrormessage=''; 
    		        }, 30000, true);*/
          		}
          	});
    	  }
      };
      
      $scope.saveDriverbeacon = function(driverbeacon) {
    	  if(!$scope.driverbeacon.id){
    		  NewDeviceManagementService.saveDriverbeacon($scope.driverbeacon, function(response){
          		if(response.data.data.statusCode == 200){
		            $scope.dbsuccessmessage = response.data.data.message;
          			$timeout( function(){
              			$scope.dbsuccessmessage='';
                 	    $('#form-idb').modal('hide');
                  	   NewDeviceManagementService.fetchAlldriverbeacons($scope.currentPage, $scope.pageSize, function(response){
                 		   $scope.driverbeacons = response.data.data.object.content;
            			   $scope.totalElements = response.data.data.object.totalElements;
               			   $scope.totalPages = response.data.data.object.totalPages;
               			   $scope.page = response.data.data.object.number+1;
               			   $scope.numberOfElements = response.data.data.object.numberOfElements;
            			   $scope.size = response.data.data.object.size;
                 	});
          			}, 3000 );
          		}else{
		            $scope.dberrormessage = response.data.data.message;
/*          			$timeout( function(){
              			$scope.dberrormessage='';
    		        }, 3000 );*/
          		}
    		  });
    	  }else{
    		  NewDeviceManagementService.updateDriverbeacon($scope.driverbeacon, $scope.driverbeacon.id, function(response){
          		if(response.data.data.statusCode == 200){
		            $scope.dbsuccessmessage = response.data.data.message;
          			$timeout( function(){
              			$scope.dbsuccessmessage='';
              			$('#form-idb').modal('hide');
              			NewDeviceManagementService.fetchAlldriverbeacons($scope.currentPage, $scope.pageSize, function(response){
              			   $scope.driverbeacons = response.data.data.object.content;
            			   $scope.totalElements = response.data.data.object.totalElements;
               			   $scope.totalPages = response.data.data.object.totalPages;
               			   $scope.page = response.data.data.object.number+1;
               			   $scope.numberOfElements = response.data.data.object.numberOfElements;
            			   $scope.size = response.data.data.object.size;
              		});
          			}, 3000 );
          		}else{
		            $scope.dberrormessage = response.data.data.message;
/*          			$timeout( function(){
              			$scope.dberrormessage='';
    		        }, 3000 );*/
          		}
          	});
    	  }
      };
      
      $scope.validateAndupdateSerf = function(serf){
    	  NewDeviceManagementService.validateAndupdateSerf(serf, serf.macId, function(response){
      		if(response.data.data.statusCode == 200){
	            $scope.serfressuccessmessage = response.data.data.message;
      			$timeout( function(){
          			$scope.serfressuccessmessage='';
		        }, 3000);
    		}else{
	            $scope.serfreserrormessage = response.data.data.message;
/*    			$timeout( function(){
         			$scope.serfreserrormessage='';
		        }, 3000 );*/
    		}
    	  });
      };
      
      $scope.rollbackSerf = function(serf){
    	  NewDeviceManagementService.rollbackSerf(serf, serf.macId, function(response){
      		if(response.data.data.statusCode == 200){
	            $scope.serfressuccessmessage = response.data.data.message;
      			$timeout( function(){
          			$scope.serfressuccessmessage='';
          			NewDeviceManagementService.fetchAllSerfs($scope.currentPage, $scope.pageSize, function(response){
          				$scope.serfs = response.data.data.object.content;
            			$scope.totalElements = response.data.data.object.totalElements;
            			$scope.totalPages = response.data.data.object.totalPages;
            			$scope.page = response.data.data.object.number+1;
            			$scope.numberOfElements = response.data.data.object.numberOfElements;
         			   $scope.size = response.data.data.object.size;
          		});
      			}, 3000);
    		}else{
	            $scope.serfreserrormessage = response.data.data.message;
/*    			$timeout( function(){
             		$scope.serfreserrormessage='';
		        }, 3000 );*/
    		}
    	  });
      };
	
      $scope.rollbackWpBeacon = function(wpbeacon){
    	  NewDeviceManagementService.rollbackWpBeacon(wpbeacon, wpbeacon.macId, function(response){
      		if(response.data.data.statusCode == 200){
	            $scope.wpsuccessmessage = response.data.data.message;
      			$timeout( function(){
          			$scope.wpsuccessmessage='';
          			NewDeviceManagementService.fetchAllwpbeacons($scope.currentPage, $scope.pageSize,function(response){
          			   $scope.wpbeacons = response.data.data.object.content;
        			   $scope.totalElements = response.data.data.object.totalElements;
           			   $scope.totalPages = response.data.data.object.totalPages;
           			   $scope.page = response.data.data.object.number+1;
           			   $scope.numberOfElements = response.data.data.object.numberOfElements;
        			   $scope.size = response.data.data.object.size;
          		});
		        }, 3000);
    		}else{
	            $scope.wperrormessage = response.data.data.message;
    			$timeout( function(){
         			$scope.wperrormessage='';
		        }, 3000 );
    		}   	
    	  });
      };
      
      $scope.rollbackDriverBeacon = function(driverbeacon){
    	  NewDeviceManagementService.rollbackDriverBeacon(driverbeacon, driverbeacon.macId, function(response){
      		if(response.data.data.statusCode == 200){
	            $scope.driversuccessmessage = response.data.data.message;
      			$timeout( function(){
          			$scope.driversuccessmessage='';
          			NewDeviceManagementService.fetchAlldriverbeacons($scope.currentPage, $scope.pageSize,function(response){
          			   $scope.driverbeacons = response.data.data.object.content;
        			   $scope.totalElements = response.data.data.object.totalElements;
           			   $scope.totalPages = response.data.data.object.totalPages;
           			   $scope.page = response.data.data.object.number+1;
           			   $scope.numberOfElements = response.data.data.object.numberOfElements;
        			   $scope.size = response.data.data.object.size;
          		});
		        }, 3000);
    		}else{
	            $scope.drivererrormessage = response.data.data.message;
    			$timeout(function(){
         			$scope.drivererrormessage='';
		        }, 3000);
    		}      		
    	  });
      };
      
      $scope.restoreSerf = function(serf){
    	  NewDeviceManagementService.restoreSerf(serf, serf.macId, function(response){
      		if(response.data.data.statusCode == 200){
	            $scope.serfressuccessmessage = response.data.data.message;
      			$timeout( function(){
          			$scope.serfressuccessmessage='';
          			NewDeviceManagementService.fetchAllSerfs($scope.currentPage, $scope.pageSize, function(response){
          				$scope.serfs = response.data.data.object.content;
            			$scope.totalElements = response.data.data.object.totalElements;
            			$scope.totalPages = response.data.data.object.totalPages;
            			$scope.page = response.data.data.object.number+1;
            			$scope.numberOfElements = response.data.data.object.numberOfElements;
         			   $scope.size = response.data.data.object.size;
          		});
		        }, 3000 );
    		}else{
	            $scope.serfreserrormessage = response.data.data.message;
/*    			$timeout( function(){
         			$scope.serfreserrormessage='';
		        }, 3000 );*/
    		}
    	  });
      };
      
      
      
      /*function formatMAC(e) {
    	    var r = /([a-fA-F0-9]{2})([a-fA-F0-9]{2})/i,
    	        str = e.target.value.replace(/[^a-fA-F0-9]/ig, "");
    	    
    	    while (r.test(str)) {
    	        str = str.replace(r, '$1' + ':' + '$2');
    	    }

    	    e.target.value = str.slice(0, 17);
    	};

    	macAddress.on("keyup", formatMAC);
    	macAddresswp.on("keyup", formatMAC);
    	macAddressid.on("keyup", formatMAC);*/
    	
}
]);
