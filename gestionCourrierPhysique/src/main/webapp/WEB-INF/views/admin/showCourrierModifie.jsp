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
									Information courrier avant la modification 
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
														<th >id : </th>
														<td>${courrierModifie.idCourrier}</td>
													</tr>
													<tr>
														<th >etat du courrier :</th>
														<td>${courrierModifie.etatCourrier}</td>
													</tr>
													<tr>
														<th >objet :</th>
														<td>${courrierModifie.objetCourrier}</td>
													</tr>
													<tr>
														<th >Détails :</th>
														<td>${courrierModifie.detailsCourrier}</td>
													</tr>
													<tr>
														<th >Date de création :</th>
														<td>${courrierModifie.dateCreationCourrier}</td>
													</tr>
													<tr>
														<th >Nature :</th>
														<td>${courrierModifie.nature.libelleNature}</td>
													</tr>
													<tr>
														<th >Langue :</th>
														<td>${courrierModifie.langue.libelleLangue}</td>
													</tr>
													
													<tr>
														<th >Emetteur :</th>
														<td>
														<c:choose>
									      				<c:when test="${courrierModifie.emetteurType == 'sousContact'}">
									      				${courrierModifie.emetteur.nom} - ${courrierModifie.emetteur.contactExterne.nom}
									      				</c:when>
									     				 <c:otherwise>
									      				${courrierModifie.emetteur.nom} 
									      			</c:otherwise>
									     			 </c:choose>
														</td>
													</tr>
													
													<tr>
														<th >Destinataire :</th>
														<td>
														<c:choose>
									      				<c:when test="${courrierModifie.destinataireType == 'sousContact'}">
									      				${courrierModifie.destinataire.nom} - ${courrierModifie.destinataire.contactExterne.nom}
									      				</c:when>
									     				 <c:otherwise>
									      				${courrierModifie.destinataire.nom} 
									      			</c:otherwise>
									     			 </c:choose>
														</td>
													</tr>
													
													<tr>
														<th >Acteur :</th>
														<td>${courrierModifie.actor.nom}</td>
													</tr>
													
													<tr>
														<th >Date de la modification :</th>
														<td>${courrierModifie.dateModification}</td>
													</tr>
													
												</tbody>
											</table>
										</div>
									</div>
									<div  style="margin-right: 5%;">
						                
						            </div>
									<!-- /.col -->
							</div>
							
							
							<!-- old -->
							
							
							
					</div>
					  </div>
				   </div>	
				</div>
						
						
						<div class="clearfix"></div>
			
					<div class="page-title">
						<div class="row">
							<div class="col-xs-12 invoice-header">
								<h2>
									<i class="fa fa-user-o"></i>
									Information courrier apres la modification 
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
									<div class="col-xs-12">
<!-- 									<div class="col-xs-6"> -->
										<div class="table-responsive">
											<table class="table table-striped" style="margin-bottom:5px;">
												<tbody>
													<tr>
														<th >id : </th>
														<td>${newCourrier.idCourrier}</td>
													</tr>
													<tr>
														<th >etat du courrier :</th>
														<td>${newCourrier.etatCourrier}</td>
													</tr>
													<tr>
														<th >objet :</th>
														<td>${newCourrier.objetCourrier}</td>
													</tr>
													<tr>
														<th >Détails :</th>
														<td>${newCourrier.detailsCourrier}</td>
													</tr>
													<tr>
														<th >Date de création :</th>
														<td>${newCourrier.dateCreationCourrier}</td>
													</tr>
													<tr>
														<th >Nature :</th>
														<td>${newCourrier.nature.libelleNature}</td>
													</tr>
													<tr>
														<th >Langue :</th>
														<td>${newCourrier.langue.libelleLangue}</td>
													</tr>
													
													<tr>
														<th >Emetteur :</th>
														<td>
														<c:choose>
									      				<c:when test="${newCourrier.emetteurType == 'sousContact'}">
									      				${newCourrier.emetteur.nom} - ${newCourrier.emetteur.contactExterne.nom}
									      				</c:when>
									     				 <c:otherwise>
									      				${newCourrier.emetteur.nom} 
									      			</c:otherwise>
									     			 </c:choose>
														</td>
													</tr>
													
													<tr>
														<th >Destinataire :</th>
														<td>
														<c:choose>
									      				<c:when test="${newCourrier.destinataireType == 'sousContact'}">
									      				${newCourrier.destinataire.nom} - ${newCourrier.destinataire.contactExterne.nom}
									      				</c:when>
									     				 <c:otherwise>
									      				${newCourrier.destinataire.nom} 
									      			</c:otherwise>
									     			 </c:choose>
														</td>
													</tr>
													
												
													
												</tbody>
											</table>
										</div>
									</div>
									<div  style="margin-right: 5%;">
						                
						            </div>
									<!-- /.col -->
							</div>
							
							
							<!-- old -->
							
							
							
					</div>
					  </div>
				   </div>	
				<!-- /Put your main JSP here -->
			</div>
			
			
				
						
				<!-- /Put your main JSP here -->
			
			
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
