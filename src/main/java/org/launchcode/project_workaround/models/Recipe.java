package org.launchcode.project_workaround.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Recipe {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min=3, max=15)
    private String recipeName;
    @NotNull
    private RecipeType courseName;
    @NotNull
    private RecipeCategory category;
//    @NotNull
//    private RecipeIngredient ingredient;
    @NotNull
    private int servingSize;
    @NotNull
    private String prepTime;
    @NotNull
    private String cookTime;
    @NotNull
    private String ingredient;
    @NotNull
    @Size(min=3, message = "Direction field should not be empty")
    private String direction;


    public Recipe(String recipeName, int servingSize, String prepTime, String cookTime, String ingredient,
                  String direction) {
        this();
        this.recipeName = recipeName;
        this.servingSize = servingSize;
        this.prepTime = prepTime;
        this.cookTime = cookTime;
        this.ingredient = ingredient;
        this.direction = direction;
    }

    public Recipe(){ }

    public int getId() {
        return id;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public int getServingSize() {
        return servingSize;
    }

    public void setServingSize(int servingSize) {
        this.servingSize = servingSize;
    }

    public String getPrepTime() {
        return prepTime;
    }

    public void setPrepTime(String prepTime) {
        this.prepTime = prepTime;
    }

    public String getCookTime() {
        return cookTime;
    }

    public void setCookTime(String cookTime) {
        this.cookTime = cookTime;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public RecipeType getCourseName() {
        return courseName;
    }

    public void setCourseName(RecipeType courseName) {
        this.courseName = courseName;
    }

    public RecipeCategory getCategory() {
        return category;
    }

    public void setCategory(RecipeCategory category) {
        this.category = category;
    }

//    public RecipeIngredient getIngredient() {
//        return ingredient;
//    }

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }
//
//    public void setIngredient(RecipeIngredient ingredient) {
//        this.ingredient = ingredient;
//    }
}
