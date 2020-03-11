
function initMap() { 
	console.log("SALMAN GET LOCATION");
	var res =  getWindFarmLocation();
	
}

function createMap(res){
	
	console.log("SALMAN GET LOCATION");
	
	var locs = [];
	for (i=0;i<res.length;i++){
		
	locs.push({lat: parseFloat(res[i].farmLatitude), lng: parseFloat(res[i].farmLongitude), flag: res[i].hasAlerts});
	}
	
	/*var locs = [
					 {lat: 31.9686, lng: -99.9018,   windfarm_id: "w_farm", city: "City  1", flag:'red'},
	                 {lat: -18.24587, lng: -43.59613, windfarm_id: "w_farm", city: "City  2",flag:'green'},
	                 {lat: 36.7783, lng: -119.4179,windfarm_id: "w_farm", city: "City  3", flag:'red'}, 
	                 {lat: 56.1304, lng: -106.3468,windfarm_id: "w_farm", city: "City  4",flag:'green'}, 
	                 {lat: 45.5017, lng: -73.5673, windfarm_id: "w_farm", city: "City  5",flag:'green'}, 
	                 
	                 ]*/


	var map = new google.maps.Map(document.getElementById('map'), {
		zoom: 4.7,
		center:{lat: 39.5501, lng: -105.7821} //Colorado 

	});

	var contentString ;
	var infoWin = new google.maps.InfoWindow({
		content: contentString
	});

	var content = [];
	 for (var i=0; i < res.length; i++) {
		 console.log(i);
		 contentString = '<div id="content">'+
         '<div id="siteNotice" style="background-color: #000000;"></div>'+
		 '<b>Windfarm Id:        '+res[i].windFarmId+'</b><br>'+
       	 '<b>Location:            '+res[i].locationCity+'</b><br>'+
       	 '<b>State:            '+res[i].locationState+'</b><br>'+
       	 '<b>Total Energy Generated:         '+res[i].totalEnergyGenerated+'MWh</b><br>'+
       	 '<b>Total Energy Consumed:   				'+res[i].totalEnergyConsumed+'MWh</b><br><br>'+
         '<div id="bodyContent">'+
         '<p><i class="fas fa-link"></i> <a id="monitor_link" href="farmdashboard/'+res[i].windFarmId+'">'+
         'Farm Dashboard</a></b></p>'+
		 '</div>'+
         '</div>';
		 
		 var location = [];
		 location[0]=contentString;
		 location[1]=locs[i].lat;
		 location[2]=locs[i].lng;
		 location[3]=locs[i].flag;
		 content[i]=location;
		}

	 var locations = content;
	 
	

    var infowindow = new google.maps.InfoWindow();

    var marker, i;

    for (i = 0; i < locations.length; i++) {  
	if (locations[i][3])
		var image = './resources/images/redMarker.png';
	else if (!locations[i][3])
		var image = './resources/images/greenMarker.png';
     

	marker = new google.maps.Marker({
        position: new google.maps.LatLng(locations[i][1], locations[i][2]),
		icon: image,
        map: map
      });

      google.maps.event.addListener(marker, 'click', (function(marker, i) {
        return function() {
          infowindow.setContent(locations[i][0]);
          infowindow.open(map, marker);
        }
      })(marker, i));
    }
}


function getWindFarmLocation() {
	console.log("salman");
	$.ajax({
		url: "getWindFarmLocation",
		type: 'GET',
		success: function(res) {
			console.log("Salman   :  " + res.length);
			createMap(res);
		}
	});
}