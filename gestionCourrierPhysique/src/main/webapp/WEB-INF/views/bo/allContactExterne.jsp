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
				<div class=""></div>
				<!-- Put your main JSP here -->
				<div class="col-md-12 col-sm-12 col-xs-12">
					<div class="x_panel">
						<div class="x_title">
							<h2>
								Les Contacts externes
							</h2>
							<ul class="nav navbar-right panel_toolbox">
								<a href="<c:url value="/bo/createContactExterne" />" class="btn btn-success btn-xs"><i class="fa fa-user-plus"></i> nouveau contact externe </a>
								
							</ul>
							<div class="clearfix"></div>
						</div>
						<div class="x_content">
							<table id="datatable-keytable" class="table table-striped table-bordered">
								  <thead>
								    <tr>
								      <th>Nom</th>
								      <th>Adresse</th>
								      <th>télephone</th>
								      <th>Email</th>
								      <th>Fax</th>
								      <th></th>
								      <th></th>
								      <th></th>
								    </tr>
								  </thead>
								<c:forEach var="contactExterneToShow" items="${contactExternes}">
									  	<tr>
									      <td >${contactExterneToShow.nom}</td>
									      <td >${contactExterneToShow.adresse}</td>
									      <td >${contactExterneToShow.tel}</td>
									      <td>${contactExterneToShow.email}</td>
									      <td>${contactExterneToShow.fax}</td>
									      
					                	  <td style="text-align: center">
					                	 		 <spring:url value="/bo/contactexterne-{idContactExterne}" var="displayUrl">
						                      	<spring:param name="idContactExterne" value="${contactExterneToShow.id}"/>
						                	  </spring:url>
						                	  <a href="${fn:escapeXml(displayUrl)}" class="btn btn-primary btn-xs">Visualiser</a>
						                </td>
						                <td style="text-align: center">	  
										      <spring:url value="contactexterne-{idContactExterne}-edit" var="editUrl">
						                      	<spring:param name="idContactExterne" value="${contactExterneToShow.id}"/>
						                	  </spring:url>
						                	  <a href="${fn:escapeXml(editUrl)}" class="btn btn-info btn-xs">Editer</a>
					                	  </td>
					                	  <td style="text-align: center">	  
						                	  <a href="#myModal_${contactExterneToShow.id}" role="button" class="btn btn-danger btn-xs" data-toggle="modal">Supprimer</a>
						                	  <div id="myModal_${contactExterneToShow.id}" class="modal fade bs-example-modal-sm-mehdi" tabindex="-1" role="dialog" aria-hidden="true">
                    							<div class="modal-dialog modal-sm">
                      								<div class="modal-content">
                        								<div class="modal-header">
                          									<h4 class="modal-title" id="myModalLabel2">Verification</h4>
                       									 </div>
                       									 <div class="modal-body">
                          								<h4>Attention !!!</h4>
                          								<p>Etes-vous sur de vouloir supprimer ce contact externe ???</p>
                        								</div>
                       									 <div class="modal-footer">
                          									<button type="button" class="btn btn-default" data-dismiss="modal" >Fermer</button>
                         									 <a href="${pageContext.request.contextPath}/bo/contactexterne-${contactExterneToShow.id}/delete" class="btn btn-primary" ">Supprimer</a>
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
