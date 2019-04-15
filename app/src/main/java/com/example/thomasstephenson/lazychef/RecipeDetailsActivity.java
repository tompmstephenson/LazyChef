package com.example.thomasstephenson.lazychef;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Parcel;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class RecipeDetailsActivity extends AppCompatActivity {

    TextView mRecipeName;
    TextView mRecipeInstructions;
    TextView mRecipeIngredients;
    TextView mPrepTime;
    TextView mServings;
    static ImageView mRecipeImage;
    Button mSaveOrDeleteButton;
    Recipe recipe;

    private View.OnClickListener mSaveButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            AddRecipesAsync async = new AddRecipesAsync(recipe, RecipeDetailsActivity.this);
            async.execute();
}
    };

    private View.OnClickListener mRemoveButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            RemoveRecipesAsync async = new RemoveRecipesAsync(recipe, RecipeDetailsActivity.this);
            async.execute();
        }
    };

    public void setRecipeName(String name) {
        mRecipeName.setText(name);
    }

    public void setRecipeInstructions(String instructions) {
        mRecipeInstructions.setText(instructions);
    }

    public void setRecipeIngredients(String ingredients) {
        mRecipeIngredients.setText(ingredients);
    }

    public void setRecipeServings(int servings) {
        mServings.setText("Servings: " + servings);
    }

    public void setRecipePrepTime(int prepTime) {
        mPrepTime.setText("Prep Time: " + prepTime + " minutes");
    }

    public static void setRecipeImage(Bitmap image) {
        mRecipeImage.setImageBitmap(image);
    }

    public void setView(Recipe recipe) {
        setRecipeName(recipe.getName());
        setRecipeInstructions(recipe.getInstructions());
        setRecipeIngredients(recipe.getListIngredients());
        setRecipeServings(recipe.getServings());
        setRecipePrepTime(recipe.getPrepTime());
        Log.d("SAVED_INGREDIENTS", "instructions: " + recipe.getInstructions());
        if (recipe.getImage() == null) {
            Query query = new Query();
            query.addRecipeImageToRecipeDetails(recipe, this);
        }
        else {
            setRecipeImage(recipe.getImage());
        }
    }

    boolean isRecipeSaved(String recipeName) {
        RecipeDao recipeDao = RecipeDatabase.getInstance(this).getRecipeDao();
        List<RecipeEntity> recipes = recipeDao.findRecipeByName(recipeName);
        return recipes.size() == 0 ? false : true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_details);

        try {
            this.getSupportActionBar().hide();
        }
        catch (Exception e){ }

        mRecipeName = findViewById(R.id.recipe_details_name);
        mPrepTime = findViewById(R.id.recipe_details_prep_time);
        mServings = findViewById(R.id.recipe_details_servings);
        mRecipeImage = findViewById(R.id.recipe_details_image);
        mRecipeInstructions = findViewById(R.id.recipe_details_instructions);
        mRecipeInstructions.setMovementMethod(new ScrollingMovementMethod());
        mRecipeIngredients = findViewById(R.id.recipe_details_ingredients);
        mRecipeIngredients.setMovementMethod(new ScrollingMovementMethod());
        mSaveOrDeleteButton = findViewById(R.id.recipe_details_save);
        Intent intent = getIntent();
        try {
            recipe = intent.getExtras().getParcelable("recipe");
            Log.d("SAVED_INGREDIENTS", "recipe has been retrieved");
            setView(recipe);
            setRecipeButton setRecipe = new setRecipeButton(recipe.getName(), this);
            setRecipe.execute();
        }
        catch (Exception e) {
            Log.d("SAVED_INGREDIENTS", "recipe could not be retrieved: " + e.getMessage());
        }
    }

    private class setRecipeButton extends AsyncTask<Void, Void, Integer> {
        Activity context;
        String recipeName;

        public setRecipeButton(String recipeName, Activity context) {
            this.recipeName = recipeName;
            this.context = context;
        }

        @Override
        protected Integer doInBackground(Void... voids) {
            if (isRecipeSaved(recipe.getName())) {
                mSaveOrDeleteButton.setText("Remove Recipe");
                mSaveOrDeleteButton.setOnClickListener(mRemoveButtonListener);
                Log.d("SAVED_INGREDIENTS", "Recipe found");
            }
            else {
                mSaveOrDeleteButton.setText("Save Recipe");
                mSaveOrDeleteButton.setOnClickListener(mSaveButtonListener);
                Log.d("SAVED_INGREDIENTS", "Recipe not found");
            }
            return null;
        }
    }


    private class RemoveRecipesAsync extends AsyncTask<Void, Void, Integer> {

        Activity context;
        Recipe recipe;

        public RemoveRecipesAsync(Recipe recipe, Activity context) {
            this.recipe = recipe;
            this.context = context;
        }

        @Override
        protected Integer doInBackground(Void... voids) {
            RecipeDao recipeDao = RecipeDatabase.getInstance(context).getRecipeDao();
            try {
                RecipeEntity recipeEntity = recipeDao.findRecipeByName(recipe.getName()).get(0);
                recipeDao.delete(recipeEntity);
            }
            catch (Exception e) {

            }
            return null;
        }
    }

    private static class AddRecipesAsync extends AsyncTask<Void, Void, Integer> {

        Activity context;
        Recipe recipe;

        public AddRecipesAsync(Recipe recipe, Activity context) {
            this.context = context;
            this.recipe = recipe;
        }

        @Override
        protected Integer doInBackground(Void... voids) {
            recipe.saveRecipe(context);
            return null;
        }
    }
}
