'use strict';

/* Services */

var App = angular.module('mainuxApp');

App.service('MainUXService', ['$http', '$q','$rootScope','$location', function($http, $q,$rootScope,$location){
	
	return {
		
		login : function(userName,password) {
			
			$http({
			    method: 'POST',
			    url: '/',
			    data: "username="+userName+"&password="+password,
			    headers: {
			        'Content-Type': 'application/x-www-form-urlencoded'
			    }}).then(function(result) {
			           return result;
			       }, function(error) {
			           return error;
			       });
		},
	
		menu: function(){
		$rootScope.token = localStorage.token;
	   	var deferred = $q.defer();
	   	var baseURL=$location.protocol()+"://"+ $location.host() +":"+$location.port();
	   	var url=baseURL + "/menu";
		var req = {
				 method: 'GET',
				 url: url,
				 headers: {
					'Authorization' : $rootScope.token
				 }
			 }
		$http(req).success(
	    		function(data) { 
	    	          deferred.resolve({
	    	        	  dataFromService: data,
	    	            });
	    	       }).error(function (data, status) {
	    	    	 if(status === 407){
					   	 window.location.href = data.logoutURL;
					 } 
	    			if(data.error){
	    				$rootScope.error.errorMsg=data.error;	
	    			}else{
	    				$rootScope.error.errorMsg="Service Unavailable";
	    			}
	    		})
	    return deferred.promise;
	    }
		
	}
	
}]);




