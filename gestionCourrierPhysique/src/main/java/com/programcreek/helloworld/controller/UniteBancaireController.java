package com.programcreek.helloworld.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.sharing.entity.UniteBancaire;
import com.sharing.entity.User;
import com.sharing.service.BordereauService;
import com.sharing.service.GlobalCrudService;
import com.sharing.service.RoleService;
import com.sharing.service.UniteBancaireService;
import com.sharing.service.UserService;


@Controller
public class UniteBancaireController {
	
	private UserService userService;
	private GlobalCrudService globalCrudService;
	private UniteBancaireService uniteBancaireService;
	private RoleService roleService;
	private BordereauService bordereauService;
	
	
	@Autowired
	public UniteBancaireController(UserService userService, GlobalCrudService globalCrudService, 
			UniteBancaireService uniteBancaireService, RoleService roleService,
			BordereauService bordereauService) {
		this.userService = userService;
		this.globalCrudService = globalCrudService;
		this.uniteBancaireService = uniteBancaireService;
		this.roleService = roleService;
		this.bordereauService = bordereauService;
	}
	

		// ************ create unite bancaire *****************//
		@RequestMapping(value = "/admin/newUniteBancaire", method = RequestMethod.GET)
		public ModelAndView createUniteBancaire() {
			ModelAndView modelAndView = new ModelAndView(
					"admin/newUniteBancaire.jsp");
			UniteBancaire newUniteBancaire = new UniteBancaire();
			modelAndView.addObject("newUniteBancaire", newUniteBancaire);
			System.out.println(bordereauService.getAllVille());
			return modelAndView;
		}

		@RequestMapping(value = "/admin/newUniteBancaire", method = RequestMethod.POST)
		public String processCreateUniteBancaire(
				@ModelAttribute(value = "newUniteBancaire") UniteBancaire newUniteBancaire) {
			// List<User> users = new ArrayList<User>();
			// newUniteBancaire.setUsers(users);
			globalCrudService.save(newUniteBancaire);
			return "redirect:/admin/unite-" + newUniteBancaire.getId();
		}

		// ************ consult unité bancaire *****************//
		@RequestMapping(value = "/admin/unite-{uniteBancaireId}")
		public ModelAndView showUniteBancaireAfterCreate(
				@PathVariable("uniteBancaireId") long uniteBancaireId) {
			ModelAndView modelAndView = new ModelAndView(
					"admin/showUniteBancaireAfterCreate.jsp");
			UniteBancaire createdUniteBancaire = uniteBancaireService
					.findUniteBancaireById(uniteBancaireId);
			modelAndView.addObject("createdUniteBancaire", createdUniteBancaire);
			modelAndView.addObject("users",
					userService.getUsersByUB(createdUniteBancaire));
			return modelAndView;
		}

		// ************ edit unité bancaire *****************//
		@RequestMapping(value = "/admin/unite-{uniteBancaireId}-edit", method = RequestMethod.GET)
		public ModelAndView initUpdateUniteBancaireForm(
				@PathVariable("uniteBancaireId") long uniteBancaireId) {
			ModelAndView modelAndView = new ModelAndView(
					"admin/newUniteBancaire.jsp");
			UniteBancaire newUniteBancaire = this.uniteBancaireService
					.findUniteBancaireById(uniteBancaireId);
			modelAndView.addObject("newUniteBancaire", newUniteBancaire);
			return modelAndView;
		}

		@RequestMapping(value = "/admin/unite-{uniteBancaireId}-edit", method = RequestMethod.POST)
		public String processUpdateUniteBancaireForm(
				UniteBancaire newUniteBancaire,
				@PathVariable("uniteBancaireId") long uniteBancaireId) {
			newUniteBancaire.setId(uniteBancaireId);
			this.globalCrudService.update(newUniteBancaire);
			return "redirect:/admin/unite-" + newUniteBancaire.getId();
		}

		// ************ All unite bancaire *****************//
		@RequestMapping(value = "/admin/allUniteBancaire", method = RequestMethod.GET)
		public ModelAndView showAllUniteBancaire() {
			ModelAndView modelAndView = new ModelAndView("admin/allUnites.jsp");
			List<UniteBancaire> uniteBancaires = uniteBancaireService
					.getAllUniteBancaire();
			modelAndView.addObject("uniteBancaires", uniteBancaires);
			return modelAndView;
		}

		// ************ delete unite bancaire *****************//

		@RequestMapping(value = "/admin/unite-{uniteBancaireId}/delete", method = RequestMethod.GET)
		public String processDeleteUniteBancaire(
				@PathVariable("uniteBancaireId") long uniteBancaireId) {
			UniteBancaire uniteBancaire = uniteBancaireService
					.findUniteBancaireById(uniteBancaireId);
			userService.removeUBFromUser(uniteBancaire);
			this.globalCrudService.remove(uniteBancaire, uniteBancaireId);
			return "redirect:/admin/allUniteBancaire";
		}

}
