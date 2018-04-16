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
								<h2> <c:if test="${newCourrier.idCourrier==0}">Nouveau </c:if>Courrier</h2>
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
											data-toggle="validator" role="form" modelAttribute="newCourrier" >
								         <div class="item form-group">
									        <label for="EtatLab"  class="control-label col-md-3 col-sm-3 col-xs-12">Etat<em>*</em></label>	
									        <div class="col-md-6 col-sm-6 col-xs-12">
												<select name="etat" class="form-control" required>
												<option value="depart" selected="selected">Départ</option>
												<option value="arrive" >Arrivé</option>
												</select>
												<div class="help-block with-errors"></div>
											</div>
									     </div>
										<div class="item form-group">
										     <label for="inputObjetLab"  class="control-label col-md-3 col-sm-3 col-xs-12">Objet<em>*</em></label>
										     <div class="col-md-6 col-sm-6 col-xs-12">
												 <input type="text" class="form-control col-md-7 col-xs-12" id="inputNom" placeholder="nom"
								                		name="nomSousContactExterne" value="${newCourrier.objetCourrier}" required> 
											 </div>
										</div>
										<div class="item form-group">
										     <label for="inputDetailLab"  class="control-label col-md-3 col-sm-3 col-xs-12">Details<em>*</em></label>
										     <div class="col-md-6 col-sm-6 col-xs-12">
												 <textarea type="detail" class="form-control col-md-7 col-xs-12" id="inputDetail" rows="3" placeholder="Détails"
												 		name="detailsCourrier" value="${newCourrier.detailsCourrier}" required ></textarea>
											 </div>
										</div>
										
										<div class="item form-group ">
										     <label for="inputNatureLab"  class="control-label col-md-3 col-sm-3 col-xs-12">Nature<em>*</em></label>	
										      <div class="col-md-6 col-sm-6 col-xs-12">
												<select name="natureC" class="select2_single nature form-control" tabindex="-1" required>
													<option value="${newCourrier.nature.idNature}">${newCourrier.nature.libelleNature}</option>
												<c:forEach var="nature" items="${natures}">
													<option value="${nature.idNature}">${nature.libelleNature}</option>
												</c:forEach>
												</select>
												<div class="help-block with-errors"></div>
											 </div>
									    </div>
									    
									    <div class="item form-group ">
										     <label for="inputLangueLab"  class="control-label col-md-3 col-sm-3 col-xs-12">Langue<em>*</em></label>	
										      <div class="col-md-6 col-sm-6 col-xs-12 has-feedback">
												<select name="langueC" class="select2_single langue form-control" tabindex="-1" required>
													<option value="${newCourrier.langue.idLangue}">${newCourrier.langue.libelleLangue}</option>
												<c:forEach var="langue" items="${langues}">
													<option value="${langue.idLangue}">${langue.libelleLangue}</option>
												</c:forEach>
												</select>
												<span class="fa fa-language form-control-feedback right" aria-hidden="true"></span>
												<div class="help-block with-errors"></div>
											 </div>
									    </div>
									    
									    <div class="form-group">
											 <label for="inputEmetteurLab"  class="control-label col-md-3 col-sm-3 col-xs-12">Emetteur<em>*</em></label>	
											 <div class="col-md-6 col-sm-6 col-xs-12">
											 <div class="radio">
													<label> <input type="radio" value="user" name="emetteur"  required id="emetuser">
														utilisateur
													</label>
												</div>
												<div class="radio">
													<label> <input type="radio" 
													value="unite" name="emetteur" required id="emetunite">
														unité bancaire
													</label>
												</div>
												<div class="radio">
													<label> <input type="radio" 
													value="contact" name="emetteur" required id="emetcontact"> 
														contact externe
													</label>
												</div>
											</div>
										</div>
										
									    <div class="item form-group " id="usersemet" >
										     <label for="inputEmetteurLab"  class="control-label col-md-3 col-sm-3 col-xs-12"><em></em></label>	
										      <div class="col-md-6 col-sm-6 col-xs-12 has-feedback" >
												<select name="emetteurUser" class="select2_single emetteur form-control" tabindex="-1" required id="selectEmetUser">
													<!--  <option value="${newCourrier.emetteurUser.idUser}">${newCourrier.emetteurUser.userName} </option>-->
												<c:forEach var="user" items="${users}">
													<option value="${user.idUser}">${user.surName} ${user.userName}</option>
												</c:forEach>
												</select>
												<span class="fa fa-user form-control-feedback right" aria-hidden="true"></span>
												<div class="help-block with-errors"></div>
											 </div>
									    </div>
									    
									    <div class="item form-group " id="unitesemet" >
										     <label for="inputEmetteurLab"  class="control-label col-md-3 col-sm-3 col-xs-12"><em></em></label>	
										      <div class="col-md-6 col-sm-6 col-xs-12 has-feedback" >
												<select name="emetteurUnite" class="select2_single emetteur form-control" tabindex="-1" required id="selectEmetUnite">
													<!--  <option value="${newCourrier.emetteurUnite.idUniteBancaire}">${newCourrier.emetteurUnite.nomUniteBancaire} </option>-->
												<c:forEach var="unite" items="${uniteBancaires}">
													<option value="${unite.idUniteBancaire}">${unite.nomUniteBancaire}</option>
												</c:forEach>
												</select>
												<span class="fa fa-home form-control-feedback right" aria-hidden="true"></span>
												<div class="help-block with-errors"></div>
											 </div>
									    </div>
									    
									    <div class="item form-group " id="contactsemet" >
										     <label for="inputEmetteurLab"  class="control-label col-md-3 col-sm-3 col-xs-12"><em></em></label>	
										      <div class="col-md-6 col-sm-6 col-xs-12 has-feedback" >
												<select name="emetteurContactExterne" class="select2_single emetteur form-control" tabindex="-1" required id="selectEmetContact">
													<!--  <option value="${newCourrier.emetteurContact.idContactExterne}">${newCourrier.emetteurContact.nomContactExterne} </option>-->
												<c:forEach var="contact" items="${contactExternes}">
													<option value="${contact.idContactExterne}">${contact.nomContactExterne}</option>
												</c:forEach>
												</select>
												<span class="fa fa-home form-control-feedback right" aria-hidden="true"></span>
												<div class="help-block with-errors"></div>
											 </div>
									    </div>
									    
									    <div class="form-group">
											 <label for="inputEmetteurLab"  class="control-label col-md-3 col-sm-3 col-xs-12">Destinataire<em>*</em></label>	
											 <div class="col-md-6 col-sm-6 col-xs-12">
											 <div class="radio">
													<label> <input type="radio" value="user" name="destinataire" required id="destuser">
														utilisateur
													</label>
												</div>
												<div class="radio">
													<label> <input type="radio" 
													value="unite" name="destinataire" required id="destunite">
														unité bancaire
													</label>
												</div>
												<div class="radio">
													<label> <input type="radio" 
													value="contact" name="destinataire" required id="destcontact">
														contact externe
													</label>
												</div>
											</div>
										</div>
									    
									    <div class="item form-group " id="usersdest">
										     <label for="inputDestinataireLab"  class="control-label col-md-3 col-sm-3 col-xs-12"><em></em></label>	
										      <div class="col-md-6 col-sm-6 col-xs-12 has-feedback">
												<select name="destinataireUser" class="select2_single destinataire form-control" tabindex="-1" required id="selectDestUser">
													<!--  <option value="${newCourrier.destinataireUser.idUser}">${newCourrier.destinataireUser.userName} </option>-->
												<c:forEach var="user" items="${users}">
													<option value="${user.idUser}">${user.surName} ${user.userName}</option>
												</c:forEach>
												</select>
												<span class="fa fa-user form-control-feedback right" aria-hidden="true"></span>
												<div class="help-block with-errors"></div>
											 </div>
									    </div>
									    
									    <div class="item form-group " id="unitesdest">
										     <label for="inputDestinataireLab"  class="control-label col-md-3 col-sm-3 col-xs-12"><em></em></label>	
										      <div class="col-md-6 col-sm-6 col-xs-12 has-feedback">
												<select name="destinataireUnite" class="select2_single destinataire form-control" tabindex="-1" required id="selectDestUnite">
													<!--  <option value="${newCourrier.destinataireUnite.idUniteBancaire}">${newCourrier.destinataireUnite.nomUniteBancaire} </option>-->
												<c:forEach var="unite" items="${uniteBancaires}">
													<option value="${unite.idUniteBancaire}">${unite.nomUniteBancaire}</option>
												</c:forEach>
												</select>
												<span class="fa fa-user form-control-feedback right" aria-hidden="true"></span>
												<div class="help-block with-errors"></div>
											 </div>
									    </div>
									    
									    <div class="item form-group " id="contactsdest">
										     <label for="inputDestinataireLab"  class="control-label col-md-3 col-sm-3 col-xs-12"><em></em></label>	
										      <div class="col-md-6 col-sm-6 col-xs-12 has-feedback">
												<select name="destinataireContact" class="select2_single destinataire form-control" tabindex="-1" required id="selectDestContact">
													<!--  <option value="${newCourrier.destinataireContact.idContactExterne}">${newCourrier.destinataireContact.nomContactExterne} </option>-->
												<c:forEach var="contact" items="${contactExternes}">
													<option value="${contact.idContactExterne}">${contact.nomContactExterne}</option>
												</c:forEach>
												</select>
												<span class="fa fa-user form-control-feedback right" aria-hidden="true"></span>
												<div class="help-block with-errors"></div>
											 </div>
									    </div>
										
									 
										<div class="ln_solid"></div>
										<div class="form-group" style="margin-left: 65%;margin-top: 2%;">
<!-- 					                        <div > -->
					                          <c:choose>
										                <c:when test="${newCourrier.idCourrier==0}">
										                   	<button type="submit" class="btn btn-success btn-xs source" >Créer</button>
															<spring:url value="/admin/allContactExterne" var="returnUrl">
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
      
      <script>
      
      $(document).ready(function(){
    	  $('#usersemet').hide();
    	  $('#unitesemet').hide();
    	  $('#contactsemet').hide();
    	  
    	  $('#usersdest').hide();
    	  $('#unitesdest').hide();
    	  $('#contactsdest').hide();
    	  
    	  $('#emetuser').click(function(){
    		  $('#unitesemet').hide();
    		  $('#contactsemet').hide();
    		  $('#usersemet').show();
    		  $('#selectEmetUnite').prop("required", false);
    		  $('#selectEmetContact').prop("required", false);
    	  });
    	  $('#emetunite').click(function(){
    		  $('#usersemet').hide();
    		  $('#contactsemet').hide();
    		  $('#unitesemet').show();
    		  $('#selectEmetUser').prop("required", false);
    		  $('#selectEmetContact').prop("required", false);
    	  });
    	  $('#emetcontact').click(function(){
    		  $('#usersemet').hide();
    		  $('#unitesemet').hide();
    		  $('#contactsemet').show();
    		  $('#selectEmetUnite').prop("required", false);
    		  $('#selectEmetUser').prop("required", false);
    	  });
    	  
    	  $('#destuser').click(function(){
    		  $('#unitesdest').hide();
        	  $('#contactsdest').hide();
    		  $('#usersdest').show();
    		  $('#selectDestUnite').prop("required", false);
    		  $('#selectDestContact').prop("required", false);
    	  });
    	  
    	  $('#destunite').click(function(){
    		  $('#usersdest').hide();
        	  $('#contactsdest').hide();
    		  $('#unitesdest').show();
    		  $('#selectDestUser').prop("required", false);
    		  $('#selectDestContact').prop("required", false);
    	  });
    	  
    	  $('#destcontact').click(function(){
    		  $('#unitesdest').hide();
        	  $('#usersdest').hide();
    		  $('#contactsdest').show();
    		  $('#selectDestUnite').prop("required", false);
    		  $('#selectDestUser').prop("required", false);
    	  });
    	  
      });
      
      </script>
    
    
    <!-- /validator -->
</body>
</html>
	