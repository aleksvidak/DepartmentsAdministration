package com.fon.controller;

import java.util.List;

import model.Katedra;
import model.Zvanje;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fon.jpadatabase.JPADatabase;

@Controller
public class ZvanjeController {
	
	@RequestMapping(value = "/zvanje", method=RequestMethod.GET)
	public String predmetGET (Model model) {
		List<Katedra> listaKatedri=JPADatabase.dajObjekat().listaKatedri();
		model.addAttribute("listaKatedri", listaKatedri);
		
		List<Zvanje> listaZvanje=JPADatabase.dajObjekat().listaZvanje();
		model.addAttribute("listaZvanje", listaZvanje);
		
		return "zvanje";
	}
	@RequestMapping(value="/dodajZvanje", method=RequestMethod.POST)
	public @ResponseBody String dodajZvanje(String zvanje){
	
		String poruka=JPADatabase.dajObjekat().sacuvajZvanje(zvanje);
		
		return poruka;
	}
	@RequestMapping(value="/obrisiZvanje", method=RequestMethod.POST)
	public @ResponseBody String obrisiZvanje(String idZvanje){
	
		String poruka=JPADatabase.dajObjekat().obrisiZvanje(idZvanje);
		
		return poruka;
	}
	
	
}
