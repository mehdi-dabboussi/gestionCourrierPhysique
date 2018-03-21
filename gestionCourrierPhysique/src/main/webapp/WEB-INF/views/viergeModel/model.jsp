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
				
				
				
				<c:url value="/questionPost" var="postUrl"/>

					<form:form action="${postUrl}" modelAttribute="questionsModel" method="post">
					    <table>
					    <tr>
					        <th>Question</th>
					        <th>Options</th>        
					    </tr>   
					    <c:forEach items="${questionsModel.questionMap}" var="currQue" varStatus="queIndex">
					        <tr>
					            <td>
					                <form:hidden path="questionMap[${queIndex.count}].question"/>
					                <label>Question:</label><c:out value="${currQue.value.question}"/><br/>
					            </td>
					            <td>
					            <c:forEach items="${currQue.value.optionMap}" var="opt" varStatus="optionIndex">
					                <form:hidden path="questionMap[${queIndex.count}].optionMap[${optionIndex.count}].optionText"/>
					                <form:hidden path="questionMap[${queIndex.count}].optionMap[${optionIndex.count}].optionKey"/>
					
					                <form:radiobutton path="questionMap[${queIndex.count}].selectedOptionKey"
					                    value="${opt.value.optionKey}" label="${opt.value.optionText}"/>
					
					            </c:forEach>
					
					             </td>
					        </tr>
					    </c:forEach>
					    </table>
					    <input type="submit"/>
					</form:form>
				
				
				
				
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
