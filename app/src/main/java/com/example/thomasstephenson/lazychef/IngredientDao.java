package com.example.thomasstephenson.lazychef;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

/**
 * This is the interface where you are accessing the saved ingredients. Use the methods to insert,
 * delete, update, and query the database.
 */
@Dao
public interface IngredientDao {
    @Insert
    void insert(IngredientEntity ingredient);

    @Update
    void Update(IngredientEntity... ingredients);

    @Delete
    void Delete(IngredientEntity... ingredients);

    @Query("SELECT * from IngredientEntity")
    List<Ingredient> getAllIngredients();


    /*THIS IS HOW YOU GET ALL THE INGREDIENTS FOR A CERTAIN SAVED RECIPE.
    In the parameter you are usually going to want to call RecipeEntity.id if you have one
    readily available. I'm not sure how you wouldn't.
     */
    @Query("SELECT * from IngredientEntity where recipeId=:recipeId")
    List<Ingredient> findIngredientsForRecipe(final int recipeId);
}
