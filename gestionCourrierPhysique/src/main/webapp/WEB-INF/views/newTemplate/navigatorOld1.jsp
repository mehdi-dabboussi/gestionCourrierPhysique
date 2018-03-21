<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<div id="sidebar-menu" class="main_menu_side hidden-print main_menu">
	<div class="col-md-3 left_col">
		<div class="left_col scroll-view">
			<div class="navbar nav_title" style="border: 0;">
				<a href="index.html" class="site_title"> <!-- <i class="fa fa-euro"></i>  -->
					<img src="../images/logo.png" alt="..."> <span>Gestion
						du courrier</span></a>
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
			<div class="menu_section">
			 <h3>General</h3>
				<sec:authorize access="isAuthenticated()">
					<sec:authentication property="principal" var="user" />
					<h3>
						<c:if test="${user.authorities == '[ROLE_ADMIN]'}"> {Administrateur} </c:if>
						<c:if test="${user.authorities == '[ROLE_USER]'}"> {Simple Utilisateur}</c:if>
					</h3>
				</sec:authorize>

				<ul class="nav side-menu">
					<c:set var="contextPath" value="${pageContext.request.contextPath}" />
					<sec:authorize access="hasAnyRole('ROLE_ADMIN')">
						<li><a><i class="fa fa-home"></i>Gestion des courriers<span
								class="fa fa-chevron-down"></span></a>
							<ul class="nav child_menu">
								<li><a href="${contextPath}/courrier/allCourrier">Tous les courriers</a></li>
								<li><a href="${contextPath}/admin/createCoursier">Nouveau coursier</a></li>

							</ul></li>
					</sec:authorize>

					<li><a><i class="fa fa-home"></i>Courriers<span
							class="fa fa-chevron-down"></span></a>
						<ul class="nav child_menu">
							<sec:authorize
								access="hasAnyRole('ROLE_RESPONSABLE_COURRIER_BANQUE','ROLE_ADMIN')">
								<li><a href="${contextPath}/courrier/transfertToMe">Transfret
										à traiter</a></li>
								<li><a
									href="${contextPath}/courrier/userTransfertParticipate">Transfret
										participation</a></li>
								<li><a href="${contextPath}/courrier/newCourrier">Nouveau
										courrier</a></li>
							</sec:authorize>
							<li><a href="${contextPath}/courrier/createdCourrierByUser">Courriers
									envoyés</a></li>
							<li><a href="${contextPath}/courrier/courrierReceived">Courriers
									reçus</a></li>
						</ul></li>


					<sec:authorize
						access="hasAnyRole('ROLE_RESPONSABLE_COURRIER_BANQUE','ROLE_ADMIN')">
						<li><a><i class="fa fa-home"></i>Gestion bordereaux<span
								class="fa fa-chevron-down"></span></a>
							<ul class="nav child_menu">
								<li><a href="${contextPath}/courrier/createBordereau">Nouveau
										Bordereau</a></li>
							</ul></li>
					</sec:authorize>



					<sec:authorize access="hasAnyRole('ROLE_ADMIN')">
						<li><a><i class="fa fa-home"></i>Gestion des unité
								Bancaire<span class="fa fa-chevron-down"></span></a>
							<ul class="nav child_menu">
								<li><a href="${contextPath}/admin/newUniteBanque">Nouvelle
										unité</a></li>
								<li><a href="${contextPath}/admin/allUniteBanque">Unités
										bancaires</a></li>
							</ul></li>
					</sec:authorize>
					<li><a href="${contextPath}/courrier/allCourrier">Tous les
							courriers</a></li>
				</ul>
			</div>

			<!-- /sidebar menu -->

			<!-- /menu footer buttons -->
			<div class="sidebar-footer hidden-small">
				<a data-toggle="tooltip" data-placement="top" title="Settings">
					<span class="glyphicon glyphicon-cog" aria-hidden="true"></span>
				</a> <a data-toggle="tooltip" data-placement="top" title="FullScreen">
					<span class="glyphicon glyphicon-fullscreen" aria-hidden="true"></span>
				</a> <a data-toggle="tooltip" data-placement="top" title="Lock"> <span
					class="glyphicon glyphicon-eye-close" aria-hidden="true"></span>
				</a> <a data-toggle="tooltip" data-placement="top" title="Logout"> <span
					class="glyphicon glyphicon-off" aria-hidden="true"></span>
				</a>
			</div>
			<!-- /menu footer buttons -->
		</div>
	</div>
</div>