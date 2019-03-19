package com.spring5.recipe.commands;

import com.spring5.recipe.domain.Recipe;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class NotesCommand {

	private Long id;
	private String recipeNotes;
}
