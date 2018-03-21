package com.sharing.service;


public interface GlobalCrudService {
	Object save(Object object);
	Object getObjectById(Object object,long idTypeGarantie);
	void update(Object object);
	void remove(Object object,long idObject);
	
}
