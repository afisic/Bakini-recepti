package hr.fesb.afisic.bakinirecepti;

import java.io.Serializable;
import java.util.HashMap;

public class Recipe implements Serializable {

    private String sourceURL;
    private int ID;
    private String title;
    private int servings;
    private int readyInMinutes;
    private String description;
    private String imageURL;
    private String name;
    private int amount;
    private String unit;
    public HashMap<Integer, Ingredients> recipeIngHashMap;


    public Recipe(int ID, String title, String imageURL, int servings, int readyInMinutes, String description, HashMap recipeIngredients, String sourceURL){
        this.ID = ID;
        this.title = title;
        this.servings = servings;
        this. readyInMinutes = readyInMinutes;
        this.description = description;
        this.imageURL = imageURL;
        this.recipeIngHashMap = recipeIngredients;
        this.sourceURL = sourceURL;
    }
    public Recipe(String title){
        this.title = title;
    };

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getReadyInMinutes() {
        return readyInMinutes;
    }

    public void setReadyInMinutes(int readyInMinutes) {
        this.readyInMinutes = readyInMinutes;
    }

    public int getServings() {
        return servings;
    }

    public void setServings(int servings) {
        this.servings = servings;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getSourceURL() {
        return sourceURL;
    }
}
