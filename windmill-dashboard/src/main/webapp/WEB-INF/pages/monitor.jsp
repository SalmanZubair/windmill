<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html lang="en">
<head>
<title>Dashboard</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">

<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet"
	href='https://use.fontawesome.com/releases/v5.7.0/css/all.css'
	integrity='sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ'
	crossorigin='anonymous'>
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Roboto:100,300,400,500">

<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/Monitor.css">


<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/Monitor.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/canvasjs-commercial-2.3.2/canvasjs.min.js"></script>
<script src="http://d3js.org/d3.v3.min.js" language="JavaScript"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
<script src="https://code.highcharts.com/highcharts.js"></script>
<script src="https://code.highcharts.com/highcharts-more.js"></script>
<script src="https://code.highcharts.com/modules/exporting.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
<script>

window.onload = function() {
	var id = "${windmillId}";
	displayTime(id);
	powerGauge(id);
	powerTrend(id);
	windSpeed(id);
	windTrend(id);
	prodChart(id);
	tempBars(id);
	var alertid = "";
	var windmill_id = "";
	var alertKey = "";
	getAlert(id);
	console.log("##############################")
	
	console.log("SALMAN   ::  " +id);
};

/*  document.addEventListener('DOMContentLoaded', function () {
  var checkbox = document.querySelector('input[type="checkbox"]');

  checkbox.addEventListener('change', function () {
    if (checkbox.checked) {
      updateWindMill("ON");
      console.log('Checked');
    } else {
    	updateWindMill("OFF");
      console.log('Not checked');
    }
  }); 
}); */
</script>
</head>
<body>


	<!--Topmost Header-->
<nav class="navbar navbar-default top-fixed">
		<!-- Prolim Image -->
		<a class="navbar-brand" href="#"> <img src="${pageContext.request.contextPath}/resources/images/windmillLogo.png" alt="prolim" class="d-inline-block align-top" id="prolim">
		</a>
		<!-- Navigation Bar Right -->
		    <ul class="nav navbar-nav navbar-right" id="nav_links" style="margin-right:12px; list-style:none;">
      	  <li style="padding:0; margin-top:5px; margin-right:30px;"><a style="a:link{color:#edf0f1;}" href="/redirectFarmMap">Farm Map</a></li>
     <li><a href="/logout"><button style="border-radius:50px; background-color:#90ad90; border:none; padding:5px 16px; transition: all 0.3s ease 0;">Log Out</button> </a></li>
    	</ul>
	</nav>

	<!-- Container -->
	<div class="container-fluid" style="width: 100%">

		<!-- Main row -->
		<div class="row" style="padding: 0; margin: 0 0 0 0;">

			<!-- First column of main row -->
			<div class="col-sm-10 col-md-10 col-lg-10"
				style="padding: 0; margin: 0 0 0 0;">

				<!-- First row of main row first column -->
				<div class="row" style="margin: 0 0 0 0; padding: 0;">

					<!-- First Column of first row first column -->
					<div class="col-sm-3 col-md-3 col-lg-3" style="padding: 0 0 0 0;">
					<!-- WindMill Image -->
						<img id="windmill"
							src="${pageContext.request.contextPath}/resources/images/windmill.gif">

					</div>
					<!-- Second Column of first row first column -->
					<div class="col-sm-3 col-md-3 col-lg-3"
						style="padding: 0 0 0 0;">
						<h5 class="sub-title">Overview</h5>
						<!-- Table I -->
						<div
							style="overflow-x: auto; width: 100%; height: 88%; position: relative;">
							
							<table class="table1">
			
								<tr>
									<td class="t_data1" style="">Wind Mill Id&nbsp;</td>
									<td class="t_data1"> ${windmillId}</td>
								</tr>
								<tr>
									<td class="t_data1">Location</td>
									<td class="t_data1">Snyder, Texas</td>
								</tr>
								<tr>
									<td class="t_data1">Turbine</td>
									<td class="t_data1">Active</td>
								</tr>
								
								<tr>
									<td class="t_data1">Converter</td>
									<td class="t_data1">Running</b></td>
								</tr>
								<tr>
									<td class="t_data1">Local Time</td>
									<td class="t_data1"><span id='time'></span></td>
								</tr>

							</table>
						</div>
					</div>

					<!-- Third column of First row-->
					<div class="col-sm-3 col-md-3 col-lg-3"
						style="margin:; padding: 0 0 0 0;">
						<h5 class="sub-title">Performance Overview (Previous Day)</h5>
						<!-- Meter Gauge -->
						<div class="power-graph-container" style="height: 45%; padding:4%">
							<div class="gaugeContainer">
								<h1 class="sign">
									<i class="fa fa-bolt" style="margin: 1px 24px; font-size: 20px"></i><br>Efficiency
								</h1>
								<div class="gauge"></div>
							</div>
							<h2 class="reading">0</h2>
						</div>

						<!--Table II-->
						<div class="powerTable" style="overflow-x: auto; position: relative; height: 42%; margin:0 0 0 0; padding: 0 0 0 0;">
							
						</div>
					</div>

					<!-- Fourth column of First row-->
					<div class="col-sm-3 col-md-3 col-lg-3"
						style="margin:; padding: 0 0 0 0">
						<!-- PV Curve -->
						<h5 class="sub-title">Actual v/s Expected Power</h5>
						<div id="chartContainer1"
							style="height: 87%; width: 98%; margin: -3px 0 0 0;"></div>

					</div>
				</div>

				<!--Second row of main row first column-->
				<div class="row" style="margin: 0 0 0 0; padding: 0;">

					<!-- First column of Second row-->
					<div class="col-sm-3 col-md-3 col-lg-3" style="padding: 0 0 0 0;">
						<h5 class="sub-title">Wind Speed</h5>
						<!-- Speedometer -->
						<div class="speedometer-container"
							style="height: 43%; margin: 0 0 0 0;">
							<canvas id="tutorial" style="width: 100%; height: 100%;">
								Canvas not available.
								</canvas>
						</div>
						<!-- Table III -->
						<div class="windTable" style="overflow-x: auto; position: relative; height: 45%; margin: 0 0 0 0; padding: 0 0 0 0;">
						</div>
					</div>

					<!-- Second column of Second row-->
					<div class="col-sm-3 col-md-3 col-lg-3"
						style="margin:; padding: 0 0 0 0;">
						<h5 class="sub-title">Wind Speed Trend</h5>
						<!-- Actual v/s Expected Energy Production Graph-->
						<div id="chartContainer2"
							style="width: 98%; height: 87%; margin: -3px 0 0 0;"></div>
					</div>

					<!-- Third column of Second row-->
					<div class="col-sm-3 col-md-3 col-lg-3"
						style="margin:; padding: 0 0 0 0">
						<h5 class="sub-title">Energy Production</h5>
						<!-- Production Graph-->
						<div id="chartContainer"
							style="height: 87%; width: 98%; margin: -3px 0 0 0;"></div>
					</div>

					<!-- Fourth column of Second row-->
					<div class="col-sm-3 col-md-3 col-lg-3" style="padding: 0 0 0 0;">
						<h5 class="sub-title">Component Temperatures</h5>
						<!-- --Temperature Bar I------- -->
						<div class="tempBar-Container">
							<div class="progress progress-bar-vertical">
								<div class="progress-bar bar1 active" id="bar1"
									role="progressbar"></div>
								<h6 id="value"
									style="margin:; left: 5%; top: 86%; position: absolute;">Ambient
								</h6>
								<h6 id="value1"
									style="margin:; left: 7%; top: 91%; position: absolute; color: #6D7B8D;">
								</h6>
							</div>
						</div>
						<!-- --Temperature Bar II------ -->
						<div class="tempBar-Container">
							<div class="progress progress-bar-vertical">
								<div class="progress-bar bar2 active" id="bar2"
									role="progressbar"></div>
								<h6 id="value"
									style="margin:; left: 28%; top: 86%; position: absolute;">Gear Box
								</h6>
								<h6 id="value2"
									style="margin:; left: 32%; top: 91%; position: absolute;">
								</h6>
							</div>
						</div>
						<!-- --Temperature Bar III------ -->
						<div class="tempBar-Container">
							<div class="progress progress-bar-vertical">
								<div class="progress-bar bar3 active" id="bar3"
									role="progressbar"></div>
								<h6 id="value"
									style="margin:; left: 57%; top: 86%; position: absolute;">Rotor
								</h6>
								<h6 id="value3"
									style="margin:; left: 57%; top: 91%; position: absolute; color: #6D7B8D;">
								</h6>
							</div>
						</div>
						<!-- --Temperature Bar IV------- -->
						<div class="tempBar-Container1"
							style="height: 88%; width: 25%; float: left;">
							<div class="progress progress-bar-vertical">
								<div class="progress-bar bar4 active" id="bar4"
									role="progressbar"></div>
								<h6 id="value"
									style="margin:; left: 81%; top: 86%; position: absolute;">Stator
								</h6>
								<h6 id="value4"
									style="margin:; left: 82%; top: 91%; position: absolute; color: #6D7B8D;">
								</h6>
							</div>
						</div>
					</div>


				</div>
			</div>

			<!--Second column of main row -->
			<div class="col-sm-2 col-md-2 col-lg-2" style="padding: 0 0 0 0;">
				<!-- Alerts Section-- -->

				<h3 class="alert-title">ALERTS</h3>
				<!-- Notifications container -->
				<div class="notification-container"
					style="overflow: auto; margin: 0 0 0 0; height: 89%;">

					<!-- Single Notification -->

<!-- 					<div class="notification confirm" id="alert1">
						<div class="icon">
							<i class="fas fa-exclamation-triangle"
								style="color: white; font-size: 23px; top: 40%; position: relative;">
							</i>
						</div>
						<div class="data">
							<h6>Speed crossed the Threshold value</h6>
							<p class="timestamp">08/04/2019 17:49:11</p>
						</div>
						<div class="time">
							<i class="fa fa-flag flag f_color0 aria-hidden="true"> </i>
						</div>
					</div>

					<div id="myModal" class="modal fade">
						<div class="modal-dialog modal-confirm">
							<div class="modal-content">
								<div class="modal-header">
									<div class="ic-box">
										<i class="fas fa-check-circle"></i>
									</div>
									<h4 class="modal-title">Alert Resolved?</h4>
									<button type="button" class="close" data-dismiss="modal"
										aria-hidden="true">&times;</button>
								</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-info" data-dismiss="modal"
										id="modal-btn-si">Yes</button>
									<button type="button" class="btn btn-danger" id="modal-btn-no">No</button>
								</div>
							</div>
						</div>
					</div>


 -->
				</div>

			</div>
		</div>
	</div>



</body>

</html>
