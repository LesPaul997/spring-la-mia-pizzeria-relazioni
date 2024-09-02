package org.hello.spring.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/pizze")
public class PizzaController {
	
	//repository field con autowired pr d.i.
	
	public String index(Model model) {
		
		//prendo i dati da consegnare a pizze 
		//li inserisco nel modello
		
		
		return "/pizze/index";
	}
}
