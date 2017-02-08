'use strict';
 
var App = angular.module('mainuxApp');
var environmentdata=[];
App.controller('mainUXController', ['$scope', 'MainUXService','$location','$http','$rootScope','$translate', function($scope, MainUXService,$location,$http,$rootScope,$translate) {
	$scope.error = false;
	$scope.loadMenu = function() {
		
		var promise1= MainUXService.menu();
		promise1.then(				
					function(result) {
						$scope.menus  = result.dataFromService;
										for(var i=0;i<$scope.menus.length;i++){
											if(angular.isDefined($scope.menus[i].menus)){
												for(var j=0;j<$scope.menus[i].menus.length;j++){
													if(angular.equals($scope.menus[i].heading,$scope.menus[i].menus[j])) {
														delete $scope.menus[i].menus;
													}
												}
											}
										}						
				});			
	};	
	
	
	   $scope.rulesSelector="Rules";
	   //$scope.environmentSelector="Environment";
	   $scope.companySelector="Company";
	   $scope.productSelector="Product";
	   $scope.issueCodeSelector="Issue Code";
	   $scope.lineOfBusinessSelector="LOB";
	   $scope.planeffectivedateSelector="Today";
	   $scope.projectSelector="Project";
	  
	  // alert("value of company is"+$scope.companySelector + "value of environments"+ $scope.environments);
	   
	
	   

	   $scope.environments=environmentdata;
	
	
	$scope.getClassForBodyElement = function(screenId){
		if(screenId=='home')
			return  'ui-layout ui-layout-container';
			
	}
	
	$scope.selectMenu = function(menu) {

		if($rootScope.projectChange==true){
			$scope.removeProjectClass();
		}
		$rootScope.selectedMenu = menu;
		menu = menu.heading.replace(/\s/g, '');
		menu =menu.toLowerCase();
		$rootScope.searchContext=menu;
		$rootScope.artifactType= menu;
		$rootScope.projectChange=false;
		$rootScope.selectedSubMenu = undefined;
		var id = $(".leftMenuItem ul:visible:first").attr("id");
    	if(id != null && id != 'undefined'){
    			var idd = id.replace('child','anchor');
    			$('#' + idd).toggleClass('fa-caret-left fa-caret-down');
    	}
		$('.leftMenuItem ul:visible').slideUp('normal');
		$location.path("/"+menu);
		
    
	}
	
	$scope.toggleChildMenu = function(menu, menuAnchor) {
    	if(!$('#' + menu).is(":visible")) {
         	$('#' + menuAnchor).toggleClass('fa-caret-left fa-caret-down');
			$('#' + menu).slideDown('normal');
			
    	}
    };
    
    $scope.isActiveSubMenu = function(subMenu) {
    	var active = $scope.isActiveSubMenu(arguments);
    	return active;
	}
    
    $scope.isActiveSubMenu = function() {
    	var active = false;
    	for(var i=0; i < arguments.length; i++) {
    		if($rootScope.selectedSubMenu === arguments[i][0]){
    			active = true;
    			break;
    		}	
    	}
    	return active;
    }
    
    $scope.getClassForSubMenu = function(menu) {
    	if( $scope.isActiveSubMenu(arguments)) {
    		return 'btn-info';
    	}else
    		return  'menuLabelLeft';
    };
    
    $scope.selectSubMenu = function(menu,subMenu) {
		//PA1501-1202 begin
    	subMenu = subMenu.replace(/\s/g, '');
    	subMenu =subMenu.toLowerCase();
		$rootScope.includeErrorPage=false;
		$rootScope.selectedMenu = menu.heading;
		$rootScope.selectedSubMenu = subMenu;		
		$rootScope.searchContext=subMenu;
		$rootScope.artifactType=subMenu;
		$rootScope.projectChange=false;
		$location.path("/"+subMenu);
        //PA1501-1202 end
    }
    
/*	$scope.checkInIt = function(){
		console.log($rootScope.userData);
		$http({
		    method: 'GET',
		    url: 'checktoken/'+$rootScope.userData.access_token,
		    headers: {
		        'Content-Type': 'application/x-www-form-urlencoded'
		    }}).then(function(result) {
		           console.log(result.data);
		           if(result.data === false){
		        	   $location.path("/login");
		           }
		       }, function(error) {
		    	   console.log(error);
		       });
	};
	*/
	
	$scope.redirectAV = function(){
		$location.url("/allowedvalue");		
	};
	
	$scope.changeLanguage = function(key){
		$translate.use(key);
	};
	
	$rootScope.getUserID = function() {
		
		/*if(angular.isDefined($cookieStore.get('user')))
			return $cookieStore.get('user');*/
		if(angular.isDefined( localStorage.userName))
			return localStorage.userName;
		else
			return sessionStorage.userName;
	}
	
}]);


App.controller('loginController', ['$scope', 'MainUXService','$location','$http','$rootScope','$route','$window', function($scope, MainUXService,$location,$http,$rootScope,$route,$window) {
	$scope.error = false;
	$rootScope.screenId='login';
	$scope.showAlert = false;
	$rootScope.showMenu = false;
	$scope.rememberMe=false;//PA1501-1393
	$scope.isSignOut=false;
	$scope.username;
	
	/*if(angular.isDefined( localStorage.environmentData)&& localStorage.environmentData!=null  )
		{
		$scope.environments=localStorage.environmentData;
		}*/
	
	 
	$scope.closeAlert = function() {
        $scope.showAlert=false;
   };
	$scope.login = function(){
		$scope.showAlert = false;
		$http({
		    method: 'POST',
		    url: 'http://localhost:7080/ng-pw-rest/security/authentication',
		    data: {userName:$scope.username,password:$scope.password},
		    headers: {
		    	 'Content-Type' : 'application/json'
		    		//  "Accept": "application/json"
		    }}).then(function(result) {
		           //console.log(result.data.code);
		           if (result.status == '200' && (result.data.tokenId!=null||result.data.tokenId!='')){
		        	   console.log(result.data);
		        	   //alert("token id " + result.data.tokenId);
		        	   //$rootScope.token = result.data.tokenId;
		        	   localStorage.token=result.data.tokenId;
		        	   localStorage.setItem("tokenId",result.data.tokenId); 
		        	   $scope.errorMessage = "";
		        	   $scope.error = false;
		        	   // api call for get environment
		
		        	
		        	   
		        	   
		        	   
		        	   
		        	
		        	 //  $location.path('/dashboard');
		        	   
		        	   
		        	   
		        
		        	   
		        	   
		        	
		        	   
		        	   
		        	//   $location.url('/index');
		        	  // $location.path("/index");
		           }
		           else if(result.status == '401')
		        	   {
		        	   $scope.errorMessage = result.data.message;
		        	   $scope.showAlert = true;
		        	   }
		           else{
		        	   alert("error recieved ")
		        	   $scope.errorMessage = result.data.message;
		        	   $scope.showAlert = true;
		        	  // window.location.href = "/login.html";
		        	  // $location.path("/login");
		        	   //$route.reload();
		           }
		       }, function(error) {
		    	   console.log(error);
		    	   $scope.errorMessage = "Server Error";
	        	   $scope.error = true;
		       });
		
		
		   $http({
   		    method: 'GET',
   		    url: 'http://localhost:7080/ng-pw-rest/search/common/environment',
   		    headers: {
   		    	 //'Content-Type' : 'application/json',
   		    	 'sessionToken' : localStorage.token
   		    }}).then(function(newResult) {
                 // alert("status of rsult is"+newResult.status);
                  
                  alert(angular.toJson(newResult.data));
                  environmentdata=newResult.data;
                  $scope.environments=newResult.data;
                  
                 // alert("Data in rootscope is"+$rootScope.environments);
  	        	
                  
   		           if (newResult.status == '200' ){
   		        	  // alert("Triggering get environment api");
		        	   window.location.href = "/dashboard.html";
   		        	  // alert(result.data);
   		        	   //console.log(result.data);
   		        	   
   		        	   //localStorage.environmentData=newResult.data;
   		        	 
   		        	   //$rootScope.environments=angular.toJson(newResult.data);
   		        	
   		        	  
   		        	   //$rootScope.token = result.data.tokenId;

   		        	   $scope.errorMessage = "";
   		        	
   		        	 /*  $rootScope.userData = result.data;
   		        	   $rootScope.showMenu = true;
   		        	   $rootScope.userName = result.data.username;
   					   $rootScope.screenId='home';*/
   		      //  	   $state.go(/);
   		        	
   		        	 //  window.location.href = "/dashboard.html";
   		        
   		        //   $location.url('/index');
   		        	  // $location.path("/index");
   		           }
   		           else if(newResult.status == '401')
   		        	   {
   		        	   $scope.errorMessage = result.data.message;
   		        	  // $scope.showAlert = true;
   		        	   }
   		           else{
   		        	   alert("error recieved ")
   		        	   $scope.errorMessage = result.data.message;
   		        	   //$scope.showAlert = true;
   		        	  // window.location.href = "/login.html";
   		        	  // $location.path("/login");
   		        	   //$route.reload();
   		           }
   		       }, function(error) {
   		    	   console.log(error);
   		    	   alert(errror);
   		    	   $scope.errorMessage = "Server Error";
   	        	   $scope.error = true;
   		       });
		
		
		

		
	};
	
	$scope.clearStorage=function(){
		$scope.isSignOut=true;
		if(angular.isDefined(sessionStorage.userName)){
			delete sessionStorage.userName;
			localStorage.clear();
		}
		
	}
	

	
	/*$scope.checkLoginStatus = function() {
		if($location.absUrl().indexOf('error')>0){
			$scope.showAlert=true;
		}else{
			$scope.showAlert=false;
		}	
	};*/
	

	
}]);

App.controller('configuratorAppController', ['$scope', 'MainUXService','$location','$http','$rootScope', function($scope, MainUXService,$location,$http,$rootScope) {
	function closeModals() {
        if ($scope.warning) {
          $scope.warning.close();
          $scope.warning = null;
        }

        if ($scope.timedout) {
          $scope.timedout.close();
          $scope.timedout = null;
        }
    }
	
	$scope.removeProjectClass=function(){
		$("#PROJECT_menu").css("color", "");
		$("#PROJECT_menu").css("background-color", "");
	};
	
	$scope.toggleChildMenu = function(menu, menuAnchor) {
    	if(!$('#' + menu).is(":visible")) {
         	$('#' + menuAnchor).toggleClass('fa-caret-left fa-caret-down');
			$('#' + menu).slideDown('normal');
			
    	}
    };
    
   
}]);
	/*$scope.localLanguage = $window.navigator.userLanguage || $window.navigator.language;
	localize.setLanguage($scope.localLanguage);
	$rootScope.hasProject=false;// PASE-1160
	$rootScope.currentProject={};
	$scope.userName="";
	$rootScope.currentProject['projectId']="NA";
	$scope.projectData=[];
	$rootScope.projectItem={};
	
	
	function closeModals() {
        if ($scope.warning) {
          $scope.warning.close();
          $scope.warning = null;
        }

        if ($scope.timedout) {
          $scope.timedout.close();
          $scope.timedout = null;
        }
    }
	
	$scope.$on('IdleStart', function() {
        closeModals();

        $scope.warning = $uibModal.open({
          templateUrl: 'warning-dialog.html',
          windowClass: 'modal-danger'
        });
    });

	$scope.$on('IdleEnd', function() {
		closeModals();
	});

	$scope.$on('IdleTimeout', function() {
		closeModals();
		$scope.timedout = $modal.open({
			templateUrl: 'timedout-dialog.html',
			windowClass: 'modal-danger'
		});
		DSCacheFactory.clearAll();
		window.location.href="/cxf/authenticate/logout?Authorization="+$rootScope.token;
	});
	
	//Remove hard dependency of dictionary module 
	//PA1501-1367
	$scope.getUserID = function() {
		//PA1501-1393 Begin
		if(angular.isDefined($cookieStore.get('user')))
			return $cookieStore.get('user');
		$scope.userName=="";
		if(angular.isDefined(localStorage.userName)){
			$scope.userName=localStorage.userName;
			return localStorage.userName;
		}
			
		else{
			$scope.userName=sessionStorage.userName;
			return sessionStorage.userName;//PA1501-1393 End	
		}
			
	}
	$scope.selectMenuProject = function(menu) {
		var id = $(".leftMenuItem ul:visible:first").attr("id");
    	if(id != null && id != 'undefined'){
    			var idd = id.replace('Child','Anchor');
    			$('#' + idd).toggleClass('fa-caret-left fa-caret-down');
    	}
		$('.leftMenuItem ul:visible').slideUp('normal');
		
    }
	
	$scope.selectMenu = function(menu) {
		if($rootScope.projectChange==true){
			$scope.removeProjectClass();
		}
		
		$rootScope.selectedMenu = menu;
		$rootScope.searchContext=menu.id;
		$rootScope.artifactType= menu.artifactType;
		$rootScope.projectChange=false;
		//PA1501-1725 begin
		$rootScope.selectedSubMenu = undefined;
        //PA1501-1725 end    
		var id = $(".leftMenuItem ul:visible:first").attr("id");
    	if(id != null && id != 'undefined'){
    			var idd = id.replace('Child','Anchor');
    			$('#' + idd).toggleClass('fa-caret-left fa-caret-down');
    	}
		$('.leftMenuItem ul:visible').slideUp('normal');
		
    }
	
	//PA1501-1725 begin
	$scope.selectSubMenu = function(menu,subMenu) {
		//PA1501-1202 begin
		$rootScope.includeErrorPage=false;
		$rootScope.selectedMenu = menu.id;
		$rootScope.selectedSubMenu = subMenu.id;		
		$rootScope.searchContext=subMenu.id;
		$rootScope.artifactType=subMenu.artifactType;
		$rootScope.projectChange=false;
        //PA1501-1202 end
    }
	$scope.closeAlert = function() {
   		$rootScope.includeErrorPage=false;
    };
    
    //Addes by Prakash for NGPA 323
    $scope.closeDeployAlert = function() {
   		$rootScope.includeDeployPage=false;
   		$rootScope.includeDeploySuccessMsg=false;
   		$rootScope.includeDeployErrorMsg=false;
   		
    };
	//PA1501-1725 end
	$scope.isActiveMenu = function(menu) {
    	var active = $scope.isActive(arguments);
    	return active;
	}
	//PA1501-1725 starts
	$scope.isActiveSubMenu = function(subMenu) {
    	var active = $scope.isActiveSubMenu(arguments);
    	return active;
	}
	//PA1501-1725 ends		
    $scope.toggleChildMenu = function(menu, menuAnchor) {
    	if(!$('#' + menu).is(":visible")) {
         	$('#' + menuAnchor).toggleClass('fa-caret-left fa-caret-down');
			$('#' + menu).slideDown('normal');
			
    	}
    }
    $scope.isSelected = function(menu) {
        if($rootScope.selectedMenu === menu) console.log('returning true')
        return $rootScope.selectedMenu === menu ;
    }
    
    $scope.getClassFor = function(menu) {
    	if($scope.isActive(arguments)) {
    		return 'btn-info';
    	}else
    		return 'menuLabelLeft';
    }
   	//PA1501-1725 starts
    $scope.getClassForSubMenu = function(menu) {
    	if( $scope.isActiveSubMenu(arguments)) {
    		return 'btn-info';
    	}else
    		return  'menuLabelLeft';
    }
	//PA1501-1725 ends    

    $scope.isActive = function() {
    	var active = false;
    	for(var i=0; i < arguments.length; i++) {
    		if($rootScope.selectedMenu === arguments[i][0]){
    			active = true;
    			break;
    		}	
    	}
    	return active;
    }
    
   	//PA1501-1725 starts
    $scope.isActiveSubMenu = function() {
    	var active = false;
    	for(var i=0; i < arguments.length; i++) {
    		if($rootScope.selectedSubMenu === arguments[i][0]){
    			active = true;
    			break;
    		}	
    	}
    	return active;
    }
	//PA1501-1725 ends
    
    //PA1501-2135 starts
    $scope.logOut = function(){    	
    	DSCacheFactory.clearAll();
    }
    //PA1501-2135 end
   
   //PA1601-376 start
    $scope.checkLogOut = function(){    	
    	if(angular.isDefined($rootScope.logoutCommon)){
    		$rootScope.logoutCommon();
    	}
    	else{
    		window.location.href="/cxf/authenticate/logout?Authorization="+$rootScope.token;
			$scope.logOut();
    	}
    }
     //PA1601-376 end
    
	$scope.menuPosition = 'left';
	$scope.isSubMenu = false;
	$scope.isLeftMenu = function(){return $scope.isSignedIn() && $scope.menuPosition == 'left'}
	
	$scope.isTopMenu = function(){return $scope.isSignedIn() && $scope.menuPosition == 'top'}
	
	$scope.toggleMenu = function() {$scope.menuPosition  = $scope.menuPosition  == 'top' ? 'left' : 'top';}
	
	$scope.toggleMenuOption = function(menuOption) {
		switch(menuOption) {
		case 1:	//Simple menu on left
			$scope.menuPosition = 'left';
			$scope.isSubMenu = false;
			break;
		case 2:	//Simple menu on top
			$scope.menuPosition = 'top';
			$scope.isSubMenu = false;
			break;
		case 3:	//Menu on left with submenu
			$('.leftSubMenu').hide();
			$('.submenuAnchor').addClass('fa-caret-left').removeClass('fa-caret-left');
			$scope.menuPosition = 'left';
			$scope.isSubMenu = true;
			break;
		case 4://Menu on top with submenu
			$scope.menuPosition = 'top';
			$scope.isSubMenu = true;
			break;
			
		}
	}
	
	$scope.toggleSubMenu = function() {
		$scope.isSubMenu = !$scope.isSubMenu; 
		console.log($scope.isSubMenu);
	}
	
	$scope.isSignedIn = function() {
		return true;//PA1501-1367
	};
	
	$scope.leftMenuCollapsed = false;
	
	$scope.layoutMe = function(divId, modalToHide) {
		$('#' + modalToHide).modal('hide');
		if($('#' + divId).length > 0) {
			$('#' + divId).layout({
				applyDefaultStyles: true,
				resizable: true,
				east: {
					size: 0.15
				},
				west: {
					size: 0.15}
				});
			}
		}
	$scope.deselectMenu = function(menu) {
        
        $scope.selectedMenu = menu; 
        //$location.path('/signedIn');
    }
	 $scope.closeErrorAlert= function() {
		  $rootScope.showErrorMsg=false;
		  $rootScope.errorList=[];
		  $rootScope.devErrorList=[];
    };
     
	$scope.loadMenu = function() {
		
		var promise1= MenuService.menu();
		promise1.then(				
					function(result) {
						$scope.menus  = result.dataFromService;
						 angular.forEach($scope.menus , function (item) {
					           if(angular.equals(item.label,"PROJECT")){
					        	  $rootScope.projectItem=item;
					           }				      
					        });
						
				});			
	};	
	
	$scope.selectProject = function(){
			if(!(angular.equals($state.current.templateUrl,'common/search.html') && ($rootScope.searchContext =="project"))){
				$scope.getProjectClass();
				$scope.selectMenuProject($rootScope.projectItem);
				$rootScope.projectChange=true;
				if(angular.isDefined($state.current)){
					$rootScope.stateData=$state.current.name;
					$rootScope.lsSearchContext=$rootScope.searchContext;
					$rootScope.lsArtifactType=$rootScope.artifactType;
				}
				$rootScope.searchContext="project";
				$rootScope.artifactType="project";
				$rootScope.searchResult();
			}
		};
		
		
		$scope.removeProjectClass=function(){
			$("#PROJECT_menu").css("color", "");
			$("#PROJECT_menu").css("background-color", "");
		};
		
		
		$scope.getProjectClass =function(){
			$("#PROJECT_menu").css("color", "#2a6496");
			$("#PROJECT_menu").css("background-color", "#A4D3FF");
		};	
	
	$scope.getProject= function(){
		$scope.getUserID();
		$rootScope.currentProject['projectId'] ="NA";
		if(angular.isDefined($scope.userName)){
			var promise= MenuService.currentProject($scope.userName);
			promise.then(				
						function(result) {
						    //$rootScope.hasProject=true;
							$scope.projectData=result.dataFromService;
							  if(angular.isDefined($scope.projectData) && $scope.projectData.length!=0 && $scope.projectData[0].activeProject!=""){
								  var currProject=	$scope.projectData[0].activeProject;
								  $rootScope.currentProject['projectId'] =currProject;
							  }
							  else{
							      $rootScope.currentProject['projectId'] ="NA";
							  }
					});
		}
	}
    	    
    $http.get('json/pADetails.json').success(
			function(data) {
				$scope.details = data.domain;				
			});
    
		}]).controller('errorHandlingCtrl', ['$scope','$rootScope','$state', function($scope,$rootScope,$state){
			$scope.errorCode=$rootScope.error.errorStatusCode;
			$scope.errorMessage=$rootScope.error.errorMsg;

		}]).controller('deployCtrl', ['$scope','$rootScope','$state', function($scope,$rootScope,$state){
			//
		}]);
*/

