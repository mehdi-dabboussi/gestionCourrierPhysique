package com.programcreek.helloworld.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.sharing.entity.TransporteurExterne;
import com.sharing.entity.UniteBancaire;
import com.sharing.service.BordereauService;
import com.sharing.service.TransporteurExterneService;
import com.sharing.service.UniteBancaireService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


@Controller
public class chartsController {
	
	private UniteBancaireService uniteBancaireService;
	private TransporteurExterneService transporteurExterneService;
	private BordereauService bordereauService;
	
	@Autowired
	public chartsController(UniteBancaireService uniteBancaireService, TransporteurExterneService transporteurExterneService,
			BordereauService bordereauService) {
		this.uniteBancaireService = uniteBancaireService;
		this.transporteurExterneService  = transporteurExterneService;
		this.bordereauService = bordereauService;
	}
	
	@RequestMapping(value = "/admin/charts")
	public ModelAndView getCharts(){
		
		ModelAndView modelAndView = new ModelAndView("/admin/chart.jsp");
		 
		 List<List<Map<Object,Object>>> list = new ArrayList<List<Map<Object,Object>>>();
		 List<List<Map<Object,Object>>> list2 = new ArrayList<List<Map<Object,Object>>>();
		 
		 List<List<Map<Object,Object>>> listTransporteurs = new ArrayList<List<Map<Object,Object>>>();
		 List<Map<Object,Object>> dataPointsTransporteur = new ArrayList<Map<Object,Object>>();
		 
		 List<UniteBancaire> uniteBancaires = uniteBancaireService.getAllUniteBancaire();
		 List<Map<Object,Object>> dataPoints1 = new ArrayList<Map<Object,Object>>();
		 List<Map<Object,Object>> dataPoints2 = new ArrayList<Map<Object,Object>>();
		 
		 List<Map<Object,Object>> dataPoints3 = new ArrayList<Map<Object,Object>>();
		 List<Map<Object,Object>> dataPoints4 = new ArrayList<Map<Object,Object>>();
		 for (UniteBancaire uniteBancaire : uniteBancaires){
			 Map<Object,Object> map = null;
			 Map<Object,Object> map2 = null;
			 map = new HashMap<Object,Object>(); map.put("label", uniteBancaire.getNom()); map.put("y", uniteBancaireService.countCourreirArrivé(uniteBancaire));
			 dataPoints1.add(map);
			 
			 map2 = new HashMap<Object,Object>(); map2.put("label", uniteBancaire.getNom()); map2.put("y", uniteBancaireService.countCourreirDepart(uniteBancaire));
			 dataPoints3.add(map2);
			 
			 map = new HashMap<Object,Object>(); map.put("label", uniteBancaire.getNom()); map.put("y", uniteBancaireService.countCourreirDepart(uniteBancaire));
			 dataPoints2.add(map);
			 
			 map2 = new HashMap<Object,Object>(); map2.put("label", uniteBancaire.getNom()); map2.put("y", uniteBancaireService.countCourrierReci(uniteBancaire));
			 dataPoints4.add(map2);
		 }
		 list.add(dataPoints1);
		 list.add(dataPoints2);
		 
		 list2.add(dataPoints3);
		 list2.add(dataPoints4);
		 
		 
		 List<TransporteurExterne> transporteurExternes = transporteurExterneService.getAllTransporteurExterne();
		 long pourcentage = 1;
		 for (TransporteurExterne transporteurExterne : transporteurExternes) {
			 Map<Object,Object> map = null;
			 if(bordereauService.getTotalBordereau() != 0){
			 pourcentage = transporteurExterneService.countBordereau(transporteurExterne) * 100 / bordereauService.getTotalBordereau();}
			 map = new HashMap<Object,Object>(); map.put("name", transporteurExterne.getNomTransporteurExterne()); map.put("y",pourcentage );
			 dataPointsTransporteur.add(map);
		}
		 
		 listTransporteurs.add(dataPointsTransporteur);
		 
		 modelAndView.addObject("dataPointsList", list);
		 modelAndView.addObject("dataPointsList2", list2);
		 modelAndView.addObject("dataPointsListTrans", listTransporteurs);
		 return modelAndView;
	}

}
