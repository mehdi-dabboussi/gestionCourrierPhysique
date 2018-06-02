 	 <!DOCTYPE html>

<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="datatables"
	uri="http://github.com/dandelion/datatables"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="myfn" uri="/WEB-INF/custom-functions.tld"%>

<html lang="en">
<!-- css files -->
<jsp:include page="../newTemplate/staticFiles.jsp" />
<!-- /css files -->


<function> <name>notNull</name> <function-class>com.sharing.ServiceJavaScript</function-class>
<function-signature>boolean notNull(java.lang.Object)</function-signature> </function>

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
								<h2>
									<c:if test="${newTransfert.idTransfert==0}"> Nouveau </c:if>
									Transfert
								</h2>
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
									data-toggle="validator" role="form"
									modelAttribute="newCourrier">
									
									<div class="item form-group">
										<label for="inputObjetLab"
											class="control-label col-md-3 col-sm-3 col-xs-12">
											Estimation ( par heure ) <em>*</em>
										</label>
										<div class="col-md-6 col-sm-6 col-xs-12">
											<input type="number" class="form-control col-md-7 col-xs-12"
												id="inputNom" placeholder="Estimation en heure" name="estimation"
												value="${newTransfert.estimation}" required>
										</div>
									</div>

									<div class="form-group">
										<label for="inputEmetteurLab"
											class="control-label col-md-3 col-sm-3 col-xs-12">
											Destinataire<em>*</em>
										</label>
										<div class="col-md-6 col-sm-6 col-xs-12">
											<div class="radio">
												<label> <input type="radio"
													<c:if test="${ myfn:notNull( newTransfert.destinataireUnite ) }">checked="checked"</c:if>
													value="unite_dest" name="destinataire" required
													id="destunite"> unité bancaire
												</label>
											</div>
											<div class="radio">
												<label> <input type="radio"
													<c:if test="${ myfn:notNull( newTransfert.destinataireContact ) }">checked="checked"</c:if>
													value="contact_dest" name="destinataire" required
													id="destcontact"> contact externe
												</label>
											</div>
										</div>
									</div>

									<div class="item form-group " id="unitesdest">
										<label for="inputDestinataireLab"
											class="control-label col-md-3 col-sm-3 col-xs-12"><em></em></label>
										<div class="col-md-6 col-sm-6 col-xs-12 has-feedback">
											<select name="destinataireUnite"
												class="select2_single destinataire form-control"
												tabindex="-1" required id="selectDestUnite">
												<option
													value="${newTransfert.destinataireUnite.idUniteBancaire}">${newTransfert.destinataireUnite.nom}</option>
												<c:forEach var="unite" items="${uniteBancaires}">
													<option value="${unite.id}">${unite.nom}</option>
												</c:forEach>
											</select> <span class="fa fa-user form-control-feedback right"
												aria-hidden="true"></span>
											<div class="help-block with-errors"></div>
										</div>
									</div>

									<div class="item form-group " id="contactsdest">
										<label for="inputDestinataireLab"
											class="control-label col-md-3 col-sm-3 col-xs-12"><em></em></label>
										<div class="col-md-6 col-sm-6 col-xs-12 has-feedback">
											<select name="destinataireContact"
												class="select2_single destinataire form-control"
												tabindex="-1" required id="selectDestContact">
												<option
													value="${newTransfert.destinataireContact.id}">${newTransfert.destinataireContact.nom}</option>
												<c:forEach var="contact" items="${contactExternes}">
													<option value="${contact.id}">${contact.nom}</option>
												</c:forEach>
											</select> <span class="fa fa-user form-control-feedback right"
												aria-hidden="true"></span>
											<div class="help-block with-errors"></div>
										</div>
									</div>
									


									<div class="ln_solid"></div>
									<div class="form-group"
										style="margin-left: 65%; margin-top: 2%;">
										<!-- 					                        <div > -->
										<c:choose>
											<c:when test="${newTransfert.idTransfert==0}">
												<button type="submit" class="btn btn-success btn-xs source">Créer</button>
												<spring:url value="/bo/allCourriers" var="returnUrl">
												</spring:url>
												<a href="${fn:escapeXml(returnUrl)}"
													class="btn btn-danger btn-xs"> Annuler </a>
											</c:when>
											<c:otherwise>
												<button type="submit" class="btn btn-success btn-xs source">Enregistrer</button>
												<a class="btn btn-danger btn-xs"
													href="<c:url value="javascript:history.go(-1)" />">
													Annuler </a>
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
		$('form').on('blur',
				'input[required], input.optional, select.required',
				validator.checkField).on('change', 'select.required',
				validator.checkField).on('keypress',
				'input[required][pattern]', validator.keypress);

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
		$(document).ready(function() {
			$('#usersemet').hide();
			$('#unitesemet').hide();
			$('#contactsemet').hide();

			$('#usersdest').hide();
			$('#unitesdest').hide();
			$('#contactsdest').hide();

			if ($('#emetuser').is(':checked')) {
				$('#unitesemet').hide();
				$('#contactsemet').hide();
				$('#usersemet').show();
				$('#selectEmetUnite').prop("required", false);
				$('#selectEmetContact').prop("required", false);
			}

			$('#emetuser').click(function() {
				$('#unitesemet').hide();
				$('#contactsemet').hide();
				$('#usersemet').show();
				$('#selectEmetUnite').prop("required", false);
				$('#selectEmetContact').prop("required", false);
			});

			if ($('#emetunite').is(':checked')) {
				$('#usersemet').hide();
				$('#contactsemet').hide();
				$('#unitesemet').show();
				$('#selectEmetUser').prop("required", false);
				$('#selectEmetContact').prop("required", false);
			}

			$('#emetunite').click(function() {
				$('#usersemet').hide();
				$('#contactsemet').hide();
				$('#unitesemet').show();
				$('#selectEmetUser').prop("required", false);
				$('#selectEmetContact').prop("required", false);
			});

			if ($('#emetcontact').is(':checked')) {
				$('#usersemet').hide();
				$('#unitesemet').hide();
				$('#contactsemet').show();
				$('#selectEmetUnite').prop("required", false);
				$('#selectEmetUser').prop("required", false);
			}

			$('#emetcontact').click(function() {
				$('#usersemet').hide();
				$('#unitesemet').hide();
				$('#contactsemet').show();
				$('#selectEmetUnite').prop("required", false);
				$('#selectEmetUser').prop("required", false);
			});

			if ($('#destuser').is(':checked')) {
				$('#unitesdest').hide();
				$('#contactsdest').hide();
				$('#usersdest').show();
				$('#selectDestUnite').prop("required", false);
				$('#selectDestContact').prop("required", false);
			}

			$('#destuser').click(function() {
				$('#unitesdest').hide();
				$('#contactsdest').hide();
				$('#usersdest').show();
				$('#selectDestUnite').prop("required", false);
				$('#selectDestContact').prop("required", false);
			});

			if ($('#destunite').is(':checked')) {
				$('#usersdest').hide();
				$('#contactsdest').hide();
				$('#unitesdest').show();
				$('#selectDestUser').prop("required", false);
				$('#selectDestContact').prop("required", false);
			}

			$('#destunite').click(function() {
				$('#usersdest').hide();
				$('#contactsdest').hide();
				$('#unitesdest').show();
				$('#selectDestUser').prop("required", false);
				$('#selectDestContact').prop("required", false);
			});

			if ($('#destcontact').is(':checked')) {
				$('#unitesdest').hide();
				$('#usersdest').hide();
				$('#contactsdest').show();
				$('#selectDestUnite').prop("required", false);
				$('#selectDestUser').prop("required", false);
			}

			$('#destcontact').click(function() {
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
