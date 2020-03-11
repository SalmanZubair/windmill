<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>WindMill Remote Monitoring</title>

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
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>

<!-- Custom styles for this template-->
<link rel="stylesheet" href="resources/css/FarmMap.css">


<script type="text/javascript" src="resources/js/WindFarmMap.js"></script>

<script async defer
	src="https://maps.googleapis.com/maps/api/js?key=AIzaSyD_Ssn6MIgQigUX5qfTz2jVM90-koFQlRg&callback=initMap"></script>

</head>
<body>

<nav class="navbar navbar-default top-fixed">
		<!-- Prolim Image -->
		<a class="navbar-brand" href="#"> <img src="${pageContext.request.contextPath}/resources/images/windmillLogo.png" alt="prolim" class="d-inline-block align-top" id="prolim">
		</a>
		<!-- Navigation Bar Right -->
		    <ul class="nav navbar-nav navbar-right" id="nav_links" style="margin-right:12px; list-style:none;">
      	 <li><a href="/logout"><button style="border-radius:50px; background-color:#90ad90; border:none; padding:5px 16px; transition: all 0.3s ease 0;">Log Out</button> </a></li>
    	</ul>
	</nav>
  
  
  
    
    <div id="wrapper">
     
      
         
          <script>
		
	</script>
        <div id="map"></div>
        
    </div>
    
  </body>
</html>
