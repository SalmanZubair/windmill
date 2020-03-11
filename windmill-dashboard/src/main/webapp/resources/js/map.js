var windFarmId='';

function setWindFarmId(id) {
	windFarmId = id;
	console.log("########################   :  " + windFarmId);
}

function initMap() { 

	var res =  getLocation();
}

function getLocation() {
	console.log("farmId from Session");
	var farmId = sessionStorage.getItem("farmId");
	$.ajax({
		url: "getLocation/" + "tx101",
		type: 'GET',
		success: function(res) {
			createMap1();
		}
	});
}

function createMap1(){
	console.log("ABCDEFG  :   " + windFarmId);
	$.ajax({
		url: "getLocation/" + windFarmId,
		type: 'GET',
		success: function(res) {
			console.log("Salman please get location dynamic  :  " + res.length);
	var locations = [];
	for (i=0;i<res.length;i++){
		
	locations.push({lat: parseFloat(res[i].latitude), lng: parseFloat(res[i].longitude), info:'<div id="content">'+
       	 '<div id="siteNotice" style="background-color: #000000;"></div><div id="bodyContent"><p>'+
       	 '<b>WindMill Id:        '+res[i].windmill_id+'</b><br>'+
       	 '<i class="fa fa-link" aria-hidden="true" style="color: #87AFC7"></i><a id="monitor_link" href="monitor/'+res[i].windmill_id+'">'+
       	 ' Dashboard</a></b>'+
       	 '</p></div></div>',flag: res[i].hasAlert});
	}
	
	
	console.log("locations stored lang are");
	console.log(locations); 
	
	var zm = 15.4;
	if(windFarmId == 'tx101')
		zm=13.3;
	
	var map = new google.maps.Map(document.getElementById('map'), {
		zoom: zm,
		center:new google.maps.LatLng(locations[4].lat, locations[4].lng),
		disableDefaultUI: true,
		
		});

	 var infowindow = new google.maps.InfoWindow();
	
	var marker, i;

    for (i = 0; i < locations.length; i++) {
    	
    	if (!locations[i].flag)
    		 var image = './images/windmill-green1.png';
    	else if (locations[i].flag)
    		var image = './images/windmill-red1.png';

	marker = new google.maps.Marker({
        position: new google.maps.LatLng(locations[i].lat, locations[i].lng),
        icon: image,
		title: 'Click',
        map: map
      });

      google.maps.event.addListener(marker, 'click', (function(marker, i) {
        return function() {
          infowindow.setContent(locations[i].info);
          infowindow.open(map, marker);
        }
      })(marker, i));
    }
		}	});
}

