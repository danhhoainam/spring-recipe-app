package com.spring5.recipe.controllers;

import com.spring5.recipe.commands.RecipeCommand;
import com.spring5.recipe.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
public class RecipeController {

	private final RecipeService recipeService;

	public RecipeController(RecipeService recipeService) {
		this.recipeService = recipeService;
	}

	@RequestMapping("/recipe/show/{id}")
	public String getRecipe(@PathVariable("id") Long id, Model model) {

		model.addAttribute("recipe", recipeService.findById(id));

		return "recipe/show";
	}

	@RequestMapping("recipe/new")
	public String newRecipe(Model model) {

		model.addAttribute("recipe", new RecipeCommand());

		return "recipe/recipeform";
	}

	@RequestMapping("recipe/{id}/update")
	public String updateRecipe(@PathVariable String id, Model model) {

		model.addAttribute("recipe", recipeService.findCommandById(Long.valueOf(id)));
		return "recipe/recipeform";
	}

	@PostMapping
	@RequestMapping("recipe")
	public String saveOrUpdate(@ModelAttribute RecipeCommand command) {

		RecipeCommand savedCommand = recipeService.saveRecipeCommand(command);

		return "redirect:/recipe/show/" + savedCommand.getId();
	}
}
