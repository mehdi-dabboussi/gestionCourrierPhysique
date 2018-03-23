package com.programcreek.helloworld.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.sharing.entity.User;
import com.sharing.service.UserService;

@Controller
public class AuthentificationController {
	
	UserService userService;
	
	@Autowired
	public AuthentificationController(UserService userService) {
		this.userService = userService;
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView authenticate(@RequestParam(value = "error", required = false) String error,@RequestParam(value = "logout", required = false) String logout) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (logout==null) {
			System.out.println("is authenticate");
		}
		else {
			System.out.println("is not authenticate");
		}
		
		ModelAndView model = new ModelAndView();
		if (error != null) {
			model.addObject("error", "Invalid username and password!");
		}
		if (logout != null) {
			model.addObject("msg", "You've been logged out successfully.");
		}
		model.setViewName("login.jsp");
		return model;
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView defaultPage(@RequestParam(value = "error", required = false) String error,@RequestParam(value = "logout", required = false) String logout) {
		/*ModelAndView model = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		if (auth != null) {
			System.out.println("is authenticate");
			model.setViewName("showMyProfile.jsp");
			
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			User user = userService.findUserByLogin(userDetail.getUsername());
			model.addObject("createdUser", user);
			model.addObject("role", user.getRoles().get(0).getName());
		}
		else {
		System.out.println("is not authenticate");
		if (error != null) {
			model.addObject("error", "Invalid username and password!");
		}
		if (logout != null) {
			model.addObject("msg", "You've been logged out successfully.");
		}
		model.setViewName("login.jsp");
		}
		return model;*/
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (logout==null) {
			System.out.println("is authenticate");
		}
		else {
			System.out.println("is not authenticate");
		}
		
		ModelAndView model = new ModelAndView();
		if (error != null) {
			model.addObject("error", "Invalid username and password!");
		}
		if (logout != null) {
			model.addObject("msg", "You've been logged out successfully.");
		}
		model.setViewName("login.jsp");
		return model;
	}
	
	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
	    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    if (auth != null){    
	        new SecurityContextLogoutHandler().logout(request, response, auth);
	    }
	    return "redirect:/login?logout";//You can redirect wherever you want, but generally it's a good practice to show login screen again.
	}
	
	@RequestMapping(value = "/deniedAccess/403", method = RequestMethod.GET)
	public ModelAndView accesssDenied() {

		ModelAndView model = new ModelAndView();
		
		//check if user is login
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			System.out.println(userDetail);
		
			model.addObject("username", userDetail.getUsername());
		}
		model.setViewName("deniedAccess/page403.jsp");
		return model;
	}

}
