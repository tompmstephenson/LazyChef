package com.example.thomasstephenson.lazychef;

import org.junit.Test;
import java.io.File;

import static org.junit.Assert.*;
/**
 * Created by Jack on 3/19/2019.
 * Local unit test for Ingredient object class, which will execute on the development machine (host).
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class IngredientUnitTest {

    @Test
    public void test_ObjectFunctionality() throws Exception {
        String name = "Spaghetti";
        int amount = 1;
        String unit = "lbs";
        int calories = 400;
        String imageURL = "www.example.com";
        File file = new File("../../../../photos/spaghetti.jpg");

        //Test construction and memory mgmt
        Ingredient spaghetti = new Ingredient(name,amount,unit,calories,imageURL,file);
        assertEquals(name,spaghetti.getName());
        assertEquals(amount,spaghetti.getAmount());
        assertEquals(unit,spaghetti.getUnit());
        assertEquals(calories,spaghetti.getCalories());
        assertEquals(imageURL,spaghetti.getImageURL());
        assertEquals(file,spaghetti.getImage());

        File file2 = new File("../../../../photos/meatballs.jpg");
        Ingredient meatballs = new Ingredient("Meatballs",5,"pieces",150,"www.example.com",file2);

        //Test Equals Override
        assertEquals(spaghetti,spaghetti);
        assertNotEquals(spaghetti,meatballs);

    }

}
