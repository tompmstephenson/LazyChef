package com.example.thomasstephenson.lazychef;

import android.graphics.Bitmap;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

/**
 * This represents an ingredient of a recipe that has been saved to the phone. You usually aren't
 * going to need to access this directly. Use recipeDao.findIngredientsForRecipe() to find these
 * objects in the database.
 */

@Entity(foreignKeys = @ForeignKey(entity = Recipe.class,
                        parentColumns = "id",
                        childColumns = "recipeId",
                        onDelete = CASCADE))
public class IngredientEntity {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public String name;
    public int amount;
    public String unit;
    public String type;
    public int calories;
    public Bitmap image;

    public int recipeId;

    public IngredientEntity(String name, int amount, String unit, String type, int calories, Bitmap image, final int recipeId) {
        this.name = name;
        this.amount = amount;
        this.unit = unit;
        this.type = type;
        this.calories = calories;
        this.image = image;
        this.recipeId = recipeId;
    }

    public IngredientEntity(Ingredient ingredient, RecipeEntity recipeEntity) {
        this.name = ingredient.getName();
        this.amount = ingredient.getAmount();
        this.unit = ingredient.getUnit();
        this.type = ingredient.getType();
        this.calories = ingredient.getCalories();
        this.image = ingredient.getImage();
        this.recipeId = recipeEntity.id;
    }

}
