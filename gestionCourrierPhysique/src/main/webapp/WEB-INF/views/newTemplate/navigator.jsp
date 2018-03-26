<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div class="col-md-3 left_col" style="min-height: 100%;">
	<div class="left_col scroll-view">
		<div class="navbar nav_title" style="border: 0;">
		<spring:url value="/home/myProfile" var="myProfile"></spring:url>
			<a href="${fn:escapeXml(myProfile)}" class="site_title"><i class="fa fa-envelope"></i>
				<span>Gestion Courrier</span></a>
		</div>

		<div class="clearfix"></div>

		<!-- menu profile quick info -->
		<div class="profile">
			<div class="profile_pic">
				<img src="../images/myp1.png" alt="..." class="img-circle profile_img">
			</div>
			<div class="profile_info">
				<span>Bienvenue,</span>
				<sec:authorize access="isAuthenticated()">
					<sec:authentication property="principal" var="user" />
					<h2>${user.username}</h2>
				</sec:authorize>
				<sec:authorize access="!isAuthenticated()">
					<h2>Anonymos</h2>
				</sec:authorize>
			</div>
		</div>
		<!-- /menu profile quick info -->

		<br />

		<!-- sidebar menu -->
		<div id="sidebar-menu" class="main_menu_side hidden-print main_menu">
			<div class="menu_section">
				<sec:authorize access="isAuthenticated()">
					<sec:authentication property="principal" var="user" />
					<h3>
						<c:if test="${user.authorities == '[ROLE_USER]'}"> Simple Utilisateur</c:if>
						<c:if test="${user.authorities == '[ROLE_ADMIN]'}"> Administrateur </c:if>
						<c:if test="${user.authorities == '[ROLE_BUREAU_ORDRE]'}"> Bureau d'ordre </c:if>
					</h3>
				</sec:authorize>
				<c:set var="contextPath" value="${pageContext.request.contextPath}" />
				<ul class="nav side-menu">
					
					<sec:authorize access="hasAnyRole('ROLE_ADMIN')">
						<li><a><i class="fa fa-users"></i>Utilisateur<span class="fa fa-chevron-down"></span></a>
							<ul class="nav child_menu">
								<li><a href="${contextPath}/admin/newUser">Nouveau</a></li>
								<li><a href="${contextPath}/admin/allUsers">Les utilisateurs</a></li>
							</ul>
						</li>
					</sec:authorize>
					
					<sec:authorize access="hasAnyRole('ROLE_ADMIN')">
						<li><a><i class="fa fa-users"></i>Unité Bancaire<span class="fa fa-chevron-down"></span></a>
							<ul class="nav child_menu">
								<li><a href="${contextPath}/admin/newUniteBancaire">Nouveau</a></li>
								<li><a href="${contextPath}/admin/allUniteBancaire">Les unités Bancaire</a></li>
							</ul>
						</li>
					</sec:authorize>
					
					<sec:authorize access="hasAnyRole('ROLE_BUREAU_ORDRE')">
						<li><a><i class="fa fa-users"></i>Contact externe<span class="fa fa-chevron-down"></span></a>
							<ul class="nav child_menu">
								<li><a href="${contextPath}/bo/createContactExterne">Nouveau</a></li>
								<li><a href="${contextPath}/bo/allContactExterne">Les contacts externes</a></li>
							</ul>
						</li>
					</sec:authorize>
					
					<sec:authorize access="hasAnyRole('ROLE_ADMIN')">
						<li><a><i class="fa fa-users"></i>Transporteur<span class="fa fa-chevron-down"></span></a>
							<ul class="nav child_menu">
								<li><a href="${contextPath}/admin/createTransporteurExterne">Nouveau</a></li>
								<li><a href="${contextPath}/admin/allTransporteurExterne">Les transporteurs externes</a></li>
							</ul>
						</li>
					</sec:authorize>
					
				</ul>
			</div>
		</div>
		<div>
			<div class="profile">
				<div class="profile_pic">
					<img style="padding-left: 60%;" src="../images/steLogo.png" alt="">
				</div>
			</div>
		</div>
		<!-- /sidebar menu -->

		<!-- /menu footer buttons -->
		<div class="sidebar-footer hidden-small">
			<a data-toggle="tooltip" data-placement="top" title="Settings"> 
				<span class="glyphicon glyphicon-cog" aria-hidden="true"></span>
			</a> <a data-toggle="tooltip" data-placement="top" title="FullScreen">
				<span class="glyphicon glyphicon-fullscreen" aria-hidden="true"></span>
			</a> <a data-toggle="tooltip" data-placement="top" title="Lock"> 
				<span class="glyphicon glyphicon-eye-close" aria-hidden="true"></span>
			</a> <a data-toggle="tooltip" data-placement="top" title="Logout"> 
				<span class="glyphicon glyphicon-off" aria-hidden="true"></span>
			</a>
		</div>
		<!-- /menu footer buttons -->
	</div>
</div>