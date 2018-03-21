package com.programcreek.helloworld.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.sharing.entity.User;
import com.sharing.service.UserService;

@Controller
public class HomeController {

	private UserService userService;

	@Autowired
	public HomeController(UserService userService) {
		this.userService = userService;
	}

	@RequestMapping(value = "/calendar", method = RequestMethod.GET)
	public ModelAndView getcalendar() {
		ModelAndView modelAndView = new ModelAndView("form.jsp");
		return modelAndView;
	}

	@RequestMapping(value = "/calendar", method = RequestMethod.POST)
	public ModelAndView goTocalendar() {
		ModelAndView modelAndView = new ModelAndView("form.jsp");
		return modelAndView;
	}
	
	@RequestMapping(value = "/dynamicTable", method = RequestMethod.GET)
	public ModelAndView getDynamicTable() {
		ModelAndView modelAndView = new ModelAndView("tables_dynamic.jsp");
		return modelAndView;
	}

	@RequestMapping(value = "/dynamicTable", method = RequestMethod.POST)
	public ModelAndView goTodynamicTable() {
		ModelAndView modelAndView = new ModelAndView("tables_dynamic.jsp");
		return modelAndView;
	}
	
	
	
	@RequestMapping(value = "/echarts", method = RequestMethod.GET)
	public ModelAndView getEcharts() {
		ModelAndView modelAndView = new ModelAndView("echarts.jsp");
		modelAndView.addObject("val1", 10);
		modelAndView.addObject("val2", 50);
		modelAndView.addObject("val3", 100);
		return modelAndView;
	}

	@RequestMapping(value = "/echarts", method = RequestMethod.POST)
	public ModelAndView goToEcharts() {
		ModelAndView modelAndView = new ModelAndView("echarts.jsp");
		return modelAndView;
	}
	@RequestMapping(value = "/gElements", method = RequestMethod.GET)
	public ModelAndView getGeneral_elements() {
		ModelAndView modelAndView = new ModelAndView("general_elements.jsp");
		return modelAndView;
	}

	@RequestMapping(value = "/gElements", method = RequestMethod.POST)
	public ModelAndView goToGeneral_elements() {
		ModelAndView modelAndView = new ModelAndView("general_elements.jsp");
		return modelAndView;
	}	
	
	
	@RequestMapping(value = "vierge/model", method = RequestMethod.GET)
	public String getModel(Model model) {
		
		System.out.println("**********************************************");
		System.out.println("**********************************************");
		    return "/viergeModel/model.jsp";
	}

	/*@RequestMapping(value = "vierge/model", method = RequestMethod.POST)
	public ModelAndView goToModel() {
		ModelAndView modelAndView = new ModelAndView("viergeModel/model.jsp");
		return modelAndView;
	}	*/
}
