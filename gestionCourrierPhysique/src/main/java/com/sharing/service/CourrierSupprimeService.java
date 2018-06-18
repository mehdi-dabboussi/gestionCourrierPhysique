package com.sharing.service;

import java.util.List;

import com.sharing.entity.CourrierSupprime;

public interface CourrierSupprimeService {
	
	List<CourrierSupprime> getAll();
	
	CourrierSupprime findCourrierSupprimeById(long idCourrierSupprime);

}
