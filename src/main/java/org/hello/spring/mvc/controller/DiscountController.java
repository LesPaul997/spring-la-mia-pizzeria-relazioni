package org.hello.spring.mvc.controller;

import org.hello.spring.mvc.model.Discount;
import org.hello.spring.mvc.service.DiscountService;
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
@RequestMapping("/discounts")
public class DiscountController {

	@Autowired
	private DiscountService service;
	
	@GetMapping
	public String index(Model model) {
		
		model.addAttribute("discounts", service.findAllSortedByDiscountDate());
		return "/discounts/index";
	}
	
	@PostMapping("/create")
	public String store(@Valid @ModelAttribute("discount") Discount formDiscount, BindingResult bindingResult, Model model) {
		
		if (bindingResult.hasErrors()) {
			return "/discount/create";
		}
		service.create(formDiscount);
		
		return "redirect:/pizze/show" + formDiscount.getPizza().getId();
		
	}
}