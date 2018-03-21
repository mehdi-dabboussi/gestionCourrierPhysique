package com.programcreek.helloworld.controller;

import java.util.List;

import org.springframework.context.ApplicationListener;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.session.SessionDestroyedEvent;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

@Component
@Controller
public class SessionExpiration implements ApplicationListener<SessionDestroyedEvent> {

    public String onApplicationEvent1(SessionDestroyedEvent event)
    {
    	System.out.println("lougout from controller");
        List<SecurityContext> lstSecurityContext = event.getSecurityContexts();
        UserDetails ud;
        for (SecurityContext securityContext : lstSecurityContext)
        {
            ud = (UserDetails) securityContext.getAuthentication().getPrincipal();
            
        }
        return "login.jsp";
    }

	@Override
	public void onApplicationEvent(SessionDestroyedEvent arg0) {
    	System.out.println("lougout from controller onApplicationEvent");
		
	}
    
}


