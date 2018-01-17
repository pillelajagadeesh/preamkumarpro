appRaamApp.service('ConfigurationService', ['$rootScope', '$http', 'RESOURCES', 
                  function ($rootScope, $http, RESOURCES) {
$http.defaults.headers.post["Accept"]="application/json";
$http.defaults.useXDomain = true;
var urlBase = RESOURCES.PROD_DOMAIN;
var service={};
var responseData={};

service.saveConfiguration=function(data, callback){
	var headers={"Accept": "application/json", "Content-type":"application/json"};
	$http.post(urlBase+"/configuration/saveConfigutaionStatus", data).then(function(response){
		responseData.call = true;
		  responseData.data=response;
		  callback(responseData);
    });
};


service.fetchAllConfig=function(callback){
	var headers={"Accept": "application/json", "Content-type":"application/json"};
		$http.get(urlBase+"/configuration/getAllConfigurationStatus").then(function(response){
		responseData.call = true;
		  responseData.data=response;
		  callback(responseData);
    });
   }


	
return service;
}]);