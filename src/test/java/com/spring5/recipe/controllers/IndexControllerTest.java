package com.spring5.recipe.controllers;

import com.spring5.recipe.domain.Recipe;
import com.spring5.recipe.services.RecipeService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class IndexControllerTest {

	private static final int WANTED_NUMBER_OF_INVOCATIONS_FOR_MODEL = 1;
	private static final int WANTED_NUMBER_OF_INVOCATIONS_FOR_SERVICE = 1;
	private static final int EXPECTED_RECIPES_IN_CONTROLLER = 2;
	private static final String RECIPES_MODEL_NAME = "recipes";
	private static final String EXPECTED_VIEW_NAME = "index";

	private IndexController indexController;

	@Mock
	RecipeService recipeService;

	@Mock
	Model model;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);

		indexController = new IndexController(recipeService);
	}

	@Test
	public void testMockMVC() throws Exception {
		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(indexController).build();

		mockMvc.perform(get("/"))
			.andExpect(status().isOk())
			.andExpect(view().name(EXPECTED_VIEW_NAME));
	}

	/**
	 * verify model.addAttribute is called 1 time
	 * verify recipeService call exactly 1 time
	 * verify getIndexPage return string that equals index
	 */
	@Test
	public void getIndexPage() {

		// given
		Set<Recipe> recipes = new HashSet<>();
		recipes.add(new Recipe());

		Recipe recipe = new Recipe();
		recipe.setId(1L);
		recipes.add(recipe);

		when(recipeService.getRecipes()).thenReturn(recipes);

		ArgumentCaptor<Set<Recipe>> argumentCaptor = ArgumentCaptor.forClass(Set.class);

		// when
		String viewName = indexController.getIndexPage(model);

		// then
		assertEquals(EXPECTED_VIEW_NAME, viewName);
		verify(model, times(WANTED_NUMBER_OF_INVOCATIONS_FOR_MODEL)).addAttribute(eq(RECIPES_MODEL_NAME), argumentCaptor.capture());
		verify(recipeService, times(WANTED_NUMBER_OF_INVOCATIONS_FOR_SERVICE)).getRecipes();

		Set<Recipe> setInController = argumentCaptor.getValue();
		assertEquals(EXPECTED_RECIPES_IN_CONTROLLER, setInController.size());
	}
}