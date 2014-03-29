package com.fon.controller;

import java.util.List;

import model.Katedra;
import model.Vrsta_nastavnika;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fon.jpadatabase.JPADatabase;

@Controller
public class VrstaNastavnikaController {
	@RequestMapping(value = "/vrstaNastavnika", method=RequestMethod.GET)
	public String predmetGET (Model model) {
		List<Katedra> listaKatedri=JPADatabase.dajObjekat().listaKatedri();
		model.addAttribute("listaKatedri", listaKatedri);
		
		List<Vrsta_nastavnika> listaVrstaNastavnika=JPADatabase.dajObjekat().listaVrsteNastavnika();
		model.addAttribute("listaVrstaNastavnika", listaVrstaNastavnika);
		
		return "vrstaNastavnika";
	}
	@RequestMapping(value="/dodajVrstu", method=RequestMethod.POST)
	public @ResponseBody String dodajZvanje(String vrsta){
	
		String poruka=JPADatabase.dajObjekat().sacuvajVrstu(vrsta);
		
		return poruka;
	}
	
	@RequestMapping(value="/obrisiVrstu", method=RequestMethod.POST)
	public @ResponseBody String obrisiZvanje(String idVrsta){
	
		String poruka=JPADatabase.dajObjekat().obrisiVrstu(idVrsta);
		
		return poruka;
	}

}
