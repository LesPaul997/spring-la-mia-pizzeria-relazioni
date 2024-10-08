package org.hello.spring.mvc.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.hello.spring.mvc.model.Discount;
import org.hello.spring.mvc.model.Pizza;
import org.hello.spring.mvc.repo.PizzaRepository;
import org.hello.spring.mvc.service.IngredientService;
import org.hello.spring.mvc.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/pizze")
public class PizzaController {
	
	//repository field con autowired pr d.i.
	
	@Autowired
	private PizzaRepository repo;
	
	@Autowired
	private PizzaService pizzaService;
	
	@Autowired
	private IngredientService ingredientService;
	
	@GetMapping
	public String index(Authentication authentication, Model model, @RequestParam(name = "name", required = false) String name) {
		
		// consegna dei dati a pizza/index 
		List<Pizza> pizzaList;
		model.addAttribute("username", authentication.getName());
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
	
	// Ricerca per ID
	@GetMapping("/show/{id}")
	public String pizzaDetails(@PathVariable int id, Model model) {

		// consegna al model di una specifica ennupla pizza tramite ID
		model.addAttribute("pizza", repo.findById(id).get());
		
		return "/pizze/show";
	}
	
	// Creazione di un nuovo sconto
	@GetMapping("/{id}/discount")
	public String discount(@PathVariable("id") Integer id, Model model, RedirectAttributes redirectAttributes) {
		Pizza pizza = pizzaService.getById(id);
		if(pizza.getNumberOfDiscount() > 0) {
			
		Discount discount = new Discount();
		discount.setDiscountDate(LocalDate.now());
		discount.setPizza(pizza);
		model.addAttribute("discount", discount);
		return "/discount/create";
		}else {
			redirectAttributes.addFlashAttribute("successMessage", pizza.getName() + "has no more discounts available!");
			return "redirect:/pizze";
		}
	}
	
	//Campo di ricerca
	@GetMapping("/search")
	public String pizzaSearch(@RequestParam String name, Model model) {

		// consegna al model di specifiche ennuple di pizza tramite JPA Query Methods
		model.addAttribute("pizze", repo.findByNameContainingOrderByName(name));

		return "/pizze/index";
	}
	
	//CREATE
	@GetMapping("/create")
	public String create(Model model) {
		model.addAttribute("pizza", new Pizza());
		model.addAttribute("ingredients", ingredientService.findAll());
		return "/pizze/create";
	}
	
	//STORE
	@PostMapping("/create")
	public String store(@Valid @ModelAttribute("pizza") Pizza formPizza, BindingResult bindingResult, Model model) {
		
		if (bindingResult.hasErrors()) {
			model.addAttribute("ingredients", ingredientService.findAll());
			return "/pizze/create";
		} else {
			repo.save(formPizza);
			return "redirect:/pizze";
		}
		
	}
	
	//EDIT
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, Model model) {
		
		// Trovo la pizza
		//Pizza pizzaToEdit = repo.findById(id).get();
		// Lo inserisco nel model
		model.addAttribute("pizza", repo.findById(id).get());
		model.addAttribute("ingredients", ingredientService.findAll());
		// Restituisco la view con il model inserito	
		return "/pizze/edit";
	}
	
	//UPDATE 
	@PostMapping("/edit/{id}") 
	public String update(@Valid @ModelAttribute("pizza") Pizza updatedFormPizza, BindingResult bindingResult, Model model) {
		
		// Se ci sono errori nel form
		if (bindingResult.hasErrors()) {
			// Ritorna nel form e mostra gli errori
			model.addAttribute("ingredients", ingredientService.findAll());
			return "/pizze/edit";
		// Altrimenti 
		} else { 
			// Prendi la mia repo particolare e aggiorna la pizza con i nuovi dati convalidati
			repo.save(updatedFormPizza);
			// Ridireziona l'utente alla index delle pizze
			return "redirect:/pizze";
		}
	}
	
	// DELETE
	@PostMapping("/delete/{id}")
	public String delete(@PathVariable("id") Integer id) {
		
		// Prendi la mia repo particolare e dopo aver trovato la mia pizza attraverso l'id, cancellalo dal DB
		repo.deleteById(id);
		return "redirect:/pizze";
	}

	
	
}
