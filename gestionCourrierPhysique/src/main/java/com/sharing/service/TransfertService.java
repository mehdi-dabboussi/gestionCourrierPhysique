package com.sharing.service;

import java.util.List;

import com.sharing.entity.Transfert;

public interface TransfertService {
	
	Transfert findTransfertById( long idTransfert);
	List<Transfert> getAllTransfert();
	List<Transfert> getTransfertsByCourrier(long idCourrier);
	

}