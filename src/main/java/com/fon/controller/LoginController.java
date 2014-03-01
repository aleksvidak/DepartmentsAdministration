package com.fon.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fon.model.PodaciPrijava;


@Controller
public class LoginController {

	@RequestMapping(value = "/prijava")
	public String SayHello (@ModelAttribute ("loginForma") PodaciPrijava korisnik) {
		
		System.out.println(" "+korisnik.getKorisnickoIme()+ " " + korisnik.getKorisnickaSifra());
		return "login";
	}
	
}
