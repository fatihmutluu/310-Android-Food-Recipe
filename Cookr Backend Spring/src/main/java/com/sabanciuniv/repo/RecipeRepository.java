package com.sabanciuniv.repo;

import com.sabanciuniv.model.Recipe;

import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Set;

public interface RecipeRepository extends MongoRepository<Recipe, String> {
	// Find all recipes by cuisine
	List<Recipe> findByCuisineContainingIgnoreCase(String cuisine);

	// Find all recipes by diet
	List<Recipe> findByDietContainingIgnoreCase(String diet);

	// Find recipes by name (case-insensitive)
	List<Recipe> findByNameContainingIgnoreCase(String name);

	// Find a random recipe
	@Aggregation(pipeline = {
			"{ $sample: { size: ?0 } }",
			"{ $project: { _id: 0 } }"
	})
	List<Recipe> findRandomRecipes(int limit);

	// Find all cuisines
	@Query(value = "{}", fields = "{ 'cuisine': 1, '_id': 0 }")
	List<String> findDistinctCuisines();

	@Query(value = "{}", fields = "{ 'diet': 1, '_id': 0 }")
	List<String> findDistinctDiets();

	// Find all tags
	@Query(value = "{}", fields = "{ 'tags': 1, '_id': 0 }")
	List<String> findDistinctTags();

	// Find all recipes by tags
	List<Recipe> findByTagsIn(List<String> tags);
}
