package com.fon.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import model.Login;
import model.Nastavnik;

import org.springframework.security.access.annotation.Secured;
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
	//@Secured(value="ROLE_ADMIN")
	@RequestMapping(value = "/prijava", method=RequestMethod.POST)
	public String prijavaPOST (@Valid @ModelAttribute ("loginForma") Login korisnik, BindingResult res, HttpSession sesija, HttpServletRequest hsr) {	
		
		//PodaciPrijava k=PodaciPrijava.login(korisnik.getKorisnickoIme(), korisnik.getKorisnickaSifra());
		
		//System.out.println(korisnik.getKorisnickoIme());
		List<Login> lkorisnik=JPADatabase.dajObjekat().prijavaNaSistem(korisnik.getJ_username(), korisnik.getJ_password());
	
		boolean user = hsr.isUserInRole("ROLE_ADMIN");
		
		System.out.print(user);
		
		if(!lkorisnik.isEmpty()){
			Nastavnik n=JPADatabase.dajObjekat().dajNastavnika(lkorisnik.get(0).getID_nastavnika()+"");
			sesija.setAttribute("idKorisnika", lkorisnik.get(0).getID_nastavnika());
			sesija.setAttribute("privilegije", lkorisnik.get(0).getPrivilegija());
			sesija.setAttribute("nastavnik", n.getIme()+" "+n.getPrezime());
			
			
			if(lkorisnik.get(0).getPrivilegija().equalsIgnoreCase("ROLE_ADMIN")){
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
		
		else {
			
			System.out.print("Korisnik ne postoji.");
		}
		
	
		
		//ovde znaci da treba da se obavesti da korisnik ne postoji
		return "login";
	}
	
}
