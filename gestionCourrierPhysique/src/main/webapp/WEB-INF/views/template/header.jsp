<%@ page session="false" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<div class="header-main">
	<div class="header-left">
		<div class="logo-name">
			<a href="index.html"> <!--<img id="logo" src="" alt="Logo"/>--> <img
				src="../images/UBCI2.png" alt="Logo" />
			</a>
		</div>
		<div class="clearfix"></div>
	</div>
	<div class="header-right">


		<!--notification menu end -->
		<div class="profile_details">
			<ul>
				<li class="dropdown profile_details_drop"><a href="#"
					class="dropdown-toggle" data-toggle="dropdown"
					aria-expanded="false">
						<div class="profile_img">
							<span class="prfil-img"><img src="../images/myp1.png"
								alt=""> </span>
							<div class="user-name">
								<!--  <sec:authentication var="principal" property="principal" /> -->
								<sec:authorize access="isAuthenticated()">
									<sec:authentication property="principal" var="username" />
									<p>${principal.username}</p>
									<span>${principal.authorities}</span>
								</sec:authorize>
								<sec:authorize access="!isAuthenticated()">
									<sec:authentication property="principal" var="username" />
									<p>Anonymos</p>
								</sec:authorize>
							</div>
							<i class="fa fa-angle-down lnr"></i> <i
								class="fa fa-angle-up lnr"></i>
							<div class="clearfix"></div>
						</div>
				</a>
					<ul class="dropdown-menu drp-mnu">
						 <sec:authorize access="hasRole('ROLE_ADMIN')">
						 <li> <a class="glyphicon glyphicon-user" href="<c:url value="/admin/newUser" />">  Nouveau</a> </li>
						 
						 
						 <li> <a class="glyphicon glyphicon-user" href="<c:url value="/admin/allUsers" />">  Modifier</a> </li>
						 
						 
						 
					    </sec:authorize>
						<li> <a href="<c:url value="/home/myProfile" />" class="glyphicon glyphicon-pencil">  Profile</a> </li>
						<li> <a href="<c:url value="/logout" />"><i class="fa fa-sign-out"></i>Déconnexion</a> </li>
						
					</ul></li>
			</ul>
		</div>
		<div class="clearfix"></div>
	</div>
	<div class="clearfix"></div>
</div>