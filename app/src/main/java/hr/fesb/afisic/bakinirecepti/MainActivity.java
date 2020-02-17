package hr.fesb.afisic.bakinirecepti;


import android.content.Intent;
import android.preference.PreferenceActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;

import org.json.*;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {

    public static String apiUrl = "https://api.spoonacular.com/recipes/complexSearch/";
    public static String apiKey = "99a197ac7169452eafd8dc34fa599f6d";

    Button searchBtn;
    EditText searchText;
    TextView listText;
    String resultJSON;
    ListView myListView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        myToolbar.inflateMenu(R.menu.appbar);

        searchBtn = (Button) findViewById(R.id.searchButton);
        searchText = (EditText) findViewById(R.id.searchText);
        myListView = (ListView) findViewById(R.id.myListView);


        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HttpCall();
            }
        });

        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                startDetailsActivity(i);

            }
        });
        myToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {

            @Override
            public boolean onMenuItemClick(MenuItem item) {

                if(item.getItemId()==R.id.action_favorite)
                {
                    Intent myIntent = new Intent(MainActivity.this, Favourites.class);
                    startActivity(myIntent);
                }
                else{

                }

                return false;
            }
        });



    }


    void startDetailsActivity (int i) {

        Integer dataItem = i;
        Intent myIntent = new Intent(MainActivity.this, DetaillsActivity.class);
        myIntent.putExtra("Recipe ID", dataItem.toString());
        startActivity(myIntent);
    }

    void HttpCall(){

        final AsyncHttpClient myHttpClient = new AsyncHttpClient();

        RequestParams myApiParams = new RequestParams();
        myApiParams.add("apiKey",apiKey);
        myApiParams.add("query",searchText.getText().toString());
        myApiParams.add("addRecipeInformation", "true");
        myApiParams.add("fillIngredients", "true");
        myApiParams.add("number","10");
        myHttpClient.get(apiUrl, myApiParams, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable
                    throwable) {

                Toast.makeText(getApplicationContext(), "Greska u komunikaciji sa serverom",
                        Toast.LENGTH_LONG).show();
            }
            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {

                parseJSON(responseString);

            }
        });
    }

    void parseJSON(String resultJSON){

        try {

            JSONObject jObject = new JSONObject(resultJSON);
            JSONArray jArray = jObject.getJSONArray("results");
            for (int i=0; i < jArray.length(); i++)
            {
                try {
                    JSONObject oneObject = jArray.getJSONObject(i);
                    DataStorage.fillData(i, oneObject);
                } catch (JSONException e) {
                    Log.e("ANA", e.getMessage());
                    e.printStackTrace();
                }
            }
            myListView.setAdapter(new MyAdapter(getApplicationContext()));
        } catch (JSONException e) {
            Log.i("ANA", e.getMessage());
            e.printStackTrace();
        }

    }
}
