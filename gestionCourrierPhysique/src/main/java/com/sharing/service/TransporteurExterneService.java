package com.sharing.service;

import java.util.List;

import com.sharing.entity.Courrier;
import com.sharing.entity.TransporteurExterne;


public interface TransporteurExterneService {
	
	List<TransporteurExterne> getAllTransporteurExterne();

	TransporteurExterne findTransporteurExterneByName(String nomTransporteurExterne);

	TransporteurExterne findTransporteurExterneById(long idTransporteurExterne);
	
	Integer countBordereau(TransporteurExterne transporteurExterne);
	
}
