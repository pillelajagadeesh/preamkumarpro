/*(function() {*/
'use strict';

/**
* @ngdoc overview
* @name newappApp
* @description
* # newappApp
*
* Main module of the application.
*/
var appRaamApp = angular.module('AppRaamLabsApp', 
[
'ngAnimate',
   'ngResource',
   'ngRoute',
   'ngSanitize',
   'ngTouch',
   'moment-picker',
   'ngCookies',
   'ngSanitize',
   'bw.paging'
/*   'uiGmapgoogle-maps'*/
]
).config([  
         '$locationProvider',
         function($locationProvider) {
             $locationProvider.html5Mode(false);
             $locationProvider.hashPrefix('!');
         }
     ])
.config(['$routeProvider',  function ($routeProvider) {
     $routeProvider
       .when('/404', {
           templateUrl: '404.html'
       })
       .when('/hawkeye', {
           templateUrl: './app/hawkeye/hawkeye.html',
           title: 'Hawkeye',
           controller : 'HawkeyeController'
       })
       .when('/storyboard', {
           templateUrl: './app/storyboard/storyboard.html',
           controller : 'StoryboardController'
       })
       .when('/reports', {
           templateUrl: './app/reports/reports.html',
           controller : 'ReportsController'
       })
       .when('/vehicleManagement', {
           templateUrl: './app/vehicleManagement/vehicleManagement.html',
           controller : 'VehicleManagementController'
       })
       .when('/drivermanagement', {
           templateUrl: './app/driverManagement/driverManagement.html',
           controller : 'DriverManagementController'
       })
       .when('/newdevicemanagement', {
           templateUrl: './app/newDeviceManagement/newDeviceManagement.html',
           controller : 'NewDeviceManagementController'
       })
       .when('/configuration', {
           templateUrl: './app/configuration/configuration.html',
           controller : 'ConfigurationController'
       })
       .when('/deviceBoard', {
           templateUrl: './app/deviceBoard/deviceBoard.html',
           controller : 'DeviceBoardController'
       })
       .when('/', {
           templateUrl: './app/hawkeye/hawkeye.html',
           title: 'Hawkeye',
           controller : 'HawkeyeController'
       })
/*      .otherwise({
           redirectTo: '/index.html'
       });*/     
 }]);
 /*.directive('input',[function(){
 return {
 restrict:'E',
 link :function($scope, element, attributes) {
            element.bind('focus',function(){
            	
             $(this).removeClass('hasError');
            
            });
            element.bind('blur',function(){
   if(!$(this).hasClass('ng-valid')){
                   $(this).addClass('hasError');
                  	}else{
                   $(this).removeClass('hasError');
                  	}
           });
            
    }
 };
 }])*/
/*})();*/