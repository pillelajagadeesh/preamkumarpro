appRaamApp.service('DeviceManagementService', ['$rootScope', '$http', function ($rootScope, $http) {
$http.defaults.headers.post["Accept"]="application/json";
$http.defaults.useXDomain = true;
//var urlBase = 'http://localhost:8080/serf';
var urlBase = 'https://api.mlab.com/api/1/databases/tollplus/collections/serfs?apiKey=nKb7MfbwOct6o0Kvb27ca7e3nFLi1DXv';
var service={};
var responseData={};

   service.fetchAllSerfs=function(callback){
	var headers={"Accept": "application/json", "Content-type":"application/json"};
	//$http.get(urlBase+'/getAll').then(function(response){
		$http.get(urlBase).then(function(response){
			//console.log("Data : "+JSON.stringify(response));
		responseData.call = true;
		  responseData.data=response;
		  callback(responseData);
        //console.log("coming from servicejs", response.data);
        
    });

	service.saveSerf=function(data, callback){
		var headers={"Accept": "application/json", "Content-type":"application/json"};
		/*$http.defaults.headers.post["Accept"]="application/json";
		$http.defaults.useXDomain = true;*/
		//$http.post(urlBase+'/save', data).then(function(response){
		$http.post(urlBase, data).then(function(response){
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
	  
   };
   


return service;
}]);