package com.programcreek.helloworld.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.sharing.entity.Nature;
import com.sharing.entity.UniteBancaire;
import com.sharing.service.GlobalCrudService;
import com.sharing.service.NatureService;

@Controller
public class NatureController {
	
	private GlobalCrudService globalCrudService;
	private NatureService natureService;
	
	@Autowired
	public NatureController(GlobalCrudService globalCrudService,
			NatureService natureService) {
		this.globalCrudService = globalCrudService;
		this.natureService = natureService;
	}
	
	// ************ create nature *****************//
	@RequestMapping(value = "/bo/newNature", method=RequestMethod.GET)
	public ModelAndView addNature()
	{
		ModelAndView modelAndView = new ModelAndView("bo/newNature.jsp");
		Nature newNature = new Nature();
		modelAndView.addObject("newNature", newNature);
		return modelAndView;
	}
	
	
	@RequestMapping(value = "/bo/newNature", method=RequestMethod.POST)
	public String processAddNature(@ModelAttribute(value ="newNature") Nature nature)
	{
		globalCrudService.save(nature);
		return "redirect:/bo/allNature";
	}
	
	// ************ All nature *****************//
			@RequestMapping(value = "/bo/allNature", method = RequestMethod.GET)
			public ModelAndView showAllNature() {
				ModelAndView modelAndView = new ModelAndView("bo/allNature.jsp");
				List<Nature> natures = natureService
						.getAllNature();
				modelAndView.addObject("natures", natures);
				return modelAndView;
			}
	
			// ************ delete nature *****************//

			@RequestMapping(value = "/bo/nature-{idNature}/delete", method = RequestMethod.GET)
			public String processDeleteNature(
					@PathVariable("idNature") long idNature) {
				Nature nature = natureService.findNatureById(idNature);
				globalCrudService.remove(nature, idNature);
				return "redirect:/bo/allNature";
			}
	

}
