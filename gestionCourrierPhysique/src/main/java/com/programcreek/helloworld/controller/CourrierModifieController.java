package com.programcreek.helloworld.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.sharing.entity.Courrier;
import com.sharing.entity.CourrierModifie;
import com.sharing.entity.CourrierSupprime;
import com.sharing.entity.Transfert;
import com.sharing.service.CourrierModifieService;
import com.sharing.service.CourrierService;
import com.sharing.service.CourrierSupprimeService;


@Controller
public class CourrierModifieController {

	
	@Autowired
	private CourrierModifieService courrierModifieService;
	
	@Autowired
	private CourrierService courrierService;
	
	//all courriers modifiés
	@RequestMapping(value ="/admin/allCourrierModifie", method=RequestMethod.GET)
	public ModelAndView showAllCourrierModifie(){
		ModelAndView  modelAndView = new ModelAndView("admin/allCourrierModifie.jsp");
		
		List<CourrierModifie> courrierModifies = courrierModifieService.getAll();
		modelAndView.addObject("courrierModifies", courrierModifies);
		
		return modelAndView;
	}
	
	
	//consult courrier modifié
		@RequestMapping(value = "/admin/courrierModif-{idCourrier}")
		public ModelAndView showUserAfterCreate(@PathVariable("idCourrier") long idCourrier) {
			
			ModelAndView modelAndView = new ModelAndView(
					"admin/showCourrierModifie.jsp");
			
			CourrierModifie courrierModifie = courrierModifieService.findCourrierModifieById(idCourrier);
			
			Courrier courrier = courrierService.findCourrierById(idCourrier);
			
			modelAndView.addObject("newCourrier", courrier);
			modelAndView.addObject("courrierModifie", courrierModifie);
			
			return modelAndView;
		}
	
	
}
