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
import com.sharing.entity.TransporteurExterne;
import com.sharing.entity.UniteBancaire;
import com.sharing.entity.User;
import com.sharing.service.ContactExterneService;
import com.sharing.service.CoursierExterneService;
import com.sharing.service.GlobalCrudService;
import com.sharing.service.RoleService;
import com.sharing.service.SousContactExterneService;
import com.sharing.service.TransporteurExterneService;
import com.sharing.service.UniteBancaireService;
import com.sharing.service.UserService;

@Controller
public class ProfileController {

	private UserService userService;
	private UniteBancaireService uniteBancaireService;
	private RoleService roleService;
	private GlobalCrudService globalCrudService;

	@Autowired
	public ProfileController(UserService userService, RoleService roleService,
			GlobalCrudService globalCrudService,
			UniteBancaireService uniteBancaireService,
			ContactExterneService contactExterneService,
			SousContactExterneService sousContactExterneService,
			TransporteurExterneService transporteurExterneService,
			CoursierExterneService coursierExterneService) {
		this.userService = userService;
		this.roleService = roleService;
		this.globalCrudService = globalCrudService;
		this.uniteBancaireService = uniteBancaireService;
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

}
