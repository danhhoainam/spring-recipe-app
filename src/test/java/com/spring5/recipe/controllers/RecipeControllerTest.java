package com.spring5.recipe.controllers;

import com.spring5.recipe.domain.Recipe;
import com.spring5.recipe.services.RecipeService;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class RecipeControllerTest {

	@Mock
	RecipeService recipeService;

	@Mock
	Model model;

	RecipeController recipeController;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);

		recipeController = new RecipeController(recipeService);
	}

	@Test
	public void testGetRecipe() throws Exception {

		Recipe recipe = new Recipe();
		recipe.setId(1L);

		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(recipeController).build();

		when(recipeService.findById(anyLong())).thenReturn(recipe);

		mockMvc.perform(get("/recipe/show/1"))
				.andExpect(status().isOk())
				.andExpect(view().name("recipe/show"))
				.andExpect(model().attributeExists("recipe"));
	}
}
