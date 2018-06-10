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
									Bordereau ${ville}
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
									<div class="col-xs-12">
<!-- 									<div class="col-xs-6"> -->
										<div class="table-responsive">
											<table class="table table-striped" style="margin-bottom:5px;">
												<tbody>
													<tr>
														<th >ville : </th>
														<td>${bordereau.ville}</td>
													</tr>
													<tr>
														<th >Transporteur :</th>
														<td>${bordereau.transporteurExterne.nomTransporteurExterne}</td>
													</tr>
													<tr>
														<th >Coursier :</th>
														<td>${bordereau.coursierExterne.nomCoursierExterne} ${bordereau.coursierExterne.prenomCoursierExterne}</td>
													</tr>
													<tr>
														<th >Date de la création :</th>
														<td>${bordereau.dateCreation}</td>
													</tr>
												</tbody>
											</table>
										</div>
									</div>
							<div class="clearfix"></div>
							<div class="x_title">
							<h2>
								Les courriers 
							</h2>
							  
							<div class="clearfix"></div>
						</div>
								<table id="datatable-keytable" class="table table-striped table-bordered">
								  <thead>
								    <tr>
								      <th>Id courrier </th>
								      <th>Objet</th> 
								      <th>Destinataire</th>
								      <th>Transfert vers</th>
								      <th>Date de transfert </th>
								      <th>Estimation</th>
								    </tr>
								  </thead>
								<c:forEach var="transfertToShow" items="${bordereau.transferts}">
									  	<tr>
									  	
									      <td >${transfertToShow.courrier.idCourrier}</td>
									      <td >${transfertToShow.courrier.objetCourrier}</td>
									      <td >${transfertToShow.courrier.destinataire.nom}</td>
									      <td>
									      <c:if test="${transfertToShow.destinataireType == 'unite'}">${transfertToShow.destinataireUnite.nom}</c:if>
							    	      <c:if test="${transfertToShow.destinataireType == 'contact'}">${transfertToShow.destinataireContact.nom}</c:if> 
										</td>
									      <td >${transfertToShow.dateTransfert}</td>
									      <td>${transfertToShow.estimation} Heure</td>
					                	 
									    </tr>
								  </c:forEach>
							</table>
									
									<div  style="margin-right: 5%;">
						                <%--  <spring:url value="/admin/{userId}/delete" var="deleteUrl">
						                         <spring:param name="userId" value="${createdUser.idUser}"/>
						                  </spring:url>
						                  <a href="${fn:escapeXml(deleteUrl)}" class="btn btn-danger btn-xs pull-right">Supprimer</a> --%>

						                 <spring:url value="bordereau-{idBordereau}-print" var="printUrl">
						                      	<spring:param name="idBordereau" value="${bordereau.idBordereau}"/>
						                 </spring:url>
					                	<a href="${fn:escapeXml(printUrl)}" class="btn btn-info btn-xs pull-right">Imprimer </a>
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
	<script>
      $(document).ready(function() {
        var handleDataTableButtons = function() {
          if ($("#datatable-buttons").length) {
            $("#datatable-buttons").DataTable({
              dom: "Bfrtip",
              buttons: [
                {
                  extend: "copy",
                  className: "btn-sm"
                },
                {
                  extend: "csv",
                  className: "btn-sm"
                },
                {
                  extend: "excel",
                  className: "btn-sm"
                },
                {
                  extend: "pdfHtml5",
                  className: "btn-sm"
                },
                {
                  extend: "print",
                  className: "btn-sm"
                },
              ],
              responsive: true
            });
          }
        };

        TableManageButtons = function() {
          "use strict";
          return {
            init: function() {
              handleDataTableButtons();
            }
          };
        }();

        $('#datatable').dataTable();

        $('#datatable-keytable').DataTable({
          keys: true
        });

        $('#datatable-responsive').DataTable();

        $('#datatable-scroller').DataTable({
          ajax: "js/datatables/json/scroller-demo.json",
          deferRender: true,
          scrollY: 380,
          scrollCollapse: true,
          scroller: true
        });

        $('#datatable-fixed-header').DataTable({
          fixedHeader: true
        });

        var $datatable = $('#datatable-checkbox');

        $datatable.dataTable({
          'order': [[ 1, 'asc' ]],
          'columnDefs': [
            { orderable: false, targets: [0] }
          ]
        });
        $datatable.on('draw.dt', function() {
          $('input').iCheck({
            checkboxClass: 'icheckbox_flat-green'
          });
        });

        TableManageButtons.init();
      });
    </script>
	
	 <!-- Datatables -->
	<!-- /Js files -->
</body>
</html>
