appRaamApp.service('VehicleManagementService', ['$rootScope', '$http', 'RESOURCES', 
                                                function ($rootScope, $http, RESOURCES) {
$http.defaults.headers.post["Accept"]="application/json";
$http.defaults.useXDomain = true;
var urlBase = RESOURCES.PROD_DOMAIN;
//var urlBase = 'https://api.mlab.com/api/1/databases/tollplus/collections/VehicleList?apiKey=nKb7MfbwOct6o0Kvb27ca7e3nFLi1DXv';
var service={};
var responseData={};

   service.fetchAllVehicles=function(page, size, callback){
	var headers={"Accept": "application/json", "Content-type":"application/json"};
		$http.get(urlBase+"/vehicle/getAll?page="+page+"&size="+size).then(function(response){
		responseData.call = true;
		  responseData.data=response;
		  callback(responseData);        
    });
   };
   
	service.fetchAllVehicleSerfs=function(page, size, callback){
	  var headers={"Accept": "application/json", "Content-type":"application/json"};
		   $http.get(urlBase+"/vehicle/getAllMappedVehicles?page="+page+"&size="+size).then(function(response){
		   responseData.call = true;
		   responseData.data=response;
		   callback(responseData);
	});
	};
	service.searchVehicleByText=function(text, callback){
		var headers={"Accept": "application/json", "Content-type":"application/json"};
		$http.get(urlBase+'/vehicle/search/'+text).then(function(response){
			//$http.get(urlBase).then(function(response){
			responseData.call = true;
			  responseData.data=response;
			  callback(responseData);
	    });
	   };
	service.fetchVehicleByVehicleNo=function(vehicleNo, callback){
		  var headers={"Accept": "application/json", "Content-type":"application/json"};
			   $http.get(urlBase+"/vehicle/get-by-vehicleNo/"+vehicleNo).then(function(response){
			   responseData.call = true;
			   responseData.data=response;
			   callback(responseData);
		});
		};
	
	   service.fetchAllUnmappedVehiclesAndSerfs=function(callback){
			var headers={"Accept": "application/json", "Content-type":"application/json"};
				$http.get(urlBase+"/vehicleSerf/getAllUnmappedVehicleSerfs").then(function(response){
				responseData.call = true;
				  responseData.data=response;
				  callback(responseData);        
		    });
		   };
		   
	service.saveVehicle=function(data, callback){
		var headers={"Accept": "application/json", "Content-type":"application/json"};
		$http.post(urlBase+'/vehicle/save', data).then(function(response){
			responseData.call = true;
			  responseData.data=response;
			  callback(responseData);	        
	    });
	};
	
	service.saveVehicleSerf=function(data, callback){
		var headers={"Accept": "application/json", "Content-type":"application/json"};
		$http.post(urlBase+'/vehicleSerf/save', data).then(function(response){
			responseData.call = true;
			  responseData.data=response;
			  callback(responseData);	        
	    });
	};
	
	service.updateVehicle=function(data, id, callback){
		var headers={"Accept": "application/json", "Content-type":"application/json"};
		$http.post(urlBase+'/vehicle/update/'+id, data).then(function(response){
			responseData.call = true;
			  responseData.data=response;
			  callback(responseData);	        
	    });
	};
	
	service.updateVehicleSerf=function(data, id, callback){
		var headers={"Accept": "application/json", "Content-type":"application/json"};
		$http.post(urlBase+'/vehicleSerf/update/vehicleserf/'+id, data).then(function(response){
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