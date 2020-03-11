function getWindFarmEnergy(farmId) {

	var windspeed = [];
	var totalenergy = [];

	var chart = new CanvasJS.Chart("chartContainer2", {
		backgroundColor : "#FFFFFF",
		animationEnabled : true,
		legend : {
			horizontalAlign : "center", // "center" , "right"
			verticalAlign : "top", // "top" , "bottom"
		},

		axisY : {
			title : "Power(kW)",
			titleFontColor : "#848482",
			labelFontColor : "#848482",
			gridColor : "#D1D0CE",
			interval : 100,
			lineColor : "#D1D0CE",
			tickColor : "D1D0CE"
		},
		axisY2 : {
			title : "Wind Speed(m/s)",
			titleFontColor : "#848482",
			maximum: 50,
			labelFontColor : "#848482",
			lineColor : "#D1D0CE",
			tickColor : "D1D0CE",
			interval : 5
		},
		axisX : {
			titleFontColor : "#848482",
			labelFontColor : "#848482",
			lineColor : "#D1D0CE",
			tickColor : "D1D0CE",
			valueFormatString : "HH:mm",

		},

		data : [ {
			type : "spline",
			name : "Wind Speed",
			axisYType : "secondary",
			name : "Wind Speed",
			lineColor : "#BFA466",
			markerType: "none",
			legendMarkerColor : "#BFA466",
			color : "#BFA466",
			fillOpacity : .3,
			showInLegend : true,
			dataPoints : windspeed
		}, {
			type : "spline",
			name : "Power",
			lineColor : "#4863A0",
			markerType: "none",
			legendMarkerColor : "#4863A0",
			color : "#4863A0",
			fillOpacity : .3,
			showInLegend : true,
			dataPoints : totalenergy
		} ]
	});

	var updateChart = function() {

		$.ajax({
			url : "getWindFarmEnergy/" + farmId,
			// url : "getWindFarmEnergy/"+"tx101",
			type : 'GET',
			success : function(res) {
				console.log("PowerTrend res----------------------");
				console.log(res);

				for (var i = 0; i < res.length; i++) {
					var dateVar = res[i].timestamp.split(" ");
					var dateArr = dateVar[0].split("-");
					var timeArr = dateVar[1].split(":");
					windspeed.push({
						x : new Date(Date.UTC(dateArr[0], dateArr[1] - 1,
								dateArr[2], timeArr[0], timeArr[1], timeArr[2]
										.split(".")[0])),
						y : Number(res[i].windSpeed)
					});
					totalenergy.push({
						x : new Date(Date.UTC(dateArr[0], dateArr[1] - 1,
								dateArr[2], timeArr[0], timeArr[1], timeArr[2]
										.split(".")[0])),
						y : Number(res[i].totalAveragePower)
					});
				}
				console.log("powerSpeed array:\n")
				console.log(windspeed);

				chart.render();
			}

		});
	};
	updateChart();
	setInterval(function() {
		updateChart()
	}, 720000);
}

function energyBar(farmId) {

	var i = 0;
	var windSpeed = 0;

	$.ajax({
		url : "getFarmEnergyWindspeed/" + farmId,
		type : 'GET',
		success : function(res) {
			console.log("getFarmEnergyWindspeed : ");
			console.log(res);
			i = res.totalEnergySum;
			document.getElementById("value1").innerHTML = i + " MWh";
			makeProgress();
			windSpeed = res.windSpeed;

			// calling tempBar2 to avoid another ajax call
			getWindSpeed(farmId, windSpeed);
		}
	});

	function makeProgress() {

		if (i > 100) {
			i = (100 * i) / 17280;
			console.log("i vlaue is " + i);
		}

		if (i >= 0 && i <= 50) {
			$(".bar1").css("height", i + "%");
			// javascript method for accesssing an id and adding class
			document.getElementById('bar1').classList
					.add('progress-bar-danger');
			document.getElementById('bar1').classList
					.add('progress-bar-striped');
		} else if (i > 50 && i <= 70) {
			$(".bar1").css("height", i + "%");
			document.getElementById('bar1').classList
					.add('progress-bar-warning');
			document.getElementById('bar1').classList
					.add('progress-bar-striped');
		} else {
			$(".bar1").css("height", i + "%");
			// jquery method for accesssing an id and adding class
			$('#bar1').addClass('progress-bar-striped');
			$('#bar1').addClass('progress-bar-success');
		}
	}
}

function getWindSpeed(farmId, windSpeed) {
	var i = windSpeed * 3;
	i = Math.round(i * 100) / 100;
	function makeProgress() {
		if (i >= 0 && i <= 18) {
			$(".bar2").css("height", i + "%");
			// javascript method for accesssing an id and adding class
			document.getElementById('bar2').classList
					.add('progress-bar-warning');
			document.getElementById('bar2').classList
					.add('progress-bar-striped');
		}
		// Wait for sometime before running this script again
		else if (i > 18 && i <= 72) {
			$(".bar2").css("height", i + "%");
			document.getElementById('bar2').classList
					.add('progress-bar-success');
			document.getElementById('bar2').classList
					.add('progress-bar-striped');
		} else {
			$(".bar2").css("height", i + "%");
			// jquery method for accesssing an id and adding class
			$('#bar2').addClass('progress-bar-striped');
			$('#bar2').addClass('progress-bar-danger');
		}
	}
	makeProgress();
	document.getElementById("value2").innerHTML = windSpeed + " m/s";
}

// Anomalies Function
function getAlertWindfarm(farmId) {
	console.log("Testing Anomalies");

	$
			.ajax({
				url : "getWindfarmAlert/" + farmId,
				type : 'GET',
				success : function(res) {

					console.log("getWindfarmAlert response");
					console.log(res);

					var htmlDiv = '';

					for (var i = 0; i < res.length; i++) {
						var alertCount = res[i].alertCount;
						htmlDiv += '<div class="notification confirm" id="'
									+ res[i].windmillId;
							htmlDiv += '"><div class="data">' + 'Windmill '
									+ res[i].windmillId + ' has '
									+ res[i].alertCount + ' alerts '
									+ '</div></div>';

					}

					var x = document
							.getElementsByClassName("notification-container");

					x[0].innerHTML = htmlDiv;

					$(".confirm")
							.click(
									function() {
										console
												.log("Linking to the respective monitor of the windmill");
										var value = $(this).attr("id");
										console
												.log("the value here is the windmill Id of the of clicked div="
														+ value);
										window.open("monitor/" + value,
												'_blank');
									});

				}

			});
}

function getFarmMetaData(farmId) {
	$
			.ajax({
				url : "getFarmMetaData/" + farmId,
				type : 'GET',
				success : function(res) {
					console.log("getFarmMetaData -->");
					console.log(res);
					
					var farmTitleHtmlDiv = '';
					farmTitleHtmlDiv += res.state + ' Wind Farm';

					document.getElementById("farmTitle").innerHTML = farmTitleHtmlDiv;

					
					var table1HtmlDiv = '';

					table1HtmlDiv += '<table class="table1">';
					table1HtmlDiv += '<tr><th class="t_head1">Turbine</th><td class="t_data1">'+ res.turbine + '</td></tr>';
					table1HtmlDiv += '<tr><th class="t_head1" style="">Identity</th><td class="t_data1">'+ res.identity + '</td></tr>';
					table1HtmlDiv += '<tr><th class="t_head1" style="">Turbine Type</th><td class="t_data1">'+ res.turbineType + '</td></tr>';
					table1HtmlDiv += '<tr><th class="t_head1" style="">Rated Power</th><td class="t_data1">'+ res.ratedPower + '</td></tr>';
					table1HtmlDiv += '<tr><th class="t_head1" style="">Wind farm</th><td class="t_data1">'+ res.windFarm + '</td></tr>';
					table1HtmlDiv += '</table>';

					document.getElementById("container-table1").innerHTML = table1HtmlDiv;

					/*var table2HtmlDiv = '';

					table2HtmlDiv += '<table class="table2">';
					table2HtmlDiv += '<tr><th class="t_head2">Energy Total</th></tr>';
					table2HtmlDiv += '<tr><td class="t_data2"><b id="value">'+ res.energyTotal + '</b> GWh</td></tr>';
					table2HtmlDiv += '<tr><th class="t_head2" style="">Energy Today</th></tr>';
					table2HtmlDiv += '<tr><td class="t_data2"><b id="value">'+ res.energyToday + '</b> kWh</td></tr>';
					table2HtmlDiv += '<tr><th class="t_head2" style="">Operating Hours</th></tr>';
					table2HtmlDiv += '<tr><td class="t_data2"><b id="value">'+ res.operatingHours + '</b> h</td></tr>';
					table2HtmlDiv += '<tr><th class="t_head2" style="">Service this Year</th></tr>';
					table2HtmlDiv += '<tr><td class="t_data2"><b id="value">'+ res.serviceThisYear + '</b> h</td></tr>';
					table2HtmlDiv += '</table>';

					document.getElementById("container-table2").innerHTML = table2HtmlDiv;
*/					
					var cardHtmlDiv = '';
					cardHtmlDiv += '<div class="table2"><div class="card">';
					cardHtmlDiv += '<p class="cardValue" style="padding:0 0 0 0;margin-top:20px;">'+ res.energyTotal + '<b style="color:#506a64; font-size:12px;">GWh</b></p>';
					cardHtmlDiv += '<p class="cardHeader" style="font-weight:550; color:#506a64;">Energy Total</p></div></div>';
					cardHtmlDiv += '<div class="table4"><div class="card">';
					cardHtmlDiv += '<p class="cardValue" style="padding:0 0 0 0; margin-top:20px;">'+ res.operatingHours + '<b style="color:#506a64; font-size:12px;">h</b></p>';
					cardHtmlDiv += '<p class="cardHeader" style="font-weight:550; color:#506a64;">Operating Hours</p></div></div>';
					
					document.getElementById("container-Card1").innerHTML = cardHtmlDiv ;
					
					var cardHtmlDiv2 = '';
					
					cardHtmlDiv2 +='<div class="table2"><div class="card">';
					cardHtmlDiv2 +='<p class="cardValue" style="padding:0 0 0 0; margin-top:20px;">'+ res.energyToday + '<b style="color:#506a64; font-size:12px;">kWh</b></p>';
					cardHtmlDiv2 +='<p class="cardHeader" style="font-weight:550; color:#506a64;" >Energy Today</p></div></div>';
					cardHtmlDiv2 +='<div class="table4"><div class="card">';
					cardHtmlDiv2 +='<h4 class="cardValue" style="padding:0 0 0 0; margin-top:30px;">'+ res.serviceThisYear + '<b style="color:#506a64; font-size:12px;">h</b></p>';
					cardHtmlDiv2 +='<p class="cardHeader" style="font-weight:550; color:#506a64;">Service this Year</p></div></div>';
					
					document.getElementById("container-Card2").innerHTML = cardHtmlDiv2 ;
					
				}
			});
}

function topPerformingWindmills(farmId) {
	//console.log('topPerformingWindmills windmillid is --> ' + farmId);
	
	var datum = [];

	var chart = new CanvasJS.Chart("chartContainer4", {
		backgroundColor : "#FFFFFF",
		colorSet : "colorShades2",
		animationEnabled : true,
		dataPointWidth : 25,
		axisX : {
			// interval: 1,
			intervalType : "Day",
			valueFormatString : "DD MMM YY"
		},
		axisY : {
			// valueFormatString:"$#0bn",
			gridColor : "#B6B1A8",
			tickColor : "#B6B1A8",
			interval : 15
		},
		toolTip : {
			shared : true,
			content : toolTipContent
		},
		data : datum
	});

	var updateTop5Chart = function() {
		
		$.ajax({
			url : "getTopFiveWindmillEnergy/" + farmId,
			type : 'GET',
			success : function(res) {
				console.log('farmId in updateTop5Chart is'+farmId);
		
				console.log("getTopFiveWindmillEnergy -->");
				
				console.log(res); 
				
				for(var i = 0; i < res.length; i++) {
					var dataPoints = [];
					for(var j = 0 ; j < res[i].windmillEnergyItems.length; j++) {
						
						var year = res[i].windmillEnergyItems[j].date.split('-')[0];
						var month = res[i].windmillEnergyItems[j].date.split('-')[1];
						var day = res[i].windmillEnergyItems[j].date.split('-')[2];
						
						dataPoints.push({
							y:Number(res[i].windmillEnergyItems[j].totalEnergy),
							x:new Date(year, month, day)
						})
					}
					datum.push({
						type : "stackedColumn",
						showInLegend : true,
						name : res[i].windmillId,
						dataPoints : dataPoints
					})
				}
				console.log('datum is \n');
				console.log(datum);
				chart.render();
			}
		});
	}
	
	updateTop5Chart();
	
	function toolTipContent(e) {
		var str = "";
		var total = 0;
		var str2, str3;
		for (var i = 0; i < e.entries.length; i++) {
			var str1 = "<span style= \"color:" + e.entries[i].dataSeries.color
					+ "\"> " + e.entries[i].dataSeries.name
					+ "</span>:<strong>" + e.entries[i].dataPoint.y
					+ "</strong>kW<br/>";
			total = e.entries[i].dataPoint.y + total;
			str = str.concat(str1);
		}
		str2 = "<span style = \"color:DodgerBlue;\"><strong>"
				+ (e.entries[0].dataPoint.x).getFullYear()
				+ "</strong></span><br/>";
		total = Math.round(total * 100) / 100;
		str3 = "<span style = \"color:Tomato\">Total:</span><strong>" + total
				+ "</strong>kW<br/>";
		return (str2.concat(str)).concat(str3);
	}
}