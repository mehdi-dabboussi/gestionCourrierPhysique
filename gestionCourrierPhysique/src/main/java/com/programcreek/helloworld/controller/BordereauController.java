package com.programcreek.helloworld.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;












import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.Gson;
import com.sharing.entity.Bordereau;
import com.sharing.entity.Courrier;
import com.sharing.entity.CoursierExterne;
import com.sharing.entity.Transfert;
import com.sharing.entity.TransporteurExterne;
import com.sharing.entity.User;
import com.sharing.service.BordereauService;
import com.sharing.service.CourrierService;
import com.sharing.service.CoursierExterneService;
import com.sharing.service.GlobalCrudService;
import com.sharing.service.TransfertService;
import com.sharing.service.TransporteurExterneService;
import com.sharing.service.UserService;


@Controller
public class BordereauController {
	
	private GlobalCrudService globalCrudService;
	private BordereauService bordereauService;
	private CourrierService courrierService;
	private TransfertService transfertService;
	private UserService userService;
	private TransporteurExterneService transporteurExterneService;
	private CoursierExterneService coursierExterneService;
	
	@Autowired
	public BordereauController(GlobalCrudService globalCrudService, BordereauService bordereauService,
			CourrierService courrierService, TransfertService transfertService, UserService userService,
			TransporteurExterneService transporteurExterneService, CoursierExterneService coursierExterneService){
		this.globalCrudService = globalCrudService;
		this.bordereauService = bordereauService;
		this.courrierService = courrierService;
		this.transfertService = transfertService;
		this.userService = userService;
		this.transporteurExterneService = transporteurExterneService;
		this.coursierExterneService = coursierExterneService;
	}
	
	
	@RequestMapping(value = "/bo/generateBordereau", method=RequestMethod.GET)
	public ModelAndView initChoisirVille(){
		ModelAndView modelAndView = new ModelAndView("bo/bordereauSelectVille.jsp");
		modelAndView.addObject("villes", bordereauService.getAllVille());
		return modelAndView;
	}
	
	
	@RequestMapping(value = "/bo/generateBordereau", method=RequestMethod.POST)
	public String processChoisirVille(String selectVille, RedirectAttributes redirectAttributes){
		
		redirectAttributes.addFlashAttribute("selectVille", selectVille);
		return "redirect:/bo/generateBordereauFinal";
	}
	
	@RequestMapping(value = "/bo/generateBordereauFinal", method=RequestMethod.GET)
	public ModelAndView processGenerateCourrier(Model model){
		
		ModelAndView modelAndView = new ModelAndView("bo/generateBordereau.jsp");
		
		String selectVille = (String) model.asMap().get("selectVille");
		System.out.println(selectVille);
		if(selectVille == null)
			return new ModelAndView("redirect:/bo/generateBordereau");
		
		List<Courrier> courriers = courrierService.getAllCourrier();
		List<TransporteurExterne> transporteurs = transporteurExterneService.getAllTransporteurExterne();
		
		//connected user
				Authentication auth = SecurityContextHolder.getContext()
						.getAuthentication();
				UserDetails userDetail = (UserDetails) auth.getPrincipal();
				User connectedUser = userService.findUserByLogin(userDetail
						.getUsername());
				
				
				ArrayList<Transfert> transfertsBord = new ArrayList<Transfert>();
				
				
				for (Courrier courrier : courriers){
					if(courrier.getTransferts().size() > 0){
					int last = courrier.getTransferts().size() - 1;
					String villeDestinataire;
					if(courrier.getTransferts().get(last).getDestinataireType().equals("unite"))
						villeDestinataire = courrier.getTransferts().get(last).getDestinataireUnite().getVille();
					else
						villeDestinataire = courrier.getTransferts().get(last).getDestinataireContact().getVille();
					if(courrier.getTransferts().get(last).getEmetteurUser().getLogin().equals(connectedUser.getLogin())
							&& villeDestinataire.equals(selectVille) )
					{
						transfertsBord.add(courrier.getTransferts().get(last));
					}
					}
				}
		
				modelAndView.addObject("transferts", transfertsBord);
				modelAndView.addObject("ville", selectVille);		
				modelAndView.addObject("transporteurs", transporteurs);
		
		return modelAndView;
	}
	
	
	@RequestMapping(value = "/bo/generateBordereauFinal", method=RequestMethod.POST)
	public String processGenerateBordereauFinal(HttpServletRequest request, String transporteur, String coursier){
		
		Bordereau bordereau = new Bordereau();
		
		ArrayList<Transfert> transferts = new ArrayList<Transfert>();
		for (String idTransfert : request.getParameterValues("idTransfert")){
			transferts.add(transfertService.findTransfertById(Long.parseLong(idTransfert)));
			
		}
		
		String ville = transferts.get(0).getDestinataireUnite().getVille();
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		Date date = new Date();
		
		TransporteurExterne transporteurExterne = transporteurExterneService.
				findTransporteurExterneById(Long.parseLong(transporteur));
		
		if(!(coursier.equals(""))){
			bordereau.setCoursierExterne(coursierExterneService.findCoursierExterneServiceById(
					Long.parseLong(coursier)));
		}

		
		bordereau.setTransporteurExterne(transporteurExterne);
		bordereau.setDateCreation(dateFormat.format(date));
		bordereau.setTransferts(transferts);
		bordereau.setVille(ville);
		
		globalCrudService.save(bordereau);
		
		for (Transfert transfert : transferts){
			transfert.setBordereau(bordereau);
			globalCrudService.update(transfert);
		}
		
		return "redirect:/bo/bordereau-" + bordereau.getIdBordereau();
		
	}
	
	@RequestMapping(value = "/bo/bordereau-{idBordereau}")
	public ModelAndView showBordereau(@PathVariable("idBordereau") long idBordereau){
		ModelAndView modelAndView = new ModelAndView(
				"bo/showBordereau.jsp");
		
		Bordereau bordereau = bordereauService.findBordereauById(idBordereau);
		modelAndView.addObject("bordereau", bordereau);
		return modelAndView;
	}
	
	
	@RequestMapping(value = "/bo/bordereau-{idBordereau}-print")
	public ModelAndView printBordereau(@PathVariable("idBordereau") long idBordereau){
		ModelAndView modelAndView = new ModelAndView("bo/printBordereau.jsp");
		Bordereau bordereau = bordereauService.findBordereauById(idBordereau);
		
		List<Map<String, ?>> listTransferts = new ArrayList<Map<String, ?>>();
		
		
		for( Transfert transfert : bordereau.getTransferts())
		{
			Map<String, Object> m = new HashMap<String, Object>();
			m.put("id", transfert.getCourrier().getIdCourrier());
			m.put("objet", transfert.getCourrier().getObjetCourrier());
			m.put("destinataire", transfert.getCourrier().getDestinataire().getNom());
			m.put("transfert", transfert.getDestinataireUnite().getNom());
			listTransferts.add(m);
		}
		modelAndView.addObject("listTransferts", listTransferts);
		
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("nomTransporteur", bordereau.getTransporteurExterne().getNomTransporteurExterne());
		parameters.put("sysDate", bordereau.getDateCreation());
		
		parameters.put("ville", bordereau.getVille());
		
		modelAndView.addObject("parameters", parameters);
		
		return modelAndView;
	}
	
	@RequestMapping(value = "/bo/allBordereau")
	public ModelAndView getAllBordereau(){
		ModelAndView modelAndView = new ModelAndView("bo/allBordereau.jsp");
		
		List<Bordereau> bordereaux = bordereauService.getAllBordereau();
		modelAndView.addObject("bordereaux", bordereaux);
		return modelAndView;
	}
	
	@RequestMapping(value = "/bo/bordereau-{idBordereau}/delete", method = RequestMethod.GET)
	public String processDeleteCourrier(@PathVariable("idBordereau") long idBordereau) {
		Bordereau bordereau = bordereauService.findBordereauById(idBordereau);
		
		for (Transfert transfert : bordereau.getTransferts()){
			transfert.setBordereau(null);
			globalCrudService.update(transfert);
		}
		
		globalCrudService.remove(bordereau, idBordereau);
		return "redirect:/bo/allBordereau";
	}
	
	@RequestMapping(value = "/bo/bordereau/loadCoursier" ,method = RequestMethod.GET)
	@ResponseBody
	public List<CoursierExterne> loadCoursier(@RequestParam(value = "TransporteurId" ,required = true) String TransporteurId ){
		TransporteurExterne transporteurExterne = transporteurExterneService.findTransporteurExterneById(Long.parseLong(TransporteurId));
		return coursierExterneService.getCoursierExternesByTransporteurExterne(transporteurExterne);
	}

}
