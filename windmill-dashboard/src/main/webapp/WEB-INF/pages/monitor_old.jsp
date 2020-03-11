<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html lang="en">
<head>
<title>Dashboard</title>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
		
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
		<link rel="stylesheet" href='https://use.fontawesome.com/releases/v5.7.0/css/all.css' integrity='sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ' crossorigin='anonymous'>
		<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:100,300,400,500">
		

		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/Monitor.css">


		
		<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/Monitor.js"></script>		
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
		<script src="https://canvasjs.com/assets/script/canvasjs.min.js"></script>
		<script src="http://d3js.org/d3.v3.min.js" language="JavaScript"></script> 
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
		<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
		<script src="https://code.highcharts.com/highcharts.js"></script>
		<script src="https://code.highcharts.com/highcharts-more.js"></script>
		<script src="https://code.highcharts.com/modules/exporting.js"></script>


</head>
 <body>
 
		
	<!--Topmost Header-->
		<nav class="navbar navbar-default top-fixed">
	<!-- Prolim Image -->
			<a class="navbar-brand" href="https://www.prolim.com/">
			<img src="${pageContext.request.contextPath}/resources/images/prolim.png" alt="Prolim" class="d-inline-block align-top" id="prolim">
			</a>
	<!-- Navigation Bar Right -->
			<ul class="nav navbar-right " style="margin: 12px 0 0 0;">
	<!-- On/Off Button -->
				<li><label class="switch">
					<input class="switch-input" type="checkbox" />
					<span class="switch-label" data-on="On" data-off="Off"></span> 
					<span class="switch-handle"></span> 
					</label>
			   </li>
		   </ul>
		</nav>
	
	<!-- Container -->
		<div class="container-fluid" style="width:100%">
	
	<!-- Main row -->
			<div class="row" style=" padding:0; margin:0 0 0 0; ">
		
	<!-- First column of main row -->
				<div class="col-sm-10 col-md-10 col-lg-10" style=" padding:0; margin:0 0 0 0;">
		
	<!-- First row of main row first column -->
					<div class="row" style=" margin: 0 0 0 0; padding:0;"> 

	<!-- First Column of first row first column -->
						<div class="col-sm-6 col-md-6 col-lg-6" style="">
	<!-- Status Bar -->
							<div class="page-header" style="margin:2px;">
								<h5 style="margin: 15px 0 2px 2px; font-weight:600;">Status</h5>
							</div>
	<!-- WindMill Image -->
							<div class="part1" style="margin:0 0 0 0; padding:0">
								<img class="windmill" src="${pageContext.request.contextPath}/resources/images/windmill.gif"> 
							</div>
	<!-- Table I -->
							<div class="part2" style=" padding:2%">
								<div  style="overflow-x:auto;" >
									<table class="table1">
										<tr>
											<th class="t_head1">Item</td>
											<th class="t_head1">Status</td>
										</tr>
										<tr>
											<td class="t_data1">Turbine</td>
											<td class="t_data1">Active</td>
										</tr>
										<tr>
											<td class="t_data1">Rotor</td>
											<td class="t_data1">Pitch Control</td>
										</tr>
										<tr>
											<td class="t_data1">Nacelle</td>
											<td class="t_data1">On wind</td>
										</tr>
										<tr>
											<td class="t_data1">Converter</td>
											<td class="t_data1">Running</td>
										</tr>
										<tr>
											<td class="t_data1">Local Time</td>
											<td class="t_data1"><span id='time' ></span></td>
										</tr>
									</table>
								</div>
							</div>
					</div> 
				 
	<!-- Second column of First row-->
					    <div class="col-sm-3 col-md-3 col-lg-3" style="margin:; padding: 0 0 0 0;">
							<h5 class="sub-title">Overview</h5>
	<!-- Meter Gauge -->
							<div class="power-graph-container" style="height: 44%">
							<div class="gaugeContainer">
								<h1 class="sign"><i class="fa fa-bolt" style="margin:1px 14px; font-size:20px"></i><br>Power</h1>
								<div class="gauge">
								</div>
							</div>
							<h2 class="reading"></h2>	
							</div>
				
	<!--Table II-->
							<div style="overflow-x:auto; position:relative; height:42%;">
								<table class="table2">
									<tr>
										<td class="t_data2">Average Power:</td>
										<td class="t_data2">230.6 kW</td>
									</tr>
									<tr>
										<td class="t_data2">Total Energy:</td>
										<td class="t_data2">4.0 GWh</td>
									</tr>
								</table>
							 </div>
						</div>
				
	<!-- Third column of First row-->
						<div class="col-sm-3 col-md-3 col-lg-3" style="margin:; padding: 0 0 0 0">
	<!-- PV Curve -->
							<h5 class="sub-title">Actual v/s Expected</h5>
							<div id="chartContainer1" style="height:88%; width: 98%; margin: -3px 0 0 0;">
							</div>
				
						</div>
					</div>
			
	<!--Second row of main row first column-->
					<div class="row" style="margin:0 0 0 0; padding:0; " >
				
	<!-- First column of Second row-->
							<div class="col-sm-3 col-md-3 col-lg-3" style="padding:0 0 0 0;">
								<h5 class="sub-title">Wind Speed</h5>
		<!-- Speedometer -->
							<div class="speedometer-container" style="height:45%; margin:0 0 0 0;">
								<canvas id="tutorial" style=" width:100%; height:100%;" >
								Canvas not available.
								</canvas>
								</div>
		<!-- Table III -->
								<div style="overflow-x:auto; position:relative; height:43%; margin:0 0 0 0;padding:0 0 0 0; ">
									<table class="table3">
										<tr>
											<td class="t_data3">Wind Speed:</td>
											<td class="t_data3" id="Wind_Speed">0 m/s</td>
										</tr>
										
										<tr>
											<td class="t_data3">Total Energy:</td>
											<td class="t_data3">4.0 GWh</td>
										</tr>
									</table>
								</div>
							</div>
							
	<!-- Second column of Second row-->
							<div class="col-sm-3 col-md-3 col-lg-3" style="margin:; padding:0 0 0 0;">
								<h5 class="sub-title">Wind Speed Trend</h5>
		<!-- Actual v/s Expected Energy Production Graph-->
								<div id="chartContainer2" style=" width:99%; height:88%; margin: -3px 0 0 0;">
								</div>
							</div>
							
	<!-- Third column of Second row-->
							<div class="col-sm-3 col-md-3 col-lg-3" style="margin:; padding:0 0 0 0">
								<h5 class="sub-title">Production</h5>
		<!-- Production Graph-->
								<div id="chartContainer" style="height:88%; width:98%; margin: -3px 0 0 0;">
								</div>
							</div>
							
	<!-- Fourth column of Second row-->
							<div class="col-sm-3 col-md-3 col-lg-3" style="padding:0 0 0 0;">
								<h5 class="sub-title" >Temperatures</h5>
		<!-- --Temperature Bar I------- -->
								<div class="tempBar-Container" >
									<div class="progress progress-bar-vertical">
										<div class="progress-bar bar1 active" id="bar1" role="progressbar">
										</div>
										<h6 id="value" style="margin:; left:4%; top:86%; position: absolute;">Ambient
										</h6>
										<h6 id="value1" style="margin:; left:8%; top:91%; position: absolute; color:#6D7B8D;">
										</h6>
									</div>
								</div>
		<!-- --Temperature Bar II------ -->
								<div class="tempBar-Container" >
									<div class="progress progress-bar-vertical">
										<div class="progress-bar bar2 active" id="bar2" role="progressbar">
										</div>
										<h6 id="value" style="margin:; left:30%; top:86%; position: absolute;">Gear Box
										</h6>
										<h6 id="value2" style="margin:; left: 33%; top:91%; position: absolute;"> </h6>
									</div>
								</div>
		<!-- --Temperature Bar III------ -->
								<div class="tempBar-Container" >
									<div class="progress progress-bar-vertical">
										<div class="progress-bar bar3 active" id="bar3" role="progressbar">
										</div>
										<h6 id="value" style="margin:; left:57%; top:86%; position: absolute;">Rotor
										</h6>
										<h6 id="value3" style="margin:; left: 58%; top:91%; position: absolute; color:#6D7B8D;"> </h6>
									</div>
								</div>
		<!-- --Temperature Bar IV------- -->
								<div class="tempBar-Container1" style="height: 88%; width: 25%; float:left;">
									<div class="progress progress-bar-vertical">
										<div class="progress-bar bar4 active" id="bar4" role="progressbar">
										</div>
										<h6 id="value" style="margin:; left:81%; top:86%; position: absolute;">Stator
										</h6>
										<h6 id="value4" style="margin:; left:82%; top:91%; position: absolute; color:#6D7B8D;">
										</h6>
									</div>
								</div>
							</div>
											
			
					</div>
				</div>
			
	<!--Second column of main row -->
				<a href="http://google.com">
  
<div class="col-sm-2 col-md-2 col-lg-2" style=" padding: 0 0 0 0;">
	<!-- Alerts Section-- -->
					
						<h3 class="alert-title">ALERTS</h3>
	<!-- Notifications container -->
						<div class="notification-container" style=" overflow:auto; margin: 0 0 0 0; height: 92%;">
	
	<!-- Single Notification -->
						
					</div>
				
				</div>
				</a>
			</div>
		</div>
	
 </body>
</html>
		