package com.programcreek.helloworld.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.sharing.entity.ContactExterne;
import com.sharing.entity.SousContactExterne;
import com.sharing.entity.UniteBancaire;
import com.sharing.entity.User;
import com.sharing.service.ContactExterneService;
import com.sharing.service.GlobalCrudService;
import com.sharing.service.SousContactExterneService;


@Controller
public class ContactExterneController {
	
	private GlobalCrudService globalCrudService;
	private ContactExterneService contactExterneService;
	private SousContactExterneService sousContactExterneService;

	@Autowired
	public ContactExterneController(GlobalCrudService globalCrudService, ContactExterneService contactExterneService,
			SousContactExterneService sousContactExterneService) {
		this.globalCrudService = globalCrudService;
		this.contactExterneService = contactExterneService;
		this.sousContactExterneService = sousContactExterneService;
	}
	
	
	// ------------------------Contact Externe ------------------------//

		// ************ create contact externe *****************//

		@RequestMapping(value = "/bo/createContactExterne", method = RequestMethod.GET)
		public ModelAndView createContactExterne() {
			ModelAndView modelAndView = new ModelAndView(
					"bo/createContactExterne.jsp");
			ContactExterne newContactExterne = new ContactExterne();
			modelAndView.addObject("newContactExterne", newContactExterne);
			return modelAndView;
		}

		@RequestMapping(value = "/bo/createContactExterne", method = RequestMethod.POST)
		public String processCreateContactExterne(
				@ModelAttribute(value = "newContactExterne") ContactExterne newContactExterne) {
			globalCrudService.save(newContactExterne);
			return "redirect:/bo/contactexterne-"
					+ newContactExterne.getId();
		}

		// ************ consult contact externe *****************//
		@RequestMapping(value = "/bo/contactexterne-{idContactExterne}")
		public ModelAndView showContactExterne(
				@PathVariable("idContactExterne") long idContactExterne) {
			ModelAndView modelAndView = new ModelAndView(
					"bo/showContactExterne.jsp");
			ContactExterne createdContactExterne = contactExterneService
					.findContactExterneById(idContactExterne);
			modelAndView.addObject("createdContactExterne", createdContactExterne);
			modelAndView.addObject("sousContacts", sousContactExterneService
					.getSousContactsByContact(createdContactExterne));
			return modelAndView;
		}

		// ************ edit contact externe *****************//
		@RequestMapping(value = "/bo/contactexterne-{idContactExterne}-edit", method = RequestMethod.GET)
		public ModelAndView initUpdateContactExterneForm(
				@PathVariable("idContactExterne") long idContactExterne) {
			ModelAndView modelAndView = new ModelAndView(
					"bo/createContactExterne.jsp");
			ContactExterne newContactExterne = this.contactExterneService
					.findContactExterneById(idContactExterne);
			modelAndView.addObject("newContactExterne", newContactExterne);
			return modelAndView;
		}

		@RequestMapping(value = "/bo/contactexterne-{idContactExterne}-edit", method = RequestMethod.POST)
		public String processUpdateContactExterneForm(
				ContactExterne newContactExterne,
				@PathVariable("idContactExterne") long idContactExterne) {
			newContactExterne.setId(idContactExterne);
			this.globalCrudService.update(newContactExterne);
			return "redirect:/bo/contactexterne-"
					+ newContactExterne.getId();
		}

		// ************ All contact externe *****************//
		@RequestMapping(value = "/bo/allContactExterne", method = RequestMethod.GET)
		public ModelAndView showAllContactExterne() {
			ModelAndView modelAndView = new ModelAndView("bo/allContactExterne.jsp");
			List<ContactExterne> contactExternes = contactExterneService
					.getAllContactExterne();
			modelAndView.addObject("contactExternes", contactExternes);
			return modelAndView;
		}
		
		// ************ delete contact externe *****************//
		
		@RequestMapping(value = "/bo/contactexterne-{idContactExterne}/delete", method = RequestMethod.GET)
		public String processDeleteContactExterne(
				@PathVariable("idContactExterne") long idContactExterne) {
			ContactExterne contactExterne = contactExterneService.findContactExterneById(idContactExterne);
			sousContactExterneService.deleteWithContactExterne(contactExterne);
			this.globalCrudService.remove(contactExterne, idContactExterne);
			return "redirect:/bo/allContactExterne";
		}
		

		// ------------------------ Sous contact externe ------------------------//

		// ************ create sous contact externe *****************//
		@RequestMapping(value = "/bo/createSousContact", method = RequestMethod.GET)
		public ModelAndView createSousContactExterne(Long idContactExterne) {
			ModelAndView modelAndView = new ModelAndView("bo/createSousContact.jsp");
			SousContactExterne newSousContactExterne = new SousContactExterne();
			ContactExterne contactExterne = contactExterneService
					.findContactExterneById(idContactExterne);
			System.out.println(contactExterne.getNom());
			// newSousContactExterne.setContactExterne(contactExterne);
			modelAndView.addObject("newSousContactExterne", newSousContactExterne);
			modelAndView
					.addObject("Societe", contactExterne.getNom());
			return modelAndView;
		}

		@RequestMapping(value = "/bo/createSousContact", method = RequestMethod.POST)
		public String processCreateSousContactExterne(
				@ModelAttribute(value = "newSousContactExterne") SousContactExterne newSousContactExterne,
				Long idContactExterne) {
			ContactExterne contactExterne = contactExterneService
					.findContactExterneById(idContactExterne);
			System.out.println(contactExterne.getNom());
			newSousContactExterne.setContactExterne(contactExterne);
			globalCrudService.save(newSousContactExterne);
			return "redirect:/bo/contactexterne-"
					+ newSousContactExterne.getContactExterne()
							.getId();
		}

		// ************ consult sous contact externe *****************//
		@RequestMapping(value = "/bo/sousContact-{idSousContactExterne}")
		public ModelAndView showSousContactExterne(
				@PathVariable("idSousContactExterne") long idSousContactExterne) {
			ModelAndView modelAndView = new ModelAndView(
					"bo/showSousContactExterne.jsp");
			SousContactExterne sousContactExterne = sousContactExterneService.findSousContactExterneById(idSousContactExterne);
			modelAndView.addObject("createdSousContactExterne", sousContactExterne);
			modelAndView.addObject("Societe", sousContactExterne
					.getContactExterne().getNom());
			return modelAndView;
		}

		// ************ edit sous contact externe *****************//

		@RequestMapping(value = "/bo/sousContact-{idSousContactExterne}-edit", method = RequestMethod.GET)
		public ModelAndView initUpdateSousContactExterneForm(
				@PathVariable("idSousContactExterne") long idSousContactExterne,
				Long idContactExterne) {
			ModelAndView modelAndView = new ModelAndView("bo/createSousContact.jsp");
			SousContactExterne newSousContactExterne = sousContactExterneService
					.findSousContactExterneById(idSousContactExterne);

			modelAndView.addObject("newSousContactExterne", newSousContactExterne);
			modelAndView.addObject("Societe", newSousContactExterne
					.getContactExterne().getNom());
			return modelAndView;
		}

		@RequestMapping(value = "/bo/sousContact-{idSousContactExterne}-edit", method = RequestMethod.POST)
		public String processUpdateSousContactExterneForm(
				SousContactExterne newSousContactExterne,
				@PathVariable("idSousContactExterne") long idSousContactExterne,
				Long idContactExterne) {
			newSousContactExterne.setIdSousContactExterne(idSousContactExterne);
			ContactExterne contactExterne = contactExterneService
					.findContactExterneById(idContactExterne);
			newSousContactExterne.setContactExterne(contactExterne);
			this.globalCrudService.update(newSousContactExterne);
			return "redirect:/bo/contactexterne-"
					+ newSousContactExterne.getContactExterne()
							.getId();
		}
		
		
		// ************ delete sous contact externe *****************//
				@RequestMapping(value = "/bo/sousContact-{idSousContactExterne}/delete", method = RequestMethod.GET)
				public String processDeleteSousContact(@PathVariable("idSousContactExterne") long idSousContactExterne, Long idContactExterne) {
					SousContactExterne sousContactExterne = sousContactExterneService.findSousContactExterneById(idSousContactExterne);
					globalCrudService.remove(sousContactExterne, idSousContactExterne);
					return "redirect:/bo/contactexterne-" + idContactExterne;
				}
}
