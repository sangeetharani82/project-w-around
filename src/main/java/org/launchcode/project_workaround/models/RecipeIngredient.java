package org.launchcode.project_workaround.models;

public enum RecipeIngredient {

    RICE ("Rice"),
    SALT ("Salt"),
    SUGAR ("Sugar"),
    MILK ("Milk");

    private final String name;

    RecipeIngredient(String name){
        this.name = name;
    }

    public String getName() {

        return name;
    }
}
