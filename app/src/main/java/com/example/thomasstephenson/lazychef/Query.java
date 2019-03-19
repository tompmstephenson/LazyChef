package com.example.thomasstephenson.lazychef;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.function.Function;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.RequestFuture;
import com.android.volley.toolbox.Volley;
import android.app.Activity;
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
    public void QueryIngredients(Activity activity){
        QueryIngredientsHelper(this.ingNames, activity);
    }


    /* API Key. Right now, this is my personal API key. In the future we might want to change this
     * to a team-wide API key (I don't want my debit card to be charged...
     */
    private final String MASHAPE_AUTH = "9c1a1208bbmsh1a3a7f5fe78b0a7p15f439jsn581ffaba03cb";

    List<Recipe> recipes = new ArrayList<>();

    public void getRecipeDetails(String request, final String recipeName, final Activity activity) {
        RequestQueue requestQueue = Volley.newRequestQueue(activity);
        JsonObjectRequest JSONrequest = new JsonObjectRequest(Request.Method.GET, request, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                addRecipe(response, recipeName, activity);
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error
                    }
                })
        {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String>  params = new HashMap<>();
                params.put("X-RapidAPI-Key", MASHAPE_AUTH);
                return params;
            }
        };

        requestQueue.add(JSONrequest);
    }

    public void getRecipes(String request, final Activity activity) {
        RequestQueue requestQueue = Volley.newRequestQueue(activity);
        JsonArrayRequest JSONrequest = new JsonArrayRequest(Request.Method.GET, request, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                createRecipes(response, activity);
            }
        },
            new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    // TODO: Handle error
                }
            })
        {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String>  params = new HashMap<>();
                params.put("X-RapidAPI-Key", MASHAPE_AUTH);
                return params;
            }
        };

        requestQueue.add(JSONrequest);
    }

    public void addRecipe(JSONObject jsonRecipeDetails, String recName, Activity activity) {
        try {
            String instr = jsonRecipeDetails.getString("instructions");
            int preptime = jsonRecipeDetails.getInt("readyInMinutes");
            int servings = jsonRecipeDetails.getInt("servings");

            List<Ingredient> ingList = new ArrayList<>();
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
            Recipe recipe = new Recipe(recName, ingList, instr, preptime, servings);
            recipes.add(recipe);
            MainActivity.createRecipeView(recipe, activity);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    //making an array of recipes out of the JSON blob that the API returned
    public void createRecipes(JSONArray jsonRecipes, Activity activity) {
        for (int i = 0; i < jsonRecipes.length(); i++) {

            //fields needed for recipe constructor
            String recName;
            List<Ingredient> ingList;
            String instr;
            int preptime;
            int servings;
            int id;
            try {
                JSONObject jsonRecipe = jsonRecipes.getJSONObject(i);
                Log.d("RECIPE NAME", jsonRecipe.toString());
                recName = jsonRecipe.getString("title");  //get the name of the recipe
                id = jsonRecipe.getInt("id"); //get the id of the recipe, from which we will extract the other information
                String request = "https://spoonacular-recipe-food-nutrition-v1.p.rapidapi.com/recipes/" + id + "/information";

                //extract recipe information from API
                //make into JSON Object
                getRecipeDetails(request, recName, activity);

                //extract more fields needed for constructor
            }
            catch (Exception e) {
                Log.e("ERROR", e.getMessage());
            }
        }
    }
    //find recipes that match ingredients
    public void QueryIngredientsHelper(String[] ingredientNames, Activity activity) {
        List<Recipe> recipes = new ArrayList<>();
        try {
            //building the request String that will query the API
            StringBuilder request = new StringBuilder("https://spoonacular-recipe-food-nutrition-v1.p.rapidapi.com/recipes/findByIngredients?number=5&ranking=2&ingredients=");
            for (int i = 0; i < ingredientNames.length; i++) {
                request.append(ingredientNames[i]);
                if (i != ingredientNames.length - 1) {
                    request.append("%2C");
                }
            }
            getRecipes(request.toString(), activity);
        } catch (Exception e) {
            Log.e("ERROR", e.getMessage());
        }
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
    public void AddIngredientsPantry(Pantry p) {
        for(Ingredient i: p.getIngredientList()) {
            AddToQueryIngredients(i.getName());
        }

    }

    public String[] getIngNames() {
        return ingNames;
    }


}
