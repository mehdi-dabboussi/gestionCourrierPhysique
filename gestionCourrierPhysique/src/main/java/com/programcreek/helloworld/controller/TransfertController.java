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
import org.springframework.web.servlet.ModelAndView;

import com.sharing.entity.ContactExterne;
import com.sharing.entity.Courrier;
import com.sharing.entity.Notification;
import com.sharing.entity.Transfert;
import com.sharing.entity.UniteBancaire;
import com.sharing.entity.User;
import com.sharing.service.ContactExterneService;
import com.sharing.service.CourrierService;
import com.sharing.service.GlobalCrudService;
import com.sharing.service.TransfertService;
import com.sharing.service.UniteBancaireService;
import com.sharing.service.UserService;

@Controller
public class TransfertController {

	private GlobalCrudService globalCrudService;
	private CourrierService courrierService;
	private TransfertService transfertService;
	private UserService userService;
	private UniteBancaireService uniteBancaireService;
	private ContactExterneService contactExterneService;

	@Autowired
	public TransfertController(GlobalCrudService globalCrudService,
			CourrierService courrierService, TransfertService transfertService,
			UserService userService, UniteBancaireService uniteBancaireService,
			ContactExterneService contactExterneService) {
		this.courrierService = courrierService;
		this.globalCrudService = globalCrudService;
		this.transfertService = transfertService;
		this.userService = userService;
		this.uniteBancaireService = uniteBancaireService;
		this.contactExterneService = contactExterneService;
	}

	// ************ create Transfert *****************//

	@RequestMapping(value = "/bo/createTransfert", method = RequestMethod.GET)
	public ModelAndView createTransfert(Long idTransfert, long idCourrier) {
		ModelAndView modelAndView = new ModelAndView("bo/createTransfert.jsp");
		Transfert newTransfert = new Transfert();
		modelAndView.addObject("newTransfert", newTransfert);
		List<User> users = userService.getAllUsers();

		modelAndView.addObject("users", users);

		List<UniteBancaire> uniteBancaires = uniteBancaireService
				.getAllUniteBancaire();
		modelAndView.addObject("uniteBancaires", uniteBancaires);

		List<ContactExterne> contactExternes = contactExterneService
				.getAllContactExterne();
		modelAndView.addObject("contactExternes", contactExternes);

		return modelAndView;
	}

	@RequestMapping(value = "/bo/createTransfert", method = RequestMethod.POST)
	public String processCreateTransfert(
			@ModelAttribute(value = "newTransfert") Transfert newTransfert,
			BindingResult bindingResult, String emetteur, String destinataire,
			Long destinataireUnite, Long destinataireContact, Long idCourrier) {
		for (FieldError fieldError : bindingResult.getFieldErrors())
			System.out.println(fieldError.getField() + " : "
					+ fieldError.getDefaultMessage());

		Courrier courrier = courrierService.findCourrierById(idCourrier);
		System.out.println(newTransfert);
		newTransfert.setCourrier(courrier);

		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();

		UserDetails userDetail = (UserDetails) auth.getPrincipal();
		User connectedUser = userService.findUserByLogin(userDetail
				.getUsername());

		newTransfert.setEmetteurUser(connectedUser);
		UniteBancaire unite = connectedUser.getUniteBancaire();
		newTransfert.setEmetteurUnite(unite);
		String destTransfert = "";
		if (destinataire.equals("unite_dest")) {
			UniteBancaire uniteBancaire = uniteBancaireService
					.findUniteBancaireById(destinataireUnite);
			newTransfert.setDestinataireType("unite");
			newTransfert.setDestinataireUnite(uniteBancaire);
			destTransfert = uniteBancaire.getNom();
		} else {
			ContactExterne contactExterne = contactExterneService
					.findContactExterneById(destinataireContact);
			newTransfert.setDestinataireType("contact");
			newTransfert.setDestinataireContact(contactExterne);
			destTransfert = contactExterne.getNom();
		}
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		System.out.println(dateFormat.format(date));
		newTransfert.setDateTransfert(dateFormat.format(date));

		globalCrudService.save(newTransfert);
		
		if(newTransfert.getCourrier().getEmetteurType().equals("user")){
		Notification notification = new Notification();
		notification.setEmetteurTransfert(connectedUser.getUniteBancaire().getNom());
		notification.setTypeNotification("transfert");
		notification.setDateTransfert(dateFormat.format(date));
		notification.setDestinataireTransfert(destTransfert);
		notification.setNotifiedUser((User) newTransfert.getCourrier().getDestinataire());
		notification.setEstimation(newTransfert.getEstimation());
		notification.setCourrier(newTransfert.getCourrier());
		
		globalCrudService.save(notification);
		}
		return "redirect:/bo/courrier-"
				+ newTransfert.getCourrier().getIdCourrier();
	}

	// ************ consult Transfert *****************//

	@RequestMapping(value = "/bo/transfert-{idTransfert}")
	public ModelAndView showTransfert(
			@PathVariable("idTransfert") long idTransfert) {
		ModelAndView modelAndView = new ModelAndView("bo/showTransfert.jsp");
		Transfert createdTransfert = transfertService
				.findTransfertById(idTransfert);
		modelAndView.addObject("createdtransfert", createdTransfert);
		if (createdTransfert.getDestinataireUnite() != null)
			modelAndView.addObject("destinataire", createdTransfert
					.getDestinataireUnite().getNom());
		else
			modelAndView.addObject("destinataire", createdTransfert
					.getDestinataireContact().getNom());
		return modelAndView;
	}
	// ************ edit Transfert *****************

	
	 @RequestMapping(value = "/bo/transfert-{idTransfert}-edit", method = RequestMethod.GET) 
	 public ModelAndView initUpdateTransfertForm(@PathVariable("idTransfert") long idTransfert)
	 {
	 ModelAndView modelAndView = new ModelAndView("bo/createTransfert.jsp");
	 Transfert newtransfert = transfertService.findTransfertById(idTransfert);
	  modelAndView.addObject("newTransfert", newtransfert); 
	  List<UniteBancaire> uniteBancaires = uniteBancaireService.getAllUniteBancaire();
		modelAndView.addObject("uniteBancaires", uniteBancaires);
		
		List<ContactExterne> contactExternes = contactExterneService.getAllContactExterne();
		modelAndView.addObject("contactExternes", contactExternes);
	  return modelAndView; 
	  }
	  
	 @RequestMapping(value = "/bo/transfert-{idTransfert}-edit", method=RequestMethod.POST) 
	 public String processUpdateTransfertForm(@PathVariable("idTransfert") long idTransfert,
			 Long idCourrier,
			 String destinataire, String destinataireUnite, String destinataireContact,
			 String estimation) 
	 {
		 Transfert newTransfert = transfertService.findTransfertById(idTransfert);
		 //newTransfert.setIdTransfert(idTransfert); 
	  Courrier courrier = courrierService.findCourrierById(idCourrier);
	  newTransfert.setCourrier(courrier);
	  
	  if (destinataire.equals("unite_dest")){
			UniteBancaire uniteBancaire = uniteBancaireService.findUniteBancaireById(Long.parseLong(destinataireUnite));
			newTransfert.setDestinataireType("unite");
			newTransfert.setDestinataireUnite(uniteBancaire);
			newTransfert.setDestinataireContact(null);
		}
		else
		{
			ContactExterne contactExterne = contactExterneService.findContactExterneById(Long.parseLong(destinataireContact));
			newTransfert.setDestinataireType("contact");
			newTransfert.setDestinataireContact(contactExterne);
			newTransfert.setDestinataireUnite(null);
		}
		
	  newTransfert.setEstimation(estimation);
	  this.globalCrudService.update(newTransfert); 
	  return "redirect:/bo/courrier-" + courrier.getIdCourrier();
	  
	  }
	  
	  
	  // ************ delete Transfert *****************
	  
	  @RequestMapping(value = "/bo/transfert-{idTransfert}/delete", method =
	  RequestMethod.GET) public String
	  processDeleteTransfert(@PathVariable("idTransfert") long idTransfert,
	  Long idCourrier)
	  {
	  Transfert transfert = transfertService.findTransfertById(idTransfert);
	  globalCrudService.remove(transfert, idTransfert); 
	  return "redirect:/bo/courrier-"+transfert.getCourrier().getIdCourrier(); 
	  }
	 
}
