angular.module('userManagementModule', [])
  .controller('viewLogController', function($scope,$http) {
  $scope.showResult = false;
  $scope.noResult = false;
  $scope.getLogDetailsForGivenUserID = function(userID){
	  var url = 'http://localhost:8080/usermanagement/rest/errors';
	  if (userID  != '') {
	        url = url + '/' + userID;
	  }
	  $http.get(url).then(function(response) {
		  $scope.data = response.data;
		    if($scope.data ==''){
		    	$scope.noResult = true;
		    	$scope.showResult=false;
		    }else{
		    	$scope.showResult = true;
		    	$scope.noResult = false;
		    }
		  });
  }
  
});
