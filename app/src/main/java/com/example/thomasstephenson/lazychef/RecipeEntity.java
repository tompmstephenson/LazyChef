package com.example.thomasstephenson.lazychef;

import android.arch.persistence.room.ColumnInfo;
import android.graphics.Bitmap;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.io.ByteArrayOutputStream;


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
    @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
    public byte[] image;
    public String instructions;
    public int prepTime;
    public int estimatedCalories;
    public int servings;

    public RecipeEntity(String name, byte[] image, String instructions, int prepTime, int estimatedCalories, int servings) {
        this.name = name;
        this.image = image;
        this.instructions = instructions;
        this.prepTime = prepTime;
        this.estimatedCalories = estimatedCalories;
        this.servings = servings;
    }

    public RecipeEntity(Recipe recipe) {
        this.name = recipe.getName();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        Bitmap bmp = recipe.getImage();
        bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        this.image = byteArray;
        this.instructions = recipe.getInstructions();
        this.prepTime = recipe.getPrepTime();
        this.estimatedCalories = recipe.getEstimatedCalories();
        this.servings = recipe.getServings();
    }
}
