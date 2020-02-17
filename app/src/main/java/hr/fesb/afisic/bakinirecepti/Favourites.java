package hr.fesb.afisic.bakinirecepti;

import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.HashMap;

public class Favourites extends AppCompatActivity {

    static ListView shoppingList;


    ShoppingListDataSource myDb;
    public static HashMap<Integer, Ingredients> rec;
    HashMap<Integer, Recipe> recipeHashMap = new HashMap<Integer, Recipe>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.favourites);
        shoppingList = (ListView) findViewById(R.id.shopListFav);

        myDb = new ShoppingListDataSource(getApplicationContext());
        myDb.read();


        rec = myDb.getAllRecipes();
        Log.d("TAG", "onCreate: ");

        makeShoppingList(rec.size());

        shoppingList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                System.out.println("click");
                myDb.delete(rec.get(i).getId());
                rec = myDb.getAllRecipes();
                makeShoppingList(rec.size());
                return true;
            }
        });

    }
    void makeShoppingList(int size){
        shoppingList.setAdapter(new FavouritesAdapter(getApplicationContext(), size));
        shoppingList.setLongClickable(true);
    }
}
