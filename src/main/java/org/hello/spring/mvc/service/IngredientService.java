package org.hello.spring.mvc.service;

import java.util.List;

import org.hello.spring.mvc.model.Ingredient;
import org.hello.spring.mvc.repo.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IngredientService {

	@Autowired
	private IngredientRepository repo;
	
	public List<Ingredient> findAll() {
		return repo.findAll();
	}
	
	public Ingredient create(Ingredient ingredient) {
		return repo.save(ingredient);
	}
	
	public Ingredient update(Ingredient ingredient) {
		
		return repo.save(ingredient);
	}
	
	public Ingredient getById(Integer id) {
		return repo.findById(id).get();
	}
}
