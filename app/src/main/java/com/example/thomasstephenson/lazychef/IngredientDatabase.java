package com.example.thomasstephenson.lazychef;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

/**
 * This is the database where the ingredients are stored. Use getInstance().getIngredientDao() to get an
 * object that allows you to update, query, insert, and delete from the database.
 */

@Database(entities = {IngredientEntity.class, RecipeEntity.class},
            version = 1)
public abstract class IngredientDatabase extends RoomDatabase {

    private static final String DB_NAME = "ingredientDatabase.db";
    private static volatile IngredientDatabase instance;

    static synchronized IngredientDatabase getInstance(Context context) {
        if (instance == null) {
            instance = create(context);
        }
        return instance;
    }

    private static IngredientDatabase create(final Context context) {
        return Room.databaseBuilder(
                context,
                IngredientDatabase.class,
                DB_NAME
        ).build();
    }

    public abstract IngredientDao getIngredientDao();
    public abstract RecipeDao getRecipeDao();
}
