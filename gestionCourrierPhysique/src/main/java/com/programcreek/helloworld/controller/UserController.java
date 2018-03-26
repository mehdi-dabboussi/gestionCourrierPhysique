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

import com.sharing.entity.Role;
import com.sharing.entity.UniteBancaire;
import com.sharing.entity.User;
import com.sharing.service.GlobalCrudService;
import com.sharing.service.RoleService;
import com.sharing.service.UniteBancaireService;
import com.sharing.service.UserService;


@Controller
public class UserController {
	
	private UserService userService;
	private GlobalCrudService globalCrudService;
	private UniteBancaireService uniteBancaireService;
	private RoleService roleService;
	
	
	@Autowired
	public UserController(UserService userService, GlobalCrudService globalCrudService, 
			UniteBancaireService uniteBancaireService, RoleService roleService) {
		this.userService = userService;
		this.globalCrudService = globalCrudService;
		this.uniteBancaireService = uniteBancaireService;
		this.roleService = roleService;
	}
	
	@RequestMapping(value = "/admin/newUser", method = RequestMethod.GET)
	public ModelAndView createUser() {
		ModelAndView modelAndView = new ModelAndView("admin/createUser.jsp");
		User newUser = new User();
		modelAndView.addObject("newUser", newUser);
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

		return modelAndView;
	}

	@RequestMapping(value = "/admin/newUser", method = RequestMethod.POST)
	public String processCreateUser(
			@ModelAttribute(value = "newUser") User newUser, String role,
			Long uniteB) {
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
		modelAndView.addObject("uniteBancaire", user.getUniteBancaire()
				.getNomUniteBancaire());
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

		newUser.setIdUser(userId);
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
		return "redirect:/admin/" + newUser.getIdUser();
	}
	
	
	// ******** activer un utilsateur ***********//
	
	
	@RequestMapping(value="/admin/{userId}-enabled")
	public String enableUser(@PathVariable("userId") long userId){
		
		User user = userService.findUSerById(userId);
		if(user.isEnabled() == false)
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

}
