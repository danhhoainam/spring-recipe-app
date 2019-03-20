package com.spring5.recipe.services;

import com.spring5.recipe.converters.RecipeCommandToRecipe;
import com.spring5.recipe.converters.RecipeToRecipeCommand;
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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class RecipeServiceImplTest {

	private RecipeServiceImpl recipeService;

	@Mock
	RecipeRepository recipeRepository;

	@Mock
	RecipeCommandToRecipe recipeCommandToRecipe;

	@Mock
	RecipeToRecipeCommand recipeToRecipeCommand;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);

		recipeService = new RecipeServiceImpl(recipeRepository, recipeCommandToRecipe, recipeToRecipeCommand);
	}

	@Test
	public void test__findById__withExistingId__shouldFoundRecipe() throws Exception {
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
	public void test__findById__withAnyId__shouldFoundEmpty__andThrowRuntimeException() throws RuntimeException {
		// given
		when(recipeRepository.findById(anyLong())).thenReturn(Optional.empty());

		thrown.expect(RuntimeException.class);
		thrown.expectMessage("Recipe Not Found!");

		// when
		recipeService.findById(1L);
	}

	@Test
	public void test__getRecipes__shouldReturnSetOfRecipes() {

		// given
		Recipe recipe = new Recipe();
		HashSet recipesData = new HashSet();
		recipesData.add(recipe);

		when(recipeService.getRecipes()).thenReturn(recipesData);

		// when
		Set<Recipe> recipes = recipeService.getRecipes();

		// then
		assertEquals(recipes.size(), 1);
		Mockito.verify(recipeRepository, Mockito.times(1)).findAll();
	}
}