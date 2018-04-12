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
								<h2> <c:if test="${newUniteBancaire.idUniteBancaire==0}">Nouvel </c:if>Unité Bancaire</h2>
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
											data-toggle="validator" role="form" modelAttribute="newUniteBancaire" >
											
								         <div class="item form-group">
									        <label for="NomLab"  class="control-label col-md-3 col-sm-3 col-xs-12">Nom de l'unite<em>*</em></label>	
									        <div class="col-md-6 col-sm-6 col-xs-12 has-feedback">
												 <input type="text" class="form-control col-md-7 col-xs-12" id="inputNom" placeholder="Nom de l'unite"
								                		name="nomUniteBancaire" value="${newUniteBancaire.nomUniteBancaire}" required>    		
								                		<span class="fa fa-home form-control-feedback right" aria-hidden="true"></span> 
											</div>
									     </div>
										<div class="item form-group">
										     <label for="inputAdressLab"  class="control-label col-md-3 col-sm-3 col-xs-12">Adresse<em>*</em></label>
										     <div class="col-md-6 col-sm-6 col-xs-12 has-feedback">
												 <input type="text" class="form-control col-md-7 col-xs-12"id="inputAdress" placeholder="Adresse"
												 		name="adresseUniteBancaire" value="${newUniteBancaire.adresseUniteBancaire}" required>
												 		<span class="fa fa-home form-control-feedback right" aria-hidden="true"></span> 
											 </div>
										</div>
										<div class="item form-group">
										     <label for="inputTelLab"  class="control-label col-md-3 col-sm-3 col-xs-12">Téléphone<em>*</em></label>
										     <div class="col-md-6 col-sm-6 col-xs-12 has-feedback">
												 <input type="tel" class="form-control col-md-7 col-xs-12" id="inputTelUniteBancaire" placeholder="Téléphone"
												 		name="telUniteBancaire" value="${newUniteBancaire.telUniteBancaire}" data-validate-lenght="8" required >
												 		<span class="fa fa-phone form-control-feedback right" aria-hidden="true"></span> 
											 </div>
										</div>
										<div class="item form-group ">
										     <label for="inputEmailUniteBancaire"  class="control-label col-md-3 col-sm-3 col-xs-12">e-mail<em>*</em></label>	
										      <div class="col-md-6 col-sm-6 col-xs-12 has-feedback">
												 <input type="email" class="form-control col-md-7 col-xs-12" id="inputEmailUniteBancaire" placeholder="e-mail"
								              			name="emailUniteBancaire" value="${newUniteBancaire.emailUniteBancaire}" required>
								              			<span class="fa fa-envelope form-control-feedback right" aria-hidden="true"></span> 
											 </div> 
									    </div>  
										<div class="item form-group ">
										     <label for="FaxUniteBancaire"  class="control-label col-md-3 col-sm-3 col-xs-12">Fax<em>*</em></label>	
										      <div class="col-md-6 col-sm-6 col-xs-12 has-feedback">
												 <input type="text" class="form-control col-md-7 col-xs-12" id="FaxUniteBancaire" placeholder="Fax"
								              			name="faxUniteBancaire" value="${newUniteBancaire.faxUniteBancaire}" data-validate-length="7" required>
								              			<span class="fa fa-fax form-control-feedback right" aria-hidden="true"></span> 
											 </div> 
									    </div>
									 
										<div class="ln_solid"></div>
										<div class="form-group" style="margin-left: 65%;margin-top: 2%;">
<!-- 					                        <div > -->
					                          <c:choose>
										                <c:when test="${newUniteBancaire.idUniteBancaire==0}">
										                   	<button type="submit" class="btn btn-success btn-xs source" >Créer</button>
															<spring:url value="/admin/allUniteBancaire" var="returnUrl">
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
	