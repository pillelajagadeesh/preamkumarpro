appRaamApp.service('MineService', ['$http', 'RESOURCES', 
                        function ($http, RESOURCES) {
$http.defaults.headers.post["Accept"]="application/json";
$http.defaults.useXDomain = true;
var urlBase = RESOURCES.PROD_DOMAIN;
var service={};
var responseData={};

   service.fetchAllMines=function(callback){
	var headers={"Accept": "application/json", "Content-type":"application/json"};
		$http.get(urlBase+"/mine/getAllMines").then(function(response){
		responseData.call = true;
		  responseData.data=response;
		  callback(responseData);        
    });
   };
   
	  
   return service;
}]);