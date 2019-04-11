package com.example.thomasstephenson.lazychef;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

/**
 * This is the interface where you are accessing the saved recipes. Use the methods to insert,
 * delete, update, and query the database.
 */
@Dao
public interface RecipeDao {
    @Insert
    void insert(RecipeEntity... recipes);

    @Update
    void update(RecipeEntity... recipes);

    @Delete
    void delete(RecipeEntity... recipes);


    @Query("SELECT * from RecipeEntity")
    List<RecipeEntity> getAllSavedRecipes();    //THIS IS HOW YOU GET ALL THE RECIPES THAT THE USER SAVED
}
