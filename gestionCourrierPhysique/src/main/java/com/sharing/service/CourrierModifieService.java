package com.sharing.service;

import java.util.List;

import com.sharing.entity.CourrierModifie;

public interface CourrierModifieService {
	
	List<CourrierModifie> getAll();
	
	CourrierModifie findCourrierModifieById(long idCourrierModifie);

}
