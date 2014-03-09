package com.fon.controller;

import java.util.List;

import model.Katedra;
import model.Laboratorija;
import model.Predmeti;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fon.jpadatabase.JPADatabase;

@Controller
public class Predmet {
	
	@RequestMapping(value = "/predmet", method=RequestMethod.GET)
	public String predmetGET (Model model) {
		List<Katedra> listaKatedri=JPADatabase.dajObjekat().listaKatedri();
		model.addAttribute("listaKatedri", listaKatedri);
		
		List<Predmeti> listaPredmeta=JPADatabase.dajObjekat().listaPredmeta();
		model.addAttribute("listaPredmeta", listaPredmeta);
		
		return "predmet";
	}
	
	@RequestMapping(value="/dodajPredmet", method=RequestMethod.POST)
	public @ResponseBody String dodajPredmet(String nazivPred, String brBodova, String semestar, String brCasovaPredavanja, String brCasovaVezbi, String brCasovaOstalo){
		
		String idPredmeta="";	
	
		Predmeti predmet=new Predmeti();
		predmet.setNaziv_predmeta(nazivPred);
		predmet.setBr_bodova(Short.parseShort(brBodova));
		predmet.setSemestar(Short.parseShort(semestar));
		predmet.setBr_casova_predvanja(Short.parseShort(brCasovaPredavanja));
		predmet.setBr_casova_vezbi(Short.parseShort(brCasovaVezbi));
		predmet.setBr_casova_ostalo(Short.parseShort(brCasovaOstalo));
		idPredmeta=JPADatabase.dajObjekat().sacuvajPredmet(predmet);
		
		return idPredmeta;
	}
	
	@RequestMapping(value="/izmeniPredmet", method=RequestMethod.POST)
	public @ResponseBody String izmeniPredmet(String idPredmeta ,String nazivPred, String brBodova, String semestar, String brCasovaPredavanja, String brCasovaVezbi, String brCasovaOstalo){
		
		String poruka=JPADatabase.dajObjekat().izmeniPredmet(idPredmeta, nazivPred, brBodova, semestar, brCasovaPredavanja, brCasovaVezbi, brCasovaOstalo);
		
		
		return poruka;
	}
	@RequestMapping(value="/obrisiPredmet", method=RequestMethod.POST)
	public @ResponseBody String obrisiPredmet(String idPredmeta){
		
		String poruka=JPADatabase.dajObjekat().obrisiPredmet(Integer.parseInt(idPredmeta));
		
		
		return poruka;
	}

}
