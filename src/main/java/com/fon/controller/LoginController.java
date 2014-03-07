package com.fon.controller;

import java.util.List;

import javax.validation.Valid;

import model.Login;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fon.jpadatabase.JPADatabase;


@Controller
public class LoginController {

	@RequestMapping(value = "/prijava", method=RequestMethod.GET)
	public String prijavaGET (@ModelAttribute ("loginForma") Login korisnik) {		
		return "login";
	}
	@RequestMapping(value = "/prijava", method=RequestMethod.POST)
	public String prijavaPOST (@Valid @ModelAttribute ("loginForma") Login korisnik, BindingResult res) {	
		
		//PodaciPrijava k=PodaciPrijava.login(korisnik.getKorisnickoIme(), korisnik.getKorisnickaSifra());
		
		System.out.println(korisnik.getKorisnickoIme());
		List<Login> lkorisnik=JPADatabase.dajObjekat().PrijavanaSistem(korisnik.getKorisnickoIme(), korisnik.getLozinka());
		
		if(!lkorisnik.isEmpty())
		 System.out.println(lkorisnik.get(0).getPrivilegija());
		else
			System.out.println("PRAZNO");
		
		if(!lkorisnik.isEmpty()){
			if(lkorisnik.get(0).getPrivilegija().equalsIgnoreCase("superadmin")){
				return "redirect:superAdmin.html";
			}
			else if(lkorisnik.get(0).getPrivilegija().equalsIgnoreCase("adminKatedre")){
				return "redirect:adminKatedre.html";
			}
			else if(lkorisnik.get(0).getPrivilegija().equalsIgnoreCase("adminLab")){
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
