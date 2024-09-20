package org.hello.spring.mvc.controller;

import java.util.List;

import org.hello.spring.mvc.model.Pizza;
import org.hello.spring.mvc.repo.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;

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
	
	@GetMapping("/search")
	public String pizzaSearch(@RequestParam String name, Model model) {

		// consegna al model di specifiche ennuple di pizza tramite JPA Query Methods
		model.addAttribute("pizze", repo.findByNameContainingOrderByName(name));

		return "/pizze/index";
	}
	
	@GetMapping("/create")
	public String create(Model model) {
		model.addAttribute("pizza", new Pizza());
		return "/pizze/create";
	}
	
	@PostMapping("/create")
	public String store(@Valid @ModelAttribute("pizza") Pizza formPizza, BindingResult bindingResult, Model model) {
		
		if (bindingResult.hasErrors()) {
			return "/pizze/create";
		} else {
			repo.save(formPizza);
			return "redirect:/pizze";
		}
		
	}
	
}
