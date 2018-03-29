package com.sharing.service;

import java.util.List;

import com.sharing.entity.Langue;
import com.sharing.entity.Nature;

public interface LangueService {
	
	List<Langue> getAllLangue();
	Langue findLangueById(long idLangue);

}
