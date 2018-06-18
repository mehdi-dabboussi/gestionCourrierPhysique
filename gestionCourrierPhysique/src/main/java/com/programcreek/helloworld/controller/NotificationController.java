package com.programcreek.helloworld.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.sharing.entity.Notification;
import com.sharing.entity.User;
import com.sharing.service.NotificationService;
import com.sharing.service.UserService;


@ControllerAdvice
public class NotificationController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private NotificationService notificationService;
	
	@ModelAttribute("notifications")
	public List<Notification> populateNotif(){
		
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		if (!(auth.getPrincipal().equals("anonymousUser"))) {
		UserDetails userDetail = (UserDetails) auth.getPrincipal();
		User user = userService.findUserByLogin(userDetail.getUsername());
		
		return notificationService.getNotifications(user);
		}
		return null;
		
	}

}
