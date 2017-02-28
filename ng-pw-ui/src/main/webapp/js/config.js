'use strict';

var App = angular.module('mainuxApp',['ngRoute','oc.lazyLoad','pascalprecht.translate']);

App.config(function($routeProvider) { 

	 $routeProvider
     .when('/index', {
         templateUrl: 'dashboard.html',
         controller: 'mainUXController'
     })
     .when('/login', {
         templateUrl: 'login.html',
         controller: 'mainUXController'
     }).when('/dashboard', {
         templateUrl: 'dashboard.html',
         controller: 'mainUXController'
     }) .when('/businessRules', {
         templateUrl: 'businessRulesSearchUI/businessRulesSearch.html',
         controller: 'bussinessRSController'
     }).when('/entireTable', {
         templateUrl: 'entireTableTabUI/entireTableTab.html',
         controller: 'entireTableController'
     })

});