package com.sharing;

import java.util.List;

import com.sharing.entity.Courrier;
import com.sharing.entity.Role;
import com.sharing.entity.User;

public class ServiceJavaScript {
	public static boolean contains(List<Role> list, Object o) {

		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getName().equals(o)) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean enabled(User u){
		if(u.isEnabled())
			return true;
		return false;
	}
	
	public static boolean courrierArrive(Courrier courrier){
		if(courrier.getEtatCourrier().equals("arrive"))
			return true;
		return false;
		
	}
	
	public static boolean courrierDepart(Courrier courrier){
		if(courrier.getEtatCourrier().equals("depart"))
			return true;
		return false;
		
	}
	
	public static boolean notNull(Object o){
		if (o == null)
			return false;
		return true;
	}

}