package com.example.thomasstephenson.lazychef;
import java.io.File;
/**
 * Created by Jack on 3/7/2019.
 */

/*
 * Updated by Tom on 3/14/2019. Removed calories per ingredient in one constructor,
 * as that would require an extra API call in Query.
 */

public class Ingredient {

    private String name;
    private int amount;
    private String unit;
    private int calories;
    private String imageURL;
    private File image;

    public Ingredient(String ingName,int ingAmount,String ingUnit,int ingCal,String url,File img){
        name = ingName;
        amount = ingAmount;
        unit = ingUnit;
        calories = ingCal;
        imageURL = url;
        image = img;
    }
    public Ingredient(String ingName,int ingAmount,String ingUnit,int ingCal){ //Constructor if image unavailable
        name = ingName;
        amount = ingAmount;
        unit = ingUnit;
        calories = ingCal;
    }

    public String getName() { return name; }
    public void setName(String ingName) { name = ingName; }

    public int getAmount() { return amount; }
    public void setAmount(int ingAmount) { amount = ingAmount; }

    public String getUnit() { return unit; }
    public void setUnit(String ingUnit) { name = ingUnit; }

    public int getCalories() { return calories; }
    public void setCalories(int ingCal) { calories = ingCal; }

    public String getImageURL() { return imageURL; }
    public void setImageURL(String url) { imageURL = url; }

    public File getImage() { return image; }
    public void setImage(File img) { image = img; }

    @Override
    public boolean equals(Object o){
        if(o == this)
            return true;

        if(!(o instanceof Ingredient))
            return false;

        Ingredient ing = (Ingredient)o;
        return name.equals(ing.getName()) && amount==ing.getAmount() && unit.equals(ing.getUnit()) && calories==ing.getCalories();
    }

}
