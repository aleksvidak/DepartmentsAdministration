package com.fon.controller;

import java.util.List;

import model.Katedra;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.fon.jpadatabase.JPADatabase;

@Controller
public class KatedraController {
	@RequestMapping(value = "/katedra.html", method=RequestMethod.GET)
	public String katedra (@RequestParam(value = "id") String id, Model model) {
		List<Katedra> listaKatedri=JPADatabase.dajObjekat().listaKatedri();
		model.addAttribute("listaKatedri", listaKatedri);
		System.out.println(id);
		return "katedra";
	}
	

	
	
}
