package com.sharing.service;

import java.util.List;

import com.sharing.entity.SousContactExterne;


public interface SousContactExterneService {

	List<SousContactExterne> getAllSousContactExterne();

	SousContactExterne findSousContactExterneByNameSurname(String nomSousContactExterne, String prenomSousContactExterne);

	SousContactExterne findSousContactExterneById(long idSousContactExterne);
	
}
