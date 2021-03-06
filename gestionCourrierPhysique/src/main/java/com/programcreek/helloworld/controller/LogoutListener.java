package com.programcreek.helloworld.controller;

import java.util.List;

import org.springframework.context.ApplicationListener;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.session.SessionDestroyedEvent;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class LogoutListener implements ApplicationListener<SessionDestroyedEvent> {

    public void onApplicationEvent(SessionDestroyedEvent event)
    {
    	System.out.println("*******************************************");
    	System.out.println("*******************************************");
    	System.out.println("-------------- session detruite -------------");
    	System.out.println("*******************************************");
    	System.out.println("*******************************************");
        List<SecurityContext> lstSecurityContext = event.getSecurityContexts();
        UserDetails ud;
        for (SecurityContext securityContext : lstSecurityContext)
        {
            ud = (UserDetails) securityContext.getAuthentication().getPrincipal();
            
        }
    }

}
