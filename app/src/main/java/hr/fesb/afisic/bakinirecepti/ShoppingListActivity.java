package hr.fesb.afisic.bakinirecepti;

import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.HashMap;

public class ShoppingListActivity extends AppCompatActivity {

    ListView shoppingList;
    int ID;
    Button saveList;
    EditText saveListName;
    ShoppingListDataSource myDs;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shop_list);

        saveList = (Button) findViewById(R.id.saveList);
        shoppingList = (ListView) findViewById(R.id.shopList);

        final Bundle extras = getIntent().getExtras();

        if(extras != null){
            ID = extras.getInt("Recipe ID");
        }
        final Recipe recipe = DataStorage.recipeHashMap.get(ID);
        saveList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveList();
                Toast.makeText(getApplicationContext(), "Shopping lista spremljena!",
                        Toast.LENGTH_LONG).show();
            }
        });

        makeShoppingList(recipe.recipeIngHashMap.size());

    }
    void makeShoppingList(int size){
        shoppingList.setAdapter(new ShoppingListAdapter(getApplicationContext(), ID, size));
    }

    void saveList(){
        myDs = new ShoppingListDataSource(getApplicationContext());
        myDs.open();
        Ingredients ing = new Ingredients();
        int itemsCount = shoppingList.getChildCount();
        for (int i = 0; i < itemsCount; i++) {
            View view = shoppingList.getChildAt(i);
            String amount = ((TextView) view.findViewById(R.id.ingAmount)).getText().toString();
            String unit = ((TextView) view.findViewById(R.id.ingUnit)).getText().toString();
            String ingredientName = ((TextView) view.findViewById(R.id.ingName)).getText().toString();
            myDs.addShoppingListToDb(Double.valueOf(amount), unit, ingredientName);
            finish();
        }

    }
}