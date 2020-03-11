// Display date in the first column..
var wmId="";

function displayTime() {
	var x = new Date()
	// date part ///
	var month = x.getMonth() + 1;
	var day = x.getDate();
	var year = x.getFullYear();
	if (month < 10) {
		month = '0' + month;
	}
	if (day < 10) {
		day = '0' + day;
	}
	var x3 = month + '-' + day + '-' + year;

	// time part //
	var hour = x.getHours();
	var minute = x.getMinutes();
	var second = x.getSeconds();
	if (hour < 10) {
		hour = '0' + hour;
	}
	if (minute < 10) {
		minute = '0' + minute;
	}
	if (second < 10) {
		second = '0' + second;
	}

	var x3 = x3 + ' ' + hour + ':' + minute + ':' + second
	document.getElementById('time').innerHTML = x3;
	displayDate();
}

function displayDate() {
	var refresh = 1000; // Refresh rate in milli seconds
	mytime = setTimeout('displayTime()', refresh)
}

// Power gauge in the second column
function powerGauge(id) {
	console.log("CALLING get PowerGauge");
	
			$.ajax({
				url : "getPower/"+id,
				type : 'GET',
				success : function(res) {
					console.log("POWER res-----------------------");
					console.log(res);

					 const num = Math.round(res.power_efficiency);
					// const num = res[0].efficiency;
					const degrees = Math.round((num / 100) * 180);
					const root = document.querySelector(":root");
					let title = document.querySelector(".reading");
					let currentNumber = title.innerText;

					setInterval(function update() {
						if (currentNumber < num) {
							currentNumber++;
							title.innerText = currentNumber;
						} else if (currentNumber > num) {
							currentNumber--;
							title.innerText = currentNumber;// .
						}
					}, 3);
					root.style.setProperty("--rotation", `${degrees}deg`);

					var htmlDiv = '';
					htmlDiv += '<table class="table2"><tr><td class="t_data2">Average Power:</td><td class="t_data2">';
					htmlDiv += res.average_power +' kW';
					htmlDiv += '</td></tr><tr><td class="t_data2">Total Energy:</td><td class="t_data2">';
					htmlDiv += res.total_energy +' MWh';
					htmlDiv += '</td></tr></table>';

					var x = document.getElementsByClassName("powerTable");
					x[0].innerHTML = htmlDiv;

				}
			});
};


//Update Status of Windmill
function updateWindMill(status){
	$.ajax({
		url : "updateWindmillStatus/"+wmId+"/"+status,
		type : 'GET',
		success : function(res) {
			console.log("Wind Mill Status updated----------------------");
			console.log(res);
		}});
}


// Actual v/s Expected Graph:line Curve

function powerTrend(id) {
	console.log('powerTrend' + windTrend);
	var actual = [];
	var expected = [];

	var chart = new CanvasJS.Chart("chartContainer1", {
		backgroundColor : "#FFFAFA",
		animationEnabled : true,
		legend : {
			horizontalAlign : "center", // "center" , "right"
			verticalAlign : "top", // "top" , "bottom"
		},

		axisY : {
			title : "Power(KW)",
			maximum: 1500,
			titleFontColor : "#848482",
			labelFontColor : "#848482",
			gridColor : "#D1D0CE",
			interval : 300,
			lineColor : "#D1D0CE",
			tickColor : "D1D0CE"
		},

		axisX : {
			title : "Time",
			intervalType : "hour",
			labelFontSize : 10,
			titleFontSize : 12,
			valueFormatString : "HH:mm:ss",
			margin : 5
		},

		data : [ {
			type : "spline",
			name : "Expected",
			showInLegend : true,
			dataPoints : expected,
			lineColor : "#4863A0",
			markerColor : "#4863A0",
			legendMarkerColor : "#4863A0",
			color : "#4863A0",
			toolTipContent: "{x} <span style='\"'color: {color};'\"'><br/>{name}:<strong>{y} kW</strong>"
		}, {
			type : "spline",
			name : "Actual",
			lineColor : "#BFA466",
			markerColor : "#BFA466",
			legendMarkerColor : "#BFA466",
			color : "#BFA466",
			showInLegend : true,
			toolTipContent: "{x} <span style='\"'color: {color};'\"'><br/>{name}:<strong>{y} kW</strong>",
			dataPoints : actual
		} ]
	});

	var updateChart = function() {
		
		$.ajax({
			url : "getPowerTrend/"+id,
			type : 'GET',
			success : function(res) {
				console.log("PowerTrend res----------------------");
				console.log(res);

		for (var i = 0; i < res.length; i++) {
			var dateVar = res[i].created_timestamp.split(" ");
			var dateArr = dateVar[0].split("-");
			var timeArr = dateVar[1].split(":");
			expected.push({
				x : new Date(Date.UTC(dateArr[0], dateArr[1] - 1,
						dateArr[2], timeArr[0], timeArr[1],
						timeArr[2].split(".")[0])),
				y : Number(res[i].expected_power)
			});
			actual.push({
				x : new Date(Date.UTC(dateArr[0], dateArr[1] - 1,
						dateArr[2], timeArr[0], timeArr[1],
						timeArr[2].split(".")[0])),
				y : Number(res[i].actual_power)
			});
		}
		
		if (expected.length > 30 )
      	{
      		expected.splice(0,30);
      		actual.splice(0,30);
      	}
		chart.render();
	}

		});
	};
	updateChart();
setInterval(function(){updateChart()}, 300000);

}



// Wind Speed Trend: Spline chart
function windTrend(id) {
	console.log('wind speed Trend' + windTrend);
	
	var temp = [];
	var chart = new CanvasJS.Chart("chartContainer2", {
		backgroundColor : "#FFFAFA",
		animationEnabled : true,
		legend : {
			horizontalAlign : "center", // "center" , "right"
			verticalAlign : "top", // "top" , "bottom"
		},

		axisY : {
			title : "Wind Speed(m/s)",
			titleFontColor : "#848482",
			labelFontColor : "#848482",
			maximum : 40,
			stripLines : [ {
			
				startValue : 6,
				endValue : 6.3,
				color : "#728FCE",
				label:"Cut-in",
				labelPlacement:"outside",
				labelBackgroundColor:"",
				labelFontColor:"#728FCE"
				
			}, {
				startValue : 24,
				endValue : 24.3,
				color : "#E55451",
				label:"Cut-out",
				labelPlacement:"outside",
				labelBackgroundColor:"",
				labelFontColor:"#E55451"
			}],
			gridColor : "#D1D0CE",
			interval : 10,
			lineColor : "#D1D0CE",
			tickColor : "D1D0CE"
		},
		axisX : {
			title : "Time",
			intervalType : "hour",
			labelFontSize : 10,
			titleFontSize : 12,
			valueFormatString : "HH:mm:ss",
			margin : 5
		},

		data : [ {
			type : "spline",
			name : "Wind Speed",
			lineColor : "#6A287E",
			markerColor : "#6A287E",
			legendMarkerColor : "#6A287E",
			color : "#6A287E",
			showInLegend : true,
			dataPoints : temp,
			toolTipContent: "{x} <span style='\"'color: {color};'\"'><br/>{name}:<strong>{y} m/s</strong>",
		} ]});
	
	var updateChart = function(id) {
			$.ajax({
				url : "getWindSpeedTrend/"+ id,
				type : 'GET',
				success : function(res) {
					console.log("WindSpeedtrend-------------");
					console.log(res);
		for (var i = 0; i <res.length; i++) {
			var dateVar = res[i].created_timestamp.split(" ");
			var dateArr = dateVar[0].split("-");
			var timeArr = dateVar[1].split(":");
			temp.push({
				x : new Date(Date.UTC(dateArr[0], dateArr[1] - 1,
						dateArr[2], timeArr[0], timeArr[1],
						timeArr[2])),
				y : Number(res[i].wind_speed)
			});
		}
		if (temp.length > 30 )
      	{
      		temp.splice(0,30);
      	}
		chart.render();
				}});
	};
	updateChart(id);
	setInterval(function(){updateChart(id)}, 300000);
}

// Production chart:bar Graph
function prodChart(id) {
	console.log('prod chart Trend' + windTrend);
	
	$.ajax({
		url : "getEnergyTrend/"+id,
		type : 'GET',
		success : function(res) {
			console.log("Energy--------------------------- : ");
			console.log(res);

			var expected = [];
			var actual = [];
			
			var chart = new CanvasJS.Chart("chartContainer", {
				animationEnabled : true,
				dataPointMaxWidth : 10,
				backgroundColor : "#FFFAFA",
				axisY : {
					title : "Energy (kwh)",
					titleFontColor : "#848482",
					labelFontColor : "#848482",
					gridColor : "#D1D0CE",
					lineColor : "#D1D0CE",
					tickColor : "D1D0CE",
					
					valueFormatString : "#0.#0",
					maximum: 25.0,
					minimum:0.0,
					interval: 5.0
				},
				axisX : {
					title : "Date",
					labelFontSize : 10,
					titleFontSize : 12,
					valueFormatString : "DD MMM ",
					titleFontColor : "#848482",
					labelFontColor : "#848482",
					gridColor : "#D1D0CE",
					margin : 5,
					lineColor : "#D1D0CE",
					tickColor : "D1D0CE",
					labelAngle: -20,
					interval : 1,
					intervalType :"day"

				},
				toolTip : {
					shared : true
				},
				legend : {
					cursor : "pointer",
					itemclick : toggleDataSeries,
					maxWidth : 200,
					verticalAlign : "top",
					horizontalAlign : "center",
				},
				data : [ {
					type : "column",
					color : "#4863A0",
					fontColor : "#4863A0",
					name : "Expected Energy Generated",
					showInLegend : true,
					toolTipContent: "<span style='\"'color: {color};'\"'>{name}:<strong>{y} MWh</strong>",        
					dataPoints : expected
				}, {
					type : "column",
					color : "#BFA466",
					fontColor : "#BFA466",
					name : "Actual Energy Generated",
					showInLegend : true,
					toolTipContent: "<span style='\"'color: {color};'\"'>{name}:<strong>{y} MWh</strong>",        

					dataPoints : actual
				} ]
			});

			var updateChart = function() {
				for (var i = 0; i <res.length; i++) {
					var dateArr = res[i].date.split("-");
					expected.push({
						x : new Date(Date.UTC(dateArr[0], dateArr[1] - 1,
								dateArr[2])),
						y : Number(res[i].expected_energy)
					});
					actual.push({
						x : new Date(Date.UTC(dateArr[0], dateArr[1] - 1,
								dateArr[2])),
						y : Number(res[i].actual_energy)
					});
				}
				chart.render();
			}
			updateChart();
		}
	});
	function toggleDataSeries(e) {
		if (typeof (e.dataSeries.visible) === "undefined"
				|| e.dataSeries.visible) {
			e.dataSeries.visible = false;
		} else {
			e.dataSeries.visible = true;
		}
		chart.render();
	}
}

// Temperature Bars...
function tempBars(id) {
	console.log("CALLING getTemperatures");
			$.ajax({
				url : "getTemperatures/"+id,
				type : 'GET',
				success : function(res) {
					console.log("Temperature res-------------");
					console.log(res);
					var i = Math.round(res.ambient_temperature);

					function makeProgress() {
						new_i = Math.round(i / 3.5);
						if (i >= 0 && i <= 100) {
							$(".bar1").css("height", new_i + "%");
							// javascript method for accesssing an id and adding
							// class
							document.getElementById('bar1').classList
									.add('progress-bar-success');
							document.getElementById('bar1').classList
									.add('progress-bar-striped');
						}
						// Wait for sometime before running this script again
						else if (i > 100 && i <= 120) {
							$(".bar1").css("height", new_i + "%");
							document.getElementById('bar1').classList
									.add('progress-bar-warning');
							document.getElementById('bar1').classList
									.add('progress-bar-striped');
						} else {
							$(".bar1").css("height", new_i + "%");
							// jquery method for accesssing an id and adding
							// class
							$('#bar1').addClass('progress-bar-striped');
							$('#bar1').addClass('progress-bar-danger');
						}
					}
					document.getElementById("value1").innerHTML = i + "\xB0F";
					makeProgress();

					var j = Math.round(res.gearbox_temperature);

					function makeProgress2() {
						new_j = Math.round(j / 3.5);
						if (j >= 0 && j <= 250) {
							$(".bar2").css("height", new_j + "%");
							// javascript method for accesssing an id and adding
							// class
							document.getElementById('bar2').classList
									.add('progress-bar-success');
							document.getElementById('bar2').classList
									.add('progress-bar-striped');
						}
						// Wait for sometime before running this script again
						else if (j > 250 && j <= 300) {
							$(".bar2").css("height", new_j + "%");
							document.getElementById('bar2').classList
									.add('progress-bar-warning');
							document.getElementById('bar2').classList
									.add('progress-bar-striped');
						} else {
							$(".bar2").css("height", new_j + "%");
							// jquery method for accesssing an id and adding
							// class
							$('#bar2').addClass('progress-bar-striped');
							$('#bar2').addClass('progress-bar-danger');
						}
					}
					makeProgress2();
					document.getElementById("value2").innerHTML = j + "\xB0F";

					var k = Math.round(res.rotor_temperature);

					function makeProgress3() {
						new_k = Math.round(k / 3.5);
						if (k >= 0 && k <= 250) {
							$(".bar3").css("height", new_k + "%");
							// javascript method for accesssing an id and adding
							// class
							document.getElementById('bar3').classList
									.add('progress-bar-success');
							document.getElementById('bar3').classList
									.add('progress-bar-striped');
						}
						// Wait for sometime before running this script again
						else if (k > 250 && k <= 300) {
							$(".bar3").css("height", new_k + "%");
							document.getElementById('bar3').classList
									.add('progress-bar-warning');
							document.getElementById('bar3').classList
									.add('progress-bar-striped');
						} else {
							$(".bar3").css("height", new_k + "%");
							// jquery method for accesssing an id and adding
							// class
							$('#bar3').addClass('progress-bar-striped');
							$('#bar3').addClass('progress-bar-danger');
						}
					}
					makeProgress3();
					document.getElementById("value3").innerHTML = k + "\xB0F";

					var l = Math.round(res.bearing_temperature);

					function makeProgress4() {
						new_l = Math.round(l / 3.5);
						if (l >= 0 && l <= 250) {
							$(".bar4").css("height", new_l + "%");
							// javascript method for accesssing an id and adding
							// class
							document.getElementById('bar4').classList
									.add('progress-bar-success');
							document.getElementById('bar4').classList
									.add('progress-bar-striped');
						}
						// Wait for sometime before running this script again
						else if (l > 250 && l <= 300) {
							$(".bar4").css("height", new_l + "%");
							document.getElementById('bar4').classList
									.add('progress-bar-warning');
							document.getElementById('bar4').classList
									.add('progress-bar-striped');
						} else {
							$(".bar4").css("height", new_l + "%");
							// jquery method for accesssing an id and adding
							// class
							$('#bar4').addClass('progress-bar-striped');
							$('#bar4').addClass('progress-bar-danger');
						}
					}
					makeProgress4();
					document.getElementById("value4").innerHTML = l + "\xB0F";

				}
			});

}

// Temperature END

// Getting Alerts: Second large column
function getAlert(id) {
	console.log("Testing Alerts");
	
	wmId=id;
	$
			.ajax({
				url : "getAlerts/"+id,
				type : 'GET',
				success : function(res1) {
					console.log("ALERTS : ");
					
					var res = JSON.parse(res1.newRes);
					console.log("New response"+JSON.stringify(res));
					
					windmill_id = res1.WindMillId;
					
					var htmlDiv = '';
					for (var i = 0; i < res.length; i++) {
						
						var key = res[i].alert_key;
						
						htmlDiv += '<div class="notification confirm" id="'
								+ key;
						htmlDiv += '"><div class="icon">';
						
						if (res[i].alert_field =='AMBIENT_TEMPERATURE'||res[i].alert_field == 'BEARING_TEMPERATURE'||res[i].alert_field == 'GEAR_BOX_TEMPERATURE'||res[i].alert_field == 'ROTOR_TEMPERATURE')
						{htmlDiv += '<i class="fas fa-thermometer-half" style="color:white; font-size:23px; top:40%; position:relative;"></i>';}
						else if (res[i].alert_field =='POWER_DEVIATION')
						{htmlDiv += '<i class="fas fa-bolt" style="color:white; font-size:23px; top:40%; position:relative;"></i>';}
						
						else{
						htmlDiv += '<i class="fas fa-exclamation-triangle" style="color:white; font-size:23px; top:40%; position:relative;"></i>';
						}
						
						htmlDiv += '</div><div class="data"><h6>';
						htmlDiv += res[i].alert_description
						htmlDiv += '</h6>	<p class="timestamp">'
								+ res[i].alert_created + '</p>';
						htmlDiv += '</div><div class="time">';
						if (res[i].alert_level == 'WARNING')
							htmlDiv += '<i class="fa fa-flag flag f_color" aria-hidden="true" style="color:orange">';
						else
							htmlDiv += '<i class="fa fa-flag flag f_color" aria-hidden="true" style="color:#C11B17">';
						htmlDiv += '</i></div></div>';
						
						
					}
						
					var modalDiv = '';

					modalDiv += '<div id="myModal" class="modal fade"> ';
					modalDiv += '<div class="modal-dialog modal-confirm"> ';
					modalDiv += '<div class="modal-content"><div class="modal-header">';
					modalDiv += '<div class="ic-box"><i class="fas fa-check-circle"></i></div>';
					modalDiv += '<h4 class="modal-title">Alert Resolved?</h4><button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;';
					modalDiv += '</button> </div><div class="modal-footer"> ';
					modalDiv += '<button type="button" class="btn btn-info" data-dismiss="modal" id="modal-btn-si" >Yes</button> ';
					modalDiv += '<button type="button" class="btn btn-danger" id="modal-btn-no">No</button></div></div></div> </div> ';

					var x = document
							.getElementsByClassName("notification-container");

					x[0].innerHTML = htmlDiv + modalDiv;

					/*
					 * for (i=0; i<5; i++) { var x=
					 * document.getElementById("notice").id="alert"+[i+1];
					 * console.log(x); }
					 */

					var modalConfirm = function(callback) {

						$(".confirm")
								.on(
										"click",
										function() {
											$("#myModal").modal('show');
											var value = $(this).attr("id");
											console
													.log("the value here is the id of clicked div="
															+ value);
											alertid = value;
														});

						$("#modal-btn-si").on("click", function() {
							callback(true);
							$("#myModal").modal('hide');
						});

						$("#modal-btn-no").on("click", function() {
							callback(false);
							$("#myModal").modal('hide');
						});
					};

					modalConfirm(function(confirm) {
						if (confirm) {
							// If yes button is pressed
							console.log("Confirmed");
							updateAlerts(wmId);
							} 
						else {
							// If no button is pressed
							console.log("Not Confirmed");
						}
					});
				}
			});
}

function reply_click(clicked_id) {
	alert(clicked_id);
}

function updateAlerts(id) {
	
	$.ajax({
		url : "updateAlerts/"+id+"/"+alertid,
		type : 'GET',
		success : function(res) {
		console.log(res);
		console.log("alert clicked= " +alertid + " "+id );
		getAlert(id);
	
	}
	});
}






function degToRad(angle) {
	return ((angle * Math.PI) / 180);
}
function radToDeg(angle) {
	return ((angle * 180) / Math.PI);
}

function drawLine(options, line) {
	options.ctx.beginPath();
	options.ctx.globalAlpha = line.alpha;
	options.ctx.lineWidth = line.lineWidth;
	options.ctx.fillStyle = line.fillStyle;
	options.ctx.strokeStyle = line.fillStyle;
	options.ctx.moveTo(line.from.X, line.from.Y);

	options.ctx.lineTo(line.to.X, line.to.Y);
	options.ctx.stroke();
}
function createLine(fromX, fromY, toX, toY, fillStyle, lineWidth, alpha) {
	return {
		from : {
			X : fromX,
			Y : fromY
		},
		to : {
			X : toX,
			Y : toY
		},
		fillStyle : fillStyle,
		lineWidth : lineWidth,
		alpha : alpha
	};
}

function drawOuterMetallicArc(options) {
	options.ctx.beginPath();
	options.ctx.fillStyle = "rgb(127,127,127)";
	options.ctx.arc(options.center.X, options.center.Y, options.radius, 0,
			Math.PI, true);
	options.ctx.fill();
}

function applyDefaultContextSettings(options) {
	options.ctx.lineWidth = 2;
	options.ctx.globalAlpha = 0.5;
	options.ctx.strokeStyle = "rgb(255, 255, 255)";
	options.ctx.fillStyle = 'rgb(255,255,255)';
}

function drawSmallTickMarks(options) {

	var tickvalue = options.levelRadius - 8, iTick = 0, gaugeOptions = options.gaugeOptions, iTickRad = 0, onArchX, onArchY, innerTickX, innerTickY, fromX, fromY, line, toX, toY;

	applyDefaultContextSettings(options);

	for (iTick = 18; iTick < 180; iTick += 18) {

		iTickRad = degToRad(iTick);

		onArchX = gaugeOptions.radius - (Math.cos(iTickRad) * tickvalue);
		onArchY = gaugeOptions.radius - (Math.sin(iTickRad) * tickvalue);
		innerTickX = gaugeOptions.radius
				- (Math.cos(iTickRad) * gaugeOptions.radius);
		innerTickY = gaugeOptions.radius
				- (Math.sin(iTickRad) * gaugeOptions.radius);

		fromX = (options.center.X - gaugeOptions.radius) + onArchX;
		fromY = (gaugeOptions.center.Y - gaugeOptions.radius) + onArchY;
		toX = (options.center.X - gaugeOptions.radius) + innerTickX;
		toY = (gaugeOptions.center.Y - gaugeOptions.radius) + innerTickY;

		line = createLine(fromX, fromY, toX, toY, "rgb(127,127,127)", 1, 0.2);

		drawLine(options, line);
	}
}

function drawTextMarkers(options) {
	var innerTickX = 0, innerTickY = 0, iTick = 0, gaugeOptions = options.gaugeOptions, iTickToPrint = 0;

	applyDefaultContextSettings(options);
	options.ctx.font = 'italic 10px sans-serif';
	options.ctx.textBaseline = 'top';
	options.ctx.fillStyle = "#0000A0";
	options.ctx.beginPath();
	options.ctx.fillText("0", 19, 114);
	options.ctx.fillText("10", 83, 20);
	options.ctx.fillText("20", 205, 20);
	options.ctx.fillText("30", 275, 114);
	options.ctx.stroke();
}

function drawSpeedometerPart(options, alphaValue, strokeStyle, startPos) {
	options.ctx.beginPath();
	options.ctx.globalAlpha = alphaValue;
	options.ctx.lineWidth = 5;
	options.ctx.strokeStyle = strokeStyle;
	options.ctx.arc(options.center.X, options.center.Y, options.levelRadius,
			Math.PI + (Math.PI / 360 * startPos), 0 - (Math.PI / 360 * 10),
			false);
	options.ctx.stroke();
}

function drawSpeedometerColourArc(options) {
	var startOfBlue = 8, endOfBlue = 76, endOfGreen = 280;
	drawSpeedometerPart(options, 1.0, "#AFDCEC", startOfBlue);
	drawSpeedometerPart(options, 0.9, "#7FE817", endOfBlue);
	drawSpeedometerPart(options, 0.9, "#DC381F", endOfGreen);
}

function convertSpeedToAngle(options) {
	var iSpeed = (options.speed / 10), iSpeedAsAngle = ((iSpeed * 20) + 10) % 180;
	if (iSpeedAsAngle > 180) {
		iSpeedAsAngle = iSpeedAsAngle - 180;
	} else if (iSpeedAsAngle < 0) {
		iSpeedAsAngle = iSpeedAsAngle + 180;
	}
	return iSpeedAsAngle;
}

function drawNeedle(options) {
	if (options.speed < 10) {
		var needleX = 12;
		var needleY = 10;
	}
	if (options.speed > 20) {
		var needleX = -12;
		var needleY = 10;
	}
	var iSpeedAsAngle = convertSpeedToAngle(options), iSpeedAsAngleRad = degToRad(iSpeedAsAngle), gaugeOptions = options.gaugeOptions, innerTickX = gaugeOptions.radius
			- (Math.cos(iSpeedAsAngleRad) * 20), innerTickY = gaugeOptions.radius
			- (Math.sin(iSpeedAsAngleRad) * 20), fromX = (options.center.X - gaugeOptions.radius)
			+ innerTickX, fromY = (gaugeOptions.center.Y - gaugeOptions.radius)
			+ innerTickY, endNeedleX = gaugeOptions.radius
			- (Math.cos(iSpeedAsAngleRad) * gaugeOptions.radius), endNeedleY = gaugeOptions.radius
			- (Math.sin(iSpeedAsAngleRad) * gaugeOptions.radius), toX = (options.center.X - gaugeOptions.radius)
			+ endNeedleX, toY = (gaugeOptions.center.Y - gaugeOptions.radius)
			+ endNeedleY, line = createLine(fromX, fromY, toX, toY,
			"#25383C", 2, 0.5);
	drawLine(options, line);
}

function buildOptionsAsJSON(canvas, iSpeed) {
	var centerX = 150, centerY = 140, radius = 125, outerRadius = 180;

	return {
		ctx : canvas.getContext('2d'),
		speed : iSpeed,
		center : {
			X : centerX,
			Y : centerY
		},
		levelRadius : radius - 8,
		gaugeOptions : {
			center : {
				X : centerX,
				Y : centerY
			},
			radius : radius
		},
		radius : outerRadius
	};
}

function clearCanvas(options) {
	options.ctx.clearRect(0, 0, 800, 600);
	applyDefaultContextSettings(options);
}

// Main entry
function windSpeed(id) {
	/*
	 * Main entry point for drawing the speedometer If canvas is not support
	 * alert the user.
	 */
	
	console.log('wind speed Trend' + windTrend);
	function updateWindSpeed(id){
			$.ajax({
				url : "getWindSpeed/"+ id,
				type : 'GET',
				success : function(res) {
					console.log("WindSpeed------------------- : ");
					console.log(res);
					iCurrentSpeed = Math.round(res.wind_speed);

					console.log("Get WindSpeed:" + iCurrentSpeed);
					var speedWind = iCurrentSpeed * 80 / 30;
					var canvas = document.getElementById('tutorial'), options = null;
					if (canvas !== null && canvas.getContext) {
						options = buildOptionsAsJSON(canvas, speedWind);
						clearCanvas(options);
						drawSmallTickMarks(options);
						drawTextMarkers(options);
						drawSpeedometerColourArc(options);
						drawNeedle(options);
					} else {
						alert("Canvas not supported by your browser!");
					}

					var htmlDiv = '';
					htmlDiv += '<table class="table3"><tr><td class="t_data3">Current Wind Speed:</td><td class="t_data3" id="Wind_Speed">';
					htmlDiv += '0'+' m/s</td></tr><tr><td class="t_data3">Average Wind Speed:</td><td class="t_data3">';
					htmlDiv += Math.round(res.average_wind_speed);
					htmlDiv += ' m/s</td></tr><tr><td class="t_data3">Availability(of Optimal Wind Speed):</td><td class="t_data3">';
					htmlDiv += res.availability;
					htmlDiv += '</td></tr></table>';

					var x = document.getElementsByClassName("windTable");
					x[0].innerHTML = htmlDiv;
					document.getElementById("Wind_Speed").innerHTML = iCurrentSpeed
							+ " m/s";
				}
			});
}

updateWindSpeed(id);
setInterval(function(){updateWindSpeed(id)}, 300000);

}

function getSpeed() {
	var speedRandom = Math.floor(Math.random() * 30) + 1;
	return speedRandom;
}
