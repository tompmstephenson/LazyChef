package com.example.thomasstephenson.lazychef;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.AsyncTask;
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

    public static Bitmap getBitMap(String url) {
        try {
            InputStream in = new java.net.URL(url).openStream();
            Bitmap bitmap = BitmapFactory.decodeStream(in);//BitmapFactory.decodeStream(in, null, null);
            in.close();
            return bitmap;
        }
        catch (Exception e) {
            //Log.d("ERROR", e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public static void createRecipeView(Recipe recipe, Activity activity) {
        //Log.d("RECIPE", recipe.getInstructions());

        //activity.setContentView(mRecipeListLayout);
        Log.d("UPDATE", "UPDATED VIEWS");
        String imageURL = recipe.getImageURL();
        if (imageURL == null) {
            Log.d("ERROR", "IMAGE URL is null");
            return;
        }
        Bitmap bitmap = getBitMap(imageURL);
        addRelativeLayout(recipe.getName(), bitmap, activity);
    }

    private static void addRelativeLayout(String recipeName, Bitmap bitmap, Activity activity) {
        View recipeView = activity.getLayoutInflater().inflate(R.layout.recipe_layout, null);
        ViewGroup recipeViewGroup = (ViewGroup) recipeView;
        ImageView imageView =  (ImageView) recipeViewGroup.getChildAt(0);
        imageView.setImageBitmap(bitmap);
        TextView recipeTitleView = (TextView) recipeViewGroup.getChildAt(1);
        if (recipeName != null) {
            if (recipeName.length() > 25)
                recipeName = recipeName.substring(0, 22) + "...";
            recipeTitleView.setText(recipeName);
            mRecipeListLayout.addView(recipeView);
        }
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
