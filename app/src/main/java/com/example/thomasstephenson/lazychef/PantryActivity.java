package com.example.thomasstephenson.lazychef;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class PantryActivity extends AppCompatActivity {

    SearchView mSearchView;
    static LinearLayout mIngredientListLayout;
    static ArrayList <Button> pantryButtons;
    static ArrayList <Ingredient> savedIngredients;
    static ArrayList <Ingredient> ingredientsResults;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_discover:
                    Bundle bundle = new Bundle();
                    bundle.putParcelableArrayList("ingredients", savedIngredients);
                    Intent intent = new Intent(PantryActivity.this, MainActivity.class);
                    intent.putExtras(bundle);
                    startActivity(intent);
                    return true;
                case R.id.navigation_pantry:
                    return true;
                case R.id.navigation_settings:
                    return true;
            }
            return false;
        }

    };

    private static View.OnClickListener mButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Button pantryButton = (Button) view;
            int index = pantryButtons.indexOf(pantryButton);
            if (index < 0)
            {
                Log.d("SAVED_INGREDIENTS", "Button could not be found: " + pantryButton.getText());
            }
            else {
                Ingredient ingredient = ingredientsResults.get(index);
                Log.d("SAVED_INGREDIENTS", "Saved Ingredient: " + ingredient.getName());
                savedIngredients.add(ingredient);
            }
        }
    };

    private void searchForIngredients(String ingredient) {
        Query query = new Query();
        ingredientsResults = new ArrayList<Ingredient>();
        query.queryIngredients(ingredient, this);
    }

    public static void addBitmapImage(int index, Bitmap bitmap) {
        ViewGroup ingredientViewGroup = (ViewGroup) mIngredientListLayout.getChildAt(index);
        if (ingredientViewGroup != null) {
            ImageView imageView = (ImageView) ingredientViewGroup.getChildAt(0);
            imageView.setImageBitmap(bitmap);
        }
    }

    public static void createIngredientView(Ingredient ingredient, Activity activity) {
        String ingredientName = ingredient.getName();
        String ingredientType = ingredient.getType();
        addIngredientLayout(ingredientName, ingredientType, activity);
        ingredientsResults.add(ingredient);
    }

    private static void addIngredientLayout(String ingredientName, String ingredientType, Activity activity) {
        View ingredientView = activity.getLayoutInflater().inflate(R.layout.ingredient_layout, null);
        ViewGroup ingredientViewGroup = (ViewGroup) ingredientView;
        TextView ingredientTitleView = (TextView) ingredientViewGroup.getChildAt(1);
        ingredientTitleView.setText(ingredientName);
        TextView typeView = (TextView) ingredientViewGroup.getChildAt(2);
        typeView.setText("Type: " + ingredientType);
        Button pantryButton = (Button) ingredientViewGroup.getChildAt(3);
        pantryButtons.add(pantryButton);
        pantryButton.setOnClickListener(mButtonListener);
        mIngredientListLayout.addView(ingredientView);
    }

    private SearchView.OnQueryTextListener mQueryListener =  new SearchView.OnQueryTextListener() {
        @Override
        public boolean onQueryTextSubmit(String s) {
            String query = mSearchView.getQuery().toString();
            searchForIngredients(query);
            return false;
        }

        @Override
        public boolean onQueryTextChange(String s) {
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ingredientsResults = new ArrayList<Ingredient>();
        pantryButtons = new ArrayList<Button>();
        Log.d("SAVED_INGREDIENTS", "STARTING PantryActivity");
        if (savedInstanceState == null) {
            savedIngredients = new ArrayList<Ingredient>();
            Log.d("SAVED_INGREDIENTS", "No Ingredients Saved");
        }
        else {
            savedIngredients = savedInstanceState.getParcelableArrayList("ingredients");
            for (int i = 0; i < savedIngredients.size(); i++)
                Log.d("SAVED_INGREDIENTS", savedIngredients.get(i).getName());
        }

        try {
            this.getSupportActionBar().hide();
        }
        catch (NullPointerException e){ }

        setContentView(R.layout.activity_pantry);
        mSearchView = findViewById(R.id.search);
        mSearchView.setOnQueryTextListener(mQueryListener);
        mIngredientListLayout = findViewById(R.id.ingredients_list);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.getMenu().findItem(R.id.navigation_pantry).setChecked(true);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList("ingredients", savedIngredients);
    }

}
