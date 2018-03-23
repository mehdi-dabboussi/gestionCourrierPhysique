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
								Utilisateurs
							</h2>
							<ul class="nav navbar-right panel_toolbox">
								<a href="<c:url value="/admin/newUser" />" class="btn btn-success btn-xs"><i class="fa fa-user-plus"></i> Nouveau compte utilisateur </a>
								
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
								      <th>Unité bancaire</th>
								      <th>Rôle</th>
								      <th></th>
								      <th></th>
								      <th></th>
								      <th></th>
								    </tr>
								  </thead>
								<c:forEach var="userToShow" items="${users}">
									  	<tr>
									      <td >${userToShow.userName}</td>
									      <td >${userToShow.surName}</td>
									      <td >${userToShow.userEmail}</td>
									      <td>${userToShow.uniteBancaire.nomUniteBancaire }</td>
									      <td>
									      		<c:if test="${userToShow.roles.get(0).name == 'ROLE_ADMIN'}">Administrateur </c:if> 
												<c:if test="${userToShow.roles.get(0).name == 'ROLE_USER'}">Simple Utilisateur</c:if>
												<c:if test="${userToShow.roles.get(0).name == 'ROLE_BUREAU_ORDRE'}">Bureau d'ordre</c:if> 
									      </td>
									      
									     
									      
					                	  <td style="text-align: center">
					                	 		 <spring:url value="/admin/{userId}" var="displayUrl">
						                      	<spring:param name="userId" value="${userToShow.idUser}"/>
						                	  </spring:url>
						                	  <a href="${fn:escapeXml(displayUrl)}" class="btn btn-primary btn-xs">Visualiser</a>
						                </td>
						                <td style="text-align: center">	  
										      <spring:url value="{userId}-edit" var="editUrl">
						                      	<spring:param name="userId" value="${userToShow.idUser}"/>
						                	  </spring:url>
						                	  <a href="${fn:escapeXml(editUrl)}" class="btn btn-info btn-xs">Editer</a>
					                	  </td>
					                	  <td style="text-align: center">	  
										      <spring:url value="{userId}/delete" var="deleteUrl">
						                      	<spring:param name="userId" value="${userToShow.idUser}"/>
						                	  </spring:url>
						                	  <a href="${fn:escapeXml(deleteUrl)}" class="btn btn-danger btn-xs">Supprimer</a>
					                	  </td>
					                	  
					                	   <td>
									      <spring:url value="/admin/{userId}-enable" var="enableUrl">
									      <spring:param name="userId" value="${userToShow.idUser }"/>
									      </spring:url>
									      <c:if test="${userToShow.enabled == false }"><a href="${fn:escapeXml(enableUrl)}" class="btn btn-success btn-xs">Enable</a></c:if>
									     <spring:url value="/admin/{userId}-disable" var="disableUrl">
									      <spring:param name="userId" value="${userToShow.idUser }"/>
									      </spring:url>
									      <c:if test="${userToShow.enabled == true }"><a href="${fn:escapeXml(disableUrl)}" class="btn btn-warning btn-xs">Disable</a></c:if>
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
