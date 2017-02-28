'use strict';
 
var App = angular.module('mainuxApp');
var environmentdata=[];
App.controller('bussinessRSController', ['$scope', 'MainUXService','$location','$http','$rootScope','$route','$window','businessRulesTabData', function($scope, MainUXService,$location,$http,$rootScope,$route,$window,businessRulesTabData) {
	
	
	$scope.error = false;
	$rootScope.screenId='login';
	
	//$scope.showconsole.log = false;

	$scope.username;
	
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
	  $scope.rulesSelector =  $scope.rules[0].coreValue ;
	  
	  



	
	
	$scope.init= function (event, tabName)
	{
	  	 console.log("Tab triggered"+tabName);
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
	    // event.currentTarget.className += " active";
	    
	   
	     
	   
	
	     	  // Default selectors set true
	  	$scope.enablecompanySelector=true;
	 	$scope.enableproductSelector=true;
	 	$scope.enableplanCodeSelector=true;
	 	$scope.enableissueStateSelector=true;
	 	$scope.enablelineOfBusinessSelector=true;
	 	$scope.enableplaneffectivedateSelector=true;
	 	
	 	// For alerts of api
	 	$scope.showAlert = false;
	 	$scope.apiCall="";
		
	     if(businessRulesTabData.getenvironments()==null || businessRulesTabData.getenvironments()=='' )
	    	 {
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
     		        	  businessRulesTabData.setenvironments(newResult.data);
     		        	 $scope.showAlert = false;
     		           }
     		        
     		       }, function(error) {   		    	   
     		    	   console.log(error);
     		    	  if(error.status == '401')
 		        	   {
 		        	   console.log("Status recieved 401 :Incorrect username/pwd ");

 		        	   }
 		              else{
 		        	   console.log("error recieved ");

 		                }
     		    	   
     		    	 	$scope.showAlert = true;
     		   	 	    $scope.apiCall=" environments ";
     		    	   $scope.errorMessage = "Server Error";
     	        	   $scope.error = true;
     		       });
		   
	    	 }
	     else
	    	 {
	    	 $scope.environmentSelector=businessRulesTabData.getenvironmentSelector();
	    	 $scope.environments=businessRulesTabData.getenvironments();
	    	
	    	 }
	     if(businessRulesTabData.getruleSelector()!=null  ) //|| businessRulesTabData.getruleSelector()!=''
	    	 {
	    	 $scope.rulesSelector=businessRulesTabData.getruleSelector();
	    	 }
	     
	     if(businessRulesTabData.getcompanys()!=null  )//|| businessRulesTabData.getcompanys()!=''
	    	 {
	    	 $scope.enablecompanySelector=false;
	    	 $scope.companys=businessRulesTabData.getcompanys();
	    	
	    	 
	    	 }
	     if(businessRulesTabData.getcompanySelector()!=null  ) //|| businessRulesTabData.getcompanySelector()!=''
		{
	    	 $scope.companySelector=businessRulesTabData.getcompanySelector();
		}
	     if(businessRulesTabData.getproducts()!=null  ) //||businessRulesTabData.getproducts()!=''
	    	 {
	    	 $scope.enableproductSelector=false;
	    	 $scope.products= businessRulesTabData.getproducts();
	    	
	    	 }
	     if(businessRulesTabData.getproductSelector()!=null  ) //|| businessRulesTabData.getproductSelector()!=''
	    	 {
	    	 $scope.productSelector=businessRulesTabData.getproductSelector();
	    	 }
	     if(businessRulesTabData.getplanCodeSelector()!=null ) //|| businessRulesTabData.getplanCodeSelector()!=''
	    {
	    	 $scope.planCodeSelector=businessRulesTabData.getplanCodeSelector();
	    }
	    if(businessRulesTabData.getplanCodes()!=null )//|| businessRulesTabData.getplanCodes()!=''
		  {
	    	  $scope.enableplanCodeSelector=false;
		  $scope.planCodes=businessRulesTabData.getplanCodes();
		
		 }
	   if(businessRulesTabData.getissueStateSelector()!=null  )//|| businessRulesTabData.getissueStateSelector()!=''
	    {
		   $scope.issueStateSelector=businessRulesTabData.getissueStateSelector();
	    }
	  if(businessRulesTabData.getissueStates()!=null )//|| businessRulesTabData.getissueStates()!=''
		  {
		  $scope.enableissueStateSelector=false;
		  $scope.issueStates=businessRulesTabData.getissueStates();
		
		  }
	  if(businessRulesTabData.getlineOfBusinessSelector()!=null ) // || businessRulesTabData.getlineOfBusinessSelector()!=''
	    {
		  $scope.lineOfBusinessSelector=businessRulesTabData.getlineOfBusinessSelector();
	    }
	 if(businessRulesTabData.getlobs()!=null  ) // || businessRulesTabData.getlobs()!=''
		 {
		 $scope.enablelineOfBusinessSelector=false;
		 $scope.lobs=businessRulesTabData.getlobs();
	
		 
		 }
	 if(businessRulesTabData.getplaneffectivedateSelector()!=null ) //|| businessRulesTabData.getplaneffectivedateSelector()!=''
	  {
		 $scope.planeffectivedateSelector=businessRulesTabData.getplaneffectivedateSelector();
	  }
	 if(businessRulesTabData.getpeds()!=null ) //||businessRulesTabData.getpeds()!=''
		 {
		 $scope.enableplaneffectivedateSelector=false;
		 $scope.peds=businessRulesTabData.getpeds();
		
		 }
	 if(businessRulesTabData.getprojects()!=null ) //|| businessRulesTabData.getprojects()!=''
		 {
		 $scope.projects=businessRulesTabData.getprojects();
		 }
	 if(businessRulesTabData.getprojectSelector()!=null ) //|| businessRulesTabData.getprojectSelector()!=''
		 {
		 $scope.projectSelector=businessRulesTabData.getprojectSelector();
		 }
	}
	
    /*
     * Method for disabling
     * corresponding drop downs
     * 
     */
	$scope.triggerdisableDropDowns=function(dropDownNo)
	{
	     
		
		 if(dropDownNo=="1"|| dropDownNo=="0") //for env
			{
			$scope.enablecompanySelector=true;
		 	$scope.enableproductSelector=true;
		 	$scope.enableplanCodeSelector=true;
		 	$scope.enableissueStateSelector=true;
		 	$scope.enablelineOfBusinessSelector=true;
		 	$scope.enableplaneffectivedateSelector=true;
		 	// environmentSelector companySelector productSelector
		 	// planCodeSelector issueStateSelector lineOfBusinessSelector planeffectivedateSelector
		 	$scope.companySelector="Company";
		 	$scope.productSelector="Product";
		 	$scope.planCodeSelector="PlanCode";
		 	$scope.issueStateSelector="IssueState";
		 	$scope.lineOfBusinessSelector="LineOfBusiness";
		 	$scope.planeffectivedateSelector="PlanEffectiveDate";
		 	
			}
		else if(dropDownNo=="2") //for comp
			{
		 	$scope.enableproductSelector=true;
		 	$scope.enableplanCodeSelector=true;
		 	$scope.enableissueStateSelector=true;
		 	$scope.enablelineOfBusinessSelector=true;
		 	$scope.enableplaneffectivedateSelector=true;
		 	$scope.productSelector="Product";
		 	$scope.planCodeSelector="PlanCode";
		 	$scope.issueStateSelector="IssueState";
		 	$scope.lineOfBusinessSelector="LineOfBusiness";
		 	$scope.planeffectivedateSelector="PlanEffectiveDate";
			}
		else if(dropDownNo=="3") //for product codes
		{
		 $scope.enableplanCodeSelector=true;
	 	$scope.enableissueStateSelector=true;
	 	$scope.enablelineOfBusinessSelector=true;
	 	$scope.enableplaneffectivedateSelector=true;
	 	$scope.planCodeSelector="PlanCode";
	 	$scope.issueStateSelector="IssueState";
	 	$scope.lineOfBusinessSelector="LineOfBusiness";
	 	$scope.planeffectivedateSelector="PlanEffectiveDate";
		}
		else if(dropDownNo=="4") //for plan code
			{
			$scope.enableissueStateSelector=true;
		 	$scope.enablelineOfBusinessSelector=true;
		 	$scope.enableplaneffectivedateSelector=true;
		 	$scope.issueStateSelector="IssueState";
		 	$scope.lineOfBusinessSelector="LineOfBusiness";
		 	$scope.planeffectivedateSelector="PlanEffectiveDate";
			}
		else if(dropDownNo=="5") //for issue statecode
		{
		$scope.enablelineOfBusinessSelector=true;
	 	$scope.enableplaneffectivedateSelector=true;
		$scope.lineOfBusinessSelector="LineOfBusiness";
		$scope.planeffectivedateSelector="PlanEffectiveDate";
		}
		
		else if(dropDownNo=="6") //for lob
		{
			$scope.planeffectivedateSelector="PlanEffectiveDate";
	 	$scope.enableplaneffectivedateSelector=true;
		}
	}
	
	
	//$scope.rulesSelector=rules[0];
	
	$scope.triggerRulesNext=function(rulesSelector){
		console.log("rule selected is "+rulesSelector);
		$scope.rulesSelector=rulesSelector;
		$scope.triggerdisableDropDowns("0");
		businessRulesTabData.setruleSelector(rulesSelector);
	}
	
	$scope.triggerEnvNext=function(environmentSelector){
		console.log("value of environment selected is "+environmentSelector);
		$scope.triggerdisableDropDowns("1");
		$scope.environmentSelector=environmentSelector;
		businessRulesTabData.setenvironmentSelector(environmentSelector);
		  if(environmentSelector!=null )
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
    		        	  businessRulesTabData.setcompanys(newResult.data);
    		        	  $scope.showAlert = false;
    		        	  
    		           }
    		        
    		       }, function(error) {
    		    	   console.log(error);
    		    	   
    		    	   if(error.status == '401')
    		    	   {
    		    		   alert("Status recieved 401 :Unauthorized access");
    	     		  		window.location.replace("/ng-pw-ui/login.html");
    	     		  		sessionStorage.removeItem("tokenId");

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
    		    	   
    		    		$scope.showAlert = true;
    		    	 	$scope.apiCall=" company ";
    		    	   $scope.errorMessage = "Server Error";
    	        	   $scope.error = true;  
    		       });
		   
		   
		   
		   $http({
	   		    method: 'GET',
	   		    url: '/ng-pw-rest/search/plan/project',
	   		    headers: {
	   		    	 //'Content-Type' : 'application/json',
	   		    	 'sessionToken' : sessionStorage.getItem("tokenId"),
	   		    	 'envId':$scope.environmentSelector,
	   	 
	   		    }}).then(function(newResult) {
	                  console.log(angular.toJson(newResult.data));
	               

	   		           if (newResult.status == '200' ){
	   		        	
	   		        	
	   		        	  $scope.projects=newResult.data;
	   		        	  businessRulesTabData.setprojects(newResult.data);
	   		        	$scope.showAlert = false;
	   		           }
	   		         
	   		       }, function(error) {
	   		    	   console.log(error);
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
	   		    		   $scope.projects="";
	   		    	   }
	   		    	   else{
	   		    		   console.log("error recieved ");

	   		    	   }
	   		    	   $scope.showAlert = true;
		    	 	   $scope.apiCall=" project ";   
	   		    	   $scope.errorMessage = "Server Error";
	   	        	   $scope.error = true;
	   		       });
		   
			  }
		  else
			  {
			  $scope.projects="";
			  }
		 
	}
	
	$scope.triggerCompNext= function(companySelector)
	{
		console.log("value of product selected is "+companySelector);
		 $scope.companySelector= companySelector;
		 $scope.triggerdisableDropDowns("2");
		 businessRulesTabData.setcompanySelector(companySelector);
		 if( companySelector!=null)
		   {
			 $http({
   		    method: 'GET',
   		    url: '/ng-pw-rest/search/plan/product',
   		    headers: {
   		    	 //'Content-Type' : 'application/json',
   		    	 'sessionToken' : sessionStorage.getItem("tokenId"),
   		    	  'viewChanges':$scope.rulesSelector,
   		    	 'envId':$scope.environmentSelector,
   		    	 'companyCode': $scope.companySelector
   		    	 
   		    }}).then(function(newResult) {
                  console.log(angular.toJson(newResult.data));
               

   		           if (newResult.status == '200' ){
   		        	 $scope.enableproductSelector=false;
   		        	  $scope.products=newResult.data;
   		        	businessRulesTabData.setproducts(newResult.data);
   		        	$scope.showAlert = false;
   		        	  
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
   		    	   
   		    	   $scope.showAlert = true;
	    	 	   $scope.apiCall=" product ";  
   		    	   $scope.errorMessage = "Server Error";
   	        	   $scope.error = true;
   		       });
	}
	}
	
	
	$scope.triggerProdNext= function(productSelector)
	{
		console.log("value of product selected is "+productSelector);
		 $scope.productSelector= productSelector;
		 $scope.triggerdisableDropDowns("3");
		 businessRulesTabData.setproductSelector(productSelector);
		 if( productSelector!=null)
			 {
		 $http({
   		    method: 'GET',
   		    url: '/ng-pw-rest/search/plan/plan',
   		    headers: {
   		    	 //'Content-Type' : 'application/json',
   		    	 'sessionToken' : sessionStorage.getItem("tokenId"),
   		    	  'viewChanges':$scope.rulesSelector,
   		    	 'envId':$scope.environmentSelector,
   		    	 'companyCode': $scope.companySelector,
   		    	 'productCode':$scope.productSelector
   		    	 
   		    }}).then(function(newResult) {
                  console.log(angular.toJson(newResult.data));
               

   		           if (newResult.status == '200' ){
   		        	  
   		        	 $scope.enableplanCodeSelector=false;
   		        	  $scope.planCodes=newResult.data;
   		        	businessRulesTabData.setplanCodes(newResult.data);
   		        	$scope.showAlert = false;
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
   		    	   
   		    	   $scope.showAlert = true;
	    	 	   $scope.apiCall=" plan "; 
   		    	   $scope.errorMessage = "Server Error";
   	        	   $scope.error = true;
   		       });
			 }
		
	}

		$scope.triggerPlanCodeNext= function(planCodeSelector)
	{
		console.log("value of plan code selected is "+planCodeSelector);
		 $scope.planCodeSelector= planCodeSelector;
		 $scope.triggerdisableDropDowns("4");
		 businessRulesTabData.setplanCodeSelector(planCodeSelector);
		  if(planCodeSelector!=null )
			  {		  
		 $http({
   		    method: 'GET',
   		    url: '/ng-pw-rest/search/plan/state',
   		    headers: {
   		    	 //'Content-Type' : 'application/json',
   		    	 'sessionToken' : sessionStorage.getItem("tokenId"),
   		    	 'viewChanges':$scope.rulesSelector,
   		    	 'envId':$scope.environmentSelector,
   		    	 'companyCode': $scope.companySelector,
   		    	 'productCode':$scope.productSelector,
   		    	 'planCode':$scope.planCodeSelector
   		    	 
   		    }}).then(function(newResult) {
                  console.log(angular.toJson(newResult.data));
               

   		           if (newResult.status == '200' ){
   		        
   		        	   $scope.enableissueStateSelector=false;
   		        	  $scope.issueStates=newResult.data;
   		        	 businessRulesTabData.setissueStates(newResult.data);
   		        	$scope.showAlert = false;
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
   		    	  $scope.showAlert = true;
	    	 	   $scope.apiCall=" state ";
   		    	   $scope.errorMessage = "Server Error";
   	        	   $scope.error = true;
   		       });
			  }
	}

		
		$scope.triggerStateNext= function(issueStateSelector)
		{
			console.log("value of plan code selected is "+issueStateSelector);
			 $scope.issueStateSelector= issueStateSelector;
			 $scope.triggerdisableDropDowns("5");
			 businessRulesTabData.setissueStateSelector(issueStateSelector);
			  if(issueStateSelector!=null  )
				  {
			 $http({
	   		    method: 'GET',
	   		    url: '/ng-pw-rest/search/plan/lob',
	   		    headers: {
	   		    	 'sessionToken' : sessionStorage.getItem("tokenId"),
	   		    	 'viewChanges':$scope.rulesSelector,
	   		    	 'envId':$scope.environmentSelector,
	   		    	 'companyCode': $scope.companySelector,
	   		    	 'productCode':$scope.productSelector,
	   		    	 'planCode':$scope.planCodeSelector,
	   		    	 'issueState': $scope.issueStateSelector
	   		    	 
	   		    }}).then(function(newResult) {
	                  console.log(angular.toJson(newResult.data));
	               

	   		           if (newResult.status == '200' ){
	   		        	console.log("response recieved from lobs");
	   		        	$scope.enablelineOfBusinessSelector=false;
	   		        	  $scope.lobs=newResult.data;
	   		        	businessRulesTabData.setlobs(newResult.data);
	   		        	$scope.showAlert = false;
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
	   		    	  $scope.showAlert = true;
		    	 	   $scope.apiCall=" lob ";
	   		    	 console.log("error recieved ");
	   		    	   $scope.errorMessage = "Server Error";
	   	        	   $scope.error = true;
	   		       });
				  }
		}
		
		$scope.triggerlobNext= function(lineOfBusinessSelector)
		{
			console.log("value of plan code selected is "+lineOfBusinessSelector);
			 $scope.lineOfBusinessSelector= lineOfBusinessSelector;
			 $scope.triggerdisableDropDowns("6");
			 businessRulesTabData.setlineOfBusinessSelector(lineOfBusinessSelector);
			  if(lineOfBusinessSelector!=null )
				  {
			 $http({
	   		    method: 'GET',
	   		    url: '/ng-pw-rest/search/plan/effdate',
	   		    headers: {
	   		    	 //'Content-Type' : 'application/json',
	   		    	 'sessionToken' : sessionStorage.getItem("tokenId"),
	   		    	 'viewChanges':$scope.rulesSelector,
	   		    	 'envId':$scope.environmentSelector,
	   		    	 'companyCode': $scope.companySelector,
	   		    	 'productCode':$scope.productSelector,
	   		    	 'planCode': $scope.planCodeSelector,
	   		    	 'issueState': $scope.issueStateSelector,
	   		    	 'lob':$scope.lineOfBusinessSelector
	   	 
	   		    }}).then(function(newResult) {
	                  console.log(angular.toJson(newResult.data));
	               

	   		           if (newResult.status == '200' ){
	   		        	
	   		        	$scope.enableplaneffectivedateSelector=false;
	   		        	  $scope.peds=newResult.data;
	   		        	businessRulesTabData.setpeds(newResult.data);
	   		        	$scope.showAlert = false;
	   		           }
	   		        
	   		       }, function(error) {
	   		    	   
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
	   		    	   console.log(error);
	   		    	   $scope.showAlert = true;
		    	 	   $scope.apiCall=" effective date ";
	   		    	   $scope.errorMessage = "Server Error";
	   	        	   $scope.error = true;
	   		       });
			  
				  }
		}
		
		$scope.triggerPlanEffDatNext= function(planeffectivedateSelector)
		{
			console.log("value of plan code selected is "+planeffectivedateSelector);
			 $scope.planeffectivedateSelector= planeffectivedateSelector;
			 businessRulesTabData.setplaneffectivedateSelector(planeffectivedateSelector);
		
			
		}
	
		
		
		

		   $scope.projectSelector = [];
		    
		    $scope.$watch('projects', function(nowSelected){
		        $scope.projectSelector = [];
		        
		        if( ! nowSelected ){   
		            // here we've initialized selected already
		            // but sometimes that's not the case
		            // then we get null or undefined
		            return;
		        }
		        angular.forEach(nowSelected, function(val){
		            $scope.projectSelector.push( val );      
		            businessRulesTabData.setprojectSelector($scope.projectSelector);
		        });
		    });

	
}]);






 

