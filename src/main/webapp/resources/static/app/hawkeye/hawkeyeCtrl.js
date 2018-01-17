appRaamApp.controller('HawkeyeController', ['$scope', '$rootScope', '$route', '$compile', '$timeout', '$location', 'HawkeyeService', '$window',
function ($scope, $rootScope, $route, $compile, $timeout,  $location, HawkeyeService, $window) {  /*'uiGmapIsReady'*/
// while refreshing the page or loading of page we are remove the cookie store loan details.
	var vm = this;
/*	$scope.map = '';*/
 /*   var latlng;
    var locations = {};*/
    var map;
	var markersData = [];
	var beaconMarkersData = [];
/*	var bounds;*/
    $scope.tab = 1;

    $scope.setTab = function(newValue){
      $scope.tab = newValue;
      /*     if($scope.tab == 1){
		$scope.trips();
	}*/
    };

/*    HawkeyeService.fetchUserAndRole(function(response){
			var sessionResponse = response.data.data.object;
			console.log("Session response : "+JSON.stringify(sessionResponse));
    });*/
    
    $scope.isSet = function(tabName){
      return $scope.tab === tabName;
     };
     
/*     setTimeout(function(){*/
     	 wpBeacons();
/*		}, 1000);*/
     
    	 function wpBeacons(){
    		 HawkeyeService.fetchAllWPBeacons(function(response){
    		 		if(response.call){
    		 			var beaconsData = response.data.data.object;
    		 			//console.log("Trips : "+JSON.stringify($scope.trips));
    		 			//drawLatestTrips($scope.trips);
    		 			//var idkey = 0;
    		 			for (var i = 0; i < beaconsData.length; i++) {
    		 				var uuId = undefined;
    		 				var macId = undefined;
/*    		 				var wptype = undefined;*/
    		 				
    		 				if(beaconsData[i].uuid){
    		 					uuId = beaconsData[i].uuid;
    		 				}
    		 				if(beaconsData[i].macId){
    		 					macId = beaconsData[i].macId;
    		 				}
/*    		 				if(beaconsData[i].type){
    		 					wptype = beaconsData[i].type;
    		 				}*/
    						
    		 				beacons = {
    		 					lat : beaconsData[i].lat,
    		 				    lng : beaconsData[i].lng,
    		 				    uuid : uuId,
    		 				    macid : macId,
    		 				}
    		 				beaconMarkersData.push(beacons);
    		 			}
    		 			
       		    	 tripsData();
    		 		}
    		 });
    	 }
     function tripsData(){
     HawkeyeService.fetchAllTrips(function(response){
 		if(response.call){
 			var tripsData = response.data.data.object;
 			//console.log("Trips : "+JSON.stringify($scope.trips));
 			//drawLatestTrips($scope.trips);
 			//var idkey = 0;
 			for (var i = 0; i < tripsData.length; i++) {
 				var vehicleNum = undefined;
 				var driverNam = undefined;
 				var status = undefined;
 				
 				if(tripsData[i].vehicleprofile){
 					vehicleNum = tripsData[i].vehicleprofile.vehicleNo
 				}
 				if(tripsData[i].driverprofile){
 					driverNam = tripsData[i].driverprofile.driverFirstName+" "+tripsData[i].driverprofile.driverLastName;
 				}
 				if(tripsData[i].configuration){
 					status = tripsData[i].configuration.status
 				}
				
 				trips = {
 					lat : tripsData[i].lat,
 				    lng : tripsData[i].lng,
 				    vehicleNo : vehicleNum,
 				    noOfTrips : tripsData[i].trips.trips,
 				    status : status,
 				    speed : tripsData[i].speed+" "+"Km/h",
 				    drivername : driverNam
 				}
 				markersData.push(trips);
 			}
 			initMap();
 		}
 	});
  }
     
     function initMap() {
    	 var mapOptions = {
                 zoom: 16,
                 center: new google.maps.LatLng("12.9716", "77.5946"),/*"12.9716", "77.5946"*/
                 mapTypeId: google.maps.MapTypeId.ROADMAP
/*                 mapTypeControl: false,
                 mapTypeControlOptions: {
                     style: google.maps.MapTypeControlStyle.HORIZONTAL_BAR
                 },
                 navigationControl: true,
                 navigationControlOptions: {
                     style: google.maps.NavigationControlStyle.SMALL
                 }*/
             };
    	 
       map = new google.maps.Map(document.getElementById('map'), mapOptions);
       
    // a new Info Window is created
	   infoWindow = new google.maps.InfoWindow();

	   // Event that closes the Info Window with a click on the map
	   google.maps.event.addListener(map, 'click', function() {
	      infoWindow.close();
	   });
		      
	   // Finally displayMarkers() function is called to begin the markers creation
	   //displayMarkers();
	   //displayBeaconMarkers();
	   setMapBounds();
     }
		//google.maps.event.addDomListener(window, 'load', initialize);
     
		// This function will iterate over markersData array
		// creating markers with createMarker function
		function displayMarkers(bounds){

		   // this variable sets the map bounds according to markers position
		   //var bounds = new google.maps.LatLngBounds();
		   // for loop traverses markersData array calling createMarker function for each marker 
		   for (var i = 0; i < markersData.length; i++){
			   
			   if(markersData[i].lat != null && markersData[i].lng != null){
		      var latlng = new google.maps.LatLng(markersData[i].lat, markersData[i].lng);
/*		      var serfId = markersData[i].serfId;*/
		      var vehicleNo = markersData[i].vehicleNo;
		      var status = markersData[i].status;
		      var noOfTrips = markersData[i].noOfTrips;
		      var speed = markersData[i].speed;
		      var drivername = markersData[i].drivername;
		      //var postalCode = markersData[i].postalCode;

		      createMarker(latlng, vehicleNo, status, noOfTrips, speed, drivername);
		      // marker position is added to bounds variable
			      bounds.extend(latlng);
		   
			   }
		   }
		   return bounds;
		   //displayBeaconMarkers(bounds);
		   // Finally the bounds variable is used to set the map bounds
		   // with fitBounds() function				  
		}
		
		// creating markers with createMarker function
		function displayBeaconMarkers(){

		   // this variable sets the map bounds according to markers position
		  var bounds = new google.maps.LatLngBounds();
		   // for loop traverses markersData array calling createMarker function for each marker 
		   for (var i = 0; i < beaconMarkersData.length; i++){
			   
			   if(beaconMarkersData[i].lat != null && beaconMarkersData[i].lng != null){
		      var latlng = new google.maps.LatLng(beaconMarkersData[i].lat, beaconMarkersData[i].lng);
/*		      var serfId = markersData[i].serfId;*/
		      var uuId = beaconMarkersData[i].uuid;
		      var macId = beaconMarkersData[i].macid;
/*		      var type = beaconMarkersData[i].type;*/
		      //var postalCode = markersData[i].postalCode;

			  createBeaconMarker(latlng, macId, uuId);
		      // marker position is added to bounds variable
			  
/*		      if (bounds.contains(latlng)) {
		          // marker is within bounds
		      }else{*/
			      bounds.extend(latlng); 
/*		      }*/
			   
/*			   if (map.getZoom()>15) {
				   console.log("ZOom level : "+map.getZoom());
				   map.setCenter(latlng);
		       	    map.setZoom(15);
		           }*/
		   
			   }
		   }
		   
		   // Finally the bounds variable is used to set the map bounds
		   // with fitBounds() function		
		   /*try {
			      adddlert("Welcome appraam!");
				 map.fitBounds(bounds);
			}
			catch(err) {
			    console.log(err.message);
			}*/
			
			return bounds;
		}
		
		function setMapBounds(){
			
			var beaconbounds = displayBeaconMarkers();
			var bounds = displayMarkers(beaconbounds);

/*			   if (map.getZoom()>15) {
		       	    map.setZoom(15);
		           }*/
				   map.fitBounds(bounds);
		}
		
		// This function creates each marker and it sets their Info Window content
		function createBeaconMarker(latlng, macId, uuId){
/*			   var statusIcon;
			   if(status == "Active" || status == "Average"){
			   statusIcon = './assets/img/icons/green_signal.gif';
			   }else if(status == "Slow"){
				   statusIcon = './assets/img/icons/orange_signal.gif';
			   }else{
				   statusIcon = './assets/img/icons/red_signal.gif';
			   }*/
			if(uuId.substring(0, 2) == "a0"){
				   var beaconIcon = './assets/img/icons/jcb_30.png';
			}
			else if(uuId.substring(0, 2) == "f0"){
				   var beaconIcon = './assets/img/icons/rock.png';
			}else{
			   var beaconIcon = './assets/img/icons/marker_18.png';
			}
		   var marker = new google.maps.Marker({
		      map: map,
		      position: latlng,
		      macid: macId,
		      uuid: uuId,
		      icon: beaconIcon
		   });
		   
		   // This event expects a click on a marker
		   // When this event is fired the Info Window content is created
		   // and the Info Window is opened.
		   google.maps.event.addListener(marker, 'mouseover', function() {

			   var contentString = '<div id="content">'+
	              '<div id="siteNotice">'+
	              '</div>'+
	              '<p> <b> Beacon Uuid </b>: '+marker.uuid+'</p>'+/*'<h1 id="firstHeading" class="firstHeading">'*/ 
	               '<div id="bodyContent">'+
	              '<p> <b> Beacon MacId</b> : '+marker.macid+'</p>'+
/*	              '<p><b> Beacon type</b> : '+marker.beaconType+'</p>'+*/
	              '</div>'+
	              '</div>'; 
			      infoWindow.setContent(contentString);
			      
			      infoWindow.open(map, marker);
			   });
		   google.maps.event.addListener(marker, 'mouseout', function() {
			      infoWindow.close();
			   });
		   
		   /*google.maps.event.addListenerOnce(map, 'bounds_changed', function(event) {
			   map.setZoom(map.getZoom()-1);

			   if (map.getZoom() > 15) {
			     map.setZoom(15);
			   }
			 });*/
		}
		
		// This function creates each marker and it sets their Info Window content
		function createMarker(latlng, vehicleNo, status, noOfTrips, speed, drivername){
			   var statusIcon;
			   if(status == "Active"){
			   statusIcon = './assets/img/icons/green_tipper.png';
			   }else if(status == "Slow"){
				   statusIcon = './assets/img/icons/orange_tipper.png';
			   }else if(status == "Idle" || status == "Turned off / Maintenance" || status == "Check"){
				   statusIcon = './assets/img/icons/red_27_27.gif';
			   }else if(status == "Fast" || status == "Very fast"){
				   statusIcon = './assets/img/icons/red_tipper.png';
			   }else if(status == "Activator" || status == "Loading"){
				   statusIcon = './assets/img/icons/L_location_black_small.png';
			   }else if(status == "Parking"){
				   statusIcon = './assets/img/icons/P_location_black_small.png';
			   }else if(status == "Terminator" || status == "Unloading"){
				   statusIcon = './assets/img/icons/U_location_black_small.png';
			   }/*else{
				   statusIcon = './assets/img/icons/black_27_27.gif';
			   }*/
			   
		   var marker = new google.maps.Marker({
		      map: map,
		      position: latlng,
		      vehicleNo: vehicleNo,
		      noOfTrips: noOfTrips,
		      speed: speed,
		      status: status,
		      driverName: drivername,
		      icon: statusIcon,
              optimized: false
		   });
		   
		   // This event expects a click on a marker
		   // When this event is fired the Info Window content is created
		   // and the Info Window is opened.
		   google.maps.event.addListener(marker, 'mouseover', function() {
			   
			   var contentString = '<div id="content">'+
	              '<div id="siteNotice">'+
	              '</div>'+
	              '<p> <b> Vehicle No </b>: '+marker.vehicleNo+'</p>'+/*'<h1 id="firstHeading" class="firstHeading">'*/ 
	               '<div id="bodyContent">'+
	              '<p> <b> Driver name</b> : '+marker.driverName+'</p>'+
	              '<p><b> Trip count</b> : '+marker.noOfTrips+'</p>'+
	              '<p><b> Vehicle Speed</b> : '+marker.speed+'</p>'+
	              '<p><b> Status</b> : '+marker.status+'</p>'+
	              '</div>'+
	              '</div>'; 
			      infoWindow.setContent(contentString);
			      
			      infoWindow.open(map, marker);
			   });
		   google.maps.event.addListener(marker, 'mouseout', function() {
			      infoWindow.close();
			   });
		   
/*		   google.maps.event.addListenerOnce(map, 'bounds_changed', function(event) {
			   map.setZoom(map.getZoom()-1);

			   if (map.getZoom() > 15) {
			     map.setZoom(15);
			   }
			 });*/
		}
		
	//$scope.markers = [];
/*	function initialize() {*/
/*        var mapProp= {*/
/*    $scope.mapProp= {
                center:new google.maps.LatLng(12.9566675, 77.716995),
                zoom:5,
            };
    
            $scope.map=new google.maps.Map(document.getElementById("map_canvas"),$scope.mapProp);*/
/*            }*/
	
/*	uiGmapGoogleMapApiProvider.then(function(maps) {
	    $scope.map     = { center: { latitude: areaLat, longitude: areaLng }, zoom: areaZoom };
	    $scope.options = { scrollwheel: false };
	  });*/
/*	$scope.randomMarkers = '';
	$scope.markers = [];
	
	$scope.map = {
            center: {
                    latitude: 12.9566675,
                    longitude: 77.716995
            },
            zoom: 8
    };*/
	
/*	$scope.options = {
		      scrollwheel: false
		    };*/
	
/*	angular.extend($scope, {
	    map: {
	      control: {},
	      center: {
	        latitude: 12.9566675,
	        longitude: 77.716995
	      },
	      options: {
	        streetViewControl: false,
	        panControl: false,
	        maxZoom: 20,
	        minZoom: 3
	      },
	      zoom: 7,
	      dragging: false,
	      bounds: {},
	      dynamicMarkers: [],
	      refresh: function () {
	        $scope.map.control.refresh(origCenter);
	      }
	    }
	  });*/

/*	$timeout(function () {
	    var dynamicMarkers = [
	      {   id: 1,
	        latitude: 46,
	        longitude: -79
	      },
	      {
	        id: 2,
	        latitude: 33,
	        longitude: -79
	      },
	      {
	        id: 3,
	        icon: 'assets/img/icons/tipper_green.png',
	        latitude: 35,
	        longitude: -127
	      }
	    ];
	    _.each(dynamicMarkers, function (marker) {
	      marker.closeClick = function () {
	        marker.showWindow = false;
	        $scope.$apply();
	      };
	      marker.onClicked = function () {
	        $scope.onMarkerClicked(marker);
	      };
	    });
	    $scope.map.dynamicMarkers = dynamicMarkers;
	  }, 2000);

	var origCenter = {latitude: $scope.map.center.latitude, longitude: $scope.map.center.longitude};*/
	
	//trips();
        
/*    $scope.mapMarkers = function(bounds){
    var createRandomMarker = function(bounds, idKey) {
        var lat_min = bounds.southwest.latitude,
          lat_range = bounds.northeast.latitude - lat_min,
          lng_min = bounds.southwest.longitude,
          lng_range = bounds.northeast.longitude - lng_min;

        if (idKey == null) {
          idKey = "id";
        }

        var latitude = lat_min + (Math.random() * lat_range);
        var longitude = lng_min + (Math.random() * lng_range);
        var ret = {
          latitude: latitude,
          longitude: longitude,
          title: 'm' + i
        };
        ret[idKey] = i;
        return ret;
      };
      var markers = [];
      for (var i = 0; i < 50; i++) {
        markers.push(createRandomMarker(i, $scope.map.bounds))
      }
      vm.randomMarkers = markers;
	}*/
/*	 vm.windowOptions = {
	            visible: false
	        };
	        vm.onClick = function() {
	            $scope.windowOptions.visible = !$scope.windowOptions.visible;
	        };
	        vm.closeClick = function() {
	            $scope.windowOptions.visible = false;
	        };*/
	        
     $scope.addMarker = function (lat, lng, status, idkey) {
		if($scope.lat != undefined && $scope.lng != undefined){
			var icon = "";
			if(status == "Fast" || status == "Slow" || status == "InActive"){
				icon = "../asserts/img/icons/tipper-red.png";
			}else{
				icon = "../asserts/img/icons/tipper-green.png";
			}
			
/*	      var marker = new google.maps.Marker({*/
		  $scope.marker = new google.maps.Marker({	  
	        map: $scope.map,
	        position:  new google.maps.LatLng($scope.lat, $scope.lng)
	      //icon: icon
	      });
/*	      $scope.markers.push({
              id: Date.now(),
              coords: {
                latitude: lat,
                longitude: lng
              },
              
            });*/

	      $scope.markers.push(marker);
		}
    };
	 
/*   $scope.trips = function(){*/
 
/*   }*/
/*    function drawTrips(){
    	drawLatestTrips($scope.trcaseuser);
    }*/
    
    function drawLatestTrips(response){
    	
    	if(response){
    		 initializeMap(response,function(latlang){
    			 locations.latlng = latlang;
      			//var markerBounds = new google.maps.LatLngBounds();
    			 setupMap(function(){
    				 $.each(response, function( index, value ) {
    					var infoWindow = new google.maps.InfoWindow();
          				var startMarker =new google.maps.Marker({
          		            map: locations.map,
          		            position: new google.maps.LatLng(value.lat, value.lng),
          		            title: value.trips.uuid,
          		        });
          				//markerBounds.extend(startMarker.position);
						var infoWindow2 = new google.maps.InfoWindow();
/*						if(value.userCase=='C'){
          				if(value.priority == 'LOW'){
          					startMarker.setIcon('content/images/Pointers-L4.png');
          				} else if(value.priority == 'MEDIUM'){
          					startMarker.setIcon('content/images/Pointers-M4.png');
          				} else if(value.priority == 'HIGH'){
          					startMarker.setIcon('content/images/Pointers-H4.png');
          				}else if(value.priority == 'CRITICAL'){
          					startMarker.setIcon('content/images/Pointer-C4.png');
          				}
          				var span='<a userId="'+value.geofenceName+'" class="userpath" >Load travelled path</a>';
          				startMarker.content = '<div class="infoWindowContent"><b>Case Id : </b>'+value.id+'<br> <b>ReportedBy :</b> ' +value.reportedByUser+'<br> <b> AssignedTo :</b> '+value.assignedToUser+'<br> <b>Address :</b> '+value.address+'</div>';
          				
						}else{*/
					    if(value.configurationData[0].Status == 'Active' || value.configurationData[0].Status == 'Average'){
               			    startMarker.setIcon('http://maps.google.com/mapfiles/ms/icons/green-dot.png');
					    	//startMarker.setIcon('../asserts/img/icons/tipper-green.png');
               			} else {
               			    startMarker.setIcon('http://maps.google.com/mapfiles/ms/icons/red-dot.png');
               				//startMarker.setIcon('../asserts/img/icons/tipper-red.png');
               			}
					        var span='<a driverId="'+value.trips.driverId+'"  class="userpath" >Loaded driver Id</a>';
               				var assigncasespan='<a driverId="'+value.trips.driverId+'" uuid="'+value.trips.uuid+'" class="assigncase" >Driver details</a>';
               				startMarker.content = '<div class="infoWindowContent">' +value.trips._id+'<br>'+assigncasespan+'</div>';	
//						}
						
						$compile(startMarker.content)($scope);
           			    google.maps.event.addListener(startMarker, 'mouseover', function(){
							infoWindow.setContent('<div class="infoWindowhead">' + startMarker.title + '</div>' + startMarker.content);
							infoWindow.open(locations.map, startMarker);
           		        });
					   
	
						// on mouseout 
						google.maps.event.addListener(startMarker, 'mouseout', function() {
							infoWindow.close();	
						});
						
						var activeInfoWindow ;
						// add content to InfoWindow for click event 
						infoWindow2.setContent('<div class="infoWindowhead">' + startMarker.title + '</div>' + startMarker.content);
						
						// add listener on InfoWindow for click event
						google.maps.event.addListener(startMarker, 'click', function() {
							
							if(activeInfoWindow != null) activeInfoWindow.close();
							// Open InfoWindow - on click 
							infoWindow2.open(locations.map, startMarker);
							$(".userpath").click(function(){
								var ele= document.getElementById("userPathModal");
								$(ele).modal('show');
								 vm.drawUserPath($(this).attr('userid'));
							 });
							$(".assigncase").click(function(){
								$('#assigncaseModal').modal('show');
								vm.assigncasetouser($(this).attr('userId'), $(this).attr('userLogin'));
								
							 });
							
							// Store new open InfoWindow in global variable
							activeInfoWindow = infoWindow2;
						}); 							
						//resizeMap(markerBounds);
					});
				}); 
			});
		}
    	
	function setupMap(callback){
		var myOptions = {
                zoom: 10,
                center: locations.latlng,
                mapTypeId: google.maps.MapTypeId.ROADMAP
            };
		locations.map = new google.maps.Map(document.getElementById('map'), myOptions); 
		console.log("MAp : "+locations.map);
		locations.overlay = new google.maps.OverlayView();
		locations.overlay.draw = function(){};
		locations.overlay.setMap($scope.map);
		locations.element = document.getElementById('map');
            callback();
	}
    }
    
    function resizeMap(markerBounds){
		setTimeout(function(){
	    	   google.maps.event.trigger(locations.map, "resize");
	    	   locations.map.setCenter(locations.latlng);
			   locations.map.fitBounds(markerBounds);
	       },1000); 
	}
    
    function initializeMap(latlangs, callback){
		if(latlangs && latlangs.length>0 && latlangs[0].lat != null && latlangs[0].lng != null){
			 latlng = new google.maps.LatLng(latlangs[0].lat,latlangs[0].lng);
			 callback(latlng);
		 }else{
/*	         callback(new google.maps.LatLng("12.9566675", "77.716995"));*/
       	  callback(new google.maps.LatLng("12.9716", "77.5946"));
		 }
	}
    
    $scope.refresh = function(){
    	$route.reload();
    };
//}
}
]);
