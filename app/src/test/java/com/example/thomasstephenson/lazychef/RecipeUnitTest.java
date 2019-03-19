package com.example.thomasstephenson.lazychef;

import org.junit.Test;
import java.io.File;
import java.util.List;
import java.util.ArrayList;
import static org.junit.Assert.*;
/**
 * Created by Jack on 3/19/2019.
 * Local unit test for Recipe object class, which will execute on the development machine (host).
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class RecipeUnitTest {
    @Test
    public void test_ObjectFunctionality() throws Exception {

        //Set up construction of a Recipe Object
        File file1 = new File("../../../../photos/spaghetti.jpg");
        File file2 = new File("../../../../photos/meatballs.jpg");

        Ingredient spaghetti = new Ingredient("Spaghetti",1,"lbs",400,"www.example.com",file1);
        Ingredient meatballs = new Ingredient("Meatballs",5,"pieces",150,"www.example.com",file2);
        String name = "Spaghetti and Meatballs";
        List<Ingredient> list = new ArrayList<Ingredient>();
        list.add(spaghetti);
        list.add(meatballs);
        String instructions = "Example Instructions";
        int prepTime =30;
        int servings = 1;
        String imageURL = "www.example.com";
        File file3 = new File("../../../../photos/spaghettiMeatballs.jpg");

        //Test Construction and Data mgmt
        Recipe recipe = new Recipe(name,list,instructions,prepTime,servings,imageURL,file3);

        assertEquals(name,recipe.getName());
        assertEquals(list,recipe.getListIngredients());
        assertEquals(instructions,recipe.getInstructions());
        assertEquals(prepTime,recipe.getPrepTime());
        assertEquals(servings,recipe.getServings());
        assertEquals(imageURL,recipe.getImageURL());
        assertEquals(file3,recipe.getImage());

        //Test Recipe Calories Counter
        assertEquals(1150,recipe.getEstimatedCalories());

        //Test Recipe equals method (also tests ingredient search function)
        Recipe recipe1 = new Recipe("Not spaghetti and meatballs",list,instructions,prepTime,servings,imageURL,file3);
        assertEquals(recipe,recipe);
        assertNotEquals(recipe,recipe1);
    }
}
