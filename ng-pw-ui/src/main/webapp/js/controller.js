'use strict';
 
var App = angular.module('mainuxApp');
var environmentdata=[];
App.controller('mainUXController', ['$scope', 'MainUXService','$location','$http','$rootScope','$translate','businessRulesTabData','entireTableTabData', function($scope, MainUXService,$location,$http,$rootScope,$translate,businessRulesTabData,entireTableTabData) {
	
	var prevElemSelected=null;  //#FFFFFF
	var defaultElem=null;
	$scope.init=function (){
		/**
		 * Since business rules needs to
		 * be shown as default open tab
		 */
		$location.path("/businessRules" );
		 defaultElem= document.getElementById("brsID");

		 defaultElem.style.backgroundColor="#ccc";
	}
	 
	$scope.tabfocus=function( event)
	{
		console.log("triggereed event is "+event);
		defaultElem.style.backgroundColor="#f1f1f1";
	     if(prevElemSelected!=null)
	    	 {
	    	 prevElemSelected.css({'background-color' : '#f1f1f1'});
	    	 }
		
		 var elem = angular.element(event.srcElement);
		 console.log("triggereed event is "+event.srcElement + "element is "+elem);
		 elem.css({'background-color' : '#ccc'});
		 prevElemSelected=elem;

	}

	
	$scope.logout=function(){
		console.log("logout called from controller");
		/**
		 * clear all data in services
		 */	
		
		/**
		 * 
		 */
		businessRulesTabData.setruleSelector(null);
		businessRulesTabData.setenvironmentSelector(null);
		businessRulesTabData.setenvironments(null);
		businessRulesTabData.setprojects(null);
		businessRulesTabData.setprojectSelector(null);
		businessRulesTabData.setpeds(null);
		businessRulesTabData.setplaneffectivedateSelector(null);
		businessRulesTabData.setlobs(null);
		businessRulesTabData.setlineOfBusinessSelector(null);
		businessRulesTabData.setissueStates(null);
		businessRulesTabData.setissueStateSelector(null);
		businessRulesTabData.setplanCodes(null);
		businessRulesTabData.setplanCodeSelector(null);
		businessRulesTabData.setproducts(null);
		businessRulesTabData.setproductSelector(null);
		businessRulesTabData.setcompanys(null);
		businessRulesTabData.setcompanySelector(null);
		
		// for entire table tab
		entireTableTabData.setruleSelector(null);
		entireTableTabData.setenvironmentSelector(null);
		entireTableTabData.setenvironments(null);
		entireTableTabData.setcompanySelector(null);
		entireTableTabData.setcompanys(null);
		entireTableTabData.setbusinessRuleSelector(null);
		entireTableTabData.setbusinessRules(null);
		
		   $http({
     		    method: 'POST',
     		    url: '/ng-pw-rest/security/logout',
     		    headers: {
     		    	 //'Content-Type' : 'application/json',
     		    	 'sessionToken' :sessionStorage.getItem("tokenId")
     		    }}).then(function(newResult) {
  
    	        	
                    
     		           if (newResult.status == '200' ){
     		           console.log("success in getting logout. Press ok to redirect");

     		  		  var url = "/ng-pw-ui/login.html";
     		 
     		  		window.location.replace(url);
     		  		sessionStorage.removeItem("tokenId");
     		        
     		           }
     		    
     		       }, function(error) {
     		    	   console.log(error);
     		    	   alert(errror);
     		    	   
     		          if(error.status == '401')
		        	   {

		        	   console.log("Status recieved 401 :Unauthorized");

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
     		         var url = "/ng-pw-ui/login.html";
     	     		 
      		  		window.location.replace(url);
      		  		sessionStorage.removeItem("tokenId");
     		    	   
     		    	   $scope.errorMessage = "Server Error";
     	        	   $scope.error = true;
     		       });
		
		
	}
	
}]);

App.controller('loginController', ['$scope', 'MainUXService','$location','$http','$rootScope','$route','$window', function($scope, MainUXService,$location,$http,$rootScope,$route,$window) {
	$scope.error = false;
	$rootScope.screenId='login';
	
	$scope.showAlert = false;

	$scope.username;

	 
	$scope.closeAlert = function() {
        $scope.showAlert=false;
   };
	$scope.login = function(){
		$scope.showAlert = false;
		
		$http({
		    method: 'POST',
		    url: '/ng-pw-rest/security/authentication',
		    data: {userName:$scope.username,password:$scope.password},
		    headers: {
		    	 'Content-Type' : 'application/json'
		    		//  "Accept": "application/json"
		    }}).then(function(result) {
		           if (result.status == '200' && (result.data.tokenId!=null||result.data.tokenId!='')){
		        	   console.log(result.data);

		        	 
		        	   console.log("Token recieved from login is "+result.data.tokenId);
		        
		        	   
		        	   sessionStorage.setItem("tokenId",result.data.tokenId); 
		        	   $scope.errorMessage = "";
		        	   $scope.error = false;
		        	   $scope.proceed=true;
		        	   $scope.showAlert = false;
		        	   window.location.href = "/ng-pw-ui/dashboard.html";

		           }
		           else if(result.status == '401')
		        	   {
		        	   $scope.showAlert = true;
		        	   }
		           else if(newResult.status == '400')
	        	   {
	        		   console.log("Bad Request. Invalid or malformed request detected.");
	        	   }
	           else if(newResult.status == '404')
	        	   {
	        	   console.log("Not Found. No values found matching the provided key values.");
	        	   }
		           else{
		        	   console.log("error recieved ")
		        	   $scope.showAlert = true;
		           }
		       }, function(error) {
		    	   console.log(error);
		    	   $scope.showAlert = true;
		    	   $scope.errorMessage = "Server Error";
	        	   $scope.error = true;
		       });


		
	};

	


	

	
}]);




 

