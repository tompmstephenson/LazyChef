package com.example.thomasstephenson.lazychef;
import java.util.ArrayList;
import java.util.List;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import android.util.Log;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by thomasstephenson on 3/11/19.
 * An instance of class Query represents a recipe search induced by the user.
 */

public class Query {
    private String[] ingNames;
    //Instance of the query constructor for there not being
    public void QueryIngredients(){
        QueryIngredientsHelper(this.ingNames);
    }


    /* API Key. Right now, this is my personal API key. In the future we might want to change this
     * to a team-wide API key (I don't want my debit card to be charged...
     */
    private final String MASHAPE_AUTH = "9c1a1208bbmsh1a3a7f5fe78b0a7p15f439jsn581ffaba03cb";

    //find recipes that match ingredients
    private List<Recipe> QueryIngredientsHelper(String[] ingredientNames) {
        List<Recipe> recipes = new ArrayList<>();
        HttpResponse<JsonNode> response;
        try {

            //building the request String that will query the API
            StringBuilder request = new StringBuilder("https://spoonacular-recipe-food-nutrition-v1.p.rapidapi.com/recipes/findByIngredients?number=5&ranking=2&ingredients=");
            for (int i = 0; i < ingredientNames.length; i++) {
                request.append(ingredientNames[i]);
                if (i != ingredientNames.length - 1) {
                    request.append("%2C");
                }
            }

            // this is where we access the API in search of recipes that match the ingredients available
            response = Unirest.get(request.toString())
                    .header("X-RapidAPI-Key", MASHAPE_AUTH)
                    .asJson();

            //making an array of recipes out of the JSON blob that the API returned
            JSONArray jsonRecipes = response.getBody().getArray();
            for (int i = 0; i < jsonRecipes.length(); i++) {

                //fields needed for recipe constructor
                String recName;
                List<Ingredient> ingList;
                String instr;
                int preptime;
                int servings;
                int id;

                JSONObject jsonRecipe = jsonRecipes.getJSONObject(i);
                recName = jsonRecipe.getString("title");  //get the name of the recipe
                id = jsonRecipe.getInt("id"); //get the id of the recipe, from which we will extract the other information

                //extract recipe information from API
                HttpResponse<JsonNode> recipeResponse = Unirest.get("https://spoonacular-recipe-food-nutrition-v1.p.rapidapi.com/recipes/" + id + "/information")
                        .header("X-RapidAPI-Key", MASHAPE_AUTH)
                        .asJson();

                //make into JSON Object
                JSONObject jsonRecipeDetails = recipeResponse.getBody().getObject();

                //extract more fields needed for constructor
                instr = jsonRecipeDetails.getString("instructions");
                preptime = jsonRecipeDetails.getInt("readyInMinutes");
                servings = jsonRecipeDetails.getInt("servings");

                ingList = new ArrayList<>();
                JSONArray jsonRecipeIngredients = jsonRecipeDetails.getJSONArray("extendedIngredients");

                //extract ingredients
                for (int j = 0; j < jsonRecipeIngredients.length(); j++) {
                    //fields needed for Ingredient constuctor
                    String ingName;
                    int ingAmount;
                    String ingUnit;
                    int ingCal;

                    //extracting ingredient information
                    JSONObject jsonIngredient = jsonRecipeIngredients.getJSONObject(j);
                    ingName = jsonIngredient.getString("name");
                    ingAmount = jsonIngredient.getInt("amount");
                    ingUnit = jsonIngredient.getString("unit");
                    ingList.add(new Ingredient(ingName, ingAmount, ingUnit));
                }

                recipes.add(new Recipe(recName, ingList, instr, preptime, servings));
            }
        }

        catch (UnirestException e) { //error from accessing the API
            Log.e("Query-API", e.getMessage());
        }

        catch (JSONException e) { //error parsing JSON
            Log.e("Query-JSON", e.getMessage());
        }

        return recipes;
    }

    public void AddToQueryIngredients(String ingredientName) {
        String[] cloneList = new String[ingNames.length + 1];
        //copying original list
        for(int i = 0; i < cloneList.length + 1; i++) {
            if(i < ingNames.length)
                cloneList[i] = ingNames[i];
        //adding final ingredient
            else
                cloneList[i] = ingredientName;
        }

        ingNames = cloneList;
    }

    public void DeleteFromQueryIngredients(String ingredientName) {
       String[] cloneList = new String[ingNames.length - 1];
        for(int i = 0; i < cloneList.length; i++){
            //copies list until finds item to delete, replaces with last item in list
            if(ingNames[i].equals(ingredientName))
                cloneList[i] = ingNames[ingNames.length - 1];
            else
                cloneList[i] = ingNames[i];
        }
        //copies list back to original var
        ingNames = cloneList;
    }
//cant do until pantry class is written
    public void AddIngredientsPantry() {

    }

    public String[] getIngNames() {
        return ingNames;
    }


}
