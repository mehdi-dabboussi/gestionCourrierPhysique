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
				
	
				<div class="row">
					<div class="col-md-12 col-sm-12 col-xs-12">
						<div class="x_panel">
							<div class="x_title">
								<h2>Mon profil</h2>
								<div class="clearfix"></div>
							</div>
							<div class="x_content">
								<br />

								<form:form class="form-horizontal form-label-leftr" 
											data-toggle="validator" role="form" modelAttribute="newUser" >			
										 <div class="form-group">
											 <label for="inputRacineLab"  class="control-label col-md-3 col-sm-3 col-xs-12">Nom<em>*</em></label>	
											 <div class="col-md-6 col-sm-6 col-xs-12">
											     <input type="text" placeholder="nom" class="form-control col-md-7 col-xs-12"
														id="exampleInputEmail1" name="userName" value="${newUser.userName}" required="required">
											 </div>
										 </div>
								         <div class="form-group">
									        <label for="inputSocieteClientLab"  class="control-label col-md-3 col-sm-3 col-xs-12">Prénom<em>*</em></label>	
									        <div class="col-md-6 col-sm-6 col-xs-12">
									             <input type="text" class="form-control  col-md-7 col-xs-12"
													id="inputSurName" name="surName" value="${newUser.surName}" placeholder="prénom" required>
											</div>
									     </div>
									     <div class="item form-group">
										     <label for="inputuserEmail"  class="control-label col-md-3 col-sm-3 col-xs-12">Email<em>*</em></label>
										     <div class="col-md-6 col-sm-6 col-xs-12">
												 <input type="email" class="form-control col-md-7 col-xs-12" id="inputUserEmail" placeholder="Email"
												 		name="userEmail" value="${newUser.userEmail}" required>
											 </div>
										</div>
									     <sec:authorize access="hasRole('ROLE_ADMIN')">
										     <div class="form-group">
											     <label for="inputNomClientLab"  class="control-label col-md-3 col-sm-3 col-xs-12">Identifiant utilisateur<em>*</em></label>
											     <div class="col-md-6 col-sm-6 col-xs-12">
													<input type="text" class="form-control col-md-7 col-xs-12" id="inputhistoriqueIdentifier" placeholder="Identitifant utilisateur"
												 		name="historiqueIdentifier" value="${newUser.historiqueIdentifier}" required>
												 </div>
											</div>
										</sec:authorize>
										<div class="form-group">
										     <label for="inputNomClientLab"  class="control-label col-md-3 col-sm-3 col-xs-12">Login<em>*</em></label>
										     <div class="col-md-6 col-sm-6 col-xs-12">
												 <input type="text"  class="form-control  col-md-7 col-xs-12"
														id="inputName" name="login" value="${newUser.login}" placeholder="login" required>
											 </div>
										</div>
									 	<div class="item form-group">
										     <label for="inputPasswordLab"  class="control-label col-md-3 col-sm-3 col-xs-12">Password<em>*</em></label>	
										      <div class="col-md-6 col-sm-6 col-xs-12">
								                <input id="password" placeholder="mot de passe" type="password" name="password" class="form-control col-md-7 col-xs-12" 
								                  value="${newUser.password}"  data-validate-length="5" required>
								              	<div class="help-block">Minimum of 6 characters</div>
											 </div> 
									    </div>
									    <div class="item form-group ">
										     <label for="inputLoginLab"  class="control-label col-md-3 col-sm-3 col-xs-12">Confirm password<em>*</em></label>	
										      <div class="col-md-6 col-sm-6 col-xs-12">
												<input id="inputPasswordConfirm" type="password" class="form-control col-md-7 col-xs-12" 
						                         	  data-validate-linked="password"   value="${newUser.password}" 
						                         	  placeholder="Confirmer mot de passe" required>
												<div class="help-block with-errors"></div>
											 </div> 
									    </div> 
									 	<sec:authorize access="hasRole('ROLE_ADMIN')">
											 <div class="form-group">
												 <label for="inputDecedeLab"  class="control-label col-md-3 col-sm-3 col-xs-12">Rôle</label>	
												 <div class="col-md-6 col-sm-6 col-xs-12">
													 <div class="radio">
														<label> <input type="radio" <c:if test="${role == 'ROLE_USER'}">checked="checked"</c:if> value="ROLE_USER" name="role" required>
															Simple utilisateur
														</label>
													</div>
													<div class="radio">
														<label> <input type="radio" <c:if test="${role == 'ROLE_ADMIN'}">checked="checked"</c:if> value="ROLE_ADMIN" name="role" required>
															Administrateur
														</label>
													</div>
												</div>
											 </div>
										 </sec:authorize>
										 
										<div class="ln_solid"></div>
										<div class="form-group" style="margin-left: 65%;margin-top: 2%;">
					                        	<button type="submit" class="btn btn-info btn-xs">Enregistrer</button>
					                        	<a class="btn btn-danger btn-xs" href="<c:url value="javascript:history.go(-1)" />"> 
													Annuler	</a>
				                       </div>
							   </form:form>
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

 <!-- validator -->
 	 <script src="/../../nJs/validator.js"></script>
    <script>
      // initialize the validator function
      validator.message.date = 'Pas une date r&#233;elle';

      // validate a field on "blur" event, a 'select' on 'change' event & a '.reuired' classed multifield on 'keyup':
      $('form')
        .on('blur', 'input[required], input.optional, select.required', validator.checkField)
        .on('change', 'select.required', validator.checkField)
        .on('keypress', 'input[required][pattern]', validator.keypress);

      $('.multi.required').on('keyup blur', 'input', function() {
        validator.checkField.apply($(this).siblings().last()[0]);
      });

      $('form').submit(function(e) {
        e.preventDefault();
        var submit = true;

        // evaluate the form using generic validaing
        if (!validator.checkAll($(this))) {
          submit = false;
        }

        if (submit)
          this.submit();

        return false;
      });
    </script>
    <!-- /validator -->
</body>
</html>
	