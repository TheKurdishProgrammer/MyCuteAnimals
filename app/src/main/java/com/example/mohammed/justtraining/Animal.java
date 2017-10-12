package com.example.mohammed.justtraining;

import android.graphics.drawable.Drawable;

/**
 * Created by mohammed on 01/10/17.
 */

public class Animal {


    public Drawable getDrawable() {
        return drawable;
    }

    public void setDrawable(Drawable drawable) {
        this.drawable = drawable;
    }

    private String name;
    private int age;
    private int weight;
    private String color;
    private String imageUri;
    private int animalIdImag;
    private Drawable drawable;

    final static String name2 = "Name:";
    final static String weight2 = "Weight:";
    final static String color2 = "Color:";

    public static String getName2() {
        return name2;
    }

    public static String getWeight2() {
        return weight2;
    }

    public static String getColor2() {
        return color2;
    }

    public static String getAge2() {
        return Age2;
    }

    final static String Age2 = "Age";


    public Animal(String name,int age,int weight,String color,String imageUri) {

       this.name = name;
       this.age = age;
       this.weight= weight;
       this.color =color;
       this.imageUri = imageUri;
   }
    public Animal(String name,int age,int weight,String color,Drawable drawable) {

        this.name = name;
        this.age = age;
        this.weight= weight;
        this.color =color;
        this.drawable = drawable;
    }



    public String getName() {
        return "Name:"+name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return "Age:"+age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getWeight() {
        return "Weight:"+weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getColor() {
        return "Color:"+color;
    }

    public void setColor(String color) {
        this.color = color;
    }


    public int getAnimalIdImage() {
        return animalIdImag;
    }

    public void setAnimalIdImage(int carIdImage) {
        this.animalIdImag = animalIdImag;
    }

    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }
}
