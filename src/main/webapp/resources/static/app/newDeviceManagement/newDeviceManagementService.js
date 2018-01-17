appRaamApp.service('NewDeviceManagementService', ['$rootScope', '$http', 'RESOURCES', 
                  function ($rootScope, $http, RESOURCES) {
$http.defaults.headers.post["Accept"]="application/json";
$http.defaults.useXDomain = true;
var urlBase = RESOURCES.PROD_DOMAIN;
//var urlBase = 'https://api.mlab.com/api/1/databases/tollplus/collections/serfs?apiKey=nKb7MfbwOct6o0Kvb27ca7e3nFLi1DXv';
var service={};
var responseData={};

   service.fetchAllSerfs=function(page, size, callback){
	var headers={"Accept": "application/json", "Content-type":"application/json"};
	$http.get(urlBase+'/serf/getAll?page='+page+"&size="+size).then(function(response){
		//$http.get(urlBase).then(function(response){
		responseData.call = true;
		  responseData.data=response;
		  callback(responseData);
    });
   };
   
   service.fetchAllwpbeacons=function(page, size, callback){
		var headers={"Accept": "application/json", "Content-type":"application/json"};
		$http.get(urlBase+'/beacon/getAllwpbeacons?page='+page+"&size="+size).then(function(response){
			responseData.call = true;
			  responseData.data=response;
			  callback(responseData);
	    });
	   };
	   
	service.fetchAlldriverbeacons=function(page, size, callback){
			var headers={"Accept": "application/json", "Content-type":"application/json"};
			$http.get(urlBase+'/beacon/getAlldriverbeacons?page='+page+"&size="+size).then(function(response){
				responseData.call = true;
				  responseData.data=response;
				  callback(responseData);
		 });
		};
		
		service.searchSerfsByText=function(text, callback){
			var headers={"Accept": "application/json", "Content-type":"application/json"};
			$http.get(urlBase+'/serf/search/'+text).then(function(response){
				//$http.get(urlBase).then(function(response){
				responseData.call = true;
				  responseData.data=response;
				  callback(responseData);
		    });
		   };
		   
			service.searchWPBeaconsByText=function(text, callback){
				var headers={"Accept": "application/json", "Content-type":"application/json"};
				$http.get(urlBase+'/beacon/searchwpbeacon/'+text).then(function(response){
					//$http.get(urlBase).then(function(response){
					responseData.call = true;
					  responseData.data=response;
					  callback(responseData);
			    });
			   };
			   
				service.searchDriverBeaconsByText=function(text, callback){
					var headers={"Accept": "application/json", "Content-type":"application/json"};
					$http.get(urlBase+'/beacon/searchdriverbeacon/'+text).then(function(response){
						//$http.get(urlBase).then(function(response){
						responseData.call = true;
						  responseData.data=response;
						  callback(responseData);
				    });
				   };
				   
	service.saveSerf=function(data, callback){
		var headers={"Accept": "application/json", "Content-type":"application/json"};
		$http.post(urlBase+'/serf/save', data).then(function(response){
			responseData.call = true;
			  responseData.data=response;
			  callback(responseData);	        
	    });
	};
	
	service.saveWpbeacon=function(data, callback){
		var headers={"Accept": "application/json", "Content-type":"application/json"};
		$http.post(urlBase+'/beacon/save/wpbeacon', data).then(function(response){
			responseData.call = true;
			  responseData.data=response;
			  callback(responseData);	        
	    });
	};
	
	service.saveDriverbeacon=function(data, callback){
		var headers={"Accept": "application/json", "Content-type":"application/json"};
		$http.post(urlBase+'/beacon/save/driverbeacon', data).then(function(response){
			responseData.call = true;
			  responseData.data=response;
			  callback(responseData);	        
	    });
	};
	
	service.updateSerf=function(data, id, callback){
		var headers={"Accept": "application/json", "Content-type":"application/json"};
		$http.put(urlBase+'/serf/update/'+id, data).then(function(response){
			responseData.call = true;
			  responseData.data=response;
			  callback(responseData);
	    });
	};
	
	service.updateWpbeacon=function(data, id, callback){
		var headers={"Accept": "application/json", "Content-type":"application/json"};
		$http.put(urlBase+'/beacon/update/wpbeacon/'+id, data).then(function(response){
			responseData.call = true;
			  responseData.data=response;
			  callback(responseData);
	    });
	};
	
	service.updateDriverbeacon=function(data, id, callback){
		var headers={"Accept": "application/json", "Content-type":"application/json"};
		$http.put(urlBase+'/beacon/update/driverbeacon/'+id, data).then(function(response){
			responseData.call = true;
			  responseData.data=response;
			  callback(responseData);
	    });
	};

	service.validateAndupdateSerf=function(data, macId, callback){
		var headers={"Accept": "application/json", "Content-type":"application/json"};
		$http.put(urlBase+'/serf/validateUpdate/'+macId, data).then(function(response){
			responseData.call = true;
			  responseData.data=response;
			  callback(responseData);
	    });
	};
	
	service.rollbackSerf=function(data, macId, callback){
		var headers={"Accept": "application/json", "Content-type":"application/json"};
		$http.put(urlBase+'/serf/rollback/'+macId, data).then(function(response){
			responseData.call = true;
			  responseData.data=response;
			  callback(responseData);
	    });
	};
	
	service.rollbackWpBeacon=function(data, macId, callback){
		var headers={"Accept": "application/json", "Content-type":"application/json"};
		$http.put(urlBase+'/beacon/rollback/wpbeacon/'+macId, data).then(function(response){
			responseData.call = true;
			  responseData.data=response;
			  callback(responseData);
	    });
	};
	
	service.rollbackDriverBeacon=function(data, macId, callback){
		var headers={"Accept": "application/json", "Content-type":"application/json"};
		$http.put(urlBase+'/beacon/rollback/driverbeacon/'+macId, data).then(function(response){
			responseData.call = true;
			  responseData.data=response;
			  callback(responseData);
	    });
	};
	
	service.restoreSerf=function(data, macId, callback){
		var headers={"Accept": "application/json", "Content-type":"application/json"};
		$http.put(urlBase+'/serf/restore/'+macId, data).then(function(response){
			responseData.call = true;
			  responseData.data=response;
			  callback(responseData);
	    });
	};
	
return service;
}]);