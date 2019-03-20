package com.spring5.recipe.converters;

import com.spring5.recipe.commands.IngredientCommand;
import com.spring5.recipe.domain.Ingredient;
import com.spring5.recipe.domain.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class IngredientToIngredientCommandTest {

	public static final BigDecimal AMOUNT = new BigDecimal("1");
	public static final String DESCRIPTION = "Cheeseburger";
	public static final Long ID_VALUE = new Long(1L);
	public static final Long UOM_ID = new Long(2L);

	IngredientToIngredientCommand converter;

	@Before
	public void setUp() throws Exception {
		converter = new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand());
	}

	@Test
	public void test__convert__parameterIsNull__shouldReturnNull() throws Exception {
		assertNull(converter.convert(null));
	}

	@Test
	public void test__convert__parameterIsEmpty__shouldReturnNotNull() throws Exception {
		assertNotNull(converter.convert(new Ingredient()));
	}

	@Test
	public void test__convert__parameterIsFullInfo__shouldReturnCorrectResult() throws Exception {

		// given
		Ingredient ingredient = new Ingredient();
		ingredient.setId(ID_VALUE);
		ingredient.setAmount(AMOUNT);
		ingredient.setDescription(DESCRIPTION);

		UnitOfMeasure unitOfMeasure= new UnitOfMeasure();
		unitOfMeasure.setId(UOM_ID);
		ingredient.setUnitOfMeasure(unitOfMeasure);

		// when
		IngredientCommand ingredientCommand = converter.convert(ingredient);

		// then
		assertNotNull(ingredientCommand);
		assertNotNull(ingredientCommand.getUnitOfMeasure());
		assertEquals(ID_VALUE, ingredientCommand.getId());
		assertEquals(AMOUNT, ingredientCommand.getAmount());
		assertEquals(DESCRIPTION, ingredientCommand.getDescription());
		assertEquals(UOM_ID, ingredientCommand.getUnitOfMeasure().getId());
	}

	@Test
	public void test__convert__parameterUomIsNull__shouldReturnCorrectResultWithUomNull() throws Exception {

		// given
		Ingredient ingredient = new Ingredient();
		ingredient.setId(ID_VALUE);
		ingredient.setAmount(AMOUNT);
		ingredient.setDescription(DESCRIPTION);

		// when
		IngredientCommand ingredientCommand = converter.convert(ingredient);

		// then
		assertNotNull(ingredientCommand);
		assertNull(ingredientCommand.getUnitOfMeasure());
		assertEquals(ID_VALUE, ingredientCommand.getId());
		assertEquals(AMOUNT, ingredientCommand.getAmount());
		assertEquals(DESCRIPTION, ingredientCommand.getDescription());
	}
}