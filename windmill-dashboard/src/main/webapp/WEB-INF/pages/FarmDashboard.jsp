<!DOCTYPE html>
<html lang="en">
<meta charset="utf-8">
<head>
<title>Farm Dashboard</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">


<!-- Bootstrap core CSS-->
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

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/js/canvasjs-commercial-2.3.2/canvasjs.min.js"></script>

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>

<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/farmCss.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/map.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/mediaquery.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/map.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/farmJs.js"></script>

<script async defer
	src="https://maps.googleapis.com/maps/api/js?key=AIzaSyD_Ssn6MIgQigUX5qfTz2jVM90-koFQlRg&callback=initMap"></script>

<script type="text/javascript">window.onload = function(){
			var farmId ='${windFarmId}';
			CanvasJS.addColorSet("greenShades",
			                [//colorSet Array

			                "#3B9C9C",
			                "#B7CEEC",
			                "#87AFC7",
			                "#BCC6CC",
			                "#90EE90"                
			                ]);
			CanvasJS.addColorSet("greenShades",
	                [//colorSet Array

	                "#3B9C9C",
	                "#B7CEEC",
	                "#87AFC7",
	                "#BCC6CC",
	                "#90EE90"                
	                ]);
		CanvasJS.addColorSet("colorShades",
	                [//colorSet Array

	                "#3B9C9C",
	                "#90d3d2",
	                "#87AFC7",
	                "#c0c2ce"                
	                ]);
		CanvasJS.addColorSet("colorShades1",
	                [//colorSet Array

	                "#c8b399",
	                "#b5c7b8",
	                "#cfe8cb",
	                "#c0c2ce"                
	                ]);
		CanvasJS.addColorSet("colorShades2",
	                [//colorSet Array

	                "#c8b399",
	                "#b5c7b8",
	                "#cfe8cb",
	                "#d5d5be"                
	                ]);
		setWindFarmId(farmId);
			energyBar(farmId);
			getAlertWindfarm(farmId);
			getWindFarmEnergy(farmId);
			getFarmMetaData(farmId);
			topPerformingWindmills(farmId);
			console.log(farmId);
			}
		</script>
</head>
<!-- <body style="background-color:#DCDCDC;"> -->
<body>
	<!--Topmost Header-->
	<nav class="navbar navbar-default top-fixed">
		<!-- Prolim Image -->
		<a class="navbar-brand" href="www.prolim.com"> <img
			src="${pageContext.request.contextPath}/resources/images/windmillLogo.png"
			alt="prolim" class="d-inline-block align-top" id="prolim">
		</a>
		<h3 id="farmTitle" style="margin: 15px 0 10px 46%; padding: 0 0 0 0;"></h3>
		<!-- Navigation Bar Right -->
		<ul class="nav navbar-nav navbar-right" id="nav_links"
			style="margin-right: 10px; list-style: none;">
			<li style="padding: 0; margin-top: 5px; margin-right: 28px;"><a
				style="a: link{color:#edf0f1;" href="/redirectFarmMap">Farm Map</a></li>
			<li><a href="/logout"><button
						style="border-radius: 50px; background-color: #90ad90; border: none; padding: 5px 16px; transition: all 0.3s ease 0;">Log
						Out</button> </a></li>
		</ul>
	</nav>


	<!-- Container -->
	<div class="container-fluid" style="width: 100%">

		<!-- Main row -->
		<div class="row" style="padding: 0; margin: 0 0 0 0;">

			<!-- First row of main row -->
			<div class="row" style="margin: 0 0 0 0; padding: 0;">

				<!-- First Column of first row -->
				<div class="col-sm-2 col-md-2 col-lg-2"
					style="padding: 0 0 0 0; margin: 12px 0 0 0;">
					<!-- Table I -->
					<div id="container-table1"
						style="overflow-x: auto; width: 100%; height: 100%; position: relative;">

					</div>
				</div>

				<!-- Second column of First row-->
				<div class="col-sm-6 col-md-6 col-lg-6"
					style="margin: 12px 0 0 14px; padding: 0 0 0 0">

					<!-- Production Graph-->
					<div id="chartContainer2"
						style="width: 99%; height: 93%; margin: 15px 0 0 2px;"></div>
				</div>



				<!-- Third Column of first row-->
				<div class="col-sm-2 col-md-2 col-lg-2"
					style="padding: 0 0 0 0; margin: 12px 0 0 14px;">
					<!-- --Temperature Bar I------- -->
					<div class="tempBar-Container1">
						<h4 class="sub-title"
							style="margin-left: 28px; font-size: 1.5rem; font-weight: 550;">Energy</h4>
						<div class="progress progress-bar-vertical">
							<div class="progress-bar bar1 active" id="bar1"
								role="progressbar"></div>

							<h6 id="value1" style=""></h6>
						</div>
					</div>
					<!-- --Temperature Bar II------ -->
					<div class="tempBar-Container2">
						<h4 class="sub-title"
							style="margin-left: 7px; font-size: 1.5rem; font-weight: 550;">Wind
							Speed</h4>
						<div class="progress progress-bar-vertical">
							<div class="progress-bar bar2 active" id="bar2"
								role="progressbar"></div>

							<h6 id="value2"></h6>
						</div>
					</div>
				</div>

					<!-- Fourth Column of first row -->
					<div class="col-sm-1 col-md-1 col-lg-1" style="padding: 0 0 0 0; margin:12px 0 0 14px;">
						<!-- Table I -->
						<div id="container-Card1" style="overflow-x: auto; width: 100%; height: 100%; position: relative; padding:0 0 0 0; ">
						
						</div>
					</div>
						  
						  <!-- Fifth Column of first row -->
						  <div class="col-sm-1 col-md-1 col-lg-1" style="padding: 0 0 0 0; margin:12px 0 0 2px;">
						  <!-- Table I -->
						<div id="container-Card2" style="overflow-x: auto; width: 100%; height: 100%; position: relative; ">
						
						
					</div>
				</div>



			</div>

			<!-- Second row of main row -->
			<div class="row" style="margin: 0 0 0 0; padding: 0;">

				<!-- First Column of second row -->
				<div class="col-sm-6 col-md-6 col-lg-6"
					style="margin: 12px 0 0 0; padding: 0 0 0 0">

					<!-- Production Graph-->
					<div id="chartContainer4"
						style="width: 99%; height: 93%; margin: 15px 0 0 2px;"></div>
				</div>



				<!-- Second Column of second row -->
				<div class="col-sm-3 col-md-3 col-lg-3"
					style="padding: 0 0 0 0; margin: 12px 0 0 14px;">
					<!-- Alerts -->

					<h3 class="alert-title">Alerts</h3>
					<!-- Notifications container -->
					<div class="notification-container"></div>
				</div>


				<!-- Third column of First row-->
				<div class="col-sm-3 col-md-3 col-lg-3"
					style="margin: 12px 0 0 14px; padding: 0 0 0 0;">
					<div id="map"></div>
				</div>
			</div>
		</div>
	</div>





</body>
</html>
