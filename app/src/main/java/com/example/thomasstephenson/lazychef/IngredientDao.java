package com.example.thomasstephenson.lazychef;

import java.util.List;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

/**
 * This is the interface where you are accessing the saved ingredients. Use the methods to insert,
 * delete, update, and query the database.
 */
@Dao
public interface IngredientDao {
    @Insert
    void insert(IngredientEntity ingredient);

    @Update
    void update(IngredientEntity... ingredients);

    @Delete
    void delete(IngredientEntity... ingredients);

    @Query("SELECT * from IngredientEntity")
    List<IngredientEntity> getAllIngredients();


    /*THIS IS HOW YOU GET ALL THE INGREDIENTS FOR A CERTAIN SAVED RECIPE.
    In the parameter you are usually going to want to call RecipeEntity.id if you have one
    readily available. I'm not sure how you wouldn't.
     */
    @Query("SELECT * from IngredientEntity where recipeId=:recipeId")
    List<IngredientEntity> findIngredientsForRecipe(final int recipeId);
}
