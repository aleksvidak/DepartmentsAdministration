package com.fon.controller;

import java.util.ArrayList;
import java.util.List;

import model.Kabinet;
import model.Katedra;
import model.Nastavnik;
import model.Vrsta_nastavnika;
import model.Zvanje;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fon.jpadatabase.JPADatabase;
import com.fon.jsonmodel.KatedraJSON;

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
	
	@RequestMapping(value="/izmeniNastavnika", method=RequestMethod.POST)
	public @ResponseBody String izmeniNastavnika(String idNastavnika,String ime, String prezime, String email, String telefon, String vrsta, String kabinet, String licPrez, String zvanje){
		String poruka=JPADatabase.dajObjekat().izmeniNastavnika(idNastavnika, ime, prezime, email, telefon, vrsta, kabinet, licPrez, zvanje);
	
		return poruka;
	}
	
	@RequestMapping(value="/dajKatedreZaNastavnika", method=RequestMethod.GET)
	public @ResponseBody List<KatedraJSON> izmeniNastavnika(String idNastavnika){
		
		List<KatedraJSON> lkat=new ArrayList<KatedraJSON>();
		Nastavnik n=JPADatabase.dajObjekat().dajNastavnika(idNastavnika);
		
		
		for(int i=0; i<n.getPripadnostNastavnikaKatedris().size();i++){
			KatedraJSON k=new KatedraJSON();
			k.setID_Katedre(n.getPripadnostNastavnikaKatedris().get(i).getKatedra().getID_katedre());
			k.setNazivKatedre(n.getPripadnostNastavnikaKatedris().get(i).getKatedra().getNaziv_katedre());
			lkat.add(k);
		}
		
		return lkat;
	}
	
	@RequestMapping(value="/sacuvajNastavnikKatedra", method=RequestMethod.POST)
	public @ResponseBody String sacuvajNastavnikPripadaKatedri(String idNastavnika, String idKat){
		String poruka=JPADatabase.dajObjekat().sacuvajNastavnikPripadaKatedri(idNastavnika, idKat);
	
		return poruka;
	}
	@RequestMapping(value="/obrisiNastavnikKatedra", method=RequestMethod.POST)
	public @ResponseBody String obrisiNastavnikPripadaKatedri(String idNastavnika, String idKat){
		//System.out.println(idNastavnika+" "+idKat);
		String poruka=JPADatabase.dajObjekat().obrisiNastavnikPripadaKatedri(idNastavnika, idKat);
	
		return poruka;
	}
	
	
}
