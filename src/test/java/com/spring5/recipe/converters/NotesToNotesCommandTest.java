package com.spring5.recipe.converters;

import com.spring5.recipe.commands.NotesCommand;
import com.spring5.recipe.domain.Notes;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class NotesToNotesCommandTest {

	public static final String DESCRIPTION = "description";
	public static final Long LONG_VALUE = new Long(1L);

	NotesToNotesCommand converter;

	@Before
	public void setUp() throws Exception {

		converter = new NotesToNotesCommand();
	}

	@Test
	public void test__convert__parameterIsNull__shouldReturnNull() {

		// when
		NotesCommand notes = converter.convert(null);

		// then
		assertNull(notes);
	}

	@Test
	public void test__convert__parameterIsEmpty__shouldReturnNotNull() {

		// given
		Notes notes = new Notes();

		// when
		NotesCommand notesCommand = converter.convert(notes);

		// then
		assertNotNull(notesCommand);
	}

	@Test
	public void test__convert__parameterIsFullInfo__shouldReturnCorrectResult() {

		// given
		Notes notes = new Notes();
		notes.setId(LONG_VALUE);
		notes.setRecipeNotes(DESCRIPTION);

		// when
		NotesCommand notesCommand = converter.convert(notes);

		// then
		assertNotNull(notesCommand);
		assertEquals(LONG_VALUE, notesCommand.getId());
		assertEquals(DESCRIPTION, notesCommand.getRecipeNotes());
	}
}