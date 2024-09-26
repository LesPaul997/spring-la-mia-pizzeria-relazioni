package org.hello.spring.mvc.service;

import java.util.Optional;

import org.hello.spring.mvc.model.Pizza;
import org.hello.spring.mvc.repo.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PizzaService {

	@Autowired
	private PizzaRepository repo;
	
	public Pizza getById(Integer id) {
		return repo.findById(id).get();
	}
	
	public Optional<Pizza> getOptionalById(Integer id) {
		return repo.findById(id);
	}
}
