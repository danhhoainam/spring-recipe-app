package com.spring5.recipe.converters;

import com.spring5.recipe.commands.CategoryCommand;
import com.spring5.recipe.domain.Category;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CategoryCommandToCategoryTest {

	public static final String DESCRIPTION = "description";
	public static final Long LONG_VALUE = new Long(1L);

	CategoryCommandToCategory converter;

	@Before
	public void setUp() throws Exception {

		converter = new CategoryCommandToCategory();
	}

	@Test
	public void test__convert__parameterIsNull__shouldReturnNull() {

		// when
		Category category = converter.convert(null);

		// then
		assertNull(category);
	}

	@Test
	public void test__convert__parameterIsEmpty__shouldReturnNotNull() {

		// given
		CategoryCommand categoryCommand = new CategoryCommand();

		// when
		Category category = converter.convert(categoryCommand);

		// then
		assertNotNull(category);
	}

	@Test
	public void test__convert__parameterIsFullInfo__shouldReturnCorrectResult() {

		// given
		CategoryCommand categoryCommand = new CategoryCommand();
		categoryCommand.setId(LONG_VALUE);
		categoryCommand.setDescription(DESCRIPTION);

		// when
		Category category = converter.convert(categoryCommand);

		// then
		assertNotNull(category);
		assertEquals(LONG_VALUE, category.getId());
		assertEquals(DESCRIPTION, category.getDescription());
	}
}