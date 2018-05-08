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
														<th >id : </th>
														<td>${createdCourrier.idCourrier}</td>
													</tr>
													<tr>
														<th >etat du courrier :</th>
														<td>${createdCourrier.etatCourrier}</td>
													</tr>
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
														<td>${emetteur}</td>
													</tr>
													
													<tr>
														<th >Destinataire :</th>
														<td>${destinataire}</td>
													</tr>
													
												</tbody>
											</table>
										</div>
									</div>
									<div  style="margin-right: 5%;">
						                <%--  <spring:url value="/admin/{userId}/delete" var="deleteUrl">
						                         <spring:param name="userId" value="${createdUser.idUser}"/>
						                  </spring:url>
						                  <a href="${fn:escapeXml(deleteUrl)}" class="btn btn-danger btn-xs pull-right">Supprimer</a> --%>

						                 <spring:url value="{userId}-edit" var="editUrl">
						                      	<spring:param name="userId" value="${createdCourrier.idCourrier}"/>
						                 </spring:url>
					                	<a href="${fn:escapeXml(editUrl)}" class="btn btn-info btn-xs pull-right">Editer </a>
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
							   <ul class="nav navbar-right panel_toolbox">
							<spring:url value="/bo/createTransfert" var="addUrl">
						        <spring:param name="idCourrier" value="${createdCourrier.idCourrier}"/>
						    </spring:url>
								<a href="${fn:escapeXml(addUrl)}" class="btn btn-success btn-xs"><i class="fa fa-send"></i> Nouveau transfert </a>
								
							</ul>
							<div class="clearfix"></div>
						</div>
						<div class="x_content">
							<table id="datatable-keytable" class="table table-striped table-bordered">
								  <thead>
								    <tr>
								      <th>Emetteur</th>
								      <th>Destinataire</th>
								      <th>Date de transfert </th>
								      <th>Estimation</th>
								      <th>jj</th>
								      <th>jj</th>
								      <th>hh</th>
								    </tr>
								  </thead>
								<c:forEach var="transfertToShow" items="${transferts}">
									  	<tr>
									      <td >${transfertToShow.emetteurUnite.nomUniteBancaire}</td>
									      <td>
									      <c:if test="${transfertToShow.destinataireType == 'unite'}">${transfertToShow.destinataireUnite.nomUniteBancaire}</c:if>
							    	      <c:if test="${transfertToShow.destinataireType == 'contact'}">${transfertToShow.destinataireContact.nomContactExterne}</c:if> 
										</td>
									      <td >${transfertToShow.dateTransfert}</td>
									      <td>${transfertToShow.estimation}</td>
					                	  <td style="text-align: center">
					                	 		 <spring:url value="/bo/transfert-{idTransfert}" var="displayUrl">
						                      	<spring:param name="idTransfert" value="${transfertToShow.idTransfert}"/>
						                	  </spring:url>
						                	  <a href="${fn:escapeXml(displayUrl)}" class="btn btn-primary btn-xs">Visualiser</a>
						                </td>
						                <td style="text-align: center">	  
										      <spring:url value="/bo/transfert-{idTransfert}-edit" var="editUrl">
						                      	<spring:param name="idTransfert" value="${transfertToShow.idTransfert}"/>
						                      	<spring:param name="idCourrier" value="${createdCourrier.idCourrier}"/>
						                	  </spring:url>
						                	  <a href="${fn:escapeXml(editUrl)}" class="btn btn-info btn-xs">Editer</a>
					                	  </td>
					                	  <td style="text-align: center">	  
										      <spring:url value="/bo/transfert-{idTransfert}/delete" var="deleteUrl">
						                      	<spring:param name="idTransfert" value="${transfertToShow.idTransfert}"/>
						                      	<spring:param name="idCourrier" value="${createdCourrier.idCourrier}"/>
						                	  </spring:url>
						                	 <a href="#myModal_${transfertToShow.idTransfert}" role="button" class="btn btn-danger btn-xs" data-toggle="modal">Supprimer</a>
						                	  <div id="myModal_${transfertToShow.idTransfert}" class="modal fade bs-example-modal-sm-mehdi" tabindex="-1" role="dialog" aria-hidden="true">
                    							<div class="modal-dialog modal-sm">
                      								<div class="modal-content">
                        								<div class="modal-header">
                          									<h4 class="modal-title" id="myModalLabel2">Verification</h4>
                       									 </div>
                       									 <div class="modal-body">
                          								<h4>Attention !!!</h4>
                          								<p>Etes-vous sur de vouloir supprimer ce transfert ???</p>
                        								</div>
                       									 <div class="modal-footer">
                          									<button type="button" class="btn btn-default" data-dismiss="modal" >Fermer</button>
                         									 <a href="${fn:escapeXml(deleteUrl)}" class="btn btn-primary" >Supprimer</a>
                       									 </div>

                      								</div>
                    							</div>
                						  </div>
					                	  </td>
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
