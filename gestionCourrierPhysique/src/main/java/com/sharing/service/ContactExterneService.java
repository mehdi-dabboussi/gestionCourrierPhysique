package com.sharing.service;

import java.util.List;

import com.sharing.entity.ContactExterne;
import com.sharing.entity.UniteBancaire;


public interface ContactExterneService {
	
	List<ContactExterne> getAllContactExterne();

	ContactExterne findContactExterneByName(String nomContactExterne);
	
	ContactExterne findContactExterneById(long idContactExterne);

}
