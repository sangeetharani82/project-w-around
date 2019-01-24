package org.launchcode.project_workaround.models;

public enum RecipeType {

    BREAKFAST ("BreakFast"),
    LUNCH ("Lunch"),
    DINNER ("Dinner"),
    SWEETS ("Sweets");

    private final String name;

    RecipeType(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
