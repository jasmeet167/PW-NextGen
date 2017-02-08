'use strict';

var App = angular.module('mainuxApp',['ngRoute','oc.lazyLoad','pascalprecht.translate']);

App.config(function($routeProvider,$ocLazyLoadProvider,$translateProvider) {
	var lazyDeferred;
	$ocLazyLoadProvider.config({	    
		modules: [ {
		      name: 'AllowedValueApp',
		      serie:true,
		      files: ['/allowedvalue/js/config.js', '/allowedvalue/js/controller.js', '/allowedvalue/js/service.js']
		    },
		    {
			      name: 'DataDictionaryApp',
			      serie:true,
			      files: ['/datadictionary/js/config.js', '/datadictionary/js/controller.js', '/datadictionary/js/service.js']
			 }] 
	  });
	
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
     })
	 /*.when('/allowedvalue', { 
        // url: '/av',	 
         	
		 resolve : {
    		 load: ['$ocLazyLoad', '$q', function ($ocLazyLoad, $q) {
				 lazyDeferred = $q.defer();
    			return $ocLazyLoad.load(['myApp']).then(function success(data) {
                    console.log('myApp module loaded', data); 
                    return lazyDeferred.resolve(data);					
                }, function error(err) {
                    console.log(err);
                    alert("myApp module load Error " + error);
					return lazyDeferred.resolve(err);
                });
    	 }]},
    	 templateUrl: function($location) { 
         	// lazyDeferred = $q.defer();
 			if(lazyDeferred.promise != null){
 				console.log('Here i am'); 
 				//lazyDeferred.promise = null;
 				return "/allowedvalue";
 			} 
 		 }
		 templateUrl:'/allowedvalue',
		 resolve : {
	   		 load: ['$ocLazyLoad', '$q', function ($ocLazyLoad, $q) {
	   			return $ocLazyLoad.load(['myApp']);
	   	 }]}
		 //templateUrl:'/av'
     });*/
      .when('/allowedvalues', { 
		 resolve : {
	   		 load: ['$ocLazyLoad', '$q', function ($ocLazyLoad, $q) {
	   			return $ocLazyLoad.load(['AllowedValueApp']);
	   	 }]},
		 templateUrl:'/allowedvalue'
     }).when('/datadictionary', { 
		 resolve : {
	   		 load: ['$ocLazyLoad', '$q', function ($ocLazyLoad, $q) {
	   			return $ocLazyLoad.load(['DataDictionaryApp']);
	   	 }]},
		 templateUrl:'/datadictionary'
     })
    /* .otherwise({
         redirectTo: '/login'
     });*/
	 
	 $translateProvider.useStaticFilesLoader({
			prefix: 'locale-',// path to translations files
			suffix: '.json'// suffix, currently- extension of the translations
		});
		$translateProvider.preferredLanguage('en');
		$translateProvider.useSanitizeValueStrategy('escape');
});