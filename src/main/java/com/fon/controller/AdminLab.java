package com.fon.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import model.Katedra;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fon.jpadatabase.JPADatabase;

@Controller
public class AdminLab {
	@RequestMapping(value = "/adminLab", method=RequestMethod.GET)
	public String prijavaGET (Model model, HttpServletRequest request) {	
		List<Katedra> listaKatedri=JPADatabase.dajObjekat().listaKatedri();
		model.addAttribute("listaKatedri", listaKatedri);
		return "adminLab";
	}

}
