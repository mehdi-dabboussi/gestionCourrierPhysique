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
									Information courrier
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
														<th >objet :</th>
														<td>${createdCourrier.objetCourrier}</td>
													</tr>
													<tr>
														<th >Détails :</th>
														<td>${createdCourrier.detailsCourrier}</td>
													</tr>
													<tr>
														<th >Date de création :</th>
														<td>${createdCourrier.dateCreationCourrier}</td>
													</tr>
													<tr>
														<th >Nature :</th>
														<td>${createdCourrier.nature.libelleNature}</td>
													</tr>
													<tr>
														<th >Langue :</th>
														<td>${createdCourrier.langue.libelleLangue}</td>
													</tr>
													
													<tr>
														<th >Emetteur :</th>
														<td>${createdCourrier.emetteur.nom}</td>
													</tr>
													
													
												</tbody>
											</table>
										</div>
									</div>
									
									<!-- /.col -->
							</div>
					</div>
					  </div>
				   </div>	
				</div>
				<div class="col-md-12 col-sm-12 col-xs-12">
					<div class="x_panel">
						<div class="x_title">
							<h2>
								Les Transferts
							</h2>
							   
							<div class="clearfix"></div>
						</div>
						<div class="row">
							<table id="datatable-keytable" class="table table-striped table-bordered">
								  <thead>
								    <tr>
								      <th>Emetteur</th>
								      <th>Destinataire</th>
								      <th>Date de transfert </th>
								      <th>Estimation</th>
								    </tr>
								  </thead>
								<c:forEach var="transfertToShow" items="${transferts}">
									  	<tr>
									      <td >${transfertToShow.emetteurUnite.nom}</td>
									      <td>
									      <c:if test="${transfertToShow.destinataireType == 'unite'}">${transfertToShow.destinataireUnite.nom}</c:if>
							    	      <c:if test="${transfertToShow.destinataireType == 'contact'}">${transfertToShow.destinataireContact.nom}</c:if> 
										</td>
									      <td >${transfertToShow.dateTransfert}</td>
									      <td>${transfertToShow.estimation}</td>
									    </tr>
								  </c:forEach>
							</table>
							
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
