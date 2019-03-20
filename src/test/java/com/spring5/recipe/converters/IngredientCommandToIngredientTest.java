package com.spring5.recipe.converters;

import com.spring5.recipe.commands.IngredientCommand;
import com.spring5.recipe.commands.UnitOfMeasureCommand;
import com.spring5.recipe.domain.Ingredient;
import com.spring5.recipe.domain.Recipe;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class IngredientCommandToIngredientTest {

	public static final BigDecimal AMOUNT = new BigDecimal("1");
	public static final String DESCRIPTION = "Cheeseburger";
	public static final Long ID_VALUE = new Long(1L);
	public static final Long UOM_ID = new Long(2L);

	IngredientCommandToIngredient converter;

	@Before
	public void setUp() throws Exception {
		converter = new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure());
	}

	@Test
	public void test__convert__parameterIsNull__shouldReturnNull() throws Exception {
		assertNull(converter.convert(null));
	}

	@Test
	public void test__convert__parameterIsEmpty__shouldReturnNotNull() throws Exception {
		assertNotNull(converter.convert(new IngredientCommand()));
	}

	@Test
	public void test__convert__parameterIsFullInfo__shouldReturnCorrectResult() throws Exception {

		// given
		IngredientCommand ingredientCommand = new IngredientCommand();
		ingredientCommand.setId(ID_VALUE);
		ingredientCommand.setAmount(AMOUNT);
		ingredientCommand.setDescription(DESCRIPTION);

		UnitOfMeasureCommand unitOfMeasureCommand = new UnitOfMeasureCommand();
		unitOfMeasureCommand.setId(UOM_ID);
		ingredientCommand.setUnitOfMeasure(unitOfMeasureCommand);

		// when
		Ingredient ingredient = converter.convert(ingredientCommand);

		// then
		assertNotNull(ingredient);
		assertNotNull(ingredient.getUnitOfMeasure());
		assertEquals(ID_VALUE, ingredient.getId());
		assertEquals(AMOUNT, ingredient.getAmount());
		assertEquals(DESCRIPTION, ingredient.getDescription());
		assertEquals(UOM_ID, ingredient.getUnitOfMeasure().getId());
	}

	@Test
	public void test__convert__parameterUomIsNull__shouldReturnCorrectResultWithUomNull() throws Exception {

		// given
		IngredientCommand ingredientCommand = new IngredientCommand();
		ingredientCommand.setId(ID_VALUE);
		ingredientCommand.setAmount(AMOUNT);
		ingredientCommand.setDescription(DESCRIPTION);

		// when
		Ingredient ingredient = converter.convert(ingredientCommand);

		// then
		assertNotNull(ingredient);
		assertNull(ingredient.getUnitOfMeasure());
		assertEquals(ID_VALUE, ingredient.getId());
		assertEquals(AMOUNT, ingredient.getAmount());
		assertEquals(DESCRIPTION, ingredient.getDescription());
	}
}