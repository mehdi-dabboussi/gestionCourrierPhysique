package com.programcreek.helloworld.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sharing.entity.ContactExterne;
import com.sharing.entity.Courrier;
import com.sharing.entity.CourrierSupprime;
import com.sharing.entity.CoursierExterne;
import com.sharing.entity.Emetteur_Recepteur;
import com.sharing.entity.Langue;
import com.sharing.entity.Nature;
import com.sharing.entity.Notification;
import com.sharing.entity.SousContactExterne;
import com.sharing.entity.Transfert;
import com.sharing.entity.TransporteurExterne;
import com.sharing.entity.UniteBancaire;
import com.sharing.entity.User;
import com.sharing.service.ContactExterneService;
import com.sharing.service.CourrierService;
import com.sharing.service.EmetteurRecepteurService;
import com.sharing.service.GlobalCrudService;
import com.sharing.service.LangueService;
import com.sharing.service.NatureService;
import com.sharing.service.SousContactExterneService;
import com.sharing.service.TransfertService;
import com.sharing.service.TransfertServiceImpl;
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
	private TransfertService tansfertService;
	private EmetteurRecepteurService emetteurRecepteurService;
	private SousContactExterneService sousContactExterneService;
	
	
	@Autowired
	public CourrierController(GlobalCrudService globalCrudService,CourrierService courrierService,
			LangueService langueService, NatureService natureService, UserService userService,
			UniteBancaireService uniteBancaireService, ContactExterneService contactExterneService, TransfertService transfertService,
			EmetteurRecepteurService emetteurRecepteurService, SousContactExterneService sousContactExterneService){
		this.globalCrudService = globalCrudService;
		this.courrierService = courrierService;
		this.langueService = langueService;
		this.natureService = natureService;
		this.userService = userService;
		this.uniteBancaireService = uniteBancaireService;
		this.contactExterneService = contactExterneService;
		this.tansfertService =transfertService;
		this.emetteurRecepteurService = emetteurRecepteurService;
		this.sousContactExterneService = sousContactExterneService;
	}
	
	
	@RequestMapping(value="/bo/newCourrier", method=RequestMethod.GET)
	public ModelAndView createCourrier(){
		ModelAndView modelAndView = new ModelAndView("bo/createCourrier.jsp");
		Courrier newCourrier = new Courrier();
		System.out.println(newCourrier);
		modelAndView.addObject("newCourrier", newCourrier);
		List<Langue> langues = langueService.getAllLangue();
		List<Nature> natures = natureService.getAllNature();
		modelAndView.addObject("langues", langues);
		modelAndView.addObject("natures", natures);
		
		List<User> users = userService.getAllUsers();
		modelAndView.addObject("users", users);
		
		List<UniteBancaire> uniteBancaires = uniteBancaireService.getAllUniteBancaire();
		modelAndView.addObject("uniteBancaires", uniteBancaires);
		
		List<ContactExterne> contactExternes = contactExterneService.getAllContactExterne();
		modelAndView.addObject("contactExternes", contactExternes);
		
		return modelAndView;
	}
	
	
	
	@RequestMapping(value="/bo/newCourrier", method=RequestMethod.POST)
	public String processCreateCourrier(
			@ModelAttribute("newCourrier") Courrier newCourrier, BindingResult bindingResult, String emetteur,
			String emetteurUser, String emetteurUnite, String emetteurContactExterne,
			String destinataire, String destinataireUser, String destinataireUnite, String destinataireContact,
			String natureC, String langueC, String etat, String objetCourrier, String emetteurSousContact,
			String destinataireSousContact){
		for( FieldError fieldError : bindingResult.getFieldErrors() )
		    System.out.println(fieldError.getField() +" : "+fieldError.getDefaultMessage());
		
		
		System.out.println(newCourrier);
		
		newCourrier.setEtatCourrier(etat);
		
		Langue langue = langueService.findLangueById(Long.parseLong(langueC));
		Nature nature = natureService.findNatureById(Long.parseLong(natureC));
		
		
		newCourrier.setLangue(langue);
		newCourrier.setNature(nature);
		
		System.out.println(newCourrier);
		
		Notification notification = new Notification();
		
		if(emetteur.equals("user_emet")){
			User user = userService.findUSerById(Long.parseLong(emetteurUser));
			newCourrier.setEmetteurType("user");
			//Emetteur_Recepteur emetteur_Recepteur = new Emetteur_Recepteur();
			//emetteur_Recepteur.setId(user.getId());
			//globalCrudService.save(emetteur_Recepteur);
			newCourrier.setEmetteur(user);
			//newCourrier.setEmetteurUser(user);
			
		}
		
		else if(emetteur.equals("unite_emet")){
			UniteBancaire uniteBancaire = uniteBancaireService.findUniteBancaireById(Long.parseLong(emetteurUnite));
			newCourrier.setEmetteurType("unite");
			//Emetteur_Recepteur emetteur_Recepteur = new Emetteur_Recepteur();
			//emetteur_Recepteur.setId(uniteBancaire.getId());
			//globalCrudService.save(emetteur_Recepteur);
			newCourrier.setEmetteur(uniteBancaire);
			//newCourrier.setEmetteurUnite(uniteBancaire);
		}
		
		else{
			if(emetteurSousContact !="")
			{
				SousContactExterne sousContactExterne = sousContactExterneService.findSousContactExterneById(
						Long.parseLong(emetteurSousContact));
				newCourrier.setEmetteurType("sousContact");
				newCourrier.setEmetteur(sousContactExterne);
			}
			else{
			ContactExterne contactExterne = contactExterneService.findContactExterneById(Long.parseLong(emetteurContactExterne));
			newCourrier.setEmetteurType("contact");
			System.out.println(emetteurSousContact);
			//Emetteur_Recepteur emetteur_Recepteur = new Emetteur_Recepteur();
			//emetteur_Recepteur.setId(contactExterne.getIdContactExterne());
			//globalCrudService.save(emetteur_Recepteur);
			newCourrier.setEmetteur(contactExterne);
			//newCourrier.setEmetteurContact(contactExterne);
			}
		}
		
		System.out.println(newCourrier);
		
		User userNotif = new User();
		
		if(destinataire.equals("user_dest")){
			User user = userService.findUSerById(Long.parseLong(destinataireUser));
			userNotif = user;
			newCourrier.setDestinataireType("user");
			//Emetteur_Recepteur emetteur_Recepteur = new Emetteur_Recepteur();
			//emetteur_Recepteur.setId(user.getId());
			//globalCrudService.save(emetteur_Recepteur);
			newCourrier.setDestinataire(user);
			//newCourrier.setDestinataireUser(user);
		}
		
		else if (destinataire.equals("unite_dest")){
			UniteBancaire uniteBancaire = uniteBancaireService.findUniteBancaireById(Long.parseLong(destinataireUnite));
			newCourrier.setDestinataireType("unite");
			//Emetteur_Recepteur emetteur_Recepteur = new Emetteur_Recepteur();
			//emetteur_Recepteur.setId(uniteBancaire.getId());
			//globalCrudService.save(emetteur_Recepteur);
			newCourrier.setDestinataire(uniteBancaire);
			//newCourrier.setDestinataireUnite(uniteBancaire);
		}
		else
		{
			if(destinataireSousContact !="")
			{
				SousContactExterne sousContactExterne = sousContactExterneService.findSousContactExterneById(
						Long.parseLong(destinataireSousContact));
				newCourrier.setDestinataireType("sousContact");
				newCourrier.setDestinataire(sousContactExterne);
			}
			else{
			ContactExterne contactExterne = contactExterneService.findContactExterneById(Long.parseLong(destinataireContact));
			newCourrier.setDestinataireType("contact");
			//Emetteur_Recepteur emetteur_Recepteur = new Emetteur_Recepteur();
			//emetteur_Recepteur.setId(contactExterne.getIdContactExterne());
			//globalCrudService.save(emetteur_Recepteur);
			newCourrier.setDestinataire(contactExterne);
			//newCourrier.setDestinataireContact(contactExterne);
			}
		}
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		System.out.println(dateFormat.format(date));
		newCourrier.setDateCreationCourrier(dateFormat.format(date));
		newCourrier.setObjetCourrier(objetCourrier);
		newCourrier.setRecu(false);
		System.out.println(newCourrier.getObjetCourrier());
		System.out.println(newCourrier);
		
		
		
		globalCrudService.save(newCourrier);
		
		if(userNotif != null){
			notification.setCourrier(newCourrier);
			notification.setTypeNotification("creation");
			notification.setNotifiedUser(userNotif);
			System.out.println(notification.getCourrier());
			globalCrudService.save(notification);
		}
		
		
		return "redirect:/bo/courrier-" + newCourrier.getIdCourrier();
		
	}
	
	//consult courrier
	@RequestMapping(value = "/bo/courrier-{idCourrier}")
	public ModelAndView showUserAfterCreate(@PathVariable("idCourrier") long idCourrier) {
		ModelAndView modelAndView = new ModelAndView(
				"bo/showCourrierAfterCreate.jsp");
		Courrier createdCourrier = courrierService.findCourrierById(idCourrier);
		modelAndView.addObject("createdCourrier", createdCourrier);
		
		List<Transfert> transferts = tansfertService.getTransfertsByCourrier(idCourrier);
		for (Transfert transfert:transferts){
			System.out.println(transfert);
		}
		modelAndView.addObject("transferts", transferts);
		
	
		/*if(createdCourrier.getEmetteurUser() != null )
			modelAndView.addObject("emetteur", createdCourrier.getEmetteurUser().getUserName() + createdCourrier.getEmetteurUser().getSurName());
		else if (createdCourrier.getEmetteurUnite() != null )
			modelAndView.addObject("emetteur", createdCourrier.getEmetteurUnite().getNomUniteBancaire());
		else 
			modelAndView.addObject("emetteur", createdCourrier.getEmetteurContact().getNomContactExterne());
		*/
		
		
		
		/*if(createdCourrier.getDestinataireUser() != null)
			modelAndView.addObject("destinataire", createdCourrier.getDestinataireUser().getUserName() +" " + createdCourrier.getDestinataireUser().getSurName());
		else if(createdCourrier.getDestinataireUnite() != null)
			modelAndView.addObject("destinataire", createdCourrier.getDestinataireUnite().getNomUniteBancaire());
		else
			modelAndView.addObject("destinataire", createdCourrier.getDestinataireContact().getNomContactExterne());
		*/
		
		
		return modelAndView;
	}
	
	
	//all courriers 
	@RequestMapping(value = "/bo/allCourriers", method = RequestMethod.GET)
	public ModelAndView showAllUsers() {
		ModelAndView modelAndView = new ModelAndView("bo/allCourriers.jsp");
		List<Courrier> courriers = courrierService.getAllCourrier();

		
		modelAndView.addObject("courriers", courriers);
		
		List<User> users = userService.getAllUsers();
		modelAndView.addObject("users", users);
		
		List<UniteBancaire> uniteBancaires = uniteBancaireService.getAllUniteBancaire();
		modelAndView.addObject("uniteBancaires", uniteBancaires);
		
		List<ContactExterne> contactExternes = contactExterneService.getAllContactExterne();
		modelAndView.addObject("contactExternes", contactExternes);
		return modelAndView;
	}
	
	//delete courrier
	
	@RequestMapping(value = "/bo/courrier-{idCourrier}/delete", method = RequestMethod.GET)
	public String processDeleteCourrier(@PathVariable("idCourrier") long idCourrier) {
		Courrier courrier = courrierService.findCourrierById(idCourrier);
		
		
		// Connected user
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		UserDetails userDetail = (UserDetails) auth.getPrincipal();
		User connectedUser = userService.findUserByLogin(userDetail
				.getUsername());
		
		CourrierSupprime courrierSupprime = new CourrierSupprime();
		courrierSupprime.setIdCourrier(courrier.getIdCourrier());
		courrierSupprime.setEtatCourrier(courrier.getEtatCourrier());
		courrierSupprime.setObjetCourrier(courrier.getObjetCourrier());
		courrierSupprime.setDetailsCourrier(courrier.getDetailsCourrier());
		courrierSupprime.setDateCreationCourrier(courrier.getDateCreationCourrier());
		courrierSupprime.setNature(courrier.getNature());
		courrierSupprime.setLangue(courrier.getLangue());
		courrierSupprime.setRecu(courrier.isRecu());
		courrierSupprime.setActor(connectedUser);
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		courrierSupprime.setDateSuppression(dateFormat.format(date));
		
		courrierSupprime.setEmetteur(courrier.getEmetteur());
		courrierSupprime.setDestinataire(courrier.getDestinataire());
		
		courrierSupprime.setEmetteurType(courrier.getEmetteurType());
		courrierSupprime.setDestinataireType(courrier.getDestinataireType());
		
		globalCrudService.save(courrierSupprime);
		
		globalCrudService.remove(courrier, idCourrier);
		return "redirect:/bo/allCourriers";
	}
	
	
	//edit courrier
	@RequestMapping(value = "bo/courrier-{idCourrier}-edit", method = RequestMethod.GET)
	public ModelAndView editCourrier(@PathVariable("idCourrier") long idCourrier){
		ModelAndView modelAndView = new ModelAndView("bo/createCourrier.jsp");
		Courrier newCourrier = courrierService.findCourrierById(idCourrier);
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
	
	
	
	
	@RequestMapping(value = "bo/courrier-{idCourrier}-edit", method = RequestMethod.POST)
	public String processEditCourrier(
			@PathVariable("idCourrier") long idCourrier,String emetteur,
			String emetteurUser, String emetteurUnite, String emetteurContactExterne,
			String destinataire, String destinataireUser, String destinataireUnite, String destinataireContact,
			String natureC, String langueC, String etat, String objetCourrier, String detailsCourrier){
		
		Courrier newCourrier = courrierService.findCourrierById(idCourrier);
		
		newCourrier.setEtatCourrier(etat);
		newCourrier.setDetailsCourrier(detailsCourrier);
		newCourrier.setObjetCourrier(objetCourrier);
		
		Langue langue = langueService.findLangueById(Long.parseLong(langueC));
		Nature nature = natureService.findNatureById(Long.parseLong(natureC));
		
		
		newCourrier.setLangue(langue);
		newCourrier.setNature(nature);
		
		if(emetteur.equals("user_emet")){
			User user = userService.findUSerById(Long.parseLong(emetteurUser));
			newCourrier.setEmetteurType("user");
			//Emetteur_Recepteur emetteur_Recepteur = emetteurRecepteurService.findUSerById(newCourrier.getEmetteur().getPrim());
			//emetteur_Recepteur.setId(user.getIdUser());
			//globalCrudService.update(emetteur_Recepteur);
			newCourrier.setEmetteur(user);
			/*newCourrier.setEmetteurUser(user);
			newCourrier.setEmetteurUnite(null);
			newCourrier.setEmetteurContact(null);*/
		}
		
		else if(emetteur.equals("unite_emet")){
			UniteBancaire uniteBancaire = uniteBancaireService.findUniteBancaireById(Long.parseLong(emetteurUnite));
			newCourrier.setEmetteurType("unite");
			//Emetteur_Recepteur emetteur_Recepteur = emetteurRecepteurService.findUSerById(newCourrier.getEmetteur().getPrim());
			//emetteur_Recepteur.setId(uniteBancaire.getIdUniteBancaire());
			//globalCrudService.update(emetteur_Recepteur);
			newCourrier.setEmetteur(uniteBancaire);
			
			/*newCourrier.setEmetteurUnite(uniteBancaire);
			newCourrier.setEmetteurUser(null);
			newCourrier.setEmetteurContact(null);*/
		}
		
		else{
			ContactExterne contactExterne = contactExterneService.findContactExterneById(Long.parseLong(emetteurContactExterne));
			newCourrier.setEmetteurType("contact");
			//newCourrier.setEmetteur(emetteurContactExterne);
			
			//Emetteur_Recepteur emetteur_Recepteur = emetteurRecepteurService.findUSerById(newCourrier.getEmetteur().getPrim());
			//emetteur_Recepteur.setId(contactExterne.getIdContactExterne());
			//globalCrudService.update(emetteur_Recepteur);
			newCourrier.setEmetteur(contactExterne);
			
			/*newCourrier.setEmetteurContact(contactExterne);
			newCourrier.setEmetteurUser(null);
			newCourrier.setEmetteurUnite(null);*/
		}
		
		System.out.println(newCourrier);
		
		if(destinataire.equals("user_dest")){
			User user = userService.findUSerById(Long.parseLong(destinataireUser));
			newCourrier.setDestinataireType("user");
			
			//Emetteur_Recepteur emetteur_Recepteur = emetteurRecepteurService.findUSerById(newCourrier.getDestinataire().getPrim());
			//emetteur_Recepteur.setId(user.getIdUser());
			//globalCrudService.update(emetteur_Recepteur);
			newCourrier.setDestinataire(user);
			
			
			/*newCourrier.setDestinataireUser(user);
			newCourrier.setDestinataireUnite(null);
			newCourrier.setDestinataireContact(null);*/
		}
		
		else if (destinataire.equals("unite_dest")){
			UniteBancaire uniteBancaire = uniteBancaireService.findUniteBancaireById(Long.parseLong(destinataireUnite));
			newCourrier.setDestinataireType("unite");
			//newCourrier.setDestinataire(destinataireUnite);
			
			//Emetteur_Recepteur emetteur_Recepteur = emetteurRecepteurService.findUSerById(newCourrier.getDestinataire().getPrim());
			//emetteur_Recepteur.setId(uniteBancaire.getIdUniteBancaire());
			//globalCrudService.update(emetteur_Recepteur);
			newCourrier.setDestinataire(uniteBancaire);
			
			/*newCourrier.setDestinataireUnite(uniteBancaire);
			newCourrier.setDestinataireUser(null);
			newCourrier.setDestinataireContact(null);*/
		}
		else
		{
			ContactExterne contactExterne = contactExterneService.findContactExterneById(Long.parseLong(destinataireContact));
			newCourrier.setDestinataireType("contact");
			//newCourrier.setDestinataire(destinataireContact);
			
			//Emetteur_Recepteur emetteur_Recepteur = emetteurRecepteurService.findUSerById(newCourrier.getDestinataire().getPrim());
			//emetteur_Recepteur.setId(contactExterne.getIdContactExterne());
			//globalCrudService.update(emetteur_Recepteur);
			newCourrier.setDestinataire(contactExterne);
			
			/*newCourrier.setDestinataireContact(contactExterne);
			newCourrier.setDestinataireUser(null);
			newCourrier.setDestinataireUnite(null);*/
		}
		
		
		this.globalCrudService.update(newCourrier);
		return "redirect:/bo/courrier-" + newCourrier.getIdCourrier();
	}
	
	@RequestMapping(value = "/bo/courrier/loadSousContact" ,method = RequestMethod.GET)
	@ResponseBody
	public List<SousContactExterne> loadSousContact(@RequestParam(value = "ContactId" ,required = true) String ContactId ){
		ContactExterne contactExterne = contactExterneService.findContactExterneById(Long.parseLong(ContactId));
		return sousContactExterneService.getSousContactsByContact(contactExterne);
	}
	
}
