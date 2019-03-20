package com.example.thomasstephenson.lazychef;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.util.Log;
import android.view.MenuItem;
import android.widget.SearchView;

public class PantryActivity extends AppCompatActivity {

    SearchView mSearchView;

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

    private SearchView.OnQueryTextListener mQueryListener =  new SearchView.OnQueryTextListener() {
        @Override
        public boolean onQueryTextSubmit(String s) {
            String query = mSearchView.getQuery().toString();
            Log.i("SEARCH", query);
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
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.getMenu().findItem(R.id.navigation_pantry).setChecked(true);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}
