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

public class RecipeDetailsActivity extends AppCompatActivity {

    TextView mRecipeName;
    TextView mRecipeInstructions;
    TextView mPrepTime;
    TextView mServings;
    static ImageView mRecipeImage;
    Button mSaveButton;
    Recipe recipe;

    private View.OnClickListener mSaveButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            RecipesAsync async = new RecipesAsync(RecipeDetailsActivity.this, recipe);
            async.execute();
        }
    };

    public void setRecipeName(String name) {
        mRecipeName.setText(name);
    }

    public void setRecipeInstructions(String instructions) {
        mRecipeInstructions.setText(instructions);
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
        mSaveButton = findViewById(R.id.recipe_details_save);
        mSaveButton.setOnClickListener(mSaveButtonListener);

        Intent intent = getIntent();
        try {
            recipe = intent.getExtras().getParcelable("recipe");
            Log.d("SAVED_INGREDIENTS", "recipe has been retrieved");
            setView(recipe);
        }
        catch (Exception e) {
            Log.d("SAVED_INGREDIENTS", "recipe could not be retrieved: " + e.getMessage());
        }
    }

    private static class RecipesAsync extends AsyncTask<Void, Void, Integer> {

        Activity context;
        Recipe recipe;

        public RecipesAsync(Activity context, Recipe recipe) {
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
