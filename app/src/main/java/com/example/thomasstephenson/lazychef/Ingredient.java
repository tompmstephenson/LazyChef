package com.example.thomasstephenson.lazychef;
import android.graphics.Bitmap;

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
    private String type;
    private int calories;
    private String imageURL;
    private Bitmap image;

    public Ingredient(String name, int amount, String type, String unit, int cal, String imageURL, Bitmap image){
        this.name = name;
        this.amount = amount;
        this.type = type;
        this.unit = unit;
        this.calories = cal;
        this.imageURL = imageURL;
        this.image = image;
    }
    public Ingredient(String name, int amount, String type, String unit, int cal){ //Constructor if image unavailable
        this.name = name;
        this.amount = amount;
        this.type = type;
        this.unit = unit;
        this.calories = cal;;
    }

    public String getName() { return name; }
    public void setName(String ingName) { name = ingName; }

    public int getAmount() { return amount; }
    public void setAmount(int ingAmount) { amount = ingAmount; }

    public String getType() { return type; }
    public void setType(String ingType) { type = ingType; }

    public String getUnit() { return unit; }
    public void setUnit(String ingUnit) { name = ingUnit; }

    public int getCalories() { return calories; }
    public void setCalories(int ingCal) { calories = ingCal; }

    public String getImageURL() { return imageURL; }
    public void setImageURL(String url) { imageURL = url; }

    public Bitmap getImage() { return image; }
    public void setImage(Bitmap img) { image = img; }

}
