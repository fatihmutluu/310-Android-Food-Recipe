package com.example.cookr;

public class Recipe {

    private String name, diet, cuisine, introduction, imageUrl;
    private String[] tags, ingredients;

    public Recipe(String name) {
        this.name = name;
    }

    public Recipe(String name, String diet, String cuisine, String introduction, String imageUrl, String[] tags, String[] ingredients) {
        this.name = name;
        this.diet = diet;
        this.cuisine = cuisine;
        this.introduction = introduction;
        this.imageUrl = imageUrl;
        this.tags = tags;
        this.ingredients = ingredients;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDiet() {
        return diet;
    }

    public void setDiet(String diet) {
        this.diet = diet;
    }

    public String getCuisine() {
        return cuisine;
    }

    public void setCuisine(String cuisine) {
        this.cuisine = cuisine;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String[] getTags() {
        return tags;
    }

    public void setTags(String[] tags) {
        this.tags = tags;
    }

    public String[] getIngredients() {
        return ingredients;
    }

    public void setIngredients(String[] ingredients) {
        this.ingredients = ingredients;
    }
}
