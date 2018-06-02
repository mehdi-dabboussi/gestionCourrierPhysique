package com.sharing.service;

import java.util.List;
import java.util.TreeSet;

import com.sharing.entity.Bordereau;

public interface BordereauService {
	
	Bordereau findBordereauById(long idBordereau);
	
	List<Bordereau> getAllBordereau();
	
	TreeSet<String> getAllVille();

}
