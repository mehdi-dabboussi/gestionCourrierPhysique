package com.programcreek.helloworld.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

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
			return "redirect:/admin/transporteurexterne-"
					+ newTransporteurExterne.getIdTransporteurExterne();
		}

		// ************ consult transporteur externe *****************//

		@RequestMapping(value = "/admin/transporteurexterne-{idTransporteurExterne}")
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
	


}
