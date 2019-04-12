package com.example.thomasstephenson.lazychef;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;
import java.io.File;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.util.Log;

/**An instance of class Recipe represents a set of instructions
 * for preparing a certain meal. Includes a list of all necessary ingredients
 * Created by Jack on 3/7/2019.
 */

public class Recipe implements Parcelable {

    private String name;
    private List<Ingredient> ingredients;
    private String instructions; //MIGHT WANT TO PARSE AS A LIST
    private int prepTime;
    private int servings;
    private String imageURL;
    private Bitmap image;

    public Recipe(String recName,List<Ingredient> ingList,String instr,int pTime,int recServings,String url,Bitmap img) {
        name = recName;
        ingredients = ingList;
        instructions = instr;
        prepTime = pTime;
        servings = recServings;
        imageURL = url;
        image = img;
    }
    public Recipe(String recName,List<Ingredient> ingList,String instr,int pTime,int recServings) { //Recipe constructor when image unavailable
        name = recName;
        ingredients = ingList;
        instructions = instr;
        prepTime = pTime;
        servings = recServings;
    }

    protected Recipe(Parcel in) {
        name = in.readString();
        instructions = in.readString();
        imageURL = in.readString();
        prepTime = in.readInt();
        servings = in.readInt();
        ingredients = in.createTypedArrayList(Ingredient.CREATOR);
    }

    public static final Creator<Recipe> CREATOR = new Creator<Recipe>() {
        @Override
        public Recipe createFromParcel(Parcel in) {
            return new Recipe(in);
        }

        @Override
        public Recipe[] newArray(int size) {
            return new Recipe[size];
        }
    };

    public String getName() { return name; }
    public void setName(String recName) { name = recName; }

    public List<Ingredient> getListIngredients() { return ingredients; }
    public void setListIngredients(List<Ingredient> ingList) { ingredients = ingList; }

    public String getInstructions() { return instructions; }
    public void setInstructions(String instr) { instructions = instr; }

    public int getPrepTime() { return prepTime; }
    public void setPrepTime(int pTime) { prepTime = pTime; }

    public int getServings() { return servings; }
    public void setServings(int recServings) { servings = recServings; }

    public String getImageURL() { return imageURL; }
    public void setImageURL(String url){ imageURL = url; }

    public Bitmap getImage() { return image; }
    public void setImage(Bitmap img) { image = img; }

    public int getEstimatedCalories() {
        int totalCal = 0;
        for(int i=0;i<ingredients.size();i++) {
            try {
                totalCal += (ingredients.get(i).getCalories() * ingredients.get(i).getAmount());
            }
            catch (Exception e) {
                Log.d("SAVED_INGREDIENTS", e.getMessage());
            }
        }
        return totalCal;
    }

    public void saveRecipe(Context context) {
        RecipeEntity recipeEntity = new RecipeEntity(this);

        RecipeDao recipeDao = RecipeDatabase.getInstance(context.getApplicationContext()).getRecipeDao();
        recipeDao.insert(recipeEntity);


        /*
        IngredientDao ingredientDao = IngredientDatabase.getInstance(context.getApplicationContext()).getIngredientDao();

        for (Ingredient ingredient: ingredients) {
            IngredientEntity ingredientEntity = new IngredientEntity(ingredient, recipeEntity);
            ingredientDao.insert(ingredientEntity);
        }
        */
    }



    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int j) {
        parcel.writeString(name);
        parcel.writeString(instructions);
        parcel.writeString(imageURL);
        parcel.writeInt(prepTime);
        parcel.writeInt(servings);
        Parcelable[] ingredientParcels = new Parcelable[ingredients.size()];
        ingredients.toArray(ingredientParcels);
        parcel.writeParcelableArray(ingredientParcels, 0);
    }
}
