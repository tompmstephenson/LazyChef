package com.example.thomasstephenson.lazychef;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.TextView;

import java.util.List;

public class PantryActivity extends AppCompatActivity {

    SearchView mSearchView;
    static LinearLayout mIngredientListLayout;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_discover:
                    startActivity(new Intent(PantryActivity.this, MainActivity.class));
                    return true;
                case R.id.navigation_pantry:
                    startActivity(new Intent(PantryActivity.this, MainActivity.class));
                    return true;
                case R.id.navigation_settings:
                    startActivity(new Intent(PantryActivity.this, MainActivity.class));
                    return true;
            }
            return false;
        }

    };

    private void searchForIngredients(String ingredient) {
        Query query = new Query();
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
    }

    private static void addIngredientLayout(String ingredientName, String ingredientType, Activity activity) {
        View ingredientView = activity.getLayoutInflater().inflate(R.layout.ingredient_layout, null);
        ViewGroup ingredientViewGroup = (ViewGroup) ingredientView;
        TextView ingredientTitleView = (TextView) ingredientViewGroup.getChildAt(1);
        ingredientTitleView.setText(ingredientName);
        TextView typeView = (TextView) ingredientViewGroup.getChildAt(2);
        typeView.setText("Type: " + ingredientType);
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

}
