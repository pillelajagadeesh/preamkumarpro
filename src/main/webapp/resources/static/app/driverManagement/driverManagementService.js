appRaamApp.service('DriverManagementService', ['$rootScope', '$http', 'RESOURCES',
                                               function ($rootScope, $http, RESOURCES) {
$http.defaults.headers.post["Accept"]="application/json";
$http.defaults.useXDomain = true;
var urlBase = RESOURCES.PROD_DOMAIN;
//var urlBase = 'https://api.mlab.com/api/1/databases/tollplus/collections/DriverList?apiKey=nKb7MfbwOct6o0Kvb27ca7e3nFLi1DXv';
var service={};
var responseData={};

   service.fetchAllDrivers=function(page, size, callback){
	var headers={"Accept": "application/json", "Content-type":"application/json"};
		$http.get(urlBase+"/driverProfile/getAll?page="+page+"&size="+size).then(function(response){
		responseData.call = true;
		  responseData.data=response;
		  callback(responseData);
    });
   }
   
	service.fetchAllDriverBeacons=function(page, size, callback){
		var headers={"Accept": "application/json", "Content-type":"application/json"};
		$http.get(urlBase+"/driverProfile/getAllUnmappedDrivers?page="+page+"&size="+size).then(function(response){
		responseData.call = true;
		  responseData.data=response;
		  callback(responseData);
    });
	}
	
	service.fetchDriverByEmpId=function(empId, callback){
		var headers={"Accept": "application/json", "Content-type":"application/json"};
		$http.get(urlBase+"/driverProfile/get-by-empId/"+empId).then(function(response){
		responseData.call = true;
		  responseData.data=response;
		  callback(responseData);
    });
	}
	
	service.searchDriversByText=function(text, callback){
		var headers={"Accept": "application/json", "Content-type":"application/json"};
		$http.get(urlBase+'/driverProfile/search/'+text).then(function(response){
			//$http.get(urlBase).then(function(response){
			responseData.call = true;
			  responseData.data=response;
			  callback(responseData);
	    });
	   };
	
	service.fetchAllUnmappedDriverBeacons=function(callback){
		var headers={"Accept": "application/json", "Content-type":"application/json"};
		$http.get(urlBase+"/driverBeacon/getAllUnmappedDriverBeacons").then(function(response){
		responseData.call = true;
		  responseData.data=response;
		  callback(responseData);
    });
	}
	
	service.saveDriver=function(data, callback){
		var headers={"Accept": "application/json", "Content-type":"application/json"};
		$http.post(urlBase+"/driverProfile/save", data).then(function(response){
			responseData.call = true;
			  responseData.data=response;
			  callback(responseData);
	    });
	};
	
	service.saveDriverBeacon=function(data, callback){
		var headers={"Accept": "application/json", "Content-type":"application/json"};
		$http.post(urlBase+"/driverBeacon/save", data).then(function(response){
			responseData.call = true;
			  responseData.data=response;
			  callback(responseData);
	    });
	};
	
	service.updateDriver=function(data, id, callback){
		var headers={"Accept": "application/json", "Content-type":"application/json"};
		$http.put(urlBase+"/driverProfile/update/"+id, data).then(function(response){
			responseData.call = true;
			  responseData.data=response;
			  callback(responseData);
	    });
	};
	
	service.updateDriverBeacon=function(data, id, callback){
		var headers={"Accept": "application/json", "Content-type":"application/json"};
		$http.put(urlBase+"/driverBeacon/update/driverbeacon/"+id, data).then(function(response){
			responseData.call = true;
			  responseData.data=response;
			  callback(responseData);
	    });
	};
	/*$http.get(urlBase+'/getAll',headers).success(function(response){
		  console.log(response);
		  responseData.call = true;
		  responseData.data=response;
		  callback(responseData);
	  }).error(function(response){
		  responseData.call = false;
		  responseData.data=response;
		  callback(responseData);
	  });*/
	  
return service;
}]);