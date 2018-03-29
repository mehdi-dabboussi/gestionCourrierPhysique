package com.sharing.service;

import java.util.List;

import com.sharing.entity.Nature;

public interface NatureService {
	
	List<Nature> getAllNature();
	Nature findNatureById(long idNature);

}
