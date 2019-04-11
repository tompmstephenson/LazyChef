package com.example.thomasstephenson.lazychef;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

/**
 * This is the database where the recipes are stored. Use getInstance().getRecipeDao() to get an
 * object that allows you to update, query, insert, and delete from the database.
 */
@Database(entities = {RecipeEntity.class}, version = 1)
public abstract class RecipeDatabase extends RoomDatabase {

    public static final String DB_NAME = "recipeDatabase.db";
    private static volatile RecipeDatabase instance;

    static synchronized RecipeDatabase getInstance(Context context) {
        if(instance == null) {
            instance = create(context);
        }
        return instance;
    }

    private static RecipeDatabase create(final Context context) {
        return Room.databaseBuilder(
                context,
                RecipeDatabase.class,
                DB_NAME).build();
    }

    public abstract RecipeDao getRecipeDao();
}
