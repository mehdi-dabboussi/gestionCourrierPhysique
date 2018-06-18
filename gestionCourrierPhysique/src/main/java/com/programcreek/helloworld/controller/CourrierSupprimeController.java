package com.programcreek.helloworld.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.sharing.entity.Courrier;
import com.sharing.entity.CourrierSupprime;
import com.sharing.entity.Transfert;
import com.sharing.service.CourrierSupprimeService;


@Controller
public class CourrierSupprimeController {

	
	@Autowired
	private CourrierSupprimeService courrierSupprimeService;
	
	//all courriers supprimés
	@RequestMapping(value ="/admin/allCourrierSupprime", method=RequestMethod.GET)
	public ModelAndView showAllCourrierSupprime(){
		ModelAndView  modelAndView = new ModelAndView("admin/allCourrierSupprime.jsp");
		
		List<CourrierSupprime> courrierSupprimes = courrierSupprimeService.getAll();
		modelAndView.addObject("courrierSupprimes", courrierSupprimes);
		
		return modelAndView;
	}
	
	
	//consult courrier supprimé
		@RequestMapping(value = "/admin/courrier-{idCourrier}")
		public ModelAndView showUserAfterCreate(@PathVariable("idCourrier") long idCourrier) {
			
			ModelAndView modelAndView = new ModelAndView(
					"admin/showCourrierSupprime.jsp");
			
			CourrierSupprime courrierSupprime = courrierSupprimeService.findCourrierSupprimeById(idCourrier);
			modelAndView.addObject("courrierSupprime", courrierSupprime);
			
			return modelAndView;
		}
	
	
}
