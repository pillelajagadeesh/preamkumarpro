appRaamApp.service('ReportsService', ['$rootScope', '$http', function ($rootScope, $http) {
//$http.defaults.headers.post["Accept"]="application/json";
/*$http.defaults.useXDomain = true;*/
$http.defaults.useXDomain = true;
//var urlBase = '/appRaamLabs/api/';
var service={};
var responseData={};

service.fetchAllReports=function(callback){
/*	var headers={"Accept": "text/html", "Content-type":"text/html"};*/
/*		$http.get("http://192.168.1.19:8080/pentaho/api/repos/%3Ahome%3Adriverreport.prpt/viewer?ts=1493721746378&userid=admin&password=password").then(function(response){
		responseData.call = true;
		  responseData.data=response;
		  callback(responseData);*/
	$http({
	      url: 'http://192.168.1.19:8080/pentaho/api/repos/%3Ahome%3Adriverreport.prpt/viewer?ts=1493721746378&userid=admin&password=password',
	      method: 'GET'
	    }).then(function(response){
	          console.log("successfully");
	          responseData.call = true;
			  responseData.data=response;
			  callback(responseData);
	    }, function(error){
	       console.log(error);
	       responseData.call = false;
			  responseData.data=error;
			  callback(responseData);
	    });
// });
};
return service;
}]);