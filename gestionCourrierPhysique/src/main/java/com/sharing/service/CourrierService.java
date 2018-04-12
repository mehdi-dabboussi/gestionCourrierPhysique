package com.sharing.service;

import java.util.List;

import com.sharing.entity.Courrier;


public interface CourrierService {
	
	Courrier findCourrierById(long idCourrier);

	List<Courrier> getAllCourrier();

}
