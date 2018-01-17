appRaamApp.service('IndexService', ['$rootScope', '$http', 'RESOURCES', 
                  function ($rootScope, $http, RESOURCES) {
$http.defaults.headers.post["Accept"]="application/json";
$http.defaults.useXDomain = true;
var urlBase = RESOURCES.PROD_DOMAIN;
var service={};
var responseData={};

service.fetchOpenedIssues=function(callback){
	var headers={"Accept": "application/json", "Content-type":"application/json"};
		$http.get(urlBase+"/issues/get-issues-opened-drivers").then(function(response){
		responseData.call = true;
		  responseData.data=response;
		  callback(responseData);
    });
   }

return service;
}]);