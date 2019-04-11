package com.example.thomasstephenson.lazychef;

import android.graphics.Bitmap;

import androidx.room.Entity;
import androidx.room.PrimaryKey;


/**
 * This class represents a recipe that has been saved to the database. It is very similar to Recipe
 * with the inclusion of an id.
 */
@Entity
public class RecipeEntity {

    //fields are public so that they can be inserted into Room Database
    @PrimaryKey(autoGenerate = true)
    public int id;


    public String name;
    public Bitmap image;
    public String instructions;
    public int prepTime;
    public int estimatedCalories;
    public int servings;

    public RecipeEntity(String name, Bitmap image, String instructions, int prepTime, int estimatedCalories, int servings) {
        this.name = name;
        this.image = image;
        this.instructions = instructions;
        this.prepTime = prepTime;
        this.estimatedCalories = estimatedCalories;
        this.servings = servings;
    }

    public RecipeEntity(Recipe recipe) {
        this.name = recipe.getName();
        this.image = recipe.getImage();
        this.instructions = recipe.getInstructions();
        this.prepTime = recipe.getPrepTime();
        this.estimatedCalories = recipe.getEstimatedCalories();
        this.servings = recipe.getServings();
    }
}
