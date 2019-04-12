package com.example.thomasstephenson.lazychef;

import org.junit.Test;
import java.io.File;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import static org.junit.Assert.*;
/**
 * Created by Jack on 3/19/2019.
 * Local unit test for Ingredient object class, which will execute on the development machine (host).
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

public class IngredientUnitTest {
    //Tests for Ingredient object functionality
    //Testing name functionality, important especially for Pantry searching
    @Test
    public void test_IngredientNameParameter() throws Exception {
        String name = "Spaghetti";
        int amount = 1;
        String type = "Pasta";
        String unit = "lbs";
        int calories = 400;
        String imageURL = "www.example.com";
        Bitmap file = BitmapFactory.decodeFile("../../../../../../../photos/spaghetti.jpg");

        //Test name parameter methods and memory mgmt
        Ingredient spaghetti = new Ingredient(name,amount,type,unit,calories,imageURL,file);
        assertEquals(name,spaghetti.getName()); //Memory mgmt test
        spaghetti.setName("Pizza");
        assertNotEquals(name,spaghetti.getName()); //Ensures parameter can be properly set and updated
        assertEquals("Pizza",spaghetti.getName());

    }

    //Test Amount Parameter
    @Test
    public void test_IngredientAmountParameter() throws Exception {
        String name = "Spaghetti";
        int amount = 1;
        String type = "Pasta";
        String unit = "lbs";
        int calories = 400;
        String imageURL = "www.example.com";
        Bitmap file = BitmapFactory.decodeFile("../../../../../../../photos/spaghetti.jpg");

        //Test amount parameter methods and memory mgmt
        Ingredient spaghetti = new Ingredient(name,amount,type,unit,calories,imageURL,file);
        assertEquals(amount,spaghetti.getAmount());
        spaghetti.setAmount(5);
        assertNotEquals(amount,spaghetti.getAmount());
        assertEquals(5,spaghetti.getAmount());

    }

    @Test
    public void test_IngredientUnitParameter() throws Exception {
        String name = "Spaghetti";
        int amount = 1;
        String type = "Pasta";
        String unit = "lbs";
        int calories = 400;
        String imageURL = "www.example.com";
        Bitmap file = BitmapFactory.decodeFile("../../../../../../../photos/spaghetti.jpg");

        //Test unit parameter methods and memory mgmt
        Ingredient spaghetti = new Ingredient(name,amount,type,unit,calories,imageURL,file);
        assertEquals(unit,spaghetti.getUnit()); //Memory mgmt test
        spaghetti.setUnit("oz");
        assertNotEquals(unit,spaghetti.getUnit()); //Ensures parameter can be properly set and updated
        assertEquals("oz",spaghetti.getUnit());

    }

    @Test
    public void test_IngredientTypeParameter() throws Exception {
        String name = "Spaghetti";
        int amount = 1;
        String type = "Pasta";
        String unit = "lbs";
        int calories = 400;
        String imageURL = "www.example.com";
        Bitmap file = BitmapFactory.decodeFile("../../../../../../../photos/spaghetti.jpg");

        //Test type parameter methods and memory mgmt
        Ingredient spaghetti = new Ingredient(name,amount,type,unit,calories,imageURL,file);
        assertEquals(type,spaghetti.getType()); //Memory mgmt test
        spaghetti.setType("Meat");
        assertNotEquals(type,spaghetti.getType()); //Ensures parameter can be properly set and updated
        assertEquals("Meat",spaghetti.getType());

    }

    @Test
    public void test_IngredientCaloriesParameter() throws Exception {
        String name = "Spaghetti";
        int amount = 1;
        String type = "Pasta";
        String unit = "lbs";
        int calories = 400;
        String imageURL = "www.example.com";
        Bitmap file = BitmapFactory.decodeFile("../../../../../../../photos/spaghetti.jpg");

        //Test calorie parameter methods and memory mgmt
        Ingredient spaghetti = new Ingredient(name,amount,type,unit,calories,imageURL,file);
        assertEquals(calories,spaghetti.getCalories()); //Memory mgmt test
        spaghetti.setCalories(600);
        assertNotEquals(calories,spaghetti.getCalories()); //Ensures parameter can be properly set and updated
        assertEquals(600,spaghetti.getCalories());

    }

    @Test
    public void test_IngredientImageURLParameter() throws Exception {
        String name = "Spaghetti";
        int amount = 1;
        String type = "Pasta";
        String unit = "lbs";
        int calories = 400;
        String imageURL = "www.example.com";
        Bitmap file = BitmapFactory.decodeFile("../../../../../../../photos/spaghetti.jpg");

        //Test imageURL parameter methods and memory mgmt
        Ingredient spaghetti = new Ingredient(name,amount,type,unit,calories,imageURL,file);
        assertEquals(imageURL,spaghetti.getImageURL()); //Memory mgmt test
        spaghetti.setImageURL("www.anothersite.com");
        assertNotEquals(imageURL,spaghetti.getImageURL()); //Ensures parameter can be properly set and updated
        assertEquals("www.anothersite.com",spaghetti.getImageURL());

    }



}
