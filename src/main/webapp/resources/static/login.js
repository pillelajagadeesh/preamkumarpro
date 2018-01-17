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
var appRaamLogin = angular.module('AppRaamLabsLogin', 
[
'ngAnimate',
   'ngResource',
   'ngRoute',
   'ngSanitize',
   'ngTouch',
   'ngCookies'
/*   'moment-picker',
   'bw.paging'*/
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
/*       .when('/index', {
           templateUrl: './app/hawkeye/hawkeye.html',
           title: 'Hawkeye',
           controller : 'HawkeyeController'
       })*/
       .when('/index',{
           templateUrl: '/index.html',
           title: 'Maanyavahika',
/*           controller : 'LoginController'*/
       })
/*      .otherwise({
           redirectTo: '/index.html'
       });*/     
 }]);
