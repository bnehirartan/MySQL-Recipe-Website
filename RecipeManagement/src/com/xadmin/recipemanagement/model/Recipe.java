package com.xadmin.recipemanagement.model;

/**
 * User.java
 * This is a model class represents a User entity
 * @author Ramesh Fadatare
 *
 */
public class Recipe {
	protected int id;
	protected String name;
	protected String recipe;
	protected String ingredients;
	
	public Recipe() {
	}
	
	public Recipe(String name, String recipe, String ingredients) {
		super();
		this.name = name;
		this.recipe = recipe;
		this.ingredients = ingredients;
	}

	public Recipe(int id, String name, String recipe, String ingredients) {
		super();
		this.id = id;
		this.name = name;
		this.recipe = recipe;
		this.ingredients = ingredients;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRecipe() {
		return recipe;
	}
	public void setRecipe(String recipe) {
		this.recipe = recipe;
	}
	public String getIngredients() {
		return ingredients;
	}
	public void setIngredients(String ingredients) {
		this.ingredients = ingredients;
	}
}