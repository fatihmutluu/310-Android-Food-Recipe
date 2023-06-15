package com.sabanciuniv;

import com.sabanciuniv.model.Recipe;
import com.sabanciuniv.repo.RecipeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class CookrApplication implements CommandLineRunner {

	@Autowired
	private RecipeRepository recipeRepo;

	private static final Logger logger = LoggerFactory.getLogger(CookrApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(CookrApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		if (recipeRepo.count() == 0) {
			Recipe recipe = new Recipe(
					"Spaghetti Bolognese",
					"Vegan",
					"Italian",
					List.of("Hot", "Main Course"),
					List.of("spaghetti", "ground beef", "tomato sauce", "onion", "garlic"),
					"A classic Italian dish, Spaghetti Bolognese is a hearty pasta dish with a flavorful meat sauce.",
					"https://example.com/spaghetti-bolognese.jpg");

			recipeRepo.save(recipe);

			logger.info("Added a sample recipe to the repository.");
		}
	}
}
