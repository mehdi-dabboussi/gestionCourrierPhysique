<!DOCTYPE html>
<%@ page session="false" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html lang="en">
<jsp:include page="template/staticFilesRacine.jsp"/>

<body>	
<div class="login-page">
    <div class="login-main">  	
    	 <div class="login-head">
				<h1>CONNEXION</h1>
			</div>
			<div class="login-block">
				<form action="<spring:url value='/j_spring_security_check' />" role="form" method="post">
					<input type="text" name="login" placeholder="Login" required="required">
					<input type="password" name="password" class="lock" placeholder="Password">
					
					<input type="submit" name="Sign In" value="Login">
					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />	
					
				</form>
			</div>
      </div>
</div>
<!--inner block end here-->
<!--copy rights start here-->
<jsp:include page="template/footer.jsp"/>

</body>
</html>


                      
						
