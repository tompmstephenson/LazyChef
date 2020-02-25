package com.example.thomasstephenson.lazychef;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Index;
import android.content.Context;
import android.graphics.Bitmap;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.graphics.BitmapFactory;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * This class represents a recipe that has been saved to the database. It is very similar to Recipe
 * with the inclusion of an id.
 */
@Entity //(indices = @Index(value = "name", unique = true))
public class RecipeEntity {

    //fields are public so that they can be inserted into Room Database
    @PrimaryKey(autoGenerate = true)
    public int id;


    public String name;
    @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
    public byte[] image;
    public String instructions;
    public int prepTime;
    //public int estimatedCalories;
    public int servings;
    public String imageURL;
    public String ingredients;

    public RecipeEntity(String name, byte[] image, String instructions, int prepTime, int servings, String imageURL, String ingredients) {
        this.name = name;
        this.image = image;
        this.instructions = instructions;
        this.prepTime = prepTime;
        //this.estimatedCalories = estimatedCalories;
        this.servings = servings;
        this.imageURL = imageURL;
        this.ingredients = ingredients;
    }

    public RecipeEntity(Recipe recipe) {
        this.name = recipe.getName();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        Bitmap bmp = recipe.getImage();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        this.image = byteArray;
        this.instructions = recipe.getInstructions();
        this.prepTime = recipe.getPrepTime();
        //this.estimatedCalories = recipe.getEstimatedCalories();
        this.servings = recipe.getServings();
        this.imageURL = recipe.getImageURL();
        /*
        String ings = new String();
        for (int i = 0; i < recipe.getListIngredients().size(); i++) {
            Ingredient j = recipe.getListIngredients().get(i);
            ings += j.toString();
            if (i < recipe.getListIngredients().size() - 1) {
                ings += ",";
            }
        }
        */
        this.ingredients = recipe.getListIngredients();
    }

    public Recipe toRecipe(Context context) {

        /*
        List <String> ingredientNames = Arrays.asList(this.ingredients.split(","));
        List <Ingredient> ingredients = new ArrayList<>();
        for (int i = 0; i < ingredientNames.size(); i++) {
            ingredients.add(new Ingredient(ingredientNames.get(i), 0, null, null, 0));
        }
        */
        ByteArrayInputStream arrayInputStream = new ByteArrayInputStream(image);
        Bitmap recImage = BitmapFactory.decodeStream(arrayInputStream);
        return new Recipe(this.name, ingredients, instructions, prepTime, servings, imageURL, recImage);
    }
}
