package org.hello.spring.mvc.controller;

import java.util.List;

import org.hello.spring.mvc.model.Pizza;
import org.hello.spring.mvc.repo.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/pizze")
public class PizzaController {
	
	//repository field con autowired pr d.i.
	
	@Autowired
	private PizzaRepository repo;
	
	@GetMapping()
	public String index(Model model) {
		
		// consegna dei dati a pizza/index 
		model.addAttribute("pizze", repo.findAll());
		
		return "/pizze/index";
	}
}
