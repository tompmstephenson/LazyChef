package com.example.thomasstephenson.lazychef;

import org.junit.Test;
import static org.junit.Assert.*;
/**
 * Created by Jack on 3/19/2019.
 * Local unit test for Recipe object class, which will execute on the development machine (host).
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

public class QueryUnitTest {

    @Test
    public void test_AddToQueryIngredientsMethod() {
        Query query = new Query();
        String[] preNames = query.getIngNames();
        query.AddToQueryIngredients("Apples");
        String[] postNames = query.getIngNames();

        assertNotEquals(preNames.length,postNames.length);

        String apples = postNames[postNames.length-1];
        assertEquals(apples,"Apples");
    }

    @Test
    public void test_DeleteFromQueryIngredientsMethod() {
        Query query = new Query();
        String[] names = query.getIngNames();
        query.AddToQueryIngredients("Apples");
        query.DeleteFromQueryIngredients("Apples");
        for(String i:names){
            assertNotEquals(i,"Apples");
        }
    }
}
