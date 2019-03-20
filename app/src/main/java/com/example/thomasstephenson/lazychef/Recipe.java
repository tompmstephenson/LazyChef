package com.example.thomasstephenson.lazychef;
import android.graphics.Bitmap;

import java.util.List;
import java.io.File;
/**An instance of class Recipe represents a set of instructions
 * for preparing a certain meal. Includes a list of all necessary ingredients
 * Created by Jack on 3/7/2019.
 */

public class Recipe {

    private String name;
    private List<Ingredient> ingredients;
    private String instructions; //MIGHT WANT TO PARSE AS A LIST
    private int prepTime;
    private int servings;
    private String imageURL;
    private Bitmap image;

    public Recipe(String recName,List<Ingredient> ingList,String instr,int pTime,int recServings,String url,Bitmap img){
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

    public String getName() { return name; }
    public void setName(String recName) { name = recName; }

    public List<Ingredient> getListIngredients() { return ingredients; }
    public void setListIngredients(List<Ingredient> ingList) { ingredients = ingList; }

    public String getInstructions() { return instructions; }
    public void setIngredients(String instr) { instructions = instr; }

    public int getPrepTime() { return prepTime; }
    public void setPrepTime(int pTime) { prepTime = pTime; }

    public int getServings() { return servings; }
    public void intServings(int recServings) { servings = recServings; }

    public String getImageURL() { return imageURL; }
    public void setImageURL(String url){ imageURL = url; }

    public Bitmap getImage() { return image; }
    public void setImage(Bitmap img) { image = img; }

    public int getEstimatedCalories() {
        int totalCal = 0;
        for(int i=0;i<ingredients.size();i++) {
            totalCal += ingredients.get(i).getCalories();
        }
        return totalCal;
    }


}
