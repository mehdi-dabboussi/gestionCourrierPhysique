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
									Information utilisateur</
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
														<td>${createdUser.nom}</td>
													</tr>
													<tr>
														<th >Prénom:</th>
														<td>${createdUser.surName}</td>
													</tr>
													<tr>
														<th >Email:</th>
														<td>${createdUser.email}</td>
													</tr>
													<tr>
														<th >Téléphone:</th>
														<td>${createdUser.tel}</td>
													</tr>
													<tr>
														<th >identifiant utilisateur:</th>
														<td>${createdUser.historiqueIdentifier}</td>
													</tr>
													<tr>
														<th >Login:</th>
														<td>${createdUser.login}</td>
													</tr>
													<tr>
														<th >Unité bancaire:</th>
														<td>${uniteBancaire}</td>
													</tr>
													<tr>
														<th >Role:</th>
														<td>
															<c:if test="${role == 'ROLE_ADMIN'}">Administrateur </c:if> 
															<c:if test="${role == 'ROLE_USER'}">Simple Utilisateur</c:if> 
															<c:if test="${role == 'ROLE_BUREAU_ORDRE'}">Bureau d'ordre</c:if>
														</td>
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
						                      	<spring:param name="userId" value="${createdUser.id}"/>
						                 </spring:url>
					                	<a href="${fn:escapeXml(editUrl)}" class="btn btn-info btn-xs pull-right">Editer </a></td>
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
