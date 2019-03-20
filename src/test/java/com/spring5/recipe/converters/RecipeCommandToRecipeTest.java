package com.spring5.recipe.converters;

import com.spring5.recipe.commands.CategoryCommand;
import com.spring5.recipe.commands.IngredientCommand;
import com.spring5.recipe.commands.NotesCommand;
import com.spring5.recipe.commands.RecipeCommand;
import com.spring5.recipe.domain.Difficulty;
import com.spring5.recipe.domain.Recipe;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RecipeCommandToRecipeTest {

	public static final Long RECIPE_ID = 1L;
	public static final Integer COOK_TIME = Integer.valueOf("5");
	public static final Integer PREP_TIME = Integer.valueOf("7");
	public static final String DESCRIPTION = "description";
	public static final Difficulty DIFFICULTY = Difficulty.MODERATE;
	public static final Integer SERVINGS = Integer.valueOf("3");
	public static final String SOURCE = "source";
	public static final String URL = "url";
	public static final Long CAT_ID_1 = 1L;
	public static final Long CAT_ID2 = 2L;
	public static final Long INGRED_ID_1 = 3L;
	public static final Long INGRED_ID_2 = 4L;
	public static final Long NOTES_ID = 9L;
	public static final String DIRECTIONS = "Directions";

	RecipeCommandToRecipe converter;

	@Before
	public void setUp() throws Exception {

		converter = new RecipeCommandToRecipe(
				new CategoryCommandToCategory(),
				new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure()),
				new NotesCommandToNotes()
		);
	}

	@Test
	public void test__convert__parameterIsNull__shouldReturnNull() {

		// when
		Recipe recipe = converter.convert(null);

		// then
		assertNull(recipe);
	}

	@Test
	public void test__convert__parameterIsEmpty__shouldReturnNotNull() {

		// when
		Recipe recipe = converter.convert(new RecipeCommand());

		// then
		assertNotNull(recipe);
	}

	@Test
	public void test__convert__parameterIsFullInfo__shouldReturnCorrectResult() {

		// given
		RecipeCommand recipeCommand = new RecipeCommand();
		recipeCommand.setId(RECIPE_ID);
		recipeCommand.setCookTime(COOK_TIME);
		recipeCommand.setPrepTime(PREP_TIME);
		recipeCommand.setDescription(DESCRIPTION);
		recipeCommand.setDifficulty(DIFFICULTY);
		recipeCommand.setDirections(DIRECTIONS);
		recipeCommand.setServings(SERVINGS);
		recipeCommand.setSource(SOURCE);
		recipeCommand.setUrl(URL);

		NotesCommand notes = new NotesCommand();
		notes.setId(NOTES_ID);

		recipeCommand.setNotes(notes);

		CategoryCommand category = new CategoryCommand();
		category.setId(CAT_ID_1);

		CategoryCommand category2 = new CategoryCommand();
		category2.setId(CAT_ID2);

		recipeCommand.getCategories().add(category);
		recipeCommand.getCategories().add(category2);

		IngredientCommand ingredient = new IngredientCommand();
		ingredient.setId(INGRED_ID_1);

		IngredientCommand ingredient2 = new IngredientCommand();
		ingredient2.setId(INGRED_ID_2);

		recipeCommand.getIngredients().add(ingredient);
		recipeCommand.getIngredients().add(ingredient2);

		// when
		Recipe recipe = converter.convert(recipeCommand);

		// then
		assertNotNull(recipe);
		assertEquals(RECIPE_ID, recipe.getId());
		assertEquals(COOK_TIME, recipe.getCookTime());
		assertEquals(PREP_TIME, recipe.getPrepTime());
		assertEquals(DESCRIPTION, recipe.getDescription());
		assertEquals(DIFFICULTY, recipe.getDifficulty());
		assertEquals(DIRECTIONS, recipe.getDirections());
		assertEquals(SERVINGS, recipe.getServings());
		assertEquals(SOURCE, recipe.getSource());
		assertEquals(URL, recipe.getUrl());

		assertEquals(NOTES_ID, recipe.getNotes().getId());
		assertEquals(2, recipe.getCategories().size());
		assertEquals(2, recipe.getIngredients().size());
	}
}