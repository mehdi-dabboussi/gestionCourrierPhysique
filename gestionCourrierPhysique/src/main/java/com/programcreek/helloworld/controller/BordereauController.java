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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sharing.entity.Courrier;
import com.sharing.entity.Transfert;
import com.sharing.entity.TransporteurExterne;
import com.sharing.entity.User;
import com.sharing.service.BordereauService;
import com.sharing.service.CourrierService;
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
	
	@Autowired
	public BordereauController(GlobalCrudService globalCrudService, BordereauService bordereauService,
			CourrierService courrierService, TransfertService transfertService, UserService userService,
			TransporteurExterneService transporteurExterneService){
		this.globalCrudService = globalCrudService;
		this.bordereauService = bordereauService;
		this.courrierService = courrierService;
		this.transfertService = transfertService;
		this.userService = userService;
		this.transporteurExterneService = transporteurExterneService;
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
		
				modelAndView.addObject("transferts", transfertsBord);
				modelAndView.addObject("ville", selectVille);		
				modelAndView.addObject("transporteurs", transporteurs);
		
		return modelAndView;
	}
	
	
	@RequestMapping(value = "/bo/generateBordereauFinal", method=RequestMethod.POST)
	public ModelAndView processGenerateBordereauFinal(HttpServletRequest request, String transporteur){
		
		ModelAndView modelAndView = new ModelAndView("bo/printBordereau.jsp");
		
		System.out.println("test");
		ArrayList<Transfert> transferts = new ArrayList<Transfert>();
		for (String idTransfert : request.getParameterValues("idTransfert")){
			transferts.add(transfertService.findTransfertById(Long.parseLong(idTransfert)));
		}
		
		String ville = transferts.get(0).getDestinataireUnite().getVille();
		
		List<Map<String, ?>> listTransferts = new ArrayList<Map<String, ?>>();
		
		
		for( Transfert transfert : transferts)
		{
			System.out.println(transfert);
			Map<String, Object> m = new HashMap<String, Object>();
			m.put("id", transfert.getCourrier().getIdCourrier());
			m.put("objet", transfert.getCourrier().getObjetCourrier());
			m.put("destinataire", transfert.getCourrier().getDestinataire().getNom());
			m.put("transfert", transfert.getDestinataireUnite().getNom());
			listTransferts.add(m);
		}
		modelAndView.addObject("listTransferts", listTransferts);
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		Date date = new Date();
		
		
		
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("nomTransporteur", transporteurExterneService.
				findTransporteurExterneById(Long.parseLong(transporteur)).getNomTransporteurExterne());
		
		parameters.put("sysDate", dateFormat.format(date));
		
		parameters.put("ville", ville);
		
		modelAndView.addObject("parameters", parameters);
		
		
		
		return modelAndView;
		
	}

}
