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
									Information contact externe</
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
														<th >Nom : </th>
														<td>${createdContactExterne.nomContactExterne}</td>
													</tr>
													<tr>
														<th >Adresse :</th>
														<td>${createdContactExterne.adresseContactExterne}</td>
													</tr>
													<tr>
														<th >Téléphone :</th>
														<td>${createdContactExterne.telContactExterne}</td>
													</tr>
													<tr>
														<th >E-mail :</th>
														<td>${createdContactExterne.emailContactExterne}</td>
													</tr>
													<tr>
														<th >Fax :</th>
														<td>${createdContactExterne.faxContactExterne}</td>
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

						                 <spring:url value="contactexterne-{idContactExterne}-edit" var="editUrl">
						                      	<spring:param name="idContactExterne" value="${createdContactExterne.idContactExterne}"/>
						                 </spring:url>
					                	<a href="${fn:escapeXml(editUrl)}" class="btn btn-info btn-xs pull-right">Editer </a></td>
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
								Les sous contacts externes
							</h2>
							   <ul class="nav navbar-right panel_toolbox">
							<spring:url value="/bo/createSousContact" var="addUrl">
						        <spring:param name="idContactExterne" value="${createdContactExterne.idContactExterne}"/>
						    </spring:url>
								<a href="${fn:escapeXml(addUrl)}" class="btn btn-success btn-xs"><i class="fa fa-user-plus"></i> Nouveau sous contact </a>
								
							</ul>
							<div class="clearfix"></div>
						</div>
						<div class="x_content">
							<table id="datatable-keytable" class="table table-striped table-bordered">
								  <thead>
								    <tr>
								      <th>Nom</th>
								      <th>Prénom</th>
								      <th>Email</th>
								      <th>Téléphone</th>
								      <th></th>
								      <th></th>
								      <th></th>
								    </tr>
								  </thead>
								<c:forEach var="sousContactToShow" items="${sousContacts}">
									  	<tr>
									      <td >${sousContactToShow.nomSousContactExterne}</td>
									      <td >${sousContactToShow.prenomSousContactExterne}</td>
									      <td >${sousContactToShow.emailSousContactExterne}</td>
									      <td>${sousContactToShow.telSousContactExterne}</td>
					                	  <td style="text-align: center">
					                	 		 <spring:url value="/bo/sousContact-{idSousContactExterne}" var="displayUrl">
						                      	<spring:param name="idSousContactExterne" value="${sousContactToShow.idSousContactExterne}"/>
						                	  </spring:url>
						                	  <a href="${fn:escapeXml(displayUrl)}" class="btn btn-primary btn-xs">Visualiser</a>
						                </td>
						                <td style="text-align: center">	  
										      <spring:url value="/bo/sousContact-{idSousContactExterne}-edit" var="editUrl">
						                      	<spring:param name="idSousContactExterne" value="${sousContactToShow.idSousContactExterne}"/>
						                      	<spring:param name="idContactExterne" value="${createdContactExterne.idContactExterne}"/>
						                	  </spring:url>
						                	  <a href="${fn:escapeXml(editUrl)}" class="btn btn-info btn-xs">Editer</a>
					                	  </td>
					                	  <td style="text-align: center">	  
										      <spring:url value="/bo/sousContact-{idSousContactExterne}/delete" var="deleteUrl">
						                      	<spring:param name="idSousContactExterne" value="${sousContactToShow.idSousContactExterne}"/>
						                      	<spring:param name="idContactExterne" value="${createdContactExterne.idContactExterne}"/>
						                	  </spring:url>
						                	  <a href="${fn:escapeXml(deleteUrl)}" class="btn btn-danger btn-xs">Supprimer</a>
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
