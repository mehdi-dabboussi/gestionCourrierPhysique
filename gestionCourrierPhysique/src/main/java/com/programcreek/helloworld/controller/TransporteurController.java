package com.programcreek.helloworld.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.itextpdf.text.pdf.PdfStructTreeController.returnType;
import com.sharing.entity.ContactExterne;
import com.sharing.entity.CoursierExterne;
import com.sharing.entity.SousContactExterne;
import com.sharing.entity.TransporteurExterne;
import com.sharing.service.CoursierExterneService;
import com.sharing.service.GlobalCrudService;
import com.sharing.service.TransporteurExterneService;


@Controller
public class TransporteurController {
	
	private GlobalCrudService globalCrudService;
	private TransporteurExterneService transporteurExterneService;
	private CoursierExterneService coursierExterneService;
	
	
	@Autowired
	public TransporteurController(GlobalCrudService globalCrudService, 
			TransporteurExterneService transporteurExterneService,
			CoursierExterneService coursierExterneService) {
		this.globalCrudService = globalCrudService;
		this.transporteurExterneService = transporteurExterneService;
		this.coursierExterneService = coursierExterneService;
	}
	
	// ------------------------Transporteur ------------------------//

		// ************ create Transporteur *****************//

		@RequestMapping(value = "/admin/createTransporteurExterne", method = RequestMethod.GET)
		public ModelAndView createTransporteurExterne() {
			ModelAndView modelAndView = new ModelAndView(
					"admin/createTransporteurExterne.jsp");
			TransporteurExterne newTransporteurExterne = new TransporteurExterne();
			modelAndView
					.addObject("newTransporteurExterne", newTransporteurExterne);
			return modelAndView;
		}

		@RequestMapping(value = "/admin/createTransporteurExterne", method = RequestMethod.POST)
		public String processCreateTransporteurExterne(
				@ModelAttribute(value = "newTransporteurExterne") TransporteurExterne newTransporteurExterne) {
			globalCrudService.save(newTransporteurExterne);
			return "redirect:/admin/transporteurExterne-"
					+ newTransporteurExterne.getIdTransporteurExterne();
		}

		// ************ consult transporteur externe *****************//

		@RequestMapping(value = "/admin/transporteurExterne-{idTransporteurExterne}")
		public ModelAndView showTransporteurExterne(
				@PathVariable("idTransporteurExterne") long idTransporteurExterne) {
			ModelAndView modelAndView = new ModelAndView(
					"admin/showTransporteurExterne.jsp");
			TransporteurExterne createdTransporteurExterne = transporteurExterneService
					.findTransporteurExterneById(idTransporteurExterne);
			modelAndView.addObject("createdTransporteurExterne",
					createdTransporteurExterne);
			modelAndView
					.addObject(
							"coursierExterne",
							coursierExterneService
									.getCoursierExternesByTransporteurExterne(createdTransporteurExterne));
			return modelAndView;
		}

		// ************ edit transporteur externe *****************//
		@RequestMapping(value = "/admin/transporteurExterne-{idTransporteurExterne}-edit", method = RequestMethod.GET)
		public ModelAndView initUpdateidTransporteurExterneForm(
				@PathVariable("idTransporteurExterne") long idTransporteurExterne) {
			ModelAndView modelAndView = new ModelAndView(
					"admin/createTransporteurExterne.jsp");
			TransporteurExterne newTransporteurExterne = this.transporteurExterneService
					.findTransporteurExterneById(idTransporteurExterne);
			modelAndView
					.addObject("newTransporteurExterne", newTransporteurExterne);
			return modelAndView;
		}

		@RequestMapping(value = "/admin/transporteurExterne-{idTransporteurExterne}-edit", method = RequestMethod.POST)
		public String processUpdateTransporteurExterneForm(
				TransporteurExterne newTransporteurExterne,
				@PathVariable("idTransporteurExterne") Long idTransporteurExterne) {
			newTransporteurExterne.setIdTransporteurExterne(idTransporteurExterne);
			this.globalCrudService.update(newTransporteurExterne);
			return "redirect:/admin/transporteurexterne-"
					+ newTransporteurExterne.getIdTransporteurExterne();
		}

		// ************ All Transporteur externe *****************//
		@RequestMapping(value = "/admin/allTransporteurExterne", method = RequestMethod.GET)
		public ModelAndView showAllTransporteurExterne() {
			ModelAndView modelAndView = new ModelAndView(
					"admin/allTransporteurs.jsp");
			List<TransporteurExterne> transporteurExternes = transporteurExterneService
					.getAllTransporteurExterne();
			modelAndView.addObject("transporteurExternes", transporteurExternes);
			return modelAndView;
		}
		
		// ************ delete transporteur externe *****************//
	
		@RequestMapping(value = "/admin/transporteurExterne-{idTransporteurExterne}/delete", method= RequestMethod.GET ) 
			public String processDeleteTransporteurExterne(
					@PathVariable("idTransporteurExterne") long  idTransporteurExterne) {
				TransporteurExterne transporteurExterne = transporteurExterneService.findTransporteurExterneById(idTransporteurExterne);
				coursierExterneService.deleteWithTransporteurExterne(transporteurExterne);
				this.globalCrudService.remove(transporteurExterne, idTransporteurExterne);
				return "redirect:/admin/allTransporteurExterne";
		}
		// ------------------------ Coursier Externe ------------------------//
		
		// ************ create Coursier Externe  *****************//
		
				@RequestMapping(value = "/admin/createCoursierExterne", method = RequestMethod.GET)
				public ModelAndView createCoursierExterne(Long idTransporteurExterne) {
					ModelAndView modelAndView = new ModelAndView("admin/createCoursierExterne.jsp");
					CoursierExterne newCoursierExterne = new CoursierExterne();
					TransporteurExterne transporteurExterne = transporteurExterneService.findTransporteurExterneById(idTransporteurExterne);
					System.out.println(transporteurExterne.getNomTransporteurExterne());
					
					modelAndView.addObject("newCoursierExterne", newCoursierExterne);
					modelAndView.addObject("Societe", transporteurExterne.getNomTransporteurExterne());
					return modelAndView;
				}
				@RequestMapping(value = "/admin/createCoursierExterne", method = RequestMethod.POST)
				public String processcreateCoursierExterne(
						@ModelAttribute(value = "newCoursierExterne") CoursierExterne newCoursierExterne,
						Long idTransporteurExterne) {
					TransporteurExterne transporteurExterne = transporteurExterneService.findTransporteurExterneById(idTransporteurExterne);
					System.out.println(transporteurExterne.getNomTransporteurExterne());
					newCoursierExterne.setTransporteurExterne(transporteurExterne);
					globalCrudService.save(newCoursierExterne);
					return "redirect:/admin/transporteurExterne-"+ newCoursierExterne.getTransporteurExterne().getIdTransporteurExterne();
				}

				// ************ consult Coursier Externe *****************//
				
				@RequestMapping(value = "/admin/coursierExterne-{idCoursierExterne}")
				public ModelAndView showSousContactExterne(
						@PathVariable("idCoursierExterne") long idCoursierExterne) {
					ModelAndView modelAndView = new ModelAndView(
							"admin/showCoursierExterne.jsp");
					CoursierExterne coursierExterne= coursierExterneService.findCoursierExterneServiceById(idCoursierExterne);
					modelAndView.addObject("createdCoursierExterne", coursierExterne);
					modelAndView.addObject("Societe", coursierExterne.getTransporteurExterne().getNomTransporteurExterne());
					return modelAndView;
				}
				
				// ************ edit Coursier Externe  *****************//

				@RequestMapping(value = "/admin/coursierExterne-{idCoursierExterne}-edit", method = RequestMethod.GET)
				public ModelAndView initUpdateCoursierExterneForm(
						@PathVariable("idCoursierExterne") long idCoursierExterne,
						Long idTransporteurExterne) {
					ModelAndView modelAndView = new ModelAndView("admin/createCoursierExterne.jsp");
					CoursierExterne newcoursierExterne = coursierExterneService.findCoursierExterneServiceById(idCoursierExterne);
					modelAndView.addObject("newcoursierExterne", newcoursierExterne);
					modelAndView.addObject("Societe", newcoursierExterne.getTransporteurExterne().getNomTransporteurExterne());
					return modelAndView;
				}

				@RequestMapping(value = "/admin/coursierExterne-{idCoursierExterne}-edit", method = RequestMethod.POST)
				public String processUpdateCoursierExterneeForm(
						CoursierExterne newcoursierExterne,
						@PathVariable("idCoursierExterne") long idCoursierExterne,
						Long idTransporteurExterne) {
					newcoursierExterne.setIdCoursierExterne(idCoursierExterne);
					TransporteurExterne transporteurExterne = transporteurExterneService.findTransporteurExterneById(idTransporteurExterne);
					newcoursierExterne.setTransporteurExterne(transporteurExterne);
					this.globalCrudService.update(newcoursierExterne);
					return "redirect:/admin/transporteurExterne-"
							+ newcoursierExterne.getTransporteurExterne().getIdTransporteurExterne();
				}
				
				
				// ************ deleteCoursier Externe *****************//
				
						@RequestMapping(value = "/admin/coursierExterne-{idCoursierExterne}/delete", method = RequestMethod.GET)
						public String processDeleteCoursierExterne(@PathVariable("idCoursierExterne") long idCoursierExterne, Long idTransporteurExterne) 
						{
							CoursierExterne coursierExterne = coursierExterneService.findCoursierExterneServiceById(idCoursierExterne);
							this.globalCrudService.remove(coursierExterne, idCoursierExterne);
							return "redirect:/admin/coursierExterne-" + idTransporteurExterne;
						}
		}




