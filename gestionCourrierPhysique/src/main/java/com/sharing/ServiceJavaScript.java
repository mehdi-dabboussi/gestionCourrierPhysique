package com.sharing;

import java.util.List;

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

}