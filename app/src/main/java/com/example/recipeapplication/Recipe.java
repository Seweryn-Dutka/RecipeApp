package com.example.recipeapplication;

public class Recipe {
    private int id;
    private String name;
    private String description;
    private String ingredients;
    private int calories;

    public Recipe(int id, String name, String description, String ingredients, int calories) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.ingredients = ingredients;
        this.calories = calories;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }
}
