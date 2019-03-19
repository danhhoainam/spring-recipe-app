package com.spring5.recipe.converters;

import com.spring5.recipe.commands.CategoryCommand;
import com.spring5.recipe.domain.Category;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CategoryToCategoryCommandTest {

	public static final String DESCRIPTION = "description";
	public static final Long LONG_VALUE = new Long(1L);

	CategoryToCategoryCommand converter;

	@Before
	public void setUp() throws Exception {

		converter = new CategoryToCategoryCommand();
	}

	@Test
	public void test__convert__parameterIsNull__shouldReturnNull() {

		// when
		CategoryCommand categoryCommand = converter.convert(null);

		// then
		assertNull(categoryCommand);
	}

	@Test
	public void test__convert__parameterIsEmpty__shouldReturnNotNull() {

		// given
		Category category = new Category();

		// when
		CategoryCommand categoryCommand = converter.convert(category);

		// then
		assertNotNull(categoryCommand);
	}

	@Test
	public void test__convert__parameterIsFullInfo__shouldReturnCorrectResult() {

		// given
		Category category = new Category();
		category.setId(LONG_VALUE);
		category.setDescription(DESCRIPTION);

		// when
		CategoryCommand categoryCommand = converter.convert(category);

		// then
		assertNotNull(categoryCommand);
		assertEquals(LONG_VALUE, categoryCommand.getId());
		assertEquals(DESCRIPTION, categoryCommand.getDescription());
	}
}