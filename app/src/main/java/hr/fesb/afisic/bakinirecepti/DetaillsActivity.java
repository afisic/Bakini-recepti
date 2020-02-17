package hr.fesb.afisic.bakinirecepti;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DetaillsActivity extends AppCompatActivity {
    ImageView image;
    TextView servings;
    TextView servingsCount;
    ListView ingredientsList;
    Button shoppingListBtn;
    Button directionsBtn;
    Button plusServings;
    Button minusServings;
    int ID;


    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipe_details);

        servingsCount = (TextView) findViewById(R.id.servingsDetailsCount);
        ingredientsList = (ListView) findViewById(R.id.ingredientsDetailsList);
        image = (ImageView) findViewById(R.id.imageDetails);
        shoppingListBtn = (Button) findViewById(R.id.addToShoppingList);
        directionsBtn = (Button) findViewById(R.id.directionsBtn);
        plusServings = (Button) findViewById(R.id.servingsInc);
        minusServings = (Button) findViewById(R.id.servingsDec);




        final Bundle extras = getIntent().getExtras();
        if(extras != null){
            String value = extras.getString("Recipe ID");
            ID = Integer.parseInt(value);
            Recipe recipe = DataStorage.recipeHashMap.get(ID);
            setListParams(ID);
        }
        shoppingListBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startShoppingListActivity(ID);
            }
        });
        directionsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openURL();

            }
        });
        plusServings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ser = servingsCount.getText().toString();
                int a = Integer.parseInt(ser) + 1;
                servingsCount.setText(Integer.toString(a));
                double newServ = (double)a / (double)Integer.parseInt(ser);
                calcAmount(newServ);
            }
        });
        minusServings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ser = servingsCount.getText().toString();
                double a = Integer.parseInt(ser) - 1;
                double b = Integer.parseInt(ser);
                servingsCount.setText(Integer.toString((int) a));
                double newServ = (double) a / (double) b;
                calcAmount(newServ);
            }
        });
    }

    void openURL() {
        Recipe recipe = DataStorage.recipeHashMap.get(ID);
        Uri uri = Uri.parse(recipe.getSourceURL());
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }
    void setListParams(int ID){

        final Recipe recipe = DataStorage.recipeHashMap.get(ID);

        new DownloadImageTask((ImageView) image.findViewById(R.id.imageDetails))
                .execute(recipe.getImageURL());
        servingsCount.setText(" "+recipe.getServings());

        ingredientsList.setAdapter(new ListDetailsAdapter(getApplicationContext(), ID, recipe.recipeIngHashMap.size(), 1));

    }
    void startShoppingListActivity(int ID){
        Intent myIntent = new Intent(DetaillsActivity.this, ShoppingListActivity.class);
        myIntent.putExtra("Recipe ID", ID);
        startActivity(myIntent);
    }
    void calcAmount(double newServ){
        final Recipe recipe = DataStorage.recipeHashMap.get(ID);
        ingredientsList.setAdapter(new ListDetailsAdapter(getApplicationContext(), ID, recipe.recipeIngHashMap.size(), newServ ));
    }

}