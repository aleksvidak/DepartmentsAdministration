package com.fon.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class SuperAdmin {

	@RequestMapping(value = "/superAdmin", method=RequestMethod.GET)
	public String prijavaGET () {		
		return "superAdmin";
	}
}
