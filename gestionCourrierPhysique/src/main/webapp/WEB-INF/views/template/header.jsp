<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="top_nav">
	<div class="nav_menu">
		<nav>
			<div class="nav toggle">
				<a id="menu_toggle"><i class="fa fa-bars"></i></a>
			</div>
			<ul class="nav navbar-nav navbar-right">
				<li class=""><a href="javascript:;"
					class="user-profile dropdown-toggle" data-toggle="dropdown"
					aria-expanded="false"> <img src="images/myp1.png" alt="">
						<sec:authorize access="isAuthenticated()">
							<sec:authentication property="principal" var="user" />
						${user.username}
					</sec:authorize> <sec:authorize access="!isAuthenticated()">
						Anonymos
					</sec:authorize> <span class=" fa fa-angle-down"></span>
				</a>
					<ul class="dropdown-menu dropdown-usermenu pull-right">
						<sec:authorize access="hasRole('ROLE_ADMIN')">
							<li><a class="glyphicon glyphicon-user pull-right" href="<c:url value="/admin/newUser" />"> Nouveau</a></li>
							<li><a class="glyphicon glyphicon-user pull-right" href="<c:url value="/admin/allUsers" />"> Modifier</a></li>
						</sec:authorize>
						<li><a href="<c:url value="/home/myProfile"/>"
							class="glyphicon glyphicon-pencil pull-right"> Profile</a></li>
						<li><a class="fa fa-sign-out pull-right"
							href="<c:url value="/logout" />"> Déconnexion</a></li>
					</ul></li>

				<li role="presentation" class="dropdown"><a href="javascript:;"
					class="dropdown-toggle info-number" data-toggle="dropdown"
					aria-expanded="false"> <i class="fa fa-bullhorn"></i> <span
						class="badge bg-green">${notifications.size()}</span>
				</a>

					<ul id="menu1" class="dropdown-menu list-unstyled msg_list"
						role="menu">
						<c:set var="contextPath"
							value="${pageContext.request.contextPath}" />
						<c:choose>
							<c:when test="${notifications.size()>0}">
								<c:forEach var="notificationVar" items="${notifications}"
									end="6">
									<li><c:choose>
											<c:when
												test="${notificationVar.typeAction.equals('Action contentieuse')}">
												<a
													href="${contextPath}/ficheClient/${notificationVar.ficheClientId}?idSpec=${Math.random()}#${notificationVar.creanceId}#actionCT${notificationVar.creanceId}">
													<span> <span class="time">${notificationVar.typeAction}</span>
														<br>
												</span> <span class="message">
														${notificationVar.natureAction} </span> <span class="message">
														${notificationVar.getDateFormat(notificationVar.dateRevoir)}
												</span>
												</a>
											</c:when>
										</c:choose> <c:choose>
											<c:when
												test="${notificationVar.typeAction.equals('Action de recouvrement')}">
												<a
													href="${contextPath}/ficheClient/${notificationVar.ficheClientId}?idSpec=${Math.random()}#${notificationVar.creanceId}#recouvrement${notificationVar.creanceId}">
													<span> <span class="time">${notificationVar.typeAction}</span>
														<br>
												</span> <span class="message">
														${notificationVar.getDateFormat(notificationVar.dateRevoir)}
												</span>
												</a>
											</c:when>
										</c:choose></li>
								</c:forEach>
								<li>
									<div class="text-center">
										<a> <strong>Toutes les notifications</strong> <i
											class="fa fa-angle-right"></i>
										</a>
									</div>
								</li>
							</c:when>
							<c:otherwise>
								<li>
									<div class="text-center">
										<a> <strong>Aucune notification disponible</strong>
										</a>
									</div>
								</li>
							</c:otherwise>
						</c:choose>
					</ul></li>
			</ul>
		</nav>
	</div>
</div>
<!-- /top navigation -->
