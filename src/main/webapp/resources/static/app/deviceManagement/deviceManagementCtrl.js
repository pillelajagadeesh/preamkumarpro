appRaamApp.controller('DeviceManagementController', ['$scope', '$rootScope', '$timeout', '$location', 'DeviceManagementService', '$window',
function ($scope, $rootScope, $timeout,  $location, DeviceManagementService, $window) {
// while refreshing the page or loading of page we are remove the cookie store loan details.
	// Get the modal
	var modal = document.getElementById('myModal');

	// Get the button that opens the modal
	var btn = document.getElementById("myBtn");

	// Get the <span> element that closes the modal
	var span = document.getElementsByClassName("close")[0];

	// When the user clicks the button, open the modal 
	btn.onclick = function() {
	    modal.style.display = "block";
	}

	// When the user clicks on <span> (x), close the modal
	span.onclick = function() {
	    modal.style.display = "none";
	}

	// When the user clicks anywhere outside of the modal, close it
	window.onclick = function(event) {
	    if (event.target == modal) {
	        modal.style.display = "none";
	    }
	}

	 /*$scope.showAdvanced = function(ev) {
		    $mdDialog.show({
		      controller: DialogController,
		      templateUrl: 'serf.dialog.html',
		      parent: angular.element(document.body),
		      targetEvent: ev,
		      clickOutsideToClose:true
		    })
		    .then(function(answer) {
		      $scope.status = 'You said the information was "' + answer + '".';
		    }, function() {
		      $scope.status = 'You cancelled the dialog.';
		    });
		  };*/
		  
	DeviceManagementService.fetchAllSerfs(function(response){
/*		if(response.call){*/
			console.log(JSON.stringify(response));
			//$scope.serfs = response.data.data.object;
			$scope.serfs = response.data.data;
/*		}*/
	});
	
	$scope.save = function(serf) {
        if (serf) {
        	DeviceManagementService.saveSerf(serf, function(response){
        		if(response.call){
        			console.log("Success");
        		}
        	});
        }
      };
	
}
]);
