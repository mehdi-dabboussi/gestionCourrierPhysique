package com.sharing.service;

import java.util.List;

import com.sharing.entity.Courrier;
import com.sharing.entity.Emetteur_Recepteur;
import com.sharing.entity.UniteBancaire;
import com.sharing.entity.User;

public interface EmetteurRecepteurService {

	

	Emetteur_Recepteur findUSerById(long prim);

	
}
