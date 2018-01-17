/*appRaamApp.config(function(uiGmapGoogleMapApiProvider) {
    	    uiGmapGoogleMapApiProvider.configure({
    	        key: 'AIzaSyDAZm2YGUI5f2URRLxqkiAexWYPE7Z6SkE', &libraries
    	        v: '3.20', //defaults to latest 3.X anyhow
    	        libraries: 'weather,geometry,visualization',
    	        //transport: 'http',
    	        //isGoogleMapsForWork: true
    	    });
    	});*/
/*appRaamApp.config(['uiGmapGoogleMapApiProvider', function(uiGmapGoogleMapApiProvider) {
        uiGmapGoogleMapApiProvider.configure({
            key: 'AIzaSyDAZm2YGUI5f2URRLxqkiAexWYPE7Z6SkE',
            v: '3.8.0',
            libraries: 'weather,geometry,visualization'
        });
    }]);*/
/*appRaamApp.directive('uiGmapMarkers', function (objSvc) {
    return {
        require: 'ngModel',
        link: function (scope, elm, attrs, ctrl) {

          // do something
        }
    };
});*/
window.addEventListener('load',function(){
	  var script = document.createElement('script');
	  script.type = 'text/javascript';
	  script.src = '//maps.googleapis.com/maps/api/js?key=AIzaSyDLVWFeNy9E2WZLGCHZHvFyvl1ZtRUble0&libraries=geometry,drawing';
	  document.body.appendChild(script);
	});