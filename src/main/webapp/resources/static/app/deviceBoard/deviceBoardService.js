appRaamApp.service('DeviceBoardService', ['$rootScope', '$http', 'RESOURCES', 
                  function ($rootScope, $http, RESOURCES) {
$http.defaults.headers.post["Accept"]="application/json";
$http.defaults.useXDomain = true;
var urlBase = RESOURCES.PROD_DOMAIN;
var service={};

return service;
}]);