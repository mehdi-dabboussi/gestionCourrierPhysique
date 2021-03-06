<!DOCTYPE html>

<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="datatables" uri="http://github.com/dandelion/datatables"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="myfn" uri="/WEB-INF/custom-functions.tld"  %>

<html lang="en">
<!-- css files -->
<jsp:include page="../newTemplate/staticFiles.jsp" />
<!-- /css files -->

<function>
        <name>contains</name>
        <function-class>com.Yourclass</function-class>
        <function-signature>boolean contains(java.util.List,java.lang.Object)
        </function-signature>
</function>

<function>
        <name>enabled</name>
        <function-class>com.Yourclass</function-class>
        <function-signature>boolean enabled(com.sharing.entity.User)
        </function-signature>
</function>

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
								<h2> <c:if test="${newUser.id==0}">Nouvel </c:if>Utilisateur</h2>
								<!-- <ul class="nav navbar-right panel_toolbox">
                      				<li>
                      					<a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
                      				</li>
                      		  	</ul> -->
								<div class="clearfix"></div>
							</div>
							<div class="x_content">
								<br />
								<form:form class="form-horizontal form-label-leftr" 
											data-toggle="validator" role="form" modelAttribute="newUser" >
											
								         <div class="item form-group">
									        <label for="NomLab"  class="control-label col-md-3 col-sm-3 col-xs-12">Nom & Pr�nom<em>*</em></label>	
									        <div class="col-md-6 col-sm-6 col-xs-12 has-feedback">
												 <input type="text" class="form-control col-md-7 col-xs-12" id="inputNom" placeholder="nom & pr�nom"
								                		name="nom" value="${newUser.nom}" required>  
								                		<span class="fa fa-user form-control-feedback right" aria-hidden="true"></span>  		
											</div>
									     </div>
										
										<div class="item form-group">
										     <label for="inputPrEnomLab"  class="control-label col-md-3 col-sm-3 col-xs-12">Email<em>*</em></label>
										     <div class="col-md-6 col-sm-6 col-xs-12 has-feedback">
												 <input type="email" class="form-control col-md-7 col-xs-12" id="inputUserEmail" placeholder="Email"
												 		name="email" value="${newUser.email}" required>
												 		<span class="fa fa-envelope form-control-feedback right" aria-hidden="true"></span>
												 		<div class="text-danger">${emailFound }</div>
											 </div>
										</div>
										
										<div class="item form-group">
										     <label for="userTel"  class="control-label col-md-3 col-sm-3 col-xs-12">T�l�phone<em>*</em></label>
										     <div class="col-md-6 col-sm-6 col-xs-12 has-feedback">
												 <input type="tel" class="form-control col-md-7 col-xs-12" id="userTel" placeholder="T�l�phone"
												 		name="tel" value="${newUser.tel}" data-validate-lenght="8" required >
												 		<span class="fa fa-phone form-control-feedback right" aria-hidden="true"></span>
											 </div>
										</div>
										
										<div class="item form-group ">
										     <label for="historiqueIdentifier"  class="control-label col-md-3 col-sm-3 col-xs-12">Identitifant utilisateur<em>*</em></label>	
										      <div class="col-md-6 col-sm-6 col-xs-12 has-feedback">
												 <input type="text" class="form-control col-md-7 col-xs-12" id="historiqueIdentifier" placeholder="Identitifant utilisateur"
								              			name="historiqueIdentifier" value="${newUser.historiqueIdentifier}" required>
								              			<span class="fa fa-user form-control-feedback right" aria-hidden="true"></span> 
											 </div> 
									    </div>  
										<div class="item form-group ">
										     <label for="inputLoginLab"  class="control-label col-md-3 col-sm-3 col-xs-12">Login<em>*</em></label>	
										      <div class="col-md-6 col-sm-6 col-xs-12 has-feedback">
												 <input type="text" class="form-control col-md-7 col-xs-12" id="Login" placeholder="login"
								              			name="login" value="${newUser.login}" required>
								              			<span class="fa fa-user form-control-feedback right" aria-hidden="true"></span> 
								              			<div class="text-danger">${loginFound }</div>
											 </div> 
									    </div>
									    <div class="item form-group">
										     <label for="inputPasswordLab"  class="control-label col-md-3 col-sm-3 col-xs-12">Password<em>*</em></label>	
										      <div class="col-md-6 col-sm-6 col-xs-12 has-feedback">
								                <input id="password" placeholder="mot de passe" type="password" name="password" class="form-control col-md-7 col-xs-12" 
								                  value="${newUser.password}"  data-validate-length="5" required>
								                  <span class="fa fa-key form-control-feedback right" aria-hidden="true"></span> 
								              	<div class="help-block">Minimum of 6 characters</div>
											 </div> 
									    </div>
									    <div class="item form-group ">
										     <label for="inputLoginLab"  class="control-label col-md-3 col-sm-3 col-xs-12">Confirm password<em>*</em></label>	
										      <div class="col-md-6 col-sm-6 col-xs-12 has-feedback">
												<input id="inputPasswordConfirm" type="password" class="form-control col-md-7 col-xs-12" 
						                         	  data-validate-linked="password"   value="${newUser.password}" 
						                         	  placeholder="Confirmer mot de passe" required>
						                         	  <span class="fa fa-key form-control-feedback right" aria-hidden="true"></span> 
												<div class="help-block with-errors"></div>
											 </div> 
									    </div> 
									    
									    <div class="item form-group ">
										     <label for="inputUniteBancaireLab"  class="control-label col-md-3 col-sm-3 col-xs-12">Unit� bancaire<em>*</em></label>	
										      <div class="col-md-6 col-sm-6 col-xs-12 has-feedback">
												<select name="uniteB" class="select2_single form-control" tabindex="-1" required>
													<option value="${newUser.uniteBancaire.id}">${newUser.uniteBancaire.nom}</option>
												<c:forEach var="unite" items="${uniteBancaires}">
													<option value="${unite.id}">${unite.nom}</option>
												</c:forEach>
												</select>
												<span class="fa fa-home form-control-feedback right" aria-hidden="true"></span> 
												<div class="help-block with-errors"></div>
											 </div>
									    </div>

									     <div class="form-group">
											 <label for="inputRoelLab"  class="control-label col-md-3 col-sm-3 col-xs-12">R�le<em>*</em></label>	
											 <div class="col-md-6 col-sm-6 col-xs-12">
											 <div class="radio">
													<label> <input type="radio" <c:if test="${ myfn:contains( role, 'ROLE_USER' ) }">checked="checked"</c:if> 
													value="ROLE_USER" name="role" required>
														Simple utilisateur
													</label>
												</div>
												<div class="radio">
													<label> <input type="radio" <c:if test="${ myfn:contains( role, 'ROLE_ADMIN' ) }">checked="checked"</c:if> 
													value="ROLE_ADMIN" name="role" required>
														Administrateur
													</label>
												</div>
												<div class="radio">
													<label> <input type="radio" <c:if test="${ myfn:contains( role, 'ROLE_BUREAU_ORDRE' ) }">checked="checked"</c:if> 
													value="ROLE_BUREAU_ORDRE" name="role" required>
														Bureau d'ordre
													</label>
												</div>
											</div>
										</div>
										<c:choose>
										<c:when test="${newUser.id==0}">
									    <div class="item form-group ">
										     <label for="inputEnabledLab"  class="control-label col-md-3 col-sm-3 col-xs-12">Enabled<em>*</em></label>	
										      <div class="col-md-6 col-sm-6 col-xs-12 has-feedback">
												<select name="enabled" class="form-control" required>
												<option value="yes" selected="selected">Yes</option>
												<option value="no" >No</option>
												</select>
												<span class="fa fa-check-circle form-control-feedback right" aria-hidden="true"></span> 
												<div class="help-block with-errors"></div>
											 </div>
									    </div>
									    </c:when>
										<c:otherwise>
										<c:choose>
										<c:when test="${ myfn:enabled(newUser) }">
										<div class="item form-group ">
										     <label for="inputEnabledLab"  class="control-label col-md-3 col-sm-3 col-xs-12">Enabled<em>*</em></label>	
										      <div class="col-md-6 col-sm-6 col-xs-12 has-feedback">
												<select name="enabled" class="form-control" required>
												<option value="yes" selected="selected">Yes</option>
												<option value="no"  >No</option>
												</select>
												<span class="fa fa-check-circle form-control-feedback right" aria-hidden="true"></span> 
												<div class="help-block with-errors"></div>
											 </div>
									    </div>
									    </c:when>
									    
									    <c:otherwise>
										<div class="item form-group ">
										     <label for="inputEnabledLab"  class="control-label col-md-3 col-sm-3 col-xs-12">Enabled<em>*</em></label>	
										      <div class="col-md-6 col-sm-6 col-xs-12 has-feedback">
												<select name="enabled" class="form-control" required>
												<option value="yes" selected="selected" >Yes</option>
												<option value="no" selected="selected" >No</option>
												</select>
												<span class="fa fa-check-circle form-control-feedback right" aria-hidden="true"></span> 
												<div class="help-block with-errors"></div>
											 </div>
									    </div>
									    </c:otherwise>
									    </c:choose>
									    </c:otherwise>
									    </c:choose>
										<div class="ln_solid"></div>
										<div class="form-group" style="margin-left: 65%;margin-top: 2%;">
<!-- 					                        <div > -->
					                          <c:choose>
										                <c:when test="${newUser.id==0}">
										                   	<button type="submit" class="btn btn-success btn-xs source" >Cr�er</button>
															<spring:url value="/admin/allUsers" var="returnUrl">
									                    	</spring:url>
									                		<a href="${fn:escapeXml(returnUrl)}" class="btn btn-danger btn-xs">
									                			 Annuler
									                		</a>
										                </c:when>
										                <c:otherwise>
										                   <button type="submit" class="btn btn-success btn-xs source" >Enregistrer</button>
										                   <a class="btn btn-danger btn-xs" href="<c:url value="javascript:history.go(-1)" />"> 
													       		 Annuler
													       	</a>
										                </c:otherwise>
										      </c:choose>				
<!-- 					                        </div> -->
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
	