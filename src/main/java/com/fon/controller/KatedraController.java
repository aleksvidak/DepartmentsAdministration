package com.fon.controller;

import java.util.List;

import model.Katedra;
import model.Laboratorija;
import model.Nastavnik;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fon.jpadatabase.JPADatabase;

@Controller
public class KatedraController {
	
	//ovo je metoda za prikaz stranice katedre
	@RequestMapping(value = "/katedra.html", method=RequestMethod.GET)
	public String katedraPrikaz (@RequestParam(value = "id") String id, Model model) {
		List<Katedra> listaKatedri=JPADatabase.dajObjekat().listaKatedri();
		model.addAttribute("listaKatedri", listaKatedri);
		System.out.println(id);
		return "katedra";
	}
	
	//ovo je za sifarnik katedre
	@RequestMapping(value = "/katedraSifarnik", method=RequestMethod.GET)
	public String katedraPrikaz(Model model) {
		
		List<Katedra> listaKatedri=JPADatabase.dajObjekat().listaKatedri();
		model.addAttribute("listaKatedri", listaKatedri);
		
		List<Nastavnik> listaNastavnik=JPADatabase.dajObjekat().listaNastavnika();
		model.addAttribute("listaNastavnik", listaNastavnik);
		
		return "katedraSifarnik";
	}
	
	@RequestMapping(value = "/dodajKatedru", method=RequestMethod.POST)
	public  @ResponseBody String dodajKatedru(String nazivKat, String rukovodilac, String sekretar) {
		
		
		
		Nastavnik nasRuk=new Nastavnik();
		nasRuk.setId_nastavnika(Integer.parseInt(rukovodilac));
		
		Nastavnik nasSek=new Nastavnik();
		nasSek.setId_nastavnika(Integer.parseInt(sekretar));
		
		Katedra k=new Katedra();
		k.setNaziv_katedre(nazivKat);
		
		k.setNastavnik1(nasRuk);
		k.setNastavnik2(nasSek);
		
		String id=JPADatabase.dajObjekat().sacuvajKatedru(k);
		
		return id;
	}
	
	
	@RequestMapping(value="/obrisiKatedru", method=RequestMethod.POST)
	public @ResponseBody String obrisiKatedru(String idKatedre){
		String poruka=JPADatabase.dajObjekat().obrisiKatedru(Integer.parseInt(idKatedre));
		
		
		return poruka;
	}
	
	@RequestMapping(value="/izmeniKatedru", method=RequestMethod.POST)
	public @ResponseBody String izmeniKatedru(String idKatedre, String nazivKat, String rukovodilac, String sekretar){
		String poruka=JPADatabase.dajObjekat().izmeniKatedru(idKatedre, nazivKat, rukovodilac, sekretar);
		return poruka;
	}

	
	
}
