package business;

import java.awt.*;

public class BaseProduct extends MenuItem {
    private String title;
    private float rating;
    private int calories;
    private int protein;
    private int fat;
    private int sodium;
    private int price;

    public BaseProduct(String title, float rating, int calories, int protein, int fat, int sodium, int price) {
        super();
        this.title = title;
        this.rating = rating;
        this.price = price;
        this.fat = fat;
        this.protein = protein;
        this.calories = calories;
        this.sodium = sodium;
    }
    public BaseProduct() {
        super();
    }


    @Override
    public int computePrice() {
        return this.price;
    }
    public float getRating() {
        return this.rating;
    }
    public int getCalories() {
        return this.calories;
    }

    @Override
    public String getTitle() {
        return title;
    }


    @Override
    public String toString(){
        return this.title + "  \nrating: " + rating  +  "  \npret: " + price;
    }

    public void setRating(int r) {
        this.rating = r;
    }

    public void setCal(int c) {
        calories = c;
    }

    public void setProt(int p) {
        protein = p;
    }

    public void setFat(int f) {
        fat = f;
    }

    public void setSodium(int s) {
        sodium = s;
    }

    public void setPrice(int p) {
        price = p;
    }
}
