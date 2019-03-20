package com.example.thomasstephenson.lazychef;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
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
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    Button mSearchButton;
    static LinearLayout mRecipeListLayout;
    ArrayList <Ingredient> ingredients;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_discover:
                    return true;
                case R.id.navigation_pantry:
                    startActivity(new Intent(MainActivity.this, PantryActivity.class));
                    return true;
                case R.id.navigation_settings:
                    return true;
            }
            return false;
        }
    };

    private View.OnClickListener mSearchButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            searchForRecipes();
        }
    };

    private void searchForRecipes() {
        String[] ingredients = getIngredientNames();
        Query query = new Query();
        query.QueryRecipesHelper(ingredients, this);
    }

    public static void addBitmapImage(int index, Bitmap bitmap) {
        ViewGroup recipeViewGroup = (ViewGroup) mRecipeListLayout.getChildAt(index);
        if (recipeViewGroup != null) {
            ImageView imageView = (ImageView) recipeViewGroup.getChildAt(0);
            imageView.setImageBitmap(bitmap);
        }
    }

    public String[] getIngredientNames() {
        String [] ingredientNames = new String[ingredients.size()];
        for (int i = 0; i < ingredients.size(); i++) {
            ingredientNames[i] = ingredients.get(i).getName();
        }
        return ingredientNames;
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

        addRecipeLayout(recipeName, ingredientsDescript, activity);
    }

    private static void addRecipeLayout(String recipeName, String ingredients, Activity activity) {
        View recipeView = activity.getLayoutInflater().inflate(R.layout.recipe_layout, null);
        ViewGroup recipeViewGroup = (ViewGroup) recipeView;
        TextView recipeTitleView = (TextView) recipeViewGroup.getChildAt(1);
        recipeTitleView.setText(recipeName);
        TextView ingredientsView = (TextView) recipeViewGroup.getChildAt(2);
        ingredientsView.setText(ingredients);
        mRecipeListLayout.addView(recipeView);

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            this.getSupportActionBar().hide();
        }
        catch (NullPointerException e){ }
        setContentView(R.layout.activity_main);

        Log.d("SAVED_INGREDIENTS", "STARTING MainActivity");
        Intent intent = getIntent();
        if (intent == null || intent.getExtras() == null) {
            ingredients = new ArrayList<Ingredient>();
            Log.d("SAVED_INGREDIENTS", "No Ingredients Saved");
        }
        else {
            ingredients = intent.getExtras().getParcelableArrayList("ingredients");
            String[] names = getIngredientNames();
            for (int i = 0; i < names.length; i++)
                Log.d("SAVED_INGREDIENTS", names[i]);
        }


        mRecipeListLayout = findViewById(R.id.recipe_list);
        mSearchButton = findViewById(R.id.searchButton);
        mSearchButton.setOnClickListener(mSearchButtonListener);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.getMenu().findItem(R.id.navigation_discover).setChecked(true);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

    }
}
