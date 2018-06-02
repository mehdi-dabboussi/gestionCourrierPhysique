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
<jsp:include page="../newTemplate/staticFiles2.jsp" />
<!-- /css files -->




<head>
<title> ${ville} </title>
</head>




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
				<div class="col-md-12 col-sm-12 col-xs-12">
					<div class="x_panel">
						<div class="x_title">
							<h2>
								Bordereau ${ville}
							</h2>
							
							<div class="clearfix"></div>
						</div>
						
						<div class="x_content">
						<form:form class="form-horizontal form-label-leftr" 
											data-toggle="validator" role="form" >
						<div class="item form-group ">
										     <label for="inputTransporteurLab"  class="control-label col-md-3 col-sm-3 col-xs-12">Choisir le transporteur<em>*</em></label>	
										      <div class="col-md-6 col-sm-6 col-xs-12">
												<select name="transporteur" class="select2_single nature form-control" tabindex="-1" required>
													<option ></option>
												<c:forEach var="transporteur" items="${transporteurs}">
													<option value="${transporteur.idTransporteurExterne}">${transporteur.nomTransporteurExterne}</option>
												</c:forEach>
												</select>
												<div class="help-block with-errors"></div>
											 </div>
									    </div>
						
						
							<table id="datatable-buttons" class="table table-striped table-bordered">
								  <thead>
								    <tr>
								      <th>
								      <div class="checkbox">
								      <input type="checkbox" id="checkBoxAll" /></th>
								      </div>
								      <th>Id courrier </th>
								      <th>Objet</th> 
								      <th>Destinataire</th>
								      <th>Transfert vers</th>
								      <th>Date de transfert </th>
								      <th>Estimation</th>
								      
								    </tr>
								  </thead>
								<c:forEach var="transfertToShow" items="${transferts}">
									  	<tr>
									  	<td> 
									  	<div class="checkbox">
									  	<input class="chkCheckBoxId" type="checkbox" value="${transfertToShow.idTransfert}" name="idTransfert" /> </td>
									    </div>  
									      <td >${transfertToShow.courrier.idCourrier}</td>
									      <td >${transfertToShow.courrier.objetCourrier}</td>
									      <td >${transfertToShow.courrier.destinataire.nom}</td>
									      <td>
									      <c:if test="${transfertToShow.destinataireType == 'unite'}">${transfertToShow.destinataireUnite.nom}</c:if>
							    	      <c:if test="${transfertToShow.destinataireType == 'contact'}">${transfertToShow.destinataireContact.nom}</c:if> 
										</td>
									      <td >${transfertToShow.dateTransfert}</td>
									      <td>${transfertToShow.estimation} Heure</td>
					                	 
									    </tr>
								  </c:forEach>
							</table>
                						  
                			<div class="ln_solid"></div>
										<div class="form-group" style="margin-left: 95%;margin-top: 2%;">
<!-- 					                        <div > -->
										                   	<button type="submit" class="btn btn-success btn-xs source" >Confirmer</button>
<!-- 					                        </div> -->
				                       </div>
                						  </form:form>
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
	<script>
      $(document).ready(function() {
        var handleDataTableButtons = function() {
          if ($("#datatable-buttons").length) {
            $("#datatable-buttons").DataTable({
              dom: "Bfrtip",
              buttons: [
                {
                  extend: "print",
                  className: "btn-sm"
                }
              ],
              responsive: true
            });
          }
        };

        TableManageButtons = function() {
          "use strict";
          return {
            init: function() {
              handleDataTableButtons();
            }
          };
        }();

        $('#datatable').dataTable();

        $('#datatable-keytable').DataTable({
          keys: true
        });

        $('#datatable-responsive').DataTable();

        $('#datatable-scroller').DataTable({
          ajax: "js/datatables/json/scroller-demo.json",
          deferRender: true,
          scrollY: 380,
          scrollCollapse: true,
          scroller: true
        });

        $('#datatable-fixed-header').DataTable({
          fixedHeader: true
        });

        var $datatable = $('#datatable-checkbox');

        $datatable.dataTable({
          'order': [[ 1, 'asc' ]],
          'columnDefs': [
            { orderable: false, targets: [0] }
          ]
        });
        $datatable.on('draw.dt', function() {
          $('input').iCheck({
            checkboxClass: 'icheckbox_flat-green'
          });
        });

        TableManageButtons.init();
      });
    </script>

	 <!-- Datatables -->
	 
	 <script>
	 	$(document).ready(function(){
	 		$('#checkBoxAll').click(function(){
	 			if ($(this).is(":checked"))
	 				$('.chkCheckBoxId').prop('checked', true);
	 			else
	 				$('.chkCheckBoxId').prop('checked', false);
	 		});
	 	});
	 </script>
	 
	<!-- /Js files -->
</body>
</html>
