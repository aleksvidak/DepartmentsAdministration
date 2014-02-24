package com.fon.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class LoginController {

	@RequestMapping(value = "/prijava")
	public String SayHello () {
		
		//model.addAttribute("prijava", "Hello World!");
		return "login";
	}
	
}
