package com.programcreek.helloworld.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sharing.entity.ContactExterne;
import com.sharing.entity.Courrier;
import com.sharing.entity.Role;
import com.sharing.entity.Transfert;
import com.sharing.entity.UniteBancaire;
import com.sharing.entity.User;
import com.sharing.service.ContactExterneService;
import com.sharing.service.CourrierService;
import com.sharing.service.GlobalCrudService;
import com.sharing.service.RoleService;
import com.sharing.service.TransfertService;
import com.sharing.service.UniteBancaireService;
import com.sharing.service.UserService;


@Controller
public class UserController {
	
	private UserService userService;
	private GlobalCrudService globalCrudService;
	private UniteBancaireService uniteBancaireService;
	private RoleService roleService;
	private CourrierService courrierService;
	private ContactExterneService contactExterneService;
	private TransfertService transfertService;
	
	
	@Autowired
	public UserController(UserService userService, GlobalCrudService globalCrudService, 
			UniteBancaireService uniteBancaireService, RoleService roleService, CourrierService courrierService,
			ContactExterneService contactExterneService, TransfertService transfertService) {
		this.userService = userService;
		this.globalCrudService = globalCrudService;
		this.uniteBancaireService = uniteBancaireService;
		this.roleService = roleService;
		this.courrierService = courrierService;
		this.contactExterneService = contactExterneService;
		this.transfertService = transfertService;
	}
	
	@RequestMapping(value = "/admin/newUser", method = RequestMethod.GET)
	public ModelAndView createUser(Model model) {
		ModelAndView modelAndView = new ModelAndView("admin/createUser.jsp");
		List<UniteBancaire> uniteBancaires = uniteBancaireService
				.getAllUniteBancaire();
		modelAndView.addObject("uniteBancaires", uniteBancaires);
		
		// Connected user
				Authentication auth = SecurityContextHolder.getContext()
						.getAuthentication();
				UserDetails userDetail = (UserDetails) auth.getPrincipal();
				User connectedUser = userService.findUserByLogin(userDetail
						.getUsername());
				modelAndView.addObject("connectedUser", connectedUser);
				modelAndView.addObject("role", connectedUser.getRoles());
				
		User oldUser = (User) model.asMap().get("newUser");
		User oldUserWrongEmail = (User) model.asMap().get("newUserWrongEmail");
		if(oldUser != null && oldUserWrongEmail != null){
			modelAndView.addObject("newUser", oldUser);
			modelAndView.addObject("loginFound", "le login que vous avez entré existe deja");
			modelAndView.addObject("emailFound", "l'adresse e-mail que vous avez entré existe deja");
		}
		else{
		if(oldUser != null){
			modelAndView.addObject("newUser", oldUser);
			modelAndView.addObject("loginFound", "le login que vous avez entré existe deja");
		}
		else{
		if(oldUserWrongEmail != null){
			modelAndView.addObject("newUser", oldUserWrongEmail);
			modelAndView.addObject("emailFound", "l'adresse e-mail que vous avez entré existe deja");
		}
		else{
		User newUser = new User();
		modelAndView.addObject("newUser", newUser);
		}
		}
		}
		return modelAndView;
	}

	@RequestMapping(value = "/admin/newUser", method = RequestMethod.POST)
	public String processCreateUser(
			@ModelAttribute(value = "newUser") User newUser, String role,
			Long uniteB, RedirectAttributes redirectAttributes) {
		if (role.equals("ROLE_USER")) {
			List<Role> roles = new ArrayList<Role>();
			roles.add(roleService.findByName(role));
			newUser.setRoles(roles);
		} else if (role.equals("ROLE_BUREAU_ORDRE")) {
			List<Role> roles = new ArrayList<Role>();
			roles.add(roleService.findByName("ROLE_BUREAU_ORDRE"));
			newUser.setRoles(roles);
		} else {
			List<Role> roles = new ArrayList<Role>();
			roles.add(roleService.findByName("ROLE_ADMIN"));
			newUser.setRoles(roles);
		}

		UniteBancaire uniteBancaire = uniteBancaireService
				.findUniteBancaireById(uniteB);
		newUser.setUniteBancaire(uniteBancaire);

		newUser.setEnabled(true);
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(newUser.getPassword());
		newUser.setPassword(hashedPassword);
		if(userService.findUserByLogin(newUser.getLogin()) != null && userService.findUserByEmail(newUser.getEmail()) != null){
			redirectAttributes.addFlashAttribute("newUser", newUser);
			redirectAttributes.addFlashAttribute("newUserWrongEmail", newUser);
			return "redirect:/admin/newUser";
		}
		else{
		if(userService.findUserByLogin(newUser.getLogin()) != null){
			redirectAttributes.addFlashAttribute("newUser", newUser);
			return "redirect:/admin/newUser";
		}
		else {
			if(userService.findUserByEmail(newUser.getEmail()) != null){
				redirectAttributes.addFlashAttribute("newUserWrongEmail", newUser);
				return "redirect:/admin/newUser";
			}
			
		else{
		userService.CreateUser(newUser);
		return "redirect:/admin/" + newUser.getId();
		}
		}
		}
	}

	@RequestMapping(value = "/admin/{userId}")
	public ModelAndView showUserAfterCreate(@PathVariable("userId") long userId) {
		ModelAndView modelAndView = new ModelAndView(
				"admin/showUserAfterCreate.jsp");
		User user = userService.findUSerById(userId);
		modelAndView.addObject("createdUser", user);
		modelAndView.addObject("role", user.getRoles().get(0).getName());
		if(user.getUniteBancaire() != null)
		modelAndView.addObject("uniteBancaire", user.getUniteBancaire()
				.getNom());
		/*if(user.getUniteBancaire() == null)
			modelAndView.addObject("haveUB", 0);
		else
			modelAndView.addObject("haveUB", 1);*/
		return modelAndView;
	}

	@RequestMapping(value = "/admin/{userId}-edit", method = RequestMethod.GET)
	public ModelAndView initUpdateOwnerForm(@PathVariable("userId") long userId) {
		ModelAndView modelAndView = new ModelAndView("admin/createUser.jsp");
		User newUser = this.userService.findUSerById(userId);
		modelAndView.addObject("newUser", newUser);
		// modelAndView.addObject("role", newUser.getRoles().get(0).getName());
		modelAndView.addObject("role", newUser.getRoles());
		List<UniteBancaire> uniteBancaires = uniteBancaireService
				.getAllUniteBancaire();
		modelAndView.addObject("uniteBancaires", uniteBancaires);
		return modelAndView;
	}

	@RequestMapping(value = "/admin/{userId}-edit", method = RequestMethod.POST)
	public String processUpdateOwnerForm(User newUser, String role,
			@PathVariable("userId") long userId, Long uniteB) {
		User user = userService.findUSerById(userId);
		List<Role> roles = new ArrayList<Role>();
		if (role.equals("ROLE_ADMIN")) {
			roles.add(roleService.findByName("ROLE_ADMIN"));
		} else {
			roles.add(roleService.findByName(role));
			// newUser.setRoles(roles);
		}

		newUser.setRoles(roles);

		newUser.setId(userId);
		newUser.setEnabled(true);
		UniteBancaire uniteBancaire = uniteBancaireService
				.findUniteBancaireById(uniteB);
		newUser.setUniteBancaire(uniteBancaire);

		if (!newUser.getPassword().equals(user.getPassword())) {
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			String hashedPassword = passwordEncoder.encode(newUser
					.getPassword());
			newUser.setPassword(hashedPassword);
		}
		this.userService.updateUser(newUser);
		return "redirect:/admin/" + newUser.getId();
	}
	
	
	// ******** activer un utilsateur ***********//
	
	
	@RequestMapping(value = "/admin/{userId}-enabled")
	public String enableUser(@PathVariable("userId") long userId) {

		User user = userService.findUSerById(userId);
		if (user.isEnabled() == false)
			user.setEnabled(true);
		else
			user.setEnabled(false);
		globalCrudService.update(user);
		return "redirect:/admin/allUsers";

	}
	
	// ******** desactiver un utilsateur ***********//
	
	
		/*@RequestMapping(value="/admin/{userId}-disable")
		public String disableUser(@PathVariable("userId") long userId){
			
			User user = userService.findUSerById(userId);
			user.setEnabled(false);
			
			globalCrudService.update(user);
			return "redirect:/admin/allUsers";
			
		}*/
		
		
		// ************ All users *****************//
	@RequestMapping(value = "/admin/allUsers", method = RequestMethod.GET)
	public ModelAndView showAllUsers() {
		ModelAndView modelAndView = new ModelAndView("admin/allUsers.jsp");
		List<User> users = userService.getAllUsers();

		for (int i = 0; i < users.size(); i++) {
			System.out.println(users.get(i).toString());
		}
		modelAndView.addObject("users", users);
		return modelAndView;
	}
		
		// ************ delete user *****************//
	@RequestMapping(value = "/admin/{userId}/delete", method = RequestMethod.GET)
	public String processDeleteUser(@PathVariable("userId") long userId) {
		User user = userService.findUSerById(userId);
		this.userService.deleteUser(user);
		return "redirect:/admin/allUsers";
	}
		
		
		//courriers recus
		
	@RequestMapping(value = "/user/Courrier-recus")
	public ModelAndView courriersRecus() {
		ModelAndView modelAndView = new ModelAndView("user/courriersRecus.jsp");

		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		UserDetails userDetail = (UserDetails) auth.getPrincipal();
		User connectedUser = userService.findUserByLogin(userDetail
				.getUsername());
		System.out.println(userDetail.getUsername());

		List<Courrier> courriers = userService
				.getCourrierDestinataire(connectedUser);
		for (Courrier courrier : courriers) {
			System.out.println(courrier);
		}

		modelAndView.addObject("courriers", courriers);
		List<User> users = userService.getAllUsers();
		modelAndView.addObject("users", users);
		
		List<UniteBancaire> uniteBancaires = uniteBancaireService.getAllUniteBancaire();
		modelAndView.addObject("uniteBancaires", uniteBancaires);
		
		List<ContactExterne> contactExternes = contactExterneService.getAllContactExterne();
		modelAndView.addObject("contactExternes", contactExternes);
		return modelAndView;
	}
		
		//courriers envoyes
		
	@RequestMapping(value = "/user/Courrier-envoyes")
	public ModelAndView courriersEnvoyes() {
		ModelAndView modelAndView = new ModelAndView(
				"user/courriersEnvoyes.jsp");

		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		UserDetails userDetail = (UserDetails) auth.getPrincipal();
		User connectedUser = userService.findUserByLogin(userDetail
				.getUsername());

		System.out.println(connectedUser);

		List<Courrier> courriers = userService
				.getCourrierEmetteur(connectedUser);

		modelAndView.addObject("courriers", courriers);
		List<User> users = userService.getAllUsers();
		modelAndView.addObject("users", users);
		
		List<UniteBancaire> uniteBancaires = uniteBancaireService.getAllUniteBancaire();
		modelAndView.addObject("uniteBancaires", uniteBancaires);
		
		List<ContactExterne> contactExternes = contactExterneService.getAllContactExterne();
		modelAndView.addObject("contactExternes", contactExternes);
		return modelAndView;
	}
		
		
		//courriers en attente
		
	@RequestMapping(value = "/user/Courrier-attente")
	public ModelAndView courriersEnAttente() {
		ModelAndView modelAndView = new ModelAndView(
				"user/courriersEnAttente.jsp");

		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		UserDetails userDetail = (UserDetails) auth.getPrincipal();
		User connectedUser = userService.findUserByLogin(userDetail
				.getUsername());

		System.out.println(connectedUser);

		List<Courrier> courriers = userService.getCourrierEnAttente(connectedUser);

		modelAndView.addObject("courriers", courriers);
		List<User> users = userService.getAllUsers();
		modelAndView.addObject("users", users);
		
		List<UniteBancaire> uniteBancaires = uniteBancaireService.getAllUniteBancaire();
		modelAndView.addObject("uniteBancaires", uniteBancaires);
		
		List<ContactExterne> contactExternes = contactExterneService.getAllContactExterne();
		modelAndView.addObject("contactExternes", contactExternes);
		return modelAndView;
	}
				
				
				
				//Confirmer courrier
				
	@RequestMapping(value = "/user/courrier-{idCourrier}/confirmer")
	public String confirmerCourrier(@PathVariable("idCourrier") long idCourrier) {

		Courrier courrier = courrierService.findCourrierById(idCourrier);
		courrier.setRecu(true);
		globalCrudService.update(courrier);
		return "redirect:/user/Courrier-attente";
	}
	
	//consult courrier
		@RequestMapping(value = "/user/courrier-{idCourrier}")
		public ModelAndView showCourrier(@PathVariable("idCourrier") long idCourrier) {
			ModelAndView modelAndView = new ModelAndView(
					"user/showCourrier.jsp");
			Courrier createdCourrier = courrierService.findCourrierById(idCourrier);
			modelAndView.addObject("createdCourrier", createdCourrier);
			
			List<Transfert> transferts = transfertService.getTransfertsByCourrier(idCourrier);
			modelAndView.addObject("transferts", transferts);
			
			
			
			
			return modelAndView;
		}

}
