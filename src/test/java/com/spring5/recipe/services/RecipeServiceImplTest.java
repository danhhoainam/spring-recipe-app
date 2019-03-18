package com.spring5.recipe.services;

import com.spring5.recipe.domain.Recipe;
import com.spring5.recipe.repositories.RecipeRepository;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.never;

public class RecipeServiceImplTest {

	private RecipeServiceImpl recipeService;

	@Mock
	RecipeRepository recipeRepository;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);

		recipeService = new RecipeServiceImpl(recipeRepository);
	}

	@Test
	public void getRecipeByIdTest() throws Exception {
		// given
		Recipe recipe = new Recipe();
		recipe.setId(1L);
		Optional<Recipe> optionalRecipe = Optional.of(recipe);

		when(recipeRepository.findById(anyLong())).thenReturn(optionalRecipe);

		// when
		Recipe foundRecipe = recipeService.findById(1L);

		// then
		assertNotNull(foundRecipe);
		verify(recipeRepository, times(1)).findById(anyLong());
		verify(recipeRepository, never()).findAll();
	}

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Test
	public void getRecipeByIdReturnNullTest() throws RuntimeException {
		// given
		when(recipeRepository.findById(anyLong())).thenReturn(Optional.empty());

		thrown.expect(RuntimeException.class);
		thrown.expectMessage("Recipe Not Found!");

		// when
		recipeService.findById(1L);
	}

	@Test
	public void getRecipes() {

		Recipe recipe = new Recipe();
		HashSet recipesData = new HashSet();
		recipesData.add(recipe);

		when(recipeService.getRecipes()).thenReturn(recipesData);

		Set<Recipe> recipes = recipeService.getRecipes();

		assertEquals(recipes.size(), 1);
		Mockito.verify(recipeRepository, Mockito.times(1)).findAll();
	}
}