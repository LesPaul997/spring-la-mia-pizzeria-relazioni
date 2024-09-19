package org.hello.spring.mvc.controller;

import java.util.List;

import org.hello.spring.mvc.model.Pizza;
import org.hello.spring.mvc.repo.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/pizze")
public class PizzaController {
	
	//repository field con autowired pr d.i.
	
	@Autowired
	private PizzaRepository repo;
	
	@GetMapping
	public String index(Model model, @RequestParam(name = "name", required = false) String name) {
		
		// consegna dei dati a pizza/index 
		List<Pizza> pizzaList;
		
		if ( name !=null && !name.isEmpty()) {		
			pizzaList = repo.findByNameContainingOrderByName(name);
		} else {
			pizzaList = repo.findAll();
		}
		model.addAttribute("pizze", pizzaList);
		
		return "/pizze/index";
	}
	
//	@GetMapping("/findByTitle/{name}")
//	public String findByTitle(@PathVariable("name") String name, Model model) {
//		model.addAttribute("pizze", repo.findByName(name));
//		return "/pizze/index";
//	}
	

	@GetMapping("/show/{id}")
	public String pizzaDetails(@PathVariable int id, Model model) {

		// consegna al model di una specifica ennupla pizza tramite ID
		model.addAttribute("pizza", repo.findById(id).get());

		return "/pizze/show";
	}
	
	
}
