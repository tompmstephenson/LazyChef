package com.example.thomasstephenson.lazychef;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;
/**
 * Created by Jack on 3/19/2019.
 * Local unit test for Pantry object class, which will execute on the development machine (host).
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

public class PantryUnitTest {

    @Test
    public void test_PantrySearch() {
        Pantry pantry = new Pantry();

        Ingredient spaghetti = new Ingredient("Spaghetti",1,"Pasta","lbs",400);
        Ingredient meatballs = new Ingredient("Meatballs",5,"Meat","pieces",150);
        Ingredient chicken = new Ingredient("Chicken",10,"Meat","oz",200);

        ArrayList<Ingredient> list = new ArrayList<Ingredient>();
        list.add(spaghetti);
        list.add(meatballs);
        list.add(chicken);
        pantry.addIngredients(list);

        //Tests Results of Search Query in hard-coded pantry
        assertTrue(pantry.checkIngredient("Spaghetti"));
        assertTrue(pantry.checkIngredient("Meatballs"));
        assertTrue(pantry.checkIngredient("Chicken"));
        assertFalse(pantry.checkIngredient("Salt"));
        assertFalse(pantry.checkIngredient("Butter"));

    }

    @Test
    public void test_PantryAdd() {
        Pantry pantry = new Pantry();

        Ingredient spaghetti = new Ingredient("Spaghetti",1,"Pasta","lbs",400);
        Ingredient meatballs = new Ingredient("Meatballs",5,"Meat","pieces",150);
        Ingredient chicken = new Ingredient("Chicken",10,"Meat","oz",200);
        Ingredient butter = new Ingredient("Butter",7,"Dairy","Stick",1000);
        Ingredient salt = new Ingredient("Salt",5,"Spice","tblspn",25);

        ArrayList<Ingredient> list = new ArrayList<Ingredient>();
        list.add(spaghetti);
        list.add(meatballs);
        list.add(chicken);
        list.add(butter);
        list.add(salt);
        pantry.addIngredients(list);

        //Tests that list data added to pantry is stored properly
        assertEquals(list,pantry.getIngredientList());

    }

    @Test
    public void test_PantryRemove() {
        Pantry pantry = new Pantry();

        Ingredient spaghetti = new Ingredient("Spaghetti",1,"Pasta","lbs",400);
        Ingredient meatballs = new Ingredient("Meatballs",5,"Meat","pieces",150);
        Ingredient chicken = new Ingredient("Chicken",10,"Meat","oz",200);
        Ingredient butter = new Ingredient("Butter",7,"Dairy","Stick",1000);
        Ingredient salt = new Ingredient("Salt",5,"Spice","tblspn",25);

        ArrayList<Ingredient> list = new ArrayList<Ingredient>();
        list.add(spaghetti);
        list.add(meatballs);
        list.add(chicken);
        list.add(butter);
        list.add(salt);
        pantry.addIngredients(list);

        //Tests that removed data is no longer in pantry object
        assertTrue(pantry.checkIngredient("Chicken"));
        pantry.removeIngredient("Chicken");
        assertFalse(pantry.checkIngredient("Chicken"));

        assertTrue(pantry.checkIngredient("Spaghetti"));
        pantry.removeIngredient("Spaghetti");
        assertFalse(pantry.checkIngredient("Spaghetti"));

        assertTrue(pantry.checkIngredient("Salt"));
        pantry.removeIngredient("Salt");
        assertFalse(pantry.checkIngredient("Salt"));
    }

    @Test
    public void test_PantryClear() {
        Pantry pantry = new Pantry();

        Ingredient spaghetti = new Ingredient("Spaghetti",1,"Pasta","lbs",400);
        Ingredient meatballs = new Ingredient("Meatballs",5,"Meat","pieces",150);

        ArrayList<Ingredient> fullList = new ArrayList<Ingredient>();
        ArrayList<Ingredient> emptyList = new ArrayList<Ingredient>();
        fullList.add(spaghetti);
        fullList.add(meatballs);
        pantry.addIngredients(fullList);

        //Tests that all data is deleted from the pantry object in memory
        assertNotEquals(emptyList,pantry.getIngredientList());
        pantry.clearPantry();
        assertEquals(emptyList,pantry.getIngredientList());
    }

}
