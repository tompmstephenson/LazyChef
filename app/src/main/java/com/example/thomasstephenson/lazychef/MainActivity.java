package com.example.thomasstephenson.lazychef;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.Image;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.IntegerRes;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.TextView;

import java.io.InputStream;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

import static java.lang.System.in;

public class MainActivity extends AppCompatActivity {


    Button mSearchButton;
    static LinearLayout mRecipeListLayout;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_discover:
                    startActivity(new Intent(MainActivity.this, PantryActivity.class));
                    return true;
                case R.id.navigation_pantry:
                    startActivity(new Intent(MainActivity.this, PantryActivity.class));
                    return true;
                case R.id.navigation_settings:
                    startActivity(new Intent(MainActivity.this, PantryActivity.class));
                    return true;
            }
            return false;
        }
    };

    private View.OnClickListener mSearchButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            performSearch();
        }
    };

    private void performSearch() {
        String[] ingredients = new String[]{"Eggs", "Cheese"};
        Query query = new Query();
        query.QueryIngredientsHelper(ingredients, this);
    }

    public static void addBitmapImage(int index, Bitmap bitmap) {
        ViewGroup recipeViewGroup = (ViewGroup) mRecipeListLayout.getChildAt(index);
        if (recipeViewGroup != null) {
            ImageView imageView = (ImageView) recipeViewGroup.getChildAt(0);
            imageView.setImageBitmap(bitmap);
        }
    }

    public static void createRecipeView(Recipe recipe, Activity activity) {
        String recipeName = recipe.getName();
        if (recipeName.length() > 45)
            recipeName = recipeName.substring(0, 42) + "...";
        List <Ingredient> ingredients = recipe.getListIngredients();
        StringBuilder ingredientsStrBuilder = new StringBuilder();
        ingredientsStrBuilder.append("Ingredients: ");
        for (int i = 0; i < ingredients.size(); i++) {
            if (i == ingredients.size() - 1)
                ingredientsStrBuilder.append(ingredients.get(i).getName());
            else
                ingredientsStrBuilder.append(ingredients.get(i).getName() + ", ");
        }
        String ingredientsDescript = ingredientsStrBuilder.toString();
        if (ingredientsDescript.length() > 60)
            ingredientsDescript = ingredientsDescript.substring(0, 57) + "...";

        Log.d("CREATING RECIPE VIEW", recipeName);
        addRelativeLayout(recipeName, ingredientsDescript, activity);
        Log.d("CREATING RECIPE VIEW", recipeName + " view has been created");
    }

    private static void addRelativeLayout(String recipeName, String ingredients, Activity activity) {
        View recipeView = activity.getLayoutInflater().inflate(R.layout.recipe_layout, null);
        ViewGroup recipeViewGroup = (ViewGroup) recipeView;
        TextView recipeTitleView = (TextView) recipeViewGroup.getChildAt(1);
        recipeTitleView.setText(recipeName);
        mRecipeListLayout.addView(recipeView);
        TextView ingredientsView = (TextView) recipeViewGroup.getChildAt(2);
        ingredientsView.setText(ingredients);

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            this.getSupportActionBar().hide();
        }
        catch (NullPointerException e){ }
        setContentView(R.layout.activity_main);
        mRecipeListLayout = findViewById(R.id.recipe_list);
        mSearchButton = findViewById(R.id.searchButton);
        mSearchButton.setOnClickListener(mSearchButtonListener);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.getMenu().findItem(R.id.navigation_discover).setChecked(true);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

    }

}
