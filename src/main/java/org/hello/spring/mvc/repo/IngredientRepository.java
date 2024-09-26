package org.hello.spring.mvc.repo;

import org.hello.spring.mvc.model.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientRepository extends JpaRepository<Ingredient, Integer> {

}
