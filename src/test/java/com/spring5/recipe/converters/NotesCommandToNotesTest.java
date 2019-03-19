package com.spring5.recipe.converters;

import com.spring5.recipe.commands.NotesCommand;
import com.spring5.recipe.domain.Notes;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class NotesCommandToNotesTest {

	public static final String DESCRIPTION = "description";
	public static final Long LONG_VALUE = new Long(1L);

	NotesCommandToNotes converter;

	@Before
	public void setUp() throws Exception {

		converter = new NotesCommandToNotes();
	}

	@Test
	public void test__convert__parameterIsNull__shouldReturnNull() {

		// when
		Notes notes = converter.convert(null);

		// then
		assertNull(notes);
	}

	@Test
	public void test__convert__parameterIsEmpty__shouldReturnNotNull() {

		// given
		NotesCommand notesCommand = new NotesCommand();

		// when
		Notes notes = converter.convert(notesCommand);

		// then
		assertNotNull(notes);
	}

	@Test
	public void test__convert__parameterIsFullInfo__shouldReturnCorrectResult() {

		// given
		NotesCommand notesCommand = new NotesCommand();
		notesCommand.setId(LONG_VALUE);
		notesCommand.setRecipeNotes(DESCRIPTION);

		// when
		Notes notes = converter.convert(notesCommand);

		// then
		assertNotNull(notes);
		assertEquals(LONG_VALUE, notes.getId());
		assertEquals(DESCRIPTION, notes.getRecipeNotes());
	}
}