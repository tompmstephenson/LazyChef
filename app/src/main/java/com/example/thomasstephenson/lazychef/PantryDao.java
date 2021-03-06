package com.example.thomasstephenson.lazychef;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface PantryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Ingredient ingredient);

    @Update
    void update(Ingredient... ingredients);

    @Delete
    void delete(Ingredient... ingredients);

    @Query("SELECT * from Ingredient")
    List<Ingredient> getSavedPantry();
}
