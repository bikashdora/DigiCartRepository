<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page session="false"%>
<html>
<h1>Catalog of Products</h1>
<head>
<meta charset="utf-8">
<title>jQuery UI Menu - Default functionality</title>
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">
<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<script src="//code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
<link rel="stylesheet" href="/resources/demos/style.css">
<script>
  $(function() {
    $( "#menu" ).menu();
  });
  </script>
<style>
.ui-menu {
	width: 150px;
}
</style>
</head>
<body>

	<ul id="menu">
		<li><a href="#">Electronics</a>
			<ul>
				<!-- <li class="ui-state-disabled"><a href="#">Ada</a></li> -->
				<li><a href="#" id="Mobiles">Mobiles</a></li>
				<li><a href="#">TV</a></li>
				<li><a href="#">Computers</a></li>
				<li><a href="#">Cameras</a></li>
			</ul></li>
		<li><a href="#">Men</a>
			<ul>
				<!-- <li class="ui-state-disabled"><a href="#">Ada</a></li> -->
				<li><a href="#">Jeans</a></li>
				<li><a href="#">Shirts</a></li>
				<li><a href="#">Watches</a></li>
				<li><a href="#">FootWears</a></li>
			</ul></li>
		<li><a href="#">Women</a>
			<ul>
				<!-- <li class="ui-state-disabled"><a href="#">Ada</a></li> -->
				<li><a href="#">Dresses</a></li>
				<li><a href="#">Sunglasses</a></li>
				<li><a href="#">Watches</a></li>
				<li><a href="#">Sarees</a></li>
			</ul>
	</ul>


	<div id="images"></div>

	<script>
	
	
	/* $("#yourLink").click(function() {
  var mobilesAPI = "http://localhost:8080/CatalogService/service/catalogService/getDefaultSkusProductsForCategory/Mobiles";
  $.getJSON(mobilesAPI)
    .done(function( data ) {
      $.each( data.items, function( i, item ) {
    	   '<li><img src= "' + this.skuMedia.primary.url + '"></li>'.appendTo( "#images" );
        
      });
    });
})(); */
	
	
$("#Mobiles").click(function () {
	  var jsonURL = "http://localhost:8080/CatalogService/getDefaultSkusProductsForCategory/Mobiles";
	  $.getJSON(jsonURL, function (json) 
	  {
	    var imgList= "";
	   // json=$.parseJSON(json);
	    console.log ("Data0Id: " + json[0].skuMedia.primary.url);
	    $.each(json, function (i,item) {
	    	//imgList += '<li><img src= "' + item.skuMedia.primary.url + '"></li>';
	    	//var imageLink = item.skuMedia.primary.url;
	    	//imgList += '<li><img src="<c:url value='item.skuMedia.primary.url'/>" alt=.../></li>';
	    	
	    	imgList += '<li><img src= "' + '${pageContext.request.contextPath}'+item.skuMedia.primary.url + ' "width="120" height="120" border="0" "></li>';
	    	console.log(imgList);
	    });

	   $('#images').append(imgList);
	  });
	});	
		
</script>













</body>
</html>