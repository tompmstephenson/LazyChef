package com.example.thomasstephenson.lazychef;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Parcel;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

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
            
        }
    };

    public void setRecipeName(String name) {
        mRecipeName.setText(name);
    }

    public void setRecipeInstructions(String instructions) {
        mRecipeInstructions.setText(instructions);
    }

    public void setRecipeServings(int servings) {
        mRecipeInstructions.setText("Servings: " + servings);
    }

    public void setRecipePrepTime(int prepTime) {
        mRecipeInstructions.setText("Prep Time: " + prepTime + " minutes");
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
}
