package com.example.thomasstephenson.lazychef;

import android.arch.persistence.room.Index;
import android.graphics.Bitmap;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.graphics.BitmapFactory;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import static android.arch.persistence.room.ForeignKey.CASCADE;

/**
 * This represents an ingredient of a recipe that has been saved to the phone. You usually aren't
 * going to need to access this directly. Use recipeDao.findIngredientsForRecipe() to find these
 * objects in the database.
 */

@Entity(foreignKeys = @ForeignKey(entity = RecipeEntity.class,
                        parentColumns = "id",
                        childColumns = "recipeId",
                        onDelete = CASCADE),
        indices = {@Index("recipeId")})
public class IngredientEntity {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public String name;
    public int amount;
    public String unit;
    public String type;
    public int calories;
    public byte[] image;
    public String imageURL;

    public int recipeId;

    public IngredientEntity(String name, int amount, String unit, String type, int calories, byte[] image, String imageURL, final int recipeId) {
        this.name = name;
        this.amount = amount;
        this.unit = unit;
        this.type = type;
        this.calories = calories;
        this.image = image;
        this.recipeId = recipeId;
        this.imageURL = imageURL;
    }

    public IngredientEntity(Ingredient ingredient, RecipeEntity recipeEntity) {
        this.name = ingredient.getName();
        this.amount = ingredient.getAmount();
        this.unit = ingredient.getUnit();
        this.type = ingredient.getType();
        this.calories = ingredient.getCalories();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        Bitmap bmp = ingredient.getImage();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        this.image = byteArray;
        this.recipeId = recipeEntity.id;
        this.imageURL = ingredient.getImageURL();
    }

    public Ingredient toIngredient() {
        ByteArrayInputStream arrayInputStream = new ByteArrayInputStream(image);
        Bitmap bitmapImage = BitmapFactory.decodeStream(arrayInputStream);
        return new Ingredient(name, amount, type, unit, calories, imageURL, bitmapImage);
    }

}
