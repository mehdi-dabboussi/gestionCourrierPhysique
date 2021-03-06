package com.programcreek.helloworld.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.sharing.entity.Langue;
import com.sharing.service.GlobalCrudService;
import com.sharing.service.LangueService;

@Controller
public class LangueController {

	private LangueService langueService;
	private GlobalCrudService globalCrudService;
	
	
	@Autowired
	public LangueController(LangueService langueService, GlobalCrudService globalCrudService) {
		this.langueService = langueService;
		this.globalCrudService = globalCrudService;
	}
	
	// ************ create langue *****************//
		@RequestMapping(value = "/bo/newLangue", method=RequestMethod.GET)
		public ModelAndView addNature()
		{
			ModelAndView modelAndView = new ModelAndView("bo/newLangue.jsp");
			Langue newLangue = new Langue();
			modelAndView.addObject("newLangue", newLangue);
			return modelAndView;
		}
		
		
		@RequestMapping(value = "/bo/newLangue", method=RequestMethod.POST)
		public String processAddNature(@ModelAttribute(value ="newLangue") Langue langue)
		{
			globalCrudService.save(langue);
			return "redirect:/bo/allLangue";
		}
		
		// ************ All langue *****************//
				@RequestMapping(value = "/bo/allLangue", method = RequestMethod.GET)
				public ModelAndView showAllNature() {
					ModelAndView modelAndView = new ModelAndView("bo/allLangue.jsp");
					List<Langue> langues = langueService
							.getAllLangue();
					modelAndView.addObject("langues", langues);
					return modelAndView;
				}
		
				// ************ delete langue *****************//

				@RequestMapping(value = "/bo/langue-{idlangue}/delete", method = RequestMethod.GET)
				public String processDeleteNature(
						@PathVariable("idlangue") long idlangue) {
					Langue langue = langueService.findLangueById(idlangue);
					globalCrudService.remove(langue, idlangue);
					return "redirect:/bo/allLangue";
				}
	
}
