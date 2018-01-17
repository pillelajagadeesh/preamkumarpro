appRaamLogin.service('LoginService', ['$rootScope', '$http', 'RESOURCEURL',
                  function ($rootScope, $http, RESOURCEURL) {
$http.defaults.headers.post["Accept"]="application/json";
$http.defaults.useXDomain = true;
var urlBase = RESOURCEURL.PROD_DOMAIN;
var service={};
var responseData={};

service.userLogin=function(data,callback){
	var headers={"Accept": "application/json", "Content-type":"application/json"};
		$http.post(urlBase+"/usermysql/uservalidation", data).then(function(response){
		responseData.call = true;
		  responseData.data=response;
		  callback(responseData);
    });
   }

return service;
}]);