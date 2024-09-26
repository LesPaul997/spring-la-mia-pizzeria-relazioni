package org.hello.spring.mvc.controller;

import org.hello.spring.mvc.model.Ingredient;
import org.hello.spring.mvc.service.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/ingredients")
public class IngredientController {

	@Autowired
	private IngredientService service;
	
	@GetMapping()
	public String index(Model model) {
		model.addAttribute("ingredients", service.findAll());
		return "/ingredients/index";
	}
	
	
	
	//CREATE
	@GetMapping("/create")
	public String create(Model model) {
		model.addAttribute(new Ingredient());
		return "/ingredients/create";
	}
		
	//STORE
	@PostMapping("/create")
	public String store(@Valid @ModelAttribute("pizza") Ingredient formIngredient, BindingResult bindingResult, Model model) {
		
		if (bindingResult.hasErrors()) {
			return "/ingredients/create";
		} else {
			service.create(formIngredient);
			return "redirect:/ingredients";
		}
		
	}
		
}
