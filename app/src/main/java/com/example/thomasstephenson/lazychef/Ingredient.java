package com.example.thomasstephenson.lazychef;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.File;
/**
 * Created by Jack on 3/7/2019.
 */

/*
 * Updated by Tom on 3/14/2019. Removed calories per ingredient in one constructor,
 * as that would require an extra API call in Query.
 */

@Entity(indices = @Index(value = "name", unique = true))
public class Ingredient implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    public int id;

    private String name;
    private int amount;
    private String unit;
    private String type;
    private int calories;
    private String imageURL;
    @Ignore
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
    public Ingredient(String name, int amount, String type, String unit, int calories){ //Constructor if image unavailable
        this.name = name;
        this.amount = amount;
        this.type = type;
        this.unit = unit;
        this.calories = calories;;
    }

    protected Ingredient(Parcel in) {
        name = in.readString();
        amount = in.readInt();
        unit = in.readString();
        type = in.readString();
        calories = in.readInt();
        imageURL = in.readString();
    }

    public static final Creator<Ingredient> CREATOR = new Creator<Ingredient>() {
        @Override
        public Ingredient createFromParcel(Parcel in) {
            return new Ingredient(in);
        }

        @Override
        public Ingredient[] newArray(int size) {
            return new Ingredient[size];
        }
    };

    public String getName() { return name; }
    public void setName(String ingName) { name = ingName; }

    public int getAmount() { return amount; }
    public void setAmount(int ingAmount) { amount = ingAmount; }

    public String getType() { return type; }
    public void setType(String ingType) { type = ingType; }

    public String getUnit() { return unit; }
    public void setUnit(String ingUnit) { unit = ingUnit; }

    public int getCalories() { return calories; }
    public void setCalories(int ingCal) { calories = ingCal; }

    public String getImageURL() { return imageURL; }
    public void setImageURL(String url) { imageURL = url; }

    public Bitmap getImage() { return image; }
    public void setImage(Bitmap img) { image = img; }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeInt(amount);
        parcel.writeString(type);
        parcel.writeString(unit);
        parcel.writeInt(calories);
        parcel.writeString(imageURL);
    }

    public String toString() {
        return "" + amount + " " + unit + " of " + name;
    }
}
