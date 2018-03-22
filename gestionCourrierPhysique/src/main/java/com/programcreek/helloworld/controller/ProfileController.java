package com.programcreek.helloworld.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.sharing.entity.ContactExterne;
import com.sharing.entity.Role;
import com.sharing.entity.SousContactExterne;
import com.sharing.entity.UniteBancaire;
import com.sharing.entity.User;
import com.sharing.service.ContactExterneService;
import com.sharing.service.GlobalCrudService;
import com.sharing.service.RoleService;
import com.sharing.service.SousContactExterneService;
import com.sharing.service.UniteBancaireService;
import com.sharing.service.UserService;

@Controller
public class ProfileController {

	private UserService userService;
	private UniteBancaireService uniteBancaireService;
	private RoleService roleService;
	private GlobalCrudService globalCrudService;
	private ContactExterneService contactExterneService;
	private SousContactExterneService sousContactExterneService;

	@Autowired
	public ProfileController(UserService userService, RoleService roleService,
			GlobalCrudService globalCrudService, UniteBancaireService uniteBancaireService, 
			ContactExterneService contactExterneService, SousContactExterneService sousContactExterneService) {
		this.userService = userService;
		this.roleService = roleService;
		this.globalCrudService = globalCrudService;
		this.uniteBancaireService = uniteBancaireService;
		this.contactExterneService = contactExterneService;
		this.sousContactExterneService = sousContactExterneService;
	}

	@RequestMapping(value = "/admin/newUser", method = RequestMethod.GET)
	public ModelAndView createUser() {
		ModelAndView modelAndView = new ModelAndView("admin/createUser.jsp");
		User newUser = new User();
		modelAndView.addObject("newUser", newUser);
		List<UniteBancaire> uniteBancaires = uniteBancaireService.getAllUniteBancaire();
		modelAndView.addObject("uniteBancaires", uniteBancaires);

		// Connected user
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetail = (UserDetails) auth.getPrincipal();
		User connectedUser = userService.findUserByLogin(userDetail.getUsername());
		modelAndView.addObject("connectedUser", connectedUser);
		modelAndView.addObject("role", connectedUser.getRoles());

		return modelAndView;
	}

	@RequestMapping(value = "/admin/newUser", method = RequestMethod.POST)
	public String processCreateUser(@ModelAttribute(value = "newUser") User newUser, String role, Long uniteB) {
		if (role.equals("ROLE_USER")) {
			List<Role> roles = new ArrayList<Role>();
			roles.add(roleService.findByName(role));
			newUser.setRoles(roles);
		} else if (role.equals("ROLE_BUREAU_ORDRE")){
			List<Role> roles = new ArrayList<Role>();
			roles.add(roleService.findByName("ROLE_BUREAU_ORDRE"));
			newUser.setRoles(roles);
		}
		else{
			List<Role> roles = new ArrayList<Role>();
			roles.add(roleService.findByName("ROLE_ADMIN"));
			newUser.setRoles(roles);
		}
		
		UniteBancaire uniteBancaire = uniteBancaireService.findUniteBancaireById(uniteB);
		newUser.setUniteBancaire(uniteBancaire);
	

		newUser.setEnabled(true);
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(newUser.getPassword());
		newUser.setPassword(hashedPassword);
		userService.CreateUser(newUser);
		return "redirect:/admin/" + newUser.getIdUser();
	}
	
	

	@RequestMapping(value = "/admin/{userId}")
	public ModelAndView showUserAfterCreate(@PathVariable("userId") long userId) {
		ModelAndView modelAndView = new ModelAndView(
				"admin/showUserAfterCreate.jsp");
		User user = userService.findUSerById(userId);
		modelAndView.addObject("createdUser", user);
		modelAndView.addObject("role", user.getRoles().get(0).getName());
		modelAndView.addObject("uniteBancaire", user.getUniteBancaire().getNomUniteBancaire());
		return modelAndView;
	}

	@RequestMapping(value = "/admin/{userId}-edit", method = RequestMethod.GET)
	public ModelAndView initUpdateOwnerForm(@PathVariable("userId") long userId) {
		ModelAndView modelAndView = new ModelAndView("admin/createUser.jsp");
		User newUser = this.userService.findUSerById(userId);
		modelAndView.addObject("newUser", newUser);
		// modelAndView.addObject("role", newUser.getRoles().get(0).getName());
		modelAndView.addObject("role", newUser.getRoles());
		List<UniteBancaire> uniteBancaires = uniteBancaireService.getAllUniteBancaire();
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
		} 
		else{
			roles.add(roleService.findByName(role));
			// newUser.setRoles(roles);
		}

		newUser.setRoles(roles);

		newUser.setIdUser(userId);
		newUser.setEnabled(true);
		UniteBancaire uniteBancaire = uniteBancaireService.findUniteBancaireById(uniteB);
		newUser.setUniteBancaire(uniteBancaire);

		if (!newUser.getPassword().equals(user.getPassword())) {
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			String hashedPassword = passwordEncoder.encode(newUser
					.getPassword());
			newUser.setPassword(hashedPassword);
		}
		this.userService.updateUser(newUser);
		return "redirect:/admin/" + newUser.getIdUser();
	}

	// ******** My Profile section ***********//
	@RequestMapping(value = "/home/myProfile")
	public ModelAndView showMyProfile() {
		ModelAndView modelAndView = new ModelAndView("home/showMyProfile.jsp");
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		UserDetails userDetail = (UserDetails) auth.getPrincipal();
		User user = userService.findUserByLogin(userDetail.getUsername());
		modelAndView.addObject("createdUser", user);
		modelAndView.addObject("role", user.getRoles().get(0).getName());
		return modelAndView;
	}

	@RequestMapping(value = "/home/myProfileUpdate", method = RequestMethod.GET)
	public ModelAndView initUpdateMyProfileForm() {
		ModelAndView modelAndView = new ModelAndView("home/myProfileUpdate.jsp");
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		UserDetails userDetail = (UserDetails) auth.getPrincipal();
		User newUser = userService.findUserByLogin(userDetail.getUsername());
		modelAndView.addObject("newUser", newUser);
		modelAndView.addObject("role", newUser.getRoles().get(0).getName());
		return modelAndView;
	}

	@RequestMapping(value = "/home/myProfileUpdate", method = RequestMethod.POST)
	public String processUpdateMyProfileForm(
			@ModelAttribute("newUser") User newUser, String role) {
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		UserDetails userDetail = (UserDetails) auth.getPrincipal();
		User user = userService.findUserByLogin(userDetail.getUsername());
		if (role != null) {
			if (role.equals("ROLE_USER")) {
				List<Role> roles = new ArrayList<Role>();
				roles.add(roleService.findByName(role));
				newUser.setRoles(roles);
			} else {
				List<Role> roles = new ArrayList<Role>();
				roles.add(roleService.findByName("ROLE_ADMIN"));
				newUser.setRoles(roles);
			}
		} else {
			newUser.setRoles(user.getRoles());
		}

		newUser.setIdUser(user.getIdUser());
		newUser.setEnabled(true);

		if (!newUser.getPassword().equals(user.getPassword())) {
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			String hashedPassword = passwordEncoder.encode(newUser
					.getPassword());
			newUser.setPassword(hashedPassword);
		}

		this.userService.updateUser(newUser);

		return "redirect:/home/myProfile";
	}

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
	
	// ------------------------Unite bancaire ------------------------//
	
	// ************ create unite bancaire *****************//
	@RequestMapping(value = "/admin/newUniteBancaire", method = RequestMethod.GET)
	public ModelAndView createUniteBancaire() {
		ModelAndView modelAndView = new ModelAndView("admin/newUniteBancaire.jsp");
		UniteBancaire newUniteBancaire = new UniteBancaire();
		modelAndView.addObject("newUniteBancaire", newUniteBancaire);

		return modelAndView;
	}
	
	@RequestMapping(value = "/admin/newUniteBancaire", method = RequestMethod.POST)
	public String processCreateUniteBancaire(@ModelAttribute(value = "newUniteBancaire") UniteBancaire newUniteBancaire) {
		//List<User> users = new ArrayList<User>();
		//newUniteBancaire.setUsers(users);
		globalCrudService.save(newUniteBancaire);
		return "redirect:/admin/unite-" + newUniteBancaire.getIdUniteBancaire();
	}
	
	// ************ consult unité bancaire *****************//
	@RequestMapping(value = "/admin/unite-{uniteBancaireId}")
	public ModelAndView showUniteBancaireAfterCreate(@PathVariable("uniteBancaireId") long uniteBancaireId) {
		ModelAndView modelAndView = new ModelAndView(
				"admin/showUniteBancaireAfterCreate.jsp");
		UniteBancaire createdUniteBancaire = uniteBancaireService.findUniteBancaireById(uniteBancaireId);
		modelAndView.addObject("createdUniteBancaire", createdUniteBancaire);
		modelAndView.addObject("users", userService.getUsersByUB(createdUniteBancaire));
		return modelAndView;
	}
	
	// ************ edit unité bancaire *****************//
	@RequestMapping(value = "/admin/unite-{uniteBancaireId}-edit", method = RequestMethod.GET)
	public ModelAndView initUpdateUniteBancaireForm(@PathVariable("uniteBancaireId") long uniteBancaireId) {
		ModelAndView modelAndView = new ModelAndView("admin/newUniteBancaire.jsp");
		UniteBancaire newUniteBancaire = this.uniteBancaireService.findUniteBancaireById(uniteBancaireId);
		modelAndView.addObject("newUniteBancaire", newUniteBancaire);
		return modelAndView;
	}
	
	@RequestMapping(value = "/admin/unite-{uniteBancaireId}-edit", method = RequestMethod.POST)
	public String processUpdateUniteBancaireForm(UniteBancaire newUniteBancaire, 
			@PathVariable("uniteBancaireId") long uniteBancaireId) {
		newUniteBancaire.setIdUniteBancaire(uniteBancaireId);
		this.globalCrudService.update(newUniteBancaire);
		return "redirect:/admin/unite-" + newUniteBancaire.getIdUniteBancaire();
	}
	
	// ************ All unite bancaire *****************//
		@RequestMapping(value = "/admin/allUniteBancaire", method = RequestMethod.GET)
		public ModelAndView showAllUniteBancaire() {
			ModelAndView modelAndView = new ModelAndView("admin/allUnites.jsp");
			List<UniteBancaire> uniteBancaires = uniteBancaireService.getAllUniteBancaire();
			modelAndView.addObject("uniteBancaires", uniteBancaires);
			return modelAndView;
		}
		
		
		// ************ delete unite bancaire *****************//
		
		@RequestMapping(value = "/admin/unite-{uniteBancaireId}/delete", method = RequestMethod.GET)
		public String processDeleteUniteBancaire(@PathVariable("uniteBancaireId") long uniteBancaireId) {
			UniteBancaire uniteBancaire = uniteBancaireService.findUniteBancaireById(uniteBancaireId);
			this.globalCrudService.remove(uniteBancaire,uniteBancaireId);
			return "redirect:/admin/allUniteBancaire";
		}
		// ------------------------Contact Externe ------------------------//
		
		// ************ create contact externe *****************//
		
		@RequestMapping(value="/bo/createContactExterne", method=RequestMethod.GET)
		public ModelAndView createContactExterne (){
			ModelAndView modelAndView = new ModelAndView("bo/createContactExterne.jsp");
			ContactExterne newContactExterne = new ContactExterne();
			modelAndView.addObject("newContactExterne", newContactExterne);
			return modelAndView;
		}
		
		@RequestMapping(value = "/bo/createContactExterne", method = RequestMethod.POST)
		public String processCreateContactExterne(@ModelAttribute(value = "newContactExterne") ContactExterne newContactExterne) {
			globalCrudService.save(newContactExterne);
			return "redirect:/bo/contactexterne-" + newContactExterne.getIdContactExterne();
		}
		
		// ************ consult contact externe *****************//
		@RequestMapping(value = "/bo/contactexterne-{idContactExterne}")
		public ModelAndView showContactExterne(@PathVariable("idContactExterne") long idContactExterne) {
			ModelAndView modelAndView = new ModelAndView(
					"bo/showContactExterne.jsp");
			ContactExterne createdContactExterne = contactExterneService.findContactExterneById(idContactExterne);
			modelAndView.addObject("createdContactExterne", createdContactExterne);
			modelAndView.addObject("sousContacts", sousContactExterneService.getSousContactsByContact(createdContactExterne));
			return modelAndView;
		}
		
		// ************ edit contact externe *****************//
		@RequestMapping(value = "/bo/contactexterne-{idContactExterne}-edit", method = RequestMethod.GET)
		public ModelAndView initUpdateContactExterneForm(@PathVariable("idContactExterne") long idContactExterne) {
			ModelAndView modelAndView = new ModelAndView("bo/createContactExterne.jsp");
			ContactExterne newContactExterne = this.contactExterneService.findContactExterneById(idContactExterne);
			modelAndView.addObject("newContactExterne", newContactExterne);
			return modelAndView;
		}
		
		@RequestMapping(value = "/bo/contactexterne-{idContactExterne}-edit", method = RequestMethod.POST)
		public String processUpdateContactExterneForm(ContactExterne newContactExterne, 
				@PathVariable("idContactExterne") long idContactExterne) {
			newContactExterne.setIdContactExterne(idContactExterne);
			this.globalCrudService.update(newContactExterne);
			return "redirect:/bo/contactexterne-" + newContactExterne.getIdContactExterne();
		}
		
		// ************ All contact externe *****************//
		@RequestMapping(value = "/bo/allContactExterne", method = RequestMethod.GET)
		public ModelAndView showAllContactExterne() {
			ModelAndView modelAndView = new ModelAndView("bo/allContactExterne.jsp");
			List<ContactExterne> contactExternes = contactExterneService.getAllContactExterne();
			modelAndView.addObject("contactExternes", contactExternes);
			return modelAndView;
		}
		
		// ------------------------ Sous contact externe ------------------------//

		@RequestMapping(value="/bo/createSousContact", method=RequestMethod.GET)
		public ModelAndView createSousContactExterne (Long idContactExterne){
			ModelAndView modelAndView = new ModelAndView("bo/createSousContact.jsp");
			SousContactExterne newSousContactExterne = new SousContactExterne();
			ContactExterne contactExterne = contactExterneService.findContactExterneById(idContactExterne);
			System.out.println(contactExterne.getNomContactExterne());
			//newSousContactExterne.setContactExterne(contactExterne);
			modelAndView.addObject("newSousContactExterne", newSousContactExterne);
			modelAndView.addObject("Societe", contactExterne.getNomContactExterne());
			return modelAndView;
		}
		
		@RequestMapping(value = "/bo/createSousContact", method = RequestMethod.POST)
		public String processCreateSousContactExterne(@ModelAttribute(value = "newSousContactExterne") SousContactExterne newSousContactExterne, Long idContactExterne) {
			ContactExterne contactExterne = contactExterneService.findContactExterneById(idContactExterne);
			System.out.println(contactExterne.getNomContactExterne());
			newSousContactExterne.setContactExterne(contactExterne);
			globalCrudService.save(newSousContactExterne);
			return "redirect:/bo/contactexterne-" + newSousContactExterne.getContactExterne().getIdContactExterne();
		}
}
