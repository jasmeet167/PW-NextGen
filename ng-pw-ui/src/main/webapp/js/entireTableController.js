'use strict';
 
var App = angular.module('mainuxApp');
var environmentdata=[];
App.controller('entireTableController', ['$scope', 'MainUXService','$location','$http','$rootScope','$route','$window', function($scope, MainUXService,$location,$http,$rootScope,$route,$window) {
	
	$scope.init= function (evt, tabName)
	{
	  	 console.log("Tab triggered " +tabName);
	     var i, tabcontent, tablinks;
	     tabcontent = document.getElementsByClassName("tabcontent");
	     for (i = 0; i < tabcontent.length; i++) {
	         tabcontent[i].style.display = "none";
	     }
	     tablinks = document.getElementsByClassName("tablinks");
	     for (i = 0; i < tablinks.length; i++) {
	         tablinks[i].className = tablinks[i].className.replace(" active", "");
	     }
	     document.getElementById(tabName).style.display = "block";	
	     
	     //environment api trigger
		   $http({
    		    method: 'GET',
    		    url: '/ng-pw-rest/search/common/environment',
    		    headers: {
    		    	 //'Content-Type' : 'application/json',
    		    	 'sessionToken' : sessionStorage.getItem("tokenId")
    		    }}).then(function(newResult) {
                   console.log(angular.toJson(newResult.data));
                   environmentdata=newResult.data;

    		           if (newResult.status == '200' ){
    		        	
    		        	  $scope.environments=newResult.data;
    		        	   $scope.errorMessage = "";
    		           }
    		           else if(newResult.status == '401')
    		        	   {
    		        	   console.log("Status recieved 401 :Incorrect Username/password");

    		        	   }
    		           else if(newResult.status == '400')
		        	   {
		        		   alert("Bad Request. Invalid or malformed request detected.");
		        	   }
		           else if(newResult.status == '404')
		        	   {
		        	   alert("Not Found. No values found matching the provided key values.");
		        	   }
    		           else{
    		        	   console.log("error recieved ");

    		           }
    		       }, function(error) {
    		    	   console.log(error);
    		    	   console.log(errror);
    		    	   $scope.errorMessage = "Server Error";
    	        	   $scope.error = true;
    		       });
		
	
	}
	
  // Default value set to true	
	$scope.enablecompanySelector=true;
	
	$scope.rules=
		[
			  {
			    "coreValue": false,
			    "displayValue": "Rules"
			  },
			  {
			    "coreValue": true,
			    "displayValue": "Rules with Changes"
			  }
			  
			  ];
	
		$scope.triggerRulesNext=function(rulesSelector){
			console.log("rule selected is "+rulesSelector);
			$scope.rulesSelector=rulesSelector;
		}
	
		$scope.triggerEnvNext=function(environmentSelector){
		console.log("value of environment selected is "+environmentSelector);
		$scope.environmentSelector=environmentSelector;
		   $http({
    		    method: 'GET',
    		    url: '/ng-pw-rest/search/plan/company',
    		    headers: {
    		    	 //'Content-Type' : 'application/json',
    		    	 'sessionToken' : sessionStorage.getItem("tokenId"),
    		    	  'viewChanges':$scope.rulesSelector,
    		    	 'envId':$scope.environmentSelector
    		    	 
    		    }}).then(function(newResult) {
                   console.log(angular.toJson(newResult.data));
                

    		           if (newResult.status == '200' ){
    		        	   $scope.enablecompanySelector=false;
    		        	  $scope.companys=newResult.data;
    		        	  
    		           }
    		           else if(newResult.status == '401')
    		        	   {
    		        	   console.log("Status recieved 401 :Unauthorized Access");

    		        	   }
    		           else if(newResult.status == '400')
		        	   {
		        		   alert("Bad Request. Invalid or malformed request detected.");
		        	   }
		           else if(newResult.status == '404')
		        	   {
		        	   alert("Not Found. No values found matching the provided key values.");
		        	   }
    		           else{
    		        	   console.log("error recieved ");

    		           }
    		       }, function(error) {
    		    	   console.log(error);
    		    	   console.log(errror);
    		    	   $scope.errorMessage = "Server Error";
    	        	   $scope.error = true;  
    		       });
		   
		}
		
		$scope.triggerCompNext= function(companySelector){
			
			console.log("value of company selected is "+companySelector);
			 $scope.companySelector= companySelector;
			   $http({
	   		    method: 'GET',
	   		    url: '/ng-pw-rest/search/plan/table',
	   		    headers: {
	   		    	 //'Content-Type' : 'application/json',
	   		    	 'sessionToken' : sessionStorage.getItem("tokenId"),
	   		    	 'envId':$scope.environmentSelector,
	   		    	 'companyCode': $scope.companySelector
	   		    	 
	   		    }}).then(function(newResult) {
	                 console.log(angular.toJson(newResult.data));
	               

	   		           if (newResult.status == '200' ){
	   		        	
	   		        	  $scope.businesRules=newResult.data;
	   		        	  
	   		           }
	   		           else if(newResult.status == '401')
	   		        	   {
	   		        	   console.log("Status recieved 401 :Unauthorized Access");

	   		        	   }
	   		           else{
	   		        	   console.log("error recieved ");

	   		           }
	   		       }, function(error) {
	   		    	   console.log(error);
	   		    	   console.log(errror);
	   		    	   $scope.errorMessage = "Server Error";
	   	        	   $scope.error = true;
	   		       });
			
			
		}
		
		$scope.businessRulesSelector = [];
		   $scope.$watch('businesRules', function(nowSelected){
		        $scope.businessRulesSelector = [];
		        
		        if( ! nowSelected ){   
		            // here we've initialized selected already
		            // but sometimes that's not the case
		            // then we get null or undefined
		            return;
		        }
		        angular.forEach(nowSelected, function(val){
		            $scope.businessRulesSelector.push( val );      
		        });
		    });
		
		
		
		
}]);
