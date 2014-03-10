package com.fon.controller;

import java.util.List;

import model.Kabinet;
import model.Katedra;
import model.Nastavnik;
import model.Predmeti;
import model.Pripadnost_predmeta_katedri;
import model.Vrsta_nastavnika;
import model.Zvanje;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fon.jpadatabase.JPADatabase;

@Controller
public class NastavnikController {

	@RequestMapping(value = "/nastavnik", method=RequestMethod.GET)
	public String getNastavnik (Model model) {
		
		List<Katedra> listaKatedri=JPADatabase.dajObjekat().listaKatedri();
		model.addAttribute("listaKatedri", listaKatedri);
		
		
		List<Nastavnik> listaNastavnik=JPADatabase.dajObjekat().listaNastavnika();
		model.addAttribute("listaNastavnik", listaNastavnik);
		
		List<Vrsta_nastavnika> listaVrsta_nastavnika=JPADatabase.dajObjekat().listaVrsteNastavnika();
		model.addAttribute("listaVrsta_nastavnika", listaVrsta_nastavnika);
		
		List<Kabinet> listaKabinet=JPADatabase.dajObjekat().listaKabineta();
		model.addAttribute("listaKabinet", listaKabinet);
		
		List<Zvanje> listaZvanje=JPADatabase.dajObjekat().listaZvanje();
		model.addAttribute("listaZvanje", listaZvanje);
		return "nastavnikSifarnik";
	}
	
	@RequestMapping(value="/dodajNastavnika", method=RequestMethod.POST)
	public @ResponseBody String dodajNastavnik(String ime, String prezime, String email, String telefon, String vrsta, String kabinet, String licPrez, String zvanje){
		
		String idnas=JPADatabase.dajObjekat().sacuvajNastavnika(ime, prezime, email, telefon, vrsta, kabinet, licPrez, zvanje);
		String poruka=JPADatabase.dajObjekat().sacuvajNastavnik_Zvanje(idnas, zvanje);
		
		return idnas;
	}
	@RequestMapping(value="/obrisiNastavnika", method=RequestMethod.POST)
	public @ResponseBody String obrisiNastavnika(String idNastanivk){
		String poruka=JPADatabase.dajObjekat().obrisiNastavnika(Integer.parseInt(idNastanivk));
	
		return poruka;
	}
}
