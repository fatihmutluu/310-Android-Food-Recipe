package com.sabanciuniv.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.sabanciuniv.model.Recipe;
import com.sabanciuniv.repo.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cookr")
public class RecipeController {
	private final RecipeRepository recipeRepository;

	@Autowired
	public RecipeController(RecipeRepository recipeRepository) {
		this.recipeRepository = recipeRepository;
	}

	// !Recipes

	// Get all recipes
	@GetMapping("/recipes")
	public List<Recipe> getAllRecipes() {
		return recipeRepository.findAll();
	}

	// Create a new recipe
	@PostMapping("/recipes")
	public Recipe createRecipe(@RequestBody Recipe recipe) {
		return recipeRepository.save(recipe);
	}

	// Get recipes by name (search)
	@GetMapping("/recipes/search")
	public List<Recipe> searchRecipesByName(@RequestParam("name") String name) {
		return recipeRepository.findByNameContainingIgnoreCase(name);
	}

	// Get random recipes
	@GetMapping("/recipes/random")
	public List<Recipe> getRandomRecipes() {
		return recipeRepository.findRandomRecipes(7); // Retrieve 5 random recipes
	}

	// Retrieve a recipe by ID
	@GetMapping("/recipes/{id}")
	public Optional<Recipe> getRecipeById(@PathVariable("id") String id) {
		return recipeRepository.findById(id);
	}

	// Delete a recipe
	@DeleteMapping("/recipes/{id}")
	public void deleteRecipe(@PathVariable("id") String id) {
		recipeRepository.deleteById(id);
	}

	// !Cuisines
	// Get all cuisines
	@GetMapping("/cuisines")
	public Set<String> getAllCuisines() {
		List<String> cuisines = recipeRepository.findDistinctCuisines();
		Set<String> uniqueCuisines = new HashSet<>();

		for (String cuisine : cuisines) {
			try {
				JsonNode jsonNode = new ObjectMapper().readTree(cuisine);
				if (jsonNode.has("cuisine")) {
					String cuisineValue = jsonNode.get("cuisine").asText();
					if (!cuisineValue.isEmpty()) {
						uniqueCuisines.add(cuisineValue);
					}
				}
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		}

		return uniqueCuisines;
	}

	// Get all recipes by cuisine
	@GetMapping("/cuisines/{cuisine}")
	public List<Recipe> getRecipesByCuisine(@PathVariable("cuisine") String cuisine) {
		return recipeRepository.findByCuisineContainingIgnoreCase(cuisine);
	}

	// !Diets
	// Get all diets
	@GetMapping("/diets")
	public Set<String> getAllDiets() {
		List<String> diets = recipeRepository.findDistinctDiets();
		Set<String> uniqueDiets = new HashSet<>();

		for (String diet : diets) {
			try {
				JsonNode jsonNode = new ObjectMapper().readTree(diet);
				if (jsonNode.has("diet")) {
					String dietValue = jsonNode.get("diet").asText();
					if (!dietValue.isEmpty()) {
						uniqueDiets.add(dietValue);
					}
				}
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		}

		return uniqueDiets;
	}






	// Get all recipes by diet
	@GetMapping("/diets/{diet}")
	public List<Recipe> getRecipesByDiet(@PathVariable("diet") String diet) {
		return recipeRepository.findByDietContainingIgnoreCase(diet);
	}

	// Get all tags
	@GetMapping("/tags")
	public Set<String> getAllTags() {
		List<String> recipes = recipeRepository.findDistinctTags();
		Set<String> uniqueTags = new HashSet<>();

		for (String recipe : recipes) {
			try {
				JsonNode jsonNode = new ObjectMapper().readTree(recipe);
				if (jsonNode.has("tags")) {
					JsonNode tagsNode = jsonNode.get("tags");
					if (tagsNode.isArray()) {
						ArrayNode tagsArray = (ArrayNode) tagsNode;
						for (JsonNode tagNode : tagsArray) {
							String tagValue = tagNode.asText();
							if (!tagValue.isEmpty()) {
								uniqueTags.add(tagValue);
							}
						}
					}
				}
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		}

		return uniqueTags;
	}

	// Get recipes by tags (search)
	@GetMapping("/tags/searchByTags")
	public List<Recipe> searchRecipesByTags(@RequestParam("tags") List<String> tags) {
		return recipeRepository.findByTagsIn(tags);
	}

}
