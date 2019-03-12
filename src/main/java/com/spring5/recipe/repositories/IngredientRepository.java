package com.spring5.recipe.repositories;

import com.spring5.recipe.domain.Ingredient;
import org.springframework.data.repository.CrudRepository;

public interface IngredientRepository extends CrudRepository<Ingredient, Long> {
}
