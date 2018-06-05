<!DOCTYPE html>

<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="datatables" uri="http://github.com/dandelion/datatables"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<html lang="en">
<!-- css files -->
<jsp:include page="../newTemplate/staticFiles.jsp" />
<!-- /css files -->

<script type="text/javascript">
window.onload = function() {
	functionBar();
	functionPie();
	}
 
	function functionBar(){
var dps = [[], []];
var chart = new CanvasJS.Chart("chartContainer", {
	exportEnabled: true,
	animationEnabled: true,
	title: {
		text: "Courriers arrivée/départ"
	},
	subtitles: [{
		text: "Cliquez sur Légende pour masquer ou afficher les séries de données"
	}],
	axisX: {
		title: "Unité bancaire"
	},
	axisY: {
		title: "courriers"
	},
	toolTip: {
		shared: true
	},
	legend: {
		cursor: "pointer",
		verticalAlign: "top",
		itemclick: toggleDataSeriesBar
	},
	data: [{
		type: "column",
		name: "arivée",
		showInLegend: true,
		dataPoints: dps[0]
	},
	{
		type: "column",
		name: "départ",
		showInLegend: true,
		dataPoints: dps[1]
	}]
});
 
var yValue;
var label;
 
<c:forEach items="${dataPointsList}" var="dataPoints" varStatus="loop">	
	<c:forEach items="${dataPoints}" var="dataPoint">
		yValue = parseFloat("${dataPoint.y}");
		label = "${dataPoint.label}";
		dps[parseInt("${loop.index}")].push({
			label : label,
			y : yValue,
		});		
	</c:forEach>	
</c:forEach> 
 
chart.render();
	}
function toggleDataSeriesBar(e) {
	if (typeof (e.dataSeries.visible) === "undefined" || e.dataSeries.visible) {
		e.dataSeries.visible = false;
	} else {
		e.dataSeries.visible = true;
	}
	e.chart.render();
}
 


function functionPie(){
	
var dps = [[]];
var chartPie = new CanvasJS.Chart("chartContainerTrans", {
	animationEnabled: true,
	exportEnabled: true,	
	title:{
		text: "Bordereaux par transporteur"
	},
	data: [{
		type: "pie",    
	    yValueFormatString: "#,###\"%\"",
	    showInLegend: true,
	    indexLabel: "{y}", 
	    indexLabelPlacement: "inside",
		dataPoints: dps[0]
	}]
});
 
var yValuePie;
var namePie;
 
<c:forEach items="${dataPointsListTrans}" var="dataPointsTrans" varStatus="loop">
	<c:forEach items="${dataPointsTrans}" var="dataPointTrans">
		yValuePie = parseFloat("${dataPointTrans.y}");
		namePie = "${dataPointTrans.name}";
		dps[parseInt("${loop.index}")].push({
			name : namePie,
			y : yValuePie
		});
	</c:forEach>
</c:forEach>
 
chartPie.render();
 
}
</script>

<body class="nav-md footer_fixed">
	<div class="container body">
		<div class="main_container">
			<!-- top Menu -->
			<jsp:include page="../newTemplate/navigator.jsp" />
			<!-- /top Menu -->
			
			<!-- top navigation -->
			<jsp:include page="../newTemplate/header.jsp" />
			<!-- /top navigation -->

			<!-- page content -->
			<div class="right_col" role="main">
				<div class="">
					<div class="page-title">
						<div class="row">
							<div class="col-xs-12 invoice-header">
								<h2>
									<i class="fa fa-user-o"></i>
									Statistiques
								</h2>
							</div> 
							<!-- /.col -->
						</div>
					</div>
					<div class="clearfix"></div>
					<div class="row">
<!-- 						<div class="col-md-12"> -->
							<div class="col-md-12">
							<div class="x_panel">
								<div class="row">
									<!-- accepted payments column -->
									<div class="col-md-8 col-sm-8 col-xs-12">
                					<div class="x_panel">
                  					<div class="x_title">
                    
                    
                    <div class="clearfix"></div>
                  </div>
                  <div class="x_content">

                    <div id="chartContainer" style="height: 370px; width: 100%;"></div>

                  </div>
                </div>
              </div>
              
               <div class="col-md-4 col-sm-4 col-xs-12">
                <div class="x_panel">
                  <div class="x_title">
                    
                    
                    <div class="clearfix"></div>
                  </div>
                  <div class="x_content">

                    <div id="chartContainerTrans" style="height: 370px; width: 100%;"></div>

                  </div>
                </div>
              </div>
									
									<!-- /.col -->
								</div>
					</div>
					  </div>
				   </div>	
				</div>
								
				<!-- /Put your main JSP here -->
			</div>
			<!-- /page content -->
			<!-- footer content -->
			<jsp:include page="../newTemplate/footer.jsp" />
			<!-- /footer content -->
		</div>
	</div>
	<!-- Js files -->
	<jsp:include page="../newTemplate/jsFiles.jsp" />
	<!-- /Js files -->
</body>
</html>
