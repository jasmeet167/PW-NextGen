'use strict';
 
var App = angular.module('mainuxApp');
var environmentdata=[];
App.controller('entireTableController', ['$scope', 'MainUXService','$location','$http','$rootScope','$route','$window','entireTableTabData', function($scope, MainUXService,$location,$http,$rootScope,$route,$window,entireTableTabData) {
	
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
	
	
	  $scope.rulesSelector = $scope.rules[0].coreValue ;
	
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
	     
	     if(entireTableTabData.getenvironments()==null || entireTableTabData.getenvironments()=='')
	     {
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
    		        	   entireTableTabData.setenvironments(newResult.data);
    		           }
    		   
    		       }, function(error) {
    		    	   console.log(error);
    		    	   
    		    	   if(error.status == '401')
    		    	   {
    		    		   console.log("Status recieved 401 :Incorrect Username/password");

    		    	   }
    		    	   else if(error.status == '400')
    		    	   {
    		    		   console.log("Bad Request. Invalid or malformed request detected.");
    		    	   }
    		    	   else if(error.status == '404')
    		    	   {
    		    		   console.log("Not Found. No values found matching the provided key values.");
    		    	   }
    		    	   else{
    		    		   console.log("error recieved ");

    		    	   }
    		    	   
    		    	   
    		    	   console.log(errror);
    		    	   $scope.errorMessage = "Server Error";
    	        	   $scope.error = true;
    		       });
		
	     }
	     else
    	 {
    	 $scope.environmentSelector=entireTableTabData.getenvironmentSelector();
    	 $scope.environments=entireTableTabData.getenvironments();
    	
    	 }
	     if(entireTableTabData.getruleSelector()!=null)
	     {
	    	 $scope.rulesSelector=entireTableTabData.getruleSelector();
	     }
	     if(entireTableTabData.getcompanys()!=null  )//|| businessRulesTabData.getcompanys()!=''
    	 {
    	 $scope.enablecompanySelector=false;
    	 $scope.companys=entireTableTabData.getcompanys();
    	 }
	     if(entireTableTabData.getcompanySelector()!=null  ) //|| businessRulesTabData.getcompanySelector()!=''
			{
		    	 $scope.companySelector=entireTableTabData.getcompanySelector();
			}
	     if(entireTableTabData.getbusinessRules()!=null)
	    	 {
	    	
	    	 $scope.businesRules=entireTableTabData.getbusinessRules();
	    	 }
	     if(entireTableTabData.getbusinessRuleSelector()!=null)
	    	 {
	    	 $scope.businessRulesSelector=entireTableTabData.getbusinessRuleSelector();
	    	 }
	}
	
  // Default value set to true	
	$scope.enablecompanySelector=true;
	

	
		$scope.triggerRulesNext=function(rulesSelector){
			console.log("rule selected is "+rulesSelector);
			$scope.rulesSelector=rulesSelector;
			entireTableTabData.setruleSelector(rulesSelector);
		}
	
		$scope.triggerEnvNext=function(environmentSelector){
		console.log("value of environment selected is "+environmentSelector);
		$scope.environmentSelector=environmentSelector;
		entireTableTabData.setenvironmentSelector(environmentSelector);
		$scope.enablecompanySelector=true;
		  if(environmentSelector!=null)
			  {
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
    		        	  entireTableTabData.setcompanys(newResult.data);
    		        	  
    		           }
    		       
    		       }, function(error) {
    		    	   console.log(error);
    		    	   if(error.status == '401')
    		    	   {
    		    		   console.log("Status recieved 401 :Unauthorized Access");

    		    	   }
    		    	   else if(error.status == '400')
    		    	   {
    		    		   console.log("Bad Request. Invalid or malformed request detected.");
    		    	   }
    		    	   else if(error.status == '404')
    		    	   {
    		    		   console.log("Not Found. No values found matching the provided key values.");
    		    	   }
    		    	   else{
    		    		   console.log("error recieved ");

    		    	   }
    		    	   $scope.errorMessage = "Server Error";
    	        	   $scope.error = true;  
    		       });
		   
			  }
		  else
			  {
			  $scope.companySelector="Company";
			  $scope.businessRulesSelector="";
			  $scope.businesRules="";
			  $scope.enablecompanySelector=true;
			  }
		}
		
		$scope.triggerCompNext= function(companySelector){
			
			console.log("value of company selected is "+companySelector);
			 $scope.companySelector= companySelector;
			 entireTableTabData.setcompanySelector(companySelector);
			 if(companySelector!=null ) 
				 {
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
	   		        	entireTableTabData.setbusinessRules(newResult.data);
	   		           }
	   		         
	   		       }, function(error) {
	   		    	   console.log(error);
	   		    	   if(error.status == '401')
	   		    	   {
	   		    		   console.log("Status recieved 401 :Unauthorized Access");

	   		    	   }
	   		    	   
	   		    	   if(error.status == '404')
	   		    		   {
	   		    		$scope.businesRules="";
	   		    		   }
	   		    	   else{
	   		    		   console.log("error recieved ");

	   		    	   }
	   		    	   $scope.errorMessage = "Server Error";
	   	        	   $scope.error = true;
	   		       });
			
				 }
			 else
				 {
				 $scope.businessRulesSelector="";
				 $scope.businesRules="";
				 entireTableTabData.setbusinessRules("");
				 }
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
