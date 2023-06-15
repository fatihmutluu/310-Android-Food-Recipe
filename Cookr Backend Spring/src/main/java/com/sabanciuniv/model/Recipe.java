package com.sabanciuniv.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "recipes")
public class Recipe {
	@Id
	private String id;
	private String name;
	private String diet;
	private String cuisine;
	private List<String> tags;
	private List<String> ingredients;
	private String introduction;
	private String imageUrl;

	public Recipe() {
	}

	public Recipe(String name, String diet ,String cuisine, List<String> tags, List<String> ingredients,
			String introduction, String image) {
		this.name = name;
		this.diet = diet;
		this.cuisine = cuisine;
		this.tags = tags;
		this.ingredients = ingredients;
		this.introduction = introduction;
		this.imageUrl = image;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCuisine() {
		return cuisine;
	}

	public void setCuisine(String cuisine) {
		this.cuisine = cuisine;
	}

	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}

	public List<String> getIngredients() {
		return ingredients;
	}

	public void setIngredients(List<String> ingredients) {
		this.ingredients = ingredients;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	@Override
	public String toString() {
		return "Recipe [name=" + name + ", cuisine=" + cuisine + ", tags=" + tags + ", ingredients=" + ingredients
				+ ", introduction=" + introduction + "]";
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getDiet() {
		return diet;
	}

	public void setDiet(String diet) {
		this.diet = diet;
	}

}
