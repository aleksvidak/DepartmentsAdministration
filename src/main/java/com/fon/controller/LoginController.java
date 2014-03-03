package com.fon.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fon.model.PodaciPrijava;


@Controller
public class LoginController {

	@RequestMapping(value = "/prijava", method=RequestMethod.GET)
	public String prijavaGET (@ModelAttribute ("loginForma") PodaciPrijava korisnik) {		
		return "login";
	}
	@RequestMapping(value = "/prijava", method=RequestMethod.POST)
	public String prijavaPOST (@Valid @ModelAttribute ("loginForma") PodaciPrijava korisnik, BindingResult res) {	
		
		PodaciPrijava k=PodaciPrijava.login(korisnik.getKorisnickoIme(), korisnik.getKorisnickaSifra());
		
//		System.out.println(res.hasErrors());
		
		if(k.getPrivilegija()!=null && !k.getPrivilegija().isEmpty()){
			if(k.getPrivilegija().equalsIgnoreCase("superadmin")){
				return "redirect:superAdmin.html";
			}
			else if(k.getPrivilegija().equalsIgnoreCase("adminKatedre")){
				return "redirect:adminKatedre.html";
			}
			else if(k.getPrivilegija().equalsIgnoreCase("adminLab")){
				return "redirect:adminLab.html";
			}
			else{
				return "redirect:obicanKorisnik.html";
			}
		}
		
		//ovde znaci da treba da se obavesti da korisnik ne postoji
		return "login";
	}
	
}
