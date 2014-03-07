package com.fon.controller;

import java.util.ArrayList;
import java.util.List;

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
	public String laboratorija(Model model){
		List<Laboratorija> labList=JPADatabase.dajObjekat().listaLaboratorija();
		model.addAttribute("labList",labList);
	
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
			
			System.out.println(p.getNazivKatedre());
			listaJSON.add(p);
		}
	
		return listaJSON;
	}
	


}
