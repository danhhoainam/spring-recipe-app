package com.spring5.recipe.controllers;

import com.spring5.recipe.commands.RecipeCommand;
import com.spring5.recipe.domain.Recipe;
import com.spring5.recipe.services.RecipeService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class RecipeControllerTest {

	@Mock
	RecipeService recipeService;

	private RecipeController recipeController;

	private MockMvc mockMvc;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);

		recipeController = new RecipeController(recipeService);
		mockMvc = MockMvcBuilders.standaloneSetup(recipeController).build();
	}

	@Test
	public void test__getRecipe__shouldReturnViewName__andAddAttributeToModel() throws Exception {

		// given
		Recipe recipe = new Recipe();
		recipe.setId(1L);

		// when
		when(recipeService.findById(anyLong())).thenReturn(recipe);

		// then
		mockMvc.perform(get("/recipe/show/1"))
				.andExpect(status().isOk())
				.andExpect(view().name("recipe/show"))
				.andExpect(model().attributeExists("recipe"));
	}

	@Test
	public void test__newRecipe__shouldReturnViewName__andAddAttributeToModel() throws Exception {

		// given
		RecipeCommand recipeCommand = new RecipeCommand();

		// when - then
		mockMvc.perform(get("/recipe/new"))
				.andExpect(status().isOk())
				.andExpect(view().name("recipe/recipeform"))
				.andExpect(model().attributeExists("recipe"));
	}

	@Test
	public void test__saveOrUpdate__whenPostNewRecipeForm__shouldRedirectToShowForm() throws Exception {

		// given
		RecipeCommand recipeCommand = new RecipeCommand();
		recipeCommand.setId(2L);

		when(recipeService.saveRecipeCommand(any())).thenReturn(recipeCommand);

		// when - then
		mockMvc.perform(post("/recipe")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.param("id", "")
				.param("description", "some string"))
				.andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/recipe/show/2"));
	}

	@Test
	public void test__updateRecipe__whenUpdateById__shouldFindRecipeById__andReturnUpdateView() throws Exception {

		// given
		RecipeCommand recipeCommand = new RecipeCommand();
		recipeCommand.setId(3L);

		when(recipeService.findCommandById(anyLong())).thenReturn(recipeCommand);

		// when - then
		mockMvc.perform(get("/recipe/1/update"))
				.andExpect(status().isOk())
				.andExpect(view().name("recipe/recipeform"))
				.andExpect(model().attributeExists("recipe"));
	}
}
