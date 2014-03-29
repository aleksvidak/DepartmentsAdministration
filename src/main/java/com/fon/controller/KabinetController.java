package com.fon.controller;
import java.util.List;

import model.Kabinet;
import model.Katedra;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fon.jpadatabase.JPADatabase;

@Controller
public class KabinetController {

	@RequestMapping(value = "/kabinet", method=RequestMethod.GET)
	public String kabinetGet (Model model) {
		List<Katedra> listaKatedri=JPADatabase.dajObjekat().listaKatedri();
		model.addAttribute("listaKatedri", listaKatedri);
		
		List<Kabinet> listaKabinet=JPADatabase.dajObjekat().listaKabineta();
		model.addAttribute("listaKabinet", listaKabinet);
		
		return "kabinet";
	}
	
	@RequestMapping(value="/dodajKabinet", method=RequestMethod.POST)
	public @ResponseBody String dodajKabinet(String kabinet){
	
		String poruka=JPADatabase.dajObjekat().sacuvajKabinet(kabinet);
		
		return poruka;
	}
	
	@RequestMapping(value="/obrisiKabinet", method=RequestMethod.POST)
	public @ResponseBody String obrisiKabinet(String idKabinet){
	
		String poruka=JPADatabase.dajObjekat().obrisiKabinet(idKabinet);
		
		return poruka;
	}
		
}
