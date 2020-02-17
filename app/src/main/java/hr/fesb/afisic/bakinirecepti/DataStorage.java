package hr.fesb.afisic.bakinirecepti;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class DataStorage {
    public static HashMap<Integer, Recipe> recipeHashMap = new HashMap<Integer, Recipe>();

    public static void fillData(int i, JSONObject oneObject) {

        try {
            int ID = oneObject.getInt("id");
            String title = oneObject.getString("title");
            String imageURL = oneObject.getString("image");
            int servings = oneObject.getInt("servings");
            int readyInMinutes = oneObject.getInt("readyInMinutes");
            String sourceURL = oneObject.getString("sourceUrl");


            JSONArray jArray = oneObject.getJSONArray("missedIngredients");

            HashMap<Integer, Ingredients> recipeIngHashMap = new HashMap<Integer, Ingredients>();

            Ingredients ingredientsObj = null;

            for (int j=0; j < jArray.length(); j++)
            {
                String name = null;
                String unit = null;
                Double amount = null;
                try {
                    JSONObject ingredientsObject = jArray.getJSONObject(j);

                    name = ingredientsObject.getString("name");
                    amount = ingredientsObject.getDouble("amount");
                    unit = ingredientsObject.getString("unitShort");
                } catch (JSONException e) {
                    Log.e("ANA", e.getMessage());
                    e.printStackTrace();
                }
                ingredientsObj = new Ingredients(name, amount, unit);
                recipeIngHashMap.put(j, ingredientsObj);
            }

            Recipe aRecipe = new Recipe(ID, title, imageURL, servings, readyInMinutes,null, recipeIngHashMap, sourceURL);
            recipeHashMap.put(i, aRecipe);

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}