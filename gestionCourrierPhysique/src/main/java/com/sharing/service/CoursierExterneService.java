package com.sharing.service;

import java.util.List;

import com.sharing.entity.ContactExterne;
import com.sharing.entity.CoursierExterne;
import com.sharing.entity.TransporteurExterne;


public interface CoursierExterneService {
	
	List<CoursierExterne> getAllCoursierExterne();

	CoursierExterne findCoursierExterneByNameSurname(String nomCoursierExterne, String prenomCoursierExterne);
	
	CoursierExterne findCoursierExterneServiceById(long idCoursierExterneService);

	List<CoursierExterne> getCoursierExternesByTransporteurExterne(
			TransporteurExterne transporteurExterne);
	
	void deleteWithTransporteurExterne(TransporteurExterne transporteurExterne);

}
