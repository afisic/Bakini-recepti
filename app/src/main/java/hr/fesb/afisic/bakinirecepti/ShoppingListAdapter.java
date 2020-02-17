package hr.fesb.afisic.bakinirecepti;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ShoppingListAdapter extends BaseAdapter {
    private Context mContext;
    private LayoutInflater mInflater;
    private int ID;
    private int ingredientsNumber;

    public ShoppingListAdapter(Context context, int ID, int ingredientsNumber) {
        mContext = context;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.ID = ID;
        this.ingredientsNumber = ingredientsNumber;
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
            view = mInflater.inflate(R.layout.shop_list_single_item, viewGroup, false);

        final EditText amount = (EditText) view.findViewById(R.id.ingAmount);
        final TextView unit = (TextView) view.findViewById(R.id.ingUnit);
        final TextView ingredientName = (TextView) view.findViewById(R.id.ingName);

        Button plus = (Button) view.findViewById(R.id.plus);
        Button minus = (Button) view.findViewById(R.id.minus);

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

        final Recipe recipe = DataStorage.recipeHashMap.get(ID);

        amount.setText(recipe.recipeIngHashMap.get(i).getAmount().toString());
        unit.setText(recipe.recipeIngHashMap.get(i).getUnit());
        ingredientName.setText(recipe.recipeIngHashMap.get(i).getName());

        return view;
    }
}
