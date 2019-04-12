package com.example.thomasstephenson.lazychef;

import org.junit.Test;
import java.io.File;
import java.util.List;
import java.util.ArrayList;
import static org.junit.Assert.*;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Created by Jack on 3/19/2019.
 * Local unit test for Recipe object class, which will execute on the development machine (host).
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class RecipeUnitTest {

    @Test
    public void test_RecipeNameParameter() throws Exception {
        //Set up construction of a Recipe Object
        Bitmap file1 = BitmapFactory.decodeFile("../../../photos/spaghetti.jpg");
        Bitmap file2 = BitmapFactory.decodeFile("../../../../photos/meatballs.jpg");
        Ingredient spaghetti = new Ingredient("Spaghetti",1,"lbs","unknown",400,"www.example.com",file1);
        Ingredient meatballs = new Ingredient("Meatballs",5,"pieces","unknown",150,"www.example.com",file2);
        String name = "Spaghetti and Meatballs";
        List<Ingredient> list = new ArrayList<Ingredient>();
        list.add(spaghetti);
        list.add(meatballs);
        String instructions = "Example Instructions";
        int prepTime =30;
        int servings = 1;
        String imageURL = "www.example.com";
        Bitmap file3 = BitmapFactory.decodeFile("../../../../photos/spaghettiMeatballs.jpg");

        //Test Construction and Data mgmt
        Recipe recipe = new Recipe(name,list,instructions,prepTime,servings,imageURL,file3);

        assertEquals(name,recipe.getName());
        recipe.setName("Pizza");
        assertNotEquals(name,recipe.getName());
        assertEquals("Pizza",recipe.getName());
    }

    @Test
    public void test_RecipeIngredientListParameter() throws Exception {
        //Set up construction of a Recipe Object
        Bitmap file1 = BitmapFactory.decodeFile("../../../photos/spaghetti.jpg");
        Bitmap file2 = BitmapFactory.decodeFile("../../../../photos/meatballs.jpg");
        Ingredient spaghetti = new Ingredient("Spaghetti",1,"lbs","unknown",400,"www.example.com",file1);
        Ingredient meatballs = new Ingredient("Meatballs",5,"pieces","unknown",150,"www.example.com",file2);
        String name = "Spaghetti and Meatballs";
        List<Ingredient> list = new ArrayList<Ingredient>();
        list.add(spaghetti);
        list.add(meatballs);
        String instructions = "Example Instructions";
        int prepTime =30;
        int servings = 1;
        String imageURL = "www.example.com";
        Bitmap file3 = BitmapFactory.decodeFile("../../../../photos/spaghettiMeatballs.jpg");

        //Test Construction and Data mgmt
        Recipe recipe = new Recipe(name,list,instructions,prepTime,servings,imageURL,file3);

        assertEquals(list,recipe.getListIngredients());
        assertEquals(2,recipe.getListIngredients().size());
        list.add(meatballs);
        recipe.setListIngredients(list);
        assertEquals(list,recipe.getName());
        assertEquals(3,recipe.getListIngredients().size());
    }
}
