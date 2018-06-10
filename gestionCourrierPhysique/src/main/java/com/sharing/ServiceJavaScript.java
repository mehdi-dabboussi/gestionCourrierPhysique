package com.sharing;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.sharing.entity.ContactExterne;
import com.sharing.entity.Courrier;
import com.sharing.entity.Emetteur_Recepteur;
import com.sharing.entity.Role;
import com.sharing.entity.UniteBancaire;
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
	
	/*public static String getNom(Object o, Emetteur_Recepteur emetteur_Recepteur,List<User> listUser,
			List<UniteBancaire> listUniteBancaire, List<ContactExterne> listContactExterne){
		
		if(o.equals("user")){
			for (User user:listUser){
				if (user.getId() == emetteur_Recepteur.getId())
					return user.getSurName() + " " + user.getNom();
			}
		}
		
		if(o.equals("unite")){
			for (UniteBancaire unite:listUniteBancaire){
				if (unite.getId() == emetteur_Recepteur.getId())
					return unite.getNom();
			}
		}
		
		if(o.equals("contact")){
			for (ContactExterne contact:listContactExterne){
				if (contact.getId() == emetteur_Recepteur.getId())
					return contact.getNom();
			}
		}
		
		return null;
	}*/

}