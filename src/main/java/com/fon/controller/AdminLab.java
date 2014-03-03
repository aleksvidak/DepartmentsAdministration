package com.fon.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AdminLab {
	@RequestMapping(value = "/adminLab", method=RequestMethod.GET)
	public String prijavaGET () {		
		return "adminLab";
	}

}
