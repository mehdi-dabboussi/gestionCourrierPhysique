package com.sharing.service;

import java.util.List;

import com.sharing.entity.ContactExterne;
import com.sharing.entity.SousContactExterne;
import com.sharing.entity.UniteBancaire;
import com.sharing.entity.User;


public interface SousContactExterneService {

	List<SousContactExterne> getAllSousContactExterne();

	SousContactExterne findSousContactExterneByNameSurname(String nomSousContactExterne, String prenomSousContactExterne);

	SousContactExterne findSousContactExterneById(long idSousContactExterne);
	
	List<SousContactExterne> getSousContactsByContact(ContactExterne contactExterne);
	
	void deleteWithContactExterne(ContactExterne contactExterne);
	
}
