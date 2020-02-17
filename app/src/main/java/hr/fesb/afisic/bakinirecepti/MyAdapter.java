package hr.fesb.afisic.bakinirecepti;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

public class MyAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater mInflater;

    public MyAdapter(Context context) {
        mContext = context;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    //    Dvije ključne funkcije adaptera su:
//    - getCount() - vraća ukupni broj item-a na listi
    @Override
    public int getCount() {
        return DataStorage.recipeHashMap.size();
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
            view = mInflater.inflate(R.layout.list_view_single_item,
                    viewGroup, false);
        final ImageView imageTmb = (ImageView) view.findViewById(R.id.image_tmb);
        final TextView title = (TextView) view.findViewById(R.id.title);
        final TextView servings = (TextView) view.findViewById(R.id.servings);

        final Recipe recipe = DataStorage.recipeHashMap.get(i);

        title.setText(recipe.getTitle());
        servings.setText("Servings: " + recipe.getServings());
        new DownloadImageTask((ImageView) imageTmb.findViewById(R.id.image_tmb))
                .execute(recipe.getImageURL());
        return view;
    }
}
