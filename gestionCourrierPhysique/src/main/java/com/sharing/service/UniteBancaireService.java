package com.sharing.service;

import java.util.List;

import com.sharing.entity.UniteBancaire;



public interface UniteBancaireService {
	
	UniteBancaire findUniteBancaireById(long idUniteBancaire);

	List<UniteBancaire> getAllUniteBancaire();

	UniteBancaire findUniteBancaireByName(String nomUniteBancaire);

}
