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


/**
 * Service for storing business search rules 
 * tab data
 */

App.service('businessRulesTabData',function()
		{
	 var ruleSelector=null;
	 var environmentSelector=null;
	 var environments=null;
	 var companySelector=null;
	 var companys=null;
	 var productSelector=null;
	 var products=null;
	 
	 var planCodeSelector=null;
	 var planCodes=null;
	 
	 var issueStateSelector=null;
	 var issueStates=null;
	 
	 var lineOfBusinessSelector=null;
	 var lobs=null;
	 
	 var planeffectivedateSelector=null;
	 var peds=null;
	 
	 var projectSelector=null;
	 var projects=null;
	 
	   return {
           
		   getruleSelector:getruleSelector,
		   setruleSelector:setruleSelector,
		   
		   getenvironmentSelector: getenvironmentSelector,
           setenvironmentSelector: setenvironmentSelector,
           
           getenvironments: getenvironments,
           setenvironments: setenvironments,
           
           getcompanySelector: getcompanySelector,
           setcompanySelector: setcompanySelector,
           
           getcompanys: getcompanys,
           setcompanys: setcompanys,
           
           getproductSelector: getproductSelector,
           setproductSelector: setproductSelector,
           
           getproducts: getproducts,
           setproducts: setproducts,
           // pend
           getplanCodeSelector: getplanCodeSelector,
           setplanCodeSelector: setplanCodeSelector,
           
           getplanCodes: getplanCodes,
           setplanCodes: setplanCodes,
           
           getissueStateSelector: getissueStateSelector,
           setissueStateSelector: setissueStateSelector,
           
           getissueStates: getissueStates,
           setissueStates: setissueStates,
           
           getlineOfBusinessSelector: getlineOfBusinessSelector,
           setlineOfBusinessSelector: setlineOfBusinessSelector,
           
           getlobs: getlobs,
           setlobs: setlobs,
           
           getplaneffectivedateSelector: getplaneffectivedateSelector,
           setplaneffectivedateSelector: setplaneffectivedateSelector,
           
           getpeds:getpeds,
           setpeds: setpeds,
           
           getprojectSelector: getprojectSelector,
           setprojectSelector: setprojectSelector,
           
           getprojects: getprojects,
           setprojects: setprojects
           
           
           
       };

       function getruleSelector() {
           return ruleSelector;
       }

       function setruleSelector(value) {
    	   ruleSelector = value;
       }
       
       
       function getenvironmentSelector() {
           return environmentSelector;
       }

       function setenvironmentSelector(value) {
    	   environmentSelector = value;
       }
       
       function getenvironments() {
           return environments;
       }

       function setenvironments(value) {
    	   environments = value;
       }
       
       function getcompanys() {
           return companys;
       }

       function setcompanys(value) {
    	   companys = value;
       }
       
       function getcompanySelector() {
           return companySelector;
       }

       function setcompanySelector(value) {
    	   companySelector = value;
       }
       function getproductSelector() {
           return productSelector;
       }

       function setproductSelector(value) {
    	   productSelector = value;
       }
       function getproducts() {
           return products;
       }

       function setproducts(value) {
    	   products = value;
       }
       
       function getplanCodeSelector() {
           return planCodeSelector;
       }

       function setplanCodeSelector(value) {
    	   planCodeSelector = value;
       }
       
       function getplanCodes() {
           return planCodes;
       }

       function setplanCodes(value) {
    	   planCodes = value;
       }
	 
       function getissueStateSelector() {
           return issueStateSelector;
       }

       function setissueStateSelector(value) {
    	   issueStateSelector = value;
       }
       
       function getissueStates() {
           return issueStates;
       }

       function setissueStates(value) {
    	   issueStates = value;
       }
       
       function getlineOfBusinessSelector() {
           return lineOfBusinessSelector;
       }

       function setlineOfBusinessSelector(value) {
    	   lineOfBusinessSelector = value;
       }
       
       function getlobs() {
           return lobs;
       }

       function setlobs(value) {
    	   lobs = value;
       }
       
       function getplaneffectivedateSelector() {
           return planeffectivedateSelector;
       }

       function setplaneffectivedateSelector(value) {
    	   planeffectivedateSelector = value;
       }
       
       function getpeds() {
           return peds;
       }

       function setpeds(value) {
    	   peds = value;
       } 
       
       function getprojectSelector() {
           return projectSelector;
       }

       function setprojectSelector(value) {
    	   projectSelector = value;
       }
       
       function getprojects() {
           return projects;
       }

       function setprojects(value) {
    	   projects = value;
       } 
       
		}
);