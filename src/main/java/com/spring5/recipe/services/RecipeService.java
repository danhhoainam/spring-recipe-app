package com.spring5.recipe.services;

import com.spring5.recipe.domain.Recipe;

import java.util.Set;

public interface RecipeService {

	Set<Recipe> getRecipes();

	Recipe findById(Long id);
}
