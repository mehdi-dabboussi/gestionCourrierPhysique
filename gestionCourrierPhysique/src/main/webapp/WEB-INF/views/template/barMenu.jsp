<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!--slider menu-->
   <div class="sidebar-menu" >
		  	<div class="logo"> <a class="sidebar-icon"> <span class="fa fa-bars"></span> </a> <a > <span id="logo" ></span> 
			      <!--<img id="logo" src="" alt="Logo"/>--> 
			      <!--<img src="images/UBCI2.jpg" alt="">--> 
			  </a> </div>			  
		    <div class="menu">
		      <ul id="menu" >
		      <c:set var="contextPath" value="${pageContext.request.contextPath}"/>
		      	<li id="menu-home"><a href="${contextPath}/recouvrement/allCreancesAmiables" data-toggle="tooltip" title="Gestion des Recouvrements"><i class="fa fa-eur" ></i><span>Recouvrement Amiable</span></a></li>
		       
		        <li id="menu-home"><a href="${contextPath}/recouvrement/allCreancesContentieux" data-toggle="tooltip" title="Gestion des Recouvrements"><i class="fa fa-gavel"></i><span>Recouvrement Contentieux</span></a></li>
		        
		        <li id="menu-home"><a href="${contextPath}/recouvrement/allCreancesSocRecv" data-toggle="tooltip" title="Gestion des Recouvrements"><i class="fa fa-suitcase" ></i><span>Recouvrement Soc.Rrecv</span></a></li>
		        
		        <li id="menu-home"><a href="#"><i class="fa fa-cog"></i><span>Paramètres</span><span class="fa fa-angle-right" style="float: right;"></span></a>
		          <ul id="menu">
		            <li  id="menu-home"><a href="${contextPath}/client/allClients" data-toggle="tooltip" title="Gestion des Clients"><i class="fa fa-users" ></i><span>Gestion des Clients</span></a></li>
		            <li  id="menu-home"><a href="${contextPath}/avocatHuissier/allAvocatHuissiers" data-toggle="tooltip" title="Gestion des Avocats/Huissier"><i class="fa fa-balance-scale" ></i><span>Avocat / Huissier Notaire</span></a></li>		            
		        	<li  id="menu-home"><a href="${contextPath}/societeRecouvrement/allSocieteRecouvrements" data-toggle="tooltip" title="Gestion des sociétés de Recouvrement"><i class="fa fa-building-o" ></i><span>Sociétés de Recouvrement</span></a></li>
		          </ul>
		        </li>
		      </ul>
		    </div>
	 </div>
