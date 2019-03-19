package com.spring5.recipe.converters;

import com.spring5.recipe.commands.UnitOfMeasureCommand;
import com.spring5.recipe.domain.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UnitOfMeasureCommandToUnitOfMeasureTest {

	public static final String DESCRIPTION = "description";
	public static final Long LONG_VALUE = new Long(1L);

	UnitOfMeasureCommandToUnitOfMeasure converter;

	@Before
	public void setUp() throws Exception {
		converter = new UnitOfMeasureCommandToUnitOfMeasure();
	}

	@Test
	public void test__convert__parameterIsNull__shouldReturnNull() {

		// when
		UnitOfMeasure result = converter.convert(null);

		// then
		assertNull(result);
	}

	@Test
	public void test__convert__parameterIsEmptyObject__shouldReturnNotNull() {

		// when
		UnitOfMeasure result = converter.convert(new UnitOfMeasureCommand());

		// then
		assertNotNull(result);
	}

	@Test
	public void test__convert__parameterIsFullInfo__shouldReturnCorrectResult() {

		// given
		UnitOfMeasureCommand command = new UnitOfMeasureCommand();
		command.setId(LONG_VALUE);
		command.setDescription(DESCRIPTION);

		// when
		UnitOfMeasure uom = converter.convert(command);

		// then
		assertNotNull(uom);
		assertEquals(LONG_VALUE, uom.getId());
		assertEquals(DESCRIPTION, uom.getDescription());
	}
}