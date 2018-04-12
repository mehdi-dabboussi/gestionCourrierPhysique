package com.programcreek.helloworld.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.sharing.entity.ContactExterne;
import com.sharing.entity.Courrier;
import com.sharing.entity.Langue;
import com.sharing.entity.Nature;
import com.sharing.entity.UniteBancaire;
import com.sharing.entity.User;
import com.sharing.service.ContactExterneService;
import com.sharing.service.CourrierService;
import com.sharing.service.GlobalCrudService;
import com.sharing.service.LangueService;
import com.sharing.service.NatureService;
import com.sharing.service.UniteBancaireService;
import com.sharing.service.UserService;


@Controller
public class CourrierController {
	
	private GlobalCrudService globalCrudService;
	private CourrierService courrierService;
	private LangueService langueService;
	private NatureService natureService;
	private UserService userService;
	private UniteBancaireService uniteBancaireService;
	private ContactExterneService contactExterneService;
	
	
	@Autowired
	public CourrierController(GlobalCrudService globalCrudService,CourrierService courrierService,
			LangueService langueService, NatureService natureService, UserService userService,
			UniteBancaireService uniteBancaireService, ContactExterneService contactExterneService){
		this.globalCrudService = globalCrudService;
		this.courrierService = courrierService;
		this.langueService = langueService;
		this.natureService = natureService;
		this.userService = userService;
		this.uniteBancaireService = uniteBancaireService;
		this.contactExterneService = contactExterneService;
	}
	
	
	@RequestMapping(value="/bo/newCourrier", method=RequestMethod.GET)
	public ModelAndView createCourrier(){
		ModelAndView modelAndView = new ModelAndView("bo/createCourrier.jsp");
		Courrier newCourrier = new Courrier();
		modelAndView.addObject("newCourrier", newCourrier);
		List<Langue> langues = langueService.getAllLangue();
		List<Nature> natures = natureService.getAllNature();
		List<User> users = userService.getAllUsers();
		modelAndView.addObject("langues", langues);
		modelAndView.addObject("natures", natures);
		modelAndView.addObject("users", users);
		
		List<UniteBancaire> uniteBancaires = uniteBancaireService.getAllUniteBancaire();
		modelAndView.addObject("uniteBancaires", uniteBancaires);
		
		List<ContactExterne> contactExternes = contactExterneService.getAllContactExterne();
		modelAndView.addObject("contactExternes", contactExternes);
		
		return modelAndView;
	}
	
}
