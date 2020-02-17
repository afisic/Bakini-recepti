package hr.fesb.afisic.bakinirecepti;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

public class FavouritesAdapter extends BaseAdapter {
    private Context mContext;
    private LayoutInflater mInflater;
    private int ingredientsNumber;
    ShoppingListDataSource myDs;
    ListView shoppingList;

    public FavouritesAdapter(Context context, int ingredientsNumber) {
        mContext = context;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.ingredientsNumber = ingredientsNumber;
        this.shoppingList = shoppingList;
    }

    @Override
    public int getCount() {
        return ingredientsNumber;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }


    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view == null)
            view = mInflater.inflate(R.layout.favourites_single_items, viewGroup, false);

        final EditText amount = (EditText) view.findViewById(R.id.ingAmountFav);
        final TextView unit = (TextView) view.findViewById(R.id.ingUnitFav);
        final TextView ingredientName = (TextView) view.findViewById(R.id.ingNameFav);
        final int id = Favourites.rec.get(i).getId();
        final int j = i;

        Button plus = (Button) view.findViewById(R.id.plusFav);
        Button minus = (Button) view.findViewById(R.id.minusFav);


        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Double aaplus = Double.parseDouble(amount.getText().toString()) + 0.5;
                amount.setText(aaplus.toString());
            }
        });
        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Double aaminus = 0.0;
                if(Double.valueOf(amount.getText().toString())>0.0) {
                    aaminus = Double.parseDouble(amount.getText().toString()) - 0.5;
                    amount.setText(aaminus.toString());
                }
            }
        });



        amount.setText(Favourites.rec.get(i).getAmount().toString());
        unit.setText(Favourites.rec.get(i).getUnit().toString());
        ingredientName.setText(Favourites.rec.get(i).getName().toString());

        return view;
    }

}