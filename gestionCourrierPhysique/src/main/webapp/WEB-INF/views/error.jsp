<!DOCTYPE html>

<%@ page session="false" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<html lang="en">
<jsp:include page="template/staticFiles.jsp"/>
<body>	
<div class="page-container">	
   <div class="left-content">
	   <div class="mother-grid-inner">
            <!--header start here-->
				<jsp:include page="template/header.jsp"/>
<!--heder end here-->
<!-- script-for sticky-nav -->
		<script>
		$(document).ready(function() {
			 var navoffeset=$(".header-main").offset().top;
			 $(window).scroll(function(){
				var scrollpos=$(window).scrollTop(); 
				if(scrollpos >=navoffeset){
					$(".header-main").addClass("fixed");
				}else{
					$(".header-main").removeClass("fixed");
				}
			 });
			 
		});
		</script>
		<!-- /script-for sticky-nav -->
<!--inner block start here-->
<div class="inner-block">
    <div class="error-404">  	
    	<div class="error-page-left">
    	<spring:url value="/images/error.jpg" var="banner404"/>
    		<img src="${banner404}" alt="">
    	</div>
    	<div class="error-right">
	    	<h2>Something happened...</h2>
	    	<h4>An error occurred while running the web service</h4>
	    	<a href="index.html">Go Back</a>
    	</div>
    </div>
</div>
<div class="copyrights">
	 <!-- <p>copyright © 2016 Sharing Technologies. All Rights Reserved <a href="http://www.sharing.com.tn//" target="_blank">W3layouts</a> </p> -->
	 <p>copyright © 2016  <img src="images/sharing.jpg" alt="Sharing Technologies">. All Rights Reserved <a href="http://www.sharing.com.tn//" target="_blank">W3layouts</a> </p>
</div>	
<!--COPY rights end here-->
</div>
</div>
<!--slider menu-->
    <jsp:include page="template/barMenu.jsp"/>
	<div class="clearfix"> </div>
</div>
<!--slide bar menu end here-->
<script>
var toggle = true;
            
$(".sidebar-icon").click(function() {                
  if (toggle)
  {
    $(".page-container").addClass("sidebar-collapsed").removeClass("sidebar-collapsed-back");
    $("#menu span").css({"position":"absolute"});
  }
  else
  {
    $(".page-container").removeClass("sidebar-collapsed").addClass("sidebar-collapsed-back");
    setTimeout(function() {
      $("#menu span").css({"position":"relative"});
    }, 400);
  }               
                toggle = !toggle;
            });
</script>
<!-- mother grid end here-->
</body>
</html>       
                      
						
