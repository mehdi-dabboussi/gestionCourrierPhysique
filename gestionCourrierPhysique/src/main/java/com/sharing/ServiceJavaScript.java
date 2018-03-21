package com.sharing;

import java.util.List;

import com.sharing.entity.Role;

public class ServiceJavaScript {
	public static boolean contains(List<Role> list, Object o) {

		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getName().equals(o)) {
				return true;
			}
		}
		return false;
	}

}