package com.example.thomasstephenson.lazychef;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {Ingredient.class}, version = 1, exportSchema = false)
public abstract class PantryDatabase extends RoomDatabase {
    public static final String DB_NAME = "pantryDatabase.db";
    private static volatile PantryDatabase instance;

    static synchronized PantryDatabase getInstance(Context context) {
        if(instance == null) {
            instance = create(context);
        }
        return instance;
    }

    private static PantryDatabase create(final Context context) {
        return Room.databaseBuilder(
                context,
                PantryDatabase.class,
                DB_NAME).build();
    }

    public abstract PantryDao getPantryDao();
}
