package org.launchcode.project_workaround.models;

public enum RecipeCategory {

    CHICKEN ("Chicken"),
    FISH ("Fish"),
    RICE ("Rice"),
    EGG ("Egg");

    private final String name;

    RecipeCategory(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
