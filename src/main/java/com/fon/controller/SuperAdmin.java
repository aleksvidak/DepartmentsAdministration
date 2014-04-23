package com.fon.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.Katedra;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fon.jpadatabase.JPADatabase;


@Controller
public class SuperAdmin {
	@RequestMapping(value = "/superAdmin", method=RequestMethod.GET)
	public String prijavaGET (Model model, HttpServletRequest request) {
		List<Katedra> listaKatedri=JPADatabase.dajObjekat().listaKatedri();
		HttpSession sesija = request.getSession();
		sesija.getAttribute("idKorisnika");
		//System.out.print(sesija.getAttribute("idKorisnika"));
		model.addAttribute("listaKatedri", listaKatedri);
		/*if(sesija.getAttribute("idKorisnika")!=null)*/
		return "superAdmin";
		/*else
			return "redirect:prijava.html";*/
	}
}
