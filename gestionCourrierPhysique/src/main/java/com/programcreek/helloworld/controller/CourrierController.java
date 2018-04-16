package com.programcreek.helloworld.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
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
		System.out.println(newCourrier);
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
	
	/*@RequestMapping(value="/bo/newCourrier", method=RequestMethod.POST)
	public String processCreateCourrier(@ModelAttribute("newCourrier") Courrier newCourrier, BindingResult bindingResult, String emetteur,
			long emetteurUser, Long emetteurUnite, Long emetteurContactExterne,
			String destinataire, long destinataireUser, Long destinataireUnite, Long destinataireContact,
			Long natureC, Long langueC, String etat){
		for( FieldError fieldError : bindingResult.getFieldErrors() )
		    System.out.println(fieldError.getField() +" : "+fieldError.getDefaultMessage());
		System.out.println(destinataire);
		System.out.println(emetteur);
		System.out.println(etat);
		return "redirect:/bo/allContactExterne";
	}*/
	
	@RequestMapping(value="/bo/newCourrier", method=RequestMethod.POST)
	public String processCreateCourrier(
			@ModelAttribute("newCourrier") Courrier newCourrier, BindingResult bindingResult, String emetteur,
			long emetteurUser, Long emetteurUnite, Long emetteurContactExterne,
			String destinataire, long destinataireUser, Long destinataireUnite, Long destinataireContact,
			Long natureC, Long langueC, String etat, String objetCourrier){
		for( FieldError fieldError : bindingResult.getFieldErrors() )
		    System.out.println(fieldError.getField() +" : "+fieldError.getDefaultMessage());
		
		
		System.out.println(newCourrier);
		
		newCourrier.setEtatCourrier(etat);
		
		Langue langue = langueService.findLangueById(langueC);
		Nature nature = natureService.findNatureById(natureC);
		
		
		newCourrier.setLangue(langue);
		newCourrier.setNature(nature);
		
		System.out.println(newCourrier);
		
		if(emetteur.equals("user")){
			User user = userService.findUSerById(emetteurUser);
			newCourrier.setEmetteurUser(user);
		}
		
		else if(emetteur.equals("unite")){
			UniteBancaire uniteBancaire = uniteBancaireService.findUniteBancaireById(emetteurUnite);
			newCourrier.setEmetteurUnite(uniteBancaire);
		}
		
		else{
			ContactExterne contactExterne = contactExterneService.findContactExterneById(emetteurContactExterne);
			newCourrier.setEmetteurContact(contactExterne);
		}
		
		System.out.println(newCourrier);
		
		if(destinataire.equals("user")){
			User user = userService.findUSerById(destinataireUser);
			newCourrier.setDestinataireUser(user);
		}
		
		else if (destinataire.equals("unite")){
			UniteBancaire uniteBancaire = uniteBancaireService.findUniteBancaireById(destinataireUnite);
			newCourrier.setDestinataireUnite(uniteBancaire);
		}
		else
		{
			ContactExterne contactExterne = contactExterneService.findContactExterneById(destinataireContact);
			newCourrier.setDestinataireContact(contactExterne);
		}
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		System.out.println(dateFormat.format(date));
		newCourrier.setDateCreationCourrier(dateFormat.format(date));
		newCourrier.setObjetCourrier(objetCourrier);
		System.out.println(newCourrier.getObjetCourrier());
		System.out.println(newCourrier);
		globalCrudService.save(newCourrier);
		return "redirect:/bo/allContactExterne";
		
	}
	
}
