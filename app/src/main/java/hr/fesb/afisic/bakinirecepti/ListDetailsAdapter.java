package hr.fesb.afisic.bakinirecepti;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.HashMap;

public class ListDetailsAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater mInflater;
    private int ID;
    private int ingredientsNumber;
    private double newServ;

    public ListDetailsAdapter(Context context, int ID, int ingredientsNumber, double newServ) {
        mContext = context;
        this.ID = ID;
        this.ingredientsNumber = ingredientsNumber;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.newServ = newServ;
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

        view = mInflater.inflate(R.layout.recipe_details_single_item_list,
                viewGroup, false);

        final TextView amount = (TextView) view.findViewById(R.id.amount);
        final TextView ingredientName = (TextView) view.findViewById(R.id.ingredientName);

        final Recipe recipe = DataStorage.recipeHashMap.get(ID);
        amount.setText(recipe.recipeIngHashMap.get(i).getAmount()*newServ +" "+ recipe.recipeIngHashMap.get(i).getUnit());
        ingredientName.setText(recipe.recipeIngHashMap.get(i).getName());
        return view;
    }
}