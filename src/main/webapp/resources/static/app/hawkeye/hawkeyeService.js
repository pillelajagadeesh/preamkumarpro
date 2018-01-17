appRaamApp.service('HawkeyeService', ['$rootScope', '$http', 'RESOURCES', function ($rootScope, $http, RESOURCES) {

	$http.defaults.headers.post["Accept"]="application/json";
	$http.defaults.useXDomain = true;
	var urlBase = RESOURCES.PROD_DOMAIN;
	var service={};
	var responseData={};

	   service.fetchAllTrips=function(callback){
		var headers={"Accept": "application/json", "Content-type":"application/json"};
			$http.get(urlBase+"/hawkeye/getAllObdStatus").then(function(response){
			responseData.call = true;
			  responseData.data=response;
			  callback(responseData);
	    });
	   }
		  
	   service.fetchAllWPBeacons=function(callback){
			var headers={"Accept": "application/json", "Content-type":"application/json"};
				$http.get(urlBase+"/hawkeye/getAllWaypointBeacons").then(function(response){
				responseData.call = true;
				  responseData.data=response;
				  callback(responseData);
		    });
		   }
	   
	   service.fetchVehicleDriver=function(serfId, driverId, callback){
			var headers={"Accept": "application/json", "Content-type":"application/json"};
				$http.get(urlBase+"/hawkeye/getDriverAndVehicleDetails/"+serfId+"/"+driverId).then(function(response){
				responseData.call = true;
				  responseData.data=response;
				  callback(responseData);
		    });
		   }
/*	   service.fetchUserAndRole=function(callback){
			var headers={"Accept": "application/json", "Content-type":"application/json"};
				$http.get(urlBase+"/pentaho/getUserAndRole").then(function(response){
				responseData.call = true;
				  responseData.data=response;
				  callback(responseData);
		    });
	   }*/
	return service;
}]);