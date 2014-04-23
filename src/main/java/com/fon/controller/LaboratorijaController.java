package com.fon.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.Katedra;
import model.Laboratorija;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fon.jpadatabase.JPADatabase;
import com.fon.jsonmodel.KatedraJSON;

@Controller
public class LaboratorijaController {
	
	@RequestMapping(value="/laboratorija", method=RequestMethod.GET)
	public String laboratorija(Model model, HttpServletRequest request){
		HttpSession sesija = request.getSession();
		List<Laboratorija> labList=JPADatabase.dajObjekat().listaLaboratorija();
		model.addAttribute("labList",labList);
		/*if(sesija.getAttribute("privilegije")=="superAdmin"){
		List<Laboratorija> labList=JPADatabase.dajObjekat().listaLaboratorija();
		model.addAttribute("labList",labList);
		}
		else if(sesija.getAttribute("privilegije")=="adminLab"){
			int id=Integer.parseInt(sesija.getAttribute("privilegije")+"");
			List<Laboratorija> labList=JPADatabase.dajObjekat().listaLaboratorija();
			//List<Laboratorija> labList=JPADatabase.dajObjekat().listaLaboratorijaZaAdminLab(id);
			model.addAttribute("labList",labList);
			
		}*/
	
		List<Katedra> listaKatedri=JPADatabase.dajObjekat().listaKatedri();
		model.addAttribute("listaKatedri", listaKatedri);
	
		return "laboratorija";
	}
	
	@RequestMapping(value="/laboratorijaListaKatedri", method=RequestMethod.GET)
	public @ResponseBody List<KatedraJSON> listaKatedri(){
		
		List<Katedra> listaKatedri=JPADatabase.dajObjekat().listaKatedri();
		List<KatedraJSON> listaJSON=new ArrayList<KatedraJSON>();
		
		for (Katedra item : listaKatedri) {
			KatedraJSON p=new KatedraJSON();
			p.setID_Katedre(item.getID_katedre());
			p.setNazivKatedre(item.getNaziv_katedre());
			listaJSON.add(p);
		}
	
		return listaJSON;
	}
	@RequestMapping(value="/dodajLab", method=RequestMethod.POST)
	public @ResponseBody String dodajLab(String nazivLab, String selKatedre, String sajt){
		
		
		Katedra k=new Katedra();
		k.setID_katedre(Integer.parseInt(selKatedre));
		Laboratorija l=new Laboratorija();
		l.setNaziv_laboratorije(nazivLab);
		l.setSajt(sajt);
		l.setKatedra(k);
//		
		String id_Lab=JPADatabase.dajObjekat().sacuvajLaboratoriju(l);
		
		
		
		return id_Lab;
	}
	
	@RequestMapping(value="/izmeniLab", method=RequestMethod.POST)
	public @ResponseBody String izmeniLab(String id_Lab, String nazivLab, String selKatedre, String sajt){
		Katedra k=new Katedra();
		k.setID_katedre(Integer.parseInt(selKatedre));
		Laboratorija l=new Laboratorija();
		l.setId_laboratorije(Integer.parseInt(id_Lab));
		l.setNaziv_laboratorije(nazivLab);
		l.setSajt(sajt);
		l.setKatedra(k);
		String poruka=JPADatabase.dajObjekat().izmeniLaboratoriju(l);
		
		
		return poruka;
	}
	@RequestMapping(value="/obrisiLab", method=RequestMethod.POST)
	public @ResponseBody String izmeniLab(String id_Lab){
		String poruka=JPADatabase.dajObjekat().obrisiLaboratoriju(Integer.parseInt(id_Lab));
		
		
		return poruka;
	}


}
