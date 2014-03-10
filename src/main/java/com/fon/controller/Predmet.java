package com.fon.controller;

import java.util.List;

import model.Katedra;
import model.Nastavnik;
import model.Predmeti;
import model.Pripadnost_predmeta_katedri;

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
		
		List<Nastavnik> listaNastavnik=JPADatabase.dajObjekat().listaNastavnika();
		model.addAttribute("listaNastavnik", listaNastavnik);
		
		
		return "predmet";
	}
	
	@RequestMapping(value="/dodajPredmet", method=RequestMethod.POST)
	public @ResponseBody String dodajPredmet(String predavac, String katedra, String nazivPred, String brBodova, String semestar, String brCasovaPredavanja, String brCasovaVezbi, String brCasovaOstalo){
		
		String idPredmeta="";	
	
		Predmeti predmet=new Predmeti();
		predmet.setNaziv_predmeta(nazivPred);
		predmet.setBr_bodova(Short.parseShort(brBodova));
		predmet.setSemestar(Short.parseShort(semestar));
		predmet.setBr_casova_predvanja(Short.parseShort(brCasovaPredavanja));
		predmet.setBr_casova_vezbi(Short.parseShort(brCasovaVezbi));
		predmet.setBr_casova_ostalo(Short.parseShort(brCasovaOstalo));
		idPredmeta=JPADatabase.dajObjekat().sacuvajPredmet(predmet);
		String idPripadost="error";
		try {
			int id=Integer.parseInt(idPredmeta);
			Pripadnost_predmeta_katedri prk=new Pripadnost_predmeta_katedri();
			
			Predmeti p=new Predmeti();
			p.setID_predmeta(id);
			
			Katedra k=new Katedra();
			k.setID_katedre(Integer.parseInt(katedra));
			
			Nastavnik n=new Nastavnik();
			n.setId_nastavnika(Integer.parseInt(predavac));
			
			prk.setKatedra(k);
			prk.setNastavnik(n);
			prk.setPredmeti(p);
			
			 idPripadost=JPADatabase.dajObjekat().sacuvajPripadnos_predmeta_Katedri(prk);
			
			
			
		} catch (Exception e) {
			//ovde ide poruka da nije sacuvan predmet
		}
		
		
		return idPredmeta+";"+idPripadost;
	}
	
	@RequestMapping(value="/izmeniPredmet", method=RequestMethod.POST)
	public @ResponseBody String izmeniPredmet(String idPripadnost ,String predavac, String katedra,String idPredmeta ,String nazivPred, String brBodova, String semestar, String brCasovaPredavanja, String brCasovaVezbi, String brCasovaOstalo){
		
		String poruka=JPADatabase.dajObjekat().izmeniPredmet(idPredmeta, nazivPred, brBodova, semestar, brCasovaPredavanja, brCasovaVezbi, brCasovaOstalo);
		String poruka2=JPADatabase.dajObjekat().izmeniPripadnostPredmetaKatedri(idPripadnost, predavac, katedra, idPredmeta);
		
		return poruka;
	}
	@RequestMapping(value="/obrisiPredmet", method=RequestMethod.POST)
	public @ResponseBody String obrisiPredmet(String idPredmeta, String idPripadnostPred){
		
		String poruka1=JPADatabase.dajObjekat().obrisiPripadnostPredmetaKatedri(Integer.parseInt(idPripadnostPred));
		String poruka="";
		if(poruka1.equalsIgnoreCase("obrisano")){
			poruka=JPADatabase.dajObjekat().obrisiPredmet(Integer.parseInt(idPredmeta));
			
		}
		else{
			poruka="GRESKA";
		}
		
		return poruka;
	}

}
